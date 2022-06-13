package test;

import logic.controller.applicationcontroller.LoginController;
import logic.engineeringclasses.bean.UserBean;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class LoginTest {

	private static final String ADMIN=  "admin";

	@Test
	public void testLoginMissing()
	{
		int code = 0;
		UserBean bean = new UserBean();
		LoginController controller = new LoginController();
		try {
			controller.login(bean);
		} catch (Exception e){
			code = 1;
		}
		assertEquals(0,code);
	}

	@Test
	public void testLoginWrongPwd()
	{
		int code = 0;
		UserBean bean = new UserBean();
		LoginController controller = new LoginController();
		try {
			bean.setUsrName(ADMIN);
			bean.setUsrPwd("resident");
			controller.login(bean);
		} catch (Exception e){
			code = 1;
		}
		assertEquals(0,code);
	}

	@Test
	public void testRegisterCorrect() {
		int code = 0;
		UserBean bean = new UserBean();
		LoginController controller = new LoginController();
		try {
			bean.setUsrName(ADMIN);
			bean.setUsrPwd(ADMIN);
			bean.setUsrAddr("Via del Corso 22");
			controller.login(bean);
		} catch (Exception e) {
			code = 1;
		}
		assertEquals(0, code);
	}
}
