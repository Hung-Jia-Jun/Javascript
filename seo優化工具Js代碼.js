function eraseCookie(name)
{
	console.log(name+'=; Max-Age=-99999999;');
	document.cookie = name+'=; Max-Age=-99999999;';
}

function ShowSearchFirst(BlockString, SeoString) {
	var cookies = document.cookie.split(';');
	for (var i = 0; i < cookies.length; i++)
		eraseCookie(cookies[i].split('=')[0]);
	var canclick = true;
	var SearchText = [, , ];
	try {
		SearchText[0] = document.getElementsByClassName('ads-fr')[0];
	} catch(e) {}
	try {
		SearchText[1] = document.getElementsByClassName('ads-fr')[1];
	} catch(e) {}
	try {
		SearchText[2] = document.getElementsByClassName('ads-fr')[2];
	} catch(e) {}
	var Blockli = BlockString.split(',');
	var Seoli = SeoString.split(',');
	Blockli.forEach(function(BlockKeyword) {
		if (BlockKeyword != '') {
			var num = 0;
			SearchText.forEach(function(SearchTextele) {
				if (SearchTextele.innerHTML.indexOf(BlockKeyword) > -1) {
					SearchText.splice(num, 1);
				}
				num += 1
			})
		}
	});
	if (SeoString == '') {
		SearchText.forEach(function(SearchTextele) {
			if (SearchTextele != null) {
				if (canclick) {
					canclick = false;
					SearchTextele.getElementsByTagName('div')[4].getElementsByTagName('span')[1].click();
				}
			}
		})
	} else if (SeoString != '') {
		SearchText.forEach(function(SearchTextele) {
			Seoli.forEach(function(SeoKeyword) {
				if (SeoKeyword != '') {
					try {
						console.log(SearchText[0].getElementsByTagName('div')[2].getElementsByTagName('div')[0].innerHTML);
					} catch(e) {}
					try {
						console.log(SearchText[1].getElementsByTagName('div')[2].getElementsByTagName('div')[0].innerHTML);
					} catch(e) {}
					try {
						console.log(SearchText[2].getElementsByTagName('div')[2].getElementsByTagName('div')[0].innerHTML);
					} catch(e) {}
					if (SearchTextele.getElementsByTagName('div')[0].innerHTML.indexOf(SeoKeyword) > -1) {
						if (canclick) {
							canclick = false;
							console.log('Click: ' + SearchTextele.getElementsByTagName('div')[0].innerHTML);
							try
							{
								SearchTextele.getElementsByTagName('div')[2].getElementsByTagName('div')[1].click();
							}
							catch(e)
							{}
							try
							{
								SearchTextele.getElementsByTagName('div')[2].getElementsByTagName('div')[0].click();
							}
							catch(e)
							{}
						}
					}
					console.log('-----------------')
				}
			})
		});
		return SearchText
	}
}
