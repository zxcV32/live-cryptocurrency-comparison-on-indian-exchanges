<!DOCTYPE html>
<html lang="en">
<head>
  <title>BTC PRICE</title>
  <meta charset="utf-8">
  <meta name="viewport" content="width=device-width, initial-scale=1">
  <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0-beta.2/css/bootstrap.min.css">
  <link rel="stylesheet" type="text/css" href="./css/main.css">
  <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>
  <script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.12.6/umd/popper.min.js"></script>
  <script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0-beta.2/js/bootstrap.min.js"></script>
  <script src="https://cdnjs.cloudflare.com/ajax/libs/Chart.js/2.7.1/Chart.bundle.js"></script>
  <script type="text/javascript" src="./js/exchanges.js"></script>

    <script src="https://code.highcharts.com/stock/highstock.js"></script>
    <script src="https://code.highcharts.com/stock/modules/exporting.js"></script>
</head>
<body class="bg-dark text-white">

<div class="container-fluid bg-secondary text-light">
	<div align="center">Note: this is demo site.</div>
</div>

<nav class="navbar navbar-expand-sm bg-dark navbar-dark sticky-top">
  <a class="navbar-brand" href="#">LiveCrypto</a>
  <ul class="navbar-nav">

    <li class="nav-item dropdown">
      <a class="nav-link dropdown-toggle" href="#" id="navbardrop" data-toggle="dropdown">
        Exchange List
      </a>
      <div class="dropdown-menu bg-dark ">
        <a class="dropdown-item text-light" href="#">Zebpay</a>
        <a class="dropdown-item text-light" href="#">CoinSecure</a>
        <a class="dropdown-item text-light" href="#">Koinex</a>
        <a class="dropdown-item text-light" href="#">Local Bitcoins</a>
      </div>
    </li>
  </ul>
</nav>
<hr>
<div class="container-fluid">
<div class="row">
<!--ZEBPAY CARD-->
<div class="col-lg-3 col-md-6 col-sm-6 col-xs-12">
  	<div id="content1"></div>
		<div class="card" style="margin-bottom: 1.5em;">
  <div class="card-block bg-dark rounded">
  		<p id="id"></p>
	<canvas id="zebpay"></canvas>
	<div class="card-text" align="center">
		<a href="#"  >ZEBPAY
		<br>
		<p id="buy1"></p>
		<p id="sell1"></p>
	</a>

	</div>
  <script type="text/javascript">zebpay();</script>
  </div>
</div>
</div>

<!--COINSECURE CARD-->
<div class="col-lg-3 col-md-6 col-sm-6 col-xs-12">
  	<div id="content1"></div>
		<div class="card" style="margin-bottom: 1.5em;">
  <div class="card-block bg-dark rounded">
  		<p id="id"></p>
	<canvas id="coinsecure"></canvas>
	<div class="card-text" align="center">
		<a href="#"  >COIN SECURE
		<br>
		<p id="buy2"></p>
		<p id="sell2"></p>
	</a>

	</div>
  <script>coinsecure();</script>

  </div>
</div>
</div>

<!--KOINEX CARD-->
<div class="col-lg-3 col-md-6 col-sm-6 col-xs-12">
  	<div id="content1"></div>
		<div class="card" style="margin-bottom: 1.5em;">
  <div class="card-block bg-dark rounded">
  		<p id="id"></p>
	<canvas id="koinex"></canvas>
	<div class="card-text" align="center">
		<a href="#"  >KOINEX 
		<br>
		<p id="buy3"></p>
		<p id="sell3"></p>
	</a>

	</div>
  <script> koinex();  </script>

  </div>
</div>
</div>

<!--LOCALBITCOISNS CARD-->
<div class="col-lg-3 col-md-6 col-sm-6 col-xs-12">
  	<div id="content1"></div>
		<div class="card" style="margin-bottom: 1.5em;">
  <div class="card-block bg-dark rounded">
  		<p id="id"></p>
	<canvas id="localbitcoins"></canvas>
	<div class="card-text" align="center">
		<a href="#"  >LOCAL BITCOINS 
		<br>
		<p id="buy4"></p>
		<p id="sell4"></p>
	</a>

	</div>
  <script>localbitcoins();</script>

  </div>
</div>
</div>	
</div>
<hr>
<div class="row">
	<div class="col-lg-6 col-md-6 col-sm-12 col-xs-12">
		<div align="center">
			<h3>buy sell rate difference of each exchange in last hour</h3>
			<canvas id="volatile" ></canvas>	
			<p id="one"></p>		
<script type="text/javascript" src="./js/doughnut.js"></script>
		</div>
	</div>

	<div class="col-lg-6 col-md-6 col-sm-12 col-xs-12">
		<div align="center">
			<canvas id="average"></canvas>	
					
			  <script>
  	$(document).ready(function(){
  		var dataValRate=[];
  		var labelsValRate=[];
           	$.ajax({
            url:"todayAvg.php",
            method:"POST",
            data:{row:24},
            dataType:"JSON",
            success:function(data){
			var hr, mm;
           	for (var i = 0; i < data.length; i++) {
            		dataValRate.push(data[i].rate);
            		date = new Date(data[i].timestamp);
	           		//labelsValRate.push(date.getHours()+date.getMinutes()+date.getDate()+'-'+(month[date.getMonth()]));
            	if(date.getHours() < 10)
            		hr='0'+date.getHours();
            	else
        			hr=date.getHours();
            	if(date.getMinutes() < 10)
            		mm='0'+date.getMinutes();
            	else
        			mm=date.getMinutes();
            	
            		
            	labelsValRate.push(hr+':'+mm);
            	
            	}
            	
            	dataValRate.reverse();
            	labelsValRate.reverse();
       		}
        })
          

avv();
function avv(){
    setTimeout(function(){
    	if(labelsValRate.length > 1){
    const ctx = document.getElementById("average");
	let myTimeChart = new Chart(ctx,{
			type:'bar',
			data: {
				labels: labelsValRate,
				datasets: [{
					label: "Average in INR",
					data: dataValRate,
					type: 'line',
					pointRadius: 4,
					fill: true,
					lineTension: 00,
					borderWidth: 2,
				backgroundColor: '#868e96',
				pointHitRadius: 10
					
				}]
			}
		});
	return;
	}
	avv();   	
    },1500);    
}

});
  </script>

		</div>
	</div>

</div>
<div class="row">
  <div class="col-12">

    <div id="plotHigh">
    <script type="text/javascript" src="./js/homeAverage.js"></script>
    </div>
  
  </div>
</div>
</div>

<hr>

<footer align="center">
	&copy; 2017-2018 Live Crypto
</footer>

</body>
</html>