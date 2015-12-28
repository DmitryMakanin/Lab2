package Serialize;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;


 /**
  * class Connector for writing and reading serialize data
  * @author DMITRY
  *
  */
public class SerializeConnector {
	private static FileOutputStream fos;
    private static ObjectOutputStream oos;
    private static FileInputStream fis;
    private static ObjectInputStream ois;
    
    /**
     * write method writes into the file by ObjectOutputStream 
     * @param filename String - the name of file to write in
     * @param obj
     * @throws IOException
     */
    public static void write(String filename, Object obj) throws IOException {
	fos = new FileOutputStream(filename);
	oos = new ObjectOutputStream(fos);
	oos.writeObject(obj);
	oos.flush();
	oos.close();
    }
    
    /**
     * read method read Object from file by ObjectInputStream
     * @param filename
     * @return Object, that was read from file
     * @throws IOException
     * @throws ClassNotFoundException
     */
    public static Object read(String filename) throws IOException, ClassNotFoundException {
	fis = new FileInputStream(filename);
	ois = new ObjectInputStream(fis);
	return ois.readObject();
    }
}
