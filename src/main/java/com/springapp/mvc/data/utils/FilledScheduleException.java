package com.springapp.mvc.data.utils;

import java.io.Serializable;

/**
 * Created by Nathan on 20/03/2015.
 */
public class FilledScheduleException extends RuntimeException  implements Serializable {

    private final Schedule what;
    private final Schedule overs;

    public FilledScheduleException(Schedule what, Schedule overs) {
        this.what = what;
        this.overs = overs;
    }

    public Schedule getWhat() {
        return what;
    }

    public Schedule getOvers() {
        return overs;
    }

    public String toString()
    {
        return "what : " + this.what + " ; overs : " + this.overs;
    }
}
