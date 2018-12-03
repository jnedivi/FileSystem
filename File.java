
public class File extends SystemItem{
	
	private Integer m_size;
	
	public File(String i_name, Directory i_parent, Integer i_size) {
		super(i_name, i_parent);
		m_size = i_size;
	}
	
	public String toString(){
		StringBuilder sb = new StringBuilder();
		sb.append("File: Name-" + m_name);
		sb.append(", Size-" + m_size);
		sb.append(", Date Created-" + m_date_created);
		
		return sb.toString();
	}

}
