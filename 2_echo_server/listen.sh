# Issues to address before you can replace your apps
#
# - if you run a while loop in the background there is no easy way to kill it
echo "http://netgear.rohidekar.com:4461?param1=Sridhar"
#{
#echo $PWD
	while true; do \
		echo $PWD
		cat fifo \
		| nc -l -p $1  \
		| ./http_request_body.js \
		| tee fifo
	done;

