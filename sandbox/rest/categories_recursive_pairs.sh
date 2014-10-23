#!/usr/bin/env bash
#while
#read x ; 
#echo "root: $1 $x";
#do 
#exit;
type="categoryNode"
curl -d"{\"query\":\"start n=node(*) match path=n-[r:CONTAINS]->c WHERE has(n.name) and has(c.name) return id(n), n.name, id(c), c.name\"}" \
	-H accept:text/plain \
	-H content-type:application/json \
	http://netgear.rohidekar.com:7474/db/data/cypher 2> /dev/null
echo ""

#start n=node(*) match path=n-[r:CONTAINS]->c where has(n.type) and n.type = 'categoryNode' and has(c.type) and c.type = 'categoryNode' return extract ( nn in nodes(path): nn.name?) LIMIT 1000;\"}" \
#done

