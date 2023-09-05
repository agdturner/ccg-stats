/*
 * Copyright 2021 Centre for Computational Geography.
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
import java.math.RoundingMode;
import java.util.ArrayList;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import uk.ac.leeds.ccg.math.number.Math_BigRationalRoot;
import uk.ac.leeds.ccg.math.number.Math_BigRationalSqrt;

/**
 *
 * @author Andy Turner
 */
public class Stats_MomentsTest {
    
    public Stats_MomentsTest() {
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
     * Test of init method, of class Stats_Moments.
     */
    @Test
    public void testInit() {
        // No test.
    }

    /**
     * Test of toString method, of class Stats_Moments.
     */
    @Test
    public void testToString() {
        // No test.
    }
    
    /**
     * Test of hashCode method, of class Stats_Moments.
     */
    @Test
    public void testHashCode() {
        // No test.
    }
    
    /**
     * Test of equals method, of class Stats_Moments.
     */
    @Test
    public void testEquals() {
        System.out.println("equals");
        var data = new ArrayList<BigDecimal>();
        Stats_BigDecimal1 result;
        Stats_BigDecimal1 expResult;
        // Test 1
        data.add(BigDecimal.valueOf(100.0d));
        data.add(BigDecimal.valueOf(-100.0d));
        data.add(BigDecimal.valueOf(50.0d));
        data.add(BigDecimal.valueOf(-50.0d));
        data.add(BigDecimal.valueOf(0.0d));
        expResult = new Stats_BigDecimal1();
//        expResult.n = BigInteger.valueOf(5);
//        expResult.max = BigDecimal.valueOf(100.0d);
//        expResult.min = BigDecimal.valueOf(-100.0d);
//        expResult.sum = BigDecimal.valueOf(0.0d);
//        expResult.mean = BigRational.ZERO;
//        expResult.median = BigRational.ZERO;
//        expResult.nNeg = BigInteger.valueOf(2);
//        expResult.nZero = BigInteger.valueOf(1);
//        expResult.q1 = BigDecimal.valueOf(-50);
//        expResult.q3 = BigDecimal.valueOf(50);
        expResult.moments = new Stats_Moments(expResult);
        expResult.moments.m1 = BigRational.valueOf(300);
        expResult.moments.m2 = BigRational.valueOf(25000);
        expResult.moments.m3 = BigRational.valueOf(2250000);
        expResult.moments.m4 = BigRational.valueOf(212500000);
        result = new Stats_BigDecimal1(data);
        // Have to get something to force an update.
        result.getMoments().getM1();
        assertEquals(expResult.moments, result.getMoments());
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
        expResult = new Stats_BigDecimal1();
//        expResult.n = BigInteger.valueOf(8);
//        expResult.max = BigDecimal.valueOf(2950);
//        expResult.min = new BigDecimal("525.8");
//        expResult.sum = new BigDecimal("12510.2");
//        //expResult.mean = BigRational.valueOf(1563.8d);
//        expResult.mean = BigRational.valueOf("1563.775");
//        expResult.median = BigRational.valueOf(1570.55d);
//        expResult.nNeg = BigInteger.ZERO;
//        expResult.nZero = BigInteger.ZERO;
//        expResult.q1 = BigDecimal.valueOf(843.3d);
//        expResult.q3 = BigDecimal.valueOf(2135.6d);
        expResult.moments = new Stats_Moments(expResult);
        //expResult.moments.m1 = BigRational.valueOf(6169.6d);
        expResult.moments.m1 =  BigRational.valueOf("6169.6");
        //expResult.moments.m2 = BigRational.valueOf(5599317.68d);
        expResult.moments.m2 =  BigRational.valueOf("5599317.675");
        //expResult.moments.m3 = BigRational.valueOf(5741453120.494d);
        expResult.moments.m3 =  BigRational.valueOf("5741475556.1755");
        //expResult.moments.m4 = BigRational.valueOf("6419805908521.9556");
        expResult.moments.m4 =  BigRational.valueOf("6419895701972.435690625");
        result = new Stats_BigDecimal1(data);
        // Have to get something to force an update.
        result.getMoments().getM1();
        //System.out.println(result);
        assertEquals(expResult.moments, result.getMoments());
    }
    
    /**
     * Test of getStandardDeviationSquared method, of class Stats_Moments.
     */
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
        Stats_BigDecimal1 stats = new Stats_BigDecimal1(data);
        BigRational result = stats.getMoments().getStandardDeviationSquared();
        //BigRational expResult = BigRational.valueOf(799951.36d);
        BigRational expResult = BigRational.valueOf(799902.525d);
        assertTrue(result.compareTo(expResult) == 0);
    }

    /**
     * Test of getStandardDeviation method, of class Stats_Moments.
     */
    @Test
    public void testGetStandardDeviation() {
        System.out.println("getStandardDeviation");
        var data = new ArrayList<BigDecimal>();
        data.add(BigDecimal.valueOf(525.8d));
        data.add(BigDecimal.valueOf(605.7d));
        data.add(BigDecimal.valueOf(843.3d));
        data.add(BigDecimal.valueOf(1195.5d));
        data.add(BigDecimal.valueOf(1945.6d));
        data.add(BigDecimal.valueOf(2135.6d));
        data.add(BigDecimal.valueOf(2308.7d));
        data.add(BigDecimal.valueOf(2950.0d));
        Stats_BigDecimal1 stats = new Stats_BigDecimal1(data);
        int oom = -3;
        RoundingMode rm = RoundingMode.HALF_UP;
        Math_BigRationalSqrt result = stats.getMoments().getStandardDeviation(
                oom, rm);
        //BigRational expResult = BigRational.valueOf(799951.36d);
        Math_BigRationalSqrt expResult = new Math_BigRationalSqrt(
                BigRational.valueOf("799902.525"), oom, rm);
        assertTrue(result.compareTo(expResult) == 0);
    }
    
}
