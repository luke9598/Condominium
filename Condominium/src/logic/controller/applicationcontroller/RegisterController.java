package logic.controller.applicationcontroller;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import logic.engineeringclasses.bean.FeeBean;
import logic.engineeringclasses.bean.RegistrationBean;
import logic.engineeringclasses.bean.UserBean;
import logic.engineeringclasses.dao.CondominiumDAO;
import logic.engineeringclasses.dao.RegisterDAO;
import logic.engineeringclasses.exception.InputException;
import logic.model.Registration;
import logic.model.Role;
import logic.model.User;

import java.sql.SQLException;

public class RegisterController{

	private final CondominiumDAO cond = new CondominiumDAO();
	private final RegisterDAO register = new RegisterDAO();
	private final ApartmentController aptController = new ApartmentController();
	private final PatternController pattern = new PatternController();

	public boolean registration(UserBean bean){
		try {
			if(pattern.isName(bean.getUsrName())) {
				throw new InputException("Incorrect Name : "+bean.getUsrName());
			}
			if(pattern.isName(bean.getUsrSurname())) {
				throw new InputException("Incorrect Surname : "+bean.getUsrSurname());
			}
			if(!pattern.isEmail(bean.getUsrEmail())) {
				throw new InputException("Incorrect Email : "+bean.getUsrEmail());
			}
			if(!pattern.isPassword(bean.getUsrPwd())){
				throw new InputException("Incorrect Password");
			}
			if(!bean.getUsrPwd().equals(bean.getOkPassword())) {
				throw new InputException("Password Mismatch");
			}
			if(bean.getUsrRole() == null) {
				throw new InputException("No Role Selected");
			}
			if(bean.getUsrAddr() == null || bean.getUsrAddr().isEmpty()) {
				throw new InputException("No Address Selected");
			}
			return checkRegistration(bean.getUsrEmail(),bean.getUsrAddr());
		}catch(InputException | SQLException e){
			return false;
		}
	}

	private boolean checkRegistration(String email, String address) throws SQLException{
		return register.checkRegistration(email,address);
	}

	public ObservableList<String> loadAddresses() throws SQLException{
		return cond.checkAddressesList();
	}

	public ObservableList<Registration> loadRegistration(String address)throws SQLException{
		return register.loadRegistrationList(address);
	}

	public void addRegistered(RegistrationBean reg, FeeBean fee){
		ApartmentController aptCtrl = new ApartmentController();
		switch (Role.valueOf(reg.getRole())){
			case OWNER:
				try {
					register.addRegistered(reg);
					aptCtrl.addOwner(reg.getApartment(), reg.getAddress());
				}catch (SQLException e){
					e.printStackTrace();
				}
				break;
			case RESIDENT:
				try{
					FeeController feeCtrl = new FeeController();
					register.addRegistered(reg);
					fee.setApt(aptCtrl.loadApartmentId(reg.getApartment(),reg.getAddress()));
					feeCtrl.addFees(fee,"fee");
					aptCtrl.addResident(reg.getApartment(),reg.getAddress());
				}catch(SQLException e) {
					e.printStackTrace();
				}
				break;
			default:
				break;
		}

	}

	public void removeRegistered(int id) {
		try{
			register.deleteRegistered(id);
		}catch(SQLException e){
			e.printStackTrace();
		}
	}

	public void removeAllRegistered(String apartment) {
		try{
			register.deleteAllRegistered(apartment);
		}catch(SQLException e){
			e.printStackTrace();
		}
	}

	public ObservableList<String> loadAddress(UserBean bean) {
		ObservableList<String> list = FXCollections.observableArrayList();
		try {
			switch (Role.valueOf(bean.getUsrRole().toUpperCase())) {
				case OWNER:
					list = aptController.loadApartmentOwner(bean.getUsrAddr());
					break;
				case RESIDENT:
					list = aptController.loadApartmentResident(bean.getUsrAddr());
					break;
				default:
					break;
			}
			if (list.isEmpty()) {
				throw new InputException("No Apartment Available");
			} else {
				return list;
			}
		}catch (InputException|SQLException e){
			 return list;
		}
	}

	public void addRegistrationUser(User user, String role, String aptName) throws SQLException {
		register.addRegistrationUser(user,role,aptName);
	}
}
