package mate.academy.rickandmorty.service.impl;

import java.util.List;
import java.util.Random;
import lombok.RequiredArgsConstructor;
import mate.academy.rickandmorty.dto.external.ExternalCharacterDto;
import mate.academy.rickandmorty.dto.internal.CharacterDto;
import mate.academy.rickandmorty.dto.internal.CharacterSearchParameters;
import mate.academy.rickandmorty.exception.CharacterNotFoundException;
import mate.academy.rickandmorty.mapper.CharacterMapper;
import mate.academy.rickandmorty.model.Character;
import mate.academy.rickandmorty.repository.SpecificationBuilder;
import mate.academy.rickandmorty.repository.character.CharacterRepository;
import mate.academy.rickandmorty.service.CharacterService;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CharacterServiceImpl implements CharacterService {
    public static final int MIN_ID = 1;

    private final CharacterMapper characterMapper;
    private final CharacterRepository characterRepository;
    private final SpecificationBuilder<Character> specificationBuilder;

    @Override
    public void saveAll(List<ExternalCharacterDto> externalCharacterList) {
        List<Character> characters = externalCharacterList.stream()
                .map(characterMapper::toModel)
                .toList();
        characterRepository.saveAll(characters);
    }

    @Override
    public List<CharacterDto> findAll() {
        List<Character> characterModelList = characterRepository.findAll();
        List<CharacterDto> characterDtoList = characterModelList.stream()
                .map(characterMapper::toDto)
                .toList();
        return characterDtoList;
    }

    @Override
    public Long countAll() {
        return characterRepository.count();
    }

    @Override
    public CharacterDto getRandomCharacter() {
        Random random = new Random();
        Long randomId = random.nextLong(characterRepository.count() - MIN_ID + 1) + MIN_ID;
        Character character = characterRepository.findById(randomId).orElseThrow(() ->
                new CharacterNotFoundException("Can`t find random character in database"));
        return characterMapper.toDto(character);
    }

    @Override
    public List<CharacterDto> search(CharacterSearchParameters characterSearchParameters) {
        Specification<Character> characterSpecification = specificationBuilder
                .build(characterSearchParameters);
        return characterRepository.findAll(characterSpecification)
                .stream()
                .map(characterMapper::toDto)
                .toList();
    }
}
