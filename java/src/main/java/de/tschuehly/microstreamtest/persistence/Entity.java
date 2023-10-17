package de.tschuehly.microstreamtest.persistence;

import java.util.Optional;

abstract class Entity {

  private Optional<Long> id = Optional.empty();


  public Optional<Long> getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = Optional.of(id);
  }
}
