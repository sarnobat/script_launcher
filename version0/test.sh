while true
do
{   
	# For openbsd, add -p to nc
	 echo 'HTTP/1.0 200 OK
Access-Control-Allow-Origin: *
Content-Type: application/json\n\n' \
'{"Hello":"world"}' \
	| nc -l -p $1
}  
done;