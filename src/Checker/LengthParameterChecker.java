package Checker;

import java.io.Serializable;

public class LengthParameterChecker extends ParameterChecker implements Serializable {
	private static final long serialVersionUID = 1L;
	private int maxNameLength;
	
	public LengthParameterChecker(Integer _maxNameLength) {
		this.maxNameLength = _maxNameLength;
	}
	
	public boolean check(String value) { return value.length() <= maxNameLength; }
	public boolean check(Integer value) { return value <= maxNameLength; }
}
