import java.util.NoSuchElementException;


public class AVL<K extends Comparable<K>, T>{

              class AVLNode<K extends Comparable<K>, T> {
                public K key;
                public T data;
                AVLNode<K,T> ps; 
                AVLNode<K,T> left; 
                AVLNode<K,T> right; 
                int balance; 

                    public AVLNode() {
                            this.key = null;  
                            this.data = null;
                            this.ps = this.left = this.right = null;
                            this.balance = 0;
                    }

                    public AVLNode(K key, T data) {
                            this.key = key  ;  
                            this.data = data;
                            this.ps = this.left = this.right = null;
                            this.balance = 0;
                    }

                    public AVLNode(K key, T data, AVLNode<K,T> p, AVLNode<K,T> l, AVLNode<K,T> r){
                            this.key = key  ;  
                            this.data = data;
                            left = l;
                            right = r;
                            ps = p;
                            balance =0;
                    }

                    public AVLNode<K,T> getLeft()
                    {
                        return left;
                    }

                    public AVLNode<K,T> getRight()
                    {
                        return right;
                    }

                    public T getData()
                    {
                        return data;
                    }
                    
                    @Override
                    public String toString() {
                        return "The node of AVL (" + "key=" + key + ", data =" + data + ')';
                    }
            }

        private AVLNode<K,T> root;
        private AVLNode<K,T>  current;
        private int count;
        
        public AVL() {
                root = current = null;
                count = 0;
        }
        
        public void clear()
        {
            root = current = null;
            count = 0;
        }


        public boolean empty() {
            return root == null;
        }

        public int size() {
            return count;
        }


         public T retrieve()
        {
            T data =null;
            if (current != null)
                data = current.data;
            return data;
        }

        public void update(T e)
        {
            if (current != null)
                current.data = e;
        }

        private T searchTree(AVLNode<K,T> node, K key) {
                    if (node == null)
                        return null;
                    else if (node.key.compareTo(key) ==0) 
                    {
                        current = node;
                        return node.data;
                    } 
                    else if (node.key.compareTo(key) >0)
                         return searchTree(node.left, key);
                    else
                         return searchTree(node.right, key);
        }
        
        private void updatesBalance(AVLNode<K,T> node) {
                if (node.balance < -1 || node.balance > 1) {
                        rebalance(node);
                        return;
                }

                if (node.ps != null) {
                        if (node == node.ps.left) {
                                node.ps.balance -= 1;
                        } 

                        if (node == node.ps.right) {
                                node.ps.balance += 1;
                        }

                        if (node.ps.balance != 0) {
                                updatesBalance(node.ps);
                        }
                }
        }

        void rebalance(AVLNode<K,T> node) {
                if (node.balance > 0) {
                        if (node.right.balance < 0) {
                                rightRotate(node.right);
                                leftRotate(node);
                        } else {
                                leftRotate(node);
                        }
                } else if (node.balance < 0) {
                        if (node.left.balance > 0) {
                                leftRotate(node.left);
                                rightRotate(node);
                        } else {
                                rightRotate(node);
                        }
                }
        }

        public boolean find(K key) {
                T data = searchTree(this.root, key);
                if ( data != null)
                    return true;
                return false;
        }

        void leftRotate(AVLNode<K,T> x) {
            AVLNode<K,T> y = x.right;
            x.right = y.left;
            if (y.left != null) {
                    y.left.ps = x;
            }
            
            y.ps = x.ps;
            if (x.ps == null) {
                    this.root = y;
            } else if (x == x.ps.left) {
                    x.ps.left = y;
            } else {
                    x.ps.right = y;
            }
            y.left = x;
            x.ps = y;

            x.balance = x.balance - 1 - Math.max(0, y.balance);
            y.balance = y.balance - 1 + Math.min(0, x.balance);
        }

        void rightRotate(AVLNode<K,T> x) {
                AVLNode<K,T> y = x.left;
                x.left = y.right;
                if (y.right != null) {
                        y.right.ps = x;
                }
                y.ps = x.ps;
                if (x.ps == null) {
                        this.root = y;
                } else if (x == x.ps.right) {
                        x.ps.right = y;
                } else {
                        x.ps.left = y;
                }
                y.right = x;
                x.ps = y;

                x.balance = x.balance + 1 - Math.min(0, y.balance);
                y.balance = y.balance + 1 + Math.max(0, x.balance);
        }

        
        
        public boolean insert(K key, T data) {
            AVLNode<K,T> node = new AVLNode<K,T>(key, data);

            AVLNode<K,T> p = null;
            AVLNode<K,T> c = this.root;

            while (c != null) {
                    p = c;
                    if (node.key.compareTo(c.key) ==0) {
                            return false;
                    }else if (node.key.compareTo(c.key) <0 ) {
                            c = c.left;
                    } else {
                            c = c.right;
                    }
            }
            node.ps = p;
            if (p == null) {
                    root = node;
                    current = node;
            } else if (node.key.compareTo(p.key) < 0 ) {
                    p.left = node;
            } else {
                    p.right = node;
            }
            count ++;

            updatesBalance(node);
            return true;        
        }
        
    public boolean remove(K key) {
        K k1 = key;
        AVLNode<K,T>  p = root;
        AVLNode<K,T>  q = null; 
         while (p != null) 
        {
                if (k1.compareTo(p.key) <0)
                {
                    q =p;
                    p = p.left;
                } 
                else if (k1.compareTo(p.key) >0)
                {    
                    q = p;
                    p = p.right;
                }
                else 
                {                     
                    if ((p.left != null) && (p.right != null)) 
                    { 
                        AVLNode<K,T> min = p.right;
                        q = p;
                        while (min.left != null) 
                        {
                            q = min;
                            min = min.left;
                        }
                        p.key = min.key;
                        p.data = min.data;
                        k1 = min.key;
                        p = min;
                    }
                    if (p.left != null) 
                    { 
                        p = p.left;
                    } 
                    else 
                    { 
                        p = p.right;
                    }
                    if (q == null)
                    { 
                        root = p;
                        this.updatesBalance(p);
                    } 
                    else 
                    {
                        if (k1.compareTo(q.key) <0)
                            q.left = p;
                        else 
                            q.right = p;
                        this.updatesBalance(q);
                    }
                    count--;
                    current = root;
                    return true;    
            } 
        } 
        return false;
    }
    
     private void traverseTree (AVLNode<K,T> node  )
        {
            if (node == null)
                return;
            traverseTree( node.left);
            System.out.println(node.data);
            traverseTree( node.right);
            
        }


       public void Traverse()
        {
            if (root != null)
                traverseTree(root);
        }
        
       
        
       public void TraverseT()
        {
            if (root != null)
                traverseTreeT(root);
        }
        
        private void traverseTreeT (AVLNode<K,T> node)
        {
            if (node == null)
                return;
            traverseTreeT( node.left );
            if (node.getData() instanceof AVL )
            {
                System.out.println( "Node key ==== "+ node.key);
                ((AVL <String,Ranking>) node.getData()).Traverse();
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
        
        private void PrintData_ (AVLNode<K,T> node)
        {
            if (node == null)
                return;
            PrintData_( node.left );
           
           System.out.print(node.key);
            if (node.getData() instanceof Record )
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
