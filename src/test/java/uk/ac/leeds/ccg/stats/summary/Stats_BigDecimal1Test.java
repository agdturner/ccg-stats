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

import java.math.BigDecimal;
import java.math.RoundingMode;
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
public class Stats_BigDecimal1Test {
    
    public Stats_BigDecimal1Test() {
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
     * Test of toString method, of class Stats_BigDecimal1.
     */
    @Test
    public void testToString() {
        // No test!
    }

    /**
     * Test of toString1 method, of class Stats_BigDecimal1.
     */
    @Test
    public void testToString1() {
        // No test!
    }

    /**
     * Test of equals method, of class Stats_BigDecimal1.
     */
    @Test
    public void testEquals() {
        System.out.println("equals");
        var data = new ArrayList<BigDecimal>();
        int dp = 1;
        RoundingMode rm = null;
        data.add(BigDecimal.valueOf(100.0d));
        data.add(BigDecimal.valueOf(-100.0d));
        data.add(BigDecimal.valueOf(50.0d));
        data.add(BigDecimal.valueOf(-50.0d));
        data.add(BigDecimal.valueOf(0.0d));
        Stats_BigDecimal1 expResult = new Stats_BigDecimal1();
        expResult.n = 5;
        expResult.max = BigDecimal.valueOf(100.0d);
        expResult.min = BigDecimal.valueOf(-100.0d);
        expResult.sum = BigDecimal.valueOf(0.0d);
        expResult.mean = BigDecimal.valueOf(0.0d);
        expResult.median = BigDecimal.valueOf(0.0d);
        expResult.nNeg = 2;
        expResult.nZero = 1;
        expResult.q1 = BigDecimal.valueOf(-50.0d);
        expResult.q3 = BigDecimal.valueOf(50.0d);
        Stats_BigDecimal1 result = new Stats_BigDecimal1(data, dp, rm);
        assertEquals(expResult, result);
        // Test 2
        data.add(BigDecimal.valueOf(0.0d));
        expResult.max = BigDecimal.valueOf(100.0d);
        expResult.min = BigDecimal.valueOf(-100.0d);
        expResult.sum = BigDecimal.valueOf(0.0d);
        expResult.mean = BigDecimal.valueOf(0.0d);
        expResult.median = BigDecimal.valueOf(0.0d);
        expResult.nNeg = 2;
        expResult.nZero = 2;
        expResult.q1 = BigDecimal.valueOf(-50.0d);
        expResult.q3 = BigDecimal.valueOf(50.0d);
        result = new Stats_BigDecimal1(data, dp, rm);
        assertEquals(expResult, result);
        // Test 3
        data.add(BigDecimal.valueOf(0.0d));
        expResult.max = BigDecimal.valueOf(100.0d);
        expResult.min = BigDecimal.valueOf(-100.0d);
        expResult.sum = BigDecimal.valueOf(0.0d);
        expResult.mean = BigDecimal.valueOf(0.0d);
        expResult.median = BigDecimal.valueOf(0.0d);
        expResult.nNeg = 2;
        expResult.nZero = 3;
        expResult.q1 = BigDecimal.valueOf(-50.0d);
        expResult.q3 = BigDecimal.valueOf(50.0d);
        result = new Stats_BigDecimal1(data, dp, rm);
        assertEquals(expResult, result);
        // Test 4
        data.add(BigDecimal.valueOf(0.0d));
        expResult.max = BigDecimal.valueOf(100.0d);
        expResult.min = BigDecimal.valueOf(-100.0d);
        expResult.sum = BigDecimal.valueOf(0.0d);
        expResult.mean = BigDecimal.valueOf(0.0d);
        expResult.median = BigDecimal.valueOf(0.0d);
        expResult.nNeg = 2;
        expResult.nZero = 4;
        expResult.q1 = BigDecimal.valueOf(0.0d);
        expResult.q3 = BigDecimal.valueOf(0.0d);
        result = new Stats_BigDecimal1(data, dp, rm);
        assertEquals(expResult, result);
        // Test 5
        data.add(BigDecimal.valueOf(0.0d));
        expResult.max = BigDecimal.valueOf(100.0d);
        expResult.min = BigDecimal.valueOf(-100.0d);
        expResult.sum = BigDecimal.valueOf(0.0d);
        expResult.mean = BigDecimal.valueOf(0.0d);
        expResult.median = BigDecimal.valueOf(0.0d);
        expResult.nNeg = 2;
        expResult.nZero = 5;
        expResult.q1 = BigDecimal.valueOf(0.0d);
        expResult.q3 = BigDecimal.valueOf(0.0d);
        result = new Stats_BigDecimal1(data, dp, rm);
        assertEquals(expResult, result);
    }

    /**
     * Test of hashCode method, of class Stats_BigDecimal1.
     */
    @Test
    public void testHashCode() {
        // No test!
    }
    
}
