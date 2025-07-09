public class Example4Pow {
	/**
	 * Возведение в степень
	 * @param x основание
	 * @param n показатель
	 * @return x^n
	 */
	public static double pow(double x, int n) {
		double result = 1.0;
		while(n > 0) {
			if((n & 1) == 1) {
				result *= x;
			}
			n >>= 1;
			x = x * x;
		}
		return result;
	}

	public static void main(String[] args) {
	}
}
