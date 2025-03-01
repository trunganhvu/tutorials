#!/bin/sh

# Read secrets and set password
if [ -f "/run/secrets/db-password" ]; then
  export POSTGRES_PASSWORD=$(cat /run/secrets/db-password)
fi

exec java -cp app:app/lib/* org.anhvt.spring.Application
