package com.obsbs.utils.distance;

import java.util.List;

public class CADummy {
    private double DistanceFromHome;
    private double DistanceTrain;
    private double DistanceCompany;
    private int ResonCode;
    private List<Day> WeekDaysForApplacation;

    public int getResonCode() {
        return ResonCode;
    }

    public void setResonCode(int resonCode) {
        ResonCode = resonCode;
    }

    public boolean isAcepted() {
        return Acepted;
    }

    public void setAcepted(boolean acepted) {
        Acepted = acepted;
    }

    private boolean Acepted = false;

    public double getDistanceFromHome() {
        return DistanceFromHome;
    }

    public void setDistanceFromHome(double distanceFromHome) {
        DistanceFromHome = distanceFromHome;
    }

    public double getDistanceTrain() {
        return DistanceTrain;
    }

    public void setDistanceTrain(double distanceTrain) {
        DistanceTrain = distanceTrain;
    }

    public double getDistanceCompany() {
        return DistanceCompany;
    }

    public void setDistanceCompany(double distanceCompany) {
        DistanceCompany = distanceCompany;
    }

    public CADummy(Double DistanceFromHome, Double DistanceTrain, Double DistanceCompany) {
        this.DistanceFromHome = DistanceFromHome;
        this.DistanceCompany = DistanceCompany;
        this.DistanceTrain = DistanceTrain;
    }

    @Override
    public String toString() {
        return "CADummy{" +
                "DistanceFromHome=" + DistanceFromHome +
                ", DistanceTrain=" + DistanceTrain +
                ", DistanceCompany=" + DistanceCompany +
                ", ResonCode=" + ResonCode +
                ", Acepted=" + Acepted +
                '}';
    }

    public List<Day> getDays() {
        return WeekDaysForApplacation;
    }
}
