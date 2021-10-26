package org.github.dumijdev.sgepfx;

import javafx.application.Application;
import javafx.application.HostServices;
import javafx.application.Platform;
import javafx.stage.Stage;
import org.github.dumijdev.sgepfx.pool.StageReadyEvent;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.context.ApplicationContextInitializer;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.support.GenericApplicationContext;

public class LoginApp extends Application {
    private static Stage localStage;
    private ConfigurableApplicationContext context;

    public static Stage getLocalStage() {
        return localStage;
    }

    @Override
    public void init() {
        ApplicationContextInitializer<GenericApplicationContext> initializer = ac -> {
            ac.registerBean(Application.class, () -> this);
            ac.registerBean(Application.Parameters.class, this::getParameters);
            ac.registerBean(HostServices.class, this::getHostServices);
        };

        this.context = new SpringApplicationBuilder().sources(SGESpringFX.class)
                .initializers(initializer).run(getParameters().getRaw().toArray(new String[0]));
    }

    @Override
    public void start(Stage stage) {
        localStage = stage;
        this.context.publishEvent(new StageReadyEvent(stage));
    }

    @Override
    public void stop() {
        this.context.close();
        Platform.exit();
    }
}
