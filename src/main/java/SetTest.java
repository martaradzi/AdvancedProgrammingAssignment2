import org.junit.*;
import static org.junit.Assert.*;

import java.math.BigInteger;

/**
 * Created by Sebastian on 08/08/15.
 */
public class SetTest {
	Set<Integer> s1 = new Set<Integer>(), s2 = new Set<Integer>(), s3 = new Set<Integer>();

    @Before
    public void setUp() {
    	s1.add(1);
    	s1.add(2);
    	s1.add(3);
    	s1.add(4);
    	
    	s2.add(1);
    	s2.add(4);
    	s2.add(5);
    	s2.add(6);
    	
    	s3.add(1);
    	s3.add(5);
    	s3.add(7);
    	s3.add(8);
    }
    
    @Test
    public void testUnion() {
    	String s1s2 = "1 2 3 4 5 6 ";
    	String s1s3  = "1 2 3 4 5 7 8 ";
    	String s2s3 = "1 4 5 6 7 8 ";
    	
    	assertEquals(s1s2, s1.union(s2).toString());
    	assertEquals(s1s3, s1.union(s3).toString());
    	assertEquals(s2s3, s2.union(s3).toString());
    }
    
    @Test
    public void testIntersection() {
    	String s1s2 = "1 4 ";
    	String s1s3  = "1 ";
    	String s2s3 = "1 5 ";
    	
    	assertEquals(s1s2, s1.intersect(s2).toString());
    	assertEquals(s1s3, s1.intersect(s3).toString());
    	assertEquals(s2s3, s2.intersect(s3).toString());
    }
    
    @Test
    public void testComplement() {
    	String s1s2 = "2 3 ";
    	String s1s3  = "2 3 4 ";
    	String s2s3 = "4 6 ";
    	String s2s1 = "5 6 ";
    	String s3s1  = "5 7 8 ";
    	String s3s2 = "7 8 ";
    	
    	assertEquals(s1s2, s1.complement(s2).toString());
    	assertEquals(s1s3, s1.complement(s3).toString());
    	assertEquals(s2s3, s2.complement(s3).toString());
    	assertEquals(s2s1, s2.complement(s1).toString());
    	assertEquals(s3s1, s3.complement(s1).toString());
    	assertEquals(s3s2, s3.complement(s2).toString());
    }
    
    @Test
    public void testSymmetricDifference() {
    	String s1s2 = "2 3 5 6 ";
    	String s1s3  = "2 3 4 5 7 8 ";
    	String s2s3 = "4 6 7 8 ";
    	
    	assertEquals(s1s2, s1.symmetricDifference(s2).toString());
    	assertEquals(s1s3, s1.symmetricDifference(s3).toString());
    	assertEquals(s2s3, s2.symmetricDifference(s3).toString());
    }
    
    @Test
    public void testRandomShit() {
    	String test1 = "1 5 ";
    	String test2 = "2 3 4 6 ";
    	String test3 = "4 ";
    	String test4 = "1 2 3 5 7 8 ";
    	String test5 = "";
    	
    	assertEquals(test1, s1.union(s2).intersect(s3).toString());
    	assertEquals(test2, s1.union(s2).complement(s3).toString());
    	assertEquals(test3, s1.intersect(s2).complement(s3).toString());
    	assertEquals(test4, s1.complement(s2).union(s3).toString());
    	assertEquals(test5, s1.complement(s2).intersect(s3).toString());
    }

    @Test
    public void testIsEmpty() {
        // Test an empty list.
        Set<Letter> set = new Set<>();
        assertTrue("New list should be empty", set.isEmpty());

        set.add(new Letter('a'));
        assertFalse("Adding one element should return false.", set.isEmpty());

        set.remove(new Letter('a'));
        assertTrue("Removing should make list empty again.", set.isEmpty());
    }

    @Test
    public void testSize() {
        Set<Integer> set = new Set<>();

        assertEquals("Empty list should be size 0", 0, set.size());

        // Insert one item
        set.add(1);
        assertEquals("List of one element should have size 1", 1, set.size());

        // Add 200 items to the list.
        for (int i = 2; i <= 200; i++) {
            set.add(i);
        }
        assertEquals("Adding many elements should result in a long list", 200, set.size());

        // Remove 1 item -> 200 items left
        set.remove(1);
        assertEquals("Removing one item should decrement the size", 199, set.size());
        
     // Remove 1 item -> 199 items left
        set.remove(101);
        assertEquals("Removing one item should decrement the size", 198, set.size());
        
     // Remove 1 item -> 198 items left
        set.remove(200);
        assertEquals("Removing one item should decrement the size", 197, set.size());
        
     // Add 1 duplicate item -> 197 items left
        set.add(123);
        assertEquals("Adding a duplicate item should not change the size", 197, set.size());
    }

    @Test
    public void testAdd() {
        Set<BigInteger> set = new Set<>();

        /* Insert first item
         * Inserting into empty set is an edge case.
         */
        BigInteger b1 = new BigInteger("6166699999999999999999999999999999999999999999999999999999999999999999");
        set.add(b1);
        assertEquals("Inserted element should be in the set", b1, set.get());

        // Append second item
        BigInteger b2 = new BigInteger("61298353489543656866699999999999999999999999999999999999999999999999999999999999999999");
        set.add(b2);
        
        assertTrue("Inserted element should be in the set", set.contains(b1));
        assertTrue("Inserted element should be in the set", set.contains(b2));
        
        // Insert duplicate item b1; remove b1 from set; set should not contain b1 anymore (no duplicates in a set).
        set.add(b1);
        set.remove(b1);
        assertFalse("'b1' should not be present in the set", set.contains(b1));
    }

    @Test
    public void testGet() {
        Set<String> set = new Set<>();

        String a = "abc";
        String b = "def";
        String c = "ghi";
        
        set.add(c);
        set.add(b);
        set.add(a);
        
        // Find and get 'a' in the set.
        set.contains(a);
        assertEquals("Retrieve should return an equal object", a, set.get());
        
        // Find and get 'c' in the set.
        set.contains(c);
        assertEquals("Retrieve should return an equal object", c, set.get());
        
        // Find and get 'b' in the set.
        set.contains(b);
        assertEquals("Retrieve should return an equal object", b, set.get());
    }

    @Test
    public void testRemove() {
        List<Letter> list = new List<>();
        Letter a = new Letter('a');
        Letter b = new Letter('b');
        Letter c = new Letter('c');
        Letter d = new Letter('d');
        list.insert(a);
        list.insert(b);
        list.insert(c);
        list.insert(d);
        // Remove an element in the middle
        list.goToFirst();
        list.goToNext();
        list.remove();

        assertEquals(c, list.retrieve());

        // Remove last element in list
        list.goToLast();
        list.remove();
        assertEquals(c, list.retrieve());


        // Remove on list with size 1
        list.remove();
        list.remove();
        try {
            assertNull(list.retrieve()); // Inconsistent specification. Undefined behaviour for retrieve on empty list.
        } catch (NullPointerException e) {
        }

        // TODO: You can add more of your own tests.
    }

    @Test
    public void testContains() {
    	Set<Double> set = new Set<>();
    	double d1 = 45.5;
        double d2 = 90.;
        double d3 = 545645.56465456;
        
        set.add(d1);
        set.add(d2);
        
        assertTrue(set.contains(d2));
        assertTrue(set.contains(d2));
        assertFalse("Double 'd3' should not be found", set.contains(d3));
    }

    @Test
    public <E> void testCopy() {
    	Set<Integer> set = new Set<>();
    	Set<Integer> realCopy = (Set<Integer>) set.copy();
    	
    	assertFalse("A new set object should have been created", set.equals(realCopy));
    }
    
    
    /**
     * Represents a comparable and clonable Letter.
     *
     * This internal class is only used for testing.
     * It is independent of any of your Implementations.
     *
     * If you write your own tests you may also use your own
     * Implementations (i.e., of Identifier).
     */
    private class Letter implements Comparable<Letter>, Cloneable {

        private char letter;

        public Letter(char c){
            this.letter = c;
        }

        public int compareTo(Letter l) {
            return this.letter - l.letter;
        }

        public Letter clone() {
            return new Letter(this.letter);
        }

        /*
         * Tests whether o is the same as this Letter.
         *
         * For assertEquals() to work an equals() method is necessary.
         *
         * Adapt this method for any of your classes that you use in assertEquals(). 
         */
        public boolean equals(Object o){

            // First clause: Test whether o is null before treating it as an object.
            // Second clause: Test whether o is of correct type.
            //                Change this type when copying to another class.
            if(o != null && o.getClass() == getClass()) {

                // Now we know that o is not null and has the same type as this.

                // Do any calculation to determine whether o and this are the same Letter.
                // In this case, Letter implements Comparable, so we can use compareTo().
                return this.compareTo((Letter)o) == 0;
            }

            // Since o was null or not of type Letter, we can safely conclude
            // that o does not equal this.
            return false;
        }
    }
}