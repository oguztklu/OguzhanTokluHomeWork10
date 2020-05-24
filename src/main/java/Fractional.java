import java.util.ArrayList;

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

	Fractional simplify() {
		if (isNaN()) {
			throw new IllegalArgumentException("Cannot simplify a NaN number");
		}

		if (isInfinity()) {
			throw new IllegalArgumentException("Cannot simplify infinity");
		}

		int gcd = findGcd();

		return new Fractional(this.numerator / gcd, this.denominator / gcd);
	}

	// Not a Number (NaN)
	boolean isNaN() {
		if (this.denominator == 0 && this.numerator == 0) {
			return true;
		}

		return false;
	}

	boolean isInfinity() {
		if (this.denominator == 0 && this.numerator != 0) {
			return true;
		}

		return false;
	}

	int sign(long numerator, long denominator) {
		if (numerator == 0 && denominator == 0) {
			throw new ArithmeticException("NaN does not have a sign");
		}

		if (numerator != 0 && denominator == 0) {
			if (numerator > 0) {
				return 1;
			}
			return -1;
		}

		long multipication = numerator * denominator;
		if (multipication < 0) {
			return -1;
		}

		else if (multipication > 0) {
			return 1;
		}

		return 0;
	}

	int sign() {
		if (isInfinity()) {
			if (this.numerator > 0) {
				return 1;
			} else {
				return -1;
			}
		}

		if (isNaN()) {
			throw new ArithmeticException("NaN does not have a sign");
		}

		long multipication = this.denominator * this.numerator;
		if (multipication < 0) {
			return -1;
		} else if (multipication == 0) {
			return 0;
		} else {
			return 1;
		}
	}

	@Override
	public boolean equals(Object obj) {
		Fractional sample = (Fractional) obj;

		return this.getValue() == sample.getValue();
	}

	@Override
	public String toString() {
		if (isNaN()) {
			return NotANumber;
		}

		if (isInfinity()) {
			if (sign() == +1) {
				return PositiveInfinity;
			} else {
				return NegativeInfinity;
			}
		}

		return this.fractionalNumber.getStr();
	}

	public int findGcd() {

		if (isNaN()) {
			throw new IllegalArgumentException("Cannot find gcd for a NaN number");

		}

		if (isInfinity()) {
			throw new IllegalArgumentException("Cannot find gcd for a infinity number");
		}

		int counter = 1;
		ArrayList<Integer> listOfEbob = new ArrayList<Integer>();

		long numerator = Math.abs(this.numerator);
		long denominator = Math.abs(this.denominator);

		if (numerator > denominator) {
			while (counter <= denominator) {
				if ((denominator % counter == 0) && (numerator % counter == 0)) {
					listOfEbob.add(counter);
				}
				counter++;
			}
		}

		else {
			while (counter <= numerator) {
				if ((denominator % counter == 0) && (numerator % counter == 0)) {
					listOfEbob.add(counter);
				}
				counter++;
			}
		}

		return listOfEbob.get(listOfEbob.size() - 1);
	}

	double getValue() {
		return (double) numerator / denominator;
	}

}
