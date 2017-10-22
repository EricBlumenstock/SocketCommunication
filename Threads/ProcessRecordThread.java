package Threads;

import Communication.Communication;
import ClientServerProcessor.Processor;
import StudentRecord.StudentRecord;
import com.google.gson.Gson;

import java.net.Socket;

public class ProcessRecordThread implements Runnable{

    Socket s;

    public void run(){
        //compute age
        try {

            System.out.println("Processing start");
            Communication com = new Communication();
            Gson gson = new Gson();

            while(true){
                String json = com.receiveJSONRecord(s);
                StudentRecord o = gson.fromJson(json, StudentRecord.class);
                System.out.println(json);
                Processor.recordsNoAge.offer(o);

                if (!Processor.recordsNoAge.isEmpty()) {
                    StudentRecord r = Processor.recordsNoAge.poll();
                    r.ComputeAge();
                    Processor.recordsWithAge.offer(r);
                    if (r.isIDZero())
                    {
                        System.out.println("Processing finished");
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
