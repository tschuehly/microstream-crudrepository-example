package de.tschuehly.microstreamtest.web;

import de.tschuehly.microstreamtest.persistence.Person;
import de.tschuehly.microstreamtest.persistence.PersonRepository;
import java.util.List;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/person")
public class PersonController {
  private final PersonRepository personRepository;

  public PersonController(PersonRepository personRepository) {
    this.personRepository = personRepository;
  }

  @PostMapping("")
  Person savePerson(@RequestBody Person person){
    return personRepository.save(person);
  }
  @PostMapping("/saveAll")
  List<Person> saveAllPerson(
      @RequestBody List<Person> personList
  ) {
    return personRepository.saveAll(personList);
  }
  @GetMapping("")
  List<Person> getAll(){
    return personRepository.getAll();
  }


  @GetMapping("/findByName")
  List<Person> findByName(
      @RequestParam String name
  ){
    return personRepository.findByName(name);
  }

}
