import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.util.Scanner;
public class TirunagaruP2Client {
	
	public static void main(String[] args){
		//TirunagaruP2ServerInterface remoteObject = null;
		TirunagaruP2CoinsInterface coinsObject = null;
		String host = "192.168.1.76";//"10.91.65.214";
		int port = 1099;
	//	rmi://10.91.65.214:1099/TirunagaruP2ServerInterface/
		//String rminame = "rmi://"+host+":"+port+"/TirunagaruP2ServerInterface/";
		String rminame ="/";
		String userName,password;
		String coinname= null;
		try {			
		//	Registry registoryConn = LocateRegistry.getRegistry(host, port);
			Registry registoryConn = LocateRegistry.getRegistry();
			
			coinsObject =(TirunagaruP2CoinsInterface)registoryConn.lookup((rminame+"authentication"));
			Scanner scanner = new Scanner(System.in);
			
			while(true) {
			System.out.println("Enter the username : ");
			 userName = scanner.next();
			
			System.out.println("Enter the password : ");
			 password = scanner.next();
			
		
			boolean status = coinsObject.authenticate(userName, password);
			
			if(status) {
				System.out.println("Successfull Login");
				break;

			}
				
			else {
				System.out.println("Unauthorized Login Attempt!! Please try again");
		
					}
			}
		
		boolean flag = true;
	
	while(flag){
		System.out.println("Enter the option: ");
		System.out.println("1.To know About coins 2. userDetails 3. Exit");
		int option = scanner.nextInt();
		
		switch(option)
		{
		case 1:  
				System.out.println("Coins with the Server are:");
				String [] references = registoryConn.list();
				for (int i = 0; i < references.length; i++) 
			 	{
					if(references[i].contains("authentication") || references[i].contains("userdetails") ) {}
					else
					System.out.println(i+"."+ references[i].substring(1));
				}
				System.out.println("Enter the number of the coin You wish to buy or sell or to know the your Details: ");
				int index  = scanner.nextInt();
				String ref =references[index];
				System.out.println("ref:"+ref);
				coinsObject = (TirunagaruP2CoinsInterface) registoryConn.lookup(ref);
				
				System.out.println("do you wish to 1. BUY or 2.SELL?");
				int buy_sell = scanner.nextInt();
				int price = coinsObject.getPrice();
				coinname = coinsObject.getName();
				System.out.println("coiname:"+coinname+" price :"+price);
				System.out.println("Price for the selected coin is: "+ price);
				
				if(buy_sell == 1)
				{
					System.out.println(" Enter the Quantity to BUY: ");
					int quantity = scanner.nextInt();
					String ret_buy = coinsObject.buy(userName,coinname, quantity, price);
					System.out.println(ret_buy);
					
				}
				else if (buy_sell == 2)
				{
					System.out.println(" Enter the Quantity to SELL: ");
					int quantity = scanner.nextInt();
					String ret_sell = coinsObject.sell(userName,coinname, quantity, price);
					System.out.println(ret_sell);
				}
				else
					System.out.println("please select correct option next time" );
						
			break;
			
		case 2:
			
			coinsObject = (TirunagaruP2CoinsInterface) registoryConn.lookup(rminame+"userdetails");
			String fromserver = coinsObject.UserDetails(userName);
			System.out.println(fromserver);
			break;
			
		case 3: System.exit(0); break;
		
		default: break;
	
		} // switch	
		
	} //while
	scanner.close();
		
		
/*		while(flag)
		{
			
  		
		System.out.println("Enter the number of the coin You wish to buy or sell or to know the your Details: ");
		System.out.println("1.Bitcoin 2. Ethereum 3.Litecoin 4. Ripple 5.Blackcoin 6. user 7.Exit");
		int coin = scanner.nextInt();
		String name2 =null;
		switch (coin) {
		case 1: 	name2 = 	rminame+"bitcoin";  coinname = "bitcoin";	 break;
		case 2: 	name2 = 	rminame+"ethereum"; coinname = "ethereum";	 break;
		case 3: 	name2 = 	rminame+"litecoin"; coinname = "litecoin"; 	 break;  				
		case 4: 	name2 = 	rminame+"ripple"; 	 coinname = "ripple";	 break;   				
		case 5: 	name2 = 	rminame+"blackcoin";coinname = "blackcoin";	 break;
		case 6:
					coinsObject = (TirunagaruP2CoinsInterface) registoryConn.lookup(rminame+"/userdetails");
					String fromserver = coinsObject.UserDetails(userName);
					System.out.println(fromserver);
					continue;
		
		case 7:		System.exit(0); break;
		default: break;
		}
		coinsObject = (TirunagaruP2CoinsInterface) registoryConn.lookup(name2);
		
		System.out.println("do you wish to 1. BUY or 2.SELL?");
		int buy_sell = scanner.nextInt();
		int price = coinsObject.getPrice();
		
		System.out.println("Price for the selected coin is: "+ price);
		
		if(buy_sell == 1)
		{
			System.out.println(" Enter the Quantity to BUY: ");
			int quantity = scanner.nextInt();
			System.out.println("selected coin :"+coinname +" url: "+name2);
			System.out.println(coinsObject.buy(userName,coinname, quantity, price));
			
		}
		else if (buy_sell == 2)
		{
			System.out.println(" Enter the Quantity to SELL: ");
			int quantity = scanner.nextInt();
			System.out.println(coinsObject.sell(userName,coinname, quantity, price));
		}
		else
			System.out.println("please select correct option next time" );
		
	
		} 
		scanner.close(); */
		} //try
		
		 catch (RemoteException e) {
			e.printStackTrace();
		} catch (NotBoundException e) {
			e.printStackTrace();
		}
			
	}
		
	}


