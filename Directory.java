import java.util.TreeMap;


public class Directory extends SystemItem{
	
	/*
	 * I decided to implement a tree map for my sub entries, so that I will have
	 * a hash map for faster access, and have it sorted for the print function
	 */
	protected TreeMap<String, SystemItem> m_subEntries; // contains all sub directories and files
	
	public Directory(String i_name) {
		super(i_name);
		m_subEntries = new TreeMap<String, SystemItem>();
	}
	
	public Directory(String i_name, Directory i_parent) {
		super(i_name, i_parent);
		m_subEntries = new TreeMap<String, SystemItem>();
	}
	
	public boolean remove(SystemItem i_item){
		return m_subEntries.remove(i_item.m_name, i_item);
	}
	
	public void add(SystemItem i_item){	
		m_subEntries.put(i_item.m_name, i_item);		
	}
	
	public TreeMap<String, SystemItem> getSubEntries(){
		return m_subEntries;
	}
	
	public String toString(){
		return "[" + m_name +"]";
	}
	
}
