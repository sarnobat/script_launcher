cd /home/sarnobat/Desktop/new/github/script_launcher/version1 || exit "You need to cd to version1 first"
echo "Now open http://netgear.rohidekar.com/launcher/"

DIR=/home/sarnobat/Desktop/new/github/script_launcher/version1

echo "Checking files exist."
ls http_response_header_add.sh 2>/dev/null || exit "Could not find http_response_header_add.sh"
ls http_request_body.js 2>/dev/null || exit "Could not find http_request_body.js"

# tee -a must be used otherwise the file gets cleared before you can read it
while true; do \
	cd $DIR
	echo $CWD
	cat fifo \
	| tee -a ~/4458_fifo.log \
	| $DIR/neo_shell_to_yurl_json.js \
	| $DIR/http_response_header_add.sh \
	| nc -l -p 4458  \
	| $DIR/http_request_body.js \
	| xargs -n 1 $DIR/get_urls.sh  \
	| tee -a ~/4458_neo4j.log > fifo
done;

