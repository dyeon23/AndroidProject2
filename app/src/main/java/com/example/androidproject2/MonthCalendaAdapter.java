package com.example.androidproject2;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.viewpager2.adapter.FragmentStateAdapter;

import java.util.Calendar;

import static java.lang.Short.MAX_VALUE;

public class MonthCalendaAdapter extends FragmentStateAdapter {
    private static int NUM_ITEMS=100;
    private  static int START_POSITON = MAX_VALUE / 2;
    Calendar today;

    public MonthCalendaAdapter(@NonNull Fragment fragment) {

        super(fragment);
    }

    @NonNull
    @Override
    public Fragment createFragment(int position) {

        initCalendar();
        int year = getyear();
        int month = getmonth()+position%12;

        if(month == 12)
        {

            month= month-12;
            year= year+1;
        }
        return MonthCalendarFragment.newInstance(year,month);
    }

    public void initCalendar(){

        today=Calendar.getInstance();
    }

    @Override
    public int getItemCount() {

        return NUM_ITEMS;
    }

    public int getmonth(){
        int a = today.get(Calendar.MONTH);
        return a;
    }

    public int getyear(){
        int b = today.get(Calendar.YEAR);
        return b;
    }


}
