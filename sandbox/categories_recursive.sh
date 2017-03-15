echo "start n=node(45) match n-[CONTAINS]->c where has(c.type) AND c.type = 'categoryNode' return c.name LIMIT 80;" | neo4j-shell -readonly
