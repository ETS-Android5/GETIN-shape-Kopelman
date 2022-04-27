package com.example.yalla;

import android.app.Application;

import java.util.ArrayList;
import java.util.List;

public class EmailList extends Application {
    private static List<String> emailList = new ArrayList<>();

    public EmailList() {
    }

    public static List<String> getEmailList() {
        return emailList;
    }
}
