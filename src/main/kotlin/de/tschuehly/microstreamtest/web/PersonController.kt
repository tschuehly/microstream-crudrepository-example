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

    @GetMapping("")
    fun getAll(): List<Person> {
        return personRepository.getAll()
    }
}