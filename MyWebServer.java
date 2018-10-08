import java.io.*;
import java.net.*;
import java.util.ArrayList;


class Worker extends Thread{
  //SIMILARLY TO INET AND JOKE SERVER A NEW WORKER THREAD IS SPAWNED
  //TO HANDLE EACH CLIENT CONNECTION
    Socket sock;
    Worker (Socket s) {sock = s;}

public void run(){

  //RUN METHOD TAKEN FROM INET/JOKE SERVERS. CREATES BUFFERED READERS TO READ ANY INPUT
  //CREATES PRINT STREAM TO SEND OUTPUT
    PrintStream out = null;
    BufferedReader in = null;
    String userData;
    
    try{
        in = new BufferedReader(new InputStreamReader(sock.getInputStream()));
        out = new PrintStream(sock.getOutputStream());
        }catch(IOException ioe){System.out.println(ioe);}
   try{
    //CALLING FUNCTION CREATEHTML UPON SUCCESSFUL CONNECTION AND WRITES THE RESULT
    //TO THE OUTPUT STREAM, THEN CLOSES SOCKET
        out.println(createHTML());
        sock.close();
    } catch (Exception x){x.printStackTrace();}
   
}

public String createHTML(){

  //I DID NOT HAVE TIME TO FULLY COMPLETE THIS FUNCTION. IT SUCCESSFULLY RETURNS
  //THE FILES IN THE CURRENT WORKING DIRECTORY AND FORMATS THEM TO BE HOTLINKS
  //BUT I DID NOT GET THE CHANCE TO WRITE THE HTTP RESPONSE HEADERS TO DELIVER
  //THEM AS HTML AND NOT TEXT SO IT JUST SHOWS THE HTML AS TEXT IN BROWSER

  String htmlToReturn =   "HTTP/1.1 200 OK" + "\r\n" +
  "Content-Length: " + "5000" + "Content-Type: text/html"+
   "\r\n" + "\r\n";

  htmlToReturn = htmlToReturn + "\n" + "<html>";

  ReadFiles fileReader = new ReadFiles();
  ArrayList<File> files = fileReader.printDir();

  for(File f : files){
    htmlToReturn  = htmlToReturn + "<a href='"+f+"'>"+f+"</a>"+"<br>" + "\n";
  }

  htmlToReturn = htmlToReturn + "</html>";
  return htmlToReturn;
}

}
  
public class MyWebServer {
    //AGAIN SIMILARLY TO THE OTHER SERVERS WE HAVE WRITTEN THE WEBSERVER LISTENS AT PORT 2540
    //FOR CONNECTIONS 
    public static void main(String[] args) throws IOException {

       int q_len = 6;
       int port = 2540;
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
  //READ FILES CLASS SENDS BACK A LIST OF ALL THE FILES IN THE CURRENT WORKING DIRECTORY
  //USES GETPROPERTY SYSTEM FUNCTION TO GET THE CURRENT DIRECTORY, AND THEN ITERATES
  //THROUGH FILES, APPENDING THEM TO THE ARRAY LIST AND RETURNING IT TO THE CALLER

  public static void main (String[] args ) {}
    
  public ArrayList<File> printDir(){

    ArrayList<File> fileList = new ArrayList<File>();
    String filedir ;
    File f1 = new File (System.getProperty("user.dir")); 
    
    File[] strFilesDirs = f1.listFiles ( );
    for ( int i = 0 ; i < strFilesDirs.length ; i ++ ) {
      if ( strFilesDirs[i].isDirectory ( )){
      }
      
      else if ( strFilesDirs[i].isFile ( ) ){
        fileList.add(strFilesDirs[i]);
      }

    }
    return fileList;
  }
}









