package ensias.teams.buzinessLayer;

import java.sql.Date;


public class Student extends Person{
	public Speciality specialty;
	
	public Student(String firstName, String secondName, Address address, Date birthday, String email,Speciality specialty){
		super(firstName, secondName, address, birthday, email);
		this.specialty = specialty;
	}
	
	@Override
	public String toString() {
		return "Student [specialty=" + specialty + ", groups=" +  ", FirstName=" + FirstName + ", LastName="
				+ LastName + ", address=" + address + ", Birthday=" + Birthday + ", Email=" + Email + "]";
	}
}
