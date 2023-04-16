import java.io.IOException;

class Process {
    private int pid; 
    private int size; 
    private int start; 

    public Process(int pid, int size) throws IOException{
        this.pid = pid; 
        this.size = size; 
        start = -0; 
    }

    public int getPid(){
        return this.pid; 
    }

    public int getSize(){
        return this.size; 
    }

    public int getStart(){
        return start; 
    }

    public void setStart(int s){
        start = s; 
    }
}
