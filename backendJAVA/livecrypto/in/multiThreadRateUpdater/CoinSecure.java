package livecrypto.in.multiThreadRateUpdater;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.ProtocolException;
import java.net.URL;
import java.sql.Connection;
import java.sql.DriverManager;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

import com.mysql.jdbc.PreparedStatement;

public class CoinSecure {
	final static String USER_AGENT = "Mozilla/5.0";
	static BufferedWriter br2=null;
	public static void execute() {
		String host="https://api.coinsecure.in";
		String apiEndpoint="v1";
		String response = null;
		JSONParser parser;
		JSONObject obj;
		Connection con=null;		
		PreparedStatement posted;
		con=getConnection();
		while(con==null) {
			con=getConnection();
			try {
				Thread.sleep(5000);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		String buyprice,sellprice,inr_vol,coin_vol,min_24hr,max_24hr;
//		long start = System.currentTimeMillis();
//		long now=System.currentTimeMillis();
		while(true) {
			try {				
//				now=System.currentTimeMillis();
				apiEndpoint="/v1/exchange/ticker";
				 response=sendGet(host,apiEndpoint);			
				 parser = new JSONParser();
				 obj = (JSONObject)parser.parse(response);   
				    
				 obj = (JSONObject)parser.parse( (obj.get("message")).toString());
				 buyprice=(obj.get("ask")).toString();
				 buyprice=buyprice.substring(0,buyprice.length()-2)+"."+buyprice.substring(buyprice.length()-2);
				
				 sellprice=(obj.get("bid")).toString();
				 sellprice=sellprice.substring(0,sellprice.length()-2)+"."+sellprice.substring(sellprice.length()-2);
				
				 inr_vol=(obj.get("fiatvolume")).toString();
				 inr_vol=inr_vol.substring(0,inr_vol.length()-2)+"."+inr_vol.substring(inr_vol.length()-2);
				
				 coin_vol=(obj.get("coinvolume")).toString();
				 coin_vol=coin_vol.substring(0,coin_vol.length()-8)+"."+coin_vol.substring(coin_vol.length()-8);
					
				 min_24hr=(obj.get("low")).toString();
				 min_24hr=min_24hr.substring(0,min_24hr.length()-2)+"."+min_24hr.substring(min_24hr.length()-2);
					
				 max_24hr=(obj.get("high")).toString();
				 max_24hr=max_24hr.substring(0,max_24hr.length()-2)+"."+max_24hr.substring(max_24hr.length()-2);
				 
				    try {
						posted=(PreparedStatement) con.prepareStatement("INSERT INTO coinsecure (buyprice,sellprice,inr_vol,coin_vol,min_24hr,max_24hr) VALUES("+buyprice+","+sellprice+","+inr_vol+","+coin_vol+","+min_24hr+","+max_24hr+")");				    
						posted.executeUpdate();
					} catch (Exception e) {
						try {
							con.close();
						} catch (Exception e1) {
							// TODO Auto-generated catch block
							e1.printStackTrace();
						}
						con=null;
						while(con==null) {
							con=getConnection();
							try {
								Thread.sleep(5000);
							} catch (InterruptedException e2) {
								// TODO Auto-generated catch block
								e.printStackTrace();
							}
						}
						e.printStackTrace();
					}
				   
				    System.out.println("coinsecure: "+(new SimpleDateFormat ("E dd/MM/yyyy HH:mm:ss zzz")).format(new Date()));
				    Thread.sleep(5*60*1000);			
			} catch (Exception e) {
				
				try {
					br2 = new BufferedWriter(new FileWriter("coinsecure_error_log", true));
					br2.write(">>"+e.toString()+"\n");
					br2.close();
				} catch (IOException x) {
					x.printStackTrace();
				}
				response=null;
				try {
					Thread.sleep(5*60*1000);
				} catch (InterruptedException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		   }
	}
	public static Connection getConnection() {		
		Connection con=null;
			try {
				Class.forName("com.mysql.jdbc.Driver");
				con=DriverManager.getConnection("jdbc:mysql://DATABASEURL.amazonaws.com:3306/livecrypto","USERNAME","PASSWORD");				
			} catch (Exception e) {

				try {
					br2 = new BufferedWriter(new FileWriter("coinsecure_error_log", true));
					br2.write(">>"+e.toString()+"\n");
					br2.close();
				} catch (IOException x) {
					x.printStackTrace();
				}
				
				e.printStackTrace();
			}
			return con;
	}
	private static String sendGet(String host,String apiEndpoint) {

		URL obj=null;
		
		try {
			obj = new URL(host+apiEndpoint);
		} catch (MalformedURLException e) {
			
			e.printStackTrace();
		}

		HttpURLConnection con=null;
		
		while(con==null) {
			try {
				con = (HttpURLConnection) obj.openConnection();
			} catch (IOException e1) {
				// TODO Auto-generated catch block
				con=null;
				e1.printStackTrace();
				try {
					Thread.sleep(5000);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}	
		}
		
		// optional default is GET
		try {
			con.setRequestMethod("GET");
		} catch (ProtocolException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		//add request header
		con.setRequestProperty("User-Agent", USER_AGENT);
		BufferedReader in = null;
		try {
			in = new BufferedReader(
			        new InputStreamReader(con.getInputStream()));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		String inputLine;
		StringBuffer response = new StringBuffer();

		try {
			while ((inputLine = in.readLine()) != null) {
				response.append(inputLine);
			}
			in.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}		
		return(response.toString());
	}
}

