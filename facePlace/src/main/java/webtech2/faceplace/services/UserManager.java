package webtech2.faceplace.services;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Date;
import java.util.logging.Logger;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.Query;
import org.apache.tapestry5.hibernate.HibernateSessionManager;
import webtech2.faceplace.entities.Person;
import webtech2.faceplace.persistence.Persistence;

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
  private Person pers;

  /**
   * Creates a new user.
   *
   * @param username The username.
   * @param password The password
   * @param repeatPassword The repeatPassword.
   * @param birthdate The birthdate.
   * @param gender The gender.
   * @return True if everything is ok, false if pw is incorrect.
   */
  public boolean signUp(String name,
          String password,
          String repeatPassword,
          Date birthdate,
          String gender) {
    if (!password.equals(repeatPassword)) {
      return false;
    }

    log.info("person data: " + name + " " + password + " " + repeatPassword + " " + birthdate.toString() + " " + gender);

    String saltedPassword = hashText + password;
    String hashedPassword = generateHash(saltedPassword);
    log.info("1. schritt");
    Person xperson = new Person(name, hashedPassword, birthdate, gender);
    pers = xperson;
    log.info("person name: " + xperson.getName());
    em.persist(xperson);
    if (em.contains(xperson)) {
      log.info("contains xperson");
    } else {
      log.info("doesnt contain xperson");
    }
    log.info("nach persist");
    return true;
  }

  /**
   * Checks whether combination of username and password is correct.
   *
   * @param username The username.
   * @param password The password.
   * @return True if it's correct, false otherwise.
   */
  public boolean isValid(String username, String password) {
    boolean isAuthenticated = false;
    if (em.contains(pers)) {
      log.info("contains pers");
    } else {
      log.info("doesnt contain pers");
    }
    String saltedPassword = hashText + password;
    String hashedPassword = generateHash(saltedPassword);
    Query q = em.createQuery("select password from Person s where s.name = " + username);

    String storedPasswordHash = (String) q.getSingleResult();
    if (storedPasswordHash != null && hashedPassword.equals(storedPasswordHash)) {
      isAuthenticated = true;
    }
    return isAuthenticated;
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
}
