#!/usr/bin/env node

var stdin = process.openStdin();

var data = "";

stdin.on('data', function(chunk) {
	//console.log(">>>>>>>>>> ");
	  data += chunk;
});

stdin.on('end', function() {
	//process.stderr.write(">>>>>>>>>>>>"+data + '<<<<<<<<<<<<<\n');
	process.stderr.write("\n>>> 1");
	var dataJsonString = data;
	process.stderr.write("\n>>> 2" + dataJsonString);
	if (dataJsonString == null) {
		process.stderr.write("\n>>> dataJsonString is null");
	}
	var dataJson = JSON.parse(dataJsonString);
	process.stderr.write("\n>>> 3");
	var body_size = getByteLength(dataJsonString);
	process.stderr.write("\n>>> 4");
	var response = "HTTP/1.1 200 OK\nAccess-Control-Allow-Origin: *\nContent-length : " + 2000 + "\nContent-Type: application/json\n" + dataJsonString;

	process.stderr.write("\n>>> 5");
	//console.log("DATA:\n" + dataJson.foo + "\nEND DATA");
	process.stdout.write(response + '\n');
	process.stderr.write("\n>>> 6");
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
