package web2.ls5.de.entities.atoms;

import javax.inject.Inject;
import javax.inject.Named;
import java.util.Vector;

import web2.ls5.de.boundries.DbBoundry;
import web2.ls5.de.boundries.dummys.DbBoundryDummy;
import web2.ls5.de.entities.lists.FriendsList;
import web2.ls5.de.entities.lists.InvitationsList;
import web2.ls5.de.entities.lists.PostingList;

@Named
public class User implements Comparable<User>{
	
	DbBoundry dbb = new DbBoundryDummy();
	
	private String username;
	private String passwort;
	private String name;
	private Session session;

	@Inject
	FriendsList friendsList;	
	@Inject
	PostingList postingsList;
	@Inject
	InvitationsList invitationsList;
	
	
	
	public User() {
		// TODO Auto-generated constructor stub
		this("dummyUsername", "DummyPasswort", "DummyName");		
	}
	
	public User(String username, String passwort, String name){
		this.username = username;
		this.passwort = passwort;
		this.name = name;
		this.session = null;
		
		dbb.initUserToUsers(this);
		dbb.initInvitations(this);
		dbb.initPostings(this);
		dbb.initFriends(this);
	}
	
	public int compareTo(User u) {
		// TODO Auto-generated method stub
		if(this.getName().compareTo(u.getName()) < 0)
			return -1;
		if(this.getName().compareTo(u.getName()) > 0)
			return 1;
		return 0;
	}
	
	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPasswort() {
		return passwort;
	}

	public void setPasswort(String passwort) {
		this.passwort = passwort;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
	public Session getSession() {
		return session;
	}

	public void setSession(Session session) {
		this.session = session;
	}

	public FriendsList getFriendsList() {
		return friendsList;
	}

	public void setFriends(FriendsList friendsList) {
		this.friendsList = friendsList;
	}

	public PostingList getPostingsList() {
		return postingsList;
	}

	public void setPostingsList(PostingList postiongsList) {
		this.postingsList = postiongsList;
	}

	public InvitationsList getInvitationsList() {
		return invitationsList;
	}

	public void setInvitationsList(InvitationsList invitationsList) {
		this.invitationsList = invitationsList;
	}
	
	public void invite(User invitee){
		Invitation inv = new Invitation();
		inv.setInviter(this);
		inv.setInvitee(invitee);
		invitee.getInvitationsList().add(inv);
	}
	
	public Vector<Invitation> chooseInvitation(User user){
		return this.getInvitationsList().getInvitations();
	}
	
	public void startFriendship(Invitation invitation){
		invitation.approve();
	}
	
	public void endFrindship(User notFriend){
		friendsList.remove(notFriend);
		notFriend.getFriendsList().remove(this);
	}
	
	public void postOnAllWalls(String text){
		Posting posting = new Posting();
		posting.setText(text);
		postOnMyWall(posting);
		for(User friend : friendsList.getFriends()){
			friend.postOnMyWall(posting);
		}
	}
	
	public void postOnMyWall(Posting posting){
		postingsList.add(posting);
	}

}
