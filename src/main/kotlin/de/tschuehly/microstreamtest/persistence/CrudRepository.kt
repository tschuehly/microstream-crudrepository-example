package de.tschuehly.microstreamtest.persistence

open class CrudRepository<T : Entity>(
    open val root: Root,
    private val mutableMap: MutableMap<Long, T>,
) {

    fun getAll(): List<T> {
        return mutableMap.values.toList()
    }

    fun getByIdOrNull(id: Long): T? {
        return mutableMap[id]
    }

    fun save(obj: T): T {
        (obj.id ?: root.newIndex()).let { id ->
            obj.id = id
            mutableMap[id] = obj
            store()
            return obj
        }
    }

    fun saveAll(objList: List<T>): List<T> {
        val objMap = objList.map { obj ->
            (obj.id ?: root.newIndex()).let { id ->
                obj.id = id
                return@map Pair(id,obj)
            }
        }.toMap()
        mutableMap.putAll(objMap)
        store()
        return objMap.values.toList()
    }
    fun store(){
        root.store(mutableMap)
    }
}