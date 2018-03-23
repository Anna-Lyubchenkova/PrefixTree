package ru.spbstu.kspt.task1;


import java.lang.reflect.Array;
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
        StringBuffer buffer = new StringBuffer();
        for (int i = 0; i < s.length(); i++) {
            if (lastNode.get(s.charAt(i)) == null)
                break;
            buffer.append(s.charAt(i));
            lastNode = lastNode.get(s.charAt(i));
        }

        if (!s.equals(buffer.toString()))
            return "NOT_FOUND";

        List<String> substring = new ArrayList<>();

        String[] strings = getLevel(lastNode).split(", ");
        StringJoiner joiner = new StringJoiner(", ");
        for (int i = 0; i < strings.length; i++) {
            strings[i] = s + strings[i];
            joiner.add(strings[i]);
        }

        return joiner.toString();
    }

    private String getLevel(Node node) {
        String result = "";
        if (node.isEnd())
            return ", ";

        List<Character> chars = new ArrayList<>();
        chars.addAll(node.children.keySet());

        for (int i = 0; i < node.children.keySet().size(); i++) {
            result += node.children.keySet().toArray()[i];
            result += getLevel(node.children.get(chars.get(i)));
        }

        return result;
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


        for (int i = s.length() - 1; i >= 0; i--) {
            Node deleter = stack.removeFirst();
            if (!deleter.removeNode(s.charAt(i)))
                return true;
        }

        return true;
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

        public boolean isEnd() {
            return end;
        }

        public void setEnd(boolean end) {
            this.end = end;
        }
    }
}
