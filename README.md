# Distributed-Trading-System-using-Java-RMI
Steps to run the program:

Server Side: 
open the command prompt, compile 
  Cryptocoin.java 
	TirunagaruP2CryptoCoinInterface.java
	TirunagaruP2CryptoCoin.java
	TirunagaruP2ClientStatus.java
	TirunagaruP2ServerDriver.java

Using rmic complier, compile TirunagaruP2CryptoCoin class file
  	rmic TirunagaruP2CryptoCoin
Start the RMI Registry
    rmiregistry <port number>
Run TirunagaruP2ServerDriver class file.
    java TirunagaruP1ServerDriver portnumber

Client Side:
copy the TirunagaruP2CryptoCoin_Stub.class to the client side.
Compile TirunagaruP2Client.java and TirunagaruP2CryptoCoin.java
   java TirunagaruP1Client IPaddress portnumber

