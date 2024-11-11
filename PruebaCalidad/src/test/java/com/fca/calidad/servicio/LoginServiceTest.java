package com.fca.calidad.servicio;

///////////////
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import org.junit.jupiter.api.Test;
import static org.hamcrest.MatcherAssert.assertThat; 

import static org.hamcrest.Matchers.is;
import com.fca.calidad.dao.IDAOUser;
import com.fca.calidad.modelo.User;

class LoginServiceTest {
///FFFFFFFFFFFFFF
	LoginService service;
	IDAOUser dao;
	@Test
	void test() {
		//Inicialización
		dao=mock(IDAOUser.class);
		service = new LoginService(dao);
		User usuario = new User("nombre1","email@email.com","123456");
		
		when(dao.findByUserName("nombre1")). thenReturn(usuario);
		//Ejercicio
		boolean result = service.login("nombre1", "123456");
		//Verificación
		assertThat(result, is (true));
	}
	

}
