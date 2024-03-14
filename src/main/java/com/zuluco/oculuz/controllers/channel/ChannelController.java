package com.zuluco.oculuz.controllers.channel;

import com.zuluco.oculuz.controllers.user.AuthController;
import com.zuluco.oculuz.models.dtos.DtoConverter;
import com.zuluco.oculuz.models.dtos.channel.ChannelPageDTO;
import com.zuluco.oculuz.models.dtos.user.UserPageDTO;
import com.zuluco.oculuz.models.entities.Channel;
import com.zuluco.oculuz.models.entities.User;
import com.zuluco.oculuz.models.entities.intermediates.Subscription;
import com.zuluco.oculuz.payload.request.SubscriptionRequest;
import com.zuluco.oculuz.repository.SubscriptionRepository;
import com.zuluco.oculuz.security.jwt.JwtUtils;
import com.zuluco.oculuz.services.ChannelService;
import com.zuluco.oculuz.services.SubscriptionService;
import com.zuluco.oculuz.services.UserService;
import org.modelmapper.ConfigurationException;
import org.modelmapper.MappingException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.crossstore.ChangeSetPersister;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.*;

import java.sql.Date;
import java.util.Optional;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/channel")
public class ChannelController {
    private static final Logger logger = LoggerFactory.getLogger(AuthController.class);

    private ChannelPageDTO response = new ChannelPageDTO();

    @Autowired
    private JwtUtils jwtUtils;

    @Autowired
    private ChannelService channelService;

    @Autowired
    private UserService userService;

    @Autowired
    private SubscriptionService subscriptionService;

    @Autowired
    private SubscriptionRepository subscriptionRepository;


    private void tryToGetChannelPageDTO(String channelName) throws ChangeSetPersister.NotFoundException {
        System.out.println("tryToGetChannelPageDTO method called with channelName: " + channelName);
        Channel channel = channelService.getChannelByChannelName(channelName).orElseThrow(
                ChangeSetPersister.NotFoundException::new
        );
        this.response = DtoConverter.convertChannelToChannelPageDto(channel);
    }

    private void checkSubscribe(String username, String channelName) throws ChangeSetPersister.NotFoundException, UsernameNotFoundException {
        Channel channel = channelService.getChannelByChannelName(channelName).orElseThrow(
                ChangeSetPersister.NotFoundException::new
        );
        User user = userService.getUserByUsername(username).orElseThrow(
                () -> new UsernameNotFoundException("User \"" + username + "\" not found")
        );
        this.response.setIsSubscribed(subscriptionService.isUserSubscribedToChannel(user, channel));
    }

    @GetMapping("/{channelName}")
    public ResponseEntity<?> getChannelPage(@PathVariable String channelName,
                                         @RequestHeader(value = "Authorization", required = false) String authHeader) {
        try {
            tryToGetChannelPageDTO(channelName);
            if (authHeader != null && authHeader.startsWith("Bearer ")) {

                String jwt = authHeader.substring(7);
                if (jwtUtils.validateJwtToken(jwt))
                    checkSubscribe(jwtUtils.getUserNameFromJwtToken(jwt), channelName);
            }
            return ResponseEntity.ok(response);
        } catch (ChangeSetPersister.NotFoundException | UsernameNotFoundException e) {
            logger.error(e.getMessage());
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        } catch (Exception e) {
            logger.error(e.getMessage());
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }
    }
    @PostMapping("/{channelName}")
    public ResponseEntity<?> manageSubscription(@PathVariable String channelName,
                                                @RequestBody SubscriptionRequest subscriptionRequest,
                                                @RequestHeader(value="Authorization") String authHeader) {
        logger.info("manageSubscription method called with channelName: {}, action: {}", channelName, subscriptionRequest.getAction());
        try {
            if (authHeader != null && authHeader.startsWith("Bearer ")) {
                String jwt = authHeader.substring(7);
                if (jwtUtils.validateJwtToken(jwt)) {
                    String username = jwtUtils.getUserNameFromJwtToken(jwt);
                    User user = userService.getUserByUsername(username).orElseThrow(
                            () -> new UsernameNotFoundException("User \"" + username + "\" not found")
                    );
                    Channel channel = channelService.getChannelByChannelName(channelName).orElseThrow(
                            ChangeSetPersister.NotFoundException::new
                    );

                    if ("subscribe".equals(subscriptionRequest.getAction())) {
                        subscribe(user, channel);
                    } else if ("unsubscribe".equals(subscriptionRequest.getAction())) {
                        unsubscribe(user, channel);
                    } else {
                        logger.error("Invalid action");
                        return ResponseEntity.badRequest().body("Invalid action");
                    }
                    logger.info("Subscription action completed!");
                    return ResponseEntity.ok().build();
                }
            }
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Unauthorized request");
        } catch (ChangeSetPersister.NotFoundException | UsernameNotFoundException e) {
            logger.error(e.getMessage());
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        } catch (IllegalArgumentException e) {
            logger.error(e.getMessage());
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        } catch (Exception e) {
            logger.error(e.getMessage());
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }
    }

    private void subscribe(User user, Channel channel) {
        logger.info("subscribe method called for user: {}, channel: {}", user.getUsername(), channel.getName());
        if (user.equals(channel.getAuthor())) {
            throw new IllegalArgumentException("User cannot subscribe to their own channel");
        }
        Subscription subscription = new Subscription(user, channel, new Date( new java.util.Date().getTime()));
        subscriptionRepository.save(subscription);
    }

    private void unsubscribe(User user, Channel channel) {
        logger.info("unsubscribe method called for user: {}, channel: {}", user.getUsername(), channel.getName());
        if (user.equals(channel.getAuthor())) {
            throw new IllegalArgumentException("User cannot unsubscribe from their own channel");
        }
        Optional<Subscription> subscription = subscriptionRepository.findByUserAndChannel(user, channel);
        subscription.ifPresent(subscriptionRepository::delete);
    }
}
