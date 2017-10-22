package Threads;


import Communication.Communication;
import Auxiliary.CmdLine;
import StudentRecord.StudentRecord;
import com.google.gson.Gson;
import java.io.*;
import java.net.Socket;
import java.util.ArrayList;

public class ReadRecordThread implements Runnable{
    Socket s;

    public void run(){

        ArrayList<String> lines = new ArrayList<String>();
        BufferedReader br = null;

        Gson gson = new Gson();
        Communication com = new Communication();

        try{
            br = new BufferedReader(new FileReader(CmdLine.inputFile));
            while(br.ready()){
                lines.add(br.readLine());
                if (lines.size() == 4){
                    StudentRecord r = new StudentRecord.StudentRecordBuilder(lines.get(1), lines.get(0)).id(lines.get(2)).dateOfBirth(lines.get(3)).build();

                    String o = gson.toJson(r);
                    System.out.println(o);
                    com.sendJSONRecord(o, s);

                    lines.clear();
                }
            }

        }catch (Exception e) {
            System.out.println(e);

        }finally {
            try{
                br.close();
            }
            catch(Exception e){
                System.out.println(e);}
        }
        //End thread One
    }

    public void obtainSocket(Socket s)
    {
        this.s = s;
    }
}
