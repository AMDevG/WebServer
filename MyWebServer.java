import java.io.*;
import java.net.*;
import java.util.ArrayList;


class Worker extends Thread{
    Socket sock;
    Worker (Socket s) {sock = s;}

public void run(){

    PrintStream out = null;
    BufferedReader in = null;
    String userData;
    
    try{
        in = new BufferedReader(new InputStreamReader(sock.getInputStream()));
        out = new PrintStream(sock.getOutputStream());
        }catch(IOException ioe){System.out.println(ioe);}
   try{
        out.println(createHTML());
        sock.close();
    } catch (Exception x){x.printStackTrace();}
   
}

public String createHTML(){
  String htmlToReturn = "<html>";
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









