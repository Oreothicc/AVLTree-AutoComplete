import java.util.*;

public class Main {
    public static void main(String args[]){
        Scanner sc=new Scanner(System.in);
        Solution s=new Solution();
        BSTNode root=null;
        while(sc.hasNextInt()){
            root=s.insert(root,sc.nextInt());
        }
        System.out.println("DONE");
    }
}

class BSTNode{
    int data,h;
    BSTNode left,right;
    BSTNode(int data){
        this.data=data;h=1;
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
        updateHeight(root);

        if(getBF(root)==2){
            if(getBF(root.left)==1){
                System.out.println("LL");
            }
            else
                {System.out.println("LR");}
        }
        else if(getBF(root)==-2){
            if(getBF(root.right)==1){
                System.out.println("RL");
            }
            else
                {System.out.println("RR");}
        }
        return root;
    }

    public void updateHeight(BSTNode root){
        root.h=1+Math.max (root.left==null?0:root.left.h,
                           root.right==null?0:root.right.h);
    }

    public int getBF(BSTNode root){
        return ((root.left==null?0:root.left.h)-(root.right==null?0:root.right.h));
    }

    public void inorder(BSTNode root){
        if(root==null){return;}
        inorder(root.left);
        System.out.print(root.data+":"+root.h+" ");
        inorder(root.right);
        
    }
}


