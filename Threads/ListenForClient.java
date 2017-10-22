package Threads;

import ClientServerProcessor.Processor;
import Communication.Communication;

import java.net.Socket;


public class ListenForClient implements Runnable{

    Socket s;

    public void run() {

        System.out.println("Listening for lookup results start");


        Communication com = new Communication();

        try {
            while(true) {

                String results = com.receiveJSONRecord(s);
                Processor.lookupRecords.offer(results);

                if (results == "0")
                    return;
            }


        }catch(Exception e) {
            System.out.println(e);

        }
        System.out.println("Listening for lookup results end");
    }
    public void obtainSocket(Socket s)
    {
        this.s = s;
    }
}
