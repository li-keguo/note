package exec.leetcode.ring;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Objects;
import java.util.Set;

/**
 * Main.
 *
 * @author <a href='mailto:likeguo@apache.org'> likeguo </a>
 */
public class Main {
    public static void main(String[] args) {
        final Node a = new Node("A");
        final Node b = new Node("B");
        a.add(b);
        final Node c = new Node("C");
        b.add(c);
        final Node d = new Node("D");
        c.add(d);
        final Node e = new Node("E");
        d.add(e);
        
        e.add(a);
        
        c.add(e);
        c.add(a);
        
        print(a, a, 0, new HashSet<>());
        for (List<String> s : results) {
            System.out.println(s);
        }
    }
    
    static int i = 0;
    
    static List<List<String>> results = new ArrayList<>();
    
    public static Set<Integer> print(Node node, Node firstnode, int dep, Set<Node> route) {
        if (node.child.isEmpty()) {
            return null;
        }
        if ((dep > 0 && node == firstnode)) {
            final ArrayList<String> list = new ArrayList<>();
            list.add(node.vale);
            results.add(list);
            Set<Integer> set = new HashSet<>();
            set.add(i);
            i++;
            return set;
        }
        if (route.contains(node) && node != firstnode) {
            return null;
        }
        route.add(node);
        Set<Integer> set = new HashSet<>();
        for (Node n : node.child) {
            final Set<Integer> s = print(n, firstnode, dep + 1, route);
            if (s == null) {
                continue;
            }
            set.addAll(s);
        }
        for (Integer se : set) {
            results.get(se).add(node.vale);
        }
        return set;
    }
}

class Node {
    String vale;
    
    List<Node> child;
    
    public Node(String vale) {
        this.vale = vale;
        child = new ArrayList<>();
    }
    
    public void add(Node node) {
        child.add(node);
    }
    
    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Node node = (Node) o;
        return vale.equals(node.vale);
    }
    
    @Override
    public int hashCode() {
        return Objects.hash(vale);
    }
}
