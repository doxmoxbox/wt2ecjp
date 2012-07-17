package web2.ls5.de.backend;

import java.util.Date;

import web2.ls5.de.entities.DBPerson;
import web2.ls5.de.entities.DBPost;

public class TestEntriesFactory
{	
	private static int onlyOnce = 1;
	
	public TestEntriesFactory(ApplicationBackend backend)
	{
		if(onlyOnce != 1)
		{
			return;
		}
		/*Create persons*/
		
		DBPerson person1 = backend.createNewPerson();
		person1.setName("Mr.X");
		person1.setBirthdate(new Date(42, 9, 3));
		person1.setPassword(backend.getUserManager().getHashedPassword("123"));
		
		DBPerson person2 = backend.createNewPerson();
		person2.setName("Emil");
		person2.setBirthdate(new Date(87, 3, 24));
		person2.setPassword(backend.getUserManager().getHashedPassword("123"));
		
		DBPerson person3 = backend.createNewPerson();
		person3.setName("Tom");
		person3.setBirthdate(new Date(54, 1, 25));
		person3.setPassword(backend.getUserManager().getHashedPassword("123"));
		
		DBPerson person4 = backend.createNewPerson();
		person4.setName("Johnny");
		person4.setBirthdate(new Date(23, 8, 2));
		person4.setPassword(backend.getUserManager().getHashedPassword("123"));
		
		DBPerson person5 = backend.createNewPerson();
		person5.setName("Christoph");
		person5.setBirthdate(new Date(88, 2, 28));
		person5.setPassword(backend.getUserManager().getHashedPassword("123"));
		
		/*Create posts*/
	    
		backend.createPosting(person5, "Moin Jungs wir schaffen das!");
		backend.createPosting(person2, "Wann ist das nächste Treffen?");
	    backend.createPosting(person1, "Moin Jungs heute funktioniert mal alles wie geschmiert! :D");
	    backend.createPosting(person3, "ich zock ne Runde! ;)");
	    backend.createPosting(person4, "Yeah ich hab ein neues Netbook! :D");
	    backend.createPosting(person1, "Bus oder Bahn? :/");
	    backend.createPosting(person2, "Ich frage mich echt, wie Tapestrie das macht... >:(");
	    backend.createPosting(person4, "Was geht Leute?");
	    backend.createPosting(person1, "Gib mir einfach mein Blackticket! ;)");
	    backend.createPosting(person5, "Tapestrie ist echt genial.");
	    backend.createPosting(person3, "Listening to: Papa Roach - Last Resort");
	    backend.createPosting(person2, "Einfach: 42!");
	    backend.createPosting(person3, "Morgen!"); 
	    backend.createPosting(person4, "Traurig aber wahr ...");    
	    backend.createPosting(person5, "Kein Bock mehr.");
	    
	    /* Diff the creationdate of postings*/
	    int i=23;
	    for( DBPost post : backend.getAllPostingsFromDBPerson(person1))
	    {
	    	post.getCreationDate().setDate(post.getCreationDate().getDate()-i);
	    	i--;
	    }
	    for( DBPost post : backend.getAllPostingsFromDBPerson(person2))
	    {
	    	post.getCreationDate().setDate(post.getCreationDate().getDate()-i);
	    	i--;
	    }
	    
	    for( DBPost post : backend.getAllPostingsFromDBPerson(person3))
	    {
	    	post.getCreationDate().setDate(post.getCreationDate().getDate()-i);
	    	i--;
	    }
	    
	    for( DBPost post : backend.getAllPostingsFromDBPerson(person4))
	    {
	    	post.getCreationDate().setDate(post.getCreationDate().getDate()-i);
	    	i--;
	    }
	    
	    for( DBPost post : backend.getAllPostingsFromDBPerson(person5))
	    {
	    	post.getCreationDate().setDate(post.getCreationDate().getDate()-i);
	    	i--;
	    }

	    /*Friendships*/
	    
	    backend.createInvitation(person5, person4);
	    backend.createInvitation(person4, person3);
	    backend.createInvitation(person2, person1);
	    backend.createInvitation(person3, person2);
	    backend.createInvitation(person1, person5);
	    	    
	    backend.createInvitation(person1, person3);
	    backend.createInvitation(person2, person4);
	    backend.createInvitation(person3, person5);
	    backend.createInvitation(person4, person1);
	    backend.createInvitation(person5, person3);
	    
	    onlyOnce = 0;
	}
}

