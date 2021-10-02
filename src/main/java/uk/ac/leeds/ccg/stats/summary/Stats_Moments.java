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
import java.io.Serializable;
import java.math.BigInteger;
import java.util.Collection;
import java.util.Iterator;
import java.util.Objects;
import uk.ac.leeds.ccg.math.Math_BigRational;
import uk.ac.leeds.ccg.math.Math_BigRationalSqrt;

/**
 * POJO for moments.
 *
 * Proposed future developments:
 * <ul>
 * <li>Support adding further collections of values.</li>
 * </ul>
 *
 * @author Andy Turner
 * @version 1.0
 */
public class Stats_Moments implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * For storing the sum of all the (differences from the mean).
     */
    protected Math_BigRational m1;

    /**
     * For storing the sum of all the (differences from the mean squared).
     */
    protected Math_BigRational m2;

    /**
     * For storing the sum of all the (differences from the mean cubed).
     */
    protected Math_BigRational m3;

    /**
     * For storing the sum of all the (differences from the mean squared
     * squared).
     */
    protected Math_BigRational m4;

    /**
     * A reference to the stats this is attached to.
     */
    protected final Stats_Abstract stats;

    /**
     * Records if {@link #stats} has changed since last {@link #init()}.
     */
    protected boolean isUpToDate;
    
    /**
     * Create a new instance.
     *
     * @param stats What {@link #stats} is set to.
     */
    public Stats_Moments(Stats_Abstract stats) {
        this.stats = stats;
        isUpToDate = false;
    }

    /**
     * Initialise.
     */
    protected void init() {
        BigInteger n = stats.getN();
        BigRational mean = stats.getMean();
        Collection<? extends Number> data;
        if (stats instanceof Stats_Float1) {
            data = ((Stats_Float1) stats).data;
        } else if (stats instanceof Stats_Double1) {
            data = ((Stats_Double1) stats).data;
        } else {
            data = ((Stats_BigDecimal1) stats).data;
        }
        int dataSize = n.intValueExact();
        switch (dataSize) {
            case 0:
                break;
            case 1:
                m1 = Math_BigRational.ZERO;
                m2 = Math_BigRational.ZERO;
                m3 = Math_BigRational.ZERO;
                m4 = Math_BigRational.ZERO;
                break;
            default:
                BigRational v1 = BigRational.ZERO;
               BigRational v2 = BigRational.ZERO;
               BigRational v3 = BigRational.ZERO;
               BigRational v4 = BigRational.ZERO;
                Iterator<? extends Number> ite = data.iterator();
                while (ite.hasNext()) {
                    BigRational i = BigRational.valueOf(ite.next().toString());
                    v1 = v1.add(i.subtract(mean).abs());
                    v2 = v2.add(i.subtract(mean).pow(2));
                    v3 = v3.add(i.subtract(mean).pow(3).abs());
                    v4 = v4.add(i.subtract(mean).pow(4));
                }
                m1 = new Math_BigRational(v1);
                m2 = new Math_BigRational(v2);
                m3 = new Math_BigRational(v3);
                m4 = new Math_BigRational(v4);
                break;
        }
    }

    /**
     * Description.
     *
     * @return A string representation.
     */
    @Override
    public String toString() {
        return getClass().getName()
                + "[" + super.toString()
                + ", m1=" + getM1()
                + ", m2=" + m2.toString()
                + ", m3=" + m3.toString()
                + ", m4=" + m4.toString()
                + "]";
    }

    /**
     * @param o The object to test for equality.
     * @return {@code true} iff {@code this} and o are equal.
     */
    @Override
    public boolean equals(Object o) {
        if (o instanceof Stats_Moments) {
            Stats_Moments s = (Stats_Moments) o;
            //if (this.hashCode() == o.hashCode()) {
            if (this.m1.compareTo(s.m1) == 0) {
                if (this.m2.compareTo(s.m2) == 0) {
                    if (this.m3.compareTo(s.m3) == 0) {
                        if (this.m4.compareTo(s.m4) == 0) {
                            return true;
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
     * @return {@link #m1} for the collection computing it if necessary.
     */
    public BigRational getM1() {
        if (!isUpToDate) {
            init();
        }
        return m1.getX();
    }

    /**
     * @return {@link #m2} for the collection computing it if necessary.
     */
    public BigRational getM2() {
        if (!isUpToDate) {
            init();
        }
        return m2.getX();
    }

    /**
     * @return {@link #m3} for the collection computing it if necessary.
     */
    public BigRational getM3() {
        if (!isUpToDate) {
            init();
        }
        return m3.getX();
    }

    /**
     * @return {@link #m4} for the collection computing it if necessary.
     */
    public BigRational getM4() {
        if (!isUpToDate) {
            init();
        }
        return m4.getX();
    }

    /**
     * Calculates and returns the standard deviation.
     *
     * @return A BigRational representing the standard deviation.
     */
    public BigRational getStandardDeviationSquared() {
        if (!isUpToDate) {
            init();
        }
        return m2.getX().divide(stats.getN().add(BigInteger.ONE.negate()));
    }

    /**
     * Calculates and returns the standard deviation.
     *
     * @param oom The Order of Magnitude for the initialisation of the root.
     * @return A BigRational representing the standard deviation.
     */
    public Math_BigRationalSqrt getStandardDeviation(int oom) {
        return new Math_BigRationalSqrt(getStandardDeviationSquared(), oom);
    }
}
