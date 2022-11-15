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
 * @author Rsand
 */
public class SignUpViewController implements Initializable {

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
    private void createAccountButtonPress(ActionEvent event) {

        boolean successful = registerUser();

        if (successful) {
            try {
                App.setRoot("LoginView.fxml");
            } catch (IOException ex) {
                Logger.getLogger(WebContainerController.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

    public boolean registerUser() {

        String emailInput = emailTextField.getText().trim();
        String passwordInput = passwordTextField.getText().trim();

        if (userExists(emailInput)) {
            displayMessage("Email Already Exists");
            return false;
        } else {
            UserRecord.CreateRequest request = new UserRecord.CreateRequest()
                    .setEmail(emailInput)
                    .setEmailVerified(false)
                    .setPassword(passwordInput)
                    .setDisabled(false);

            UserRecord userRecord;
            try {
                userRecord = App.fauth.createUser(request);
                System.out.println("Successfully created new user: " + userRecord.getUid());
                return true;

            } catch (FirebaseAuthException ex) {
                // Logger.getLogger(FirestoreContext.class.getName()).log(Level.SEVERE, null, ex);
                displayMessage("Error: Please Try Again");
                return false;
            }
        }
    }

    private boolean userExists(String email) {
        UserRecord userRecord;
        try {
            userRecord = App.fauth.getInstance().getUserByEmail(email);
            // See the UserRecord reference doc for the contents of userRecord.
            System.out.println("Successfully fetched user data: " + userRecord.getEmail());
            return true;
        } catch (FirebaseAuthException ex) {
            Logger.getLogger(SignUpViewController.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        }
    }

    private void displayMessage(String message) {
        statusMessageLabel.setText(message);
        statusMessageLabel.setVisible(true);
    }

}
