package streams;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class StreamsAPI {

	static List<Employee> employees = new ArrayList<>();

	static {
		employees.add(new Employee("Alex", "Joshua", 10000.0, Arrays.asList("Project 1", "project2")));
		employees.add(new Employee("Peter", "Boyes", 12500.0, Arrays.asList("Project 3, Project 4")));
		employees.add(new Employee("Eileen", "Abisgold", 15000.0, Arrays.asList("Project 5, Project 6")));
		employees.add(new Employee("Matthew", "Armstrong", 17500.0, Arrays.asList("Project 7, Project 8")));
		employees.add(new Employee("Joel", "Plavin", 20000.0, Arrays.asList("Project 9, Project 10")));
	}

	public static void main(String[] args) {

		// foreach
		employees.stream().forEach(employee -> System.out.println(employee));
		System.out.println("---------------------------");

		// map
		List<Employee> mappedEmployees = employees.stream().map(employee -> new Employee(employee.getFirstName(),
				employee.getLastName(), employee.getSalary() * 2, employee.getProjects())).collect(Collectors.toList());

		mappedEmployees.stream().forEach(employee -> System.out.println(employee));
		System.out.println("---------------------------");

		// filter
		List<Employee> filteredEmployees = employees.stream().filter(empl -> empl.getSalary() > 15000.0)
				.map(employee -> new Employee(employee.getFirstName(), employee.getLastName(), employee.getSalary(),
						employee.getProjects()))
				.collect(Collectors.toList());

		filteredEmployees.stream().forEach(employee -> System.out.println(employee));
		System.out.println("---------------------------");

		// find First
		Employee findFirstEmployee = employees.stream().filter(empl -> empl.getSalary() > 15000)
				.map(employee -> new Employee(employee.getFirstName(), employee.getLastName(), employee.getSalary(),
						employee.getProjects()))
				.findFirst().orElse(null);
		System.out.println(findFirstEmployee);
		System.out.println("---------------------------");

		// flatMap
		String projects = employees.stream().map(employee -> employee.getProjects())
				.flatMap(strings -> strings.stream()).collect(Collectors.joining(","));

		System.out.println(projects);
		System.out.println("---------------------------");

		// short circuit
		List<Employee> shortcircuitedEmployee = employees.stream().skip(1).limit(1).collect(Collectors.toList());

		shortcircuitedEmployee.stream().forEach(emp -> System.out.println(emp));
		System.out.println("---------------------------");

		// Finite Data
		Stream.generate(Math::random).limit(5).forEach(rec -> System.out.println(rec));
		System.out.println("---------------------------");

		// sorting
		List<Employee> sortedEmployee = employees.stream()
				.sorted((o1, o2) -> o1.getFirstName().compareToIgnoreCase(o2.getFirstName()))
				.collect(Collectors.toList());
		sortedEmployee.stream().forEach(emp -> System.out.println(emp));
		System.out.println("---------------------------");

		// min or max value
		employees.stream().max(Comparator.comparing(Employee::getSalary)).orElseThrow(NoSuchElementException::new);
		System.out.println("---------------------------");

		// reduce
		Double totalSalary = employees.stream().map(employee -> employee.getSalary()).reduce(0.0, Double::sum);
		System.out.println(totalSalary);

		System.out.println("---------------------------");

	}
}