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

public class Zebpay {
	final static String USER_AGENT = "Mozilla/5.0";
	static BufferedWriter br2=null;
	public static void execute(){
		String host="https://www.zebapi.com";
		String apiEndpoint="/api/v1/market/ticker/btc/inr";
		String response = null;
		JSONParser parser;
		JSONObject obj;
		Connection con = null;		
		PreparedStatement posted;
		
		while(con==null) {
			try {
				con=getConnection();
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				con=null;
				try {
					Thread.sleep(5000);
				} catch (InterruptedException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			} 	
		}
//		long start = System.currentTimeMillis();
//		long now=System.currentTimeMillis();
		while(true) {
			try {				
//				now=System.currentTimeMillis();
				 response=sendGet(host,apiEndpoint);			
				 parser = new JSONParser();
				    obj = (JSONObject)parser.parse(response);   
				    //System.out.println(obj.get("buy")+","+obj.get("sell")+","+ obj.get("sell"));
				    
				    try {
				    	posted=(PreparedStatement) con.prepareStatement("INSERT INTO zebpay(buyprice,sellprice,volume) VALUES("+obj.get("buy")+","+obj.get("sell")+","+obj.get("volume")+")");
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
				    
				    System.out.println("Zebpay: "+(new SimpleDateFormat ("E dd/MM/yyyy HH:mm:ss zzz")).format(new Date()));
					
				    Thread.sleep(5*60*1000);			
			} catch (Exception e) {
				
				try {
					br2 = new BufferedWriter(new FileWriter("zebpay_error_log", true));
					br2.write(">>"+e.toString()+"\n");
					br2.close();
				} catch (IOException x) {
					x.printStackTrace();
				}
				response=null;
				try {
					Thread.sleep(10000);
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
					br2 = new BufferedWriter(new FileWriter("zebpay_error_log", true));
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

