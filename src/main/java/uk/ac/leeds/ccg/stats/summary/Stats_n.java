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
 * A POJO for storing summary statistics.
 *
 * @author Andy Turner
 * @version 1.0
 */
public abstract class Stats_n {

    /**
     * For storing the number of values.
     */
    public int n;
    
    public String toString1() {
        return "n=" + n;
    }
}
