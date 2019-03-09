import java.io.*;
import java.net.*;

public class Exit2 {
	
	public static void main(String[] args) throws IOException {
		
	    Socket ExitSocket = null;
	    BufferedReader in = null;
	    PrintWriter out = null;

	    String CarParkServerName = "localhost";
	    int CarParkSocketNumber = 4545;
	    
	    try {
	    	ExitSocket = new Socket(CarParkServerName, CarParkSocketNumber);
            out = new PrintWriter(ExitSocket.getOutputStream(), true);
            in = new BufferedReader(new InputStreamReader(ExitSocket.getInputStream()));
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
	    
        System.out.println("Initialised Exit 2 client and IO connections");
        
        
        while (true) {
            
            fromUser = stdIn.readLine();
            
            if (fromUser != null) {
                System.out.println("Exit 2 sending " + fromUser + " to Car Park Server");
                out.println(fromUser);
            }
            
            System.out.println("Exit 2 received " + in.readLine() + " from Car Park Server");
        }
            
        
        /*out.close();
        in.close();
        stdIn.close();
        ExitSocket.close();*/
        
	}
}
