function ShowSearchFirst(BlockString,SeoString="")
{
	var canclick=true;
	var SearchText = [document.getElementsByTagName('h3')[0],document.getElementsByTagName('h3')[1],document.getElementsByTagName('h3')[2]];
	
	var Blockli=BlockString.split(",")
	var Seoli=SeoString.split(",")
	if (BlockString=="")
	{
		return "No BlockString list input";
	}
	Blockli.forEach(function(BlockKeyword)
	{
		if (BlockKeyword!="")
		{
			var num=0;
		SearchText.forEach(function(SearchTextele)
		{
			if (SearchTextele.innerHTML.indexOf(BlockKeyword)> -1)
			{
				SearchText.splice(num,1);
			}
			num+=1;
		});
		}
	});
	if (SeoString=="")
	{
		alert(SearchTextele.innerHTML);
 		console.log(SearchText);
 		SearchText.forEach(function(SearchTextele)
		{
			if (SearchTextele!=null)
			{
				if (canclick)
				{
					canclick=false;
					SearchTextele.click();	
				}
			}
			
		});
	}
	else if (SeoString!="")
	{
		SearchText.forEach(function(SearchTextele)
		{
			Seoli.forEach(function(SeoKeyword)
			{
				if (SeoKeyword!="")
				{
					console.log("SeoKeyword:"+SeoKeyword)
					console.log(SearchTextele.innerHTML.indexOf(SeoKeyword).toString())
					console.log(SearchTextele.innerHTML)
					alert(SearchTextele.innerHTML);
			  		if (SearchTextele.innerHTML.indexOf(SeoKeyword)> -1)
			  		{
			  			if (canclick)
			  			{
			  				canclick=false;
			  				console.log("Click: "+SearchTextele.innerHTML)
			  				SearchTextele.click();
			  			}
			  		}
			  		console.log("-----------------")
				}
			});
		});
		return SearchText;
	}
}
