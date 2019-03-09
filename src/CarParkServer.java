import java.net.*;
import java.io.*;

public class CarParkServer {
	
	public static void main(String[] args) throws IOException {
				
		ServerSocket CarParkServerSocket = null;
	    boolean listening = true;
	    int CarParkServerNumber = 4545;
	    
	    try {
	    	CarParkServerSocket = new ServerSocket(CarParkServerNumber);
	    } 
	    catch (IOException e) {
	    	System.err.println("Could not start Car Park Server specified port.");
	    	System.exit(-1);
	    }
	    
	    System.out.println("Car Park Server started");

	    
	    CarParkState CarParkStateObject = new CarParkState(20, 20);
	    
	    while (listening){
	    	new CarParkThread(CarParkServerSocket.accept(), "Entrance1", CarParkStateObject).start();
	    	new CarParkThread(CarParkServerSocket.accept(), "Exit1", CarParkStateObject).start();
	    	new CarParkThread(CarParkServerSocket.accept(), "Entrance2", CarParkStateObject).start();
	    	new CarParkThread(CarParkServerSocket.accept(), "Exit2", CarParkStateObject).start();
	    	System.out.println("New Car Park Server thread started.");
	    }
	    
	    CarParkServerSocket.close();
	}
}
