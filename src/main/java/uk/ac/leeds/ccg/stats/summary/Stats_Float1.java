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
 * <li>This will break when more values are added than can be stored in a single
 * {@link Collection}.</li>
 * </ul>
 *
 * @author Andy Turner
 * @version 1.0
 */
public class Stats_Float1 extends Stats_Float {

    private static final long serialVersionUID = 1L;

    /**
     * An ordered store of the values.
     */
    List<Float> data;

    /**
     * Records if {@link data} has changed since last {@link #init()}.
     */
    protected boolean isUpToDate;

    /**
     * For storing the median value.
     */
    protected BigRational median;

    /**
     * For storing the lower inter quartile range value.
     */
    protected float q1;

    /**
     * For storing the upper inter quartile range value.
     */
    protected float q3;

    /**
     * For storing the number of values equal to zero.
     */
    public BigInteger nZero;

    /**
     * For storing the number of negative values.
     */
    public BigInteger nNeg;

    /**
     * Moments
     */
    protected Stats_Moments moments;
    
    /**
     * Create.
     */
    public Stats_Float1() {
    }

    /**
     * @param d The initial collection of values. Elements should not be
     * {@code null, NaN, NEGATIVE_INFINITY, POSITIVE_INFINITY}.
     */
    public Stats_Float1(Collection<Float> d) {
        super(d);
        data = d.stream().sorted().collect(Collectors.toList());
        init();
    }

    /**
     * Initialises statistics.
     */
    protected final void init() {
        super.init(data);
        nNeg = BigInteger.ZERO;
        nZero = BigInteger.ZERO;
        int dataSize = data.size();
        n = BigInteger.valueOf(dataSize);
        switch (dataSize) {
            case 0:
                break;
            case 1:
                Float v = data.stream().findAny().get();
                median = BigRational.valueOf(v);
                int c = v.compareTo(0.0f);
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
                int h = dataSize / 2;
                if (dataSize % 2 == 0) {
                    median = BigRational.valueOf(
                            BigDecimal.valueOf(data.get(h - 1))
                                    .add(BigDecimal.valueOf(data.get(h))))
                            .divide(2);
                } else {
                    median = BigRational.valueOf(data.get(h));
                }
                int q1p = dataSize / 4;
                q1 = data.get(q1p);
                q3 = data.get(dataSize - q1p - 1);
                mean = BigRational.valueOf(sum).divide(dataSize);
                break;
        }
        max = data.get(data.size() - 1);
        min = data.get(0);
        isUpToDate = true;
    }

    @Override
    public String toString() {
        return getClass().getName()
                + "[" + super.toString()
                + ", median=" + getMedian()
                + ", q1= " + getQ1()
                + ", q3=" + getQ3()
                + ", nZero=" + getNZero()
                + ", nNeg=" + getNNeg()
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

    /**
     * @return {@link #median} for the collection computing it if necessary.
     */
    public BigRational getMedian() {
        if (!isUpToDate) {
            init();
        }
        return median;
    }

    /**
     * @return {@link #q1} for the collection computing it if necessary.
     */
    public float getQ1() {
        if (!isUpToDate) {
            init();
        }
        return q1;
    }

    /**
     * @return {@link #q3} for the collection computing it if necessary.
     */
    public float getQ3() {
        if (!isUpToDate) {
            init();
        }
        return q3;
    }

    /**
     * @return {@link #nNeg} for the collection computing it if necessary.
     */
    public BigInteger getNNeg() {
        if (!isUpToDate) {
            init();
        }
        return nNeg;
    }

    /**
     * @return {@link #nZero} for the collection computing it if necessary.
     */
    public BigInteger getNZero() {
        if (!isUpToDate) {
            init();
        }
        return nZero;
    }
    
    /**
     * @return {@link #max} 
     */
    @Override
    public float getMax() {
        if (!isUpToDate) {
            init();
        }
        return max;
    }

    /**
     * @return {@link #min} 
     */
    @Override
    public float getMin() {
        if (!isUpToDate) {
            init();
        }
        return min;
    }

    /**
     * @return {@link #sum} 
     */
    @Override
    public BigDecimal getSum() {
        if (!isUpToDate) {
            init();
        }
        return sum;
    }
    
    /**
     * @return {@link #n} 
     */
    @Override
    public BigInteger getN() {
        if (!isUpToDate) {
            init();
        }
        return n;
    }
    
    /**
     * @return {@link #mean} 
     */
    @Override
    public BigRational getMean() {
        if (!isUpToDate) {
            init();
        }
        return mean;
    }
    
    /**
     * Adds a single value to {@link #data}.
     * 
     * @param x The value to add. Should not be
     * {@code null, NaN, NEGATIVE_INFINITY, POSITIVE_INFINITY}.
     */
    public void add(Float x) {
        data.add(x);
        isUpToDate = false;
        getMoments().isUpToDate = false;
    }

    /**
     * Adds a collection of values to {@link #data}.
     *
     * @param c The collection of values to add. Values should not be
     * {@code null, NaN, NEGATIVE_INFINITY, POSITIVE_INFINITY}.
     */
    public void add(Collection<Float> c) {
        data.addAll(c);
        isUpToDate = false;
        getMoments().isUpToDate = false;
    }

    /**
     * @return {@link #moments} initialising and updating as necessary.
     */
    public Stats_Moments getMoments() {
        if (moments == null) {
            moments = new Stats_Moments(this);
        }
        return moments;
    }
}
