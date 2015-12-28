package Vegetables;

import java.io.Serializable;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;

@XmlAccessorType(XmlAccessType.NONE)
public class Vegetable implements Serializable {
	private static final long serialVersionUID = 1L;
	
	public String name;
	public String country;
	public Date dateOfManufacture;
	public int calories;
	public String type;
	
	@XmlElement
	public String getName() {
		return name;
	}
	@XmlElement
	public String getCountry() {
		return country;
	}
	@XmlElement
	public Date getDateOfManufacture() {
		return dateOfManufacture;
	}
	@XmlElement
	public int getCalories() {
		return calories;
	}
	@XmlElement
	public String getType() {
		return type;
	}
	
	
	
	
	
	public Vegetable() {
		this.name = "0";
		this.country = "0";
		this.calories = 0;
		this.type = getType(null);
		
		try {
			this.dateOfManufacture = new SimpleDateFormat("dd.MM.yy").parse("01.01.2001");
		} catch (ParseException e) {
			e.printStackTrace();
		}
	}
	
	
	public Vegetable(String _name, String _country, String _date, int _calories, String _type) {
		this.name = _name;
		this.country = _country;
		this.calories = _calories;
		this.type = getType(_type);
		
		try {
			this.dateOfManufacture = new SimpleDateFormat("dd.MM.yy").parse(_date);
		} catch (ParseException e) {
			e.printStackTrace();
		}
	}
	
	public String getType(String typeName) {
		if (typeName == null)
			return "undef";
		switch (typeName) {
			case "carrot":
				return "carrot";
			case "cucumber":
				return "cucumber";
			case "sourcream":
				return "sourcream";
			default:
				return "undef";
		}
	}


	public void setCountry(String country) {
		this.country = country;
	}


	public void setDateOfManufacture(Date dateOfManufacture) {
		this.dateOfManufacture = dateOfManufacture;
	}


	public void setCalories(int calories) {
		this.calories = calories;
	}


	public void setType(String type) {
		this.type = type;
	}


	public void setName(String name) {
		this.name = name;
	}
	
    /**
     * Processes Date date into normal string form
     * @param date
     * @return normal form of date
     */
    @SuppressWarnings("deprecation")
	public String getDateString() {
		return Integer.toString(dateOfManufacture.getDate()) + "-" + Integer.toString(dateOfManufacture.getMonth()) + "-" + Integer.toString(dateOfManufacture.getYear() + 1900);
	}
}
