package de.tschuehly.microstreamtest.persistence

import org.springframework.stereotype.Repository

@Repository
class PersonRepository(
    root: Root
): CrudRepository<Person>(root.persons)