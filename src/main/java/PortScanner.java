import java.awt.*;
import java.net.InetSocketAddress;
import java.net.MalformedURLException;
import java.net.Socket;
import java.net.URL;
import java.util.ArrayList;

public class PortScanner {
    private URL url;
    private int[] port_range;
    private int single_port;

    public PortScanner(String url, int port_min, int port_max) throws MalformedURLException {
        this.url = new URL(url);
        populate_ports(port_min, port_max);
    }

    public PortScanner(String url, int single_port) throws MalformedURLException {
        this.url = new URL(url);
        this.single_port = single_port;
    }

    public ArrayList<Boolean> scan(){

        // It should return table of open ports, propably it will be keys[port number] and value[state]

        for(int i=0; i < port_range.length; i++){
            try{
                Socket socket = new Socket();
                socket.connect(new InetSocketAddress(this.url.getPath(), port_range[i]), 1000);
                socket.close();

            }
        }

    }




    // Populate ports
    private void populate_ports(int port_min, int port_max){
        this.port_range = new int[port_max-port_min];

        for(int i=0; i < port_range.length; i++){
            port_range[i] = port_min++;
        }
    }
}
