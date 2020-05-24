
public class Fractional {
	public final long numerator;
	public final long denominator;
	public static final String NotANumber = "Not a Number";
	public static final String PositiveInfinity = "+Infinity";
	public static final String NegativeInfinity = "-Infinity";
	private FractionalNumber fractionalNumber;

	Fractional(long numerator, long denominator) {
		this.numerator = numerator;
		this.denominator = denominator;
		fractionalNumber = new FractionalNumber(denominator, numerator);
	}

}
