package com.zuluco.oculuz.controllers.user;

import com.zuluco.oculuz.models.dtos.DtoConverter;
import com.zuluco.oculuz.models.dtos.user.UserPageDTO;
import com.zuluco.oculuz.models.entities.User;
import com.zuluco.oculuz.security.jwt.JwtUtils;
import com.zuluco.oculuz.security.services.UserDetailsServiceImpl;
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


public class UserPageController {

}
