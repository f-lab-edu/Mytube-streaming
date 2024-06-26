package com.flab.Mytube.dto.user;

import jakarta.validation.ConstraintViolation;
import jakarta.validation.Validation;
import jakarta.validation.Validator;
import jakarta.validation.ValidatorFactory;
import org.junit.jupiter.api.Test;

import java.util.Set;

class UserDTOTest {
    ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
    Validator validator = factory.getValidator();

    @Test
    void validateUserDTO() {
        UserDTO userDTO = UserDTO.builder()
                .password("0000")
                .nickname("hello")
                .build();
        Set<ConstraintViolation<UserDTO>> violations = validator.validate(userDTO);
        for (ConstraintViolation<UserDTO> violation : violations) {
            System.err.println(violation.getMessage());
        }
    }
}
