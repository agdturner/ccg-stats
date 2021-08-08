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
import static org.hamcrest.MatcherAssert.*;
import org.hamcrest.Matchers;

/**
 * Test.
 * 
 * @author Andy Turner
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
     * Test of equals method, of class Stats_BigDecimal2.
     */
    @Test
    public void testEquals() {
        System.out.println("equals");
        var data = new ArrayList<BigDecimal>();
        Stats_BigDecimal2 result;
        Stats_BigDecimal2 expResult;
        // Test 1
        data.add(BigDecimal.valueOf(100.0d));
        data.add(BigDecimal.valueOf(-100.0d));
        data.add(BigDecimal.valueOf(50.0d));
        data.add(BigDecimal.valueOf(-50.0d));
        data.add(BigDecimal.valueOf(0.0d));
        expResult = new Stats_BigDecimal2();
        expResult.n = BigInteger.valueOf(5);
        expResult.max = BigDecimal.valueOf(100.0d);
        expResult.min = BigDecimal.valueOf(-100.0d);
        expResult.sum = BigDecimal.valueOf(0.0d);
        expResult.mean = BigRational.ZERO;
        expResult.median = BigRational.ZERO;
        expResult.nNeg = BigInteger.valueOf(2);
        expResult.nZero = BigInteger.valueOf(1);
        expResult.q1 = BigDecimal.valueOf(-50);
        expResult.q3 = BigDecimal.valueOf(50);
        expResult.m1 = BigRational.valueOf(300);
        expResult.m2 = BigRational.valueOf(25000);
        expResult.m3 = BigRational.valueOf(2250000);
        expResult.m4 = BigRational.valueOf(212500000);
        result = new Stats_BigDecimal2(data);
        assertEquals(expResult, result);
        // Test 1
        data = new ArrayList<>();
        data.add(BigDecimal.valueOf(525.8d));
        data.add(BigDecimal.valueOf(605.7d));
        data.add(BigDecimal.valueOf(843.3d));
        data.add(BigDecimal.valueOf(1195.5d));
        data.add(BigDecimal.valueOf(1945.6d));
        data.add(BigDecimal.valueOf(2135.6d));
        data.add(BigDecimal.valueOf(2308.7d));
        data.add(BigDecimal.valueOf(2950.0d));
        expResult = new Stats_BigDecimal2();
        expResult.n = BigInteger.valueOf(8);
        expResult.max = BigDecimal.valueOf(2950);
        expResult.min = new BigDecimal("525.8");
        expResult.sum = new BigDecimal("12510.2");
        //expResult.mean = BigRational.valueOf(1563.8d);
        expResult.mean = BigRational.valueOf("1563.775");
        expResult.median = BigRational.valueOf(1570.55d);
        expResult.nNeg = BigInteger.ZERO;
        expResult.nZero = BigInteger.ZERO;
        expResult.q1 = BigDecimal.valueOf(843.3d);
        expResult.q3 = BigDecimal.valueOf(2135.6d);
        //expResult.m1 = BigRational.valueOf(6169.6d);
        expResult.m1 = BigRational.valueOf("6169.6");
        //expResult.m2 = BigRational.valueOf(5599317.68d);
        expResult.m2 = BigRational.valueOf("5599317.675");
        //expResult.m3 = BigRational.valueOf(5741453120.494d);
        expResult.m3 = BigRational.valueOf("5741475556.1755");
        //expResult.m4 = BigRational.valueOf("6419805908521.9556");
        expResult.m4 = BigRational.valueOf("6419895701972.435690625");
        result = new Stats_BigDecimal2(data);
        //System.out.println(result);
        assertEquals(expResult, result);
    }
    
    @Test
    public void testGetStandardDeviationSquared(){
         System.out.println("getStandardDeviationSquared");
         var data = new ArrayList<BigDecimal>();
        data.add(BigDecimal.valueOf(525.8d));
        data.add(BigDecimal.valueOf(605.7d));
        data.add(BigDecimal.valueOf(843.3d));
        data.add(BigDecimal.valueOf(1195.5d));
        data.add(BigDecimal.valueOf(1945.6d));
        data.add(BigDecimal.valueOf(2135.6d));
        data.add(BigDecimal.valueOf(2308.7d));
        data.add(BigDecimal.valueOf(2950.0d));
        BigRational result = new Stats_BigDecimal2(data).getStandardDeviationSquared();
        //BigRational expResult = BigRational.valueOf(799951.36d);
        BigRational expResult = BigRational.valueOf(799902.525d);
        assertTrue(result.compareTo(expResult) == 0);
    } 
}
