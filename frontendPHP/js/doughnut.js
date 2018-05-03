  	$(document).ready(function(){
            var priceDiff=[1,1,1,1]
           $.ajax({
            url:"fetch_zebpay.php",
            method:"POST",
            data:{row:1},
            dataType:"JSON",
            success:function(data){
            		priceDiff[0]=data[0].buy-data[0].sell;
            		priceDiff[0]=priceDiff[0].toFixed(2);
       		}
        })
            $.ajax({
            url:"fetch_coinsecure.php",
            method:"POST",
            data:{row:1},
            dataType:"JSON",
            success:function(data){
            		priceDiff[1]=data[0].buy-data[0].sell;
            		priceDiff[1]=priceDiff[1].toFixed(2);
       		}
        })
		    $.ajax({
            url:"fetch_koinex.php",
            method:"POST",
            data:{row:1},
            dataType:"JSON",
            success:function(data){
            		priceDiff[2]=data[0].buy-data[0].sell;
            		priceDiff[2]=priceDiff[2].toFixed(2);
       		}
        })
      	    $.ajax({
            url:"fetch_localbitcoins.php",
            method:"POST",
            data:{row:1},
            dataType:"JSON",
            success:function(data){
            		priceDiff[3]=data[0].buy-data[0].sell;
            		priceDiff[3]=priceDiff[3].toFixed(2);
       		}
        })

don();
function don(){

    setTimeout(function(){
	           	for (var i = 0; i < priceDiff.length; i++) {
            		if(priceDiff[i]==1)
            			don();
            	}
	const ctx = document.getElementById("volatile");
	let myPieChart = new Chart(ctx,{
   	 type: 'doughnut',
   	 	data:{
		labels:['Zebpay','Coin Secure','Koinex','Local Bitcoins'],
		datasets:[
		{
			label:'Points',
			backgroundColor:['rgb(53,154,242)','rgb(11,170,178)','rgb(12,110,181)',' rgb(245,130,32)'],
			data:priceDiff
		}
		]},
	options:{
		animation:{
			animateScale:true
		}
	}
	});

    },1500);

}
     
   });