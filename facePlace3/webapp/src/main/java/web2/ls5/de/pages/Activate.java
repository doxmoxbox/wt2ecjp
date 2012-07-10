package web2.ls5.de.pages;

import org.apache.tapestry5.EventContext;
import org.apache.tapestry5.annotations.Property;

/**
 * Created with IntelliJ IDEA.
 * User: merten
 * Date: 04.05.12
 * Time: 12:36
 * To change this template use File | Settings | File Templates.
 */
public class Activate {

	@Property
	private String message;


	// void onActivate(String message) {}

	void onActivate(EventContext ctx) {

		this.message = ctx.get(String.class, 0);
	}

	Object[] onPassivate() {
		return new Object[]{this.message};
	}
}
