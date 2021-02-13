package ensias.teams.buzinessLayer;

import java.util.ArrayList;
import java.sql.Date;


public abstract class Person {
	@Override
	public String toString() {
		return "Person [FirstName=" + FirstName + ", LastName=" + LastName + ", address=" + address + ", Birthday="
				+ Birthday + ", Email=" + Email + "]";
	}

	public String FirstName;
	public String LastName;
	public Address address;
	public Date Birthday;
	public String Email;
	public ArrayList<String> Alias;
		
	public Person(String firstName, String secondName, Address address, Date birthday, String email){
		super();
		FirstName = firstName;
		LastName = secondName;
		this.address = address;
		Birthday = birthday;
		Email = email;
		Alias = new ArrayList<String>();
	}
	
	public void addAlias(String Alias) {
		this.Alias.add(Alias);
	}
	
	
}
