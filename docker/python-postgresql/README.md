## Getting started
```sh
docker-compose up -d
```

## URL
http://localhost:8088/


`docker-compose.yml`: Docker Compose configuration file.\
`Dockerfile`: Docker configuration file.\
`entrypoint.sh`: Entrypoint script that runs on container startup (Check the PostgreSQL health).\
`webapp/app.py`: Flask application code.\
`webapp/requirements.txt`: Python requirements file.\
`webapp/scripts/helloworld-go`: Go application that is run inside the Flask application.