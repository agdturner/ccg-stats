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
import java.util.Collection;
import java.util.Iterator;
import java.util.Objects;

/**
 * A POJO for storing summary statistics.
 *
 * @author Andy Turner
 * @version 1.0
 */
public class Stats_BigRational extends Stats_n {

    /**
     * For storing the sum of all values.
     */
    public BigRational sum;

    /**
     * For storing the mean average.
     */
    public BigRational mean;

    /**
     * For storing the minimum value.
     */
    public BigRational min;

    /**
     * For storing the maximum value.
     */
    public BigRational max;

    @Override
    public String toString() {
        return getClass().getName() + "[" + toString1() + "]";
    }

    @Override
    public String toString1() {
        return super.toString1()
                + ", sum=" + sum.toString()
                + ", min=" + min.toString()
                + ", max=" + max.toString()
                + ", mean=" + mean.toString();
    }

    public Stats_BigRational() {
    }

    /**
     * @param data The data collection.
     */
    public Stats_BigRational(Collection<BigRational> data) {
        n = data.size();
        switch (n) {
            case 0:
                break;
            case 1:
                BigRational v = data.stream().findAny().get();
                sum = v;
                min = v;
                max = v;
                mean = v;
                break;
            default:
                sum = BigRational.ZERO;
                BigRational v2 = data.iterator().next();
                min = v2;
                max = v2;
                Iterator<BigRational> ite = data.iterator();
                while (ite.hasNext()) {
                    BigRational i = ite.next();
                    sum = sum.add(i);
                    min = BigRational.min(i, min);
                    max = BigRational.max(i, max);
                }
                mean = sum.divide(BigInteger.valueOf(n));
        }
    }

    @Override
    public boolean equals(Object o) {
        if (o instanceof Stats_BigRational) {
            Stats_BigRational s = (Stats_BigRational) o;
            if (s.hashCode() == this.hashCode()) {
                if (n == n) {
                    if (s.sum.equals(sum)) {
                        if (s.min.equals(min)) {
                            if (s.max.equals(max)) {
                                if (s.mean.equals(mean)) {
                                    return true;
                                }
                            }
                        }
                    }
                }
            }
        }
        return false;
    }

    @Override
    public int hashCode() {
        int hash = 5;
        hash = 67 * hash + Objects.hashCode(this.sum);
        hash = 67 * hash + Objects.hashCode(this.mean);
        hash = 67 * hash + Objects.hashCode(this.min);
        hash = 67 * hash + Objects.hashCode(this.max);
        return hash;
    }
}
