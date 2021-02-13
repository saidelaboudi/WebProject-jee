package ensias.teams.buzinessLayer;


public class Speciality {
	public String Name;
	public Departement departement;
	public Person coordinator;
	
	public Speciality(String name,Departement departement, Person coordinator){
		Name=name;
		this.departement = departement;
		this.coordinator = coordinator;

	}
	

}
