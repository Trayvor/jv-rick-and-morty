package mate.academy.rickandmorty.dto.internal;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class CharacterDto {
    @NotBlank
    private Long id;
    @NotBlank
    private Long externalId;
    @NotBlank
    private String name;
    @NotBlank
    private String status;
    @NotBlank
    private String gender;
}
