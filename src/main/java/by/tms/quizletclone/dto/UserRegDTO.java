package by.tms.quizletclone.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserRegDTO {

    @Schema(example = "Диана")
    @NotNull @NotBlank
    private String firstName;

    @Schema(example = "Шурыгина")
    @NotNull @NotBlank
    private String lastName;

    @Schema(example = "example@gmail.com")
    @NotNull @NotBlank
    private String email;

    @Schema(example = "123")
    @NotNull
    private String password;

}
