cd /home/sarnobat/Desktop/new/github/script_launcher/hello_world || exit "You need to cd to version1 first"
#echo "Now open http://netgear.rohidekar.com/launcher/"

DIR=/home/sarnobat/Desktop/new/github/script_launcher/hello_world

echo "Checking files exist."
ls http_response_header_add.sh 2>/dev/null || exit "Could not find http_response_header_add.sh"
ls http_request_body.js 2>/dev/null || exit "Could not find http_request_body.js"

# tee -a must be used otherwise the file gets cleared before you can read it
while true; do \
        echo "current working directory is $DIR"
	cd $DIR
	echo "current working directory is $PWD"
	echo "45"  \
	| $DIR/http_response_header_add.sh \
	| nc -l -p 4460 
done;

