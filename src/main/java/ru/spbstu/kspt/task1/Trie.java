package ru.spbstu.kspt.task1;


import java.util.*;

public class Trie {
    private Node root;

    public Trie() {
        this.root = new Node(false);
    }

    public boolean add(String s) {
        Node lastNode = root;
        for (int i = 0; i < s.length(); i++) {
            lastNode = lastNode.addNode(s.charAt(i), i == s.length() - 1);
        }
        return lastNode.children.size() == 0;
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

    public Set<String> findStringByPrefix(String s) {
        Node lastNode = root;
        for (int i = 0; i < s.length(); i++) {
            if (lastNode.get(s.charAt(i)) == null)
                return null;
            lastNode = lastNode.get(s.charAt(i));
        }
        Set<String> res = new HashSet<>();
        getLevel(lastNode, res, new StringBuilder(s));
        return res;
    }

    private void getLevel(Node node, Set<String> res, StringBuilder prefix) {
        if (node.isEnd()) {
            res.add(prefix.toString());
        }
        for (Map.Entry<Character, Node> entry : node.children.entrySet()) {
            prefix.append(entry.getKey());
            getLevel(entry.getValue(), res, prefix);
            prefix.deleteCharAt(prefix.length() - 1);
        }
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
                return true;
            if (deleter.isEnd()) {
                break;
            }
        }
        return true;
    }

    static class Node {
        private Map<Character, Node> children = new HashMap<>();
        boolean end;

        public Node(boolean isEnd) {
            end = isEnd;
        }

        Node get(char c) {
            return children.get(c);
        }

        Node addNode(char c, boolean isLast) {
            if (children.containsKey(c)) {
                if (isLast) {
                    children.get(c).setEnd(isLast);
                }
                return children.get(c);
            } else {
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
