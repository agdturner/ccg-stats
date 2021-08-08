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
public class Stats_Float1 extends Stats_Float {

    private static final long serialVersionUID = 1L;

    /**
     * For storing the median value.
     */
    public BigRational median;

    /**
     * For storing the lower inter quartile range value.
     */
    public double q1;

    /**
     * For storing the upper inter quartile range value.
     */
    public double q3;

    /**
     * For storing the number of values equal to zero.
     */
    public BigInteger nZero;

    /**
     * For storing the number of negative values.
     */
    public BigInteger nNeg;

    /**
     * Create.
     */
    public Stats_Float1() {
    }

    /**
     * @param data The data collection.
     */
    public Stats_Float1(Collection<Float> data) {
        super(data);
        nNeg = BigInteger.ZERO;
        nZero = BigInteger.ZERO;
        int dataSize = data.size();
        int c;
        switch (dataSize) {
            case 0:
                break;
            case 1:
                Float v = data.stream().findAny().get();
                median = BigRational.valueOf(v);
                c = v.compareTo(0.0f);
                if (c == -1) {
                    nNeg = nNeg.add(BigInteger.ONE);
                } else if (c == 0) {
                    nZero = nZero.add(BigInteger.ONE);
                }
                q1 = v;
                q3 = v;
                break;
            default:
                for (Float x : data) {
                    c = x.compareTo(0.0f);
                    if (c == -1) {
                        nNeg = nNeg.add(BigInteger.ONE);
                    } else if (c == 0) {
                        nZero = nZero.add(BigInteger.ONE);
                    }
                }
                List<Float> sd = data.stream().sorted().collect(Collectors.toList());
                int h = dataSize / 2;
                if (dataSize % 2 == 0) {
                    median = BigRational.valueOf(
                            BigDecimal.valueOf(sd.get(h - 1))
                                    .add(BigDecimal.valueOf(sd.get(h))))
                            .divide(2);
                } else {
                    median = BigRational.valueOf(sd.get(h));
                }
                int q1p = dataSize / 4;
                int q3p = dataSize - q1p - 1;
                q1 = sd.stream().skip(q1p).findFirst().get();
                q3 = sd.stream().skip(q3p).findFirst().get();
                mean = BigRational.valueOf(sum).divide(dataSize);
                break;
        }
    }

    @Override
    public String toString() {
        return getClass().getName()
                + "[" + super.toString()
                + ", median=" + median
                + ", q1= " + q1
                + ", q3=" + q3
                + ", nZero=" + nZero
                + ", nNeg=" + nNeg
                + "]";
    }

    /**
     * @param o The object to test for equality.
     * @return {@code true} iff {@code this} and o are equal.
     */
    @Override
    public boolean equals(Object o) {
        if (o instanceof Stats_Float1) {
            Stats_Float1 s = (Stats_Float1) o;
            //if (this.hashCode() == o.hashCode()) {
            if (q1 == s.q1) {
                if (q3 == s.q3) {
                    if (this.median.compareTo(s.median) == 0) {
                        if (this.nZero.compareTo(s.nZero) == 0) {
                            if (this.nNeg.compareTo(s.nNeg) == 0) {
                                if (super.equals(o)) {
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
        hash = 37 * hash + Objects.hashCode(this.median);
        hash = 37 * hash + (int) (Double.doubleToLongBits(this.q1) ^ (Double.doubleToLongBits(this.q1) >>> 32));
        hash = 37 * hash + (int) (Double.doubleToLongBits(this.q3) ^ (Double.doubleToLongBits(this.q3) >>> 32));
        hash = 37 * hash + Objects.hashCode(this.nZero);
        hash = 37 * hash + Objects.hashCode(this.nNeg);
        return hash;
    }
}
