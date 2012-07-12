package web2.ls5.de.components;

import java.util.Date;

import org.apache.tapestry5.annotations.Component;
import org.apache.tapestry5.annotations.Persist;
import org.apache.tapestry5.annotations.Property;
import org.apache.tapestry5.corelib.components.Form;
import org.apache.tapestry5.corelib.components.PasswordField;
import org.apache.tapestry5.ioc.annotations.Inject;

import web2.ls5.de.backend.ApplicationBackend;
import web2.ls5.de.backend.UserManager;

public class RegisterComponent 
{
	@Persist
	private String name;
	@Property
	private String password;
	@Property
	private String repeatPassword;
	@Property
	@Persist
	private Date birthdate;
	@Property
	@Persist
	private String gender;
	
	@Component
	private Form registerForm;
	
	@Inject
	ApplicationBackend backend;
	
	private UserManager authenticator = backend.getUserManager();
	
	@Component(id = "registerPassword")
	private PasswordField registerPasswordField;
	
	/**
	 * Creates a new user.
	 */
	void onValidateFromRegisterForm()
	{
		if (!authenticator.signUp(name, password, repeatPassword, birthdate, gender)) 
		{
			registerForm.recordError(registerPasswordField, "Passwords are not equal.");
	    }
	}
	/**
	 * 
	 */
	Object onSuccess() 
	{
	    return "Home";
	}
	
	public String getMale() 
	{
		return gender;
	}

	public String getFemale() {
		return gender;
	}
	  
	public String getName() 
	{
		return name;
	}

	public void setName(String userName) 
	{
		this.name = userName;
	}
}
