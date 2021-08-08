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
import java.util.Iterator;
import java.util.Objects;

/**
 * A POJO for storing summary statistics for a set of values stored as
 * BigDecimals.
 *
 * @TODO Support adding further collections of values.
 *
 * @author Andy Turner
 * @version 1.0
 */
public class Stats_BigDecimal extends Stats_n {

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
    public BigDecimal min;

    /**
     * For storing the maximum value.
     */
    public BigDecimal max;

    public Stats_BigDecimal() {
    }

    /**
     * @param data The data collection.
     * @param dp The decimal places.
     * @param rm The RoundingMode.
     */
    public Stats_BigDecimal(Collection<BigDecimal> data) {
        super(data.size());
        int dataSize = data.size();
        switch (dataSize) {
            case 0:
                break;
            case 1:
                BigDecimal v = data.stream().findAny().get();
                sum = v;
                min = v;
                max = v;
                mean = BigRational.valueOf(v);
                break;
            default:
                sum = BigDecimal.ZERO;
                BigDecimal v2 = data.iterator().next();
                min = v2;
                max = v2;
                Iterator<BigDecimal> ite = data.iterator();
                while (ite.hasNext()) {
                    BigDecimal i = ite.next();
                    sum = sum.add(i);
                    min = min.min(i);
                    max = max.max(i);
                }
                mean = BigRational.valueOf(sum).divide(n);
                break;
        }
    }

    @Override
    public String toString() {
        return getClass().getName() + "["
                + super.toString()
                + ", sum=" + sum.toString()
                + ", min=" + min.toString()
                + ", max=" + max.toString()
                + ", mean=" + mean.toString()
                + "]";
    }

    /**
     *
     * @param o
     * @return
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
}
