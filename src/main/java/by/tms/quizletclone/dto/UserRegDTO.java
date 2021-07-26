package by.tms.quizletclone.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserRegDTO {

    @Pattern(regexp = "\\b[a-zA-Z0-9._%-]+@[a-zA-Z]+\\.[a-zA-Z]{2,3}\\b", message = "The field must be in the format of an email address")
    private String email;

    @NotBlank(message = "Password cannot be empty")
    @Length(min = 3, message = "The field must be at least 3 characters")
    private String password;

    private String confirmPassword;
}
