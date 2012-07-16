package web2.ls5.de.boundries;

import web2.ls5.de.biz.entities.atoms.Invitation;
import web2.ls5.de.biz.entities.atoms.Posting;
import web2.ls5.de.biz.entities.atoms.User;

import java.util.Vector;
import javax.inject.Named;

@Named
public interface DbBoundry {
	/*
	 * Dummy implementation
	 * 
	 * Vector<User> users;
	 * Hashtable<User, Vector<Invitation>> invitations;
	 * Hashtable<User, Vector<Posting>> postings;
	 * Hashtable<User, Vector<User>> friends;
	 */
	
	//New User init - create empty tables
	
	public void initUserToUsers(User user);	
	public void initInvitations(User user);
	public void initPostings(User user);
	public void initFriends(User user);
	
	// getter

	public User getUserByUsername(String username);	
	public Vector<Invitation> getUsersInvitations(User user);		
	public Vector<Posting> getPostingsWall(User user);
	public Vector<User> getFriends(User user);	
	
	// adder
	
	public void addUser(User user);
	public void addInvitation(User user, Invitation invitation);
	public void addPosting(User user, Posting posting);
	public void addFriend(User liked, User liker);

	//remover
	
	public void removeInvitation(User user, Invitation invitation);
	public void removeFriend(User hater, User hated);
}