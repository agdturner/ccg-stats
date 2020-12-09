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
import java.util.Objects;

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
            if (s.hashCode() == this.hashCode()) {
                if (super.equals(o)) {
                    if (s.nNeg == nNeg) {
                        if (s.nZero == nZero) {
                            if (s.q1.equals(q1)) {
                                if (s.q3.equals(q3)) {
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
        hash = 19 * hash + Objects.hashCode(this.median);
        hash = 19 * hash + Objects.hashCode(this.q1);
        hash = 19 * hash + Objects.hashCode(this.q3);
        hash = 19 * hash + this.nZero;
        hash = 19 * hash + this.nNeg;
        return hash;
    }

}
