package ch.fhnw.cuie.skiregion_cc;

import javafx.scene.layout.BorderPane;

/**
 * @author Dieter Holz
 */
public class DemoUI extends BorderPane {
    private SkiregionControl skiregion;


    private final DemoPM pm;

    public DemoUI(DemoPM pm) {
        this.pm = pm;
        initializeParts();
        layoutParts();

        setupBinding();
        setupEventHandler();
    }


    private void initializeParts() {
        skiregion = new SkiregionControl();
    }

    private void layoutParts() {
        setCenter(skiregion);
    }

    private void setupBinding() {
        skiregion.skiregionProperty().bindBidirectional(pm.skiregionProperty());
    }


    private void setupEventHandler() {
    }


}
