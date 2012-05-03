import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class DBData
{
	private static Connection conn = null;
	public DBData()
	{

		String url = "jdbc:mysql://itcdland.csumb.edu/jugriffin";
		String dbName = "jugriffin";
		String driver = "com.mysql.jdbc.Driver";
		String userName = "jugriffin";
		String password = "x7wCZD";

		try
		{
			Class.forName(driver).newInstance();
			conn = DriverManager.getConnection(url, userName, password);
			System.out.println("Connected");
			System.out.println("Disconnected");

		} catch (Exception e)
		{
			e.printStackTrace();
		}

	}

	public void close()
	{
		try
		{
			conn.close();
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
	}


	public boolean validateLogin(String name, String password)
	{
		boolean isValid = false;
		String resultString = "";
		String query = "SELECT Password FROM Users WHERE Username = \"" + name +"\"";
		try
		{
			Statement statement = conn.createStatement();
			ResultSet results = statement.executeQuery(query);
			if (results.next())
			{
				resultString = results.getString(1);
			}
		} catch (SQLException e)
		{
			e.printStackTrace();
		}
		System.out.println(password);
		System.out.println(resultString);
		System.out.println(isValid);
		if (resultString.equals(password))
			isValid = true;
		System.out.println(isValid);
		return isValid;					
	}

	public String getUserInfo(String name){
		String resultString = "";

		String query = "SELECT Username FROM Users WHERE Username = \"" + name +"\"";
		try
		{
			Statement statement = conn.createStatement();
			ResultSet results = statement.executeQuery(query);
			//System.out.println("user_id"+"\t"+"user_name");
			//Extact result from ResultSet rs
			
			if(results.next()){
				resultString = results.getString(1);				
			}
			// close ResultSet rs
			results.close();
		} catch (SQLException e)
		{
			e.printStackTrace();
		}
		
		query = "SELECT Password FROM Users WHERE Username = \"" + name +"\"";
		try
		{
			Statement statement = conn.createStatement();
			ResultSet results = statement.executeQuery(query);
			//System.out.println("user_id"+"\t"+"user_name");
			//Extact result from ResultSet rs
			
			if(results.next()){
				resultString += ":" + results.getString(1);
			}
			// close ResultSet rs
			results.close();
		} catch (SQLException e)
		{
			e.printStackTrace();
		}

		
		System.out.println(resultString);
		return resultString;


	}
}