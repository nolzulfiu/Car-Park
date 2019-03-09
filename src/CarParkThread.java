import java.net.*;
import java.io.*;

public class CarParkThread extends Thread {
	
	private Socket carSocket = null;
	private CarParkState carParkState;
	private String myThreadName;
	
	public CarParkThread(Socket carSocket, String ThreadName, CarParkState SharedObject) {
		
		this.carSocket = carSocket;
		carParkState = SharedObject;
		myThreadName = ThreadName;
	}
	  
	public void run() {
		  
		try {
			    	
			System.out.println(myThreadName + " initialising.");
			PrintWriter out = new PrintWriter(carSocket.getOutputStream(), true);
			BufferedReader in = new BufferedReader(new InputStreamReader(carSocket.getInputStream()));
			String inputLine, outputLine;
			  
			while ((inputLine = in.readLine()) != null) {
				  		    		  
				try {
					carParkState.acquireLock(myThreadName);
					outputLine = carParkState.processInput(myThreadName, inputLine);
					out.println(outputLine);
					carParkState.releaseLock(myThreadName);
				}
				catch(InterruptedException e) {
					System.err.println("Failed to get lock when reading: " + e);
				}
			}
			
				      
			out.close();
			in.close();
			carSocket.close();
		
		}
		catch (IOException e) {
			e.printStackTrace();
		}
	}
}
