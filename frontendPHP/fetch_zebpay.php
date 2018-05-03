<?php
if(isset($_POST['row'])){
	require('connectDB.php');
if (!$con) {
    die('Could not connect: ' . mysqli_error($con));
}

//$sql="SELECT * FROM zebpay WHERE id = '".$q."'";

//$sql="select * from coinsecuire group by date(datetime) LIMIT 10".$_POST['row'];

//$sql="SELECT AVG(buyprice) as buyprice,AVG(sellprice)as sellprice,datetime FROM coinsecuire GROUP BY date(datetime) LIMIT ".$_POST['row'];
$sql="SELECT ROUND(AVG(buyprice),2 ) as buyprice,ROUND(AVG(sellprice),2 ) as sellprice,datetime FROM zebpay group by date(datetime) order by datetime desc limit ".$_POST['row'];


$result = mysqli_query($con,$sql);

$i=0;
while($row = mysqli_fetch_array($result)) {
    $data["buy"]=$row['buyprice'];
    $data["sell"]= $row['sellprice'];
    $data["timestamp"]=$row['datetime'];
    $arr[$i]=json_encode($data);
    $i=$i + 1;
}
	echo '['.implode(',',$arr).']';

mysqli_close($con);
}
else
	echo "you are in wrong place!";
?>