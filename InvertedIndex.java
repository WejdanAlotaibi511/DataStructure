
public class InvertedIndex {
    
    LinkedList <Record> invertedindex; 

            public InvertedIndex() {
              invertedindex = new LinkedList<Record>();
            }
            
            public int size()
            {
                return invertedindex.size();
            }

            public boolean addNew(int docID, String word) {   //change
                
                if (invertedindex.empty()) {
                   
                Record t = new Record();
                t.setword(new Words (word));
                t.add_docID(docID);
                invertedindex.insert(t); 
                return true;
                
               } else {
                   invertedindex.findFirst();
               while (!invertedindex.last()) {
                  Record currentRecord = invertedindex.retrieve();
                  
                 if (currentRecord.word.word.compareTo(word) == 0) {
                 currentRecord.add_docID(docID);
                 invertedindex.update(currentRecord);
                  return false; 
                   }
                 
                  invertedindex.findNext(); 
                    }

       
                 Record lastRecord = invertedindex.retrieve();
                 if (lastRecord.word.word.compareTo(word) == 0) {
                 lastRecord.add_docID(docID);
                  invertedindex.update(lastRecord);
                 return false; 
                  } else {
            
                  Record newRecord = new Record();
                  newRecord.setword(new Words(word));
                  newRecord.add_docID(docID);
                  invertedindex.insert(newRecord);
                    return true; 
                   }
                    }
                     }
            public boolean find(String word)  //change
                {
               if (invertedindex.empty())
                   return false;

               invertedindex.findFirst();
               for ( int i = 0 ; i < invertedindex.size ; i++){
                   if ( invertedindex.retrieve().word.word.compareTo(word)==0 )
                       return true;
                  invertedindex.findNext();
               }
               return false;
                     }

            
                 public boolean[] AndORFMethod(String str) {
                       
                     if (! str.contains(" OR ") && ! str.contains(" AND "))
            {
                    boolean [] r1 = new boolean[50];
                    str = str.toLowerCase().trim();
            
                    if (this.find (str))
                    r1 =  this.invertedindex.retrieve().getDocs();
                     return r1;
            }
            
                   else if (str.contains(" OR ") && str.contains(" AND "))
            {
                    String [] AND_ORs = str.split(" OR ");
                   boolean []  r1 = AndMethod (AND_ORs[0]);
               
                    for ( int i = 1 ; i < AND_ORs.length ; i++  )
                {   
                    boolean [] r2 =AndMethod (AND_ORs[i]);
                    
                    for ( int j = 0 ; j < 50 ; j++ )
                        r1 [j] = r1[j] || r2[j];
                }
                return r1;
            }
            
            else  if (str.contains(" AND "))
                return AndMethod(str);
                 
                return ORMethod (str);
                 }
                     
                                         
             public boolean[] AndMethod(String str) {
                 String [] ANDs = str.split(" AND ");
                 boolean [] b1 = new boolean [50];
            
                if (this.find(ANDs[0].toLowerCase().trim()))
                b1 = this.invertedindex.retrieve().getDocs();

               for ( int i = 1 ; i< ANDs.length ; i++)
            {
                boolean [] b2 = new boolean [50];
                if (this.find (ANDs[i].toLowerCase().trim()))
                    b2 = this.invertedindex.retrieve().getDocs();
                
                for ( int j = 0 ; j < 50 ; j++)
                    b1 [j] = b1[j] && b2[j];
            }
               return b1;   
                 
             }
               

                 public boolean[] ORMethod(String str) {
                     
                     String [] ORs = str.split(" OR ");
                     boolean [] b1 = new boolean [50];
            
                    if (this.find(ORs[0].toLowerCase().trim()))
                    b1 = this.invertedindex.retrieve().getDocs();

                   for ( int i = 1 ; i< ORs.length ; i++)
            {
                   boolean [] b2 = new boolean [50];
                   if (this.find (ORs[i].toLowerCase().trim()))
                    b2 = this.invertedindex.retrieve().getDocs();
                
                for ( int j = 0 ; j < 50 ; j++)
                    b1 [j] = b1[j] || b2[j];
               
            }
            return b1;
                     
                     
                     
                     
                     
                 }
             public void printDocument() {
               if (this.invertedindex.empty()) 
                     System.out.println("Empty!!");
                                   //return;
                                  
                       //System.out.println(invertedindex.retrieve());
                  else {
    
                   
                        this.invertedindex.findFirst();
                      while (!this.invertedindex.last()) {
                          System.out.println(invertedindex.retrieve());
                              this.invertedindex.findNext();
                      }System.out.println(invertedindex.retrieve());
      }
             }
             

}

