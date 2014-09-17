while true; do \
	cat fifo \
	| tee -a cat_fifo_result.log \
	| neo_shell_to_yurl_json.js \
	| http_response_header_add.sh \
	| tee -a http_response.log \
	| nc -l -p 4458  \
	| tee -a input.log \
	| http_request_body.js \
	| sed --unbuffered 's/i/o/' \
	| xargs -n 1 get_urls.sh  > fifo
done;
