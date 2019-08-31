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