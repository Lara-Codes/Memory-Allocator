import java.io.File;
import java.io.IOException;
import java.util.Scanner;

public class Mallocator{
    public static void main(String[] args) throws IOException{
        //Type in FF, BF, or WF as last parameter 
        System.out.println("Type in FF, BF, or WF. Will default to WF.");
        Scanner s = new Scanner(System.in); 
        String fit = s.next(); 
        fit.toUpperCase(); 
        Data d = new Data(new File("Minput.data"), new File("Pinput.data"), fit);
        d.runInitial(); 
    }
}