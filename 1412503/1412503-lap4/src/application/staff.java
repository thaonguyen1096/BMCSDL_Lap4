package application;
import javafx.beans.property.SimpleStringProperty;

public class staff {
		private final SimpleStringProperty id = new SimpleStringProperty("");
		private final SimpleStringProperty name = new SimpleStringProperty("");
		private final SimpleStringProperty email = new SimpleStringProperty("");
		private final SimpleStringProperty salary = new SimpleStringProperty("");

//	public staff() {
//	        this("", "", "", "");
//	    }
	 	
	    public staff(String fid, String name, String email, String salary) {
	        setID(fid);
	        setName(name);
	        setEmail(email);
	        setSalary(salary);
	    }

	    public String getId() {
	        return id.get();
	    }
	 
	    public void setID(String fid) {
	    	id.set(fid);
	    }
	        
	    public String getName() {
	        return name.get();
	    }
	    
	    public void setName(String fName) {
	        name.set(fName);
	    }
	    
	    public String getEmail() {
	        return email.get();
	    }
	    
	    public void setEmail(String fName) {
	        email.set(fName);
	    }
	    
	    public String getSalary() {
	        return salary.get();
	    }
	    
	    public void setSalary(String fSalary) {
	    	salary.set(fSalary);
	    }
	    
	}