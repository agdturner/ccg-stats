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

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.Collection;
import java.util.Iterator;
import java.util.Objects;
import uk.ac.leeds.ccg.math.Math_BigDecimal;

/**
 * A POJO for storing summary statistics.
 *
 * @author Andy Turner
 * @version 1.0
 */
public class Stats_BigDecimal extends Stats_n {

    /**
     * For storing the sum of all values.
     */
    public BigDecimal sum;

    /**
     * For storing the mean average.
     */
    public BigDecimal mean;

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
    public Stats_BigDecimal(Collection<BigDecimal> data, int dp,
            RoundingMode rm) {
        n = data.size();
        switch (n) {
            case 0:
                break;
            case 1:
                BigDecimal v = data.stream().findAny().get();
                sum = v;
                min = v;
                max = v;
                mean = v;
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
                mean = Math_BigDecimal.divideRoundIfNecessary(sum,
                        BigDecimal.valueOf(n), dp, rm);
                break;
        }
    }

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

    @Override
    public boolean equals(Object o) {
        if (o instanceof Stats_BigDecimal) {
            Stats_BigDecimal s = (Stats_BigDecimal) o;
            if (n == n) {
                if (s.sum.compareTo(sum) == 0) {
                    if (s.min.compareTo(min) == 0) {
                        if (s.max.compareTo(max) == 0) {
                            if (s.mean.compareTo(mean) == 0) {
                                return true;
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
        hash = 97 * hash + this.n;
        hash = 97 * hash + Objects.hashCode(this.sum);
        hash = 97 * hash + Objects.hashCode(this.mean);
        hash = 97 * hash + Objects.hashCode(this.min);
        hash = 97 * hash + Objects.hashCode(this.max);
        return hash;
    }
}
