
public class Ranking {
    Words word;
    int rank ;

    public Ranking() {
        rank = 0;
        word = new Words("");
    }

    public Ranking(String word, int rank) {
        this.word = new Words(word);
        this.rank = rank ;
    }
    
    public int addRanking ()
    {
        return ++rank;
    }

    public void setWords(Words word)
    {
        this. word = word; 
    }
    
    public Words getWords()
    {
         return word;
    }
    
    public int getRank ()
    {
        return this.rank;
    }
    
    @Override
    public String toString() {
        return "(" + word + ", " + rank + ")" ;
    } 
}
