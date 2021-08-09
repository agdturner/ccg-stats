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
import java.util.Collection;
import java.util.Objects;

/**
 * POJO for summary statistics of BigDecimal values.
 *
 * @author Andy Turner
 * @version 1.0
 */
public class Stats_BigDecimal extends Stats_Abstract {

    private static final long serialVersionUID = 1L;

    /**
     * For storing the sum of all values.
     */
    protected BigDecimal sum;

    /**
     * For storing the minimum value.
     */
    protected BigDecimal min;

    /**
     * For storing the maximum value.
     */
    protected BigDecimal max;

    /**
     * Create.
     */
    public Stats_BigDecimal() {
    }

    /**
     * @param d The collection of values.
     */
    public Stats_BigDecimal(Collection<BigDecimal> d) {
        super(d.size());
        init(d);
    }

    /**
     * @param d The collection of values.
     */
    protected final void init(Collection<BigDecimal> d) {
        int dataSize = d.size();
        switch (dataSize) {
            case 0:
                break;
            case 1:
                BigDecimal v = d.stream().findAny().get();
                sum = v;
                min = v;
                max = v;
                mean = BigRational.valueOf(v);
                break;
            default:
                sum = BigDecimal.ZERO;
                v = d.iterator().next();
                min = v;
                max = v;
                for (BigDecimal x : d) {
                    sum = sum.add(x);
                    min = min.min(x);
                    max = max.max(x);
                }
                mean = BigRational.valueOf(sum).divide(n);
                break;
        }
    }

    @Override
    public String toString() {
        return getClass().getName() + "["
                + super.toString()
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
        if (o instanceof Stats_BigDecimal) {
            Stats_BigDecimal s = (Stats_BigDecimal) o;
            //if (this.hashCode() == o.hashCode()) {
            if (super.equals(o)) {
                if (this.sum.compareTo(s.sum) == 0) {
                    if (this.min.compareTo(s.min) == 0) {
                        if (this.max.compareTo(s.max) == 0) {
                            if (this.mean.compareTo(s.mean) == 0) {
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
        int hash = 3;
        hash = 47 * hash + Objects.hashCode(this.sum);
        hash = 47 * hash + Objects.hashCode(this.mean);
        hash = 47 * hash + Objects.hashCode(this.min);
        hash = 47 * hash + Objects.hashCode(this.max);
        return hash;
    }
    
    /**
     * @return {@link #max} 
     */
    public BigDecimal getMax() {
        return max;
    }
    
    /**
     * @return {@link #min} 
     */
    public BigDecimal getMin() {
        return min;
    }
    
    /**
     * @return {@link #sum} 
     */
    public BigDecimal getSum() {
        return sum;
    }
    
}
