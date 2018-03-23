package ru.spbstu.kspt.task1;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class TrieTest {

    @Test
    void addNode() {
        Trie tree = new Trie();
        tree.add("a");
        tree.add("b");
        assertTrue(tree.isPresent("a"));
        assertTrue(tree.isPresent("b"));
    }

    @Test
    void removeNode() {
        Trie tree = new Trie();
        tree.add("a");
        tree.add("b");
        tree.remove("a");
        assertTrue(tree.isPresent("b"));
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
        String result = "goodmorning, goodnight";
        assertEquals(result, tree.findSubstringByPrefix("good"));
    }

}