package test;


import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import logic.controller.applicationcontroller.EmailController;
import logic.controller.applicationcontroller.ViewController;
import logic.model.User;
import org.junit.Test;

//
//import logic.model.*;
//import logic.controller.HomeController;
import logic.engineeringclasses.dao.*;

import java.text.DecimalFormat;

public class AllTests {
//	@Test
//	public void TestCondominiumDAO() throws Exception{
//		CondominiumDAO dao = new CondominiumDAO();	
//		System.out.println(dao.retreiveByCondominumList(12345));
//	}
//	
	@Test
	public void generalTest() throws Exception{
//		MeetDAO meet = new MeetDAO();
//		System.out.println(meet.loadMeetList("Via del Corso 22"));
//		FeeDAO dao = new FeeDAO();
//		System.out.println(dao.checkPastId("1"));
//		EmailController email = new EmailController();
//		//email.checkEmail("Pippo@prova.com");
//		String[] recipient = new String[]{"Paperino@jchbchzbcxh.com"};
//		System.out.println(email.send(recipient,recipient,"",""));
//		TextField tf = new TextField("43.43");
//		Double d = Double.parseDouble(tf.getText());
//		System.out.println(new DecimalFormat("####.##").format(d));
//		TextField tf = new TextField();
//		tf.setText("34567.345678");
//		double d = Double.parseDouble("234567.3456");
//		DecimalFormat df = new DecimalFormat("#.##");
//		String formatted = df.format(d);
//		System.out.println(formatted); //prints 2.4
		UserDAO dao= new UserDAO();
		System.out.println(dao.checkRole("0"));
	}

//	
//	@Test
//	public void TestLoginRole() throws Exception{
//		SqlDAO log = new SqlDAO();	
//		System.out.println(log.checkRole(1));
//	}
//	
//
//	@Test
//	public void TestImagePostDAO()throws  Exception{
//		SqlDAO log = new SqlDAO();		
//		List<String> list = new ArrayList<>();
//		list = log.checkListPost("67890");
//		//(list.get(0));
//		//System.out.println(list);
//		for (int i=0;i<=list.size();i++) {
//			System.out.println(log.checkPost(list.get(i)).getUser());
//		}
//	}
//	
//	@Test
//	public void TestImagePostController()throws  Exception{
//		PostDAO post = new PostDAO();	
//		post.checkImagePost(1, 12345);
//	}
//
//	@Test
//	public void TestloadUserId()throws  Exception{
//		SqlDAO log = new SqlDAO();		
//		log.loadAdminbyID("1");
//	}
//	@Test
//	public void TestLoadListPost() throws  Exception{
//		SqlDAO log = new SqlDAO();		
//		System.out.println( log.checkListPost("67890"));
//	}
//	@Test
//	public void TestLoadLastid() throws  Exception{
//		SqlDAO log = new SqlDAO();		
//		System.out.println( log.loadLatestId("registration","reg_id"));
//		System.out.println( log.loadLatestId("users","user_id"));
//	}	
//	@Test
//	public void TestRoleByName() throws  Exception{
//		SqlDAO log = new SqlDAO();		
//		System.out.println( log.checkRoleByName("Resident"));
//	
//	}
//	@Test
//	public void TestPattern() throws  Exception{
//		PatternController controller = new PatternController();
//		System.out.println(controller.isPassword("sasca sasa"));
//	}
//	@Test
//	public void TestPattern() throws  Exception{
//		SqlDAO controller = new SqlDAO();
//		System.out.println(controller.checkRegistration(" ","6789"));
//	}
}
