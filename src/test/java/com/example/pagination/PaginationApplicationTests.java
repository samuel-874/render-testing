package com.example.pagination;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;


@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class PaginationApplicationTests {

	Calculator unitTest = new Calculator();

	@Test
	void itShouldAddTwoNumbers() {
//		given
		int numberOne = 20;
		int numberTwo = 30;

//		when
		var results = unitTest.add(numberOne,numberTwo);
//		then
		assertThat(results).isEqualTo(50);

	}

	class Calculator{
		int add(int a , int b){
		return a + b;
		};
	}

}
