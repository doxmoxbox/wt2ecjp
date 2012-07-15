package web2.ls5.de.boundries.dummys;

import java.util.Hashtable;
import java.util.Vector;

import javax.inject.Named;



import web2.ls5.de.boundries.DbBoundry;
import web2.ls5.de.entities.atoms.Invitation;
import web2.ls5.de.entities.atoms.Posting;
import web2.ls5.de.entities.atoms.User;

@Named
public class DbBoundryDummy implements DbBoundry {

	Vector<User> users;
	Vector<User> logedinUsers;
	Hashtable<User, Vector<Invitation>> invitations;
	Hashtable<User, Vector<Posting>> postings;
	Hashtable<User, Vector<User>> friends;
	
	
	public DbBoundryDummy() {
		// TODO Auto-generated constructor stub
		users = new Vector<User>();
		invitations = new Hashtable<User, Vector<Invitation>>();
		postings = new Hashtable<User, Vector<Posting>>();
		friends = new Hashtable<User, Vector<User>>();
	}
	
	//New User init
	
	public void initUserToUsers(User user){
		users.add(user);
	}
	public void initInvitations(User user){
		invitations.put(user, new Vector<Invitation>());
	}
	
	public void initPostings(User user){
		postings.put(user, new Vector<Posting>());
	}

	public void initFriends(User user){
		friends.put(user, new Vector<User>());
	}
	
	// getter
	
	public User getUserByUsername(String username){
		for(User u : users){
			if(u.getUsername().equals(username))
				return u;
		}
		System.out.println("No user with this username in the DB.");
		return null;
	}
		
	public Vector<Invitation> getUsersInvitations(User user){
		return invitations.get(user);
	}	
		
	public Vector<Posting> getPostingsWall(User user){
		return postings.get(user);
	}

	public Vector<User> getFriends(User user){
		return friends.get(user);
	}
	
	// adder
	
	public void addUser(User user){
		users.add(user);
	}
	
	public void addInvitation(User user, Invitation invitation){
		invitations.get(user).add(invitation);
	}
	
	public void addPosting(User user, Posting posting){
		postings.get(user).add(posting);
	}
	
	public void addFriend(User liked, User liker){
		friends.get(liked).add(liker);
	}

	//remover
	
	public void removeInvitation(User user, Invitation invitation){
		invitations.get(user).remove(invitation);
	}
	
	public void removeFriend(User hater, User hated){
		friends.get(hater).remove(hated);
	}

}