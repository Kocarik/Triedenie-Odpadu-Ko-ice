package com.pmm.triedenieodpadukosice;

import java.sql.Date;

/**
 * Created by Client on 28.4.2016.
 */
public class Odvoz {
    private int typ;
    //private Date datum;
    private String datum;

    public Odvoz(int typ, String datum) {
        this.typ = typ;
        this.datum = datum;
    }

    public int getTyp() {
        return typ;
    }

    public String getDatum() {
        return datum;
    }
}

