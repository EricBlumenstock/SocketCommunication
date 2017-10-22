package ServerWriter;

import Auxiliary.CmdLine;
import Auxiliary.Database;
import Communication.Communication;
import StudentRecord.StudentRecord;
import Threads.ForwardLookupToProcessor;
import Threads.PrintRecordThread;
import Threads.ProcessRecordThread;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadPoolExecutor;

public class Writer {

    public static ArrayBlockingQueue<StudentRecord> recordsWithAge = new ArrayBlockingQueue<StudentRecord>(10);
    public static ArrayBlockingQueue<String> lookup = new ArrayBlockingQueue<String>(10);
    public static Database db = new Database();

    public static void main(String[] args) {

        PrintRecordThread PartFour = new PrintRecordThread();
        ForwardLookupToProcessor lookupForward = new ForwardLookupToProcessor();
        ThreadPoolExecutor executor = (ThreadPoolExecutor) Executors.newCachedThreadPool();

        CmdLine cmd = new CmdLine();
        cmd.WriterCmdLine(args);

        Communication com = new Communication(Integer.parseInt(CmdLine.portNum));

        Communication forwardLookup = new Communication(CmdLine.serverName, Integer.parseInt(CmdLine.ProcessorListenLookup)); //1027 pass lookup results to client
        db.createSRTable();

        while(true)
        {
            PartFour.obtainSocket(com.serverAccept());
            try {
                Thread.sleep(5500);
            }
            catch(Exception e){}
            lookupForward.obtainSocket(com.getClientSocket());

            executor.execute(PartFour);

            executor.execute(lookupForward);

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
