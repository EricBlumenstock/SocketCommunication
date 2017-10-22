package ClientServerProcessor;

import Auxiliary.CmdLine;
import Communication.Communication;
import StudentRecord.StudentRecord;
import Threads.AfterProcessPassingThread;
import Threads.ForwardToClient;
import Threads.ListenForClient;
import Threads.ProcessRecordThread;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadPoolExecutor;

public class Processor {

    public static ArrayBlockingQueue<StudentRecord> recordsNoAge = new ArrayBlockingQueue<StudentRecord>(10);
    public static ArrayBlockingQueue<StudentRecord> recordsWithAge = new ArrayBlockingQueue<StudentRecord>(10);
    public static ArrayBlockingQueue<String> lookupRecords = new ArrayBlockingQueue<String>(10);

    public static void main(String[] args) {

        ProcessRecordThread PartTwo = new ProcessRecordThread();
        AfterProcessPassingThread PartThree = new AfterProcessPassingThread();
        ForwardToClient Forward = new ForwardToClient();
        ListenForClient Listen = new ListenForClient();

        ThreadPoolExecutor executor = (ThreadPoolExecutor) Executors.newCachedThreadPool();

        CmdLine cmd = new CmdLine();
        cmd.ProcessorCmdLine(args);

        Communication comClient = new Communication(CmdLine.serverName, Integer.parseInt(CmdLine.serverPort));
        Communication comServer = new Communication(Integer.parseInt(CmdLine.portNum));

        Communication listenForClient = new Communication(Integer.parseInt(CmdLine.ProcessorListenLookup)); //listen for lookup results
        Communication sendToClient = new Communication(CmdLine.serverName, Integer.parseInt(CmdLine.ClientLookupPort)); //pass lookup results to client

        while(true)
        {
            PartTwo.obtainSocket(comServer.serverAccept());
            PartThree.obtainSocket(comClient.getClientSocket());

            try {
                Thread.sleep(5500);
            }
            catch(Exception e){}
            Listen.obtainSocket(listenForClient.serverAccept());
            Forward.obtainSocket(sendToClient.getClientSocket());

            executor.execute(PartThree);
            executor.execute(PartTwo);

            executor.execute(Forward);
            executor.execute(Listen);

            try{
                executor.shutdown();
            }
            catch(Exception e)
            {
                System.out.println(e);
            }

        }
    }
}
