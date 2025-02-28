#!/bin/bash

CONTAINER_ID=''

while getopts i: flag
do
    case "${flag}" in
        i) CONTAINER_ID=${OPTARG};;
    esac
done

echo 'Started coping from '$CONTAINER_ID

# === FILES and DIRECTORIES
# dist
sudo rm -rf ./dist
sudo docker cp $CONTAINER_ID:/vue/dist ./vue/dist

# node_modules
sudo rm -rf ./node_modules
sudo docker cp $CONTAINER_ID:/vue/node_modules ./vue/node_modules

# package-lock.json
sudo rm -rf ./package-lock.json
sudo docker cp $CONTAINER_ID:/vue/package-lock.json ./vue/package-lock.json
# === FILES and DIRECTORIES

echo 'Success copied from '$CONTAINER_ID
