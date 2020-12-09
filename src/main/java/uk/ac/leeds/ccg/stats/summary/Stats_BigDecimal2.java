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
public class Stats_BigDecimal2 extends Stats_BigDecimal {

    /**
     * For storing the sum of all the (differences from the mean).
     */
    public BigDecimal m1;

    /**
     * For storing the sum of all the (differences from the mean squared).
     */
    public BigDecimal m2;

    /**
     * For storing the sum of all the (differences from the mean cubed).
     */
    public BigDecimal m3;

    /**
     * For storing the sum of all the (differences from the mean squared
     * squared).
     */
    public BigDecimal m4;
    
    @Override
    public String toString() {
        return getClass().getName() + "[" + toString1() + "]";
    }
    
    @Override
    public String toString1() {
        return super.toString1()
                + ", m1=" + m1.toString()
                + ", m2=" + m2.toString()
                + ", m3=" + m3.toString()
                + ", m4=" + m4.toString();
    }
    
    @Override
    public boolean equals(Object o) {
        if (o instanceof Stats_BigDecimal2) {
            Stats_BigDecimal2 s = (Stats_BigDecimal2) o;
            if (s.hashCode() == this.hashCode()) {
                if (super.equals(o)) {
                    if (s.m1.compareTo(m1) == 0) {
                        if (s.m2.compareTo(m2) == 0) {
                            if (s.m3.compareTo(m3) == 0) {
                                if (s.m4.compareTo(m4) == 0) {
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
        int hash = 7;
        hash = 41 * hash + Objects.hashCode(this.m1);
        hash = 41 * hash + Objects.hashCode(this.m2);
        hash = 41 * hash + Objects.hashCode(this.m3);
        hash = 41 * hash + Objects.hashCode(this.m4);
        return hash;
    }
}
