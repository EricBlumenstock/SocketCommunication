package Threads;

import ClientServerProcessor.Processor;
import Communication.Communication;
import StudentRecord.StudentRecord;

import java.net.Socket;

public class ForwardToClient implements Runnable{

    Socket s;

    public void run(){
        try {

            System.out.println("Forward lookups to client start");
            Communication com = new Communication();

            while(true){
                if (!Processor.lookupRecords.isEmpty()) {
                    String results = Processor.lookupRecords.poll();
                    com.sendJSONRecord(results, s);
                    if (results == "0")
                    {
                        System.out.println("Forwarding lookups finished");
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
