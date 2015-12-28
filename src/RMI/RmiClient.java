package RMI;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.Serializable;
import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.concurrent.locks.ReentrantLock;

import Vegetables.Vegetable;

/**
 * RMI Client class
 * @author DMITRY
 *
 */
public class RmiClient implements Serializable { 
	private static final long serialVersionUID = 1L;
	static RmiServerIntf obj = null;

	/**
	 * Puts into obj variable lookuped object
	 * gets from obj Hello World
	 * @return
	 * @throws MalformedURLException
	 * @throws NotBoundException
	 * @throws RemoteException
	 */
    public String getMessage() throws MalformedURLException, NotBoundException, RemoteException { 
        try { 
            obj = (RmiServerIntf)Naming.lookup("//localhost/RmiServer");
            return obj.getMessage();
        } catch (Exception e) { 
            System.err.println("RmiClient exception: " + e); 
            e.printStackTrace(); 
            
            return e.getMessage();
        } 
    }
    
    /**
     * shows vegetables in obj
     * @throws RemoteException
     */
    private static void showVegetables() throws RemoteException {
    	List<Vegetable> vegetables = obj.getVegetables();
    	System.out.println("===== SHOW ALL VEGETABLES IN SALAD ===== ");
		int indexNumber = 1;
		for (Vegetable veg : vegetables ) {
			System.out.println(indexNumber + ". " + veg.name + " (from " + veg.country +  ", manufactured on " + getDateString(veg.dateOfManufacture) + ", calories " + veg.calories + ", type \"" + veg.type + "\")");
			indexNumber++;
		}
    }
    
    /**
     * Processes Date date into normal string form
     * @param date
     * @return normal form of date
     */
    @SuppressWarnings("deprecation")
	public static String getDateString(Date date) {
		return Integer.toString(date.getDate()) + "-" + Integer.toString(date.getMonth()) + "-" + Integer.toString(date.getYear() + 1900);
	}

    /**
     * Main method to work with server
     * @param args
     * @throws IOException
     * @throws NotBoundException
     */
	public static void main(String args[]) throws IOException, NotBoundException {
    	RmiClient cli = new RmiClient();

    	System.out.println(cli.getMessage());
    	
    	BufferedReader input = new BufferedReader(new InputStreamReader(System.in));
    	ConsoleCreator consoleCreator = new ConsoleCreator(input);
    	Integer choice = 0;
    	String menu = "0) add prepared pack of vegetables\n"
    			+ "1) add vegetable\n"
    			+ "2) show vegetables\n"
    			+ "3) sort by calories and show\n"
    			+ "9) exit\n";
    	boolean stop = false;
    	
    	while ( !stop ) {
    		System.out.println("Choose an action: " + "\n" + menu);
    		try {
                choice = Integer.parseInt(input.readLine());
            } catch (IOException e) {
            	e.printStackTrace();
            } catch (Exception e) {
                e.printStackTrace();
            }
    		 
    		switch (choice) {
    		case 0:
    			ReentrantLock lock0 = new ReentrantLock();
    			lock0.lock();
    			try {
	    			List<Vegetable> list = new ArrayList<Vegetable>();
	    			list = consoleCreator.getListOfPreparedVegetables();
	    			for ( Vegetable veg : list ) {
	    				obj.addVegetable(veg);
	    			}
    			} finally {
    				lock0.unlock();
    			}
    			break;
            case 1:
            	ReentrantLock lock1 = new ReentrantLock();
    			lock1.lock();
    			try {
	            	Vegetable vegetable = new Vegetable();
	            	vegetable = consoleCreator.createVegetable();
	                obj.addVegetable(vegetable);
    			} finally {
    				lock1.unlock();
    			}
                break;
            case 2:
            	ReentrantLock lock4 = new ReentrantLock();
            	lock4.lock();
            	try {
            		showVegetables();
            	} finally {
            		lock4.unlock();
            	}
        		break;
            case 3:
            	ReentrantLock lock3 = new ReentrantLock();
    			lock3.lock();
    			try {
    				obj.sortByCalories();
    				showVegetables();
    			} finally {
    				lock3.unlock();
    			}
            	break;
            case 9:
            	System.exit(1);
            default:
            	System.out.println("bad choice");
    		}
    	}
    	
    }
}