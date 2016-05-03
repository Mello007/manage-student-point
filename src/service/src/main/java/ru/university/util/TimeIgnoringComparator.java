package ru.university.util;

import java.util.Comparator;
import java.util.Date;

public class TimeIgnoringComparator {
    public boolean compare(Date d1, Date d2) {
        return d1.getYear() == d2.getYear() && d1.getMonth() == d2.getMonth() && d1.getDay() == d2.getDay();
    }
}
