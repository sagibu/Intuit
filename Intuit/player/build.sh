#!/bin/bash
set -e

PLAYERS_FILE="player.csv" ./gradlew clean build
docker build -t playerapp .
