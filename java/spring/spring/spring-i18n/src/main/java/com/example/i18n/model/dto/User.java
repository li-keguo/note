package com.example.i18n.model.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.URL;
import org.springframework.lang.Nullable;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;

/**
 * User.
 *
 * @author <a href='mailto:likeguo@apache.org'> likeguo </a>
 */
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class User {
    
    private Long id;
    
    @NotNull
    @Length(min = 5, max = 200)
    private String name;
    
    @NotNull
    @Email
    private String email;
    
    @NotNull
    private String phone;
    
    @Nullable
    @URL
    private String blogUrl;
}
