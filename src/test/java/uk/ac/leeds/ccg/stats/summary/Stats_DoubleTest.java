/*
 * Copyright 2020 Centre for Computational Geography.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package uk.ac.leeds.ccg.stats.summary;

import java.util.ArrayList;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

/**
 *
 * @author agdtu
 */
public class Stats_DoubleTest {
    
    public Stats_DoubleTest() {
    }
    
    @BeforeAll
    public static void setUpClass() {
    }
    
    @AfterAll
    public static void tearDownClass() {
    }
    
    @BeforeEach
    public void setUp() {
    }
    
    @AfterEach
    public void tearDown() {
    }

    /**
     * Test of toString method, of class Stats_Double.
     */
    @Test
    public void testToString() {
        // No test!
    }

    /**
     * Test of toString1 method, of class Stats_Double.
     */
    @Test
    public void testToString1() {
        // No test!
    }

    /**
     * Test of equals method, of class Stats_Double.
     */
    @Test
    public void testEquals() {
        System.out.println("equals");
        var data = new ArrayList<Double>();
        data.add(100.0d);
        data.add(-100.0d);
        data.add(50.0d);
        data.add(-50.0d);
        data.add(0.0d);
        Stats_Double expResult = new Stats_Double();
        expResult.n = 5;
        expResult.max = 100.0d;
        expResult.min = -100.0d;
        expResult.sum = 0.0d;
        expResult.mean = 0.0d;
        Stats_Double result = new Stats_Double(data);
        assertEquals(expResult, result);
    }

    /**
     * Test of hashCode method, of class Stats_Double.
     */
    @Test
    public void testHashCode() {
        // No test!
    }
    
}
