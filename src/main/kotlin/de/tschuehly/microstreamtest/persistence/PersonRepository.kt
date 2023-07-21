package de.tschuehly.microstreamtest.persistence

import org.springframework.stereotype.Repository

@Repository
class PersonRepository(
    override val root: Root
) : CrudRepository<Person>(root, root.personMap){
    fun findByName(name: String): List<Person> {
        return root.personMap.values.filter {
            it.name == name
        }
    }
}