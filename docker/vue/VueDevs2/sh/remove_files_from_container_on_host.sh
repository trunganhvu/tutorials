#!/bin/bash

echo 'Started removing'

# === FILES and DIRECTORIES
# dist
sudo rm -rf ./dist

# dist
sudo rm -rf ./node_modules

# package-lock.json
sudo rm -rf ./package-lock.json
# === FILES and DIRECTORIES

echo 'Success removed'
