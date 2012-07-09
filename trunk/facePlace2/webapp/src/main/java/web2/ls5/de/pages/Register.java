package web2.ls5.de.pages;

import java.util.Date;
import web2.ls5.de.backend.ApplicationBackend;
import web2.ls5.de.backend.UserManager;
import org.apache.tapestry5.corelib.components.Form;
import org.apache.tapestry5.corelib.components.PasswordField;
import org.apache.tapestry5.ioc.annotations.Inject;
import org.apache.tapestry5.annotations.Component;
import org.apache.tapestry5.annotations.Persist;
import org.apache.tapestry5.annotations.Property;

public class Register
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
	
	@Inject
	ApplicationBackend backend;
	
	@Component
	private Form registerForm;
	
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
	    return "Index";
	}
	
	public String getMale() {
	    return gender;
	  }

	  public String getFemale() {
	    return gender;
	  }
	  
	  public String getName() {
		    return name;
		  }

		  public void setName(String userName) {
		    this.name = userName;
		  }
}
