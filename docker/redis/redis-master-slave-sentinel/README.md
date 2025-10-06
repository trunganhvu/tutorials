# Redis Master-Slave-Sentinel with Docker

A Redis high-availability setup with master-slave replication and Sentinel automatic failover using Docker Compose.

## Architecture Flow

```
┌─────────────────────────────────────────────────────────────────┐
│                        Docker Network                          │
│                                                                 │
│  ┌─────────────┐    ┌─────────────┐    ┌─────────────────────┐  │
│  │   Master    │    │    Slave     │    │     Sentinel       │  │
│  │  :6379      │◄───┤   :6479      │    │      :26379        │  │
│  │ (Writes)    │    │  (Reads)     │    │  (Monitoring)      │  │
│  └─────────────┘    └─────────────┘    └─────────────────────┘  │
│         ▲                   ▲                   │                │
│         │                   │                   │                │
│         └───────────────────┼───────────────────┘                │
│                             │                                    │
│  ┌─────────────────────────────────────────────────────────────┐  │
│  │                RedisInsight :5540                           │  │
│  │              (Web Management UI)                            │  │
│  └─────────────────────────────────────────────────────────────┘  │
└─────────────────────────────────────────────────────────────────┘
```

## Quick Start

```bash
# Start all services
docker-compose up -d

# Verify running
docker-compose ps

# Access RedisInsight
open http://localhost:5540
```

## Connection Details

| Service | Host | Port | Password | Purpose |
|---------|------|------|----------|---------|
| **Master** | localhost | 6379 | yourStrongPassword123 | Writes |
| **Slave** | localhost | 6479 | yourStrongPassword123 | Reads |
| **Sentinel** | localhost | 26379 | - | Failover |
| **RedisInsight** | localhost | 5540 | - | Management |

## Testing

### Basic Operations
```bash
# Write to master
docker exec -it redis-master redis-cli -a yourStrongPassword123
SET test "Hello World"

# Read from slave
docker exec -it redis-slave redis-cli -a yourStrongPassword123
GET test
```

### Failover Test
```bash
# 1. Check current master
docker exec -it sentinel1 redis-cli -p 26379 SENTINEL masters

# 2. Stop master (triggers failover)
docker stop redis-master

# 3. Check new master (slave promoted)
docker exec -it sentinel1 redis-cli -p 26379 SENTINEL masters

# 4. Restore original setup
docker start redis-master
```

## Configuration Files

| File | Purpose |
|------|---------|
| `docker-compose.yml` | Service definitions |
| `redis-master.conf` | Master Redis config |
| `redis-slave.conf` | Slave Redis config |
| `sentinel.conf` | Sentinel monitoring config |

## Key Features

✅ **High Availability**: Automatic failover with Sentinel  
✅ **Read Scaling**: Master for writes, slave for reads  
✅ **Monitoring**: RedisInsight web interface  
✅ **Persistence**: AOF enabled on master  
✅ **Security**: Password authentication  


## Management Commands

```bash
# View logs
docker-compose logs -f

# Check replication
docker exec -it redis-master redis-cli -a yourStrongPassword123 INFO replication

# Check Sentinel status
docker exec -it sentinel1 redis-cli -p 26379 SENTINEL masters

# Stop all services
docker-compose down
```

## Production Notes

- Change default passwords
- Use Docker secrets for sensitive data
- Consider multiple Sentinel instances for better quorum
- Monitor replication lag
- Implement proper backup strategies

## Troubleshooting

| Issue | Solution |
|-------|----------|
| Slave not connecting | Check master IP in `redis-slave.conf` |
| Sentinel not detecting master | Verify IP in `sentinel.conf` |
| Data not replicating | Check `INFO replication` on both instances |
| Memory issues | Adjust `maxmemory` in configs |

---

**Quick Reference**: `docker-compose up -d` → `http://localhost:5540` → Test failover with `docker stop redis-master`