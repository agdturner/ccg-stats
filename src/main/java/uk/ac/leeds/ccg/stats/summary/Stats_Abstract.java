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
import java.io.Serializable;
import java.math.BigInteger;
import java.util.Objects;

/**
 * A POJO for storing summary statistics.
 *
 * @author Andy Turner
 * @version 1.0
 */
public abstract class Stats_Abstract implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * For storing the number of values.
     */
    protected BigInteger n;

    /**
     * For storing the mean average.
     */
    protected BigRational mean;
    
    public Stats_Abstract() {
        n = BigInteger.ZERO;
    }

    /**
     * @param n What {@link #n} is set to.
     */
    public Stats_Abstract(BigInteger n) {
        this.n = n;
    }

    /**
     * @param n What {@link #n} is set to.
     */
    public Stats_Abstract(long n) {
        this.n = BigInteger.valueOf(n);
    }

    @Override
    public String toString() {
        return "n=" + n;
    }

    /**
     * @param o The object to test for equality.
     * @return {@code true} iff {@code this} and o are equal.
     */
    @Override
    public boolean equals(Object o) {
        if (o instanceof Stats_Abstract) {
            Stats_Abstract s = (Stats_Abstract) o;
            //if (this.hashCode() == o.hashCode()) {
            return this.n.compareTo(s.n) == 0;
            //}
        }
        return false;
    }

    @Override
    public int hashCode() {
        int hash = 5;
        hash = 73 * hash + Objects.hashCode(this.n);
        return hash;
    }
    
    /**
     * @return {@link #m} updating it first if necesary. 
     */
    public BigInteger getN() {
        return n;
    }
    
    /**
     * @return {@link #mean} 
     */
    public BigRational getMean() {
        return mean;
    }
}
