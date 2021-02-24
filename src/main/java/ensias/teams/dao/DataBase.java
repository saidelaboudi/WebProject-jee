package ensias.teams.dao;

import java.sql.*;
import java.util.*;
import java.io.*;


public class DataBase {
	
	public String ServerName;
	public String port;
	public String DataBaseName;
	public String UserName;
	public String UserPassword;
	public Connection connection;
	public Properties properties;
	
	//Getters & Setters
	public String getServerName() {
		return ServerName;
	}
	public void setServerName(String serverName) {
		ServerName = serverName;
	}
	public String getPort() {
		return port;
	}
	public void setPort(String port) {
		this.port = port;
	}
	public String getDataBaseName() {
		return DataBaseName;
	}
	public void setDataBaseName(String dataBaseName) {
		DataBaseName = dataBaseName;
	}
	public String getUserName() {
		return UserName;
	}
	public void setUserName(String userName) {
		UserName = userName;
	}
	public String getUserPassword() {
		return UserPassword;
	}
	public void setUserPassword(String userPassword) {
		UserPassword = userPassword;
	}
	public Connection getConnection() {
		return connection;
	}
	public Properties getProperties() {
		return properties;
	}
	
	public void setConnection() throws SQLException {
		String url="jdbc:mysql://"+getServerName()+":"+getPort()+"/"+getDataBaseName()+"?useUnicode=true"
				+ "&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&"
				+ "serverTimezone=UTC";
		setProperties();
		this.connection = DriverManager.getConnection(url,getProperties());
	}
	public void setProperties() {
		this.properties = new Properties();
		this.properties.setProperty("user",getUserName());
		this.properties.setProperty("password",getUserPassword());
	}

	// Constructor
	public DataBase(String serverName, String port, String dataBaseName, String userName, String userPassword) throws SQLException {
		super();
		setServerName(serverName);
		setPort(port);
		setDataBaseName(dataBaseName);
		setUserName(userName);
		setUserPassword(userPassword);
		setConnection();
	}
	
	public void CreateTable(String TableName,ArrayList<ArrayList<String>> Attributes){
		String query;
		query="CREATE TABLE "+TableName;
		query+="(";
		for (ArrayList<String> att: Attributes) {
			query+=att.get(0)+" "+ att.get(1) ;
			if(!att.equals(Attributes.get(Attributes.size()-1)))
				query+=",\n";
		}
		query+=");\n";
		//System.out.println(query);
		try
			{
			Query(query);
			}catch (Exception e) {
				System.out.println(e.getMessage());
			}
	}
	
	public void Query(String query) throws SQLException {
		Statement statement=getConnection().createStatement();
		statement.executeUpdate(query);
	}
	
	public ResultSet Select(String TableName,String condition){		
		ResultSet result = null;
		try {
			String query="SELECT * FROM "+TableName+" WHERE "+condition;
			System.out.println(query);
			Statement statement =getConnection().createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,ResultSet.CONCUR_UPDATABLE);
			result=statement.executeQuery(query);		
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}
	
	public void Insert(String value,String TableName) throws SQLException{
		String sql="INSERT INTO "+TableName+" VALUES("+value+");";
		PreparedStatement statement=getConnection().prepareStatement(sql);
		//INSERT STAEMENT 
		/**
		 * INSERT INTO TABLENAME VALUES(--O-*-*)
		 */
		System.out.println(statement.toString());
		try{
			statement.executeUpdate();
		}catch (Exception e) {
			System.out.println(e.getMessage());
		}
	}
	
	public void Delete(String condition,String TableName)  throws SQLException  {
		
		String sql="DELETE FROM "+TableName+" WHERE "+condition;
		Statement statement=getConnection().createStatement();
		/**
		 * DELLET FROM TABLENAME WHERE CONDITION(-- -*-*o)
		 */
		System.out.println(sql);
		statement.executeUpdate(sql);
	}
	
	public void DropTable(String TableName) throws SQLException {
		String sql="DROP TABLE "+TableName;
		Statement statement=getConnection().createStatement();
		statement.execute(sql);
	}
	
	public String HtmlTable(ArrayList<ArrayList<String>> attributes,ResultSet rs) throws SQLException {
		String table="";
		table+="<table>\n";
		rs.first();
		
		table+="\n<tr>\n";
		for(ArrayList<String> att:attributes) {
			table+="\t<th>";
			table+=att.get(0);
			table+="</th>\n";	
		}

		table+="\t</tr>\n";
		while(rs.next()) {
			table+="\t<tr>\n";
			for(ArrayList<String> att:attributes) {
				table+="\t\t<th>";
				table+=rs.getString(att.get(0));
				table+="</th>\n";	
			}
			table+="\t</tr>\n";
		}
		table+="</table>\n";
		return table;
	}
	
	public void MakeHtmlFile(ArrayList<ArrayList<String>> attributes,ResultSet rs) throws SQLException {
		String index=HtmlTable(attributes, rs);
		try {
		      FileWriter myWriter = new FileWriter("index.html");
		      myWriter.write(index);
		      myWriter.close();
		      //System.out.println("Successfully wrote to the file.");
		    } catch (IOException e) {
		      System.out.println("An error occurred.");
		      e.printStackTrace();
		    }
	}
}
	
