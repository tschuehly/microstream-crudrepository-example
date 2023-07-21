package de.tschuehly.microstreamtest.persistence

import one.microstream.integrations.spring.boot.types.Storage
import one.microstream.storage.types.StorageManager
import org.springframework.beans.factory.annotation.Autowired
import java.util.concurrent.atomic.AtomicLong

@Storage
class Root {
    @Autowired
    @Transient
    private lateinit var storageManager: StorageManager
    private val index: AtomicLong = AtomicLong(0)

    val personMap = mutableMapOf<Long, Person>()

    fun newIndex(): Long {
        val newIndex = index.incrementAndGet()
        storageManager.store(index)
        return newIndex
    }

    fun <T> store(map: MutableMap<Long, T>) {
        storageManager.store(map)
    }
}

