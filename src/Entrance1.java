import java.io.*;
import java.net.*;

public class Entrance1 {
	
	public static void main(String[] args) throws IOException {
		
	    Socket EntranceSocket = null;
	    BufferedReader in = null;
	    PrintWriter out = null;

	    String CarParkServerName = "localhost";
	    int CarParkSocketNumber = 4545;
	    
	    try {
	    	EntranceSocket = new Socket(CarParkServerName, CarParkSocketNumber);
            out = new PrintWriter(EntranceSocket.getOutputStream(), true);
            in = new BufferedReader(new InputStreamReader(EntranceSocket.getInputStream()));
	    }
        catch (UnknownHostException e) {
            System.err.println("Don't know about host: " + CarParkServerName);
            System.exit(1);
        } 
        catch (IOException e) {
            System.err.println("Couldn't get I/O for the connection to: "+ CarParkSocketNumber);
            System.exit(1);
        }
	    
        BufferedReader stdIn = new BufferedReader(new InputStreamReader(System.in));
        String fromUser;
	    
        System.out.println("Initialised Entrance 1 client and IO connections");
                
        while (true) {
            
            fromUser = stdIn.readLine();
            
            if (fromUser != null) {
                System.out.println("Entrance1 sending " + fromUser + " to Car Park Server");
                out.println(fromUser);
            }

            
            System.out.println("Entrance 1 received " + in.readLine() + " from Car Park Server");
        }
        
	}
}
