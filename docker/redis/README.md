# Redis Best Practices

## 1. Key Naming

Use clear and consistent naming:

```
<namespace>:<object>:<id>[:<subkey>]
```

**Examples**

```
session:user:123
cache:product:5678
rate:login:192.168.1.1
auth:token:abcxyz
tmp:import:0e45ff92
```

👉 Use `:` as separator.
👉 Add namespace like `cache:` or `session:` to group keys.

---

## 2. Data Types

Use the right data type for each case:

| Type   | Use case                 | Example                            |
| ------ | ------------------------ | ---------------------------------- |
| String | JSON, token, small value | `SET user:1 '{"name":"Anh"}'`      |
| Hash   | Object with fields       | `HSET user:1 name Anh age 30`      |
| List   | Queue (FIFO)             | `LPUSH queue:email job1`           |
| Set    | Unique list              | `SADD user:1:roles admin editor`   |
| ZSet   | Ranking, score           | `ZADD leaderboard 1000 player:123` |

---

## 3. TTL and Memory Policy

Set TTL for cache and temp data.

| Data type     | TTL suggestion |
| ------------- | -------------- |
| Session       | 30–60 min      |
| Token         | 5–15 min       |
| Cache         | 1–10 min       |
| Static config | No TTL         |
| Rate limit    | 1–60 min       |

Redis config:

```bash
maxmemory 256mb
maxmemory-policy allkeys-lru
```

Or per key:

```bash
SET cache:user:123 "data" EX 600
```

---

## 4. Common Use Cases

| Use           | Description            |
| ------------- | ---------------------- |
| Cache layer   | Store hot data from DB |
| Session store | Keep user session      |
| Rate limiting | Control request rate   |
| Queue         | Simple background jobs |
| Pub/Sub       | Real-time event notify |
| Locking       | Prevent race condition |
| Temp data     | OTP, upload, verify    |

---

## 5. Memory and Key Grouping

* Use **prefix** to separate apps: `app1:`, `app2:`
* Use **group key** for easy delete:

  ```bash
  redis-cli --scan --pattern "cache:user:123:*" | xargs redis-cli del
  ```
* Use **hash** instead of many strings for related data.

---

## 6. Security

* Do **not expose port 6379** to the Internet
* Use password:

  ```bash
  requirepass myStrongPass
  ```
* Keep Redis inside Docker network or private subnet.
* Use separate Redis instance for cache and auth if needed.

---

## 7. Integration Example (Spring Boot)

```
Client → API → App → Redis → Database
```

```java
String key = "cache:user:" + userId;
String data = redis.get(key);
if (data == null) {
    data = userRepo.findById(userId);
    redis.set(key, data, 600); // 10 min cache
}
return data;
```

---

## Summary

| Topic      | Best Practice                   |
| ---------- | ------------------------------- |
| Key naming | `namespace:type:id`             |
| TTL        | 5–600 sec                       |
| Memory     | `allkeys-lru`                   |
| Type       | String, Hash, ZSet              |
| Security   | password + private network      |
| Structure  | split cache / session if needed |
