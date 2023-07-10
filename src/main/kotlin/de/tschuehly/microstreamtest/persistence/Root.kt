package de.tschuehly.microstreamtest.persistence

import one.microstream.integrations.spring.boot.types.Storage
import java.util.concurrent.atomic.AtomicLong

@Storage
class Root {
    val persons = MutableMapStore<Person>()
}

class MutableMapStore<T>(
) {
    private var index: AtomicLong = AtomicLong(0)
    val map: MutableMap<Long, T> = mutableMapOf()
    fun newIndex(): Long {
        return index.incrementAndGet()
    }
}

interface Entity {
    var id: Long?

}

open class CrudRepository<T : Entity>(
    private val store: MutableMapStore<T>
) {
    fun getAll(): List<T> {
        return store.map.map { it.value }
    }

    fun getByIdOrNull(id: Long): T? {
        return store.map[id]
    }

    fun save(obj: T): T {
        (obj.id ?: store.newIndex()).let { id ->
            obj.id = id
            store.map[id] = obj
            return obj
        }

    }
}