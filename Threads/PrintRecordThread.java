package Threads;

import Auxiliary.CmdLine;
import Auxiliary.Database;
import Communication.Communication;
import ServerWriter.Writer;
import StudentRecord.StudentRecord;

import java.io.PrintWriter;
import java.net.Socket;

public class PrintRecordThread implements Runnable{

    Socket s;

    public void run() {

        System.out.println("Storing start");


        Communication com = new Communication();

        try {
            while(true) {
                Writer.recordsWithAge.offer(com.receiveRecord(s));

                if (!Writer.recordsWithAge.isEmpty()) {
                    StudentRecord r = Writer.recordsWithAge.poll();
                    if (r.getLastName().isEmpty())
                    {
                        String lookup = "ID: " + r.getID() + " " + Writer.db.lookup(r.getID());
                        System.out.println(lookup);
                    }
                    else
                        Writer.db.insertSR(r);
                    if (r.isIDZero()) {
                        System.out.println("Storing finished");
                        return;
                    }
                }
            }


        }catch(Exception e) {
            System.out.println(e);

        }
    }
    public void obtainSocket(Socket s)
    {
        this.s = s;
    }
}
