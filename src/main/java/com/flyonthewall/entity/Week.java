package com.flyonthewall.entity;

import java.util.ArrayList;
import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: obyte
 * Date: 01.06.13
 * Time: 0:19
 * To change this template use File | Settings | File Templates.
 */
public class Week {
    private List<Day> trainingDaysList;

    public Week() {
        trainingDaysList = new ArrayList<Day>();
    }

    public List<Day> getTrainingDaysList() {
        return trainingDaysList;
    }

    public void addDay(Day newDay){
        trainingDaysList.add(newDay);
    }

    public void soutWeek(){
        if (trainingDaysList.size() != 0){
            for (Day d: trainingDaysList){
                d.soutDay();
            }
        }
    }
}
