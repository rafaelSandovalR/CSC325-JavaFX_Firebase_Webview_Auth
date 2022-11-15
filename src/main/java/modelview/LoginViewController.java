/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
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
import javafx.scene.control.TextField;

/**
 * FXML Controller class
 *
 * @author Rafael Sandoval Ramirez
 */
public class LoginViewController implements Initializable {

    @FXML
    private TextField emailTextField;
    @FXML
    private TextField passwordTextField;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    @FXML
    private void loginButtonPress(ActionEvent event) {
        try {
            App.setRoot("AccessFBView.fxml");
        } catch (IOException ex) {
            Logger.getLogger(WebContainerController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @FXML
    private void SignUpButtonPress(ActionEvent event) {
        try {
            App.setRoot("SignUpView.fxml");
        } catch (IOException ex) {
            Logger.getLogger(WebContainerController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
}
