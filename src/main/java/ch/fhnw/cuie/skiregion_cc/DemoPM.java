package ch.fhnw.cuie.skiregion_cc;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class DemoPM {

    private StringProperty skiregion = new SimpleStringProperty("Tessin");

    public String getSkiregion() {
        return skiregion.get();
    }

    public StringProperty skiregionProperty() {
        return skiregion;
    }

    public void setSkiregion(String skiregion) {
        this.skiregion.set(skiregion);
    }
}
