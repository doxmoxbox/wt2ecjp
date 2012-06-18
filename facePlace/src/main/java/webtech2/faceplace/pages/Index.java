package webtech2.faceplace.pages;

import java.util.Date;
import java.util.logging.Logger;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import org.apache.tapestry5.SymbolConstants;
import org.apache.tapestry5.annotations.Component;
import org.apache.tapestry5.annotations.Persist;
import org.apache.tapestry5.annotations.Property;
import org.apache.tapestry5.corelib.components.Form;
import org.apache.tapestry5.corelib.components.PasswordField;
import org.apache.tapestry5.ioc.annotations.Inject;
import org.apache.tapestry5.ioc.annotations.Symbol;
import webtech2.faceplace.persistence.Persistence;
import webtech2.faceplace.services.UserManager;

public class Index {

  @Persist
  private String name;
  private String password;
  private String repeatPassword;
  @Persist
  private Date birthdate;
  @Persist
  private String gender;
  @javax.inject.Inject
	@Persistence
	EntityManager em;
  private UserManager authenticator = new UserManager(em);
  @Component(id = "password")
  private PasswordField passwordField;
  @Component
  private Form loginForm;
  @Component
  private Form registerForm;
  @Component(id = "registerPassword")
  private PasswordField registerPasswordField;
  @Property
  @Inject
  @Symbol(SymbolConstants.TAPESTRY_VERSION)
  private String tapestryVersion;
  private static Logger log = Logger.getLogger(Index.class.getName());

  /**
   * Checks user login.
   */
  void onValidateFromLoginForm() {
    if (!authenticator.isValid(name, password)) {
      loginForm.recordError(passwordField, "Invalid user name or password.");
    }
  }
  
  /**
   * Creates a new user.
   */
  void onValidateFromRegisterForm() {
    if(!authenticator.signUp(name, password, repeatPassword, birthdate, gender)) {
      registerForm.recordError(registerPasswordField, "Passwords are not equal.");
    }
  }
      

  /**
   * Validation passed, so we'll go to the "UserHome" page.
   */
  Object onSuccess() {
    return "Index";
  }

  public String getPassword() {
    return password;
  }

  public void setPassword(String password) {
    this.password = password;
  }
  
  public String getRepeatPassword() {
    return repeatPassword;
  }
  
  public void setRepeatPassword(String repeatPassword) {
    this.repeatPassword = repeatPassword;
  }
  
  public Date getBirthdate() {
    return birthdate;
  }
  
  public void setBirthdate(Date date) {
    birthdate = date;
  }

  public String getName() {
    return name;
  }

  public void setName(String userName) {
    this.name = userName;
  }
  
  public String getMale() {
    return gender;
  }
  
  public String getFemale() {
    return gender;
  }
  
  public String getGender() {
    return gender;
  }
  
  public void setGender(String gender) {
    this.gender = gender;    
  }
}
