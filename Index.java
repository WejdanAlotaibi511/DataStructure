
public class Index {
     class Document {
            int docID;
            LinkedList <Words> index; 

            public Document() {
                docID = 0;
                index = new LinkedList <Words>();
            }

            public Document(int id, String []  words) {  //d
                docID = id;
                index = new LinkedList <Words>();
                for ( int i = 0 ; i < words.length; i ++)
                    index.insert(new Words (words[i]));

            }

            public void addWord (String word)
            {
                index.insert(new Words (word));
            }

           public boolean findWord(String word)
           {  index.findFirst();
               for(int i = 0; i < index.size;i++){
                 if(index.retrieve().equals(word)){
                   return true;
                 }
                 index.findNext();
               }
                return false;
}
                   
                   
                 
           }
       
    
    Document [] Docindex;
    
    
    public Index() {
        Docindex = new Document [50];
        for ( int i = 0 ; i < Docindex.length ; i++)
        {
            Docindex [i] = new Document();
            Docindex [i].docID = i;
        }
    }
        
    public void addALLDocument ( int docID, String [] data)  //del
    {
        for( int i = 0 ; i < data.length ; i++)
            Docindex[docID].addWord(data[i]);
    }
    
    public void addDocument ( int docID, String data)
    {       
           if(docID >= 0 && docID < Docindex.length){
               Docindex[docID].addWord(data);
           }
            //Docindex[docID].addNew(data);
    }
    
    public void printDocment (int docID)
    {    
        if (docID < 0 || docID >= Docindex.length) {
            System.out.println("Invalid Document ID");
            return;
        }
        
         Docindex[docID].index.findFirst();
            for ( int i = 0; i<  Docindex[docID].index.size ; i++)
            {
                System.out.print ( Docindex[docID].index.retrieve() + " ");
                 Docindex[docID].index.findNext();
        
       
            }
    }       
        }
    



