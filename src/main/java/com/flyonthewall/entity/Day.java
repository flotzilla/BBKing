package com.flyonthewall.entity;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: obyte
 * Date: 31.05.13
 * Time: 23:51
 * To change this template use File | Settings | File Templates.
 */
public class Day {
    private List<Approach> approachList;
    private int approachsCount;
    private float minTonnage = 0;
    private float maxTonnage = 0;
    private int minLifts = 0;
    private int maxLifts = 0;

    private Date date;
    private SimpleDateFormat sdf;

    public Day(int approachsCount) {
        this.approachsCount = approachsCount;
        approachList = new ArrayList<Approach>(approachsCount);
        sdf = new SimpleDateFormat("dd.MM.yyyy");
    }

    public Day() {
        approachList = new ArrayList<Approach>();
        sdf = new SimpleDateFormat("dd.MM.yyyy");
    }

    public void calculate() {
        if (!validate()) {
            System.out.println("Something wrong");
            return;
        }
        for (Approach ap : approachList) {
            int maxRepetsCount = ap.getMaxRepetsCount();
            if (maxRepetsCount != 0){
                minLifts += ap.getMinRepetsCount();
                maxLifts += ap.getMaxRepetsCount();

                minTonnage += ap.getMinRepetsCount() * ap.getMass();
                maxTonnage += ap.getMaxRepetsCount() * ap.getMass();
            } else {
                minLifts += ap.getMinRepetsCount();
                maxLifts += ap.getMinRepetsCount();

                minTonnage += ap.getMinRepetsCount() * ap.getMass();
                maxTonnage += ap.getMinRepetsCount() * ap.getMass();
            }

        }
    }

    public boolean validate() {
        if (approachList == null) return false;
        if (approachList.size() == 0) return false;

        return true;
    }

    public void cleanCounts() {
        minTonnage = maxTonnage = minLifts = maxLifts = 0;
    }

    public void setDate(String date) {
        try {
            this.date = sdf.parse(date);
        } catch (ParseException e) {
            System.out.println("Cannot parse date");
        }
    }


    public void addApproach(float mass, int minRepeats, int maxRepeats) {
        approachList.add(new Approach(mass, minRepeats, maxRepeats));
    }

    public void soutDay() {
        System.out.println("Day");
        for (int i = 0; i < approachList.size(); i++) {
            System.out.println((i + 1) + ") " + approachList.get(i).toString());
        }
        calculate();
        System.out.println("Tonnage: " + minTonnage + " - " + maxTonnage);
        System.out.println("Lifts: " + minLifts + " - " + maxLifts);
        System.out.println("");
    }

    public List<Approach> getApproachList() {
        return approachList;
    }

    public float getMinTonnage() {
        return minTonnage;
    }

    public float getMaxTonnage() {
        return maxTonnage;
    }

    public int getMinLifts() {
        return minLifts;
    }

    public int getMaxLifts() {
        return maxLifts;
    }
}
