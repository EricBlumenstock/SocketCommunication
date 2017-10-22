package ClientReader;

import Auxiliary.CmdLine;
import Communication.Communication;
import Threads.ClientLookupListen;
import Threads.ReadRecordThread;

import javax.swing.plaf.synth.SynthTextAreaUI;
import java.util.Scanner;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadPoolExecutor;

public class Reader {


    public static void main(String[] args) {

        ReadRecordThread PartOne = new ReadRecordThread();
        ClientLookupListen Listen = new ClientLookupListen();
        ThreadPoolExecutor executor = (ThreadPoolExecutor) Executors.newCachedThreadPool();

        CmdLine cmd = new CmdLine();
        cmd.ReaderCmdLine(args);

        Communication com = new Communication(CmdLine.serverName, Integer.parseInt(CmdLine.serverPort));

        Communication listenForResults = new Communication(Integer.parseInt(CmdLine.ClientLookupPort));// listen for lookup results



        while(true) {
            PartOne.obtainSocket(com.getClientSocket());
            try {
                Thread.sleep(5500);
            }
            catch(Exception e){}
            Listen.obtainSocket(listenForResults.serverAccept());

            //Start thread one reader
            executor.execute(PartOne);
            executor.execute(Listen);

            try {
                //Thread.sleep(1500);
                executor.shutdown();
            } catch (Exception e) {
                System.out.println(e);
            }
        }
    }

}
