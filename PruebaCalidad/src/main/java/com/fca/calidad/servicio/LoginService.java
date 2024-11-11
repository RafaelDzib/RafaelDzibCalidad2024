package com.fca.calidad.servicio;
///sfioaoifas
import com.fca.calidad.dao.IDAOUser;
import com.fca.calidad.modelo.User;

public class LoginService {
	IDAOUser dao;

	public LoginService(IDAOUser d) {
		dao = d;
	}
	
	public boolean login(String name, String pass) {
		
		User u = dao.findByUserName(name);
		boolean result =false;
		if (u != null) {
			if (u.getPassword() == pass) {
				
				return true;
			}
			else {
				
				return false;
			}
		}
		else {
			return false;
		}
	}

}
