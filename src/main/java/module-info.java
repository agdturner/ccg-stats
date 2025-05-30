/*
 * Copyright 2021 Andy Turner, University of Leeds.
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

/**
 * Provides some summary statistics functionality.
 */
module uk.ac.leeds.ccg.stats {
    requires transitive java.logging;
    requires java.desktop;
    requires uk.ac.leeds.ccg.generic;
    requires transitive uk.ac.leeds.ccg.math;
    exports uk.ac.leeds.ccg.stats.summary;
    exports uk.ac.leeds.ccg.stats.range;
}