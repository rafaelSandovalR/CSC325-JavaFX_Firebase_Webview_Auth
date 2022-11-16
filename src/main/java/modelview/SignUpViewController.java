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
public class SignUpViewController{

    @FXML
    private TextField emailTextField;
    @FXML
    private TextField passwordTextField;
    @FXML
    private Label statusMessageLabel;
    @FXML
    private TextField usernameTextField;

    /**
     * Initializes the controller class.
     */
    
    public void initialize() {
        // TODO
    }

    @FXML
    private void createAccountButtonPress(ActionEvent event) {

        if (registerUser()) {
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
        String usernameInput = usernameTextField.getText().trim();
       
        if (userExists(emailInput)) {
            displayMessage("Email Already Exists");
            return false;
        } 
        else if(!validatePassword(passwordInput)){
            displayMessage("Password must be at least six characters long");
            return false;
        }
        else {
            UserRecord.CreateRequest request = new UserRecord.CreateRequest()
                    .setEmail(emailInput)
                    .setDisplayName(usernameInput)
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
        
        try {
            App.fauth.getInstance().getUserByEmail(email);
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
    
    private boolean validatePassword(String password){
        if(password.length() >= 6){
            return true;
        }
        else
            return false;
    }

    @FXML
    private void backButtonPress(ActionEvent event) {
        try {
            App.setRoot("LoginView.fxml");
        } catch (IOException ex) {
            Logger.getLogger(WebContainerController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

}
