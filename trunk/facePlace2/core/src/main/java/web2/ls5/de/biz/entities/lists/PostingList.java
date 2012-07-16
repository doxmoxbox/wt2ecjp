package web2.ls5.de.biz.entities.lists;

import javax.inject.Named;

import web2.ls5.de.biz.entities.atoms.Posting;

import java.util.Vector;

@Named
public class PostingList {

	Vector<Posting> postingsList;
	
	public PostingList() {
		// TODO Auto-generated constructor stub
	}
	
	public Vector<Posting> getPostingsList() {
		return postingsList;
	}

	public void setPostingsList(Vector<Posting> postingsList) {
		this.postingsList = postingsList;
	}

	public void add(Posting posting){
		postingsList.add(posting);
	}
	
}
