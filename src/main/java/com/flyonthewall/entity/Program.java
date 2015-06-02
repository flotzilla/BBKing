package com.flyonthewall.entity;

import java.util.ArrayList;
import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: obyte
 * Date: 01.06.13
 * Time: 0:55
 * To change this template use File | Settings | File Templates.
 */
public class Program {
    private List<Week> trainingWeeksList;
    private int weeksCount;

    public Program(int weeksCount) {
        this.weeksCount = weeksCount;
        trainingWeeksList = new ArrayList<Week>(weeksCount);
    }

    public Program() {
        trainingWeeksList = new ArrayList<Week>();
    }

    public void addWeek(Week newWeek){
        trainingWeeksList.add(newWeek);
    }

    public void soutProgram(){
        for (Week w: trainingWeeksList){
            w.soutWeek();
        }
    }
}
