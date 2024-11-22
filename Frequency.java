
public class Frequency {
  
    int Position = 0;
    int f = 0;

    public Frequency(int docID, int f) {
        this.Position= docID;
        this.f = f;
    }

    public int getDocID() {
        return Position;
    }

    public void setDocID(int docID) {
        this.Position = docID;
    }

    public int getFrequency() {
        return f;
    }

    public void setFrequency(int f) {
        this.f = f;
    }
} 

