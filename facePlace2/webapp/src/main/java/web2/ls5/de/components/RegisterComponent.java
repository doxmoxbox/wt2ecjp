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
  void onValidateFromRegisterForm() {
    if (!authenticator.signUp(name, password, repeatPassword, birthdate)) {
      registerForm.recordError(registerPasswordField, "Passwords are not equal.");
    }
  }

  /**
   *
   */
  Object onSuccess() {
    return "Index";
  }

  public String getName() {
    return name;
  }

  public void setName(String userName) {
    this.name = userName;
  }
}
