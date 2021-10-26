package org.github.dumijdev.sgepfx;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

/**
 * @author Dumilde Paulo
 */
public class SGEPFXAPP extends Application {

    private static Stage localStage;

    public static Stage getLocalStage() {
        return localStage;
    }

    public static void main(String[] args) {
        launch(args);
    }

    /**
     * The main entry point for all JavaFX applications.
     * The start method is called after the init method has returned,
     * and after the system is ready for the application to begin running.
     *
     * <p>
     * NOTE: This method is called on the JavaFX Application Thread.
     * </p>
     *
     * @param stage the primary stage for this application, onto which
     *              the application scene can be set.
     *              Applications may create other stages, if needed, but they will not be
     *              primary stages.
     */
    @Override
    public void start(Stage stage) {
        SGEPFXAPP.localStage = stage;
        renderFX();
    }

    private void renderFX() {
        try {
            var p = (Parent) FXMLLoader.load(getClass().getResource("/fxml/main2.fxml"));
            Scene s = new Scene(p);
            localStage.setScene(s);
            localStage.setTitle("SgeApp");
            localStage.setResizable(false);
            localStage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
