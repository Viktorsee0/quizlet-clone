package by.tms.quizletclone.dto;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ModelChangeDTO {

    @NotBlank(message = "Name cannot be empty")
    private String name;
    private String description;


}
