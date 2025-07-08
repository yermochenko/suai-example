class Point2D {
	private final double x; // поля с модификатором final, это переменные, первое значение которой является
	private final double y; // в то же время и последним, то есть константы

	public Point2D(double x, double y) {
		this.x = x;
		this.y = y;
	}

	// методы с модификатором final - это методы, "последние" в цепочке переопределённых методов, то есть
	// данные методы наследуются подклассами, но не могут быть переопределены в подклассах
	public final double getX() {
		return x;
	}

	public final double getY() {
		return y;
	}
}

// класс с модификатором final является "последним" в иерархии наследования, то есть у этого класса вообще не
// может быть наследников
final class Point3D extends Point2D {
	private final double z;

	public Point3D(double x, double y, double z) {
		super(x, y);
		this.z = z;
	}

	public double getZ() {
		return z;
	}
}

public class Example2Constructors {
	public static void main(String[] args) {
		Point3D point1 = new Point3D(1.23, 4.56, 7.89);
		System.out.printf("(%.2f; %.2f; %.2f)\n", point1.getX(), point1.getY(), point1.getZ()); // пример форматированного вывода
	}
}
