package de.tschuehly.microstreamtest.persistence

import java.util.concurrent.locks.ReentrantReadWriteLock
import java.util.function.Supplier

open class CrudRepository<T : Entity>(
    open val root: Root,
    private val mutableMap: MutableMap<Long, T>,
) {
    private val lock: ReentrantReadWriteLock = ReentrantReadWriteLock()
    fun getAll(): List<T> {
        return readAction {
            mutableMap.values.toList()
        }
    }

    fun getByIdOrNull(id: Long): T? {
        return readAction{
            mutableMap[id]
        }
    }

    fun save(obj: T): T {
        return writeAction{
            (obj.id ?: root.newIndex()).let { id ->
                obj.id = id
                mutableMap[id] = obj
                store()
            }
            obj
        }

    }

    fun saveAll(objList: List<T>): List<T> {
        return writeAction{
            val objMap = objList.map { obj ->
                (obj.id ?: root.newIndex()).let { id ->
                    obj.id = id
                    return@map Pair(id,obj)
                }
            }.toMap()
            mutableMap.putAll(objMap)
            store()
            objMap.values.toList()
        }
    }
    private fun store(){
        root.store(mutableMap)
    }

    open fun <T> readAction(supplier: Supplier<T>): T {
        lock.readLock().lock()
        return try {
            supplier.get()
        } finally {
            lock.readLock().unlock()
        }
    }

    open fun <T> writeAction(supplier: Supplier<T>): T {
        lock.writeLock().lock()
        return try {
            supplier.get()
        } finally {
            lock.writeLock().unlock()
        }
    }
}