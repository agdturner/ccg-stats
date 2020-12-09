/**
 * Copyright 2012 CCG, The University of Leeds, UK
 *
 * Licensed under the Apache License, Version 2.0 (the "License"); you may not
 * use this file except in compliance with the License. You may obtain a copy of
 * the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS, WITHOUT
 * WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied. See the
 * License for the specific language governing permissions and limitations under
 * the License.
 */
package uk.ac.leeds.ccg.stats.examples;

import ch.obermuhlner.math.big.BigRational;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.math.RoundingMode;
import java.util.Collection;
import java.util.DoubleSummaryStatistics;
import java.util.Iterator;
import java.util.List;
import java.util.stream.Collectors;
import uk.ac.leeds.ccg.math.Math_BigDecimal;
import uk.ac.leeds.ccg.stats.summary.Stats_BigDecimal;
import uk.ac.leeds.ccg.stats.summary.Stats_BigDecimal1;
import uk.ac.leeds.ccg.stats.summary.Stats_BigRational;
import uk.ac.leeds.ccg.stats.summary.Stats_Double;
import uk.ac.leeds.ccg.stats.summary.Stats_Double1;

/**
 * @author Andy Turner
 * @version 1.0.0
 */
public class Stats {

    public Stats() {
    }

    /**
     * @param data The data collection.
     * @param dp The decimal places.
     * @param rm The RoundingMode.
     * @return Stats_BigDecimal
     */
    public static Stats_BigDecimal getStats_BigDecimal(
            Collection<BigDecimal> data, int dp, RoundingMode rm) {
        Stats_BigDecimal r = new Stats_BigDecimal();
        r.n = data.size();
        switch (r.n) {
            case 0:
                return r;
            case 1:
                BigDecimal v = data.stream().findAny().get();
                r.sum = v;
                r.min = v;
                r.max = v;
                r.mean = v;
                return r;
            default:
                r.sum = BigDecimal.ZERO;
                BigDecimal v2 = data.iterator().next();
                r.min = v2;
                r.max = v2;
                Iterator<BigDecimal> ite = data.iterator();
                while (ite.hasNext()) {
                    BigDecimal i = ite.next();
                    r.sum = r.sum.add(i);
                    r.min = r.min.min(i);
                    r.max = r.max.max(i);
                }
                r.mean = Math_BigDecimal.divideRoundIfNecessary(r.sum,
                        BigDecimal.valueOf(r.n), dp, rm);
                return r;
        }
    }

    /**
     * @param data The data collection.
     * @param dp The decimal places.
     * @param rm The RoundingMode.
     * @return Stats_BigDecimal1
     */
    public static Stats_BigDecimal1 getStats_BigDecimal1(
            Collection<BigDecimal> data, int dp, RoundingMode rm) {
        Stats_BigDecimal1 r = new Stats_BigDecimal1();
        r.n = data.size();
        switch (r.n) {
            case 0:
                return r;
            case 1:
                BigDecimal v = data.stream().findAny().get();
                r.sum = v;
                r.min = v;
                r.max = v;
                r.mean = v;
                r.median = v;
                int c = v.compareTo(BigDecimal.ZERO);
                if (c == -1) {
                    r.nNeg = 1;
                } else if (c == 0) {
                    r.nZero = 1;
                }
                r.q1 = v;
                r.q3 = v;
                return r;
            default:
                r.sum = BigDecimal.ZERO;
                BigDecimal v2 = data.iterator().next();
                r.min = v2;
                r.max = v2;
                Iterator<BigDecimal> ite = data.iterator();
                while (ite.hasNext()) {
                    BigDecimal i = ite.next();
                    r.sum = r.sum.add(i);
                    r.min = r.min.min(i);
                    r.max = r.max.max(i);
                    int co = i.compareTo(BigDecimal.ZERO);
                    if (co == -1) {
                        r.nNeg++;
                    } else if (co == 0) {
                        r.nZero++;
                    }
                }
                List<BigDecimal> sd = data.stream().sorted().collect(Collectors.toList());
                if (r.n % 2 == 0) {
                    r.median = sd.stream().skip(r.n / 2 - 1).limit(2)
                            .reduce(BigDecimal.ZERO, BigDecimal::add)
                            .divide(BigDecimal.valueOf(2));
                } else {
                    int mid = r.n / 2;
                    r.median = sd.stream().skip(mid).findFirst().get();
                }
                int q1p = r.n / 4;
                int q3p = r.n - q1p - 1;
                r.q1 = sd.stream().skip(q1p).findFirst().get();
                r.q3 = sd.stream().skip(q3p).findFirst().get();
                r.mean = Math_BigDecimal.divideRoundIfNecessary(r.sum,
                        BigDecimal.valueOf(r.n), dp, rm);
                return r;
        }
    }

    /**
     * @param data The data collection.
     * @return Stats_BigDecimal
     */
    public static Stats_BigRational getStats_BigRational(
            Collection<BigRational> data) {
        Stats_BigRational r = new Stats_BigRational();
        r.n = data.size();
        switch (r.n) {
            case 0:
                return r;
            case 1:
                BigRational v = data.stream().findAny().get();
                r.sum = v;
                r.min = v;
                r.max = v;
                r.mean = v;
                return r;
            default:
                r.sum = BigRational.ZERO;
                BigRational v2 = data.iterator().next();
                r.min = v2;
                r.max = v2;
                Iterator<BigRational> ite = data.iterator();
                while (ite.hasNext()) {
                    BigRational i = ite.next();
                    r.sum = r.sum.add(i);
                    r.min = BigRational.min(i, r.min);
                    r.max = BigRational.max(i, r.max);
                }
                r.mean = r.sum.divide(BigInteger.valueOf(r.n));
                return r;
        }
    }

    /**
     * Calculates and returns summary statistics for {@code c}.
     *
     * @param data A collection of values that summary statistics are calculated
     * for.
     * @return Stats_Double
     */
    public static Stats_Double getStats_Double(Collection<Double> data) {
        DoubleSummaryStatistics stats = data.parallelStream().collect(
                DoubleSummaryStatistics::new,
                DoubleSummaryStatistics::accept,
                DoubleSummaryStatistics::combine);
        Stats_Double r = new Stats_Double();
        r.max = stats.getMax();
        r.min = stats.getMin();
        r.sum = stats.getSum();
        r.mean = stats.getAverage();
        r.n = data.size();
        return r;
    }

    /**
     * Calculates and returns summary statistics for {@code c}.
     *
     * @param data A collection of values that summary statistics are calculated
     * for.
     * @return Stats_Double1
     */
    public static Stats_Double1 getStats_Double1(Collection<Double> data) {
        DoubleSummaryStatistics stats = data.parallelStream().collect(
                DoubleSummaryStatistics::new,
                DoubleSummaryStatistics::accept,
                DoubleSummaryStatistics::combine);
        Stats_Double1 r = new Stats_Double1();
        r.max = stats.getMax();
        r.min = stats.getMin();
        r.sum = stats.getSum();
        r.mean = stats.getAverage();
        r.nNeg = 0;
        r.nZero = 0;
        r.n = data.size();
        switch (r.n) {
            case 0:
                return r;
            case 1:
                Double v = data.stream().findAny().get();
                r.sum = v;
                r.min = v;
                r.max = v;
                r.mean = v;
                r.median = v;
                int c = v.compareTo(0.0d);
                if (c == -1) {
                    r.nNeg = 1;
                } else if (c == 0) {
                    r.nZero = 1;
                }
                r.q1 = v;
                r.q3 = v;
                return r;
            default:
                r.sum = 0.0d;
                Double v2 = data.iterator().next();
                r.min = v2;
                r.max = v2;
                Iterator<Double> ite = data.iterator();
                while (ite.hasNext()) {
                    Double i = ite.next();
                    r.sum += i;
                    r.min = Double.min(r.min, i);
                    r.max = Double.max(r.max, i);
                    int co = i.compareTo(0.0d);
                    if (co == -1) {
                        r.nNeg++;
                    } else if (co == 0) {
                        r.nZero++;
                    }
                }
                List<Double> sd = data.stream().sorted().collect(Collectors.toList());
                if (r.n % 2 == 0) {
                    r.median = sd.stream().skip(r.n / 2 - 1).limit(2)
                            .reduce(0.0d, Double::sum) / (2.0d);
                } else {
                    int mid = r.n / 2;
                    r.median = sd.stream().skip(mid).findFirst().get();
                }
                int q1p = r.n / 4;
                int q3p = r.n - q1p - 1;
                r.q1 = sd.stream().skip(q1p).findFirst().get();
                r.q3 = sd.stream().skip(q3p).findFirst().get();
                r.mean = r.sum / (double) r.n;
                return r;
        }
    }
}
