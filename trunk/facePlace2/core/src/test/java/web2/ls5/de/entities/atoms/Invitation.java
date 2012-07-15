package web2.ls5.de.entities.atoms;

import javax.inject.Named;

import web2.ls5.de.entities.lists.InvitationsList;

@Named
public class Invitation {
	InvitationsList parent;
	User inviter;
	User invitee;
	
	public Invitation() {
		// TODO Auto-generated constructor stub
	}

	public User getInviter() {
		return inviter;
	}

	public void setInviter(User inviter) {
		this.inviter = inviter;
	}

	public User getInvitee() {
		return invitee;
	}

	public void setInvitee(User invitee) {
		this.invitee = invitee;
	}

	public InvitationsList getParent() {
		return parent;
	}

	public void setParent(InvitationsList parent) {
		this.parent = parent;
	}

	public void approve(){
		inviter.getFriendsList().add(invitee);
		invitee.getFriendsList().add(inviter);
		parent.remove(this);
	}
	
	public void reject(){
		parent.remove(this);
	}
}
