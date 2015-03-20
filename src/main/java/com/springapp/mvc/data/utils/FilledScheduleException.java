package com.springapp.mvc.data.utils;

/**
 * Created by Nathan on 20/03/2015.
 */
public class FilledScheduleException extends RuntimeException {

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
}
