import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class Example4PowTest {
	@Test
	void test1() {
		double actual = Example4Pow.pow(2.0, 5);
		Assertions.assertEquals(32.0, actual, 0.00001);
	}

	@Test
	void test2() {
		double actual = Example4Pow.pow(-2.0, 5);
		Assertions.assertEquals(-32.0, actual, 0.00001);
	}

	@Test
	void test3() {
		double actual = Example4Pow.pow(-2.0, 4);
		Assertions.assertEquals(16.0, actual, 0.00001);
	}

	@Test
	void test4() {
		double actual = Example4Pow.pow(0.0, 4);
		Assertions.assertEquals(0.0, actual, 0.00001);
	}

	@Test
	void test5() {
		double actual = Example4Pow.pow(2.0, 0);
		Assertions.assertEquals(1.0, actual, 0.00001);
	}

	@Test
	void test6() {
		double actual = Example4Pow.pow(0.0, 0);
		Assertions.assertEquals(1.0, actual, 0.00001);
	}
}
