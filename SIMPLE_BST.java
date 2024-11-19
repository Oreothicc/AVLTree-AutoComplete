import java.util.*;

public class Main {
    public static void main(String args[]){
        Scanner sc=new Scanner(System.in);
        Solution s=new Solution();
        BSTNode root=null;
        root=s.insert(root,1);
        root=s.insert(root,3);
        root=s.insert(root,4);
        root=s.insert(root,6);
        s.inorder(root);
        System.out.println("DONE");
    }
}

class BSTNode{
    int data;
    BSTNode left,right;
    BSTNode(int data){
        this.data=data;
    }
}

class Solution{
    public BSTNode insert(BSTNode root, int x){
        if(root==null){return new BSTNode(x);}
        if(x>root.data){
            root.right=insert(root.right,x);
        }
        else{
            root.left=insert(root.left,x);
        }
        return root;
    }

    public void inorder(BSTNode root){
        if(root==null){return;}
        inorder(root.left);
        System.out.print(root.data+" ");
        inorder(root.right);
        
    }
}


