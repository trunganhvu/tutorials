# Redis Docker Tutorials

A collection of Redis Docker tutorials from basic setup to high-availability configurations.

## 📚 Tutorials Overview

| Tutorial | Complexity | Purpose | Ports |
|----------|------------|---------|-------|
| [**redis-config**](#redis-config) | ⭐ Basic | Single instance with custom config | 6379 |
| [**redis-master-slave**](#redis-master-slave) | ⭐⭐ Intermediate | Read scaling with replication | 6379, 6479, 5540 |
| [**redis-master-slave-sentinel**](#redis-master-slave-sentinel) | ⭐⭐⭐ Advanced | High availability with failover | 6379, 6479, 26379, 5540 |
| [**redis-stack**](#redis-stack) | ⭐⭐ Development | Redis + RedisInsight GUI | 6379, 8001 |

## 🚀 Quick Start

```bash
# Choose your tutorial
cd redis-config  # or redis-master-slave, redis-master-slave-sentinel, redis-stack

# Start services
docker-compose up -d

# Connect to Redis
docker exec -it redis redis-cli -a yourStrongPassword123
```

## 📁 Tutorial Details

### Redis Config
**Single Redis instance with custom configuration**
- Custom Redis config with persistence (RDB + AOF)
- Password protection and memory management
- Perfect for learning Redis fundamentals

### Redis Master-Slave
**Master-slave replication for read scaling**
- Master (6379) for writes, Slave (6479) for reads
- RedisInsight web interface (5540)
- Learn replication and load balancing

### Redis Master-Slave-Sentinel
**High availability with automatic failover**
- Sentinel monitoring (26379)
- Automatic failover when master fails
- Production-ready setup

### Redis Stack
**Development environment with GUI**
- Latest Redis 7.2 with RedisInsight (8001)
- Modern Docker setup
- Perfect for development and learning

## 🔧 Common Commands

```bash
# Service management
docker-compose up -d          # Start
docker-compose down           # Stop
docker-compose logs -f        # View logs

# Redis operations
docker exec -it redis redis-cli -a yourStrongPassword123
docker exec -it redis-master redis-cli -a yourStrongPassword123 INFO replication

# Sentinel operations
docker exec -it sentinel1 redis-cli -p 26379 SENTINEL masters
```

## 🎯 Learning Path

1. **Beginner**: redis-config → redis-stack
2. **Intermediate**: redis-config → redis-master-slave
3. **Production**: All tutorials → Focus on Sentinel

## 🔒 Security

⚠️ **Change default password** `yourStrongPassword123` in production!

## 📊 Monitoring

- **RedisInsight**: http://localhost:5540 (master-slave) or http://localhost:8001 (stack)
- **Key metrics**: `INFO memory`, `INFO replication`, `SENTINEL masters`

## 🐛 Troubleshooting

| Issue | Solution |
|-------|----------|
| Container won't start | Check ports, view logs |
| Slave not connecting | Verify master IP in config |
| Data not persisting | Check volume mounts |

---

**Start with redis-config for basics, progress to master-slave for replication, and master-slave-sentinel for production!**
