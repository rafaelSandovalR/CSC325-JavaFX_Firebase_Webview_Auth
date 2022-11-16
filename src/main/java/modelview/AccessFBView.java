package modelview;

import com.mycompany.mvvmexample.App;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.stage.Window;

public class AccessFBView implements Initializable {

    @FXML
    private Label welcomeMessageLabel;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        System.out.println(App.loggedInUser.getDisplayName());
        welcomeMessageLabel.setText("Welcome back " + App.loggedInUser.getDisplayName());
    }

    @FXML
    private void switchToSecondary() throws IOException {
        App.setRoot("WebContainer.fxml");
    }

    @FXML
    private void displayDatabaseMenuPress(ActionEvent event) {
    }

    @FXML
    private void logOutMenuPress(ActionEvent event) {
        App.loggedInUser = null;
        try {
            App.setRoot("LoginView.fxml");
        } catch (IOException ex) {
            Logger.getLogger(WebContainerController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

}
