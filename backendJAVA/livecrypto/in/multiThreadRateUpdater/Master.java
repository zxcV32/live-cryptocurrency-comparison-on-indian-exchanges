package livecrypto.in.multiThreadRateUpdater;
public class Master {
	public static void main(String[] args) {		
		Thread cs=new Thread(){
			public void run(){
				try {
					CoinSecure.execute();
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		};
		Thread kx=new Thread(){
			public void run(){
				try {
					Koinex.execute();
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		};
		Thread lb=new Thread(){
			public void run(){
				try {
					LocalBitcoins.execute();
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		};
		Thread zp=new Thread(){
			public void run(){
				try {
					Zebpay.execute();
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		};
		zp.start();
		lb.start();
		kx.start();
		cs.start();
	}
}

