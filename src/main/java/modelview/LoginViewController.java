/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package modelview;

import com.google.firebase.auth.FirebaseAuthException;
import com.google.firebase.auth.UserRecord;
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
    @FXML
    private Label statusMessageLabel;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }

    @FXML
    private void loginButtonPress(ActionEvent event) {

        if (login()) {
            try {
                App.setRoot("AccessFBView.fxml");
            } catch (IOException ex) {
                Logger.getLogger(WebContainerController.class.getName()).log(Level.SEVERE, null, ex);
            }
        } else {
            statusMessageLabel.setText("User not found");
            statusMessageLabel.setVisible(true);
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


    private boolean login() {
        
        String emailInput = emailTextField.getText().trim();
        
        //Password verification not implemented. Just included as proof of concept
        String passwordInput = passwordTextField.getText().trim();
        
        
        try {
            App.loggedInUser = App.fauth.getInstance().getUserByEmail(emailInput);
            // See the UserRecord reference doc for the contents of userRecord.
            System.out.println("Successfully fetched user data: " + App.loggedInUser.getEmail());
            return true;
        } catch (FirebaseAuthException ex) {
            Logger.getLogger(SignUpViewController.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        }
    }
}
