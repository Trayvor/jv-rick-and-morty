package mate.academy.rickandmorty.repository.character.spec;

import mate.academy.rickandmorty.model.Character;
import mate.academy.rickandmorty.repository.SpecificationProvider;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Component;

@Component
public class NameSpecificationProvider implements SpecificationProvider<Character> {
    public static final String SQL_WILDCARD = "%";
    public static final String NAME_KEY = "name";

    @Override
    public String getKey() {
        return NAME_KEY;
    }

    @Override
    public Specification<Character> getSpecification(String param) {
        String pattern = SQL_WILDCARD + param + SQL_WILDCARD;
        return (root, query, criteriaBuilder) ->
                criteriaBuilder.like(criteriaBuilder.lower(root.get("name")),
                        pattern.toLowerCase());
    }
}
