package com.pmm.triedenieodpadukosice;

import java.sql.Timestamp;

/**
 * Created by Client on 28.4.2016.
 */
public class Odvoz {
    private int typ;
    private Timestamp datum;

    public Odvoz(int typ, Timestamp datum) {
        this.typ = typ;
        this.datum = datum;
    }

    public int getTyp() {
        return typ;
    }

    public Timestamp getDatum() {
        return datum;
    }
}

