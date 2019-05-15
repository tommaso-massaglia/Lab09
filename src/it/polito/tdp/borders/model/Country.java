package it.polito.tdp.borders.model;

public class Country {

	private int statecode;
	private String stateabb;
	private String statename;

	public Country(int statecode, String stateabb, String statename) {
		this.statecode = statecode;
		this.stateabb = stateabb;
		this.statename = statename;
	}

	public int getStatecode() {
		return statecode;
	}

	public String getStateabb() {
		return stateabb;
	}

	public String getStatename() {
		return statename;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + statecode;
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Country other = (Country) obj;
		if (statecode != other.statecode)
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Country [statecode=" + statecode + ", stateabb=" + stateabb + ", statename=" + statename + "]";
	}
	

}
