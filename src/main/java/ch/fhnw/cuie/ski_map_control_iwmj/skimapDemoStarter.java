package ch.fhnw.cuie.ski_map_control_iwmj;

import javafx.application.Application;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class skimapDemoStarter extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception {

        DemoPM pm = new DemoPM(); // Instanz des Presentationmodels

        Parent rootPanel = new DemoUI(pm);

        Scene scene = new Scene(rootPanel);
        primaryStage.setTitle("skimap custom control - homework iw, mj");
        primaryStage.setScene(scene);
        primaryStage.setWidth(400);
        primaryStage.setHeight(400);

        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
