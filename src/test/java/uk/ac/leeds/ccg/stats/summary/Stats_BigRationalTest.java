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
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Collection;
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
public class Stats_BigRationalTest {

    public Stats_BigRationalTest() {
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
     * Test of equals method, of class Stats_BigRational.
     */
    @Test
    public void testEquals() {
        System.out.println("equals");
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
        expResult.n = BigInteger.valueOf(5);
        expResult.max = p100;
        expResult.min = n100;
        expResult.sum = BigRational.ZERO;
        expResult.mean = p0;
        Stats_BigRational result = new Stats_BigRational(data);
        assertEquals(expResult, result);
    }

}
