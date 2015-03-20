package com.springapp.mvc.data.activity;

/**
 * Created by Nathan on 20/03/2015.
 */
public class CategoryName {

    private final String id;
    private final Long foreignKey;

    public CategoryName(String id, Long foreignKey)
    {
        this.id = id;
        this.foreignKey = foreignKey;
    }


    public String getId() {
        return id;
    }

    public Long getForeignKey() {
        return foreignKey;
    }
}
