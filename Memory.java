class Memory {
    private int startAddress; 
    private int endAddress; 
    private int slotSize;

    public Memory(int s, int e){
        startAddress = s; 
        endAddress = e; 
        slotSize = e-s; 
    }

    public int getStart(){
        return startAddress; 
    }

    public int getSlotSize(){
        return slotSize; 
    }

    public int getStartAddress(){
        return startAddress; 
    }

    public void setStartAddress(int s){
        startAddress = s; 
        setSlotSize(); 
    }
    
    public void setSlotSize(){
        slotSize = endAddress - startAddress; 
    }

}
