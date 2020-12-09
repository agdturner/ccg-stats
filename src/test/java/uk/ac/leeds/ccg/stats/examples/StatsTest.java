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
package uk.ac.leeds.ccg.stats.examples;

import ch.obermuhlner.math.big.BigRational;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.Collection;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import static org.hamcrest.MatcherAssert.assertThat; 
import static org.hamcrest.Matchers.*;
import uk.ac.leeds.ccg.stats.summary.Stats_BigDecimal;
import uk.ac.leeds.ccg.stats.summary.Stats_BigDecimal1;
import uk.ac.leeds.ccg.stats.summary.Stats_BigRational;
import uk.ac.leeds.ccg.stats.summary.Stats_Double;
import uk.ac.leeds.ccg.stats.summary.Stats_Double1;

/**
 *
 * @author agdtu
 */
public class StatsTest {
    
    public StatsTest() {
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
     * Test of getStats_BigDecimal method, of class Stats.
     */
    @Test
    public void testGetStats_BigDecimal() {
        System.out.println("getStats_BigDecimal");
        BigDecimal p100 = BigDecimal.valueOf(100);
        BigDecimal n100 = BigDecimal.valueOf(-100);
        BigDecimal p50 = BigDecimal.valueOf(50);
        BigDecimal n50 = BigDecimal.valueOf(-50);
        BigDecimal p0 = BigDecimal.ZERO;
        Collection<BigDecimal> data = new ArrayList<>();
        data.add(p100);
        data.add(n100);
        data.add(p50);
        data.add(n50);
        data.add(p0);
        int dp = 0;
        RoundingMode rm = RoundingMode.HALF_UP;
        Stats_BigDecimal expResult = new Stats_BigDecimal();
        expResult.n = 5;
        expResult.max = p100;
        expResult.min = n100;
        expResult.sum = p0;
        expResult.mean = p0;
        Stats_BigDecimal result = Stats.getStats_BigDecimal(data, dp, rm);
        assertEquals(expResult, result);
    }

    /**
     * Test of getStats_Double1 method, of class Stats.
     */
    @Test
    public void testGetStats_Double() {
        System.out.println("getStats_Double1");
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
        Stats_Double result = Stats.getStats_Double(data);
        assertEquals(expResult, result);
    }

    /**
     * Test of getStats_BigRational method, of class Stats.
     */
    @Test
    public void testGetStats_BigRational() {
        System.out.println("getStats_BigRational");
        BigRational p100 = BigRational.valueOf(100);
        BigRational n100 = BigRational.valueOf(-100);
        BigRational p50 = BigRational.valueOf(50);
        BigRational n50 = BigRational.valueOf(-50);
        BigRational p0 = BigRational.ZERO;
        Collection<BigRational> data = new ArrayList<>();
        data.add(p100);
        data.add(n100);
        data.add(p50);
        data.add(n50);
        data.add(p0);
        Stats_BigRational expResult = new Stats_BigRational();
        expResult.n = 5;
        expResult.max = p100;
        expResult.min = n100;
        expResult.sum = p0;
        expResult.mean = p0;
        Stats_BigRational result = Stats.getStats_BigRational(data);
        assertEquals(expResult, result);
    }

    /**
     * Test of getStats_Double1 method, of class Stats.
     */
    @Test
    public void testGetStats_Double1() {
        System.out.println("getStats_Double1");
        var data = new ArrayList<Double>();
        data.add(100.0d);
        data.add(-100.0d);
        data.add(50.0d);
        data.add(-50.0d);
        data.add(0.0d);
        Stats_Double1 expResult = new Stats_Double1();
        expResult.n = 5;
        expResult.max = 100.0d;
        expResult.min = -100.0d;
        expResult.sum = 0.0d;
        expResult.mean = 0.0d;
        expResult.median = 0.0d;
        expResult.nNeg = 2;
        expResult.nZero = 1;
        expResult.q1 = -50.0d;
        expResult.q3 = 50.0d;
        Stats_Double1 result = Stats.getStats_Double1(data);
        assertEquals(expResult, result);
        // Test 2
        data.add(0.0d);
        expResult = new Stats_Double1();
        expResult.n = 6;
        expResult.max = 100.0d;
        expResult.min = -100.0d;
        expResult.sum = 0.0d;
        expResult.mean = 0.0d;
        expResult.median = 0.0d;
        expResult.nNeg = 2;
        expResult.nZero = 2;
        expResult.q1 = -50.0d;
        expResult.q3 = 50.0d;
        result = Stats.getStats_Double1(data);
        assertEquals(expResult, result);
        // Test 3
        data.add(0.0d);
        expResult = new Stats_Double1();
        expResult.n = 7;
        expResult.max = 100.0d;
        expResult.min = -100.0d;
        expResult.sum = 0.0d;
        expResult.mean = 0.0d;
        expResult.median = 0.0d;
        expResult.nNeg = 2;
        expResult.nZero = 3;
        expResult.q1 = -50.0d;
        expResult.q3 = 50.0d;
        result = Stats.getStats_Double1(data);
        assertEquals(expResult, result);
        // Test 4
        data.add(0.0d);
        expResult = new Stats_Double1();
        expResult.n = 8;
        expResult.max = 100.0d;
        expResult.min = -100.0d;
        expResult.sum = 0.0d;
        expResult.mean = 0.0d;
        expResult.median = 0.0d;
        expResult.nNeg = 2;
        expResult.nZero = 4;
        expResult.q1 = 0.0d;
        expResult.q3 = 0.0d;
        result = Stats.getStats_Double1(data);
        assertEquals(expResult, result);
        // Test 5
        data.add(0.0d);
        expResult = new Stats_Double1();
        expResult.n = 9;
        expResult.max = 100.0d;
        expResult.min = -100.0d;
        expResult.sum = 0.0d;
        expResult.mean = 0.0d;
        expResult.median = 0.0d;
        expResult.nNeg = 2;
        expResult.nZero = 5;
        expResult.q1 = 0.0d;
        expResult.q3 = 0.0d;
        result = Stats.getStats_Double1(data);
        assertEquals(expResult, result);
    }

    /**
     * Test of getStats_BigDecimal1 method, of class Stats.
     */
    @Test
    public void testGetStats_BigDecimal1() {
        System.out.println("getStats_BigDecimal1");
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
        Stats_BigDecimal1 result = Stats.getStats_BigDecimal1(data, dp, rm);
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
        result = Stats.getStats_BigDecimal1(data, dp, rm);
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
        result = Stats.getStats_BigDecimal1(data, dp, rm);
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
        result = Stats.getStats_BigDecimal1(data, dp, rm);
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
        result = Stats.getStats_BigDecimal1(data, dp, rm);
        assertEquals(expResult, result);
    }
    
}
