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
public class Stats_BigDecimal2Test {
    
    public Stats_BigDecimal2Test() {
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
     * Test of toString method, of class Stats_BigDecimal2.
     */
    @Test
    public void testToString() {
        // No test!
    }

    /**
     * Test of toString1 method, of class Stats_BigDecimal2.
     */
    @Test
    public void testToString1() {
        // No test!
    }

    /**
     * Test of equals method, of class Stats_BigDecimal2.
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
        Stats_BigDecimal2 expResult = new Stats_BigDecimal2();
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
        expResult.m1 = BigDecimal.valueOf(300.0d);
        expResult.m2 = BigDecimal.valueOf(25000.00d);
        expResult.m3 = BigDecimal.valueOf(2250000.000d);
        expResult.m4 = BigDecimal.valueOf(212500000.0000d);
        Stats_BigDecimal2 result = new Stats_BigDecimal2(data, dp, rm);
        assertEquals(expResult, result);
    }

    /**
     * Test of hashCode method, of class Stats_BigDecimal2.
     */
    @Test
    public void testHashCode() {
        // No test!
    }
    
}
