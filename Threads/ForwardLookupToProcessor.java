package Threads;


import ClientServerProcessor.Processor;
import Communication.Communication;
import ServerWriter.Writer;

import java.net.Socket;

public class ForwardLookupToProcessor implements Runnable{

    Socket s;

    public void run(){
        try {

            System.out.println("Forward lookups to processor start");
            Communication com = new Communication();

            while(true){
                if (!Writer.lookup.isEmpty()) {
                    String results = Writer.lookup.poll();
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
