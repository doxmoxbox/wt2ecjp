package web2.ls5.de.boundries.dummys;

import java.util.Hashtable;
import java.util.Vector;

import javax.inject.Named;



import web2.ls5.de.biz.entities.atoms.Invitation;
import web2.ls5.de.biz.entities.atoms.Posting;
import web2.ls5.de.biz.entities.atoms.UserPerson;
import web2.ls5.de.boundries.DbBoundry;

@Named
public class DbBoundryDummy implements DbBoundry {

	Vector<UserPerson> users;
	Vector<UserPerson> logedinUsers;
	Hashtable<UserPerson, Vector<Invitation>> invitations;
	Hashtable<UserPerson, Vector<Posting>> postings;
	Hashtable<UserPerson, Vector<UserPerson>> friends;
	
	
	public DbBoundryDummy() {
		// TODO Auto-generated constructor stub
		users = new Vector<UserPerson>();
		invitations = new Hashtable<UserPerson, Vector<Invitation>>();
		postings = new Hashtable<UserPerson, Vector<Posting>>();
		friends = new Hashtable<UserPerson, Vector<UserPerson>>();
	}
	
	//New User init
	
	public void initUserToUsers(UserPerson user){
		users.add(user);
	}
	public void initInvitations(UserPerson user){
		invitations.put(user, new Vector<Invitation>());
	}
	
	public void initPostings(UserPerson user){
		postings.put(user, new Vector<Posting>());
	}

	public void initFriends(UserPerson user){
		friends.put(user, new Vector<UserPerson>());
	}
	
	// getter
	
	public UserPerson getUserByUsername(String username){
		for(UserPerson u : users){
			if(u.getUsername().equals(username))
				return u;
		}
		System.out.println("No user with this username in the DB.");
		return null;
	}
		
	public Vector<Invitation> getUsersInvitations(UserPerson user){
		return invitations.get(user);
	}	
		
	public Vector<Posting> getPostingsWall(UserPerson user){
		return postings.get(user);
	}

	public Vector<UserPerson> getFriends(UserPerson user){
		return friends.get(user);
	}
	
	// adder
	
	public void addUser(UserPerson user){
		users.add(user);
	}
	
	public void addInvitation(UserPerson user, Invitation invitation){
		invitations.get(user).add(invitation);
	}
	
	public void addPosting(UserPerson user, Posting posting){
		postings.get(user).add(posting);
	}
	
	public void addFriend(UserPerson liked, UserPerson liker){
		friends.get(liked).add(liker);
	}

	//remover
	
	public void removeInvitation(UserPerson user, Invitation invitation){
		invitations.get(user).remove(invitation);
	}
	
	public void removeFriend(UserPerson hater, UserPerson hated){
		friends.get(hater).remove(hated);
	}

}