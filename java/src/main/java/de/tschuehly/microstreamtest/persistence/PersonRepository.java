package de.tschuehly.microstreamtest.persistence;

import java.util.List;
import org.springframework.stereotype.Repository;

@Repository
public class PersonRepository extends CrudRepository<Person> {

  public PersonRepository(Root root) {
    super(root, root.persons);
  }

  public List<Person> findByName(String name){
    return readList(() -> root.persons.values().stream().filter(person -> person.name.equals(name)).toList());
  }
}
