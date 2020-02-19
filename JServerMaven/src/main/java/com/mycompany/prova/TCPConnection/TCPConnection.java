package tcpclientserverdownload;

import java.io.IOException;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.io.*;
import java.util.Base64;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author maspes_marco + prof
 *
 * @brief Classe descrittiva dell'utilizzo delle principali funzioni di
 * comunicazione tramite protocollo tcp
 *
 * https://www.codejava.net/java-se/networking/java-socket-client-examples-tcp-ip
 * https://stackoverflow.com/questions/9157077/transfer-binary-file-by-byte-array-using-tcp-in-java
 *
 */
public class TCPConnection {

    private Socket socket;

    private InputStream is;
    private InputStreamReader isr;
    private BufferedReader ibr;

    private OutputStream os;
    private OutputStreamWriter osw;
    private PrintWriter obr;

    private static String endMess = "@@@@@@@@@@@@";

    
//    main(){
//        ServerSocket benvenuto = new ...
//        
//        TCPConnection sleve = new TCPConnection(benvenuto);
//        sleve.getIé
//        
//    }
    /**
     * 
     * @brief Costruttore che realizza la connessione per il server
     * @param socket server che permette al server di instanziare
     * una nuova comunicazione su un socket dedicato
     */
    public TCPConnection(ServerSocket socket) throws IOException {
        this.socket = socket.accept();
        openStream();
    }

    /**
     * @brief Costruttore che realizza la connessione per il client
     * @param ip indirizzo ip del server
     * @param port porta su cui bisogna collegarsi per poter scambiare dati con
     * il server
     */
    public TCPConnection(InetAddress ip, int port) throws IOException {
        socket = new Socket(ip, port);
        openStream();
        
    }

    public String getIP(){
        return socket.getInetAddress().toString();
    }
    /**
     * @brief metodo che apre stream e buffer di lettura e di scrittura
     */
    private void openStream() throws IOException {

        is = this.socket.getInputStream();
        isr = new InputStreamReader(is);
        ibr = new BufferedReader(isr);

        os = this.socket.getOutputStream();
        osw = new OutputStreamWriter(os);
        obr = new PrintWriter(osw, true); // PrintWriter(OutputStream out, boolean autoFlush)        
    }

    /**
     * @brief metodo che chiude stream e buffer di lettura e di scrittura
     */
    public void closeStream() throws IOException {
        ibr.close();
        isr.close();
        is.close();
        obr.close();
        osw.close();
        os.close();
    }

    /**
     * @brief metodo che permette di leggere il messaggio in arrivo sul socket
     * @return restituisce una stringa contenente tutte le informazioni fino al
     * fine riga
     */
    public String waitAndReadLines() throws IOException {
        String message = "";
        boolean end = false;
        int numLineeLette = 0;

        String lineaLetta = "";
        while (!end) {
            lineaLetta = ibr.readLine();
            if ((lineaLetta == null) || (lineaLetta.equalsIgnoreCase(endMess))) {
                end = true;
            } else {
                numLineeLette++;
                message += lineaLetta;
            }
        }

        System.out.println("waitAndRead() - messaggio ricevuto numLineeLette=" + numLineeLette);

        return message;
    }

    /**
     * @brief metodo che permette di leggere il messaggio in arrivo sul socket
     * in formato byte
     * @return restituisce una stringa contenente tutte le informazioni fino al
     * fine riga
     */
    public String waitAndReadBytes() throws IOException {
        int numChar = 0;
        StringBuilder data = new StringBuilder();
        byte[] mybytearray = new byte[1024];
        String str = "";

        int count;
        while ((count = is.read(mybytearray)) > 0) {
            // Convert byte[] to String
            str = new String(mybytearray, 0, count);
            // str = Base64.getEncoder().encodeToString(mybytearray);
            if (!str.equalsIgnoreCase("")) {
                System.out.println(str);
                System.out.println("---------------"+count+"------------------");
                data.append(str, 0, count);
                numChar += count;
            }
        }

        System.out.println(data);
        System.out.println("waitAndRead() - messaggio ricevuto di " + numChar + " bytes");

        return data.toString();
    }

    /**
     * @brief metodo che permette di inviare il messaggio sul socket
     * @param message Stringa contenente il messaggio da inviare
     */
    public void sendTo(String message) throws IOException {
        obr.println(message);

        // linea con stringa particolare per indicare la fine del messaggio 
        obr.println(endMess);
        System.out.println("sendTo() - messaggio inviato");

    }

    /**
     * @brief metodo che permette di inviare il messaggio sul socket
     * @param message Stringa contenente il messaggio da inviare
     */
    public void sendToBytes(String message) throws IOException {
        // Convert string to byte[]
        byte[] mybytearray = message.getBytes();
        // Base64 Decoded
        // byte[] mybytearray = Base64.getDecoder().decode(message);
        os.write(mybytearray, 0, message.length());
        os.flush();

        System.out.println(message);
        System.out.println("sendToBytes() - messaggio inviato di " + mybytearray.length + " bytes");
    }

    /**
     * @brief metodo che chiude il socket
     */
    public void closeConnection() {
        try {
            closeStream();
            socket.close();
        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }
    }

}
