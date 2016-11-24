/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package facade;

import dto.UserDTO;

/**
 *
 * @author venca
 */
public interface UserFacade {
    UserDTO createUser(UserDTO userDTO, String password);
   
    public UserDTO findById(Long id);
    
    public UserDTO findByEmail(String email);
}
