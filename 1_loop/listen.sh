
# This works fine when the client is a browser

while true; do \
        echo "current working directory is $PWD"
        echo "45"  \
        | nc -l -p $1
done;
