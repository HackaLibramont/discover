package com.springapp.mvc.data.user;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * Created by Nathan on 20/03/2015.
 */
public class Group implements Iterable<VirtualUser>
{

    private final List<VirtualUser> users;

    public Group()
    {
        this.users = new ArrayList<VirtualUser>();
    }

    @Override
    public Iterator<VirtualUser> iterator()
    {
        return this.users.iterator();
    }

    public boolean add(VirtualUser user)
    {
        return users.add(user);
    }

    public void add(int index, VirtualUser user)
    {
        users.add(index, user);
    }

    public boolean contains(VirtualUser user)
    {
        return users.contains(user);
    }

    public VirtualUser get(int index)
    {
        return users.get(index);
    }

    public int indexOf(VirtualUser user)
    {
        return users.indexOf(user);
    }

    public boolean isEmpty()
    {
        return users.isEmpty();
    }

    public boolean remove(VirtualUser user)
    {
        return users.remove(user);
    }

    public VirtualUser remove(int index)
    {
        return users.remove(index);
    }

    public VirtualUser set(int index, VirtualUser user)
    {
        return users.set(index, user);
    }

    public int size()
    {
        return users.size();
    }
}
