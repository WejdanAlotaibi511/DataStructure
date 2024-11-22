
public class InvertedIndexAVL {

     AVL <String, Record> invertedindexAVL; 

            public InvertedIndexAVL() {
                invertedindexAVL = new AVL <String,Record>();
            }

            public int size()
            {
               return invertedindexAVL.size();
            }
                
            public boolean addNew (int docID, String word)
            {
               if (invertedindexAVL.empty())
               {
                   Record t = new Record ();
                   t.setword(new Words (word));
                   t.add_docID(docID);
                   invertedindexAVL.insert(word, t);
                   return true;
               }
               else
               {
                    if (invertedindexAVL.find(word))
                    {
                        Record t = invertedindexAVL.retrieve();
                        t.add_docID(docID);
                        invertedindexAVL.update(t);
                        return false;   
                    }Record t = new Record ();
                   t.setword(new Words (word));
                   t.add_docID(docID);
                   invertedindexAVL.insert(word, t);
                    return true;
           }
        }

        public boolean find(String word)
        {return invertedindexAVL.find(word); }
        
        public void printDocument()
        { invertedindexAVL.Traverse();}
      
              public boolean [] AndORFMethod(String str ){
                
            if (! str.contains(" OR ") && ! str.contains(" AND "))
            {
                boolean [] r1 = new boolean[50];
                str = str.toLowerCase().trim();
            
                if (this.find(str))
                    r1 =  this.invertedindexAVL.retrieve().getDocs();
                return r1;
            }
            
            else if (str.contains(" OR ") && str.contains(" AND "))
            {
                String [] AND_ORs = str.split(" OR ");
                boolean []  r1 = ANDMethod (AND_ORs[0]);
               
                for ( int i = 1 ; i < AND_ORs.length ; i++  )
                {   
                    boolean [] r2 =ANDMethod(AND_ORs[i]);
                    
                    for ( int j = 0 ; j < 50 ; j++ )
                        r1 [j] = r1[j] || r2[j];
                }
                return r1;
            }
            
            else  if (str.contains(" AND "))
                return ANDMethod(str);
            
            return ORMethod (str);     
        }
        public boolean [] ANDMethod (String str)
        {   
            String [] ANDs = str.split(" AND ");
            boolean [] b1 = new boolean [50];
            
            if (this.find (ANDs[0].toLowerCase().trim()))
                b1 = this.invertedindexAVL.retrieve().getDocs();
            for ( int i = 1 ; i< ANDs.length ; i++){
                boolean [] b2 = new boolean [50];
                if (this.find (ANDs[i].toLowerCase().trim()))
                    b2 = this.invertedindexAVL.retrieve().getDocs();
                for ( int j = 0 ; j < 50 ; j++)
                    b1 [j] = b1[j] && b2[j];}
            return b1;}
        
        public boolean [] ORMethod (String str)
        {     
             String [] ORs = str.split(" OR ");
            boolean [] b1 = new boolean [50];
              if (this.find (ORs[0].toLowerCase().trim()))
                b1 = this.invertedindexAVL.retrieve().getDocs();
            for ( int i = 1 ; i< ORs.length ; i++)
            {boolean [] b2 = new boolean [50];
                if (this.find (ORs[i].toLowerCase().trim()))
                    b2 = this.invertedindexAVL.retrieve().getDocs();
                for ( int j = 0 ; j < 50 ; j++)
                    b1 [j] = b1[j] || b2[j]; }
            return b1; 
        }
}   

