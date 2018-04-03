package ru.spbstu.kspt.task1;

import org.junit.jupiter.api.Test;

import java.util.StringJoiner;

import static org.junit.jupiter.api.Assertions.*;

class TrieTest {

    @Test
    void addNode() {
        Trie tree = new Trie();
        tree.add("alala");
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
        assertFalse(tree.isPresent("a"));
        assertTrue(tree.isPresent("b"));
    }

    @Test
    void add() {
        Trie tree = new Trie();
        tree.add("helloy");
        tree.add("ell");
        tree.add("hell");
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
        assertEquals(true, tree.isPresent("hell"));
        assertEquals(false, tree.isPresent("helloye"));
    }

    @Test
    void remove() {
        Trie tree = new Trie();
        tree.add("helloy");
        tree.add("hell");
        tree.remove("helloy");
        assertTrue(tree.isPresent("hell"));
        assertFalse(tree.isPresent("helmet"));
        assertFalse(tree.isPresent("gum"));
        assertFalse(tree.isPresent("helloy"));
        tree.add("helloy");
        tree.remove("hell");
        assertTrue(tree.isPresent("helloy"));
        assertFalse(tree.isPresent("hell"));
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
        String prefix = "good";
        String[] strings = tree.findSubstringByPrefix(prefix);
        StringJoiner joiner = new StringJoiner(", ");
        for (int i = 0; i < strings.length; i++) {
            strings[i] = prefix + strings[i];
            joiner.add(strings[i]);
        }
        assertEquals(result, joiner.toString());
    }

}