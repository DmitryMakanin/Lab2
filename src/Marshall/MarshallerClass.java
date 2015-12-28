package Marshall;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.OutputStream;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;

import Stock.Controller.Salad;

public class MarshallerClass {

	private JAXBContext jc;
    private Marshaller marshaller;
    private Unmarshaller unmarshaller;

    public MarshallerClass() throws JAXBException {
        this.jc = JAXBContext.newInstance(Salad.class);
        marshaller = jc.createMarshaller();
        marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
        unmarshaller = jc.createUnmarshaller();
    }
    public void marshall(Salad salad, String fileName) throws FileNotFoundException, JAXBException {
        OutputStream os = new FileOutputStream(fileName);
        marshaller.marshal(salad, os);   
    }
    
    public Salad unmarshall(String filename) throws JAXBException {
        return (Salad) unmarshaller.unmarshal(new File(filename));
    }
	
}
