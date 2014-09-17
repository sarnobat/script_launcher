while true; do \
	cat fifo \
	| tee cat_fifo_result.log \
	| http_response_header_add.sh \
	| nc -l -p 4458  \
	| tee input.log \
	| http_request_body.js \
	| sed --unbuffered 's/i/o/' \
	| xargs -n 1 get_urls.sh  > fifo
done;
