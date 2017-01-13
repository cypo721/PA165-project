/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package validators;

import org.springframework.validation.Errors;

/**
 *
 * @author eduard
 */
public class RevisionDtoValidator {
     public boolean supports(Class<?> clazz) {
        return false;
    }

    public void validate(Object target, Errors errors) {

    }
}
