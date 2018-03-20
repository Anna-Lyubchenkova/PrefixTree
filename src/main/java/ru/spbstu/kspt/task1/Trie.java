package ru.spbstu.kspt.task1;


import java.util.*;

public class Trie {
    private Node root;

    public Trie() {
        this.root = new Node(false);
    }

    private Trie(Node newRoot) {
        this.root = newRoot;
    }

    public void add(String s) {
        Node lastNode = root;
        boolean end = true;
        for (int i = 0; i < s.length(); i++)
            lastNode = lastNode.addNode(s.charAt(i), i == s.length() - 1);
        //lastNode = lastNode.addNode(null);
    }

    public boolean isPresent(String s) {
        Node lastNode = root;

        for (int i = 0; i < s.length(); i++) {
            if (lastNode != null) {
                lastNode = lastNode.get(s.charAt(i));
            } else
                return i == s.length();
        }

        return lastNode.isEnd();
    }

    public String findSubstringByPrefix(String s) {
        char[] prefix = s.toCharArray();
        Node lastNode = root;
        // ArrayList v = new ArrayList();
        for (char c : prefix) {
            if (lastNode.get(c) == null)
                break;
            //v.add(c);
            lastNode = lastNode.get(c);

        }

        //System.out.println(v);

        Trie subTrie = new Trie(lastNode);
        //System.out.println(subTrie);
        return subTrie.toString();
    }

    public boolean remove(String s) {
        Deque<Node> stack = new LinkedList<>();

        char[] straight = s.toCharArray();

        Node finder = root;
        for (char c : straight)
            if (finder.get(c) != null) {
                stack.addFirst(finder);
                finder = finder.get(c);
            } else return false;

        /*StringBuilder builder = new StringBuilder(s);
        builder = builder.reverse();
        char[] reversed = builder.toString().toCharArray();

        for (char c : reversed) {
            Node deleter = stack.removeFirst();
            if (!deleter.removeNode(c))
                return true;
        }*/

        for (int i = s.length() - 1; i >= 0; i--) {
            Node deleter = stack.removeFirst();
            if (!deleter.removeNode(s.charAt(i)))
                return true;
        }

        return true;
    }

    @Override
    public String toString() {
        List<String> treeLevels = new ArrayList<>();

        Collection<Node> nexts = Collections.singletonList(root);
        while (true) {
            Collection<Node> newNexts = new ArrayList<>();
            StringJoiner joiner = new StringJoiner(" | ");
            for (Node node : nexts) {
                joiner.add(node.toString());
                newNexts.addAll(node.getAllPresent());
            }
            treeLevels.add(joiner.toString());

            if (newNexts.size() != 0)
                nexts = newNexts;
            else
                break;
        }

        treeLevels.remove(treeLevels.size() - 1);
        return String.join("\n", treeLevels);
    }

    static class Node {
        private Map<Character, Node> children = new HashMap<>();
        boolean end = false;

        public Node(boolean isEnd) {
            end = isEnd;
        }

        Node get(char c) {
            return children.get(c);
        }

        private Collection<Node> getAllPresent() {
            return children.values();
        }

        Node addNode(char c, boolean isLast) {
            if (children.containsKey(c))
                return children.get(c);
            else {
                Node node = new Node(isLast);
                children.put(c, node);
                return node;
            }
        }

        boolean removeNode(char c) {
            if (children.get(c).size() == 0) {
                children.remove(c);
                return true;
            } else
                return false;
        }

        private int size() {
            return children.size();
        }

        @Override
        public String toString() {
            StringJoiner joiner = new StringJoiner(" | ");
            children.keySet().forEach((c) -> joiner.add(c.toString()));
            return joiner.toString();
        }

        public boolean isEnd() {
            return end;
        }

        public void setEnd(boolean end) {
            this.end = end;
        }
    }
}
