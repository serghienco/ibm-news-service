#!/bin/bash

post-employees-json() {
local HOST=$1
local JSON_BODY=$2
curl -s \
    -H "Content-Type: application/json" \
    -X POST \
    -d "${JSON_BODY}" \
    http://${HOST}/employees > /dev/null
}

post-employees-file() {
    local HOST=$1
    local JSON_FILE=$2
    local COUNT=0
    jq -c '.[]' "$JSON_FILE" | while read json; do
        EMPLOYEE_JSON=$(echo ${json} )
        post-employees-json $HOST "$EMPLOYEE_JSON"

        COUNT=$(($COUNT + 1))
        if [ $(($COUNT % 100)) -eq 0 ]; then
            echo "Inserted ${COUNT}"
        fi
    done
}

#post-employees-file "localhost:8080" ./employees.json
