import java.util.function.Function;

public class InvertedIndexRanked {
             
AVL <Integer, AVL <String,Ranking>> AVLrank; 
Frequency [] freqs = new Frequency[50];
            
            public InvertedIndexRanked() {
                AVLrank = new AVL <Integer, AVL <String,Ranking>>();
                
            }
            public boolean addNew(int docID, String word) {
                if (AVLrank.empty())
               {
                   AVL <String,Ranking> miniRank= new AVL <String,Ranking>();
                   miniRank.insert(word, new Ranking (word,1));
                   
                   AVLrank.insert(docID, miniRank);
                   return true;
               }
               else
               {
                    if (AVLrank.find(docID))
                    {
                        AVL <String,Ranking> miniRank= AVLrank.retrieve();
                        if (miniRank.find(word))
                        {
                            Ranking rank = miniRank.retrieve();
                            rank.addRanking();
                            miniRank.update(rank);
                            AVLrank.update(miniRank);
                            return false;
                        }
                        miniRank.insert(word, new Ranking (word , 1));
                        AVLrank.update(miniRank);
                        return true;
                    }
                   AVL <String,Ranking> miniRank= new AVL <String,Ranking>();
                   miniRank.insert(word, new Ranking (word,1));
                   
                   AVLrank.insert(docID, miniRank);
                   return true;
               }
        }
         public boolean found(int docID, String word) {
        if (AVLrank.find(docID))
            if (AVLrank.retrieve().find(word))
                return true;
        return false;
    }

    public int getRank(int docID, String word) {
        
           int value=0;
               if (AVLrank.find(docID) )
                  if (AVLrank.retrieve().find(word))
                      return AVLrank.retrieve().retrieve().getRank();
               return value;
        
           }
         
        public void printDocument()
        {
                AVLrank. TraverseT();
        }

        
        public void TF(String str) {
       str = str.toLowerCase().trim();
          String[] words = str.split(" ");

          int index = 0;
           for (int docID = 0; docID < 50; docID++) {
            int count = 0;
            for (String word : words) {
                count += this.getRank(docID, word);
            }
            if (count > 0) {
                freqs[index] = new Frequency(docID, count);
                index++;
            }
        }

        Joinsort(freqs, 0, index - 1);

        for (int x = 0; x < index; x++) {
            System.out.println(freqs[x].Position+ "\t\t" + freqs[x].f);
        } 
        }
     public void Joinsort(Frequency[] freq, int left, int right) {

        if (left >= right) return;
        int m = (left + right) / 2;
        Joinsort(freq, left, m);          
        Joinsort(freq, m + 1, right);            
         Join(freq, left, m, right);     }
        
         private void Join(Frequency[] freq, int left, int m, int right) {
      Frequency[] temp = new Frequency[right - left + 1];
        int i = left, j = m + 1, k = 0;

        while (i <= m && j <= right) {
            temp[k++] = (freq[i].getFrequency() >= freq[j].getFrequency()) ? freq[i++] : freq[j++];
        }
        while (i <= m) {
            temp[k++] = freq[i++];
        }
        while (j <= right) {
            temp[k++] = freq[j++];
        }

        System.arraycopy(temp, 0, freq, left, temp.length);
    }
    
}
        
        
        
        
    
       