/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package facade;

import dto.UserDTO;
import java.util.Collection;

/**
 *
 * @author Václav Zouzalík
 */
public interface UserFacade {
    
    /**
     * Create new user in the system
     * @param userDTO
     * @param password
     * @return newly created user
     */
    UserDTO createUser(UserDTO userDTO, String password);
    
    /**
     * Authenticate user in the system
     * @param u
     * @param password
     * @return true if the authentication was successfull
     */
    public boolean authenticate(UserDTO u, String password);
    
    /**
     * Check wheter a user has employee status or not
     * @param u
     * @return true when the user has employee status
     */
    public boolean isEmployee(UserDTO u);
   
    /**
     * Find user by id
     * @param id
     * @return found user or null
     */
    public UserDTO findById(Long id);
    
    /**
     * Find user by email
     * @param email
     * @return found user or null
     */
    public UserDTO findByEmail(String email);
    
    /**
     * Get all users
     * @return 
     */
    public Collection<UserDTO> getAllUsers();
}
