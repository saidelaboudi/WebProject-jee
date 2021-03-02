package ensias.teams.buzinessLayer;

import java.util.List;

public class ChatPersoUser {
	User otherUser;
	List<Message> messages;
	
	public ChatPersoUser(User otherUser ,	List<Message> messages) {
		this.otherUser = otherUser;
		this.messages = messages;
	}

	public User getOtherUser() {
		return otherUser;
	}

	public void setOtherUser(User otherUser) {
		this.otherUser = otherUser;
	}

	public List<Message> getMessages() {
		return messages;
	}

	public void setMessages(List<Message> messages) {
		this.messages = messages;
	}

}
