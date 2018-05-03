<?php
if(isset($_POST['row'])){
	require('connectDB.php');
if (!$con) {
    die('Could not connect: ' . mysqli_error($con));
}

//$sql="SELECT * FROM zebpay WHERE id = '".$q."'";

//$sql="select * from coinsecuire group by date(datetime) LIMIT 10".$_POST['row'];

//$sql="SELECT AVG(buyprice) as buyprice,AVG(sellprice)as sellprice,datetime FROM coinsecuire GROUP BY date(datetime) LIMIT ".$_POST['row'];
$sql1="SELECT ROUND(AVG(buyprice),2 ) as buyprice,ROUND(AVG(sellprice),2 ) as sellprice,datetime FROM zebpay group by hour(datetime),date(datetime) order by datetime desc limit ".$_POST['row'];
$result1 = mysqli_query($con,$sql1);

$sql2="SELECT ROUND(AVG(buyprice),2 ) as buyprice,ROUND(AVG(sellprice),2 ) as sellprice,datetime FROM coinsecure  group by hour(datetime),date(datetime) order by datetime desc limit ".$_POST['row'];
$result2 = mysqli_query($con,$sql2);

$sql3="SELECT ROUND(AVG(buyprice),2 ) as buyprice,ROUND(AVG(sellprice),2 ) as sellprice,datetime FROM koinex group by hour(datetime),date(datetime) order by datetime desc limit ".$_POST['row'];
$result3 = mysqli_query($con,$sql3);

$sql4="SELECT ROUND(AVG(buyprice),2 ) as buyprice,ROUND(AVG(sellprice),2 ) as sellprice,datetime FROM localbitcoins group by hour(datetime),date(datetime) order by datetime desc limit ".$_POST['row'];
$result4 = mysqli_query($con,$sql4);

$i=0;
while($i<$_POST['row']) {
$row1 = mysqli_fetch_array($result1);
$row2 = mysqli_fetch_array($result2);
$row3 = mysqli_fetch_array($result3);
$row4 = mysqli_fetch_array($result4);
    $data["rate"]=((($row1['buyprice'] + $row1['sellprice'])/2)
    				+
    				(($row2['buyprice'] + $row2['sellprice'])/2)
    				+
    				(($row3['buyprice'] + $row3['sellprice'])/2)
    				+
    				(($row4['buyprice'] + $row4['sellprice'])/2))/4;

    
    $data["timestamp"]=$row4['datetime'];

    $arr[$i]=json_encode($data);
    $i=$i + 1;
}
	echo '['.implode(',',$arr).']';

mysqli_close($con);
}
else
	echo "you are in wrong place!";
?>