#!/usr/bin/env node

var stdin = process.openStdin();

var data = "";

stdin.on('data', function(chunk) {
	  data += chunk;
});

stdin.on('end', function() {
	var dataJson = JSON.parse(data);
	console.log("DATA:\n" + dataJson.foo + "\nEND DATA");
});
