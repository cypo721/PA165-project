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
    UserDTO createUser(UserDTO userDTO, String password);
    
    public boolean authenticate(UserDTO u, String password);
    
    public boolean isEmployee(UserDTO u);
   
    public UserDTO findById(Long id);
    
    public UserDTO findByEmail(String email);
    
    public Collection<UserDTO> getAllUsers();
}
