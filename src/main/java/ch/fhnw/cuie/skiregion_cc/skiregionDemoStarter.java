package ch.fhnw.cuie.skiregion_cc;

import javafx.application.Application;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class skiregionDemoStarter extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception {

        DemoPM pm = new DemoPM(); // Instanz des Presentationmodels

        Parent rootPanel = new DemoUI(pm);

        Scene scene = new Scene(rootPanel);
        primaryStage.setTitle("SkiregionControl Demo");
        primaryStage.setScene(scene);
        primaryStage.setWidth(400);
        primaryStage.setHeight(400);

        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
