class SomeClass {}

/*
// компилятор для это класс условно генерирует следующий код
class SomeClass extends Object { // добавляет наследование от класса Object
	// добавляет конструктор по умолчанию
	public SomeClass() {
		super(); // так как класс унаследован от другого класса, в конструкторе сначала вызывается конструктор супер класса
	}
}
//*/

public class Example1Object {
	public static void main(String[] args) {
		// создание объекта созданного класса
		SomeClass obj = new SomeClass();
		System.out.println(obj);
		// вызов унаследованных методов
		int number = obj.hashCode();
		System.out.println(number);
		System.out.println(Integer.toHexString(number));
		String str = obj.toString();
		System.out.println(str);
		Class<?> c = obj.getClass();
		System.out.println(c);
	}
}
