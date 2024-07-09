#!/bin/bash

PLAYERS_FILE="player.csv" ./gradlew clean build
docker build -t playerapp .
