import java.util.Date;

// пример ассоциации
class Author {
	String firstName;
	String middleName;
	String lastName;
	Integer birthYear; // в полях использования класса вместо примитивного типа позволяет задавать значение null,
	Integer deathYear; // показывая, что поле не проинициализировано, например пустой deathYear для ещё живущего автора
}

class Book {
	String title;
	Author author; // ассоциация с классом Author, так как могут быть книги, у которых автор неизвестен
	Integer publishYear;
	Integer pagesAmount;
}

// пример агрегации
class Developer {
	String name;
	String email;
	String[] competences;
	String level;
}

class Team {
	String project;
	Developer lead;
	Developer[] developers; // команда не имеет смысла без разработчиков
	Date createdAt;
	Date deletedAt;
}

// пример композиции
class Faculty {
	String name;
	String address;
	String phone;
	String dean;
}

class University {
	String name;
	String address;
	String phone;
	String rector;
	Faculty[] faculties; // университет не имеет смысла без факультетов, но и факультеты не могут существовать без университета
}

// сравнение агрегации и наследования
class BaseClass {
	void someMethod() {
		System.out.println("BaseClass.someMethod()");
	}
}

/*
 * class ChildClass IS class BaseClass
 */
class ChildClass extends BaseClass {}

/*
 * class AggregationClass HAS class BaseClass
 */
class AggregationClass {
	private final BaseClass baseClass = new BaseClass();
	void someMethod() {
		// делегирование работы метода другому методу
		baseClass.someMethod();
	}
}

public class Example3AggregationVsInheritance {
	static void doSomething(BaseClass baseClass) {
		baseClass.someMethod();
	}

	public static void main(String[] args) {
		BaseClass baseClass = new BaseClass();
		ChildClass childClass = new ChildClass();
		AggregationClass aggregationClass = new AggregationClass();

		baseClass.someMethod();
		childClass.someMethod();
		aggregationClass.someMethod();

		doSomething(baseClass);
		doSomething(childClass);
		//doSomething(aggregationClass); // ошибка компиляции
	}
}
