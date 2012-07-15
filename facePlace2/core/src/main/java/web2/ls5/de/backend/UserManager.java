package web2.ls5.de.backend;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Date;
import java.util.HashSet;
import java.util.logging.Logger;
import javax.persistence.EntityManager;
import javax.persistence.Query;
import web2.ls5.de.entities.DBPerson;


/**
 * UserManager
 * Copyright: TU Dortmund - Webtech2 - SS 2012
 * @created 07.06.2012 - 11:02:56
 */
public class UserManager {

  /**
   * EntityManager.
   */
  EntityManager em;
  /**
   * String for hashing.
   */
  private final String hashText = "reuw436473g732g432674rt32";
  /**
   * This' class Logger.
   */
  private static final Logger log = Logger.getLogger(UserManager.class.getName());

  /**
   * Creates a new UserManager.
   */
  public UserManager(EntityManager em) {
    this.em = em;
  }

  /**
   * Creates a new user.
   *
   * @param username The username.
   * @param password The password
   * @param repeatPassword The repeatPassword.
   * @param birthdate The birthdate.
   * @return True if everything is ok, false if pw is incorrect.
   */
  public boolean signUp(String name,
          String password,
          String repeatPassword,
          Date birthdate) {
    if (!password.equals(repeatPassword)) {
      return false;
    }

    String saltedPassword = hashText + password;
    String hashedPassword = generateHash(saltedPassword);
    DBPerson xperson = new DBPerson(name, hashedPassword, birthdate);
    em.persist(xperson);
    return true;
  }

  /**
   * Checks whether combination of username and password is correct.
   *
   * @param username The username.
   * @param password The password.
   * @return True if it's correct, false otherwise.
   */
  public DBPerson isValid(String username, String password) {
    String saltedPassword = hashText + password;
    String hashedPassword = generateHash(saltedPassword);
    String storedPasswordHash = "";
    Query q = em.createQuery("SELECT p FROM DBPerson p", DBPerson.class);
    DBPerson pers = null;
    for (DBPerson p : new HashSet<DBPerson>(q.getResultList())) {
      if(p.getName().equals(username)) {
        storedPasswordHash = p.getPassword();
        pers = p;
      }
    }

    if (storedPasswordHash == null || !hashedPassword.equals(storedPasswordHash) || pers == null) {
      pers = null;
    }
    return pers;
  }

  /**
   * SHA-1 algorithm for ciphering passwords.
   *
   * @param input hastText + password.
   * @return Hashed password.
   */
  private String generateHash(String input) {
    StringBuilder hash = new StringBuilder();

    try {
      MessageDigest sha = MessageDigest.getInstance("SHA-1");
      byte[] hashedBytes = sha.digest(input.getBytes());
      char[] digits = {'0', '1', '2', '3', '4', '5', '6', '7', '8', '9',
        'a', 'b', 'c', 'd', 'e', 'f'};
      for (int idx = 0; idx < hashedBytes.length; ++idx) {
        byte b = hashedBytes[idx];
        hash.append(digits[(b & 0xf0) >> 4]);
        hash.append(digits[b & 0x0f]);
      }
    } catch (NoSuchAlgorithmException e) {
      // dunno when this happens
    }

    return hash.toString();
  }

  public String getHashedPassword(String password) {
    return generateHash(hashText + password);
  }
}
