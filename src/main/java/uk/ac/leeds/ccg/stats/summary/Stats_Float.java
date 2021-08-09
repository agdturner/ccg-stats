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
import java.util.List;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.Collection;
import java.util.DoubleSummaryStatistics;
import java.util.Objects;

/**
 * POJO for summary statistics of float values.
 *
 * Proposed future developments:
 * <ul>
 * <li>Support adding further collections of values.</li>
 * </ul>
 *
 * @author Andy Turner
 * @version 1.0
 */
public class Stats_Float extends Stats_Abstract {

    private static final long serialVersionUID = 1L;

    /**
     * For storing the sum of all values.
     */
    protected BigDecimal sum;

    /**
     * For storing the minimum value.
     */
    protected float min;

    /**
     * For storing the maximum value.
     */
    protected float max;

    /**
     * Create.
     */
    public Stats_Float() {
    }

    /**
     * @param d The collection of values.
     */
    public Stats_Float(Collection<Float> d) {
        super(d.size());
        init(d);
    }
    
    /**
     * @param d The collection of values.
     */
    protected final void init(Collection<Float> d) {
        n = BigInteger.valueOf(d.size());
        max = -Float.MAX_VALUE;
        min = Float.MAX_VALUE;
        sum = BigDecimal.ZERO;
        d.forEach(x -> {
            max = Math.max(max, x);
            min = Math.min(min, x);
            sum = sum.add(BigDecimal.valueOf(x));
        });
        mean = BigRational.valueOf(sum).divide(n);
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
                + ", sum=" + getSum()
                + ", min=" + getMin()
                + ", max=" + getMax()
                + ", mean=" + getMean()
                + "]";
    }

    /**
     * @param o The object to test for equality.
     * @return {@code true} iff {@code this} and o are equal.
     */
    @Override
    public boolean equals(Object o) {
        if (o instanceof Stats_Float) {
            Stats_Float s = (Stats_Float) o;
            //if (s.hashCode() == this.hashCode()) {
            if (s.min == min) {
                if (s.max == max) {
                    if (s.sum.compareTo(sum) == 0) {
                        if (s.mean.compareTo(mean) == 0) {
                            if (super.equals(o)) {
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
        int hash = 7;
        hash = 17 * hash + Objects.hashCode(this.sum);
        hash = 17 * hash + Objects.hashCode(this.mean);
        hash = 17 * hash + (int) (Double.doubleToLongBits(this.min) ^ (Double.doubleToLongBits(this.min) >>> 32));
        hash = 17 * hash + (int) (Double.doubleToLongBits(this.max) ^ (Double.doubleToLongBits(this.max) >>> 32));
        return hash;
    }
    
    /**
     * @return {@link #max} 
     */
    public float getMax() {
        return max;
    }
    
    /**
     * @return {@link #min} 
     */
    public float getMin() {
        return min;
    }
    
    /**
     * @return {@link #sum} 
     */
    public BigDecimal getSum() {
        return sum;
    }
    
}
