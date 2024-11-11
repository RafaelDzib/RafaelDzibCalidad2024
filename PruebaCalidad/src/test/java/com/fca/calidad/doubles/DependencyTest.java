package com.fca.calidad.doubles;
///////////////fioajsiofjoiajsofi
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import org.hamcrest.Matcher;
import static org.hamcrest.Matchers.*;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.invocation.InvocationOnMock;
import org.mockito.stubbing.Answer;

class DependencyTest {
	private Dependency dependency;
	private SubDependency sub;
	@BeforeEach
	void setup() {
		sub=mock(SubDependency.class);
		/*dependency= new Dependency(sub);*/
		dependency= mock (Dependency.class);
	}
	@Test
	void addTwoTest() {
		//Inicialización
		when(dependency.addTwo(anyInt())).thenAnswer(new Answer<Integer>() {
			public Integer answer(InvocationOnMock invocation) throws Throwable{
				int arg = (Integer)invocation.getArguments()[0];
				return 20 * 2 + 10 + arg;
			}
		
		});

		int resultadoEsperado=55;
		assertThat(resultadoEsperado, is(dependency.addTwo(5)));
	
	}
	private int anyInt() {
		// TODO Auto-generated method stub
		return 0;
	}
	private void assertThat(int resultadoEsperado, Matcher<Integer> matcher) {
		// TODO Auto-generated method stub
		
	}
	@Test
	void test() {
		System.out.println(sub.getClassName());
	}
	public void testDependency() {
		//Inicialización
		when(sub.getClassName()).thenReturn("hi there");
		String resultadoEsperado="hi there";
		//Ejercicio
		String resultadoReal= sub.getClassName();
		//Verificacion
		assertThat(resultadoReal, is (resultadoEsperado));
		
	}
	private void assertThat(String resultadoReal, Matcher<String> matcher) {
		// TODO Auto-generated method stub
	}
	
}
