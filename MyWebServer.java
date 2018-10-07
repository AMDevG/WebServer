import java.io.*;
import java.net.*;
//import java.util.ArrayList;
//import java.util.HashMap;

class Worker extends Thread{
    Socket sock;
    Worker (Socket s) {sock = s;}

public void run(){

    PrintStream out = null;
    BufferedReader in = null;
    // String userName;
    // String userID;
    String userData;
    
    try{
        in = new BufferedReader(new InputStreamReader(sock.getInputStream()));
        out = new PrintStream(sock.getOutputStream());
        }catch(IOException ioe){System.out.println(ioe);}
   try{
       //READS IN CLIENT UID AND USERNAME, SPLITS AROUND CHARACTER"|"
       //SAVES USERID (UID RANDOM NUM BETWEEN 1 AND 8000000
        userData = in.readLine();
        System.out.println("Reading in userData " + userData);
    
        
        // String[] userDataArray = userData.split("\\|");
        
        // userID = userDataArray[0];
        // userName = userDataArray[1].replaceAll("\\s+","");
        
        // if(!JokeServer.clientMap.containsKey(userID)){
            
        //     ArrayList<ArrayList<String>> contentArray = new ArrayList<ArrayList<String>>();
        //     ArrayList<String> jokeArray = new ArrayList<String>();
        //     ArrayList<String> proverbArray = new ArrayList<String>();

        //     contentArray.add(jokeArray);
        //     contentArray.add(proverbArray);
        //     JokeServer.clientMap.put(userID, contentArray);
        // }
        //sendDirs(userData, out);

        sock.close();
    } catch (Exception x){x.printStackTrace();}
   
}
}


  
public class MyWebServer {

    public static void main(String[] args) throws IOException {

       int q_len = 6;
       int port = 80;
       Socket sock;
       
       ServerSocket servsock = new ServerSocket(port, q_len);
          
          System.out.println("John Berry's WebServer starting up, listening at port 2540. \n");
       
       while(true){
           sock = servsock.accept();
           new Worker(sock).start();
       }
    }
}








