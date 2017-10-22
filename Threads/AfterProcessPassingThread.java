package Threads;

import Communication.Communication;
import ClientServerProcessor.Processor;
import StudentRecord.StudentRecord;

import java.net.Socket;

public class AfterProcessPassingThread implements Runnable{

    Socket s;

    public void run(){
        try {

            System.out.println("Sending processed records start");
            Communication com = new Communication();

            while(true){
                if (!Processor.recordsWithAge.isEmpty()) {
                    StudentRecord r = Processor.recordsWithAge.poll();
                    com.sendRecord(r, s);
                    if (r.isIDZero())
                    {
                        System.out.println("Sending processed records finished");
                        return;
                    }
                }
            }

        }catch (Exception e ){
            System.out.println(e);
        }
        //End thread two
    }
    public void obtainSocket(Socket s)
    {
        this.s = s;
    }
}
