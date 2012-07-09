package web2.ls5.de.entities;

import javax.persistence.*;
import java.lang.annotation.ElementType;
import java.util.Date;

@Entity
public class DBPost 
{
	private long id;
	private String msg;
	private Date creationDate;
	private long creator;
	
	@Id
	@GeneratedValue
	public long getId() 
	{
		return id;
	}
	
	public void setId(long id) 
	{
		this.id = id;
	}
	
	public String getMsg()
	{
		return msg;
	}
	
	public void setMsg(String msg)
	{
		this.msg = msg;
	}
	
	@Temporal(value = TemporalType.DATE)
	public Date getCreationDate() 
	{
		return creationDate;
	}

	public void setCreationDate(Date creationDate) 
	{
		this.creationDate = creationDate;
	}
	
	public void setCreator(long creator)
	{
		this.creator = creator;
	}
	
	public long getCreator()
	{
		return creator;
	}
}