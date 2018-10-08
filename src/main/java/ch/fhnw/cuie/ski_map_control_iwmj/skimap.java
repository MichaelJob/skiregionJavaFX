package ch.fhnw.cuie.ski_map_control_iwmj;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.geometry.Insets;
import javafx.scene.control.Label;
import javafx.scene.layout.Pane;
import javafx.scene.layout.Region;
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
    private Label regionName;

    //all properties
    private StringProperty skiregion = new SimpleStringProperty();

    private Pane drawingPane;


    private static double sizeFactor() {
        return PREFERRED_SIZE / ARTBOARD_SIZE;
    }

    public skimap() {
        initializeSizes();
        initializeSelf();
        initializeParts();
        initializeDrawingPane();
        layoutParts();
        setupBindings();
        setupEventHandler();
        setupValueChangeListener();
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

        CH = new SVGPath();
        CH.setContent(getSVGStrings("CHsvg"));
        CH.getStyleClass().addAll("CH");

        INNER = new SVGPath();
        INNER.setContent(getSVGStrings("INNERsvg"));
        INNER.getStyleClass().addAll("INNER");

        BE = new SVGPath();
        BE.setContent(getSVGStrings("BEsvg"));
        BE.getStyleClass().addAll("BE");

        GR = new Polygon();
        GR.getPoints().addAll(getPolygonData("GRpolygon"));
        GR.getStyleClass().addAll("GR");

        TI = new Polygon();
        TI.getPoints().addAll(getPolygonData("TIpolygon"));
        TI.getStyleClass().addAll("TI");

        VDVS = new Polygon();
        VDVS.getPoints().addAll(getPolygonData("VDVSpolygon"));
        VDVS.getStyleClass().addAll("VDVS");

        regionName = new Label();
        regionName.setPrefSize(PREFERRED_SIZE * 0.5, PREFERRED_SIZE * 0.075);
        regionName.getStyleClass().addAll("label");
        regionName.setMouseTransparent(true);

    }

    private void initializeDrawingPane() {
        drawingPane = new Pane();
        drawingPane.setMaxSize(PREFERRED_SIZE, PREFERRED_SIZE);
        drawingPane.setMinSize(PREFERRED_SIZE, PREFERRED_SIZE);
        drawingPane.setPrefSize(PREFERRED_SIZE, PREFERRED_SIZE);
        drawingPane.getStyleClass().add("skimap");
    }

    private void layoutParts() {
        drawingPane.getChildren().addAll(CH, GR, TI, INNER, BE, VDVS, regionName);
        getChildren().add(drawingPane);
    }

    private void setupBindings(){
        regionName.textProperty().bind(skiregionProperty());
    }

    private void setupEventHandler() {

        CH.setOnMouseClicked(event -> {
            setSkiregion("Nordschweiz");
            removeSelected();
            CH.getStyleClass().addAll("selected");
        });

        GR.setOnMouseClicked(event -> {
            setSkiregion("Graubünden");
            removeSelected();
            GR.getStyleClass().addAll("selected");
        });

        INNER.setOnMouseClicked(event -> {
            setSkiregion("Innerschweiz");
            removeSelected();
            INNER.getStyleClass().addAll("selected");
        });

        TI.setOnMouseClicked(event -> {
            setSkiregion("Tessin");
            removeSelected();
            TI.getStyleClass().addAll("selected");
        });

        VDVS.setOnMouseClicked(event -> {
            setSkiregion("Waadt & Wallis");
            removeSelected();
            VDVS.getStyleClass().addAll("selected");
        });

        BE.setOnMouseClicked(event -> {
            setSkiregion("Bern");
            removeSelected();
            BE.getStyleClass().addAll("selected");
        });

    }

    private void removeSelected(){
        INNER.getStyleClass().remove("selected");
        TI.getStyleClass().remove("selected");
        CH.getStyleClass().remove("selected");
        GR.getStyleClass().remove("selected");
        BE.getStyleClass().remove("selected");
        VDVS.getStyleClass().remove("selected");
    }


    private void setupValueChangeListener() {


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