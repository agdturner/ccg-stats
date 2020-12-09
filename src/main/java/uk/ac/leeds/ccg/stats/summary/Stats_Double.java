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
import java.util.DoubleSummaryStatistics;

/**
 * A POJO for storing summary statistics for a collection of double values.
 *
 * @author Andy Turner
 * @version 1.0
 */
public class Stats_Double extends Stats_n {

    /**
     * For storing the sum of all values.
     */
    public double sum;

    /**
     * For storing the mean average.
     */
    public double mean;

    /**
     * For storing the minimum value.
     */
    public double min;

    /**
     * For storing the maximum value.
     */
    public double max;
    
    public Stats_Double(){}
    
    /**
     * @param data A collection of values that summary statistics are calculated
     * for.
     */
    public Stats_Double(Collection<Double> data) {
        DoubleSummaryStatistics stats = data.parallelStream().collect(
                DoubleSummaryStatistics::new,
                DoubleSummaryStatistics::accept,
                DoubleSummaryStatistics::combine);
        max = stats.getMax();
        min = stats.getMin();
        sum = stats.getSum();
        mean = stats.getAverage();
        n = data.size();
    }
    
    @Override
    public String toString() {
        return getClass().getName() + "[" + toString1() + "]";
    }
    
    @Override
    public String toString1() {
        return super.toString1()
                + ", sum=" + Double.toString(sum)
                + ", min=" + Double.toString(min)
                + ", max=" + Double.toString(max)
                + ", mean=" + Double.toString(mean);
    }
    
    @Override
    public boolean equals(Object o) {
        if (o instanceof Stats_Double) {
            Stats_Double s = (Stats_Double) o;
            if (s.hashCode() == this.hashCode()) {
                if (s.n == n) {
                    if (s.sum == sum) {
                        if (s.min == min) {
                            if (s.max == max) {
                                if (s.mean == mean) {
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
        hash = 83 * hash + (int) (Double.doubleToLongBits(this.sum) ^ (Double.doubleToLongBits(this.sum) >>> 32));
        hash = 83 * hash + (int) (Double.doubleToLongBits(this.mean) ^ (Double.doubleToLongBits(this.mean) >>> 32));
        hash = 83 * hash + (int) (Double.doubleToLongBits(this.min) ^ (Double.doubleToLongBits(this.min) >>> 32));
        hash = 83 * hash + (int) (Double.doubleToLongBits(this.max) ^ (Double.doubleToLongBits(this.max) >>> 32));
        return hash;
    }
}
