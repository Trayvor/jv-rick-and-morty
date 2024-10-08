package mate.academy.rickandmorty.mapper;

import java.util.List;
import mate.academy.rickandmorty.config.MapperConfig;
import mate.academy.rickandmorty.dto.external.ExternalCharacterDto;
import mate.academy.rickandmorty.dto.internal.CharacterDto;
import mate.academy.rickandmorty.model.Character;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(config = MapperConfig.class)
public interface CharacterMapper {
    @Mapping(source = "id", target = "externalId")
    Character toModel(ExternalCharacterDto externalCharacterDto);

    @Mapping(source = "id", target = "externalId")
    List<Character> toModelList(List<ExternalCharacterDto> externalCharacterDtoList);

    CharacterDto toDto(Character character);

    List<CharacterDto> toDtoList(List<Character> characterList);
}
