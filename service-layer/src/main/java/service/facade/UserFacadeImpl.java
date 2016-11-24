package service.facade;

import dto.UserDTO;
import entity.User;
import facade.UserFacade;
import javax.inject.Inject;
import org.dozer.Mapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import service.UserService;
import org.apache.commons.lang3.Validate;
import service.BeanMappingService;

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
    @Inject
    private UserService userService;
    
    @Inject
    private Mapper dozer;
    
    @Inject
    private BeanMappingService beanMappingService;
    
    @Override
    public UserDTO createUser(UserDTO userDTO)
    {
        Validate.isTrue(userDTO.getId() == null);
        
        User user = convert(userDTO);
        User saved = userService.createUser(user.getGivenName(), user.getSurname(), user.getEmail(), user.getPasswordHash(), user.getPhone(), "natural", "employee");
        
        return convert(saved);
    }
    
    @Override
    public UserDTO findById(Long id)
    {
        return beanMappingService.mapTo(userService.getUserById(id), UserDTO.class);
    }
    
    @Override
    public UserDTO findByEmail(String email)
    {
        return beanMappingService.mapTo(userService.getUserByEmail(email), UserDTO.class);
    }
    
    private User convert(UserDTO dto) {
        return dozer.map(dto, User.class);
    }
    
    private UserDTO convert(User entity) {
        return dozer.map(entity, UserDTO.class);
    }
}
