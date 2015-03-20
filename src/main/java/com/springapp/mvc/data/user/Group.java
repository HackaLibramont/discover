package com.springapp.mvc.data.user;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * Created by Nathan on 20/03/2015.
 */
public class Group implements Iterable<VirtualUser>{

    private final List<VirtualUser> users;

    public Group()
    {
        this.users = new ArrayList<VirtualUser>();
    }

    @Override
    public Iterator<VirtualUser> iterator() {
        return this.users.iterator();
    }
}
