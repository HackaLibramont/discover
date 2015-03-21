package com.springapp.mvc.data.utils;

import java.io.Serializable;
import java.sql.Timestamp;

/**
 * Created by Nathan on 20/03/2015.
 */
public class Schedule implements Comparable<Object>, Serializable {

    private final Long id;
    private final Timestamp start;
    private final Timestamp end;

    public final static  int BEFORE = -5;
    public final static int JUST_BEFORE = -4;
    public final static int OVER_BEFORE = -3;
    public final static int INCLUDES_BEFORE = -2;
    public final static int INCLUDES = -1;
    public final static int EQUALS = 0;
    public final static int IN = 1;
    public final static int INCLUDES_AFTER = 2;
    public final static int OVER_AFTER = 3;
    public final static int JUST_AFTER = 4;
    public final static int AFTER = 5;


    public Schedule(Long id, Timestamp start, Timestamp end) {
        this.id = id;
        this.start = start;
        this.end = end;
    }

    public Schedule(Timestamp start, Timestamp end) {
        this(null, start, end);
    }

    public Timestamp start() {
        return this.start;
    }

    public Timestamp end() {
        return this.end;
    }

    /**
     *
     */
    public int compareTo(Object o) {
        if (Schedule.class.isAssignableFrom(o.getClass())) {
            Schedule other = (Schedule) o;
            if (this.start().before(other.start())) {
                if (this.end().before(other.start()))
                    return Schedule.BEFORE;
                else if (this.end().equals(other.start()))
                    return Schedule.JUST_BEFORE;
                else if (this.end().before(other.end()))
                    return Schedule.OVER_BEFORE;
                else if (this.end().equals(other.end()))
                    return Schedule.INCLUDES_BEFORE;
                else
                    return Schedule.INCLUDES;
            } else if (this.start().equals(other.start()) || this.start().equals(other.end())) {
                if (this.end().before(other.end()))
                    return Schedule.IN;
                else if (this.end().equals(other.end()))
                    return Schedule.EQUALS;
                else
                    return Schedule.OVER_AFTER;
            } else if (this.start().before(other.end())) {
                if (this.end().before(other.end()))
                    return Schedule.IN;
                else if (this.end().equals(other.end()))
                    return Schedule.IN;
                else
                    return Schedule.OVER_AFTER;
            } else if (this.start().equals(other.end())) {
                return Schedule.JUST_AFTER;
            } else
                return Schedule.AFTER;
        } else if (Timestamp.class.isAssignableFrom(o.getClass())) {
            Timestamp other = (Timestamp) o;
            if (this.start().before(other)) {
                if (this.end().before(other))
                    return Schedule.BEFORE;
                else if (this.end().equals(other))
                    return INCLUDES_AFTER;
                else
                    return INCLUDES;
            } else if (this.start().equals(other)) {
                return INCLUDES_BEFORE;
            } else
                return AFTER;
        } else return -99;
    }

    public String toString()
    {
        return this.start + " - " + this.end;
    }


    public Long getId() {
        return id;
    }
}
