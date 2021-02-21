package ensias.teams.buzinessLayer;

import java.util.ArrayList;

public class Group extends MenuItem{
	public String name;
	public User owner;
	public String description;
	public ArrayList<Team> teams;

	public Group(String name, String description){
		super(name);
		this.name=name;
		this.description = description;
		teams= new ArrayList<Team>();
	}
	
	public void addTeam(Team t) {
		teams.add(t);
	}
	 public void addTeams(ArrayList<Team> teams) {
		 this.teams=teams;
	 }
}
