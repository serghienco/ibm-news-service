#!/bin/bash

get-sources() {
	curl https://newsapi.org/v2/sources -G  -d apiKey=${NEWS_API_KEY} | python -m json.tool > ./sources.json 
}

get-source-news-page() {
	local source=$1
	local directory=$2

	mkdir ${directory}
	curl https://newsapi.org/v2/everything -G  -d apiKey=${NEWS_API_KEY} -d sources=${source} -d pageSize=100 | jq > ./${directory}/news-of-${source}.json
}

get-sources-news() {
    local directory=$(date +%Y-%m-%d_%H-%M-%S)
	for source in $(cat ./sources.json | jq -r '.sources[].id');do
			source=$(echo ${source} | xargs)
			get-source-news-page ${source} ${directory}
	done
}

set-news-api-key() {
	export NEWS_API_KEY=$1
}


post-news-json() {
local HOST=$1
local JSON_BODY=$2
curl -s \
    -H "Content-Type: application/json" \
    -X POST \
    -d "${JSON_BODY}" \
    http://${HOST}/news > /dev/null
}

post-news-file() {
    local HOST=$1
    local JSON_FILE=$2
    local COUNT=0
    jq -c '.[]' "$JSON_FILE" | while read json; do
        NEWS_JSON=$(echo ${json} )
        post-news-json $HOST "$NEWS_JSON"

        COUNT=$(($COUNT + 1))
        if [ $(($COUNT % 100)) -eq 0 ]; then
            echo "Inserted ${COUNT}"
        fi
    done
}

