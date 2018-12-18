package ch.fhnw.cuie.ski_map_control_iwmj;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.event.Event;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.ListCell;
import javafx.scene.control.TextField;
import javafx.scene.layout.*;
import javafx.scene.shape.Polygon;
import javafx.scene.shape.SVGPath;

import java.io.File;
import java.io.FileNotFoundException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

public class skimap extends Region {
    //artboard in sketch is 500*500
    private static final double ARTBOARD_SIZE = 500;

    private static final double PREFERRED_SIZE = ARTBOARD_SIZE;
    private static final double MINIMUM_SIZE = ARTBOARD_SIZE * 0.25;   // 1/4 of preferred
    private static final double MAXIMUM_SIZE = ARTBOARD_SIZE * 2;      // twice the preferred

    private SVGPath CH;
    private Polygon GR;
    private Polygon TI;
    private Polygon VDVS;
    private SVGPath INNER;
    private SVGPath BE;
    private SVGPath OST;
    private ComboBox<String> skiregionDropdown;

    //all properties
    private StringProperty skiregion = new SimpleStringProperty();

    private BorderPane drawingPane;
    private HBox topHBox;
    private Pane map;

    private static double sizeFactor() {
        return PREFERRED_SIZE / ARTBOARD_SIZE;
    }

    public skimap() {
        initializeSizes();
        initializeSelf();
        initializeParts();
        initializeDrawingPane();
        layoutParts();
        setupEventHandler();
        setupValueChangeListener();
        setupBindings();
    }

    private void initializeSizes() {
        Insets padding = getPadding();
        double verticalPadding = padding.getTop() + padding.getBottom();
        double horizontalPadding = padding.getLeft() + padding.getRight();

        setMinSize(MINIMUM_SIZE + horizontalPadding, MINIMUM_SIZE + verticalPadding);
        setPrefSize(PREFERRED_SIZE + horizontalPadding, PREFERRED_SIZE + verticalPadding);
        setMaxSize(MAXIMUM_SIZE + horizontalPadding, MAXIMUM_SIZE + verticalPadding);
    }

    private void initializeSelf() {
        String fonts = getClass().getResource("/fonts/fonts.css").toExternalForm();
        getStylesheets().add(fonts);

        String stylesheet = getClass().getResource("style.css").toExternalForm();
        getStylesheets().add(stylesheet);
    }

    private String getSVGStrings(String filename) {
        URL url = getClass().getResource("./chdata/"+filename + ".txt");
        File file = new File(url.getPath());
        Scanner sc = null;
        try {
            sc = new Scanner(file);
            while (sc.hasNextLine()) {
                return sc.nextLine();
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        return "";
    }

    private Double[] getPolygonData(String filename) {
        Double[] result;
        URL url = getClass().getResource("./chdata/"+filename + ".txt");
        File file = new File(url.getPath());
        Scanner sc = null;
        try {
            sc = new Scanner(file);
            while (sc.hasNextLine()) {
                String data = sc.nextLine();
                String[] dataarray = data.split(" ");
                result = new Double[dataarray.length];
                for (var i = 0; i < dataarray.length; i++) {
                    result[i] = Double.parseDouble(dataarray[i]);
                }
                return result;
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        return null;
    }


    private void initializeParts() {
        double center = getPrefWidth() * 0.5;

        topHBox = new HBox();

        CH = new SVGPath();
        CH.setContent(getSVGStrings("CHsvg"));
        CH.getStyleClass().addAll("CH");

        INNER = new SVGPath();
        INNER.setContent(getSVGStrings("INNERsvg"));

        BE = new SVGPath();
        BE.setContent(getSVGStrings("BEsvg"));

        OST = new SVGPath();
        OST.setContent(getSVGStrings("OSTsvg"));

        GR = new Polygon();
        GR.getPoints().addAll(getPolygonData("GRpolygon"));

        TI = new Polygon();
        TI.getPoints().addAll(getPolygonData("TIpolygon"));

        VDVS = new Polygon();
        VDVS.getPoints().addAll(getPolygonData("VDVSpolygon"));

        skiregionDropdown = new ComboBox<String>();
        skiregionDropdown.setPrefSize(PREFERRED_SIZE * 1.0, PREFERRED_SIZE * 0.075);
        skiregionDropdown.getStyleClass().addAll("skiregionDropdown");
        skiregionDropdown.getItems().addAll(
                "Bern",
                "Tessin",
                "Graubünden",
                "Waadt & Wallis",
                "Ostschweiz",
                "Innerschweiz",
                "Nordschweiz/FR/GE"
        );
        skiregionDropdown.setPromptText("Skiregion");
        skiregionDropdown.setEditable(true);
        skiregionDropdown.setValue(getSkiregion());

        map = new Pane();
        map.getChildren().addAll(CH, GR, TI, INNER, BE, OST, VDVS);
    }

    private void initializeDrawingPane() {
        drawingPane = new BorderPane();
        drawingPane.setMaxSize(PREFERRED_SIZE, PREFERRED_SIZE);
        drawingPane.setMinSize(PREFERRED_SIZE, PREFERRED_SIZE);
        drawingPane.setPrefSize(PREFERRED_SIZE, PREFERRED_SIZE);
        drawingPane.getStyleClass().add("skimap");
    }

    private void layoutParts() {

        topHBox.getChildren().addAll(skiregionDropdown);
        topHBox.setHgrow(skiregionDropdown, Priority.ALWAYS);
        drawingPane.setTop(topHBox);
        drawingPane.setCenter(map);

        getChildren().add(drawingPane);
    }

    private void setupEventHandler() {

        skiregionDropdown.setOnAction((event) -> {
            setSkiregion(skiregionDropdown.getSelectionModel().getSelectedItem());
        });

        CH.setOnMouseClicked(event -> {
            setSkiregion("Nordschweiz/FR/GE");
            setSelectedRegion("Nordschweiz/FR/GE");
        });

        GR.setOnMouseClicked(event -> {
            setSkiregion("Graubünden");
            setSelectedRegion("Graubünden");
        });

        INNER.setOnMouseClicked(event -> {
            setSkiregion("Innerschweiz");
            setSelectedRegion("Innerschweiz");
        });

        TI.setOnMouseClicked(event -> {
            setSkiregion("Tessin");
            setSelectedRegion("Tessin");
        });

        VDVS.setOnMouseClicked(event -> {
            setSkiregion("Waadt & Wallis");
            setSelectedRegion("Waadt & Wallis");
        });

        BE.setOnMouseClicked(event -> {
            setSkiregion("Bern");
            setSelectedRegion("Bern");
        });

        OST.setOnMouseClicked(event -> {
            setSkiregion("Ostschweiz");
            setSelectedRegion("Ostschweiz");
        });

    }

    private void setSelectedRegion(String region) {
        removeSelected();
        switch (region) {
            case "Ostschweiz":
                OST.getStyleClass().addAll("selected");
                break;
            case "Bern":
                BE.getStyleClass().addAll("selected");
                break;
            case "Waadt & Wallis":
                VDVS.getStyleClass().addAll("selected");
                break;
            case  "Tessin":
                TI.getStyleClass().addAll("selected");
                break;
            case "Innerschweiz":
                INNER.getStyleClass().addAll("selected");
                break;
            case "Graubünden":
                GR.getStyleClass().addAll("selected");
                break;
            case "Nordschweiz/FR/GE":
                CH.getStyleClass().addAll("selected");
                break;
        }
    }

    private void removeSelected(){
        INNER.getStyleClass().remove("selected");
        TI.getStyleClass().remove("selected");
        CH.getStyleClass().remove("selected");
        GR.getStyleClass().remove("selected");
        BE.getStyleClass().remove("selected");
        OST.getStyleClass().remove("selected");
        VDVS.getStyleClass().remove("selected");
    }


    private void setupValueChangeListener() {
        skiregionProperty().addListener((observable, oldValue, newValue) -> {
            skiregionDropdown.setValue(newValue);
            setSelectedRegion(newValue);
        });
    }

    private void setupBindings() {

    }

    @Override
    protected void layoutChildren() {
        super.layoutChildren();
        resize();
    }

    private void resize() {
        Insets padding = getPadding();
        double availableWidth = getWidth() - padding.getLeft() - padding.getRight();
        double availableHeight = getHeight() - padding.getTop() - padding.getBottom();
        double size = Math.max(Math.min(Math.min(availableWidth, availableHeight), MAXIMUM_SIZE), MINIMUM_SIZE);

        double scalingFactor = size / PREFERRED_SIZE;

        if (availableWidth > 0 && availableHeight > 0) {
            drawingPane.relocate((getWidth() - PREFERRED_SIZE) * 0.5, (getHeight() - PREFERRED_SIZE) * 0.5);
            drawingPane.setScaleX(scalingFactor);
            drawingPane.setScaleY(scalingFactor);
        }
    }

    // all Getter and Setter


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
