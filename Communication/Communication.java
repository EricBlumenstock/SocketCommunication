package Communication;

import StudentRecord.StudentRecord;

import java.io.InputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;


public class Communication {


    Socket client;
    ServerSocket server;

    public Communication(String hostname, int portNum) //Client
    {
        try
        {
            client = new Socket(hostname, portNum);
        }
        catch(Exception e)
        {
            System.out.print(e);
        }
    }

    public Communication(int portNum) //Server
    {
        boolean success = false;

        while (!success && portNum <= 65535)
        {
            try
            {
                server = new ServerSocket(portNum);
                success = true;
            }
            catch(Exception e)
            {
                success = false;
                portNum++;
            }
        }

        if (portNum > 65535)
            System.out.println("Failure to open port");
        else
            System.out.println("Port: " + portNum);

    }

    public Communication()
    {

    }

    public Socket serverAccept()
    {
        Socket s = null;
        try{
            s = server.accept();
            System.out.println("Accepted connection");
        }
        catch(Exception e)
        {
            System.out.println(e);
        }
        return s;

    }

    public Socket getClientSocket()
    {
        return this.client;
    }

    public void sendRecord(StudentRecord r, Socket s)
    {
        try{
            OutputStream os = s.getOutputStream();
            ObjectOutputStream oos = new ObjectOutputStream(os);

            oos.writeObject(r);

        }catch(Exception e)
        {
            System.out.print(e);
        }


    }

    public StudentRecord receiveRecord(Socket s)
    {
        try{
            InputStream os = s.getInputStream();
            ObjectInputStream ois = new ObjectInputStream(os);

            return (StudentRecord)ois.readObject();

        }catch(Exception e)
        {
            System.out.print(e);
        }


        return null;
    }

    public void sendJSONRecord(String r, Socket s)
    {
        try{
            OutputStream os = s.getOutputStream();
            ObjectOutputStream oos = new ObjectOutputStream(os);

            oos.writeObject(r);

        }catch(Exception e)
        {
            System.out.print(e);
        }


    }

    public String receiveJSONRecord(Socket s)
    {
        try{
            InputStream os = s.getInputStream();
            ObjectInputStream ois = new ObjectInputStream(os);

            return (String)ois.readObject();

        }catch(Exception e)
        {
            System.out.print(e);
        }


        return null;
    }



}
