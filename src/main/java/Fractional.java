
public class Fractional {
	public final long numerator;
	public final long denominator;
	public static final String NotANumber = "Not a Number";
	public static final String PositiveInfinity = "+Infinity";
	public static final String NegativeInfinity = "-Infinity";
	
	Fractional(long numerator, long denominator) {	
		if (denominator == 0) {
			this.numerator = numerator;
			this.denominator = denominator;			
		}
		else {
			if (numerator == 0) {
				this.numerator = 0;
				this.denominator = (int)Math.signum(denominator);
			}
			else {
				this.numerator = (numerator * denominator < 0 ? -1 : 1) * Math.abs(numerator);
				this.denominator = Math.abs(denominator);
			}
		}
	}
	
	Fractional(long value) {
		numerator = value;
		denominator = 1;
	}
	
	Fractional(double value) {
		if (value == 0) {
			// zero
			numerator = 0;
			denominator = 1;
		}
		else {
			if (Double.isNaN(value)) {
				numerator = 0;
				denominator = 0;
				return;				
			}
			
			if (value == Double.POSITIVE_INFINITY) {
				numerator = 1;
				denominator = 0;
				return;
			}

			if (value == Double.NEGATIVE_INFINITY) {
				numerator = -1;
				denominator = 0;
				return;
			}

			// fractional
			long fractionalSign = (long)Math.signum(value); 
			double absValue = Math.abs(value);
			// we should use BigDecimal for absValue instead of double (test with some large double values and you will see)

			int counter = 0;
			int zeroCount = 0;
			while (absValue - (long)absValue > 0) {
				counter++;
				absValue *= 10;
				if ((long)absValue % 10 == 0) {
					zeroCount++;
					int zeroCountThreshold = 5;
					if (zeroCount == zeroCountThreshold) {
						absValue /= Math.pow(10, zeroCountThreshold);
						break;
					}
				}
				else
					zeroCount = 0;
			}

			long a = (long)absValue;
			long b = (long)((absValue + Math.pow(10, -counter-1)) / value);
			long commonDivisor = gcd(a, b);

			numerator = fractionalSign * (a / commonDivisor); 
			denominator = (b / commonDivisor);
		}
	}
	
	Fractional simplify() {
		if (numerator == 0 || denominator == 1 || denominator == 0)
			return this;
		else {
			long fractionalSign = sign();
			long a = Math.abs(numerator);
			long b = Math.abs(denominator);

			long commonDivisor = gcd(a, b);
			if (commonDivisor > 1) {
				a = fractionalSign * (a / commonDivisor);
				b = (b / commonDivisor);
				
				return new Fractional(a, b);
			}
			else 
				return this;
		}
	}
	
	static long gcd(long a, long b) {
	    if (b == 0) 
	    	return a;
	    else
	    	return gcd(b, a % b);
	}	

	// Not a Number (NaN) 
	boolean isNaN() {
		return (numerator == 0 && denominator == 0);
	}

	boolean isInfinity() {
		return (numerator != 0 && denominator == 0);
	}
	
	static int sign(long numerator, long denominator) {
		if (numerator == 0 && denominator == 0) {
			throw new ArithmeticException("Not a Number has no sign!");
		}
		
		if (numerator == 0) {
			return 0;
		}

		if (denominator == 0) {
			return (numerator > 0 ? +1 : -1);
		}
		
		return (numerator * denominator > 0 ? +1 : -1);
	}
	
	int sign() {
		return sign(numerator, denominator);
	}
	
	double getValue() {
		return (double)numerator / denominator;
	}

	@Override
	public boolean equals(Object obj) {
		if (obj == null) {
			return false;
		}
		
		if (obj instanceof Fractional) {
			Fractional fractional = (Fractional)obj;

			if (isNaN() || fractional.isNaN()) {
				return false;
			}			
			
			Fractional fractional1 = simplify();
			Fractional fractional2 = fractional.simplify();
			
			return (fractional1.toString().equals(fractional2.toString()));
		}
		else
			return false;
	}
	
	@Override
	public String toString() {
		if (isNaN()) {
			return NotANumber;
		}
		
		if (isInfinity()) {
			switch (sign()) {
			case +1: return PositiveInfinity;
			case -1: return NegativeInfinity;
			default: return PositiveInfinity;
			}
		}
		
		if (denominator == 1) {
			return "" + numerator;
		}

		if (denominator == -1) {
			return "-" + numerator;
		}
		
		return numerator + "/" + denominator;
	}
}
