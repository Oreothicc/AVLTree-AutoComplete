# AVL TREE AUTOCOMPLETE IMPLEMENTATION

## About The Project
AVL AutoComplete -Search Optimization Using AVL Trees | Java
•Implemented an AVL tree data structure from scratch with search operations within O(logn) time complexity with rotations for LL, RR, LR, RL imbalances and worst case time complexity of O(prefix length+logn).
•Employed object oriented principles, populated the AVL tree with a dataset of over 50+ product names to simulate a real-world search engine scenario, tested and validated implementation.

## BST and its time complexity
Binary Search Trees or BSTs are a data structure used to store data items following a particular set of rules. A tree is a non-linear data structure that is used to store and represent data in a way that is easy to navigate and search. It is called non-linear because instead of being stored in a contiguous manner like an array or a list, there is a defined hierarchy.

![Tree_(computer_science) svg](https://github.com/user-attachments/assets/5619de46-8c8d-4cf0-86eb-65ba395fb36e)

The first node is the "ROOT" and it points to its children. If a tree has only 2 children - left and right such that the left child is always lesser than the parent and the right child is always greater then it is classified it as a BST because the idea of binary search can be used here (breaking the searching problem into either left or right).

![BST](https://github.com/user-attachments/assets/7460cd99-9bd7-4103-add8-55bdcdc4d1d0)

This is done by checking for the conditions above every time a node is inserted into the tree- if it is greater than the root, it is inserted into the right side or else the left side. Recursion is used to simplify this process:


``` 
public BSTNode insert(BSTNode root, int x){
        if(root==null)
        {return new BSTNode(x);}
        if(x>root.data)
        {
            root.right=insert(root.right,x);
        }
        else{
            root.left=insert(root.left,x);
        }
        return root;
    }
```

### Time Complexity:
On average the insertion, deletion, and search process of BST are O(logn)
In the case of skewed trees, this turns into O(n) because it turns into a linked-list essentially. 

## Need for self balancing trees
This can be seen for a tree like-  1,2,3,4 which is entirely right skewed.
![skewed1](https://github.com/user-attachments/assets/2609de85-643a-4fec-b001-7ec3b12a0c16)

Here because the tree is completely unbalanced, the time complexity dissolves into O(n). This can be combatted by always balancing the tree or by creating some sort of tree data structure that is self-balancing. If we are able to do this, we can always have a time complexity of O(logn) for all searches.

## AVL trees: introduction and imbalances
Considering this need, AVL trees were created. AVL trees constantly balance the tree and they do this by calculating a balance factor.

### Balance factor = height(left sub-tree)- height(right sub-tree)

If BF is -1,0,1 it is balanced or else the tree must be balanced via rotations.


![insertion-in-avl-trees-1-1648705027](https://github.com/user-attachments/assets/6da15831-126a-45e1-8a12-cfc14c5c387f)

## Subcases of imbalance (LL,LR,RL,RR)
To check imbalances, the tree is rotated once it BF is -2 or 2 which shows left or right imbalance. 
This is an LL imbalanced tree:

![LL_Rotations](https://github.com/user-attachments/assets/d55e6f11-2aef-41d9-9b0f-6111d40cffda)

This is an LR imbalanced tree:

![LR_Rotation](https://github.com/user-attachments/assets/e7875e6c-a312-47d9-b704-6c619d9cd2b0)

This is a RR imbalance tree:

![RR_Rotations](https://github.com/user-attachments/assets/6b40239c-8d57-49d6-8c62-f1c0d7033cb9)

This is a RL imbalance tree:

![RL_Rotations](https://github.com/user-attachments/assets/1adc2102-6153-4e09-8790-1eec3dc30c5b)

The different cases of RR, LL, RL, LR can be checked like this:

``` if(getBF(root)==2){
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
   public int getBF(BSTNode root){
        return ((root.left==null?0:root.left.h)-(root.right==null?0:root.right.h));
    }
```

## How to Balance Each Case

Left rotate:
When left rotation is done the tree is right skewed so the new root will be the right child of the previous root. It is useful to imagine subtrees of each node to simplify this process.
Assume the example of 1-2-3, this is a completely right skewed tree. 

Assume:
1: left subtree=t1 (null), right subtree=2-3
2: left subtree=t2 (null), right subtree=3
3: left subtree=t3 (null), right subtree=t4 (null)

Now if this is left rotated- 2 becomes the new root such that:
2: left subtree=1, right subtree=3
1: left subtree=t1, right subtree=t2
3: left subtree=t3, right subtree=t4

```  public BSTNode leftRotate(BSTNode root){
          BSTNode newroot=root.right;
          BSTNode temp=newroot.left;
          root.right=temp;
          updateHeight(root);
          newroot.left=root;
          updateHeight(newroot);
          return newroot;
      }
  ```


Right rotate:
This similar process is done for right rotation. 

```
    public BSTNode rightRotate(BSTNode root){
        BSTNode newroot=root.left;
        BSTNode temp=newroot.right;
        root.left=temp;
        updateHeight(root);
        newroot.right=root;
        updateHeight(newroot);
        return newroot;

    }
  ```

For LR or RL:
LR: left rotate + right rotate
RL: right rotate + left rotate

## AVL vs RBTrees
