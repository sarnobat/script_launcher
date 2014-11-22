# Issues to address before you can replace your apps
#
# - if you run a while loop in the background there is no easy way to kill it
echo "http://netgear.rohidekar.com:4461?param1=Sridhar"
#{
	while true; do \
		cat fifo \
		| tee cat_fifo_result.log \
		| nc -l -p $1  \
		| tee input.log \
		| http_request_body.js \
		| tee fifo \
		| tee body.log
	done;

