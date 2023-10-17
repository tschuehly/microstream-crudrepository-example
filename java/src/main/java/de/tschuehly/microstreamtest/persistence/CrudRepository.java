package de.tschuehly.microstreamtest.persistence;

import java.util.List;
import java.util.Map;
import java.util.concurrent.locks.ReentrantReadWriteLock;
import java.util.function.Supplier;

public class CrudRepository<T extends Entity> {

  protected final Root root;
  protected final Map<Long, T> dataHashMap;

  public CrudRepository(Root root, Map<Long, T> dataHashMap) {
    this.root = root;
    this.dataHashMap = dataHashMap;
  }

  private final ReentrantReadWriteLock lock = new ReentrantReadWriteLock();

  public List<T> getAll() {
    return readList(() -> dataHashMap.values().stream().toList());
  }

  public T save(T obj) {
    return writeSingle(() -> saveAndStore(obj));
  }

  private T saveAndStore(T obj) {
    saveToMap(obj);
    store();
    return obj;
  }

  private T saveToMap(T obj) {
    var id = obj.getId().orElse(root.newIndex());
    obj.setId(id);
    dataHashMap.put(id, obj);
    return obj;
  }


  public List<T> saveAll(List<T> objList) {
    return writeList(() -> {
          var savedObj = objList.stream().map(this::saveToMap).toList();
          store();
          return savedObj;
        }
    );
  }

  private void store() {
    root.store(dataHashMap);
  }

  T writeSingle(Supplier<T> supplier) {
    lock.writeLock().lock();
    try {
      return supplier.get();
    } finally {
      lock.writeLock().unlock();
    }
  }

  List<T> writeList(Supplier<List<T>> supplier) {
    lock.writeLock().lock();
    try {
      return supplier.get();
    } finally {
      lock.writeLock().unlock();
    }
  }

  List<T> readList(Supplier<List<T>> supplier) {

    lock.readLock().lock();
    try {
      return supplier.get();
    } finally {
      lock.readLock().unlock();
    }
  }

  T readSingle(Supplier<T> supplier) {

    lock.readLock().lock();
    try {
      return supplier.get();
    } finally {
      lock.readLock().unlock();
    }
  }

}
