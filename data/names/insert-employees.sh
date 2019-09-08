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

language-insert() {
local host=$1
local id=$2
local lang=$3
#echo "-X POST http://${host}/employees/${id}/languages/${lang} > /dev/null"
curl -s -H "Content-Type: application/json" -X POST http://${host}/employees/${id}/languages/${lang} > /dev/null
}

random-language-insert-all() {
    local host=$1
    declare -a languages=("no" "de" "ru" "pt" "en" "it" "fr" "es" "zh" "ud" "ar" "se" "he" "nl")
    local len=${#languages[@]}
    local USER_ID=1
    while [ $USER_ID -le 10100 ]
    do
        lang_id=$(( $RANDOM % $len ))
        while [ $lang_id -le $len ]
        do
            lang=${languages[$lang_id]}

            language-insert "$host" "$USER_ID" "$lang"

            lang_id=$(( lang_id + $RANDOM % $len + 1 ))
        done

        if [ $USER_ID % 100 -eq 0 ]
        then
            echo "User %USER_ID"
        fi

        USER_ID=$(( $USER_ID + 1 ))
    done
}

#post-employees-file "localhost:8080" ./employees.json
