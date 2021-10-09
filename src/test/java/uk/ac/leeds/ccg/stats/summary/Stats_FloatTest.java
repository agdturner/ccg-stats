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
import uk.ac.leeds.ccg.math.number.Math_BigRational;

/**
 * Test.
 * 
 * @author Andy Turner
 */
public class Stats_FloatTest {
    
    public Stats_FloatTest() {
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
     * Test of equals method, of class Stats_Double.
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
        Stats_Float expResult = new Stats_Float();
        expResult.n = BigInteger.valueOf(5);
        expResult.max = 100.0f;
        expResult.min = -100.0f;
        expResult.sum = BigDecimal.ZERO;
        expResult.mean = Math_BigRational.ZERO;
        Stats_Float result = new Stats_Float(data);
        assertEquals(expResult, result);
    }
    
}
