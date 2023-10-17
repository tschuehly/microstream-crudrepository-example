package de.tschuehly.microstreamtest

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication
class MicrostreamTestApplication

fun main(args: Array<String>) {
    runApplication<MicrostreamTestApplication>(*args)
}
