package org.github.dumijdev.sgepfx.util.javafx;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class Render {
    private static Render r;

    private static Parent p;
    private static Scene s;
    private static Stage s1;

    private static void setP(Parent p) {
        Render.p = p;
        s = new Scene(p);
    }

    public static Render setP(String path) {
        try {
            setP((Parent) FXMLLoader.load(Render.class.getResource(path)));
        } catch (IOException e) {
            e.printStackTrace();
        }
        return r;
    }

    public static void setS1(Stage s1) {
        Render.s1 = s1;
        s1.setScene(s);
    }

    public static void setTitle(String t) {
        s1.setTitle(t);
    }

    public static void setResizable(boolean r) {
        s1.setResizable(r);
    }

    public static void renderFX() {
        if (null == s1) {
            s1 = new Stage();
            s1.setScene(s);
        }
        s1.show();
    }

    public static void close() {
        if (s1 != null)
            s1.close();
    }
}
