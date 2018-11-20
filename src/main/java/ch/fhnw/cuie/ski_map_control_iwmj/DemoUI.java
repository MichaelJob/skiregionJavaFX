package ch.fhnw.cuie.ski_map_control_iwmj;

import javafx.scene.layout.BorderPane;

/**
 * @author Dieter Holz
 */
public class DemoUI extends BorderPane {
    private skimap skimap;


    private final DemoPM pm;

    public DemoUI(DemoPM pm) {
        this.pm = pm;
        initializeParts();
        layoutParts();

        setupBinding();
        setupEventHandler();
    }


    private void initializeParts() {
        skimap = new skimap();
    }

    private void layoutParts() {
        setCenter(skimap);
    }

    private void setupBinding() {
        skimap.skiregionProperty().bindBidirectional(pm.skiregionProperty());
    }


    private void setupEventHandler() {
    }


}
