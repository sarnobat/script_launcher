
echo 'HTTP/1.1 200 OK
Access-Control-Allow-Origin: *
Content-length : 4
Content-Type: application/json\r\n\r\n' \
'{"Hello":"world", "apples" : "oranges"}'  | nc -v -k -l 4458 |  perl -ne 's{HTTP}{FTP}g; $|++;print $_' | perl -ne 's{FTP}{RTSP}g; $|++;print $_' 


# Works
# my_netcat.sh 4458 | grep --line-buffered HTTP | grep --line-buffered POST