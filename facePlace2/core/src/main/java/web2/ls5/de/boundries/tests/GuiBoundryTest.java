package web2.ls5.de.boundries.tests;

import java.util.Vector;
import javax.inject.Named;

import web2.ls5.de.biz.entities.atoms.Invitation;
import web2.ls5.de.biz.entities.atoms.Posting;
import web2.ls5.de.biz.entities.atoms.UserPerson;
import web2.ls5.de.boundries.impls.GuiBoundryImpl;

@Named
public class GuiBoundryTest {

	GuiBoundryImpl gbi = new GuiBoundryImpl();
	
	public GuiBoundryTest() {
		// TODO Auto-generated constructor stub
	}	
	public String testAll(){
		String preamble = "----- GuiBoundryImpl - testAll -----\n";
		return preamble + register() + "\n";
	}

	// User status
	
	public String register(){
		String preamble = "----- testAll - register -----\n";
//		User 1
		String pre1 = "1. user\n";
		
		String lab1 = "this.register(\"a\", \"b\",\"c\")\n";
		UserPerson u1     = gbi.register("a", "b", "c");
		
		String ua1;
		if(u1 == null)
			ua1 = "null";
		else {	
			String un1  = u1.getUsername().toString();
			String pw1  = u1.getPasswort().toString();
			String n1   = u1.getName().toString();
			
			ua1 = un1 + "\n" + pw1 + "\n" + n1 + "\n";
		}	
//		User 2
		String pre2 = "2. user - no username conflict\n";
		
		String lab2 = "this.register(\"d\", \"e\",\"f\")\n";
		UserPerson u2     = gbi.register("d", "e", "f");
		
		String ua2;
		if(u2 == null)
			ua2 = "null";			
		else {	
			String un2  = u2.getUsername().toString();
			String pw2  = u2.getPasswort().toString();
			String n2   = u2.getName().toString();

			ua2 = un2 + "\n" + pw2 + "\n" + n2 + "\n";
		}
//		User 3
		String pre3 = "3. user - username conflict with 1. user";
		
		String lab3 = "this.register(\"a\", \"g\",\"h\")\n";
		UserPerson u3     = gbi.register("a", "g", "h");
		
		String ua3;
		if(u3 == null)
			ua3 = "null";			
		else {	
			String un3  = u3.getUsername().toString();
			String pw3  = u3.getPasswort().toString();
			String n3   = u3.getName().toString();
	
			ua3 = un3 + "\n" + pw3 + "\n" + n3 + "\n";
		}
	
		return preamble + pre1 + lab1 + u1 + ua1 + pre2 + lab2 + u2 + ua2 + pre3 + lab3 + u3 + ua3;
			
	}
	
	public void login(){}/*
		String preamble = "----- testAll - login -----\n";
		
		String pre1 = "1. user";
		
		String lab1 = "this.login(\"a\", \"b\")";
		User u1     = gbi.login("a", "b");
		String un1  = u1.getUsername().toString();
		String pw1  = u1.getPasswort().toString();
		String n1   = u1.getName().toString();
		
		String pre2 = "2. user - no username conflict";
		
		String lab2 = "this.register(\"d\", \"e\",\"f\")";
		User u2     = gbi.register("d", "e", "f");
		String un2  = u2.getUsername().toString();
		String pw2  = u2.getPasswort().toString();
		String n2   = u2.getName().toString();
		
		String pre3 = "3. user - username conflict with 1. user";
		
		String lab3 = "this.register(\"a\", \"g\",\"h\")";
		User u3     = gbi.register("a", "g", "h");
		String un3  = u3.getUsername().toString();
		String pw3  = u3.getPasswort().toString();
		String n3   = u3.getName().toString();
	
		return preamble + "\n" 
		+ pre1 + "\n" 
		+ lab1 + "\n" 
		+ u1 + "\n" 
		+ un1 + "\n"
		+ pw1 + "\n"
		+ n1 + "\n"
		+ "" + "\n"
		+ pre2 + "\n" 
		+ lab2 + "\n" 
		+ u2 + "\n" 
		+ un2 + "\n"
		+ pw2 + "\n"
		+ n2 + "\n"
		+ "" + "\n"
		+ pre3 + "\n" 
		+ lab3 + "\n" 
		+ u3 + "\n" 
		+ un3 + "\n"
		+ pw3 + "\n"
		+ n3 + "\n"
		+ "" + "\n";

		gbi.login(username, passwort);
		return preamble + "\n";
	}
	
	//Search - invie - ignore or accept(= startFriendship) - endFriendship
	
	public Vector<User> searchNames(String prefix);	
	
	public void invite(User inviter, User invitee);	
	public Vector<Invitation> chooseInivitation(User invitee);	
	public void acceptInvitation(Invitation invitation);
	public void ignoreInvitation(Invitation invitation);
	
	public void endFriendship(User shooter, User shooted);
	
	// Posts
	
	public void post(User poster, String text);
	public Vector<Posting> getPostsWall(User user);
*/
}
