module uk.ac.leeds.ccg.stats {
    requires transitive java.logging;
    requires java.desktop;
    requires uk.ac.leeds.ccg.generic;
    requires uk.ac.leeds.ccg.math;
    requires transitive ch.obermuhlner.math.big;
    exports uk.ac.leeds.ccg.stats.summary;
}