import java.rmi.Remote;
import java.rmi.RemoteException;

public interface TirunagaruP2CoinsInterface extends Remote {
	
	public int getPrice() throws RemoteException;
	public String getName() throws RemoteException;
	public boolean authenticate(String userName, String password) throws RemoteException;
	public String buy(String userName,String coinname, int quantity, int price) throws RemoteException;
	public String sell(String userName, String coinname, int quantity, int price) throws RemoteException;
	public String UserDetails(String userName) throws RemoteException;
}
