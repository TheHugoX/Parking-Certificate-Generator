import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;
import ui.MainPanel;

public class Main extends Application {
    public static void main(String[] args) {
        Application.launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        Scene scene = new Scene(new MainPanel().getParent());
        primaryStage.setTitle("Parking Certificate Generator");
        primaryStage.setScene(scene);
        primaryStage.show();
    }
}