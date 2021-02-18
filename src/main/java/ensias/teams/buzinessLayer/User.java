package ensias.teams.buzinessLayer;



public  class User {
	@Override
	public String toString() {
		return "Person [FirstName=" + firstName + ", LastName=" + lastName + ", address=" + address + ", Password="
				+ password + ", Email=" + email + "]";
	}

	public Long id;
	public String firstName;
	public String lastName;
	public String address;
	public String password;
	public String email;
		
	public User(String firstName, String secondName, String address, String password, String email){
		super();
		this.firstName = firstName;
		this.lastName = secondName;
		this.address = address;
		this.password = password;
		this.email = email;
	}
	
}
