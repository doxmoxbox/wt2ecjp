package web2.ls5.de.boundries;

import java.util.List;
import web2.ls5.de.biz.entities.atoms.Invitation;
import web2.ls5.de.biz.entities.atoms.Posting;
import web2.ls5.de.biz.entities.atoms.UserPerson;

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
	
	//New User init
	/*
	public void initUserToUsers(UserPerson user);	
	public void initInvitations(UserPerson user);
	public void initPostings(UserPerson user);
	public void initFriends(UserPerson user);*/
	
	// getter

	public UserPerson getUserByUsername(String username);	
	public List<Invitation> getUsersInvitations(UserPerson user);		
	public List<Posting> getPostingsWall(UserPerson user);
	public List<UserPerson> getFriends(UserPerson user);	
	
	// adder
	
	public void addInvitation(UserPerson user, Invitation invitation);
	public void addPosting(UserPerson user, Posting posting);
	public void addFriend(UserPerson liked, UserPerson liker);

	//remover
	
	public void removeInvitation(UserPerson user, Invitation invitation);
	public void removeFriend(UserPerson hater, UserPerson hated);
}