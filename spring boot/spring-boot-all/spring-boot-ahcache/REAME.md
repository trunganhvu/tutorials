# Config ehcache
Dependency data redis pom.xml
```shell
<!-- https://mvnrepository.com/artifact/org.ehcache/ehcache -->
<dependency>
    <groupId>org.ehcache</groupId>
    <artifactId>ehcache</artifactId>
    <version>3.8.1</version>
</dependency>
```

ehcache.xml
```xml
<?xml version="1.0" encoding="UTF-8"?>
<ehcache xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
         xsi:noNamespaceSchemaLocation="ehcache.xsd">
    <defaultCache
            maxElementsInMemory="10000"
            eternal="false"
            timeToIdleSeconds="3600"
            timeToLiveSeconds="0"
            overflowToDisk="false"
            diskPersistent="false"
            diskExpiryThreadIntervalSeconds="120" />

    <cache
            name="tutorial"
            maxEntriesLocalHeap="2000"
            eternal="false"
            timeToIdleSeconds="3600"
            timeToLiveSeconds="0"
            overflowToDisk="false"
            statistics="true">
    </cache>
</ehcache>
```

Cấu hình cache service với 
* @CacheConfig: Cache vơi class
* @Cacheable: Lưu cache thay vì xử lý từ methods
* @CacheEvict: Xoá cache hoặc thay đổi dữ liệu trong cache
* @CachePut: Lưu hoặc thay đổi dữ liệu trong cache mà không kiểm tra trước

```java
@CacheConfig(cacheNames = "tutorial")
public class TutorialService {

    @Cacheable(value = "tutorials")
    public List<Tutorial> findAll() {
        doLongRunningTask();
        return tutorialRepository.findAll();
    }

    @Cacheable(value = "tutorial", key = "#id")
    public Optional<Tutorial> findById(long id) {
        doLongRunningTask();
        return tutorialRepository.findById(id);
    }

    @CacheEvict(value = { "tutorials" }, allEntries = true)
    public Tutorial save(Tutorial tutorial) {
        return tutorialRepository.save(tutorial);
    }

    @CachePut(value = "tutorial", key = "#tutorial.id")
    public Tutorial update(Tutorial tutorial) {
        return tutorialRepository.save(tutorial);
    }

    @CacheEvict(value = "tutorial", key = "#id")
    public void deleteById(long id) {
        tutorialRepository.deleteById(id);
    }

    @CacheEvict(value = { "tutorial", "tutorials" }, allEntries = true)
    public void deleteAll() {
        tutorialRepository.deleteAll();
    }

    private void doLongRunningTask() {
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}

```