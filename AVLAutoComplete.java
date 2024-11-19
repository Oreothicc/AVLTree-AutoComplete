import java.util.*;

class BSTNode {
    String productName;
    int height;
    BSTNode left, right;

    BSTNode(String productName) {
        this.productName = productName;
        this.height = 1;
    }
}

class Solutions {


    public BSTNode insert(BSTNode node, String productName) {
        if (node == null) {
            return new BSTNode(productName);
        }

        if (productName.compareTo(node.productName) < 0) {
            node.left = insert(node.left, productName);
        } else if (productName.compareTo(node.productName) > 0) {
            node.right = insert(node.right, productName);
        } else {
            return node;
        }

        updateHeight(node);
        return balance(node);
    }

    private void updateHeight(BSTNode node) {
        node.height = 1 + Math.max(
                (node.left != null ? node.left.height : 0),
                (node.right != null ? node.right.height : 0)
        );
    }

    private int getBF(BSTNode node) {
        return (node.left != null ? node.left.height : 0) -
               (node.right != null ? node.right.height : 0);
    }

    private BSTNode balance(BSTNode node) {
        int balance = getBF(node);

        // Left heavy
        if (balance > 1) {
            if (getBF(node.left) < 0) {
                node.left = leftRotate(node.left);
            }
            return rightRotate(node);
        }
        if (balance < -1) {
            if (getBF(node.right) > 0) {
                node.right = rightRotate(node.right);
            }
            return leftRotate(node);
        }

        return node;
    }

    private  BSTNode leftRotate(BSTNode y) {
        BSTNode x = y.right;
        BSTNode T = x.left;

        x.left = y;
        y.right = T;

        updateHeight(y);
        updateHeight(x);

        return x;
    }

    private BSTNode rightRotate(BSTNode y) {
        BSTNode x = y.left;
        BSTNode T = x.right;

        x.right = y;
        y.left = T;

        updateHeight(y);
        updateHeight(x);

        return x;
    }

    public List<String> autoComplete(String prefix,BSTNode root) {
        List<String> results = new ArrayList<>();
        searchPrefix(root, prefix.toLowerCase(), results);
        return results;
    }

    private void searchPrefix(BSTNode node, String prefix, List<String> results) {
        if (node == null) return;
    
        if (node.productName.toLowerCase().startsWith(prefix)) {
            results.add(node.productName);
        }
    
        if (prefix.compareToIgnoreCase(node.productName) <= 0) {
            searchPrefix(node.left, prefix, results);
        }
        if (prefix.compareToIgnoreCase(node.productName) >= 0) {
            searchPrefix(node.right, prefix, results);
        }
    }
    
}


public class AVLAutoComplete {
    public static void main(String[] args) {
        BSTNode root=null;
        Solutions sol = new Solutions();
        List<String> products=Arrays.asList("Animal Farm","Alice in Wonderland",
        "All The Light We Cannot See","All the Dangerous Things","All Your Twisted Lies",
        "Beach Read","Behind Her Eyes","Catching Fire","Catch-22"
        ,"Charlie and The Chocolate Factory","Charotte's Web","City of Glass","City of Bones",
        "Dinosaurs Before Dark","Death Cure","Death's End"
        ,"Ender's Game",
        "Good Girl, Bad Blood","Good Girl's Guide to Murder"
        ,"Harry Potter and The Philosopher's Stone","Harry Potter and The Chamber Of Secrets",
        "Harry Potter and The Prisoner of Azkaban","Harry Potter and the Deathly Hallows",
        "Harry Potter and The Half-Blood Prince","Harry Potter and The Goblet Of Fire",
        "If I stay","Insurgent",
        "Kite Runner",
        "Last Mile","Last Time I Lied","Little Prince","Little Woman",
        "Murder of Roger Ackroyd","Murder On The Orient Express",
        "Normal People","November Nine",
        "One of Us is Lying","One of Us is Next",
        "Polar Express",
        "Queen of Nothing",
        "Ready Player One", "Red Queen", "Red White Royal-Blue",
        "Sin Eater's Daughter","Sinner",
        "Tuesdays with Morrie","Turtles All The Way Down",
        "Unhoneymooners",
        "Verity",
        "Where The Crawdads Sing","Wind in the Willows", "Wrong Place Wrong Time","Wrong Family",
        "You Deserve Each Other",
        "Zero Day"
        );
        
        for(String s:products)
        {root = sol.insert(root, s);}
       Scanner sc=new Scanner(System.in);
       while(true){
        
       System.out.println("Enter book you are looking for or 'STOP' to quit:");
        String get=sc.nextLine();
        if(get.equals("STOP")){
            break;
        }
        List<String> res=sol.autoComplete(get, root);
        if(res.size()==0){
            System.out.println("Sorry, we couldn't find what you're looking for :(");
        }
        else{
            System.out.println(res);
        }
       }
       sc.close();

    }
}
