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
import java.util.Collection;
import java.util.Iterator;
import java.util.Objects;

/**
 * A POJO for storing summary statistics for a collection of values stored as
 * BigDecimal.
 *
 * @TODO Support adding further collections of values.
 *
 * @author Andy Turner
 * @version 1.0
 */
public class Stats_BigDecimal2 extends Stats_BigDecimal1 {

    private static final long serialVersionUID = 1L;

    /**
     * For storing the sum of all the (differences from the mean).
     */
    public BigRational m1;

    /**
     * For storing the sum of all the (differences from the mean squared).
     */
    public BigRational m2;

    /**
     * For storing the sum of all the (differences from the mean cubed).
     */
    public BigRational m3;

    /**
     * For storing the sum of all the (differences from the mean squared
     * squared).
     */
    public BigRational m4;

    public Stats_BigDecimal2() {
    }

    /**
     * @param data The data collection.
     * @param dp The decimal places.
     * @param rm The RoundingMode.
     */
    public Stats_BigDecimal2(Collection<BigDecimal> data) {
        super(data);
        int dataSize = data.size();
        switch (dataSize) {
            case 0:
                break;
            case 1:
                m1 = BigRational.ZERO;
                m2 = BigRational.ZERO;
                m3 = BigRational.ZERO;
                m4 = BigRational.ZERO;
                break;
            default:
                m1 = BigRational.ZERO;
                m2 = BigRational.ZERO;
                m3 = BigRational.ZERO;
                m4 = BigRational.ZERO;
                Iterator<BigDecimal> ite = data.iterator();
                while (ite.hasNext()) {
                    BigRational i = BigRational.valueOf(ite.next());
                    m1 = m1.add(i.subtract(mean).abs());
                    m2 = m2.add(i.subtract(mean).pow(2));
                    m3 = m3.add(i.subtract(mean).pow(3).abs());
                    m4 = m4.add(i.subtract(mean).pow(4));
                }
                break;
        }
    }

    @Override
    public String toString() {
        return getClass().getName() 
                + "[" + super.toString()
                + ", m1=" + m1.toString()
                + ", m2=" + m2.toString()
                + ", m3=" + m3.toString()
                + ", m4=" + m4.toString()
                + "]";
    }

    /**
     *
     * @param o
     * @return
     */
    @Override
    public boolean equals(Object o) {
        if (o instanceof Stats_BigDecimal2) {
            Stats_BigDecimal2 s = (Stats_BigDecimal2) o;
            //if (this.hashCode() == o.hashCode()) {
                if (super.equals(o)) {
                    if (this.m1.compareTo(s.m1) == 0) {
                        if (this.m2.compareTo(s.m2) == 0) {
                            if (this.m3.compareTo(s.m3) == 0) {
                                if (this.m4.compareTo(s.m4) == 0) {
                                        return true;
                                }
                            }
                        }
                    }
                }
            //} 
        }
        return false;
    }

    @Override
    public int hashCode() {
        int hash = 5;
        hash = 41 * hash + Objects.hashCode(this.m1);
        hash = 41 * hash + Objects.hashCode(this.m2);
        hash = 41 * hash + Objects.hashCode(this.m3);
        hash = 41 * hash + Objects.hashCode(this.m4);
        return hash;
    }
    
    /**
     * Calculates and returns the standard deviation.
     * @return A BigRational representing the standard deviation.
     */
    public BigRational getStandardDeviationSquared() {
        return m2.divide(n.add(BigInteger.ONE.negate()));
    }
}
