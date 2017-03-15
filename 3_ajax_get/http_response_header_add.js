#!/usr/bin/env node

var stdin = process.openStdin();

var data = "";

stdin.on('data', function(chunk) {
	//console.log(">>>>>>>>>> ");
	  data += chunk;
});

stdin.on('end', function() {
	data = '{ foo : bar }';
	var dataJsonString = data;
	var dataJson = dataJsonString;
	var body_size = getByteLength(dataJsonString);
	process.stderr.write("\n>>> 4 - size: " + body_size);
	var response = "HTTP/1.1 200 OK\nAccess-Control-Allow-Origin: *\nContent-length : " 
		+ body_size 
		+ "\nContent-Type: application/json\n\n" + dataJsonString;
	process.stderr.write("\n>>> 5");
	process.stdout.write(response + '\n');
	process.stderr.write("\n>>> 6 - " + response);
	process.exit(0);
});

function getByteLength(normal_val) {
    // Force string type
    normal_val = String(normal_val);

    var byteLen = 0;
    for (var i = 0; i < normal_val.length; i++) {
        var c = normal_val.charCodeAt(i);
        byteLen += c < (1 <<  7) ? 1 :
                   c < (1 << 11) ? 2 :
                   c < (1 << 16) ? 3 :
                   c < (1 << 21) ? 4 :
                   c < (1 << 26) ? 5 :
                   c < (1 << 31) ? 6 : Number.NaN;
    }
    return byteLen;
}
