package web2.ls5.de.biz.entities.lists;

import java.util.Vector;
import javax.inject.Named;

import web2.ls5.de.biz.entities.atoms.Invitation;


@Named
public class InvitationsList {

	Vector<Invitation> invitations;
	
	public InvitationsList() {
		// TODO Auto-generated constructor stub
	}

	public Vector<Invitation> getInvitations() {
		return invitations;
	}

	public void setInvitations(Vector<Invitation> invitations) {
		this.invitations = invitations;
	}	
	
	public void add(Invitation i){
		invitations.add(i);
	}
	
	public void remove(Invitation i){
		for(Invitation x: invitations){
			if(i.equals((Invitation) x)){
				invitations.remove((Invitation) i);
			}
		}
	}
	
	public Invitation getX_thInvitation(int ordinal){
		return invitations.get(ordinal);
	}
}
