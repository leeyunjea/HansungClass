package org.androidtown.hansungclass.Class;

import java.util.ArrayList;

/**
 * Created by hscom006 on 2017-11-30.
 */

public class ExchangeUniversityContact {

    private String take;
    private String take_location;

    public ExchangeUniversityContact(String take, String take_location) {
        this.take = take;
        this.take_location = take_location;
    }

    public String getTake() {
        return take;
    }

    public String getTake_location() {
        return take_location;
    }

    private static int LastContactId = 0;

    public static ArrayList<ExchangeUniversityContact> createContactsList(int numContacts) {
        ArrayList<ExchangeUniversityContact> contacts = new ArrayList<ExchangeUniversityContact>();

        // for(int i=1; i<=numContacts; i++) {
        //     contacts.add(new Contact("Person " + ++LastContactId, "aaa"));
        //}

        return contacts;
    }
}
