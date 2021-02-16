package ensias.teams.buzinessLayer;

import java.util.ArrayList;


public class Team {
	
	public String name;
	public Person owner;
	
	public ArrayList<Person> members;
	public ArrayList<Channel> channels; 
	
	public Team( String name, Person owner){
		this.name = name;
		this.owner = owner;
		members = new ArrayList<Person>();
		channels= new ArrayList<Channel>();
	}
	
	public void addMember(Person p) {
		members.add(p);
	}
	
	public void addChannel(Channel c) {
		this.channels.add(c);
	}
	
	// Changed
	}
