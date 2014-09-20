#echo "starting" #>> temp.log 

body=""

# problem: there is no end of input so we never out of this loop
while read line; do  \
	#echo "Read a line\n" #>> temp.log 
	body="$body ${line}"; 
done


#body=`cat`

#echo "collected stdin" #>> temp.log 

body_size_with_newlines=`echo $body | http_content_size.js | perl -pe 's{^\n}{}g'`
body_size=$body_size_with_newlines

#echo "calculated body size " #>> temp.log

echo "HTTP/1.1 200 OK
Access-Control-Allow-Origin: *
Content-length : $body_size
Content-Type: application/json
"
echo $body

#echo "Body is: " $body #>> temp.log

#echo "wrote response" #>> temp.log
