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
import java.util.Collection;
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
public class Stats_BigDecimalTest {
    
    public Stats_BigDecimalTest() {
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
     * Test of toString method, of class Stats_BigDecimal.
     */
    @Test
    public void testToString() {
        // No test!
    }

    /**
     * Test of toString1 method, of class Stats_BigDecimal.
     */
    @Test
    public void testToString1() {
        // No test!
    }

    /**
     * Test of equals method, of class Stats_BigDecimal.
     */
    @Test
    public void testEquals() {
        System.out.println("equals");
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
        Stats_BigDecimal result = new Stats_BigDecimal(data, dp, rm);
        assertEquals(expResult, result);
    }

    /**
     * Test of hashCode method, of class Stats_BigDecimal.
     */
    @Test
    public void testHashCode() {
        // No test!
    }
    
}
