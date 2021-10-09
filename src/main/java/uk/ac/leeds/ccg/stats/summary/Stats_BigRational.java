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

import java.util.Collection;
import java.util.Iterator;
import java.util.Objects;
import uk.ac.leeds.ccg.math.number.Math_BigRational;

/**
 * POJO for summary statistics of BigRational values.
 *
 * Potential future developments:
 * <ul>
 * <li>Extend to provide more statistics and support adding further collections
 * of values.</li>
 * </ul>
 *
 * @author Andy Turner
 * @version 1.0
 */
public class Stats_BigRational extends Stats_Abstract {

    private static final long serialVersionUID = 1L;

    /**
     * For storing the sum of all values.
     */
    public Math_BigRational sum;

    /**
     * For storing the minimum value.
     */
    public Math_BigRational min;

    /**
     * For storing the maximum value.
     */
    public Math_BigRational max;

    /**
     * Description.
     *
     * @return A string representation.
     */
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
     * Create.
     */
    public Stats_BigRational() {
    }

    /**
     * @param d The collection of values.
     */
    public Stats_BigRational(Collection<Math_BigRational> d) {
        super(d.size());
        int dataSize = d.size();
        switch (dataSize) {
            case 0:
                break;
            case 1:
                Math_BigRational v = d.stream().findAny().get();
                sum = v;
                min = v;
                max = v;
                mean = v;
                break;
            default:
                sum = Math_BigRational.ZERO;
                Math_BigRational v2 = d.iterator().next();
                min = v2;
                max = v2;
                Iterator<Math_BigRational> ite = d.iterator();
                while (ite.hasNext()) {
                    Math_BigRational i = ite.next();
                    sum = sum.add(i);
                    min = Math_BigRational.min(i, min);
                    max = Math_BigRational.max(i, max);
                }
                mean = sum.divide(dataSize);
        }
    }

    /**
     * @param o The object to test for equality.
     * @return {@code true} iff {@code this} and o are equal.
     */
    @Override
    public boolean equals(Object o) {
        if (o instanceof Stats_BigRational) {
            Stats_BigRational s = (Stats_BigRational) o;
            //if (s.hashCode() == this.hashCode()) {
            if (super.equals(o)) {
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
            //}
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
