package com.zuluco.oculuz.controllers.user;

import com.zuluco.oculuz.models.dtos.DtoConverter;
import com.zuluco.oculuz.models.dtos.channel.ChannelMiniatureDTO;
import com.zuluco.oculuz.models.dtos.channel.ChannelPageDTO;
import com.zuluco.oculuz.models.dtos.user.UserPageDTO;
import com.zuluco.oculuz.models.entities.Channel;
import com.zuluco.oculuz.models.entities.RoleType;
import com.zuluco.oculuz.models.entities.User;
import com.zuluco.oculuz.payload.request.ChannelCreationRequest;
import com.zuluco.oculuz.repository.RoleRepository;
import com.zuluco.oculuz.security.jwt.JwtUtils;
import com.zuluco.oculuz.security.services.RoleService;
import com.zuluco.oculuz.security.services.UserDetailsServiceImpl;
import com.zuluco.oculuz.services.ChannelService;
import com.zuluco.oculuz.services.UserService;
import org.modelmapper.ConfigurationException;
import org.modelmapper.MappingException;
import org.modelmapper.ModelMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.crossstore.ChangeSetPersister;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.*;

import javax.management.InstanceAlreadyExistsException;
import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/user/{username}")
public class UserPageController {
    private static final Logger logger = LoggerFactory.getLogger(UserPageController.class);

    @Autowired
    private JwtUtils jwtUtils;

    @Autowired
    private UserService userService;

    @Autowired
    private ChannelService channelService;

    @Autowired
    private RoleRepository roleRepository;

    @Transactional
    public ChannelPageDTO tryToCreateChannel(String username, String channelName)
            throws InstanceAlreadyExistsException, ChangeSetPersister.NotFoundException {

        logger.info("Attempting to create channel with name: " + channelName + " for user: " + username);

        if (channelService.getChannelByChannelName(channelName).isPresent()) {
            logger.error("Channel \"" + channelName + "\" already exists");
            throw new InstanceAlreadyExistsException("Channel \"" + channelName + "\" already exists");
        }

        User author = userService.getUserByUsername(username).orElseThrow(
                () -> {
                    logger.error("User \"" + username + "\" not found");
                    return new UsernameNotFoundException("User \"" + username + "\" not found");
                }
        );

        channelService.createChannel(new Channel(channelName, author));

        logger.info("Channel created successfully");

        author.addRole(roleRepository.findByName(RoleType.ROLE_AUTHOR).get());

        return DtoConverter.convertChannelToChannelPageDto(channelService.getChannelByChannelName(channelName)
                .orElseThrow( ChangeSetPersister.NotFoundException::new ));
    }

    @Transactional
    @PostMapping("/create-channel")
    public ResponseEntity<ChannelPageDTO> createChannel(@PathVariable String username, @RequestBody ChannelCreationRequest channelCreationRequest) {
        try {
            logger.info("Received request to create channel with name: " + channelCreationRequest.getChannelName() + " for user: " + username);
            return ResponseEntity.ok(tryToCreateChannel(username, channelCreationRequest.getChannelName()));
        } catch (UsernameNotFoundException | ChangeSetPersister.NotFoundException e) {
            logger.error(e.getMessage());
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        } catch (InstanceAlreadyExistsException e) {
            logger.error(e.getMessage());
            return ResponseEntity.status(HttpStatus.CONFLICT).build();
        }
    }

    @GetMapping("/channels")
    public ResponseEntity<List<ChannelMiniatureDTO>> getChannels(@PathVariable String username) {
        try {
            return ResponseEntity.ok(userService.getUserChannels(username).stream()
                    .map(DtoConverter::convertChannelToChannelMiniatureDto)
                    .collect(Collectors.toList()));
        } catch (UsernameNotFoundException e) {
            logger.error(e.getMessage());
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }

}
