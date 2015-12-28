package RMI;
import java.rmi.Remote;
import java.rmi.RemoteException;

import java.util.concurrent.CopyOnWriteArrayList;

import Vegetables.Vegetable;

/**
 * Interface for RMI client-server
 * @author DMITRY
 *
 */
public interface RmiServerIntf extends Remote {
    public String getMessage() throws RemoteException;
    public void addVegetable(Vegetable vegetable) throws RemoteException;
	public CopyOnWriteArrayList<Vegetable> getVegetables() throws RemoteException;
	public void sortByCalories() throws RemoteException;
}