package Checker;

import java.io.Serializable;

public abstract class ParameterChecker implements Serializable{

	private static final long serialVersionUID = 1L;
	
	public abstract boolean check( String value );
	public abstract boolean check( Integer value );
}
