package validators;

import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

/**
 * Created by pato on 16.12.2016.
 */
public class MachineDtoValidator{
    public boolean supports(Class<?> clazz) {
        return false;
    }

    public void validate(Object target, Errors errors) {

    }
}
