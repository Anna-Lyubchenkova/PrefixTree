package ru.spbstu.kspt.task1;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class TrieTest {

    @Test
    void addNode() {
        Trie tree = new Trie();
        tree.add("a");
        tree.add("b");
        Trie.Node examplee = new Trie.Node(false);
        examplee.addNode('a', true);
        examplee.addNode('b', true);
        assertEquals(examplee.toString(), tree.toString());
    }

    @Test
    void removeNode() {
        Trie tree = new Trie();
        tree.add("a");
        tree.add("b");
        tree.remove("a");
        Trie.Node examplee = new Trie.Node(false);
        examplee.addNode('a', true);
        examplee.addNode('b', true);
        examplee.removeNode('a');
        assertEquals(examplee.toString(), tree.toString());
    }

    @Test
    void add() {
        Trie tree = new Trie();
        tree.add("helloy");
        tree.add("ell");
        tree.add("goodnight");
        tree.add("goodmorning");
        tree.add("helmet");
        assertTrue(tree.isPresent("helloy"));
        assertFalse(tree.isPresent("helloll"));
        assertFalse(tree.isPresent("gum"));
        assertTrue(tree.isPresent("helloy"));
    }

    @Test
    void isPresent() {
        Trie tree = new Trie();
        tree.add("helloy");
        tree.add("hell");
        tree.add("goodnight");
        tree.add("goodmorning");
        tree.add("helmet");
        assertEquals(false, tree.isPresent("goodmo"));
        assertEquals(false, tree.isPresent("helmost"));
        assertEquals(true, tree.isPresent("helloy"));
    }

    @Test
    void remove() {
        Trie tree = new Trie();
        tree.add("helloy");
        tree.add("hell");
        tree.add("goodnight");
        tree.add("goodmorning");
        tree.add("helmet");
        tree.remove("helmet");
        assertTrue(tree.isPresent("helloy"));
        assertFalse(tree.isPresent("helmet"));
        assertFalse(tree.isPresent("gum"));
        assertTrue(tree.isPresent("helloy"));
    }

    @Test
    void findSubstringByPrefix() {
        Trie tree = new Trie();
        tree.add("helloy");
        tree.add("hell");
        tree.add("goodnight");
        tree.add("goodmorning");
        tree.add("helmet");
        Trie subTrie = new Trie();
        subTrie.add("night");
        subTrie.add("morning");
        assertEquals(subTrie.toString(), tree.findSubstringByPrefix("good"));

    }

}