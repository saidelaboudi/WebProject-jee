package ensias.teams.buzinessLayer;

import java.util.ArrayList;

public class Groupe extends MenuItem{
	public String Name;
	public Person Owner;
	public String Description;
	public ArrayList<Team> teams;

	public Groupe(String name, String description){
		super(name);
		Description = description;
		teams= new ArrayList<Team>();
	}
	
	public void addTeam(Team t) {
		teams.add(t);
	}
	 public void addTeams(ArrayList<Team> teams) {
		 this.teams=teams;
	 }
}
