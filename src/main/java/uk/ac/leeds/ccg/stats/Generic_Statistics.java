/**
 * Copyright 2012 Andy Turner, The University of Leeds, UK
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
package uk.ac.leeds.ccg.stats;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.DoubleSummaryStatistics;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map.Entry;
import java.util.TreeMap;
import uk.ac.leeds.ccg.math.Math_BigDecimal;
import uk.ac.leeds.ccg.generic.util.Generic_Collections;

/**
 * @author Andy Turner
 * @version 1.0.0
 */
public class Generic_Statistics {

    public Generic_Statistics() {
    }

    /**
     * Calculates and returns the sum of all values in data.
     *
     * @param data the list of values to be summed.
     * @return the sum of all values in data.
     */
    public static BigDecimal getSum(List<BigDecimal> data) {
        BigDecimal r = BigDecimal.ZERO;
        Iterator<BigDecimal> ite = data.iterator();
        while (ite.hasNext()) {
            r = r.add(ite.next());
        }
        return r;
    }

    /**
     * For printing stats to std.out.
     *
     * @param stats Expected to be of length 11 with:
     * <ul>
     * <li>stats[0] is the sum</li>
     * <li>stats[1] is the mean</li>
     * <li>stats[2] is the median</li>
     * <li>stats[3] is the q1</li>
     * <li>stats[4] is the q3</li>
     * <li>stats[5] is the mode</li>
     * <li>stats[6] is the min</li>
     * <li>stats[7] is the max</li>
     * <li>stats[8] is the number of different values</li>
     * <li>stats[9] is the number of different values in the mode</li>
     * <li>stats[10] is the number of same values in any part of the mode</li>
     * </ul>
     */
    public static void printStatistics(BigDecimal[] stats) {
        System.out.println("Sum " + stats[0]);
        System.out.println("Mean " + stats[1]);
        System.out.println("Median " + stats[2]);
        System.out.println("q1 " + stats[3]);
        System.out.println("q3 " + stats[4]);
        System.out.println("Mode " + stats[5]);
        System.out.println("Min " + stats[6]);
        System.out.println("Max " + stats[7]);
        System.out.println("Number Of Different Values " + stats[8]);
        System.out.println("Number Of Different Values In The Mode "
                + stats[9]);
        System.out.println("Number Of Same Values In Any Part Of The Mode "
                + stats[10]);
    }

    /**
     * Calculates and returns a number of statistics for data.
     *
     * @TODO skewness and kurtosis...
     * @param data the data.
     * @param dp decimal places used for calculations.
     * @param rm RoundingMode
     * @return Object[] r where:
     * <ul>
     * <li>r[0] = BigDecimal[] obtained from
     * {@link #getSummaryStatistics_0(java.util.ArrayList, int, java.math.RoundingMode)</li>
     * <li>r[1] = BigDecimal[] where:
     * <ul>
     * <li>r[1][0] = moment1 = sum of the (differences from the mean)</li>
     * <li>r[1][1] = moment2 = sum of the (differences from the mean
     * squared)</li>
     * <li>r[1][2] = moment3 = sum of the (differences from the mean cubed)</li>
     * <li>r[1][3] = moment4 = sum of the (differences from the mean squared
     * squared)</li>
     * <li>r[1][4] = variance = (sum of the (differences from the mean))</li>
     * </ul>
     * </ul>
     */
    public static Object[] getSummaryStatistics_1(ArrayList<BigDecimal> data,
            int dp, RoundingMode rm) {
        Object[] r = new Object[2];
        BigDecimal[] stats0 = getSummaryStatistics_0(data, dp, rm);
        r[0] = stats0;
        BigDecimal[] stats1 = new BigDecimal[6];

        BigDecimal moment1 = BigDecimal.ZERO;
        BigDecimal moment2 = BigDecimal.ZERO;
        BigDecimal moment3 = BigDecimal.ZERO;
        BigDecimal moment4 = BigDecimal.ZERO;

        // Deal with special cases
        int n = data.size();
        BigDecimal n_BigDecimal = BigDecimal.valueOf(n);
        if (n < 2) {
            stats1[0] = BigDecimal.ZERO;
            stats1[1] = BigDecimal.ZERO;
            stats1[2] = BigDecimal.ZERO;
            stats1[3] = BigDecimal.ZERO;
            stats1[4] = BigDecimal.ZERO;
        }
        BigDecimal value;
        BigDecimal meandiff;
        Iterator<BigDecimal> ite = data.iterator();
        while (ite.hasNext()) {
            value = ite.next();
            meandiff = value.subtract(stats0[1]);
            moment1 = moment1.add(meandiff);
            moment2 = moment2.add(meandiff.pow(2));
            moment3 = moment3.add(meandiff.pow(3));
            moment4 = moment4.add(meandiff.pow(4));
        }
        BigDecimal variance = Math_BigDecimal.divideRoundIfNecessary(
                moment2, n_BigDecimal, dp, rm);
        stats1[0] = moment1;
        stats1[1] = moment2;
        stats1[2] = moment3;
        stats1[3] = moment4;
        stats1[4] = variance;
        try {
            stats1[5] = Math_BigDecimal.power(variance,
                    BigDecimal.valueOf(0.5d), dp, rm);
        } catch (UnsupportedOperationException e) {
            // A terrible hack!
            stats1[5] = variance.divide(BigDecimal.valueOf(2));
        }
        r[1] = stats1;
        return r;
    }

    /**
     * There is no universal agreement on calculating quartiles:
     * http://en.wikipedia.org/wiki/Quartile
     *
     * @param data
     * @param dp
     * @param rm
     * @return BigDecimal[] r where:
     * <ul>
     * <li>{@code r[0] = sum}</li>
     * <li>{@code r[1] = mean}</li>
     * <li>{@code r[2] = median}</li>
     * <li>{@code r[3] = q1}</li>
     * <li>{@code r[4] = q3}</li>
     * <li>{@code r[5] = mode}</li>
     * <li>{@code r[6] = min}</li>
     * <li>{@code r[7] = max}</li>
     * <li>{@code r[8] = numberOfDifferentValues}</li>
     * <li>{@code r[9] = numberOfDifferentValuesInMode}</li>
     * <li>{@code r[10] = numberOfSameValuesInAnyPartOfMode}</li>
     * </ul>
     */
    public static BigDecimal[] getSummaryStatistics_0(
            ArrayList<BigDecimal> data,
            int dp,
            RoundingMode rm) {
        BigDecimal[] r = new BigDecimal[12];
        // Deal with special cases
        int n = data.size();
        if (n == 0) {
            r[0] = null;
            r[1] = null;
            r[2] = null;
            r[3] = null;
            r[4] = null;
            r[5] = null;
            r[6] = null;
            r[7] = null;
            r[8] = null;
            r[9] = null;
            r[10] = null;
            return r;
        }
        if (n == 1) {
            BigDecimal v = data.get(0);
            r[0] = new BigDecimal(v.toString());
            r[1] = new BigDecimal(v.toString());
            r[2] = new BigDecimal(v.toString());
            r[3] = new BigDecimal(v.toString());
            r[4] = new BigDecimal(v.toString());
            r[5] = new BigDecimal(v.toString());
            r[6] = new BigDecimal(v.toString());
            r[7] = new BigDecimal(v.toString());
            r[8] = BigDecimal.ONE;
            r[9] = BigDecimal.ONE;
            r[10] = BigDecimal.ONE;
            return r;
        }
        if (n == 2) {
            BigDecimal v = data.get(0);
            BigDecimal v1 = data.get(1);
            r[0] = v.add(v1);
            if (v.compareTo(v1) == 0) {
                r[1] = new BigDecimal(v.toString());
                r[8] = BigDecimal.ONE;
                r[9] = BigDecimal.ONE;
                r[10] = BigDecimal.ONE;
            } else {
                r[1] = Math_BigDecimal.divideRoundIfNecessary(r[0],
                        Math_BigDecimal.TWO, dp, rm);
                r[8] = Math_BigDecimal.TWO;
                r[9] = Math_BigDecimal.TWO;
                r[10] = Math_BigDecimal.TWO;
            }
            r[2] = new BigDecimal(r[1].toString());
            r[3] = new BigDecimal(r[1].toString());
            r[4] = new BigDecimal(r[1].toString());
            r[5] = new BigDecimal(v.toString());
            r[6] = v.min(v1);
            r[7] = v.max(v1);
            return r;
        }
        ArrayList<BigDecimal> sortedData = new ArrayList<>(data);
        Collections.sort(sortedData);
        if (n == 3) {
            // q1 defined as the average of data.get(0) and data.get(1)
            // q3 defined as the average of data.get(1) and data.get(2)
            BigDecimal v = sortedData.get(0);
            BigDecimal value1 = sortedData.get(1);
            BigDecimal value2 = sortedData.get(2);
            r[0] = v.add(value1).add(value2);
            r[1] = Math_BigDecimal.divideRoundIfNecessary(r[0],
                    BigInteger.valueOf(3), dp, rm);
            r[2] = new BigDecimal(value1.toString());
            r[3] = Math_BigDecimal.divideRoundIfNecessary(v.add(value1),
                    Math_BigDecimal.TWO, dp, rm);
            r[4] = Math_BigDecimal.divideRoundIfNecessary(value1.add(value2),
                    Math_BigDecimal.TWO, dp, rm);
            if (v.compareTo(value1) == 0) {
                r[5] = new BigDecimal(v.toString());
                if (v.compareTo(value2) == 0) {
                    r[8] = BigDecimal.ONE;
                    r[9] = BigDecimal.ONE;
                    r[10] = Math_BigDecimal.TWO;
                } else {
                    r[8] = Math_BigDecimal.TWO;
                    r[9] = BigDecimal.ONE;
                    r[10] = Math_BigDecimal.TWO;
                }
            } else {
                if (value1.compareTo(value2) == 0) {
                    r[5] = new BigDecimal(value1.toString());
                    r[8] = Math_BigDecimal.TWO;
                    r[9] = BigDecimal.ONE;
                    r[10] = Math_BigDecimal.TWO;
                } else {
                    r[5] = new BigDecimal(r[1].toString());
                    r[8] = BigDecimal.valueOf(3);
                    r[9] = BigDecimal.valueOf(3);
                    r[10] = BigDecimal.ONE;
                }
            }
            r[6] = new BigDecimal(v.toString());
            r[7] = new BigDecimal(value2.toString());
            return r;
        }
        TreeMap<BigDecimal, Integer> m = new TreeMap<>();
        r[0] = BigDecimal.ZERO;
        Iterator<BigDecimal> ite = sortedData.iterator();
        int maxCount = 1;
        HashSet<BigDecimal> maxCountValues = new HashSet<>();
        while (ite.hasNext()) {
            BigDecimal v = ite.next();
            if (m.containsKey(v)) {
                int count = m.get(v) + 1;
                m.put(v, count);
                if (count == maxCount) {
                    maxCountValues.add(v);
                } else {
                    if (count > maxCount) {
                        maxCountValues = new HashSet<>();
                        maxCountValues.add(v);
                        maxCount = count;
                    }
                }
            } else {
                m.put(v, 1);
                if (maxCount == 1) {
                    maxCountValues.add(v);
                }
            }
            r[0] = r[0].add(v);
        }
        r[9] = BigDecimal.valueOf(maxCountValues.size());
        r[10] = BigDecimal.valueOf(maxCount);
        int nDifferentValues = m.size();
        if (nDifferentValues == 1) {
            BigDecimal v = sortedData.get(0);
            r[1] = new BigDecimal(v.toString());
            r[2] = new BigDecimal(v.toString());
            r[3] = new BigDecimal(v.toString());
            r[4] = new BigDecimal(v.toString());
            r[5] = new BigDecimal(v.toString());
            r[6] = new BigDecimal(v.toString());
            r[7] = new BigDecimal(v.toString());
            //r[8] = BigDecimal.ONE;
            //r[9] = BigDecimal.ONE;
            //r[10] = BigDecimal.ONE;
            //printStatistics(r);
            return r;
        }
        r[1] = Math_BigDecimal.divideRoundIfNecessary(r[0],
                BigDecimal.valueOf(n), dp, rm);
        r[8] = BigDecimal.valueOf(m.size());
        if (n == 4) {
            // q1 = data.get(1)
            // q3 = data.get(2)
            BigDecimal v = sortedData.get(0);
            BigDecimal v1 = sortedData.get(1);
            BigDecimal v2 = sortedData.get(2);
            BigDecimal v3 = sortedData.get(3);
            r[2] = Math_BigDecimal.divideRoundIfNecessary(v1.add(v2),
                    Math_BigDecimal.TWO, dp, rm);
            r[3] = new BigDecimal(v1.toString());
            r[4] = new BigDecimal(v2.toString());
            if (maxCount == 1) {
                r[5] = new BigDecimal(r[1].toString());
            } else {
                if (maxCount == 4) {
                    r[5] = new BigDecimal(v.toString());
                } else {
                    if (maxCountValues.size() == 1) {
                        r[5] = new BigDecimal(
                                maxCountValues.iterator().next().toString());
                    } else {
                        BigDecimal modeMean = BigDecimal.ZERO;
                        Iterator<BigDecimal> modeIte = maxCountValues.iterator();
                        while (modeIte.hasNext()) {
                            modeMean = modeMean.add(modeIte.next());
                        }
                        r[5] = Math_BigDecimal.divideRoundIfNecessary(modeMean,
                                BigDecimal.valueOf(maxCountValues.size()), dp,
                                rm);
                    }
                }
            }
            r[6] = new BigDecimal(v.toString());
            r[7] = new BigDecimal(v3.toString());
            return r;
        }
        boolean interpolateMedian = false;
        if (n % 2 != 1) {
            interpolateMedian = true;
        }
        int ndiv2 = n / 2;
        boolean interpolateQuartile = false;
        if (interpolateMedian == true) {
            interpolateQuartile = true;
        } else {
            if (n % 4 != 1) {
                interpolateQuartile = true;
            }
        }
        int ndiv4 = n / 4;
        int n3div4 = 3 * n / 4;
        int count = 0;
        boolean medianInitialised = false;
        boolean medianFinalised = false;
        boolean q1Initialised = false;
        boolean q1Finalised = false;
        boolean q3Initialised = false;
        boolean q3Finalised = false;
        //BigDecimal lastValue = null;
        Iterator<Entry<BigDecimal, Integer>> ite2 = m.entrySet().iterator();
        while (ite2.hasNext()) {
            Entry<BigDecimal, Integer> entry = ite2.next();
            BigDecimal v = entry.getKey();
            count += entry.getValue();
            if (!q1Finalised) {
                if (count > ndiv4) {
                    if (interpolateQuartile) {
                        if (q1Initialised) {
                            if (interpolateMedian) {
                                if (interpolateQuartile) {
                                    /*
                                     * The greater the remainder when n is 
                                     * divided by 4 the further q1 is from the 
                                     * median
                                     */
                                    if (n % 4 == 0) {
                                        r[3] = Math_BigDecimal.divideRoundIfNecessary(
                                                r[3].multiply(BigDecimal.valueOf(3)).add(v),
                                                BigDecimal.valueOf(4), dp, rm);
                                    }
                                    if (n % 4 == 2) {
                                        r[3] = Math_BigDecimal.divideRoundIfNecessary(
                                                r[3].multiply(BigDecimal.valueOf(3)).add(v),
                                                BigDecimal.valueOf(4), dp, rm);
                                    }
                                    if (n % 4 == 3) {
                                        r[3] = Math_BigDecimal.divideRoundIfNecessary(
                                                r[3].add(v), Math_BigDecimal.TWO, dp, rm);
                                    }
                                }
                            } else {
                                r[3] = Math_BigDecimal.divideRoundIfNecessary(r[3].add(v),
                                        Math_BigDecimal.TWO, dp, rm);
                            }
                            q1Finalised = true;
                        } else {
                            r[3] = new BigDecimal(v.toString());
                            q1Initialised = true;
                            if (count > ndiv4 + 1) {
                                q1Finalised = true;
                            } else {
                                if (!interpolateQuartile) {
                                    q1Finalised = true;
                                }
                            }
                        }
                    } else {
                        r[3] = new BigDecimal(v.toString());
                        q1Finalised = true;
                    }
                }
            }
            if (!medianFinalised) {
                if (count >= ndiv2) {
                    if (interpolateMedian) {
                        if (medianInitialised) {
                            r[2] = Math_BigDecimal.divideRoundIfNecessary(r[2].add(v),
                                    Math_BigDecimal.TWO, dp, rm);
                            medianFinalised = true;
                        } else {
                            r[2] = new BigDecimal(v.toString());
                            medianInitialised = true;
                            if (count > ndiv2 + 1) {
                                medianFinalised = true;
                            }
                        }
                    } else {
                        if (count > ndiv2) {
                            r[2] = new BigDecimal(v.toString());
                            medianFinalised = true;
                        }
                    }
                }
            }
            if (!q3Finalised) {
                if (count >= n3div4) {
                    if (interpolateQuartile) {
                        if (q3Initialised) {
                            if (interpolateMedian) {
                                if (interpolateQuartile) {
                                    /*
                                     * The greater the remainder when n is 
                                     * divided by 4 the further q3 is from the 
                                     * median
                                     */
                                    if (n % 4 == 0) {
                                        r[4] = Math_BigDecimal.divideRoundIfNecessary(
                                                //r[4].multiply(BigDecimal.valueOf(3)).add(value),
                                                r[4].add(v.multiply(BigDecimal.valueOf(3))),
                                                BigDecimal.valueOf(4), dp, rm);
                                    }
                                    if (n % 4 == 2) {
                                        r[4] = Math_BigDecimal.divideRoundIfNecessary(
                                                //r[4].multiply(BigDecimal.valueOf(3)).add(value),
                                                r[4].add(v.multiply(BigDecimal.valueOf(3))),
                                                BigDecimal.valueOf(4), dp, rm);
                                    }
                                    if (n % 4 == 3) {
                                        r[4] = Math_BigDecimal.divideRoundIfNecessary(r[4].add(v),
                                                Math_BigDecimal.TWO, dp, rm);
                                    }
                                }
                            } else {
                                r[4] = Math_BigDecimal.divideRoundIfNecessary(r[4].add(v),
                                        Math_BigDecimal.TWO, dp, rm);
                            }
                            q3Finalised = true;
                        } else {
                            r[4] = new BigDecimal(v.toString());
                            q3Initialised = true;
                            if (count > n3div4 + 1) {
                                q3Finalised = true;
                            } else {
                                if (!interpolateQuartile) {
                                    q3Finalised = true;
                                }
                            }
                        }
                    } else {
                        if (count > n3div4) {
                            r[4] = new BigDecimal(v.toString());
                            q3Finalised = true;
                        }
                    }
                }
            }
            //lastCount = count;
            //lastValue = v;
        }
        if (maxCountValues.size() == n) {
            // All the values are unique so set mode to be a copy of the mean
            r[5] = new BigDecimal(r[1].toString());
        } else {
            BigDecimal modeMean = BigDecimal.ZERO;
            Iterator<BigDecimal> modeIte = maxCountValues.iterator();
            while (modeIte.hasNext()) {
                modeMean = modeMean.add(modeIte.next());
            }
            r[5] = Math_BigDecimal.divideRoundIfNecessary(modeMean,
                    BigDecimal.valueOf(maxCountValues.size()), dp, rm);
        }
        r[6] = sortedData.get(0);
        r[7] = sortedData.get(sortedData.size() - 1);
        return r;
    }

    /**
     * Calculates and returns the sum of squared difference between the values
     * in map0 and map1
     *
     * @param m0
     * @param m1
     * @param map0Name Used for logging and can be null
     * @param map1Name Used for logging and can be null
     * @param keyName Used for logging and can be null
     * @return
     */
    public static Object[] getFirstOrderStatistics0(
            TreeMap<Integer, BigDecimal> m0, TreeMap<Integer, BigDecimal> m1,
            String map0Name, String map1Name, String keyName) {
        String m = "getFirstOrderStatistics0()";
        Object[] r = new Object[3];
        BigDecimal map0Value;
        BigDecimal map1Value;
        BigDecimal diff;
        BigDecimal diff2;
        BigDecimal sumDiff = BigDecimal.ZERO;
        BigDecimal sumDiff2 = BigDecimal.ZERO;
        HashSet<Integer> keys = Generic_Collections.getCompleteKeySet_HashSet(
                m0.keySet(), m1.keySet());
        r[0] = keys;
        Iterator<Integer> completeKeySetIterator = keys.iterator();
        while (completeKeySetIterator.hasNext()) {
            Integer k = completeKeySetIterator.next();
            Object v = m0.get(k);
            if (v == null) {
                map0Value = BigDecimal.ZERO;
            } else {
                map0Value = (BigDecimal) v;
            }
            v = m1.get(k);
            if (v == null) {
                map1Value = BigDecimal.ZERO;
            } else {
                map1Value = (BigDecimal) v;
            }
            diff = map1Value.subtract(map0Value);
            sumDiff = sumDiff.add(diff);
            diff2 = diff.multiply(diff);
            sumDiff2 = sumDiff2.add(diff2);
        }
        r[1] = sumDiff;
        r[2] = sumDiff2;
        return r;
    }

    /**
     * Calculates and returns the sum of squared difference between the values
     * in {@link m0} and {@link m1}.
     *
     * @param m0
     * @param m1
     * @param map0Name Used for logging and can be null
     * @param map1Name Used for logging and can be null
     * @param keyName Used for logging and can be null
     * @return {@code Object[]} r of length 3, where:
     * <ul>
     * <li>r[0] is a {@code HashSet<Integer>} - the union of the keys in {@link m0} and {@link m1}.</li>
     * <li></li>
     * <li></li>
     * </ul>
     */
    public static Object[] getFirstOrderStatistics1(
            TreeMap<Integer, BigDecimal> m0, TreeMap<Integer, BigDecimal> m1,
            String map0Name, String map1Name, String keyName) {
        Object[] r = new Object[3];
        BigDecimal sumDiff = BigDecimal.ZERO;
        BigDecimal sumDiff2 = BigDecimal.ZERO;
        HashSet<Integer> keys = Generic_Collections.getCompleteKeySet_HashSet(
                m0.keySet(), m1.keySet());
        r[0] = keys;
        Iterator<Integer> completeKeySetIterator = keys.iterator();
        while (completeKeySetIterator.hasNext()) {
            Integer k = completeKeySetIterator.next();
            Object v = m0.get(k);
            BigDecimal v0;
            if (v == null) {
                v0 = BigDecimal.ZERO;
            } else {
                //map0Value = new BigDecimal((BigInteger) value);
                v0 = (BigDecimal) v;
            }
            v = m1.get(k);
            BigDecimal v1;
            if (v == null) {
                v1 = BigDecimal.ZERO;
            } else {
                v1 = (BigDecimal) v;
            }
            BigDecimal diff = v1.subtract(v0);
            sumDiff = sumDiff.add(diff);
            BigDecimal diff2 = diff.multiply(diff);
            sumDiff2 = sumDiff2.add(diff2);
        }
        r[1] = sumDiff;
        r[2] = sumDiff2;
        return r;
    }

    /**
     * Calculates and returns summary statistics for {@link c}.
     *
     * @param c A collection of values that summary statistics are calculated
     * for.
     * @return {@code double[]} r where:
     * <ul>
     * <li>r[0] is the max of the values in {@code c}</li>
     * <li>r[1] is the min of the values in {@code c}</li>
     * <li>r[2] is the count of the values in {@code c}</li>
     * <li>r[3] is the sum of the values in {@code c}</li>
     * <li>r[4] is the average value in {@code c}</li>
     * <li>r[5] is the size of {@code c}</li>
     * <li>r[6] is the count of zero values in {@code c}</li>
     * <li>r[7] is the count of negative values in {@code c}</li>
     * </ul>
     */
    public static double[] getSummaryStatistics(Collection<Double> c) {
        DoubleSummaryStatistics stats = c.stream().collect(
                DoubleSummaryStatistics::new,
                DoubleSummaryStatistics::accept,
                DoubleSummaryStatistics::combine);
        double[] r = new double[8];
        r[0] = stats.getMax();
        r[1] = stats.getMin();
        r[2] = stats.getCount();
        r[3] = stats.getSum();
        r[4] = stats.getAverage();
        int countNegative = 0;
        int countZero = 0;
        Iterator<Double> ite = c.iterator();
        while (ite.hasNext()) {
            double v = ite.next();
            if (v == 0.0d) {
                countZero++;
            } else if (v < 0.0d) {
                countNegative++;
            }
        }
        r[5] = c.size();
        r[6] = countZero;
        r[7] = countNegative;
        return r;
    }
}
