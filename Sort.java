import java.util.Comparator;

public class Sort implements Comparator<Process> {
    public int compare(Process p1, Process p2){
        return p1.getStart()- p2.getStart(); 
    }
}

