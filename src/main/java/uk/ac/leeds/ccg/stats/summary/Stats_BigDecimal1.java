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
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

/**
 * A POJO for storing summary statistics for a collection of values stored as
 * BigDecimal.
 *
 * @TODO Support adding further collections of values.
 *
 * @author Andy Turner
 * @version 1.0
 */
public class Stats_BigDecimal1 extends Stats_BigDecimal {

    private static final long serialVersionUID = 1L;

    /**
     * For storing the median.
     */
    public BigRational median;

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
    public BigInteger nZero;

    /**
     * For storing the number of negative values.
     */
    public BigInteger nNeg;

    public Stats_BigDecimal1() {
    }

    /**
     * @param data The data collection.
     * @param dp The decimal places.
     * @param rm The RoundingMode.
     */
    public Stats_BigDecimal1(Collection<BigDecimal> data) {
        super(data);
        nNeg = BigInteger.ZERO;
        nZero = BigInteger.ZERO;
        int dataSize = data.size();
        int c;
        switch (dataSize) {
            case 0:
                break;
            case 1:
                BigDecimal v = data.stream().findAny().get();
                median = BigRational.valueOf(v);
                c = v.compareTo(BigDecimal.ZERO);
                if (c == -1) {
                    nNeg = BigInteger.ONE;
                } else if (c == 0) {
                    nZero = BigInteger.ONE;
                }
                q1 = v;
                q3 = v;
                break;
            default:
                for (BigDecimal x : data) {
                    c = x.compareTo(BigDecimal.ZERO);
                    if (c == -1) {
                        nNeg = nNeg.add(BigInteger.ONE);
                    } else if (c == 0) {
                        nZero = nZero.add(BigInteger.ONE);
                    }
                }
                List<BigDecimal> sd = data.stream().sorted().collect(Collectors.toList());
                if (dataSize % 2 == 0) {
                    median = BigRational.valueOf(sd.stream()
                            .skip(dataSize / 2 - 1).limit(2)
                            .reduce(BigDecimal.ZERO, BigDecimal::add))
                            .divide(2);
                } else {
                    int mid = dataSize / 2;
                    median = BigRational.valueOf(sd.stream()
                            .skip(mid).findFirst().get());
                }
                int q1p = dataSize / 4;
                int q3p = dataSize - q1p - 1;
                q1 = sd.stream().skip(q1p).findFirst().get();
                q3 = sd.stream().skip(q3p).findFirst().get();
                break;
        }
    }

    @Override
    public String toString() {
        return getClass().getName() + "["
                + super.toString()
                + ", median= " + median
                + ", q1= " + q1.toString()
                + ", q3=" + q3.toString()
                + ", nZero=" + nZero
                + ", nNeg=" + nNeg
                + "]";
    }

    /**
     *
     * @param o
     * @return
     */
    @Override
    public boolean equals(Object o) {
        if (o instanceof Stats_BigDecimal1) {
            Stats_BigDecimal1 s = (Stats_BigDecimal1) o;
            //if (this.hashCode() == o.hashCode()) {
                if (super.equals(o)) {
                    if (this.median.compareTo(s.median) == 0) {
                        if (this.q1.compareTo(s.q1) == 0) {
                            if (this.q3.compareTo(s.q3) == 0) {
                                if (this.nZero.compareTo(s.nZero) == 0) {
                                    if (this.nNeg.compareTo(s.nNeg) == 0) {
                                        return true;
                                    }
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
        hash = 79 * hash + Objects.hashCode(this.median);
        hash = 79 * hash + Objects.hashCode(this.q1);
        hash = 79 * hash + Objects.hashCode(this.q3);
        hash = 79 * hash + Objects.hashCode(this.nZero);
        hash = 79 * hash + Objects.hashCode(this.nNeg);
        return hash;
    }
}
