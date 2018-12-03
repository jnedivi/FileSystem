import java.util.ArrayList;
import java.util.TreeMap;

public class FileSystem {
	
	private Directory m_root;
	private ArrayList<String> m_allNames; // to make sure all names are unique
	
	public FileSystem(){
		m_root = new Directory("Main");
		m_allNames = new ArrayList<String>();
		m_allNames.add("Main");
	}
	
	public File addFile(String i_parentDirName, String i_fileName, Integer i_fileSize){
		
		File newFile = null;
		SystemItem parent = find(i_parentDirName);
		
		if(parent != null && !(parent instanceof File)){
			if(!(m_allNames.contains(i_fileName))){
				newFile = new File(i_fileName, (Directory) parent, i_fileSize);
				((Directory) parent).add(newFile);
				m_allNames.add(i_fileName);
			}else{
				System.out.println("This name already exists in the system");
			}
			
		}else{
			System.out.println("Parent Directory " +  i_parentDirName + " doesn't exist in system.");
		}
		
		return newFile;
	}
	
	public Directory addDir(String i_parentDirName, String i_dirName){
		
		Directory newDirectory = null;
		SystemItem parent = find(i_parentDirName);
		
		if(parent != null && !(parent instanceof File)){			
			if(!(m_allNames.contains(i_dirName))){
				newDirectory = new Directory(i_dirName, (Directory) parent);
				((Directory) parent).add(newDirectory);
				m_allNames.add(i_dirName);
			}else{
				System.out.println("This name already exists in the system");
			}
			
		}else{
			System.out.println("Parent Directory " +  i_parentDirName + " doesn't exist in system.");
		}
		
		return newDirectory;
	}
	
	public void delete(String i_name){
		
		// the user won't be able to delete the main directory
		if(i_name == "Main") return;
		
		SystemItem toBeDeleted = find(i_name);
		if(toBeDeleted!= null){
			m_allNames.remove(i_name);
			
			if(toBeDeleted != m_root){
				toBeDeleted.m_parent.remove(toBeDeleted);
			}
			
			toBeDeleted.m_parent = null;
		}else{
			System.out.println(i_name + " doesn't exist in system.\n");
		}
	}
	
	public void showFileSystem(){
		rec_showFileSystem(0, m_root);
	}
	
	private void rec_showFileSystem(int i_indents, SystemItem i_current){
		
		StringBuilder indents = new StringBuilder();
		
		for(int i = 0; i < i_indents; i++){
			indents.append("\t");
		}
		
		System.out.println(indents.toString() + i_current.toString());
		
		if(i_current instanceof Directory){
			
			TreeMap<String, SystemItem> subEntries = ((Directory) i_current).getSubEntries();
			
			// in order to print such that the files will be printed after the directories,
			// we iterate over all sub entries twice
			for(SystemItem item: subEntries.values()){
				if(item instanceof Directory){
					rec_showFileSystem(i_indents + 1, item);
				}
			}
			
			for(SystemItem item: subEntries.values()){
				if(item instanceof File){
					rec_showFileSystem(i_indents + 1, item);
				}
			}
		}
	}
	
	
	private SystemItem find(String i_name){
		if(m_root.getName() == i_name) return m_root;
		return rec_find(i_name, m_root);
	}
	
	private SystemItem rec_find(String i_name, SystemItem i_current){

		if(i_current instanceof Directory){
			TreeMap<String, SystemItem> subEntries = ((Directory) i_current).getSubEntries();
			
			if(subEntries.containsKey(i_name)){
				return subEntries.get(i_name);
			}
						
			for(SystemItem item: subEntries.values()){
				SystemItem temp = rec_find(i_name, item);
				if(temp != null) return temp;
			}
						
		}
		
		return null;
	}
	
	// For Testing
	/*public static void main(String args[]){
		
		FileSystem fs = new FileSystem();
		fs.addFile("Main", "DocOne", 20);
		//fs.delete("DocOn");
		
		fs.addDir("Main", "DirOne");
		fs.addFile("DirOne", "DocThree", 20);
		fs.addDir("Main", "Z");
		fs.addFile("Main", "DocTwo", 10);
		fs.addFile("Z", "a", 20);
		fs.addDir("Z", "P");
		//fs.delete("DocThre");
		fs.showFileSystem();
	}*/
}
