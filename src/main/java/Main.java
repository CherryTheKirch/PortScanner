import com.sun.org.apache.xpath.internal.operations.Bool;

import java.net.MalformedURLException;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class Main {
    public static void main(String []args){
        Scanner in = new Scanner(System.in);
        System.out.println("Type down port: ");
        int port_min = in.nextInt();
        System.out.println("Type up port: ");
        int port_max = in.nextInt();
        System.out.println("Type host: ");
        String host = in.nextLine();

        Map<Integer, Boolean> open_ports = new HashMap<>();

        try {
            open_ports = new PortScanner(host, port_min, port_max).scan();
        } catch (MalformedURLException e){
            e.printStackTrace();
        }

        String port_status;
        for (Map.Entry<Integer, Boolean> pair : open_ports.entrySet()){
            port_status = pair.getValue() ? "open" : "close";
            System.out.println("Port: " + pair.getKey() + " is " + port_status);
        }
    }
}
