package ensias.teams.buzinessLayer;



public  class User {
	@Override
	public String toString() {
		return "Person [FirstName=" + firstName + ", LastName=" + lastName + ", address=" + address + ", Birthday="
				+ birthday + ", Email=" + email + "]";
	}

	public String firstName;
	public String lastName;
	public String address;
	public String birthday;
	public String email;
		
	public User(String firstName, String secondName, String address, String birthday, String email){
		super();
		this.firstName = firstName;
		this.lastName = secondName;
		this.address = address;
		this.birthday = birthday;
		this.email = email;
	}
	
	
	
}
