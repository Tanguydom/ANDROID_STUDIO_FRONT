package com.example.projet_gerante_domergue.utils;

import android.content.Context;
import android.content.res.Configuration;

import java.util.Locale;

public class ChangeLanguage {
    private static Context context;
    private static String language;
    public static void setLocale(Context context, String language) {
        ChangeLanguage.context = context;
        ChangeLanguage.language = language;
        Locale locale = new Locale(language);
        Locale.setDefault(locale);
        Configuration config = new Configuration();
        config.locale = locale;
        context.getResources().updateConfiguration(config, context.getResources().getDisplayMetrics());
    }
}