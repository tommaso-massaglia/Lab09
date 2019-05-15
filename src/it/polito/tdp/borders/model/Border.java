package it.polito.tdp.borders.model;

public class Border {

	private int firstcountry;
	private int secondcountry;
	private int year;

	public Border(int firstcountry, int secondcountry, int year) {
		this.firstcountry = firstcountry;
		this.secondcountry = secondcountry;
		this.year = year;
	}

	public int getFirstcountry() {
		return firstcountry;
	}

	public int getSecondcountry() {
		return secondcountry;
	}

	public int getYear() {
		return year;
	}

	

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + firstcountry;
		result = prime * result + secondcountry;
		result = prime * result + year;
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
		Border other = (Border) obj;
		if (firstcountry != other.firstcountry)
			return false;
		if (secondcountry != other.secondcountry)
			return false;
		if (year != other.year)
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Border [firstcountry=" + firstcountry + ", secondcountry=" + secondcountry + ", year=" + year + "]";
	}

}
