

while true
do
{   
	# For openbsd, add -p to nc
	 echo 'HTTP/1.0 200 OK
Access-Control-Allow-Origin: *
Content-Type: text/plain\n\n' \
'{"Hello":"world"}' | cat - body.txt \
	 | nc -l -p 4458
}  
done;



# Works
#my_netcat.sh 4458 | grep --line-buffered HTTP | grep --line-buffered POST
my_netcat.sh 4458 | http_request_body.js | tee temp.log | xargs -n 1 cypher_query.sh

 


exit;


###################





echo 'HTTP/1.1 200 OK
Access-Control-Allow-Origin: *
Content-length : 4
Content-Type: application/json\r\n\r\n' \
'{"Hello":"world", "apples" : "oranges"}'  | nc -v -k -l 4458 |  perl -ne 's{HTTP}{FTP}g; $|++;print $_' | perl -ne 's{FTP}{RTSP}g; $|++;print $_' 

