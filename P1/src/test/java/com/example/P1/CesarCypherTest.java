package com.example.P1;

import static org.junit.Assert.*;

import org.junit.Test;

public class CesarCypherTest {

    @Test
    public void testWeak1() {
        assertEquals("BBB", CesarCypher.GenerateCypher("AAA", CypherDifficulty.SIMPLE));

    }

    @Test
    public void testWeak2() {
        assertEquals("BCD", CesarCypher.GenerateCypher("ABC", CypherDifficulty.SIMPLE));
    }

    @Test
    public void testWeak3() {
        assertEquals("BOB", CesarCypher.GenerateCypher("ANA", CypherDifficulty.SIMPLE));
    }

    @Test
    public void testWeak4() {
        assertEquals("bob", CesarCypher.GenerateCypher("ana", CypherDifficulty.SIMPLE));
    }

    @Test
    public void testMedium1() {
        assertEquals("EEE", CesarCypher.GenerateCypher("AAA", CypherDifficulty.MEDIUM));

    }

    @Test
    public void testMedium2() {
        assertEquals("EFG", CesarCypher.GenerateCypher("ABC", CypherDifficulty.MEDIUM));
    }

    @Test
    public void testMedium3() {
        assertEquals("efg", CesarCypher.GenerateCypher("abc", CypherDifficulty.MEDIUM));
    }

    @Test
    public void testMedium4() {
        assertEquals("neze", CesarCypher.GenerateCypher("java", CypherDifficulty.MEDIUM));
    }

    @Test
    public void testComplex1() {
        assertEquals("BBB", CesarCypher.GenerateCypher("AAA", CypherDifficulty.COMPLEX));
    }

    @Test
    public void testComplex2() {
        assertEquals("BCD", CesarCypher.GenerateCypher("ABC", CypherDifficulty.COMPLEX));
    }

    @Test
    public void testComplex3() {
        assertEquals("BOB", CesarCypher.GenerateCypher("ANA", CypherDifficulty.COMPLEX));
    }

    @Test
    public void testComplex4() {
        assertEquals("bob", CesarCypher.GenerateCypher("ana", CypherDifficulty.COMPLEX));
    }
}
