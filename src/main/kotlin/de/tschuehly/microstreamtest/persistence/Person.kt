package de.tschuehly.microstreamtest.persistence

class Person(
    override var id: Long?,
    val name: String,
    val age: Int
) : Entity