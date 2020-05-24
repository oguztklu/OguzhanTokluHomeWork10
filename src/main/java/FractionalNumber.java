// Oðuzhan TOKLU 16050111026

public class FractionalNumber {

	private long denominator;
	private long numerator;
	private String str;

	public FractionalNumber(long denominator, long numerator) {
		super();
		this.denominator = denominator;
		this.numerator = numerator;

		str = numerator + "/" + denominator;
	}

	public long getDenominator() {
		return denominator;
	}

	public void setDenominator(long denominator) {
		this.denominator = denominator;
	}

	public long getNumerator() {
		return numerator;
	}

	public void setNumerator(long numerator) {
		this.numerator = numerator;
	}

	public String getStr() {
		return str;
	}

	public void setStr(String str) {
		this.str = str;
	}

}
