package com.example.springi18ndemo.model.dto;

import com.example.i18n.model.dto.User;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import java.util.Objects;
import java.util.Set;

/**
 * UserTest.
 *
 * @author <a href='mailto:likeguo@apache.org'> likeguo </a>
 */
public class UserTest {
    private static final Validator validator = Validation.buildDefaultValidatorFactory().getValidator();
    
    @Test
    public void validTest() {
        final User user = new User();
        final Set<ConstraintViolation<User>> validate = validator.validate(user);
        for (ConstraintViolation<User> violation : validate) {
            assertError(violation, "name", "不能为null");
            assertError(violation, "email", "不能为null");
            assertError(violation, "phone", "不能为null");
        }
        System.out.println(validate);
    }
    
    @Test
    public void validTest2() {
        final User user = User.builder()
                .name("test")
                .email("test")
                .blogUrl("test")
                .build();
        final Set<ConstraintViolation<User>> validate = validator.validate(user);
        for (ConstraintViolation<User> violation : validate) {
            assertError(violation, "email", "不是一个合法的电子邮件地址");
            assertError(violation, "phone", "不能为null");
            assertError(violation, "blogUrl", "需要是一个合法的URL");
        }
        System.out.println(validate);
    }
    
    @Test
    public void test() {
        final User me = User.builder()
                .name("likeguo")
                .build();
        final User bigBady = User.builder()
                .name("zhanglei")
                .build();
        
        
    }
    

    
    private static void assertError(ConstraintViolation<User> violation, String property, String message) {
        if (Objects.equals(violation.getPropertyPath().toString(), property)) {
            Assertions.assertEquals(message, violation.getMessage());
        }
    }
}
