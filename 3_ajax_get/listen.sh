echo "http://netgear.rohidekar.com/ajaxget/"

# You need to add a header so that CORS works

	while true; do \
		echo "Hello" \
		| ./http_response_header_add.js \
		| nc -l -p $1  
	done;

