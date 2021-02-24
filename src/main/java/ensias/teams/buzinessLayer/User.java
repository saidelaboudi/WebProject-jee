package ensias.teams.buzinessLayer;



public  class User {
	@Override
	public String toString() {
		return "Person [FirstName=" + firstName + ", LastName=" + lastName + ", address=" + address + ", Password="
				+ password + ", Email=" + email + "]";
	}

	public Long id;
	public String firstName="";
	public String lastName;
	public String password;
	public String dateCreated;
	public String address;
	public String email;
	
	/** 
	 * Constructor used in load all users from the DB
	 */
	public User() {
		
	}
	public User(Long id, String name, String lastName, String login, String password ,String dateCreated){
		
		this.firstName   = 	name;
		this.lastName    =	lastName;
		this.email	     = 	login;
		this.password    = 	password;
		this.id 	     = 	id;
		this.dateCreated =  dateCreated;
	}
		
	public User(String firstName, String secondName, String address, String password, String email){
		super();
		this.firstName = firstName;
		this.lastName = secondName;
		this.address = address;
		this.password = password;
		this.email = email;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}
	
}
