package ensias.teams.buzinessLayer;



public  class User {
	@Override
	public String toString() {
		return "Person [FirstName=" + firstName + ", LastName=" + lastName + ", address=" + address
				+ ", Email=" + email + " , password= "+ password + " ]";
	}

	public String firstName;
	public String lastName;
<<<<<<< Updated upstream
=======
	public String password;
	public String dateCreated;
>>>>>>> Stashed changes
	public String address;
	public String email;
<<<<<<< Updated upstream
=======
	
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getDateCreated() {
		return dateCreated;
	}

	public void setDateCreated(String dateCreated) {
		this.dateCreated = dateCreated;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	
	/** 
	 * Constructor used in load all users from the DB
	 */
	public User(Long id, String name, String lastName, String login, String password ,String dateCreated){
		
		this.firstName   = 	name;
		this.lastName    =	lastName;
		this.email	     = 	login;
		this.password    = 	password;
		this.id 	     = 	id;
		this.dateCreated =  dateCreated;
	}
>>>>>>> Stashed changes
		
	public User(String firstName, String secondName, String address, String email, String pass){
		super();
		this.firstName = firstName;
		this.lastName = secondName;
		this.address = address;
		this.password = pass;
		this.email = email;
	}
	
	
	
}
