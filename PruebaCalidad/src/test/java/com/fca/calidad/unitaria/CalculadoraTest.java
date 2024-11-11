package com.fca.calidad.unitaria;
import static org.hamcrest.MatcherAssert.assertThat; 
import static org.hamcrest.Matchers.*;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;

//import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;
////////pojpojop
class CalculadoraTest {
	
	private double num1=0;
	private double num2=0;
	private Calculadora calculadora=null;
	

	@BeforeEach
	
	void setup() {
		num1=2;
		num2=5;
		calculadora = new Calculadora();
	}
	@Test
void suma2numerosPositivosTest() {
	/*double num1=2;
	double num2=5;*/
	double resEsperado=7;
	
	Calculadora calculadora=new Calculadora();
	//Ejercicio llamar al metodo que queremos probar 
	
	double resEjecucion=calculadora.suma(num1, num2);
	
	//Verificar
	
	assertThat(resEsperado, is (resEjecucion));
	}
	@AfterEach
	void ptintln() {
		System.out.println("Esto se imprime desp de cada prueba");
	}
}
	/*void restar2numerosPositivosTest(){
		double num1=8;y
		double num2=3;
		double resEsperado=5;
		Calculadora calculadora=new Calculadora();
		double resEjecucion=calculadora.resta(num1, num2);
		
		assertThat(resEsperado, is (resEjecucion));
	}*/
	/*void multiplica2numerosPostivosTest() {
		double num1=8;
		double num2=2;
		double resEsperado=16;
		Calculadora calculadora=new Calculadora();
		double resEjecucion=calculadora.multiplica(num1, num2);
		
		assertThat(resEsperado, is (resEjecucion));
	}*/
	/*void divide2numerosPostivosTest() {
		double num1=8;
		double num2=2;
		double resEsperado=4;
		Calculadora calculadora=new Calculadora();
		double resEjecucion=calculadora.divide(num1, num2);
		
		assertThat(resEsperado, is (resEjecucion));
	}*/


