package com.fca.calidad.servicio;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import static org.hamcrest.MatcherAssert.assertThat; 
import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.hasItem;
import static org.hamcrest.Matchers.hasProperty;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.invocation.InvocationOnMock;
import org.mockito.stubbing.Answer;

import com.fca.calidad.dao.IDAOUser;
import com.fca.calidad.modelo.User;
/////////sijofijaoisf

class UserServiceTest {

	private User usuario;
	private UserService servicio;
	private IDAOUser dao;
	private HashMap<Integer, User> baseDatos;

	@BeforeEach
		void setup() {
		dao =mock(IDAOUser.class);
		servicio = new UserService(dao);
		baseDatos = new  HashMap<Integer, User>();
	}
	
	//Actualizar :3
		@Test
		void updateTest() {
			//inicializacion
			User usuarioviejo =new User("nombre1", "email","password");
			usuarioviejo.setId(1);
			baseDatos.put(usuarioviejo.getId(), usuarioviejo);
			
			User usuarionuevo = new User ("Nnombre", "emailN", "PasswordN");
			usuarionuevo.setId(1);
			
			when(dao.findById(1)).thenReturn(usuarioviejo);
			when(dao.updateUser(usuarionuevo)).thenReturn(usuarionuevo);
			when(dao.updateUser(any(User.class))).thenAnswer(new Answer<User>(){
				public User answer(InvocationOnMock invocation) throws Throwable{
					User arg =(User)invocation.getArguments()[0];
					return baseDatos.get(arg.getId());
					
				}
			});
		
			
			
			//Ejercicio
			User result = servicio.updateUser(usuarionuevo);
			 
			//verificar
			assertThat("PasswordN",is(result.getPassword()));
			assertThat("Nnombre",is(result.getName()));
			
		}
		
		//Crear usuario
		@Test
		void createUser() {
			//inicializacion
			// Configuración
	        User NuevoUsuario = new User("Rafael", "rafael@correo.com", "12345678");
	        when(dao.findUserByEmail("rafael@example.com")).thenReturn(null);
	        when(dao.save(any(User.class))).thenReturn(1);

	        // Ejercicio
	        User result = servicio.createUser("Rafael", "rafael@correo.com", "12345678");

	        // Verificación
	        assertThat(result.getName(), is("Rafael"));
	        assertThat(result.getEmail(), is("rafael@correo.com"));
	        assertThat(result.getId(), is(1));
		}
		
		//buscar x email
		@Test
		void findUserByEmail() {
			
			//Inicialización
					User resultadoEsperado= new User("name", "email", "password");
					resultadoEsperado.setId(1);
					when(dao.findUserByEmail("email")).thenReturn(resultadoEsperado);
					
					//Ejercicio 
					
					 User result = servicio.findUserByEmail("email");
					 
					 //Verificar
					 assertThat(result.getId(), is(1));
					 assertThat(result.getName(), is("name"));
					 assertThat(result.getEmail(), is("email"));
					 assertThat(result.getPassword(), is("password"));
			
		}
		
		//Buscar por ID
		@Test
		void findUserById() {
					User BuscarID= new User("Rafita", "Rafita@correo.com", "123456");
					BuscarID.setId(1);
					when(dao.findById(1)).thenReturn(BuscarID);
					
					//Ejercicio 
					
					 User result = servicio.findUserById(1);
					 
					 //Verificar
					 assertThat(result.getId(), is(1));
					 assertThat(result.getName(), is("Rafita"));
					 assertThat(result.getEmail(), is("Rafita@correo.com"));
					 assertThat(result.getPassword(), is("123456"));
			
		}
		
		//Buscar todos los usuarios >:v
		@Test
		void findAllUsers() {
			
	        List<User> userList = new ArrayList<>();
	        userList.add(new User("Mari", "Maria@correo.com", "12345678"));
	        userList.add(new User("Didi", "Didier@correo.com", "12345678"));
	        when(dao.findAll()).thenReturn(userList);

	        // Ejercicio
	        List<User> result = servicio.findAllUsers();

	        // Verificación
	        
	        assertThat(result, hasItem(hasProperty("name", is("Mari"))));
	        assertThat(result, hasItem(hasProperty("name", is("Didi"))));
	    }
		
		//Borrar usuario
		@Test
		void deleteUser() {
	       
	        when(dao.deleteById(1)).thenReturn(true);

	        // Ejercicio
	        boolean result = servicio.deleteUser(1);

	        // Verificación
	        assertThat(result, is(true));
	    }
			
}