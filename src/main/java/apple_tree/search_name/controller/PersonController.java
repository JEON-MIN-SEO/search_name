package apple_tree.search_name.controller;

import apple_tree.search_name.dto.PersonDTO;
import apple_tree.search_name.service.PersonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@Controller
public class PersonController {

    @Autowired
    private PersonService personService;

    @GetMapping("/")
    public String home() {
        return "home";
    }

    @PostMapping("/search")
    public String search(@RequestParam String name, Model model) {
        Optional<PersonDTO> person = personService.findByName(name);
        if (person.isPresent()) {
            model.addAttribute("person", person.get());
        } else {
            model.addAttribute("message", "Person not found");
        }
        return "result";
    }

    @GetMapping("/manage")
    public String manage(Model model) {
        List<PersonDTO> people = personService.findAll();
        model.addAttribute("people", people);
        return "manage";
    }

    @PostMapping("/manage/add")
    public String add(@RequestParam String name, @RequestParam int floor) {
        PersonDTO person = new PersonDTO();
        person.setName(name);
        person.setFloor(floor);
        personService.save(person);
        return "redirect:/manage";
    }

    @PostMapping("/manage/update")
    public String update(@RequestParam Long id, @RequestParam String name, @RequestParam int floor) {
        PersonDTO person = new PersonDTO();
        person.setId(id);
        person.setName(name);
        person.setFloor(floor);
        personService.update(person);
        return "redirect:/manage";
    }

    @PostMapping("/manage/delete")
    public String delete(@RequestParam Long id) {
        personService.deleteById(id);
        return "redirect:/manage";
    }
}
