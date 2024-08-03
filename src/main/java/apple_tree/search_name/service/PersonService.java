package apple_tree.search_name.service;

import apple_tree.search_name.dto.PersonDTO;
import apple_tree.search_name.entity.PersonEntity;
import apple_tree.search_name.repository.PersonRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class PersonService {

    @Autowired
    private PersonRepository personRepository;

    public Optional<PersonDTO> findByName(String name) {
        return personRepository.findByName(name).map(this::convertToDTO);
    }

    public List<PersonDTO> findAll() {
        return personRepository.findAll().stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }

    public PersonDTO save(PersonDTO personDTO) {
        PersonEntity personEntity = convertToEntity(personDTO);
        PersonEntity savedEntity = personRepository.save(personEntity);
        return convertToDTO(savedEntity);
    }

    public void deleteById(Long id) {
        personRepository.deleteById(id);
    }

    public Optional<PersonDTO> findById(Long id) {
        return personRepository.findById(id).map(this::convertToDTO);
    }

    public PersonDTO update(PersonDTO personDTO) {
        PersonEntity personEntity = convertToEntity(personDTO);
        PersonEntity updatedEntity = personRepository.save(personEntity);
        return convertToDTO(updatedEntity);
    }

    private PersonDTO convertToDTO(PersonEntity personEntity) {
        PersonDTO personDTO = new PersonDTO();
        personDTO.setId(personEntity.getId());
        personDTO.setName(personEntity.getName());
        personDTO.setFloor(personEntity.getFloor());
        return personDTO;
    }

    private PersonEntity convertToEntity(PersonDTO personDTO) {
        PersonEntity personEntity = new PersonEntity();
        personEntity.setId(personDTO.getId());
        personEntity.setName(personDTO.getName());
        personEntity.setFloor(personDTO.getFloor());
        return personEntity;
    }
}
