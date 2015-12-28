package Checker;

import java.io.Serializable;

public class NameParameterChecker extends ParameterChecker implements Serializable {

	private static final long serialVersionUID = 1L;
	
	private String allowableValues;

	public NameParameterChecker(String allowableValues) {
		this.allowableValues = allowableValues;
	}

	@Override
	public boolean check(String value) {
		return value.toString().toLowerCase().contains(allowableValues);
	}

	@Override
	public boolean check(Integer value) {
		return allowableValues.contains(value.toString());
	}
	
	
}
