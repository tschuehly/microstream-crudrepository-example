package de.tschuehly.microstreamtest.persistence;

public class Person extends Entity {
    public String name;
    public Integer age;

  protected Person(String name, Integer age) {
    this.name = name;
    this.age = age;
  }
}
