package apple_tree.search_name.repository;

import apple_tree.search_name.entity.PersonEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface PersonRepository extends JpaRepository<PersonEntity, Long> {
    Optional<PersonEntity> findByName(String name);
}
