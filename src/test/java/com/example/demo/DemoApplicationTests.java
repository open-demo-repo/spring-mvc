package com.example.demo;

import static org.assertj.core.api.Assertions.assertThat;
import static org.awaitility.Awaitility.await;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.example.demo.domain.Employee;
import com.example.demo.repo.EmployeeRepository;

@SpringBootTest
class DemoApplicationTests {
	private static final Logger logger = LoggerFactory.getLogger(DemoApplicationTests.class);

	@Autowired
	private EmployeeRepository repository;


	@Test
	public void jpaListenerTest() {
		List<Employee> employees = new ArrayList<>();
		employees.add(new Employee(1L, "Zell", "Dincht"));
		employees.add(new Employee(2L, "Quistis", "Trepe"));
		employees.add(new Employee(3L, "Irvine", "Kinneas"));
		employees.add(new Employee(4L, "Rinoa", "Heartilly"));
		employees.add(new Employee(5L, "Squall", "Leonhart"));
		System.out.println();
		System.out.println();
		System.out.println();
		System.out.println();
		System.out.println();
		// DB empty at the beginning.
		assertThat(repository.findAll()).isEmpty();
//		logger.info("Employees found with findAll():");
//		logger.info("-------------------------------");
//		repository.findAll().forEach(employee -> logger.info(employee.toString()));
//		logger.info("-------------------------------");
//		logger.info("");

		// Insert some employees into the Geode Region
//		employees.forEach(employee -> employeesRegion.put(employee.getId(), employee));

		// Wait for queues to drain (AsyncEventListener invoked).
		logger.info("Waiting for AsyncEventQueue to drain...");
//		await().untilAsserted(() -> assertThat(asyncEventQueue.size()).isEqualTo(0));
		logger.info("Waiting for AsyncEventQueue to drain... Done!.");
		logger.info("");

		// DB should now have all the employees inserted through the AsyncEventListener.
		Iterable<Employee> employeeList = repository.findAll();
		assertThat(employeeList).hasSize(employees.size());
		employeeList.forEach(employee -> assertThat(employees.contains(employee)));
		logger.info("Employees found with findAll():");
		logger.info("-------------------------------");
		employeeList.forEach(employee -> logger.info(employee.toString()));
		logger.info("-------------------------------");
		logger.info("");
	}
}