package com.springapp.mvc.data.utils;

import java.util.*;

/**
 * Created by Nathan on 20/03/2015.
 */
public class ScheduleMap<T> implements Map<Schedule, T> {

    private final List<Schedule> schedules;
    private final List<T> ts;

    public ScheduleMap() {
        this.schedules = new ArrayList<Schedule>();
        this.ts = new ArrayList<T>();
    }

    @Override
    public int size() {
        return this.schedules.size();
    }

    @Override
    public boolean isEmpty() {
        return this.size() == 0;
    }

    private static boolean in(int nb, int... props) {
        for (int prop : props)
            if (nb == prop)
                return true;
        return false;
    }

    public Schedule getKey(Object key) {
        for (Schedule elem : this.schedules)
            if (in(elem.compareTo(key), Schedule.INCLUDES, Schedule.INCLUDES_BEFORE, Schedule.INCLUDES_AFTER, Schedule.EQUALS))
                return elem;
        return null;
    }

    @Override
    public boolean containsKey(Object key) {
        return this.getKey(key) != null;
    }

    @Override
    public boolean containsValue(Object value) {
        return this.ts.contains(value);
    }

    @Override
    public T get(Object key) {
        return this.ts.get(this.schedules.indexOf(key));
    }

    @Override
    public T put(Schedule key, T value) throws FilledScheduleException {
        int pos = 0;
        boolean found = false;
        for (pos = 0; !found && pos < this.size(); ++pos) {
            if (in(this.schedules.get(pos).compareTo(key), Schedule.INCLUDES, Schedule.IN, Schedule.EQUALS, Schedule.INCLUDES_AFTER, Schedule.INCLUDES_BEFORE, Schedule.OVER_AFTER, Schedule.OVER_BEFORE))
                throw new FilledScheduleException(key, this.schedules.get(pos));
            else if (in(this.schedules.get(pos).compareTo(key), Schedule.JUST_AFTER, Schedule.AFTER))
                found = true;
        }
        this.schedules.add(pos, key);
        this.ts.add(pos, value);
        return value;
    }

    @Override
    public T remove(Object key) {
        Schedule found = this.getKey(key);
        if (found != null) {
            T t = this.ts.remove(this.schedules.indexOf(found));
            this.schedules.remove(found);
            return t;
        }
        return null;
    }

    @Override
    public void putAll(Map<? extends Schedule, ? extends T> m) {
        for (Schedule s : m.keySet())
            this.put(s, m.get(s));
    }

    @Override
    public void clear() {
        this.schedules.clear();
        this.ts.clear();
    }

    @Override
    public Set<Schedule> keySet() {
        return new HashSet<Schedule>(this.schedules);
    }

    @Override
    public Collection<T> values() {
        return new ArrayList<T>(this.ts);
    }

    @Override
    public Set<Entry<Schedule, T>> entrySet() {
        return new HashSet<Entry<Schedule, T>>(this.entryList());
    }

    public List<Entry<Schedule, T>> entryList() {
        List<Entry<Schedule, T>> list = new ArrayList<Entry<Schedule, T>>();
        for (int pos = 0; pos < this.size(); ++pos)
            list.add(new AbstractMap.SimpleEntry<Schedule, T>(this.schedules.get(pos), this.ts.get(pos)));
        return list;
    }

    public String toString()
    {
        StringBuilder str = new StringBuilder("{\n");
        for (Schedule elem : this.keySet())
        {
            str.append("   " + elem + " : " + this.get(elem) + "\n");
        }
        str.append("}");
        return str.toString();
    }
}
