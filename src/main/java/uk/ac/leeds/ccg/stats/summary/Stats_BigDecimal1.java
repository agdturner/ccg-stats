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
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;
import uk.ac.leeds.ccg.math.Math_BigDecimal;

/**
 * A POJO for storing summary statistics for a collection of double values.
 *
 * @author Andy Turner
 * @version 1.0
 */
public class Stats_BigDecimal1 extends Stats_BigDecimal {

    /**
     * For storing the median.
     */
    public BigDecimal median;

    /**
     * For storing the lower inter quartile range value.
     */
    public BigDecimal q1;

    /**
     * For storing the upper inter quartile range value.
     */
    public BigDecimal q3;

    /**
     * For storing the number of values equal to zero.
     */
    public int nZero;

    /**
     * For storing the number of negative values.
     */
    public int nNeg;

    public Stats_BigDecimal1() {
    }

    /**
     * @param data The data collection.
     * @param dp The decimal places.
     * @param rm The RoundingMode.
     */
    public Stats_BigDecimal1(Collection<BigDecimal> data, int dp,
            RoundingMode rm) {
        super(data, dp, rm);
        switch (n) {
            case 0:
                break;
            case 1:
                BigDecimal v = data.stream().findAny().get();
                median = v;
                int c = v.compareTo(BigDecimal.ZERO);
                if (c == -1) {
                    nNeg = 1;
                } else if (c == 0) {
                    nZero = 1;
                }
                q1 = v;
                q3 = v;
                break;
            default:
                Iterator<BigDecimal> ite = data.iterator();
                while (ite.hasNext()) {
                    BigDecimal i = ite.next();
                    int co = i.compareTo(BigDecimal.ZERO);
                    if (co == -1) {
                        nNeg++;
                    } else if (co == 0) {
                        nZero++;
                    }
                }
                List<BigDecimal> sd = data.stream().sorted().collect(Collectors.toList());
                if (n % 2 == 0) {
                    median = sd.stream().skip(n / 2 - 1).limit(2)
                            .reduce(BigDecimal.ZERO, BigDecimal::add)
                            .divide(BigDecimal.valueOf(2));
                } else {
                    int mid = n / 2;
                    median = sd.stream().skip(mid).findFirst().get();
                }
                int q1p = n / 4;
                int q3p = n - q1p - 1;
                q1 = sd.stream().skip(q1p).findFirst().get();
                q3 = sd.stream().skip(q3p).findFirst().get();
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
                + ", median= " + median
                + ", q1= " + q1.toString()
                + ", q3=" + q3.toString()
                + ", nZero=" + nZero
                + ", nNeg=" + nNeg;
    }

    @Override
    public boolean equals(Object o) {
        if (o instanceof Stats_BigDecimal1) {
            Stats_BigDecimal1 s = (Stats_BigDecimal1) o;
            if (super.equals(o)) {
                if (s.nNeg == nNeg) {
                    if (s.nZero == nZero) {
                        if (s.q1.compareTo(q1) == 0) {
                            if (s.q3.compareTo(q3) == 0) {
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
        hash = 19 * hash + Objects.hashCode(this.median);
        hash = 19 * hash + Objects.hashCode(this.q1);
        hash = 19 * hash + Objects.hashCode(this.q3);
        hash = 19 * hash + this.nZero;
        hash = 19 * hash + this.nNeg;
        return hash;
    }

}
