package service.facade;

import dto.UserDTO;
import entity.User;
import facade.UserFacade;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import service.BeanMappingService;
import service.UserService;

import javax.inject.Inject;
import java.util.Collection;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Václav Zouzalík
 */
@Service
@Transactional
public class UserFacadeImpl implements UserFacade {
    
    final static Logger log = LoggerFactory.getLogger(UserFacadeImpl.class);
    
    @Inject
    private UserService userService;
    
    @Inject
    private BeanMappingService beanMappingService;
    
    @Override
    public UserDTO createUser(UserDTO userDTO)
    {
        log.debug("Trying to create userDto {}", userDTO);
        User usr = beanMappingService.mapTo(userDTO, User.class);
        userService.createUser(usr, userDTO.getPassword());
        userDTO.setId(usr.getId());
        return userDTO;
    }
    
    @Override
    public boolean isEmployee(UserDTO u) {
        return userService.isEmployee(u.getId());
    }
    
    @Override
    public boolean authenticate(UserDTO u, String password)
    {
        return userService.authenticate(u.getId(), password);
    }
    
    @Override
    public UserDTO findById(Long id)
    {
        log.debug("Trying to find user with id {}", id);
        return beanMappingService.mapTo(userService.getUserById(id), UserDTO.class);
    }
    
    @Override
    public UserDTO findByEmail(String email)
    {
        log.debug("Trying to find user with email {}", email);
        return beanMappingService.mapTo(userService.getUserByEmail(email), UserDTO.class);
    }
    
    @Override
    public Collection<UserDTO> getAllUsers() {
        return beanMappingService.mapTo(userService.getAllUsers(), UserDTO.class);
    }

    @Override
    public void deleteUser(Long id){
        User user = new User();
        user.setId(id);
        userService.delete(user);
    }
}
