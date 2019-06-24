import java.awt.*;
import java.awt.image.AreaAveragingScaleFilter;
import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.MalformedURLException;
import java.net.Socket;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class PortScanner {
    private URL url;
    private int[] port_range;
    private int single_port;
    private int timeout = 1000;

    public PortScanner(String url, int port_min, int port_max) throws MalformedURLException {
        url = correct_url(url);
        this.url = new URL(url);
        populate_ports(port_min, port_max);
    }

    public PortScanner(String url, int single_port) throws MalformedURLException {
        url = correct_url(url);
        this.url = new URL(url);
        this.single_port = single_port;
    }

    public Map<Integer, Boolean> scan(){
        Map<Integer, Boolean> open_ports = new HashMap<>();

        for (int port : port_range){
            try{
                Socket socket = new Socket();
                socket.connect(new InetSocketAddress(this.url.getPath(), port), this.timeout);
                socket.close();
                open_ports.put(port, true);
            } catch(Exception e){
                open_ports.put(port, false);
            }
        }

        return  open_ports;
    }

    public boolean scan_single(){
        boolean state = false;
        try{
            Socket socket = new Socket();
            socket.connect(new InetSocketAddress(this.url.getPath(), this.single_port), this.timeout);
            socket.close();
            state = true;
        } catch(Exception e){

        }

        return state;
    }




    // Populate ports
    private void populate_ports(int port_min, int port_max){
        this.port_range = new int[port_max-port_min];

        for(int i=0; i < port_range.length; i++){
            port_range[i] = port_min++;
        }
    }

    // Add protocol to string
    private String correct_url(String url){
        String correct_url;
        String protocol = "http://";
        correct_url = url.contains(protocol) ? url : protocol.concat(url);

        return correct_url;
    }
}
