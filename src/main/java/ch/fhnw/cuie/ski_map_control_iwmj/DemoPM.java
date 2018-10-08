package ch.fhnw.cuie.ski_map_control_iwmj;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class DemoPM {

    private StringProperty skiregion = new SimpleStringProperty("Skiregion");

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
