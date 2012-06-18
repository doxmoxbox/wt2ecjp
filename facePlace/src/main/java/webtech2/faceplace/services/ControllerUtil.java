package webtech2.faceplace.services;

import org.apache.log4j.Logger;

import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.transaction.*;

/**
 *
 * @author merten
 */
public class ControllerUtil {

	private final static Logger log = Logger.getLogger(ControllerUtil.class.getName());
	
	public static UserTransaction beginTransaction() throws
			SystemException, NamingException, javax.transaction.NotSupportedException {
		UserTransaction tx = (UserTransaction) new InitialContext().lookup("java:comp/UserTransaction");
		
		if(tx.getStatus() == Status.STATUS_NO_TRANSACTION ||
				tx.getStatus() == Status.STATUS_ROLLEDBACK) {
			tx.begin();
			return tx;
		}
		else if(tx.getStatus() != Status.STATUS_ACTIVE) {
			throw new IllegalArgumentException("Transaction is neither in state 'STATUS_NO_TRANSACTION' (" + 
					Status.STATUS_NO_TRANSACTION +  ") nor 'STATUS_ACTIVE' (" + 
					Status.STATUS_ACTIVE + "), but: " + tx.getStatus());
		}
		
		return null;
	}

	public static void commitTransaction(UserTransaction tx) throws HeuristicMixedException,
			HeuristicRollbackException, SystemException, javax.transaction.RollbackException {
		if (tx != null && tx.getStatus() == Status.STATUS_ACTIVE) {
			tx.commit();
		}
	}

	public static void rollbackTransaction(UserTransaction tx) throws SystemException {
		if (tx != null && tx.getStatus() == Status.STATUS_ACTIVE) {
			tx.rollback();
		}
	}
}
