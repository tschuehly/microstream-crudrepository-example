package de.tschuehly.microstreamtest.persistence;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.atomic.AtomicLong;
import one.microstream.integrations.spring.boot.types.Storage;
import one.microstream.storage.types.StorageManager;
import org.springframework.beans.factory.annotation.Autowired;

@Storage
public class Root {
  @Autowired
  private StorageManager storageManager;

  private final AtomicLong index = new AtomicLong(0);

  Map<Long, Person> persons = new HashMap<>();
  public Long newIndex(){
    var newIndex = index.incrementAndGet();
    storageManager.store(index);
    return newIndex;
  }

  public  <T> void store(Map<Long,T> map){
    storageManager.store(map);
  }

}
