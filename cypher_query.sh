#!/usr/bin/env bash
#while
#read x ; 
echo "root: $1";
#do 
curl -d'{"query":"start n=node('$1') match n-[r:CONTAINS]->c where c.title? <> \"\" return c.title? limit 500;"}' \
	-H accept:text/plain \
	-H content-type:application/json \
	http://netgear.rohidekar.com:7474/db/data/cypher

#done

