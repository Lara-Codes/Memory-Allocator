import java.util.ArrayList;

class Fit {

    String fit;
    Data d;
    ArrayList<Integer> waitArr;

    public Fit(String f, Data d) {
        this.fit = f;
        this.d = d;
        waitArr = new ArrayList<>();
    }

    public void run() {
        if (fit.equals("FF")) {
            firstFit();
        } else if (fit.equals("BF")) {
            bestFit();
        } else {
            worstFit();
        }
    }

    public void firstFit() {
        for (Process p : d.getPArray()) {
            for (Memory m : d.getMArray()){
                if (p.getSize() <= m.getSlotSize() && p.getStart()==-0){
                    setting(p, m);
                }
            }
        }
        write();
    }

    public void bestFit(){
        int min = -0; // First memory slot assumed best fit
        int mem = -0;
        for (Process p : d.getPArray()) {
            for (Memory m : d.getMArray()) {
                int diff = m.getSlotSize() - p.getSize();
                if ((diff <= min && diff >= 0 && min != -0 && p.getStart() == -0)
                        || (min == -0 && diff >= 0 && p.getStart() == -0)) {
                    if (diff == min) { // If two memory slots are the same, do FF
                        continue;
                    }
                    min = diff;
                    mem = m.getStart();
                }
            }
            if (min != -0) {
                for (Memory m : d.getMArray()) {
                    if (m.getStart() == mem) {
                        setting(p, m);
                    }
                }
            }
            min = -0;
            mem = -0;
        }
        write();
    }

    public void worstFit() {
        int max = -0;
        int mem = -0;
        for (Process p: d.getPArray()) {
            for (Memory m: d.getMArray()) {
                int diff = m.getSlotSize() - p.getSize();
                if ((diff >= max && diff >= 0 && max != -0 && p.getStart() == -0)
                        || (max == -0 && diff >= 0 && p.getStart() == -0)) {
                    if (diff == max) { // If two memory slots are the same, do FF
                        continue;
                    }
                    max = diff;
                    mem = m.getStart();
                }
            }
            if (max != -0) {
                for (Memory m : d.getMArray()) {
                    if (m.getStart() == mem) {
                        setting(p, m);
                    }
                }
            }
            max = -0;
            mem = -0;
        }
        write();
    }

    public void setting(Process p, Memory m) {
        p.setStart(m.getStart());
        m.setStartAddress(m.getStart() + p.getSize());
    }

    public void write(){
        d.sortArr(); 
        for (Process p : d.getPArray()) {
            if (p.getStart() != -0) {
                int space = p.getStart() + p.getSize();
                d.getPrintWriter().println(p.getStart() + " " + space + " " + p.getPid());
            } else {
                waitArr.add(p.getPid() * -1);
            }
        }
        if (waitArr.size() > 0) {
            for (int i = 0; i < waitArr.size(); i++) {
                d.getPrintWriter().print(waitArr.get(i) + " ");
            }
        } else {
            d.getPrintWriter().println("-0");
        }
        d.getPrintWriter().close();

    }
}
