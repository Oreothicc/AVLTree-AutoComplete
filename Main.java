import java.util.*;
import java.io.PrintStream;

public class Main {
    public static void main(String args[]){
        Scanner sc=new Scanner(System.in);
        Solution s=new Solution();
        BSTVisualizer visualizer = new BSTVisualizer();
        BSTNode root=null;
        while(sc.hasNextInt()){
            root=s.insert(root,sc.nextInt());
            visualizer.printBST(root);
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
                root=rightRotate(root);
            }
            else
                {//System.out.println("LR");}
                root.left=leftRotate(root.left);
                root=rightRotate(root);
                }
        }
        else if(getBF(root)==-2){
            if(getBF(root.right)==1){
                //RL
                root.right=rightRotate(root.right);
                root=leftRotate(root);
            }
            else
                {   //RR
                    root=leftRotate(root);}
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

    public BSTNode leftRotate(BSTNode root){
        BSTNode newroot=root.right;
        BSTNode temp=newroot.left;
        root.right=temp;
        updateHeight(root);
        newroot.left=root;
        updateHeight(newroot);
        return newroot;

    }

    public BSTNode rightRotate(BSTNode root){
        BSTNode newroot=root.left;
        BSTNode temp=newroot.right;
        root.left=temp;
        updateHeight(root);
        newroot.right=root;
        updateHeight(newroot);
        return newroot;

    }

}