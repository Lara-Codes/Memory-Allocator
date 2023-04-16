import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;

class Data{
    File minput; 
    File pinput; 
    PrintWriter pw; 
    String fit; 
    Scanner m; 
    Scanner p;
    ArrayList<Process> pArray; 
    ArrayList<Memory> mArray; 
    Fit f; 

    public Data(File mFile, File pFile, String fit) throws FileNotFoundException{
        minput = mFile; 
        pinput = pFile; 
        pw = new PrintWriter(new File(fit + "output.data")); 
        this.fit = fit; 
        pArray = new ArrayList<>(); 
        mArray = new ArrayList<>(); 
        m = new Scanner(minput); 
        p = new Scanner(pinput); 
        f = new Fit(fit, this); 
    }

    //Making arraylist of processes and memory data and writing the fit to the top of the file 
    public void runInitial() throws IOException{
        fileLabel(); 
        scanProcess(); 
        scanMemory(); 
        fitAlgorithm(); 
    }

    public void fileLabel(){
        if(this.fit.equals("FF")){
            pw.println("FFoutput.data"); 
        } else if(this.fit.equals("BF")){
            pw.println("BFoutput.data");
        } else{
            pw.println("WFoutput.data");
        }
    }

    public void scanProcess() throws IOException{
        while(p.hasNextLine()){
            String line = p.nextLine(); 
            String[] process = line.split(" ");
            if(process.length!=1){
                Process pr = new Process(Integer.parseInt(process[0]), Integer.parseInt(process[1])); 
                pArray.add(pr); 
            }

        }
    }

    public void scanMemory() throws IOException{
        while(m.hasNextLine()){
            String line = m.nextLine(); 
            String[] memory = line.split(" ");
            if(memory.length!=1){
                Memory mem = new Memory(Integer.parseInt(memory[0]), Integer.parseInt(memory[1]));
                mArray.add(mem); 
            }
        }
    }

    public void fitAlgorithm(){
        f.run(); 
    }


    public ArrayList<Process> getPArray(){
        return this.pArray; 
    }

    public ArrayList<Memory> getMArray(){
        return this.mArray; 
    }

    public PrintWriter getPrintWriter(){
        return pw; 
    }

    public void sortArr(){
        Collections.sort(this.pArray, new Sort()); 
    }
    
}