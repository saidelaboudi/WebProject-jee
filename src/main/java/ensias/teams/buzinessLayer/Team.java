package ensias.teams.buzinessLayer;


import java.util.ArrayList;



public class Team {
	
	public String name;
	public User owner;
	public ArrayList<User> members;
	public ArrayList<Channel> channels;
	public String date;
	
	public Team( String name, User owner){
		this.name = name;
		this.owner = owner;
		members = new ArrayList<User>();
		channels= new ArrayList<Channel>();
		date = new String();
	}

	public void addMember(User p) {
		members.add(p);
	}
	
	public void addChannel(Channel c) {
		this.channels.add(c);
	}
	public void setDate(String date) {
		this.date=date;
	}
	public String getDate() {
		return this.date;
	}
	
	}
