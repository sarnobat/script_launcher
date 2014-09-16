while true; do \
        cat fifo \
        | nc -l -p 4458  \
        | tee input.log \
        | http_request_body.js \
        | tee param.log \
        | sed --unbuffered 's/i/o/' \
        | head -1 \
        | xargs -n 1 get_urls.sh  \
        | http_response_header_add.js \
        | tee out.txt  \
        | tee fifo 
        #| tee body.log
done;