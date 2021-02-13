package ensias.teams.buzinessLayer;

import java.sql.Date;


public class Teacher extends Person{
	public Departement departement;
	
	public Teacher(String firstName, String secondName, Address address, Date birthday, String email,Departement departement){
		super(firstName, secondName, address, birthday, email);
		this.departement = departement;
	}
	
}
