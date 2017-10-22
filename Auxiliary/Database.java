package Auxiliary;

import StudentRecord.StudentRecord;

import java.lang.reflect.Executable;
import java.sql.*;

public class Database {

    private Connection con;
    public Database()
    {
        try
        {
            Class.forName("org.h2.Driver");
            con = DriverManager.getConnection("jdbc:h2:~/test", "sa", "");
        }
        catch (Exception e)
        {
            System.out.print(e);
        }
    }

    public void createSRTable()
    {
        try {
            con.createStatement().execute("CREATE TABLE SR (ID INT PRIMARY KEY, LastName VARCHAR(255), FirstName VARCHAR(255), Age INT)");
            System.out.println("Table Created");
        }catch (Exception e)
        {
            System.out.println(e + " \nTable already exists");
        }
    }

    public void insertSR(StudentRecord r)
    {
        int ID = r.getID();
        String LastName = r.getLastName();
        String FirstName = r.getFirstName();
        int Age = r.getAge();

        try{
            con.createStatement().execute("INSERT INTO SR VALUES(" + ID + ", '" + LastName + "', '" + FirstName + "', " + Age + ")");
        }catch (Exception e)
        {
            System.out.println(e);
        }
    }

    public String lookup(int ID)
    {
        try{
            Statement s = con.createStatement();
            ResultSet r = s.executeQuery("SELECT * FROM SR WHERE ID=" + ID);
            while(r.next())
            {
                return (r.toString());
            }
        }catch (Exception e)
        {
            System.out.println(e);
        }
        if (ID == 0)
            return "0";
        else
            return "NotFound";
    }
}
