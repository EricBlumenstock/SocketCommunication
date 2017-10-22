package Auxiliary;

/**
 * Created by ericb on 11/7/2016.
 */
public class CmdLine {
    public static String inputFile = "student.txt";
    public static String serverName = "127.0.0.1";
    public static String serverPort = "1025";

    public static String ClientLookupPort = "2000";
    public static String ProcessorListenLookup = "2001";

    public static String portNum = "1025";
    public static String outputFile = "output.txt";

    public CmdLine(){

    }

    public void ReaderCmdLine(String [] args){
        if(args.length == 1)
        {
            inputFile = args[0];
        }
        else if (args.length == 2)
        {
            inputFile = args[0];
            serverName = args[1];
        }
        else if (args.length == 3 ){
            inputFile = args[0];
            serverName = args[1];
            serverPort = args[2];
        }
    }

    public void ProcessorCmdLine(String [] args){
        if(args.length == 1)
        {
            portNum = args[0];
        }
        else if (args.length == 2 ){
            portNum = args[0];
            serverName = args[1];
        }
        else if (args.length == 3 ){
            portNum = args[0];
            serverName = args[1];
            serverPort = args[2];
        }
    }

    public void WriterCmdLine(String [] args){
        if(args.length == 1)
        {
            outputFile = args[0];
        }
        else if (args.length == 2 ){
            outputFile = args[0];
            portNum = args[1];
        }
    }

    public String getInputFile(){
        return inputFile;
    }

    public String getServerName(){
        return serverName;
    }

    public String getServerPort(){
        return serverPort;
    }
}
