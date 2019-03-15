package classifer;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import org.mindrot.jbcrypt.BCrypt;

public class Main extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception{
        Parent root = FXMLLoader.load(getClass().getResource("/logginApp/login.fxml"));
        primaryStage.setTitle("Classifer | Login");
        primaryStage.setScene(new Scene(root, 630, 463));
        primaryStage.setResizable(false);
        primaryStage.show();
    }

    public static void main(String[] args) {

        launch(args);
        /*System.out.println(BCrypt.hashpw("Admin123",BCrypt.gensalt()));
        System.out.println(BCrypt.hashpw("kamal123",BCrypt.gensalt()));
        System.out.println(BCrypt.hashpw("indika123",BCrypt.gensalt()));*/
    }
}
