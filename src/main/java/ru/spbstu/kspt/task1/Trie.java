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

    public boolean add(String s) {
        Node lastNode = root;
        for (int i = 0; i < s.length(); i++) {
            lastNode = lastNode.addNode(s.charAt(i), i == s.length() - 1);
        }

        return lastNode.isEnd() && lastNode.children.size() == 0;
    }

    public boolean isPresent(String s) {
        Node lastNode = root;

        for (int i = 0; i < s.length(); i++) {
            if (lastNode != null) {
                lastNode = lastNode.get(s.charAt(i));
            } else
                return false;
        }

        return lastNode != null && lastNode.isEnd();
    }

    public String[] findSubstringByPrefix(String s) {
        char[] prefix = s.toCharArray();
        Node lastNode = root;
        StringBuilder builder = new StringBuilder();
        for (int i = 0; i < s.length(); i++) {
            if (lastNode.get(s.charAt(i)) == null)
                break;
            builder.append(s.charAt(i));
            lastNode = lastNode.get(s.charAt(i));
        }

        if (!s.equals(builder.toString()))
            return null;

        List<String> substring = new ArrayList<>();
        String[] strings = getLevel(lastNode).split(", ");
        return strings;
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
                stack.push(finder);
                finder = finder.get(c);
            } else return false;
        for (int i = s.length() - 1; i >= 0; i--) {
            Node deleter = stack.pop();
            if (!deleter.removeNode(s.charAt(i)))
                return false;
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
            if (children.containsKey(c)) {
                if (isLast) {
                    children.get(c).setEnd(isLast);
                }
                return children.get(c);
            }
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
            } else {
                children.get(c).setEnd(false);
                return false;
            }
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
