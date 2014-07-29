javascript:(
	function() {
		if (location.href.match("youtu.*&list=.*") ) {
			var newLocation=location.href.replace(/&list=[^&]+/,"");
			newLocation = newLocation.replace(/.*watch/,"/watch");
			window.history.pushState("object or string", "Title", newLocation);
		}
		/* it seems we can't close the tab before executing the rest of the code */
		document.getElementsByTagName("body")[0].innerHTML = "";
		document.body.style.backgroundColor = "orange";
		var x = new XMLHttpRequest();
		/* main: 45, product: 29196, video: 37658, tech: 46, other: 29172 */
		x.open('POST','http://netgear.rohidekar.com:4458', true);
		x.setRequestHeader("Content-type","text/plain");
		//x.setRequestHeader('User-Agent','XMLHTTP/1.0');
		/*x.setRequestHeader("Connection","close");*/
		x.onreadystatechange = 
			function() {
				if (x.readyState == 4) {
					if (x.status == 200) {
						window.open('', '_self', ''); 
						document.body.style.backgroundColor = "green";
						/* console.debug(x.responseText);		 */
						/*x.close();*/
						x.abort();
					} else {
						document.body.style.backgroundColor = "red";
					}
					/* x.close(); */
				}
			};
		x.send(encodeURIComponent(45) + "\n");
	}
)()
