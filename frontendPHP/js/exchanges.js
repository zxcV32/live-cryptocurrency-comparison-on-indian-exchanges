var month = new Array();
month[0] = "Jan";
month[1] = "Feb";
month[2] = "Mar";
month[3] = "Apr";
month[4] = "May";
month[5] = "Jun";
month[6] = "Jul";
month[7] = "Aug";
month[8] = "Sep";
month[9] = "Oct";
month[10] = "Nov";
month[11] = "Dec";
function zebpay(){
  	            var dataValBuy = [];
  	            var dataValSell = [];
            	var labelsVal=[];
            	var date;
  	$(document).ready(function(){
           $.ajax({
            url:"fetch_zebpay.php",
            method:"POST",
            data:{row:10},
            dataType:"JSON",
            success:function(data){
            	for (var i = 0; i < data.length; i++) {
            		dataValBuy.push(data[i].buy);
            		dataValSell.push(data[i].sell);
            		date = new Date(data[i].timestamp);
	           		labelsVal.push(date.getDate()+'-'+(month[date.getMonth()]));
            	}
            	dataValBuy.reverse();
            	dataValSell.reverse();
            	labelsVal.reverse();

            	var buy1=(parseInt(dataValBuy[data.length-1])-parseInt(dataValBuy[0]))/parseInt(dataValBuy[0]);
            	var sell1=(dataValSell[data.length-1]-dataValSell[0])/dataValSell[0];
            	
            	 buy1=buy1.toFixed(3);
            	 sell1=sell1.toFixed(3);

            	if(buy1>=0)
	            	 $('#buy1').text('Buy: ₹'+dataValBuy[data.length-1]+'(+'+buy1+'%)');
            	else
            		$('#buy1').text('Buy: ₹'+dataValBuy[data.length-1]+'('+buy1+'%)');
            	if(sell1>=0)
	            	 $('#sell1').text('Sell: ₹'+dataValSell[data.length-1]+'(+'+sell1+'%)');
	            else
	            	 $('#sell1').text('Sell: ₹'+dataValSell[data.length-1]+'('+sell1+'%)');
const ctx = document.getElementById("zebpay");
let zp = new Chart(ctx, {
    type: 'line',
	data:{
		labels:labelsVal,
		datasets:[
			{	label:"buy",
				fill:true,
				lineTension:0.1,
				backgroundColor: "rgba(53,154,242,0.4)",
				data:dataValBuy,
				pointHitRadius: 10
			},
			{	label:"sell",
				fill:true,
				lineTension:0.1,
				backgroundColor: "rgba(53,154,242,0.8)",
				data:dataValSell,
				pointHitRadius: 10
			}
		]

	}
});
       }
        })
});

}

function coinsecure(){
  	            var dataValBuy1 = [];
  	            var dataValSell1 = [];
            	var labelsVal1=[];
            	var date;
  	$(document).ready(function(){
           $.ajax({
            url:"fetch_coinsecure.php",
            method:"POST",
            data:{row:10},
            dataType:"JSON",
            success:function(data){
            	for (var i = 0; i < data.length; i++) {
            		dataValBuy1.push(data[i].buy);
            		dataValSell1.push(data[i].sell);
            		date = new Date(data[i].timestamp);
	           		labelsVal1.push(date.getDate()+'-'+(month[date.getMonth()]));
            	}
            	dataValBuy1.reverse();
            	dataValSell1.reverse();
            	labelsVal1.reverse();

            	var buy2=(parseInt(dataValBuy1[data.length-1])-parseInt(dataValBuy1[0]))/parseInt(dataValBuy1[0]);
            	var sell2=(dataValSell1[data.length-1]-dataValSell1[0])/dataValSell1[0];
            	
            	 buy2=buy2.toFixed(3);
            	 sell2=sell2.toFixed(3);

            	if(buy2>=0)
	            	 $('#buy2').text('Buy: ₹'+dataValBuy1[data.length-1]+'(+'+buy2+'%)');
            	else
            		$('#buy2').text('Buy: ₹'+dataValBuy1[data.length-1]+'('+buy2+'%)');
            	if(sell2>=0)
	            	 $('#sell2').text('Sell: ₹'+dataValSell1[data.length-1]+'(+'+sell2+'%)');
	            else
	            	 $('#sell2').text('Sell: ₹'+dataValSell1[data.length-1]+'('+sell2+'%)');
const ctx = document.getElementById("coinsecure");
let cs = new Chart(ctx, {
    type: 'line',
	data:{
		labels:labelsVal1,
		datasets:[
			{	label:"buy",
				fill:true,
				lineTension:0.1,
				backgroundColor: "rgba(11,170,178,0.4)",
				data:dataValBuy1,
				pointHitRadius: 10
			},
			{	label:"sell",
				fill:true,
				lineTension:0.1,
				backgroundColor: "rgba(11,170,178,0.8)",
				data:dataValSell1,
				pointHitRadius: 10
			}
		]

	}
});
       }
        })
});

}
function koinex(){
	 	            var dataValBuy2 = [];
  	            var dataValSell2 = [];
            	var labelsVal2=[];
            	var date;
  	$(document).ready(function(){
           $.ajax({
            url:"fetch_koinex.php",
            method:"POST",
            data:{row:10},
            dataType:"JSON",
            success:function(data){
            	for (var i = 0; i < data.length; i++) {
            		dataValBuy2.push(data[i].buy);
            		dataValSell2.push(data[i].sell);
            		date = new Date(data[i].timestamp);
	           		labelsVal2.push(date.getDate()+'-'+(month[date.getMonth()]));
            	}
            	dataValBuy2.reverse();
            	dataValSell2.reverse();
            	labelsVal2.reverse();

            	var buy3=(parseInt(dataValBuy2[data.length-1])-parseInt(dataValBuy2[0]))/parseInt(dataValBuy2[0]);
            	var sell3=(dataValSell2[data.length-1]-dataValSell2[0])/dataValSell2[0];
            	
            	 buy3=buy3.toFixed(3);
            	 sell3=sell3.toFixed(3);

            	if(buy3>=0)
	            	 $('#buy3').text('Buy: ₹'+dataValBuy2[data.length-1]+'(+'+buy3+'%)');
            	else
            		$('#buy3').text('Buy: ₹'+dataValBuy2[data.length-1]+'('+buy3+'%)');
            	if(sell3>=0)
	            	 $('#sell3').text('Sell: ₹'+dataValSell2[data.length-1]+'(+'+sell3+'%)');
	            else
	            	 $('#sell3').text('Sell: ₹'+dataValSell2[data.length-1]+'('+sell3+'%)');
const ctx = document.getElementById("koinex");
let kx = new Chart(ctx, {
    type: 'line',
	data:{
		labels:labelsVal2,
		datasets:[
			{	label:"buy",
				fill:true,
				lineTension:0.1,
				backgroundColor: "rgba(12,110,181,0.4)",
				data:dataValBuy2,
				pointHitRadius: 10
			},
			{	label:"sell",
				fill:true,
				lineTension:0.1,
				backgroundColor: "rgba(12,110,181,0.8)",
				data:dataValSell2,
				pointHitRadius: 10
			}
		]

	}
});
       }
        })
});
}
function localbitcoins(){
	  	            var dataValBuy3 = [];
  	            var dataValSell3 = [];
            	var labelsVal3=[];
            	var date;
  	$(document).ready(function(){
           $.ajax({
            url:"fetch_localbitcoins.php",
            method:"POST",
            data:{row:10},
            dataType:"JSON",
            success:function(data){
            	for (var i = 0; i < data.length; i++) {
            		dataValBuy3.push(data[i].buy);
            		dataValSell3.push(data[i].sell);
            		date = new Date(data[i].timestamp);
	           		labelsVal3.push(date.getDate()+'-'+(month[date.getMonth()]));
            	}
            	dataValBuy3.reverse();
            	dataValSell3.reverse();
            	labelsVal3.reverse();

            	var buy4=(parseInt(dataValBuy3[data.length-1])-parseInt(dataValBuy3[0]))/parseInt(dataValBuy3[0]);
            	var sell4=(dataValSell3[data.length-1]-dataValSell3[0])/dataValSell3[0];
            	
            	 buy4=buy4.toFixed(3);
            	 sell4=sell4.toFixed(3);

            	if(buy4>=0)
	            	 $('#buy4').text('Buy: ₹'+dataValBuy3[data.length-1]+'(+'+buy4+'%)');
            	else
            		$('#buy4').text('Buy: ₹'+dataValBuy3[data.length-1]+'('+buy4+'%)');
            	if(sell4>=0)
	            	 $('#sell4').text('Sell: ₹'+dataValSell3[data.length-1]+'(+'+sell4+'%)');
	            else
	            	 $('#sell4').text('Sell: ₹'+dataValSell3[data.length-1]+'('+sell4+'%)');
const ctx = document.getElementById("localbitcoins");
let lb = new Chart(ctx, {
    type: 'line',
	data:{
		labels:labelsVal3,
		datasets:[
			{	label:"buy",
				fill:true,
				lineTension:0.1,
				backgroundColor: "rgba(51,122,183,0.4)",
				data:dataValBuy3,
				pointHitRadius: 10
			},
			{	label:"sell",
				fill:true,
				lineTension:0.1,
				backgroundColor: "rgba(245,130,32,0.8)",
				data:dataValSell3,
				pointHitRadius: 10
			}
		]

	}
});
       }
        })
});

}