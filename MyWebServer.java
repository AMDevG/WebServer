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
        System.out.println("Here is the current directory: ");
        ReadFiles fileReader = new ReadFiles();
        fileReader.printDir();

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

class ReadFiles {
  public static void main (String[] args ) {}

    
  public void printDir(){
    String filedir ;
    File f1 = new File ( "C:\\Users\\jberr\\Desktop\\WebServer" ) ;
    
    File[] strFilesDirs = f1.listFiles ( );
    for ( int i = 0 ; i < strFilesDirs.length ; i ++ ) {
      if ( strFilesDirs[i].isDirectory ( ) ) 
    System.out.println ( "Directory: " + strFilesDirs[i] ) ;
      else if ( strFilesDirs[i].isFile ( ) )
    System.out.println ( "File: " + strFilesDirs[i] + 
           " (" + strFilesDirs[i].length ( ) + ")" ) ;
    }
  }
}









