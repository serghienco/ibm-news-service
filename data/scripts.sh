#!/usr/bin/env bash

run-dynamodb-local() {
    docker run -p 8000:8000 amazon/dynamodb-local
}
