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

import ch.obermuhlner.math.big.BigRational;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.math.RoundingMode;
import java.util.ArrayList;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

/**
 * Test.
 * 
 * @author Andy Turner
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
     * Test of equals method, of class Stats_BigDecimal1.
     */
    @Test
    public void testEquals() {
        System.out.println("equals");
        ArrayList<BigDecimal> data = new ArrayList<>();
        Stats_BigDecimal1 result;
        Stats_BigDecimal1 expResult;
        data.add(BigDecimal.valueOf(100.0d));
        data.add(BigDecimal.valueOf(-100.0d));
        data.add(BigDecimal.valueOf(50.0d));
        data.add(BigDecimal.valueOf(-50.0d));
        data.add(BigDecimal.valueOf(0.0d));
        expResult = new Stats_BigDecimal1();
        expResult.n = BigInteger.valueOf(5);
        expResult.max = BigDecimal.valueOf(100.0d);
        expResult.min = BigDecimal.valueOf(-100.0d);
        expResult.sum = BigDecimal.valueOf(0.0d);
        expResult.mean = BigRational.valueOf(0.0d);
        expResult.median = BigRational.valueOf(0.0d);
        expResult.nNeg = BigInteger.valueOf(2);
        expResult.nZero = BigInteger.valueOf(1);
        expResult.q1 = BigDecimal.valueOf(-50.0d);
        expResult.q3 = BigDecimal.valueOf(50.0d);
        result = new Stats_BigDecimal1(data);
        assertTrue(expResult.equals(result));
        // Test 2
        data.add(BigDecimal.valueOf(0.0d));
        expResult.n = BigInteger.valueOf(6);
        expResult.max = BigDecimal.valueOf(100.0d);
        expResult.min = BigDecimal.valueOf(-100.0d);
        expResult.sum = BigDecimal.valueOf(0.0d);
        expResult.mean = BigRational.valueOf(0.0d);
        expResult.median = BigRational.valueOf(0.0d);
        expResult.nNeg = BigInteger.valueOf(2);
        expResult.nZero = BigInteger.valueOf(2);
        expResult.q1 = BigDecimal.valueOf(-50.0d);
        expResult.q3 = BigDecimal.valueOf(50.0d);
        result = new Stats_BigDecimal1(data);
        assertEquals(expResult, result);
        // Test 3
        data.add(BigDecimal.valueOf(0.0d));
        expResult.n = BigInteger.valueOf(7);
        expResult.max = BigDecimal.valueOf(100.0d);
        expResult.min = BigDecimal.valueOf(-100.0d);
        expResult.sum = BigDecimal.valueOf(0.0d);
        expResult.mean = BigRational.valueOf(0.0d);
        expResult.median = BigRational.valueOf(0.0d);
        expResult.nNeg = BigInteger.valueOf(2);
        expResult.nZero = BigInteger.valueOf(3);
        expResult.q1 = BigDecimal.valueOf(-50.0d);
        expResult.q3 = BigDecimal.valueOf(50.0d);
        result = new Stats_BigDecimal1(data);
        assertEquals(expResult, result);
        // Test 4
        data.add(BigDecimal.valueOf(0.0d));
        expResult.n = BigInteger.valueOf(8);
        expResult.max = BigDecimal.valueOf(100.0d);
        expResult.min = BigDecimal.valueOf(-100.0d);
        expResult.sum = BigDecimal.valueOf(0.0d);
        expResult.mean = BigRational.valueOf(0.0d);
        expResult.median = BigRational.valueOf(0.0d);
        expResult.nNeg = BigInteger.valueOf(2);
        expResult.nZero = BigInteger.valueOf(4);
        expResult.q1 = BigDecimal.valueOf(0.0d);
        expResult.q3 = BigDecimal.valueOf(0.0d);
        result = new Stats_BigDecimal1(data);
        assertEquals(expResult, result);
        // Test 5
        data.add(BigDecimal.valueOf(0.0d));
        expResult.n = BigInteger.valueOf(9);
        expResult.max = BigDecimal.valueOf(100.0d);
        expResult.min = BigDecimal.valueOf(-100.0d);
        expResult.sum = BigDecimal.valueOf(0.0d);
        expResult.mean = BigRational.valueOf(0.0d);
        expResult.median = BigRational.valueOf(0.0d);
        expResult.nNeg = BigInteger.valueOf(2);
        expResult.nZero = BigInteger.valueOf(5);
        expResult.q1 = BigDecimal.valueOf(0.0d);
        expResult.q3 = BigDecimal.valueOf(0.0d);
        result = new Stats_BigDecimal1(data);
        assertEquals(expResult, result);
    }
}
