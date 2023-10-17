package de.tschuehly.microstreamtest.web

import de.tschuehly.microstreamtest.persistence.Person
import de.tschuehly.microstreamtest.persistence.PersonRepository
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/api/person")
class PersonController(
    val personRepository: PersonRepository
) {
    @PostMapping("")
    fun savePerson(
        @RequestBody person: Person
    ): Person {
        return personRepository.save(person)
    }

    @PostMapping("/saveAll")
    fun saveAllPerson(
        @RequestBody personList: List<Person>
    ): List<Person> {
        return personRepository.saveAll(personList)
    }

    @GetMapping("/findByName")
    fun findByName(
        @RequestParam name: String
    ) = personRepository.findByName(name)

    @GetMapping("")
    fun getAll(): List<Person> {
        return personRepository.getAll()
    }
}