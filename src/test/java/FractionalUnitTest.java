// Oðuzhan TOKLU 16050111026

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.Test;

class FractionalUnitTest {

	@Test
	public void testFindGcd() {

		// Gcd should be undefined for NotANumber case
		assertThrows(IllegalArgumentException.class, () -> {
			new Fractional(0, 0).findGcd();
		});

		// Gcd should be undefined for infinity cases
		assertThrows(IllegalArgumentException.class, () -> {
			new Fractional(5, 0).findGcd();
		});
		assertThrows(IllegalArgumentException.class, () -> {
			new Fractional(-5, 0).findGcd();
		});

		// All prime numbers should have the same gcd which is 1.
		assertEquals(new Fractional(13, 10).findGcd(), new Fractional(5, 18).findGcd());

		// Changing the places of numerator and denominator should not affect the gcd.
		assertEquals(new Fractional(9, 24).findGcd(), new Fractional(24, 9).findGcd());

		// Sing of Numbers should not affect the gcd.
		assertEquals(3, new Fractional(-9, 24).findGcd());
		assertEquals(3, new Fractional(9, 24).findGcd());
		assertEquals(new Fractional(-9, 24).findGcd(), new Fractional(24, 9).findGcd());

	}

	@Test
	public void testIsNaN() {
		assertEquals(Fractional.NotANumber, new Fractional(0, 0).toString());
	}

	@Test
	public void testSign() {

		// Sign should throw a arithmetic exception at NaN case.
		assertThrows(ArithmeticException.class, () -> {
			new Fractional(0, 0).sign();
		});

		// Sing should be positive at Positive Infinity case.
		assertEquals(+1, new Fractional(10, 0).sign());

		// Sing should be negative at Negative Infinity case.
		assertEquals(-1, new Fractional(-10, 0).sign());

		// Zero should do not have a sign.
		assertEquals(0, new Fractional(0, 10).sign());

		assertEquals(+1, new Fractional(10, 10).sign());
		assertEquals(-1, new Fractional(-10, 10).sign());
		assertEquals(+1, new Fractional(-10, -10).sign());
		assertEquals(-1, new Fractional(10, -10).sign());
	}

	@Test
	public void testSignWithInput() {
		// Sign should throw a arithmetic exception at NaN case.
		assertThrows(ArithmeticException.class, () -> {
			new Fractional(0, 0).sign(0, 0);

		});

		// Sing should be positive at Positive Infinity case.
		assertEquals(+1, new Fractional(10, 0).sign(10, 0));

		// Sing should be negative at Negative Infinity case.
		assertEquals(-1, new Fractional(-10, 0).sign(-10, 0));

		// Zero should do not have a sign.
		assertEquals(0, new Fractional(0, 10).sign(0, 10));

		assertEquals(+1, new Fractional(10, 10).sign(10, 10));
		assertEquals(-1, new Fractional(-10, 10).sign(-10, 10));
		assertEquals(+1, new Fractional(-10, -10).sign(-10, -10));
		assertEquals(-1, new Fractional(10, -10).sign(10, -10));
	}

	@Test
	public void testIsInfinity() {

		// Positive Infinity case
		assertEquals(true, new Fractional(10, 0).isInfinity());

		// Negative Infinity case
		assertEquals(true, new Fractional(-10, 0).isInfinity());

		// Not an infinity case
		assertEquals(false, new Fractional(10, 5).isInfinity());
		assertEquals(false, new Fractional(-10, 5).isInfinity());
		assertEquals(false, new Fractional(10, -5).isInfinity());
		assertEquals(false, new Fractional(-10, -5).isInfinity());
	}

	@Test
	public void testSimplfy() {

		// In case of NaN, simplyf() method should thrown IllegalArgument exception;
		assertThrows(IllegalArgumentException.class, () -> {
			new Fractional(0, 0).simplify();
		});

		// In case of Infinity, simplyf() method should thrown IllegalArgument
		// exception;
		assertThrows(IllegalArgumentException.class, () -> {
			new Fractional(1, 0).simplify();
		});
		assertThrows(IllegalArgumentException.class, () -> {
			new Fractional(-1, 0).simplify();
		});

		// Simplification checks.
		assertEquals("5/2", new Fractional(10, 4).simplify().toString());
		assertEquals("4/-3", new Fractional(8, -6).simplify().toString());
		assertEquals("-11/5", new Fractional(-33, 15).simplify().toString());
		assertEquals("-5/-2", new Fractional(-30, -12).simplify().toString());

	}

	@Test
	public void testEquals() {

		assertEquals(true, new Fractional(22, 11).equals(new Fractional(-10, -5)));
		assertEquals(true, new Fractional(-14, -7).equals(new Fractional(10, 5)));

		assertEquals(true, new Fractional(6, -3).equals(new Fractional(-10, 5)));
		assertEquals(true, new Fractional(-8, 4).equals(new Fractional(10, -5)));
	}

	@Test
	public void testToString() {

		// NaN case
		assertEquals(Fractional.NotANumber, new Fractional(0, 0).toString());

		// Positive Infinity Case
		assertEquals(Fractional.PositiveInfinity, new Fractional(1, 0).toString());

		// Negatife Infinity Case
		assertEquals(Fractional.NegativeInfinity, new Fractional(-1, 0).toString());

		// A Normal Case
		assertEquals("1/2", new Fractional(1, 2).toString());
	}

	@Test
	public void testGetValue() {
		assertEquals(2.0 / 5, new Fractional(2, 5).getValue());
	}
}
