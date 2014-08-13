#while true; do   cat header20.txt - | nc -l -p $1 ; done 

cat header.txt -  | tee /dev/stdout | nc -k -l -p $1 ; done 