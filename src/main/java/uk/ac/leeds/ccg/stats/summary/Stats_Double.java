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
import java.util.DoubleSummaryStatistics;
import java.util.Objects;

/**
 * POJO for summary statistics of double values.
 *
 * Proposed future developments:
 * <ul>
 * <li>Support adding further collections of values.</li>
 * </ul>
 *
 * @author Andy Turner
 * @version 1.0
 */
public class Stats_Double extends Stats_n {

    private static final long serialVersionUID = 1L;

    /**
     * For storing the sum of all values.
     */
    public BigDecimal sum;

    /**
     * For storing the mean average.
     */
    public BigRational mean;

    /**
     * For storing the minimum value.
     */
    public double min;

    /**
     * For storing the maximum value.
     */
    public double max;

    /**
     * Create.
     */
    public Stats_Double() {
    }

    /**
     * @param data The data collection.
     */
    public Stats_Double(Collection<Double> data) {
        super(data.size());
        DoubleSummaryStatistics stats = data.parallelStream().collect(
                DoubleSummaryStatistics::new,
                DoubleSummaryStatistics::accept,
                DoubleSummaryStatistics::combine);
        max = stats.getMax();
        min = stats.getMin();
        sum = BigDecimal.ZERO;
        data.forEach(d -> {
            sum = sum.add(BigDecimal.valueOf(d));
        });
        mean = BigRational.valueOf(sum).divide(n);
    }

    @Override
    public String toString() {
        return getClass().getName()
                + "[" + super.toString()
                + ", sum=" + sum
                + ", min=" + min
                + ", max=" + max
                + ", mean=" + mean
                + "]";
    }

    /**
     * @param o The object to test for equality.
     * @return {@code true} iff {@code this} and o are equal.
     */
    @Override
    public boolean equals(Object o) {
        if (o instanceof Stats_Double) {
            Stats_Double s = (Stats_Double) o;
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
}
