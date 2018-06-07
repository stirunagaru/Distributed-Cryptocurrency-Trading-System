import java.io.BufferedReader;
import java.io.EOFException;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

public class TirunagaruP2CryptoCoin extends UnicastRemoteObject implements TirunagaruP2CoinsInterface   {
	
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	String name;
	String abName;
	String description;
	int marketCap;
	static int volume;
	int price;
	Date date; //	Timestamp timeStamp;
	
	public TirunagaruP2CryptoCoin() throws RemoteException
	{
		super();
	}
		public TirunagaruP2CryptoCoin(String name, String abName, String description, int cap, int volume, int price, Date time) throws RemoteException
		{
			super();
		this.name	= name;
		this.abName = abName;
		this.description = description;
		this.marketCap = cap;
		TirunagaruP2CryptoCoin.volume = volume;
		this.price=price;
		//this.timeStamp = time;
		this.date = time;
			}
			
		
	
	public String getName() throws RemoteException  { return name; }
	public void setName(String name) { this.name = name; }
	public String getAbName() { return abName;}
	public void setAbName(String abName) { this.abName = abName;	}
	public String getDescription() { return description;}
	public void setDescription(String description) {this.description = description;	}
	public int getMarketCap() { return marketCap;}
	public void setMarketCap(int marketCap) { this.marketCap = marketCap; }
	public int getVolume() { return volume;}
	public void setVolume(int volume) { TirunagaruP2CryptoCoin.volume = volume; }
	public int getPrice() throws RemoteException  {return price;}
	public void setPrice(int price) {this.price = price;	}
/*	public Timestamp getTimeStamp() { return timeStamp;} public void setTimeStamp(Timestamp timeStamp) { this.timeStamp = timeStamp;}*/
	public Date getDate() {return date;	}
	public void setDate(Date date) {this.date = date;	}


	public boolean authenticate(String userName, String password) throws RemoteException {
		Map<String, String> userListFromFile=null;
		 BufferedReader users = null;
		  String fromTextFile = null;
		  try {
				users = new BufferedReader(new FileReader("userList.txt"));
				userListFromFile = new HashMap<String, String>();
				while((fromTextFile = users.readLine())!=null)
				 {
						/* getting data from file UN, passwords */
					String[] tempData = fromTextFile.split(" ");	
					userListFromFile.put(tempData[0], tempData[1]);
				 }
			   } 
			 catch (FileNotFoundException e) { System.out.println(" from UserNameFile() Method:"+e.getMessage());}
			 catch (IOException e) {System.out.println(" from UserNameFile() Method- HashMap:"+e.getMessage());}

		
		
		if ((userName != null && !userName.isEmpty())
				&& (password != null && !password.isEmpty())) {
			
			
			if((userListFromFile.containsKey(userName)) 
					&& (userListFromFile.get(userName).equalsIgnoreCase(password))) {
				return true;
			}
		}
		return false;
	}
	
	
	@Override
	public  synchronized String buy(String username, String coinname, int quantity, int price ) throws RemoteException {
		
		TirunagaruP2ClientStatus client = deserialize(username);
		if(client == null)
	 	  { //not found
			TirunagaruP2ClientStatus newclient = new TirunagaruP2ClientStatus();
			Cryptocoin newcoin = new Cryptocoin(coinname, price, quantity, new Date());
			System.out.println("Client Buying for the first tym and needed to be serialised after adding details");
			newclient.setPower((quantity*price)); 
			newclient.username = username;
			newclient.id = "id@"+username;
			newclient.coins.add(newcoin);
			serialize(newclient);
			System.out.println("Client coins bought and serialised : BUY");
			String ret = "Coin: ["+coinname+ "] bought Successfully with Quantity :-" +quantity;
			return ret;
	 	  }
		else
		{
			System.out.println("CLient already registered and bought the coins.");
			ArrayList<Cryptocoin> clientcoins = client.getCoins();
			int total_power = client.getPower();
		//	boolean flag = false;
			/*		for (Cryptocoin crypto: clientcoins) {
				System.out.println( crypto.getName() +"---- "+coinname+ "---");
				String dummy = crypto.getName();
				if(dummy.equalsIgnoreCase(coinname)) //coiname already existed 
				{
					//flag = true;
				System.out.println("coiname already existed ");
					total_power += (price *quantity);
					client.setPower(total_power);
					crypto.price = price;
					crypto.quantity += quantity;	
					crypto.date = new Date();
					serialize(client);
					return true;
				}		
			}  */
			
			for (int i = 0; i < clientcoins.size(); i++) {
				System.out.println( clientcoins.get(i).coinname+"---- "+coinname+ "---");
				
				if(clientcoins.get(i).coinname == coinname)
				{
					System.out.println("coiname already existed ");
					total_power += (price *quantity);
					client.setPower(total_power);
					clientcoins.get(i).quantity += quantity;
					clientcoins.get(i).date = new Date();
					serialize(client);
					String ret1 = "Coin: ["+coinname+ "] bought Successfully with Quantity :-" +quantity;
					return ret1;			
				}
				
			}
			
			//else{ // buying a new coin{
				System.out.println("coiname Not existed ");
				System.out.println("selected coin: "+coinname);
				Cryptocoin newcoin_flag = new Cryptocoin(coinname, price, quantity, new Date());
				System.out.println("Client Buying for the new coin and needed to be serialised after adding details");
				int total_power_flag = client.getPower();
				total_power_flag += (price *quantity);
				client.setPower(total_power_flag);
				client.coins.add(newcoin_flag);
				serialize(client);
				String ret2 = "Coin: ["+coinname+ "] bought Successfully with Quantity :-" +quantity;
				return ret2;
			//}
		
	//		return false;
	 	  }
	
		
		
	}
	@Override
	public synchronized String sell(String username, String coinname, int quantity, int price ) throws RemoteException 
	{
			TirunagaruP2ClientStatus client = deserialize(username);
			boolean flag = false;
			int index=-1;
			if(client == null)
		 	  { //not found
				System.out.println("Could not sell any Coins as your are a new User");
				System.out.println("Please Buy coins in-order to sell coins");
				String ret = "Could not sell any Coins as your are a new User. Please Buy coins in-order to sell coins ";				
				return ret;
		 	  }
			else // found // so have to do operation Selling
				{
				//	for (Cryptocoin clientcoiname : client.coins) 
				ArrayList<Cryptocoin> clientcoins = client.coins;
				for (int i = 0; i < clientcoins.size(); i++)
				{
						System.out.println(clientcoins.get(i).coinname+"----"+coinname);
						System.out.println(clientcoins.get(i).quantity+"----"+quantity);
						
						if(clientcoins.get(i).coinname.equalsIgnoreCase(coinname))
						{
							if(clientcoins.get(i).quantity < quantity) // quantity is greater the holded quantity
							{
								System.out.println("Given Quantity: "+ quantity +" is greater than your coin Quantity:"+clientcoins.get(i).quantity );
								System.out.println("please select the correct quantity");
								String ret1= "Given Quantity :-"+ quantity +" is greater than your coin Quantity :-"+clientcoins.get(i).quantity+ "Please select Correct Quantity";
 								return ret1;
							}
							else
							{
								
								clientcoins.get(i).quantity  = clientcoins.get(i).quantity - quantity;// remove the amount required to sell
								client.power = client.power - (quantity*price); // selling decreases the purchased power of a client
								Date date= new Date(); 
								clientcoins.get(i).setDate(date); // adjusting the date
								if(clientcoins.get(i).quantity==0){ clientcoins.get(i).price = 0;}
								flag = true;
								index =i;
								break;
								
							}
						}
				}
				
				if(flag){
					if(client.coins.get(index).quantity==0){client.coins.remove(index); }
					
					serialize(client);
					String ret2 = "Coin: ["+coinname+ "] bought Successfully with Quantity :-" +quantity;
					return ret2;
					
				}
				else
				{			System.out.println("Coiname not found - You did not buy the coin");
							System.out.println("Please Buy coins in-order to sell "+ coinname +" coin");
							String ret2 = "You did not buy the coin Yet, Please Buy coins in-order to sell "+ coinname +" COIN";
							return ret2;
				}				
					
				} 
			
			
		/* 	for(int i=0; i< client.coins.size();i++)
				{
				if(client.coins[i].coinname==coinname)
				{
						if(client.coins[i].quantity < quantity) // quantity is greater the holded quantity
						{
							System.out.println("Given Quantity: "+ quantity +" is greater than your coin Quantity:"+client.coins[i].quantity);
							System.out.println("please select the correct quantity");
							return false;
						}
						else
						{
							client.coins[i].quantity = client.coins[i].quantity-quantity;
							client.power = client.power - (quantity*price);
							serialize(client);
							return true;
						}	
				}
				else
				{
					System.out.println("Coiname not found - You did not buy the coin");
					System.out.println("Please Buy coins in-order to sell "+ coinname +"coin");
					return false;
				}		
			}*/
		 
		
	}
	
	
	public synchronized void  serialize( TirunagaruP2ClientStatus tcs)
	{
		try 
		{
		 String filename = tcs.getUsername() +".ser";
		 FileOutputStream fileSer =  new FileOutputStream(filename); 
         ObjectOutputStream objSer = new ObjectOutputStream(fileSer);
         System.out.println("Serializing data for : "+tcs.getUsername());
         objSer.writeObject(tcs);    
	         //Close all streams
          objSer.close();
	      fileSer.close();
	      System.out.println("Serialized data for :-"+tcs.getUsername());

		} catch (IOException e) 
		{
			e.printStackTrace();
		}		
	}
	
	public synchronized TirunagaruP2ClientStatus deserialize(String username)
	{
		 try
	      {
			 String filename = username +".ser";
	         FileInputStream fileDeSer = new FileInputStream(filename);
	         ObjectInputStream objDeSer = new ObjectInputStream(fileDeSer);
	         TirunagaruP2ClientStatus tcs = null;
	        
	         if(objDeSer.available()>=0) // for checking whether the user has already have a serializable file else look up for serialised file
	         {
			         try{
			        	  tcs = (TirunagaruP2ClientStatus) objDeSer.readObject();
			        	if(tcs.getUsername().equalsIgnoreCase(username))
			        	{ 	  objDeSer.close();
			        		  fileDeSer.close();
			        		return tcs;
			        	}	
			        	 
			         	} catch (EOFException e){}
	         }
	         else
	         {
	        	 objDeSer.close();
	        	 fileDeSer.close();
	        	 return tcs;
	         }
	         objDeSer.close();
	         fileDeSer.close();
		      }catch(IOException exp)
		      {
		        // exp.printStackTrace();
		         return null;
		      }catch(ClassNotFoundException cexp)
		      {
		         System.out.println("Student class not found");
		        // cexp.printStackTrace();
		         return null ;
		      }
			return null;   	
	}


	public String UserDetails(String userName) 
	{	String send = "";
		TirunagaruP2ClientStatus client = deserialize(userName);
		if(client == null)
	 	  { //not found
			System.out.println("You did not have any coins with your username:"+userName);
			send ="You did not have any coins with your username:"+userName;
			return send;
	 	  }
		else
		{
			send = send+"Username:"+client.getUsername()+" Purchase Power: "+client.getPower()+" ID:"+client.getId()+" coins: \n";
			System.out.println("Username:"+client.getUsername()+" Purchase Power: "+client.getPower()+" ID:"+client.getId()+" coins:- \n ");
			for (Cryptocoin clientcoiname : client.coins) 
			{
			send = send + clientcoiname.getName()+"|"+ clientcoiname.getPrice()+"|"+ clientcoiname.getQuantity()+"|"+ clientcoiname.getDate()+"\n";
			System.out.println( clientcoiname.getName()+" "+ clientcoiname.getPrice()+" "+ clientcoiname.getQuantity()+" "+ clientcoiname.getDate()+"\n");
			}
			return send;
		}
		
	}
	
	
	

}
	

	