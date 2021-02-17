package ensias.teams.buzinessLayer;



public  class User {
	@Override
	public String toString() {
		return "Person [FirstName=" + firstName + ", LastName=" + lastName + ", address=" + address + ", Birthday="
				+ birthday + ", Email=" + email + "]";
	}
	public Long id;
	public String firstName;
	public String lastName;
	public String login;
	public String password;
	public String dateCreated;
	public String address;
	public String birthday;
	public String email;
	
	/** 
	 * Constructor used in load all users from the DB
	 */
	public User(Long id, String name, String lastName, String login, String password ,String dateCreated){
		
		this.firstName   = 	name;
		this.lastName    =	lastName;
		this.login	     = 	login;
		this.password    = 	password;
		this.id 	     = 	id;
		this.dateCreated =  dateCreated;
	}
		
	public User(String firstName, String secondName, String address, String birthday, String email){
		super();
		this.firstName = firstName;
		this.lastName = secondName;
		this.address = address;
		this.birthday = birthday;
		this.email = email;
	}
	
	
	
}
