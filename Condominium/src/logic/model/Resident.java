package logic.model;

import logic.engineeringclasses.exception.InputException;

public class Resident extends User{

	public Resident(String userID, String name, String email, String password,String cc)throws InputException {
		super( userID,  name,  email,  password, cc);
	}
}
