package ensias.teams.buzinessLayer;

import java.util.ArrayList;

public class Tag {
	
	public String tagName;
	public User owner;
	public ArrayList<User> tagged ;
	
	public Tag(String tagName, User owner) {
		super();
		this.tagName = tagName;
		this.owner = owner;
		tagged = new ArrayList<User>();
	}
	
	public void addMember(User person) {
		this.tagged.add(person);
	}
	

}
