while true; do \
	cat $(echo header20.txt) fifo \
	| nc -l -p 4458  \
	| http_request_body.js \
	| sed --unbuffered 's/i/o/' \
	| xargs -n 1 cypher_query.sh \
	> fifo;
done;

exit;

# works
while true; do cat header20.txt fifo | nc -l -p 4458  | http_request_body.js | sed --unbuffered 's/i/o/' | xargs -n 1 cypher_query.sh > fifo; done;

# works
#cat fifo | sh my_netcat2.sh 4458 | http_request_body.js | sed --unbuffered 's/i/o/' | xargs -n 1 cypher_query.sh | tee fifo
 
# works
#cat fifo | while true; do   nc -l -p 4458 ; done | tee fifo




# works
while true
do
{   
	# For openbsd, add -p to nc
	 echo 'HTTP/1.0 200 OK
Access-Control-Allow-Origin: *
Content-Type: text/plain
Content-Length: 100\n\n' \
'{"Hello":"world"}' | cat - fifo \
	 | nc -l -p 4458 | http_request_body.js | xargs -n 1 cypher_query.sh | tee fifo 
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

