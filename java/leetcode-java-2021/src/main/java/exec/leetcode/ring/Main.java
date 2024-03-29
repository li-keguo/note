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
        planing(a);
        planing(b);
        planing(c);
        planing(d);
        planing(e);
        
    }
    
    private static void planing(Node a) {
        final Plan plan = new Plan();
        plan.plan(a, a, 0, new HashSet<>());
        System.out.printf(" 从 %s 开始规划\n", a.vale);
        for (List<String> s : plan.results) {
            System.out.println(s);
        }
        System.out.println();
    }
    
    
}

class Plan {
    int resultSize = 0;
    
    List<List<String>> results = new ArrayList<>();
    
    public Set<Integer> plan(Node node, Node firstNode, int dep, Set<Node> router) {
        if (node.child.isEmpty()) {
            return null;
        }
        if ((dep > 0 && node == firstNode)) {
            // 完整环
            final ArrayList<String> list = new ArrayList<>();
            list.add(node.vale);
            results.add(list);
            Set<Integer> set = new HashSet<>();
            set.add(resultSize);
            resultSize++;
            return set;
        }
        if (router.contains(node) && node != firstNode) {
            // 中间环
            return null;
        }
        
        router.add(node);
        Set<Integer> set = new HashSet<>();
        for (Node n : node.child) {
            final Set<Integer> s = plan(n, firstNode, dep + 1, router);
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
