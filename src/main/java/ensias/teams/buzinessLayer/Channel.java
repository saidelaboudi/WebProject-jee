package ensias.teams.buzinessLayer;

import java.util.ArrayList;

public class Channel {
	public String name;
	public ArrayList<SubChannel> subChannel;
	
	public Channel(String name) {
		this.name=name;
		subChannel= new ArrayList<SubChannel>();
		SubChannel chat = new Chat("Chat");
		SubChannel files = new File("Files");
		subChannel.add(chat);
		subChannel.add(files);
		
	}
	
	public void addSubChannel(SubChannel sub) {
		this.subChannel.add(sub);
	}
	
}
