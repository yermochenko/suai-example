import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;

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

	public static void main(String[] args) throws ClassNotFoundException, NoSuchMethodException, InvocationTargetException, InstantiationException, IllegalAccessException {
		Class<?> x = Class.forName("java.util.Date");
		Constructor<?> c1 = x.getConstructor();
		Object obj1 = c1.newInstance();
		System.out.println(obj1);
		Constructor<?> c2 = x.getConstructor(long.class);
		Object obj2 = c2.newInstance(0L);
		System.out.println(obj2);
		System.out.println(obj2.getClass() == obj2.getClass());
		Object result = x.getMethod("after", x).invoke(obj1, obj2);
		System.out.println(result);
	}
}
