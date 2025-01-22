/*
 * Copyright 2025 Andy Turner, University of Leeds.
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
package uk.ac.leeds.ccg.stats.range;

/**
 * For a double number range inclusive of min and exclusive of max.
 * 
 * @author Andy Turner
 */
public class Stats_RangeDouble<T extends Comparable<T>> implements Comparable<Stats_RangeDouble<T>> {
    
    double min;

    double max;
    
    public Stats_RangeDouble(double min, double max) {
        this.min = min;
        this.max = max;
    }
    
    public boolean contains(double value) {
        return value >= min && value < max;
    }

    @Override
    public int compareTo(Stats_RangeDouble<T> other) {
        if (this.min == other.min) {
            return Double.compare(this.max, other.max);
        } else {
            return Double.compare(this.min, other.min);
        }
    }
    
}
