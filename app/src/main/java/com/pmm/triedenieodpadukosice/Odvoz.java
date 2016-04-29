package com.pmm.triedenieodpadukosice;

import java.sql.Date;

/**
 * Created by Client on 28.4.2016.
 */
public class Odvoz {
    private int typ;
    private Date datum;

    public Odvoz(int typ, Date datum) {
        this.typ = typ;
        this.datum = datum;
    }

    public int getTyp() {
        return typ;
    }

    public Date getDatum() {
        return datum;
    }
}

