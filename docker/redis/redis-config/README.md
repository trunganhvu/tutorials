# Redis Configuration with Docker

This project provides a complete Redis setup using Docker with custom configuration for production-ready deployment.

## Project Structure

```
├── data/
│   ├── appendonlydir/          # AOF persistence files
│   │   ├── appendonly.aof.1.base.rdb
│   │   ├── appendonly.aof.1.incr.aof
│   │   └── appendonly.aof.manifest
│   └── redis-server.log        # Redis server logs
├── docker-compose.yml          # Docker Compose configuration
├── redis.conf                  # Redis configuration file
└── README.md                   # This file
```

## Docker Configuration

### Docker Compose Setup
- **Redis Version**: 7.2 (latest stable)
- **Container Name**: redis
- **Restart Policy**: always (auto-restart on failure)
- **Port Mapping**: 6379:6379 (host:container)
- **Timezone**: Asia/Ho_Chi_Minh
- **Network**: Custom bridge network (redis-net)

### Volume Mounts
- `./redis.conf:/usr/local/etc/redis/redis.conf` - Custom Redis configuration
- `./data:/data` - Persistent data storage

## Redis Configuration Summary

### Basic Settings
- **Bind Address**: 0.0.0.0 (accepts connections from any IP)
- **Protected Mode**: Enabled (requires password authentication)
- **Port**: 6379 (default Redis port)

### Security
- **Password Protection**: `yourStrongPassword123`
- **Authentication Required**: Yes

### Memory Management
- **Max Memory**: 512MB
- **Eviction Policy**: allkeys-lru (Least Recently Used)
- **Purpose**: Cache-friendly configuration

### Persistence (Dual Strategy)
1. **RDB Snapshots**:
   - Save every 10 minutes if at least 1 key changed
   - Compression enabled
   - Filename: `dump.rdb`

2. **AOF (Append Only File)**:
   - Enabled for better durability
   - Sync frequency: every second
   - Files stored in `/data/appendonlydir/`

### Logging
- **Log File**: `/data/redis-server.log`
- **Log Level**: Default (notice)

### Performance Tuning
- **TCP Keepalive**: 300 seconds
- **Timeout**: 0 (no timeout)
- **Key Space Notifications**: Enabled for expired keys

## Quick Start

### Prerequisites
- Docker and Docker Compose installed
- Port 6379 available on host

### Running Redis
```bash
# Start Redis container
docker-compose up -d

# View logs
docker-compose logs -f redis

# Stop Redis container
docker-compose down
```

### Connecting to Redis
```bash
# Using Docker
docker exec -it redis redis-cli -a yourStrongPassword123
```

## Data Persistence

The setup uses both RDB and AOF persistence:
- **RDB**: Periodic snapshots for quick recovery
- **AOF**: Continuous logging for maximum durability
- **Data Location**: `./data/` directory (persistent across container restarts)

## Security Considerations

⚠️ **Important**: Change the default password in `redis.conf` before production use:
```
requirepass yourStrongPassword123
```

## Monitoring

- **Logs**: Check `./data/redis-server.log` for Redis operations
- **Memory Usage**: Monitor with `INFO memory` command
- **Persistence**: Check AOF files in `./data/appendonlydir/`

## Configuration Files

- **docker-compose.yml**: Container orchestration
- **redis.conf**: Redis server configuration
- **data/**: Persistent storage directory

This setup provides a production-ready Redis instance with proper persistence, security, and monitoring capabilities.