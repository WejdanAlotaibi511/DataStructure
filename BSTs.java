public class BSTs<K extends Comparable<K>, T>{
   class BSTNode<K extends Comparable<K>, T> {
        public K key;
        public T data;
        public BSTNode<K,T> left, right;

        public BSTNode(K k, T val) {
                key = k;
                data = val;
                left = right = null;
        }

        public BSTNode(K k, T val, BSTNode<K,T> l, BSTNode<K,T> r) {
                key = k;
                data = val;
                left = l;
                right = r;
        }
    }
    
        BSTNode<K, T> root; 
        BSTNode<K, T > curr;
        int count ;
                
        public BSTs()
        {
            root = curr = null;
            count = 0;
        }
        
        public int size()
        {
            return count;
        }

        public boolean empty()
        {
            return root == null;
        }

        public void clear()
        {
            root = curr = null;
            count = 0;
        }

        public T retrieve()
        {
            T data =null;
            if (curr != null)
                data = curr.data;
            return data;
        }

        public void update(T e)
        {
            if (curr != null)
                curr.data = e;
        }

                public boolean findKey(K key)
        {
            BSTNode<K,T> p = root;

            if(empty())
                    return false;

            while(p != null) {
                    if(p.key.compareTo(key) == 0) {
                            curr = p;
                            return true;
                    }
                    else if(key.compareTo(p.key) < 0)
                            p = p.left;
                    else
                            p = p.right;
            }
            return false;
        }


               public boolean insert(K key, T data)
        {

            if(empty())
            {
                curr = root = new BSTNode <K, T> ( key, data);
                count ++;
                return true;
            }
            BSTNode<K,T> par = null;
            BSTNode<K,T> child  = root;
            
            while(child != null) {
                    if(child.key.compareTo(key) == 0) {
                            return false;
                    }
                    else if(key.compareTo(child.key) < 0)
                    {
                        par = child;
                        child = child.left;
                    }
                    else
                    {
                        par = child;
                        child = child.right;
                    }
            }
           
            if(key.compareTo(par.key) < 0)
            {
                par.left = new BSTNode <K, T> ( key, data);
                curr = par.left;
            }
            
            else
            {
                par.right = new BSTNode <K, T> ( key, data);
                curr = par.right;
            }
            count ++;
            return true;
        }

               public boolean remove(K key)
        {
            Boolean removed = new Boolean(false);
            BSTNode<K,T> p;
            
            p = remove_aux(key, root, removed);
            root = p;
            
            if (curr.key.compareTo(key) == 0)
                curr = root;
            if (removed)
                count -- ;
            
            return removed;
        }
    
        private BSTNode<K,T> remove_aux(K key, BSTNode<K,T> p, boolean flag) 
        {
            BSTNode<K,T> q, child = null;
            if(p == null)
                    return null;
            if(key.compareTo(p.key ) < 0)
                    p.left = remove_aux(key, p.left, flag); 
            else if(key.compareTo(p.key) > 0)
                    p.right = remove_aux(key, p.right, flag); 
            else {
                    
                    flag = true;
                    if (p.left != null && p.right != null)
                    { 
                            q = find_min(p.right);
                            p.key = q.key;
                            p.data = q.data;
                            p.right = remove_aux(q.key, p.right, flag);
                    }
                    else 
                    {
                            if (p.right == null) 
                                    child = p.left;
                            else if (p.left == null)                                     child = p.right;
                            return child;
                    }
                }
            return p;
        }
        private BSTNode<K,T> find_min(BSTNode<K,T> p)
        {
            if(p == null)
                    return null;

            while(p.left != null){
                    p = p.left;
            }
            return p;
        }
        
        public void Traverse()
        {
            if (root != null)
                traverseTree(root);
        }
        
        private void traverseTree (BSTNode<K,T> node  )
        {
            if (node == null)
                return;
            traverseTree( node.left);
            System.out.println(node.data);
            traverseTree( node.right);
            
        }

       public void TraverseT()
        {
            if (root != null)
                traverseTreeT(root);
        }
        
        private void traverseTreeT (BSTNode<K,T> node)
        {
            if (node == null)
                return;
            traverseTreeT( node.left );
            if (node.data instanceof BSTs )
            {
                System.out.println( "Node key ==== "+ node.key);
                ((BSTs <String,Ranking>) node.data).Traverse();
            }
            else
                System.out.println(node.data);
            
            traverseTreeT( node.right);
            
        }
       public void PrintData()
        {
            if (root != null)
                PrintData_(root);
        }
        
        private void PrintData_ (BSTNode<K,T> node)
        {
            if (node == null)
                return;
            PrintData_( node.left );
           
           System.out.print(node.key);
            if (node.data instanceof Record )
            {
                System.out.print("   docs: ");
                boolean [] docs = ((Record) node.data).getDocs();
                for ( int i  = 0 ; i < 50 ; i++)
                    if ( docs[i])
                        System.out.print( " " + i + " " );
                System.out.println("");
            }
            PrintData_( node.right);
        }
}
