package com.springapp.mvc.data.activity;

import com.springapp.mvc.data.utils.Schedule;
import com.springapp.mvc.data.utils.ScheduleMap;

import java.io.Serializable;
import java.sql.Timestamp;
import java.util.Iterator;
import java.util.Map;

/**
 * Created by Nathan on 20/03/2015.
 */
public class Stay implements Iterable<Map.Entry<Schedule, Activity>>, Serializable {

    private final Long id;
    private ScheduleMap<Activity> activities;
    private Schedule stayDuration;

    public Stay (Schedule stayDuration)
    {
        this(null, stayDuration);
    }

    public Stay (Long id, Schedule stayDuration)
    {
        this.id = id;
        this.activities = new ScheduleMap<Activity>();
        this.stayDuration = stayDuration;
    }

    public Stay(Long id, Timestamp start, Timestamp end)
    {
        this (id, new Schedule(start, end));
    }

    public Stay(Timestamp start, Timestamp end)
    {
        this (new Schedule(start, end));
    }

    public void addActivity(Timestamp whenStart, Timestamp whenEnd, Activity activity)
    {
        this.activities.put(new Schedule(whenStart, whenEnd), activity);
    }

    public void addActivity(Schedule when, Activity activity)
    {
        this.activities.put(when, activity);
    }

    public void removeActivity (Schedule when)
    {
        this.activities.remove(when);
    }

    public void removeActtivity(Timestamp when)
    {
        this.activities.remove(when);
    }

    @Override
    public Iterator<Map.Entry<Schedule, Activity>> iterator() {
        return this.activities.entryList().iterator();
    }

    public String toString()
    {
        return "Stay " + this.stayDuration + " : " + this.activities;

    }

    public Long getId() {
        return id;
    }
}
