package web2.ls5.de.components;

import java.util.logging.Logger;
import org.apache.tapestry5.annotations.*;
import org.apache.tapestry5.corelib.components.Form;
import org.apache.tapestry5.corelib.components.PasswordField;
import org.apache.tapestry5.ioc.annotations.Inject;
import web2.ls5.de.backend.ApplicationBackend;
import web2.ls5.de.backend.UserManager;

public class LoginComponent {

  @Inject
  ApplicationBackend backend;
  @Component
  private Form loginForm;
  private UserManager authenticator = backend.getUserManager();
  @Component(id = "password")
  private PasswordField passwordField;
  @Property
  private String password;
  @Persist
  private String name;
  Logger log = Logger.getLogger(LoginComponent.class.getName());
  @SessionState
  private long id;
  
  /**
   * Checks user login.
   */
  void onValidateFromLoginForm() {
    id = authenticator.isValid(name, password);
    if (id == -1) {
      loginForm.recordError(passwordField, "Invalid user name or password.");
    }
  }
	 
	/**
	 * Validation passed, so we'll go to the "UserHome" page.
	 */
	Object onSuccess() 
	{
		return "Home";
	}	
	
	public String getHello() 
	{
		return "My LoginBox";
	}
	
	 public String getName() 
	 {  
		 return name; 
	 }

  public void setName(String userName) {
    this.name = userName;
  }
}