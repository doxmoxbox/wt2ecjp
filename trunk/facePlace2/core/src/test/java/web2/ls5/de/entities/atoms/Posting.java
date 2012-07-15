package web2.ls5.de.entities.atoms;

import javax.inject.Named;

@Named
public class Posting {

	String text;
	
	public Posting() {
		// TODO Auto-generated constructor stub
	}

	public String getText() {
		return text;
	}

	public void setText(String text) {
		this.text = text;
	}

}
