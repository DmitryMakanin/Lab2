package RMI;
import java.io.Serializable;
import java.rmi.Naming;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.rmi.registry.*;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.locks.ReentrantLock;

import Stock.Controller.Salad;
import Vegetables.Vegetable;

/**
 * RMI Server class
 * @author DMITRY
 *
 */
public class RmiServer extends UnicastRemoteObject implements RmiServerIntf, Serializable {
	private static final long serialVersionUID = 1L;
	
	/**
	 * String "Hello World"
	 */
	public static final String MESSAGE = "Hello world";

    Salad salad = new Salad();
    
    /**
     * constructs RmiServer()
     * @throws RemoteException
     */
    public RmiServer() throws RemoteException {
    }

    /**
     * Main function
     * @param args
     */
    public static void main(String args[]) {
    	System.out.println("RMI server started");

    	try {
    		LocateRegistry.createRegistry(1099); 
    		System.out.println("java RMI registry created.");
    	} catch (RemoteException e) {
    		System.out.println("java RMI registry already exists.");
    	}
            
    	try {
    		RmiServer obj = new RmiServer();
            Naming.rebind("//localhost/RmiServer", obj);
            System.out.println("PeerServer bound in registry");
        } catch (Exception e) {
            System.err.println("RMI server exception: " + e);
            e.printStackTrace();
        }
    }
    
    /**
     * returns Hello World to server
     */
    public String getMessage() {
        return MESSAGE;
    }

    /**
     * adds vegetables to salad
     */
	@Override
	public void addVegetable( Vegetable vegetable ) throws RemoteException {
		salad.addVegetable( vegetable );
	}

	/**
	 * gets vegetables from salad
	 * @return CopyOnWriteArrayList<Vegetable>
	 */
	@Override
	public CopyOnWriteArrayList<Vegetable> getVegetables() throws RemoteException {
		ReentrantLock lock = new ReentrantLock();
		lock.lock();
		CopyOnWriteArrayList<Vegetable> temp = new CopyOnWriteArrayList<Vegetable>();
		try {
			temp = salad.getSalad();
		} finally {
			lock.unlock();
		}
		return temp;
	}

	/**
	 * sorts vegetables by calories
	 */
	@Override
	public void sortByCalories() throws RemoteException {
		ReentrantLock lock = new ReentrantLock();
		lock.lock();
		try {
			salad.sortByCalories();
		} finally {
			lock.unlock();
		}
	}
}