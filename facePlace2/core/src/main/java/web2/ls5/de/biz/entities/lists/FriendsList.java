package web2.ls5.de.biz.entities.lists;

import java.util.List;
import javax.inject.Named;

import web2.ls5.de.biz.entities.atoms.User;

@Named
public class FriendsList {

	List<User> friends;
	
	public FriendsList() {
		// TODO Auto-generated constructor stub
	}
	
	public List<User> getFriends() {
		return friends;
	}

	public void setFriends(List<User> friends) {
		this.friends = friends;
	}

	public void add(User friend){
		friends.add(friend);
	}
	
	public void remove(User notFriend){
		friends.remove(notFriend);
	}
}
