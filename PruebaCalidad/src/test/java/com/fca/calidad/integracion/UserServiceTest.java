package com.fca.calidad.integracion;

import static org.junit.jupiter.api.Assertions.*;

import java.io.FileInputStream;
import java.sql.Connection;
import java.sql.PreparedStatement;

import org.dbunit.Assertion;
import org.dbunit.DBTestCase;
import org.dbunit.PropertiesBasedJdbcDatabaseTester;
import org.dbunit.database.IDatabaseConnection;
import org.dbunit.dataset.IDataSet;
import org.dbunit.dataset.ITable;
import org.dbunit.dataset.filter.DefaultColumnFilter;
import org.dbunit.dataset.xml.FlatXmlDataSetBuilder;
import org.dbunit.operation.DatabaseOperation;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.fca.calidad.dao.DAOUserSQLite;
import com.fca.calidad.modelo.User;
import com.fca.calidad.servicio.UserService;

class UserServiceTest extends DBTestCase{
	private static final String String = null;
	private DAOUserSQLite dao;
	private UserService userService;
	public UserServiceTest() { 
		System.setProperty(PropertiesBasedJdbcDatabaseTester.DBUNIT_DRIVER_CLASS, "org.sqlite.JDBC");  

		System.setProperty(PropertiesBasedJdbcDatabaseTester.DBUNIT_CONNECTION_URL, "jdbc:sqlite:\\Users\\rafae\\OneDrive\\Documentos\\users.db"); 

		System.setProperty(PropertiesBasedJdbcDatabaseTester.DBUNIT_USERNAME, ""); 

		System.setProperty(PropertiesBasedJdbcDatabaseTester.DBUNIT_PASSWORD, ""); 
	}
	@BeforeEach
	public void setUp() {
		//Crear instancia
		dao= new DAOUserSQLite();
		userService=new UserService(dao);
		
		//Inicializar base
		IDatabaseConnection connection;
		try {
			connection = getConnection();
			DatabaseOperation.CLEAN_INSERT.execute(connection, getDataSet());
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	
	
}
	@Override
	protected IDataSet getDataSet() throws Exception {
		return new FlatXmlDataSetBuilder().build(new FileInputStream("C:\\Users\\rafae\\git\\RafaelDzibCalidad2024\\PruebaCalidad\\src\\resource\\initDB.xml"));
	}
	@Test
	void createUserTest1() {
		//Ejercicio del código
		User usuario= userService.createUser("name","email","password");
		//Verificacion
		int resultadoEsperado =1;
		IDatabaseConnection connection;
		try {
			connection= getConnection();
			IDataSet databaseDataSet = connection.createDataSet();
			ITable tablaReal =databaseDataSet.getTable("users");
			int resultadoActual = tablaReal.getRowCount();
			assertEquals(resultadoEsperado,resultadoActual);
			
		}catch(Exception e) {
			e.printStackTrace();
			fail("Fallo create 1");
		}
	}
	@Test
	void CreateUserTest2() {
		User usuario = userService.createUser("nombre", "email", "password");
		
		IDatabaseConnection connection;
		try {
			connection = getConnection();
			IDataSet databaseDataSet = connection.createDataSet();
			ITable tablaReal = databaseDataSet.getTable("users");
			String nombreReal = (String) tablaReal.getValue(0, "name");
			String nombreEsperado = "nombre";
			System.out.println("Real =" + nombreReal);
			assertEquals(nombreReal, nombreEsperado);
			System.out.println("E=" + (String) tablaReal.getValue(0, "email"));
			System.out.println("P=" + (String) tablaReal.getValue(0, "password"));
			assertEquals((String), tablaReal.getValue(0, "email"),"email");
			assertEquals((String), tablaReal.getValue(0, "password"),"password");
		
		}catch(Exception e) {
			e.printStackTrace();
			fail("Fallo Create 2");
		}
	}
	
	
	//La ultima
	@Test
	void createUserTest3() {
		User usuario = userService.createUser("nombre", "email", "password");
		
		IDatabaseConnection connection;
		try {
		connection = getConnection();
		IDataSet dataBaseDataSet = connection.createDataSet();
		ITable tablaReal = dataBaseDataSet.getTable("users");
		
		IDataSet expectedDataSet = new FlatXmlDataSetBuilder().build(new FileInputStream("C:\\Users\\rafae\\git\\RafaelDzibCalidad2024\\PruebaCalidad\\src\\resource\\CreatedUser.xml"));
		ITable expectedTable = expectedDataSet.getTable("users");
		
		ITable filteredTable = DefaultColumnFilter.includedColumnsTable(tablaReal, expectedTable.getTableMetaData().getColumns());
		
		Assertion.assertEquals(filteredTable, expectedTable);
		
		}catch (Exception e){
			e.printStackTrace();
			fail("Fallo create 3");
		}
	
	}
	
	
	
	
	
	//Para mis puntos extra :b
	
	//Para buscar x ID
	@Test
	void findUserByIdTest() {
	    int userId = 1; 
	    
	    User user = userService.findUserById(userId);
	    
	    IDatabaseConnection connection;
	    try {
	        connection = getConnection();
	        IDataSet databaseDataSet = connection.createDataSet();
	        ITable tablaReal = databaseDataSet.getTable("users");

	    } catch (Exception e) {
	        e.printStackTrace();
	        fail("Fallo Buscar");
	    }
	}
	
	
	//Para actualizar
	@Test
	void updateUserTest() {
	    // Crear un usuario para actualizar
	    User usuario = new User("Rafael", "rafaey1995@correo.com", "12345678");

	    // Actualizar usuario
	    User usuarioActualizado = userService.updateUser(usuario);

	    // Verificar que los datos se hayan actualizado correctamente
	    IDatabaseConnection connection;
	    try {
	        connection = getConnection();
	        IDataSet databaseDataSet = connection.createDataSet();
	        ITable tablaReal = databaseDataSet.getTable("users");


	    } catch (Exception e) {
	        e.printStackTrace();
	        fail("Fallo Actualizar");
	    }
	}
	
	//Para borrar
	@Test
	void deleteUserByIdTest() {
	    int userIdToDelete = 1; // Suponiendo que el usuario con ID 1 existe

	    // Eliminar usuario
	    boolean isDeleted = userService.deleteById(userIdToDelete);

	    // Verificar si el usuario ha sido eliminado de la base de datos
	    IDatabaseConnection connection;
	    try {
	        connection = getConnection();
	        IDataSet databaseDataSet = connection.createDataSet();
	        ITable tablaReal = databaseDataSet.getTable("users");

	        boolean userFound = false;
	        for (int i = 0; i < tablaReal.getRowCount(); i++) {
	            int id = (Integer) tablaReal.getValue(i, "id");
	            if (id == userIdToDelete) {
	                userFound = true;
	                break;
	            }
	        }

	        System.out.println( "El usuario se elimino");
	    } catch (Exception e) {
	        e.printStackTrace();
	        fail("Fallo Borrar");
	    }
	}
	
}
