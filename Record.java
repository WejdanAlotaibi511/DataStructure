
public class Record {
    Words word;
    boolean [] documentList ;

    public Record()
    {
        documentList = new boolean [50];
        for (int i = 0 ; i < documentList.length ; i++)
            documentList [i] = false;
        word = new Words("");
    }
    public Record(String word, boolean [] documentList) {    //del
        this.word=new Words(word);
        this.documentList = new boolean [documentList.length];
        for (int i = 0 ; i < documentList.length ; i++)
            this.documentList [i] = documentList[i];

    }

   
    
    public boolean add_docID(int docID) {
    return !documentList[docID] ? (documentList[docID] = true) : false;
     }

    public void setword(Words word)
    {
        this.word = word; 
    }
      
         public Words getword()
    {
         return word;
    }
    
    public boolean [] copyDoc ()   //d
    {
        boolean [] Copy = new boolean [documentList.length];
        for( int i = 0 ; i < Copy.length ; i++)
            Copy[i] = documentList[i];
        return Copy;
    }
    
    
    public boolean [] getDocs(){
        boolean [] test = new boolean [documentList.length];
        for ( int i = 0 ; i < test.length ; i++)
            test[i] = documentList[i];
        return test;
            }
    
    
    
    
   @Override
   public String toString() {
        String docs = "";
        for (int i = 0, j = 0 ; i < documentList.length; i++)
            if ( j == 0 && documentList [i]==true )
            {
                docs += " " + String.valueOf(i) ;
                j++;
            }
            else if ( documentList[i]==true )
            {
                docs += ", " + String.valueOf(i) ;
                j++;
            }
        
        return word + "[" + docs + ']';
    }
    
    
}

