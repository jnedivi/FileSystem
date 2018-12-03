import java.util.Date;

public abstract class SystemItem {
	
	protected String m_name;
	protected Date m_date_created;
	protected Directory m_parent; //keeping track of the parent is necessary for the delete operation
	
	public SystemItem(String i_name){
		m_name = i_name;
		m_date_created = new Date();
	}
	
	public SystemItem(String i_name, Directory i_parent){
		m_name = i_name;
		m_date_created = new Date();
		m_parent = i_parent;
	}
	
	public String getName(){ return m_name;}
	

}
