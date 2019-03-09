
public class CarParkState {
	
	private boolean accessing = false;
	private int Floor1EmptySpaces;
	private int Floor2EmptySpaces;
	
	CarParkState(int floor1EmptySpaces, int floor2EmptySpaces) {
		Floor1EmptySpaces = floor1EmptySpaces;
		Floor2EmptySpaces = floor2EmptySpaces;
	}
	
	public synchronized void acquireLock(String ThreadName) throws InterruptedException {
			  
		System.out.println(ThreadName + " is attempting to acquire a lock!");
		
		//Check that there are free spaces, if not make the entrance thread wait
		
		if (ThreadName.contains("Entrance") && Floor1EmptySpaces < 1 && Floor2EmptySpaces < 1) {
			System.out.println("No available spaces, please wait!");
			while (ThreadName.contains("Entrance") && Floor1EmptySpaces < 1 && Floor2EmptySpaces < 1) wait();
		}
		
		while (accessing) {
			System.out.println(ThreadName + " waiting to get a lock as someone else is accessing...");
			wait();
		}
		
		accessing = true;
		System.out.println(ThreadName + " got a lock!"); 
	}
	
	  
	public synchronized void releaseLock(String ThreadName) {
		accessing = false;
		notify();
		System.out.println(ThreadName + " released a lock!");
	}
	
	

	public synchronized String processInput(String myThreadName, String theInput) {
		
		System.out.println(myThreadName + " received " + theInput);
		String theOutput = null;
		
		if (theInput.equalsIgnoreCase("Press")) {
			
			switch (myThreadName.toLowerCase()) {
    			case "entrance1":
    				if (Floor1EmptySpaces < 1) {
    					Floor2EmptySpaces -= 1;
    				} 
    				else Floor1EmptySpaces -= 1;
    				theOutput = "Entrance 1: Car has entered Car Park, No. of available spaces on Floor 1 is " + Floor1EmptySpaces + "" + 
    						", No. of available spaces on Floor 2 is " + Floor2EmptySpaces;
    				break;
    			case "exit1":
    		        if (Floor1EmptySpaces > 19 && Floor2EmptySpaces > 19) {
    		        	theOutput = "Cannot exit as there are no cars on this floor!";
    		        	break;
    		        } else {
    		        	if (Floor1EmptySpaces > 19){
    		        		Floor2EmptySpaces += 1;
    		        	} 
    		        	else Floor1EmptySpaces += 1;
    		        	theOutput = "Exit 1: Car has left Car Park, No. of available spaces on Floor 1 is " + Floor1EmptySpaces + "" + 
    		        			", No. of available spaces on Floor 2 is " + Floor2EmptySpaces;
    		        }
    				break;
    			case "entrance2":
    				if (Floor2EmptySpaces < 1) {
    					Floor1EmptySpaces -= 1;
    				}
    				else Floor2EmptySpaces -= 1;
    				theOutput = "Entrance 2: Car has entered Car Park, No. of available spaces on Floor 1 is " + Floor1EmptySpaces + "" + 
    						", No. of available spaces on Floor 2 is " + Floor2EmptySpaces;
    				break;
    			case "exit2":
    				if (Floor2EmptySpaces > 19 && Floor1EmptySpaces > 19) {
    		        	theOutput = "Cannot exit as there are no cars on this floor!";
    		        	break;
    		        } else {
    		        	if (Floor2EmptySpaces > 19){
    		        		Floor1EmptySpaces += 1;
    		        	} 
    		        	else Floor2EmptySpaces += 1;
    		        	theOutput = "Exit 2: Car has left Car Park, No. of available spaces on Floor 1 is " + Floor1EmptySpaces + "" + 
    		        			", No. of available spaces on Floor 2 is " + Floor2EmptySpaces;
    		        }
    				break;
    			default:
    				System.out.println("Error: Thread not recognised.");
    				break;
			}
		} 
		else theOutput = "I only recognize 'Press'";
 
		System.out.println(theOutput);
		return theOutput;
	}
}
