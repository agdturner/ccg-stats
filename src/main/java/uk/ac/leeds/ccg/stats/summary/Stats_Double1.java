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

/**
 * A POJO for storing summary statistics for a collection of double values.
 *
 * @author Andy Turner
 * @version 1.0
 */
public class Stats_Double1 extends Stats_Double {

    /**
     * For storing the median value.
     */
    public double median;

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
    public long nZero;

    /**
     * For storing the number of negative values.
     */
    public long nNeg;

    @Override
    public String toString() {
        return getClass().getName() + "[" + toString1() + "]";
    }

    @Override
    public String toString1() {
        return super.toString1()
                + ", median=" + Double.toString(median)
                + ", q1= " + Double.toString(q1)
                + ", q3=" + Double.toString(q3)
                + ", nZero=" + Long.toString(nZero)
                + ", nNeg=" + Long.toString(nNeg);
    }

    @Override
    public boolean equals(Object o) {
        if (o instanceof Stats_Double1) {
            Stats_Double1 s = (Stats_Double1) o;
            if (s.hashCode() == this.hashCode()) {
                if (super.equals(o)) {
                    if (s.median == median) {
                        if (s.nNeg == nNeg) {
                            if (s.nZero == nZero) {
                                if (s.q1 == q1) {
                                    if (s.q3 == q3) {
                                        return true;
                                    }
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
        hash = 89 * hash + (int) (Double.doubleToLongBits(this.median) ^ (Double.doubleToLongBits(this.median) >>> 32));
        hash = 89 * hash + (int) (Double.doubleToLongBits(this.q1) ^ (Double.doubleToLongBits(this.q1) >>> 32));
        hash = 89 * hash + (int) (Double.doubleToLongBits(this.q3) ^ (Double.doubleToLongBits(this.q3) >>> 32));
        hash = 89 * hash + (int) (this.nZero ^ (this.nZero >>> 32));
        hash = 89 * hash + (int) (this.nNeg ^ (this.nNeg >>> 32));
        return hash;
    }

}
