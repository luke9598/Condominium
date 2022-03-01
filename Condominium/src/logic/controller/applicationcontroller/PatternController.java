package logic.controller.applicationcontroller;

import javafx.scene.control.TextField;
import javafx.scene.control.TextFormatter;
import javafx.util.StringConverter;

import java.util.function.UnaryOperator;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class PatternController {

	public static  final Pattern VALID_TEXT_REGEX =
			Pattern.compile("^[A-z\\s]", Pattern.CASE_INSENSITIVE);

	public static final Pattern VALID_NAME_REGEX = 
		    Pattern.compile("^[A-Z][a-z\\s]{2,15}$", Pattern.CASE_INSENSITIVE);
	
	public static final Pattern VALID_EMAIL_ADDRESS_REGEX = 
		    Pattern.compile("^[A-Z0-9._%+-]+@[A-Z0-9.-]+\\.[A-Z]{2,6}$", Pattern.CASE_INSENSITIVE);
	
	public static final Pattern VALID_PASSWORD_REGEX = 
		    Pattern.compile("^(?=.*[A-z])(?=.*\\d)[A-z\\d]{4,15}$", Pattern.CASE_INSENSITIVE);

	public  boolean isText(String text){
		Matcher matcher = VALID_TEXT_REGEX.matcher(text);
		return !matcher.find();
	}


	public boolean isName(String name) {
		Matcher matcher = VALID_NAME_REGEX.matcher(name);
	    return !matcher.find();
	}
	    
	public  boolean isEmail(String emailStr) {
		Matcher matcher = VALID_EMAIL_ADDRESS_REGEX.matcher(emailStr);
	    return matcher.find();
	}
	
	public  boolean isPassword(String password) {
		Matcher matcher = VALID_PASSWORD_REGEX.matcher(password);
	    return matcher.find();
	}

	public void textFilter(TextField tf){
		Pattern validEditingState = Pattern.compile("-?(([1-9][0-9]*)|0)?(\\.[0-9]*)?");
		UnaryOperator<TextFormatter.Change> filter = c -> {
			String text = c.getControlNewText();
			if (validEditingState.matcher(text).matches()) {
				return c ;
			} else {
				return null ;
			}
		};
		StringConverter<Double> converter = new StringConverter<Double>() {
			@Override
			public Double fromString(String s) {
				if (s.isEmpty() || "-".equals(s) || ".".equals(s) || "-.".equals(s)) {
					return 0.0 ;
				} else {
					return Double.valueOf(s);
				}
			}
			@Override
			public String toString(Double d) {
				return d.toString();
			}
		};
		TextFormatter<Double> textFormatter = new TextFormatter<>(converter, 0.0, filter);
		tf.setTextFormatter(textFormatter);
	}

}
