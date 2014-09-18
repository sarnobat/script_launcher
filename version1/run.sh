# tee -a must be used otherwise the file gets cleared before you can read it
while true; do \
	cat fifo \
	| neo_shell_to_yurl_json.js \
	| http_response_header_add.sh \
	| nc -l -p 4458  \
	| http_request_body.js \
	| xargs -n 1 get_urls.sh  > fifo
done;
