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
public class Stats_Float1Test {
    
    public Stats_Float1Test() {
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
     * Test of equals method, of class Stats_Double1.
     */
    @Test
    public void testEquals() {
        System.out.println("equals");
        var data = new ArrayList<Float>();
        data.add(100.0f);
        data.add(-100.0f);
        data.add(50.0f);
        data.add(-50.0f);
        data.add(0.0f);
        Stats_Float1 expResult = new Stats_Float1();
        expResult.n = BigInteger.valueOf(5);
        expResult.max = 100.0f;
        expResult.min = -100.0f;
        expResult.sum = BigDecimal.ZERO;
        expResult.mean = BigRational.ZERO;
        expResult.median = BigRational.ZERO;
        expResult.nNeg = BigInteger.valueOf(2);
        expResult.nZero = BigInteger.valueOf(1);
        expResult.q1 = -50.0f;
        expResult.q3 = 50.0f;
        Stats_Float1 result = new Stats_Float1(data);
        assertEquals(expResult, result);
        // Test 2
        data.add(0.0f);
        expResult = new Stats_Float1();
        expResult.n = BigInteger.valueOf(6);
        expResult.max = 100.0f;
        expResult.min = -100.0f;
        expResult.sum = BigDecimal.ZERO;
        expResult.mean = BigRational.ZERO;
        expResult.median = BigRational.ZERO;
        expResult.nNeg = BigInteger.valueOf(2);
        expResult.nZero = BigInteger.valueOf(2);
        expResult.q1 = -50.0f;
        expResult.q3 = 50.0f;
        result = new Stats_Float1(data);
        assertEquals(expResult, result);
        // Test 3
        data.add(0.0f);
        expResult = new Stats_Float1();
        expResult.n = BigInteger.valueOf(7);
        expResult.max = 100.0f;
        expResult.min = -100.0f;
        expResult.sum = BigDecimal.ZERO;
        expResult.mean = BigRational.ZERO;
        expResult.median = BigRational.ZERO;
        expResult.nNeg = BigInteger.valueOf(2);
        expResult.nZero = BigInteger.valueOf(3);
        expResult.q1 = -50.0f;
        expResult.q3 = 50.0f;
        result = new Stats_Float1(data);
        assertEquals(expResult, result);
        // Test 4
        data.add(0.0f);
        expResult = new Stats_Float1();
        expResult.n = BigInteger.valueOf(8);
        expResult.max = 100.0f;
        expResult.min = -100.0f;
        expResult.sum = BigDecimal.ZERO;
        expResult.mean = BigRational.ZERO;
        expResult.median = BigRational.ZERO;
        expResult.nNeg = BigInteger.valueOf(2);
        expResult.nZero = BigInteger.valueOf(4);
        expResult.q1 = 0.0f;
        expResult.q3 = 0.0f;
        result = new Stats_Float1(data);
        assertEquals(expResult, result);
        // Test 5
        data.add(0.0f);
        expResult = new Stats_Float1();
        expResult.n = BigInteger.valueOf(9);
        expResult.max = 100.0f;
        expResult.min = -100.0f;
        expResult.sum = BigDecimal.ZERO;
        expResult.mean = BigRational.ZERO;
        expResult.median = BigRational.ZERO;
        expResult.nNeg = BigInteger.valueOf(2);
        expResult.nZero = BigInteger.valueOf(5);
        expResult.q1 = 0.0f;
        expResult.q3 = 0.0f;
        result = new Stats_Float1(data);
        assertEquals(expResult, result);
    }
    
}
