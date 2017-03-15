#!/usr/bin/env bash
#while
#read x ; 
#echo "root: $1 $x";
#do 
#exit;
type="categoryNode"
curl -d"{\"query\":\"start n=node(45) match n-[r:CONTAINS]->c where has(c.type) and c.type = 'categoryNode' return c.name limit 20;\"}" \
	-H accept:text/plain \
	-H content-type:application/json \
	http://netgear.rohidekar.com:7474/db/data/cypher 2> /dev/null
echo ""

#done

