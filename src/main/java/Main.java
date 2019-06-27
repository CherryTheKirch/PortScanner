import com.sun.org.apache.xpath.internal.operations.Bool;

import java.net.MalformedURLException;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class Main {
    public static void main(String []args){
        if(args.length < 4 || args.length > 5){
            help();
        } else if(args.length == 4){
            start(args[1], args[2], args[3]);
        } else if(args.length ==5){
            switch (args[1]){
                case "-h":
                    help();
                case "-s":
                    start(args[2], args[3], args[4]);
                 default:
                     help();
            }
        }
    }

    private static void start(String host_arg, String port_min_arg, String port_max_arg){
        int port_min = Integer.parseInt(port_min_arg);
        int port_max = Integer.parseInt(port_max_arg);
        String host = host_arg;


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

    private static void help(){
        System.out.println("Welcome to port scanner. Here is the usage:\n" +
                "GENERAL USAGE: 'program_name -flag host port_min port_max'" +
                "-h  --help  ->  You see this ;]\n" +
                "The flag is optional");
    }
}
