package Threads;

import Auxiliary.CmdLine;
import Communication.Communication;
import StudentRecord.StudentRecord;

import java.net.Socket;

/**
 * Created by ericb on 11/28/2016.
 */
public class ClientLookupListen implements Runnable{

    Socket s;

    public void run() {

        System.out.println("Listening for lookup results start");


        Communication com = new Communication();

        try {
            while(true) {

                String results = com.receiveJSONRecord(s);
                System.out.println(results);

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
