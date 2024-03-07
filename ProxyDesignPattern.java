

interface Employee{
    
    public void create(int id);
    public void delete(int id);
    public void update(int id);
    public Employee get(int id);
    
}

class EmployeeProxy implements Employee{
    
     public final String user;
     public final Employee EmployeeImplementation;
     
     EmployeeProxy(String user,Employee EmployeeImplementation){
       this.user = user;
       this.EmployeeImplementation =  EmployeeImplementation;
     }
      
    @Override
    public void create(int id){
       
       if( user == "ADMIN"){
             EmployeeImplementation.create(id);
       }
       else
        System.out.println("Access Denied for CREATION");
       
    }
    
    @Override
    public void delete(int id){
        
      if( user == "ADMIN"){
             EmployeeImplementation.delete(id);
       }
       else
        System.out.println("Access Denied for DELETION");
     
    }
    
     @Override
    public void update(int id){
        
       if( user == "ADMIN"){
                EmployeeImplementation.update(id);
       }
       else
        System.out.println("Access Denied for UPDATE");
    }
    
    @Override
    public Employee get(int id){
        
       if( user == "ADMIN" || user == "USER" ){
          return EmployeeImplementation.get(id); 
       }
       else{
        System.out.println("Access Denied for GET");
       }
     
      return null;
    }
    
}

class EmployeeImplementation implements Employee{
    
    @Override
    public void create(int id){
       System.out.println("Record Successfully created for ID " + id);
    }
    
    @Override
    public void delete(int id){
      System.out.println("Record Successfully deleted for ID " + id);
    }
    
     @Override
    public void update(int id){
      System.out.println("Record Successfully updated for ID " + id);
    }
    
    @Override
    public Employee get(int id){
      return (new EmployeeImplementation());
    }
}

public class Main{
    
	public static void main(String[] args) {
	  Employee emp = new EmployeeProxy("DUMMY",new EmployeeImplementation());
	  emp.delete(0);  
	 
	}
}
