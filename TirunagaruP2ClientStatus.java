import java.util.ArrayList;
import java.util.Date;

public class TirunagaruP2ClientStatus  implements java.io.Serializable {

	private static final long serialVersionUID = 1L;
	String username;
	String id;
	int power;
	ArrayList<Cryptocoin> coins = new ArrayList<Cryptocoin>();
	// Cryptocoin[] coins;
	//ArrayList<Cryptocoin> coins; 
	
	
	public String getUsername() {return username; }
	public void setUsername(String username) {this.username = username;}
	public String getId() {return id;}
	public void setId(String id) {this.id = id;}
	public int getPower() {return power;}
	public void setPower(int power) {this.power = power;}
	public ArrayList<Cryptocoin> getCoins() {return coins;}
	public void setCoins(ArrayList<Cryptocoin> coins) {this.coins = coins;}
//	public Cryptocoin[] getCoins() {return coins; }
//	public void setCoins(Cryptocoin[] coins) {	this.coins = coins;	}
	
}
class Cryptocoin implements java.io.Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	String coinname;
	int price;
	int quantity;
	Date date;
	public Cryptocoin(String coinname, int price, int quantity, Date date) {
		this.coinname = coinname;
		this.price = price;
		this.quantity = quantity;
		this.date = date;
	}
	public String getName() { return coinname; }
	public void setName(String name) { this.coinname = name;}
	public int getPrice() {return price;	}
	public void setPrice(int price) {this.price = price; }
	public int getQuantity() {return quantity;}
	public void setQuantity(int quantity) { this.quantity = quantity;	}
	public Date getDate() { return date; }
	public void setDate(Date date) { this.date = date;} 
	}