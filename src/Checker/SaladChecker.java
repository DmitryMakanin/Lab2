package Checker;

import java.io.Serializable;

import Vegetables.Vegetable;


public class SaladChecker implements Serializable {

	private static final long serialVersionUID = 1L;
	
	private ParameterChecker lengthChecker;
	private ParameterChecker nameChecker;
	
	protected SaladChecker(Builder builder) {
		setLengthChecker(builder.lengthChecker);
		setNameChecker(builder.nameChecker);
	}

	public boolean checkVegetable(Vegetable vegetable) {
		//if (lengthChecker != null && !lengthChecker.check(vegetable.getName())) return false;
		//if (nameChecker != null && !nameChecker.check(vegetable.getName())) return false;
		return true;
	}
	
	public ParameterChecker getLengthChecker() {
		return lengthChecker;
	}

	public void setLengthChecker(ParameterChecker lengthChecker) {
		this.lengthChecker = lengthChecker;
	}

		public ParameterChecker getNameChecker() {
		return nameChecker;
	}

	public void setNameChecker(ParameterChecker nameChecker) {
		this.nameChecker = nameChecker;
	}

		public static class Builder {
			public ParameterChecker lengthChecker;
			public ParameterChecker nameChecker;

			public Builder() {
				lengthChecker = null;
				nameChecker = null;
			}
			
			public Builder lengthChecker(ParameterChecker lengthCheck) {
				this.lengthChecker = lengthCheck;
				return this;
			}
			
			public Builder nameChecker(ParameterChecker nameCheck) {
				this.nameChecker = nameCheck;
				return this;
			}
	
			public SaladChecker build() {
				return new SaladChecker(this);
			}
		}
	
}
