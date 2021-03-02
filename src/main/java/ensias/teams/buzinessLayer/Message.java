package ensias.teams.buzinessLayer;

public class Message {
	public Long id;
	public Long idSender;
	public Long idReceiver;
	public String content;
	public String date;
	
	public Message(Long id , Long idSender , Long idReceiver , String content , String date) {
		this.id=id;
		this.idSender=idSender;
		this.idReceiver=idReceiver;
		this.content = content;
		this.date = date;
	}
	
}
