
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.util.ArrayList;
import java.util.Date;
import java.util.Scanner;

public class TirunagaruP2ServerDriver {
	//TirunagaruP2ServerInterface cryptoCurrency = new TirunagaruP2ServerInterface();
	
	public static void main(String[] args) {
		 try {
        	
        	String host = "192.168.1.76";//"10.91.65.214";
     		int port = 1099;
     	//	rmi://10.91.65.214:1099/TirunagaruP2ServerInterface/
     		//String rmiUrl = "rmi://"+host+":"+port+"/TirunagaruP2ServerInterface/";
     		String rmiUrl = "/";
     		
        	 
        	// String rmiUrl = "rmi://10.91.65.214:1099/TirunagaruP2ServerInterface/";
        	// Registry registry = LocateRegistry.getRegistry(port);
        	
        	 Registry registry = LocateRegistry.getRegistry();
         	
        	 
            ArrayList<TirunagaruP2CryptoCoin> coins = new ArrayList<TirunagaruP2CryptoCoin>();
     		Scanner scanner = new Scanner(System.in);
     		boolean wh = true;
     		registry.rebind(rmiUrl+"userdetails", new TirunagaruP2CryptoCoin());	
    		registry.rebind(rmiUrl+"authentication", new TirunagaruP2CryptoCoin());
    		System.out.println(rmiUrl+"userdetails" +"&" + rmiUrl+"authentication"+" : BINDED");
        
     		while(wh)
     	{	System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
     		System.out.println(" 1. Add Coin 2.Remove Coin 3. Edit coin 4.Show Coin Details 5.EXIT");
     		System.out.println(" Enter your choice:");
     		String option = scanner.nextLine();
     		switch(option)
     		{
     		case "1":
     				TirunagaruP2CryptoCoin maincoin = new TirunagaruP2CryptoCoin();
     				
     				System.out.println("Enter the COIN name: ");
     				String cname = scanner.nextLine();
     				maincoin.setName(cname);
     				System.out.println("Enter The abbreviated Name for the COIN:");
     				String ab = scanner.nextLine();
     				maincoin.setAbName(ab);
     				System.out.println("Enter the description:");
     				String description = scanner.nextLine();
     				maincoin.setDescription(description);
     				scanner.reset();
     				System.out.println("Enter the Market Cap:");
     				int market = scanner.nextInt();
     				maincoin.setMarketCap(market);
     				System.out.println("Enter the Volume:");
     				maincoin.setVolume(scanner.nextInt());
     				System.out.println("Enter the Price of the COIN: $");
     				maincoin.setPrice(scanner.nextInt());
     				maincoin.setDate(new Date());
     				coins.add(maincoin);
     				
     				registry.rebind(rmiUrl+(maincoin.getName()), maincoin);
         			
     			
     			
     		/*	Date date= new Date();  //getTime() returns current time in milliseconds
     			TirunagaruP2CryptoCoin bitcoin = new TirunagaruP2CryptoCoin("bitcoin", "BTC", "bitcoin coin", 10, 0, 100, date);
     			Date d2 = new Date();
     			TirunagaruP2CryptoCoin ethereum = new TirunagaruP2CryptoCoin("ethereum", "ETH", "ethereum coin", 10, 0, 90, d2);
     			Date d3 = new Date();
     			TirunagaruP2CryptoCoin litecoin = new TirunagaruP2CryptoCoin("litecoin", "LTC", "litecoin coin", 10, 0, 10, d3);
     			Date d4 = new Date();
     			TirunagaruP2CryptoCoin ripple = new TirunagaruP2CryptoCoin("ripple", "RIP", "ripple coin", 10, 0, 40, d4);
     			Date d5 = new Date();
     			TirunagaruP2CryptoCoin blackcoin = new TirunagaruP2CryptoCoin("blackcoin", "BLK", "blackcoin coin", 10, 0, 50, d5); 
     			coins.add(bitcoin);
     			coins.add(ethereum);
     			coins.add(litecoin);
     			coins.add(ripple);
     			coins.add(blackcoin); 
     			
     			registry.rebind(rmiUrl+"bitcoin", bitcoin);
     			registry.rebind(rmiUrl+"ethereum", ethereum);	
     			registry.rebind(rmiUrl+"litecoin", litecoin);     				
     			registry.rebind(rmiUrl+"ripple",ripple);     				
     			registry.rebind(rmiUrl+"blackcoin", blackcoin);
     			*/
     		//	registry.rebind(rmiUrl+"userdetails", new TirunagaruP2CryptoCoin());	
     		     
     			
     			
     			// Naming.rebind("rmi://localhost/TirunagaruP2ServerInterface/bitcoin", bitcoin);
     			// Naming.rebind("rmi://localhost/TirunagaruP2ServerInterface/ethereum", ethereum);	
     			// Naming.rebind("rmi://localhost/TirunagaruP2ServerInterface/litecoin", litecoin);     				
     			// Naming.rebind("rmi://localhost/TirunagaruP2ServerInterface/ripple",ripple);     				
     			// Naming.rebind("rmi://localhost/TirunagaruP2ServerInterface/blackcoin", blackcoin);
     			// Naming.rebind("rmi://localhost/TirunagaruP2ServerInterface/userdetails", new TirunagaruP2CryptoCoin());	
     		    
     			 System.out.println("Added Coin to RMI Registry");
     			break;
     			
     		case "2":
     			System.out.println("Enter the coin abbreviated Name to remove coin:");
     			String findCoin = scanner.nextLine();
     			int index_remove = -1 ;
     			boolean flag_remove = false;
     			//TirunagaruP2CryptoCoin crypto_remove = new TirunagaruP2CryptoCoin(); 
     			for (int i = 0; i < coins.size(); i++) {
     				
     				if(coins.get(i).getAbName().equalsIgnoreCase(findCoin))
     				{
     				String name = rmiUrl + coins.get(i).getName();
     				registry.unbind(name);
 					System.out.println("Removed Coin from RMI Registry");
 					index_remove = i;
 					flag_remove = true;
 	     			break;
     				}
				}
     			
     			
     	/*		for (TirunagaruP2CryptoCoin crypto : coins) {
     				if(crypto.getAbName().equalsIgnoreCase(findCoin)){
     					String name = rmiUrl + crypto.getName();
     				//	Naming.unbind(name);
     					registry.unbind(name);
     					System.out.println("Removed Coin from RMI Registry");
     				//	crypto = null;
     			//		crypto_remove = crypto;
     					flag_remove = true;
     	     			break;
     				}
     			*/	
     			if(flag_remove == false)
     			{
     				System.out.println("Coin is not present");
     			}
     			else { coins.remove(index_remove); }
     			break;
     		case "3":
     			System.out.println("Enter abbreviated Name of the Coin to Edit:");
     			String EditCoin = scanner.nextLine();
     			boolean flag_edit = false;
     			TirunagaruP2CryptoCoin cc = null;
     			for (TirunagaruP2CryptoCoin crypto : coins) {
     				if(crypto.getAbName().equalsIgnoreCase(EditCoin)){
     					cc=crypto;
     					flag_edit = true;
     					break;
     				}
     			}
     			
     			if (!flag_edit) {
				 System.out.println("COIN is not Present To EDIT");	
				}
     			
     			else {
     			System.out.println("1. name  2. Abbreviated name 3.Description 4.MarketCap 5.tradingVolume 6. Price");
     			String opt = scanner.nextLine();
     			for (TirunagaruP2CryptoCoin crypto : coins) {
     				if(crypto.getAbName().equalsIgnoreCase(EditCoin)){
     					cc=crypto;
     					break;
     				}
     			}
     		
     			if(cc == null){
     				System.out.println("No Object Found");
     			}
     			else {
     				if(opt.equalsIgnoreCase("1"))
     				{
     					System.out.println("Enter the new Name");
     					cc.setName(scanner.nextLine());
     				}
     				else if(opt.equalsIgnoreCase("2"))
     				{
     					System.out.println("Enter the Abbreviated Name");
     					cc.setAbName(scanner.nextLine());
     				}
     				else if(opt.equalsIgnoreCase("3"))
     				{
     					System.out.println("Enter the New Description");
     					cc.setDescription(scanner.nextLine());
     				}
     				else  if(opt.equalsIgnoreCase("4"))
     				{
     					System.out.println("Enter the new Market Cap");
     					cc.setMarketCap(scanner.nextInt());
     				}
     				else if(opt.equalsIgnoreCase("5"))
     				{
     					System.out.println("Enter the new Volume");
     					cc.setVolume(scanner.nextInt());
     				}
     				else if(opt.equalsIgnoreCase("6"))
     				{
     					System.out.println("Enter the new Price");
     					cc.setPrice(scanner.nextInt());
     				}
     				else
     				{
     					break;
     				}
     				
     			}
     			String name = rmiUrl+ cc.getName();
     			registry.rebind(name,cc);
				//Naming.rebind(name,cc);
				System.out.println("Edited coin is Re- Binded to RMI Registry");
     			
     			}
     			break;
				
     		case "4":
     			//System.out.println("Enter the Coin abbreviated Name to get Details:");
     			//String coin1 = scanner.nextLine();
     			System.out.println("All Coin Details are :");
     		//	boolean flag_details = false;
     			for (TirunagaruP2CryptoCoin crypto : coins) {
     			//	if(crypto.getAbName().equalsIgnoreCase(coin1)){
     			//		System.out.println("Details of Coin"+ coin1);
     					System.out.println(crypto.getName()+"|"+crypto.getAbName()+"|"+crypto.getDescription()+"|"+crypto.getMarketCap()+"|"+crypto.getVolume()+"|"+crypto.getPrice()+"|"+crypto.getDate());
     			//		flag_details = true;
     			//		break;
     			//	}
     			}
    // 			if (!flag_details) {	System.out.println("There is No COIN with Abbreviated Name :"+coin1);}
     			break;
     		case "5":
     			wh = false;
     		}
     		
     		
     	}
     	scanner.close();
   //  	registry.rebind(rmiUrl+"userdetails", new TirunagaruP2CryptoCoin());	
	//	registry.rebind(rmiUrl+"authentication", new TirunagaruP2CryptoCoin());
        System.out.println("--------------------------------------------------------------------------------------------");
		
        }

     	  catch (RemoteException e) {
			e.printStackTrace();
		} catch (NotBoundException e) {
			e.printStackTrace();
		}	
        
         System.out.println("Server ready");
      
		
		
	}

}
