package web2.ls5.de.components;

import org.apache.tapestry5.SymbolConstants;
import org.apache.tapestry5.annotations.Component;
import org.apache.tapestry5.annotations.Persist;
import org.apache.tapestry5.annotations.Property;
import org.apache.tapestry5.corelib.components.Form;
import org.apache.tapestry5.corelib.components.PasswordField;
import org.apache.tapestry5.ioc.annotations.Inject;
import org.apache.tapestry5.ioc.annotations.Symbol;
import javax.persistence.EntityManager;
import web2.ls5.de.backend.ApplicationBackend;
import web2.ls5.de.backend.UserManager;

public class LoginComponent
{
	@Inject
	ApplicationBackend backend;
	
	@Component
	private Form loginForm;
	
	//@javax.inject.Inject
	//@Persistence
	//EntityManager em;
	
	private UserManager authenticator = backend.getUserManager();

	@Component(id = "password")
	private PasswordField passwordField;

	@Property
	private String password;
	
	@Persist
	private String name;
	
	/**
	 * Checks user login.
	 */
	void onValidateFromLoginForm()
	{
		if (!authenticator.isValid(name, password)) 
		{
			loginForm.recordError(passwordField, "Invalid user name or password.");
		}
	}
	 
	/**
	 * Creates a new user.
	 */
	/*void onValidateFromRegisterForm() 
	{
		if (!authenticator.signUp(name, password, repeatPassword, birthdate, gender))
		{
			registerForm.recordError(registerPasswordField, "Passwords are not equal.");
		}
	}*/
	 
	/**
	 * Validation passed, so we'll go to the "UserHome" page.
	 */
	Object onSuccess() 
	{
		return "About";
	}
	
	
	public String getHello() 
	{
		return "My LoginBox";
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