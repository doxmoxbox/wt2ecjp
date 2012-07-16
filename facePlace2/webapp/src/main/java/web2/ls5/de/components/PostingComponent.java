package web2.ls5.de.components;

import org.apache.tapestry5.annotations.*;
import org.apache.tapestry5.corelib.components.Form;
import org.apache.tapestry5.corelib.components.TextArea;
import org.apache.tapestry5.ioc.annotations.Inject;

import web2.ls5.de.backend.ApplicationBackend;

/**
 * 
 * 
 *
 */
public class PostingComponent 
{
	@Inject 
	private ApplicationBackend backend;
	
	@Property
	private Form postForm;
	
	@Property
	private TextArea textArea;
	
	@Property
	private String txt;
	
	void onValidateFromPostForm()
	{
	    //backend.createNewPost();
	}
}
