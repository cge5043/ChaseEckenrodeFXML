/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import java.io.IOException;
import java.net.URL;
import java.util.Date;
import java.util.List;
import java.util.ResourceBundle;
import java.util.Scanner;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import javax.persistence.EntityManager;
import javax.persistence.Persistence;
import javax.persistence.Query;
import model.Mailbox;

/**
 *
 * @author cge19
 */
public class FXMLDocumentController implements Initializable {

    @FXML
    private Label label;
    
    @FXML
    private Button buttonCreateEmail;

    @FXML
    private Button buttonDeleteEmail;

    @FXML
    private Button buttonReadByID;

    @FXML
    private Button buttonReadByEmailAddress;

    @FXML
    private Button buttonUpdate;

    @FXML
    private Button buttonReadByIdAndEmailAddress;
    
    @FXML
    private TextField textboxName;
    
    @FXML
    private TableView<Mailbox> emailTable;
    
    @FXML
    private TableColumn<Mailbox, Integer> canvasID;
    
    @FXML
    private TableColumn<Mailbox, String> emailSender;
    
    @FXML
    private TableColumn<Mailbox, String> emailTitle;
    
    @FXML
    private TableColumn<Mailbox, Date> emailDate;

    private ObservableList<Mailbox> emailData;

    public void setTableData(List<Mailbox> emailList) {
        emailData = FXCollections.observableArrayList();
        emailList.forEach(s -> {
            emailData.add(s);
        });
        emailTable.setItems(emailData);
        emailTable.refresh();
    }

    @FXML
    void searchByEmailAction(ActionEvent event) {
        System.out.println("clicked");       
        String name = textboxName.getText();
//        List<Mailbox> emails = readByEmail(name);
//
//        if (emails == null || emails.isEmpty()) {
//
//            // show an alert to inform user 
//            Alert alert = new Alert(Alert.AlertType.INFORMATION);
//            alert.setTitle("Information Dialog Box");// line 2
//            alert.setHeaderText("This is header section to write heading");// line 3
//            alert.setContentText("No email");
//            alert.showAndWait(); 
//        } else {
//
//            // setting table data
//            setTableData(emails);
//        }

    }

    @FXML
    void searchByEmailAdvancedAction(ActionEvent event) {
        System.out.println("clicked");     
        String name = textboxName.getText();

//        List<Mailbox> emails = readByNameAdvanced(name);
//
//        if (emails == null || emails.isEmpty()) {
//            Alert alert = new Alert(Alert.AlertType.INFORMATION);
//            alert.setTitle("Information Dialog Box");
//            alert.setHeaderText("This is header section to write heading");
//            alert.setContentText("No email");
//            alert.showAndWait();
//        } 
//        else {
//            setTableData(emails);
//        }
    }

    @FXML
    void actionShowDetails(ActionEvent event) throws IOException {
        System.out.println("clicked");

        Mailbox selectedEmail = emailTable.getSelectionModel().getSelectedItem();
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/view/DetailModelView.fxml"));
        Parent detailedModelView = loader.load();
        Scene tableViewScene = new Scene(detailedModelView);
        //DetailModelController detailedControlled = loader.getController();
        //detailedControlled.initData(selectedEmail);
        Stage stage = new Stage();
        stage.setScene(tableViewScene);
        stage.show();
    }

    @FXML
    void actionShowDetailsInPlace(ActionEvent event) throws IOException {
        System.out.println("clicked");

        Mailbox selectedEmail = emailTable.getSelectionModel().getSelectedItem();
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/view/DetailModelView.fxml"));
        Parent detailedModelView = loader.load();
        Scene tableViewScene = new Scene(detailedModelView);
        //DetailModelController detailedControlled = loader.getController();
        //detailedControlled.initData(selectedEmail);
        Scene currentScene = ((Node) event.getSource()).getScene();
        //detailedControlled.setPreviousScene(currentScene);
        Stage stage = (Stage) currentScene.getWindow();
        stage.setScene(tableViewScene);
        stage.show();
    }

    @FXML
    void createEmail(ActionEvent event) {
        Scanner input = new Scanner(System.in);
        
        System.out.println("Enter ID:");
        int id = input.nextInt();
        
        System.out.println("Enter Email Address:");
        String emailAddress = input.next();
        
        System.out.println("Enter Email Title:");
        String emailTitle = input.next();
        
        System.out.println("Enter Email Text:");
        String emailText = input.next();
        
        Mailbox email = new Mailbox();
        
        
        email.setCanvasaccountid(id);
        email.setEmailsender(emailAddress);
        email.setEmailtitle(emailTitle);
        email.setEmailtext(emailText);
            
        create(email);

    }

    @FXML
    void deleteEmail(ActionEvent event) {
        Scanner input = new Scanner(System.in);
        
        System.out.println("Enter ID:");
        int id = input.nextInt();
        
        Mailbox e = readById(id);
        System.out.println("Deleting email number: "+ e.toString());
        delete(e);
        System.out.println("Email number: " + id + "is successfully deleted!");

    }
    

    @FXML
    void readByID(ActionEvent event) {
        Scanner input = new Scanner(System.in);
        
        System.out.println("Enter ID:");
        int id = input.nextInt();
        
        Mailbox e = readById(id);
        System.out.println(e.toString());

    }

    @FXML
    void readByEmailAddress(ActionEvent event) {
        Scanner input = new Scanner(System.in);
        
        System.out.println("Enter Name:");
        String address = input.next();
        
        //List<Email> e = readByEmailAddress(address);
        //System.out.println(e.toString());

    }

    @FXML
    void readByIdAndEmailAddress(ActionEvent event) {
        Scanner input = new Scanner(System.in);
        
        System.out.println("Enter ID:");
        int id = input.nextInt();
        
        System.out.println("Enter Email Address:");
        String address = input.next();
            
//        List<Mailbox> emails =  readByIdAndEmailAddress(id, address);

    }

    @FXML
    void readEmail(ActionEvent event) {

    }

    @FXML
    void updateEmails(ActionEvent event) {
        Scanner input = new Scanner(System.in);
        
        System.out.println("Enter An ID:");
        int id = input.nextInt();
        
        System.out.println("Enter Email Address:");
        String emailAddress = input.next();
        
        System.out.println("Enter Email Title:");
        String emailTitle = input.next();
        
        Mailbox email = new Mailbox();
        
        email.setCanvasaccountid(id);
        email.setEmailsender(emailAddress);
        email.setEmailtitle(emailTitle);
                
//        update(email);

    }
    

    @FXML
    private void handleButtonAction(ActionEvent event) {
        System.out.println("You clicked me!");
        label.setText("Hello World!");

        Query query = manager.createNamedQuery("Email.findAll");
        List<Mailbox> data = query.getResultList();

        for (Mailbox e : data) {
            System.out.println(e.getCanvasaccountid() + " " + e.getEmailsender() + " " + e.getEmailtitle());
        }
    }

    // Database manager Got from class code
    EntityManager manager;

    @Override //Got from class code
    public void initialize(URL url, ResourceBundle rb) {
        //database reference: "IntroJavaFXPU"
        manager = (EntityManager) Persistence.createEntityManagerFactory("gp11_db").createEntityManager();

    }

    /*
    Implementing CRUD operations Got from class code
    */
    
    // Create operation //from class code
    public void create(Mailbox email) {
        try {

            manager.getTransaction().begin();

            if (email.getCanvasaccountid() != null) {
                
                manager.persist(email);

                manager.getTransaction().commit();
                
                System.out.println(email.toString() + " is created");
            }
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }
    }
    
    // Read Operations //from class code
    public List<Mailbox> readAll(){
        Query query = manager.createNamedQuery("Email.findAll");
        List<Mailbox> emails = query.getResultList();

        for (Mailbox e : emails) {
            System.out.println(e.getCanvasaccountid() + " " + e.getEmailsender() + " " + e.getEmailtitle());
        }
        
        return emails;
    }
    
    //from class code
    public Mailbox readById(int id){
        Query query = manager.createNamedQuery("Email.findById");
        

        query.setParameter("id", id);
        

        Mailbox e = (Mailbox) query.getSingleResult();
        if (e != null) {
            System.out.println(e.getCanvasaccountid() + " " + e.getEmailsender() + " " + e.getEmailtitle());
        }
        
        return e;
    }   
    
    //from class code
    public List<Mailbox> readByEmailAddress(String emailAddress){
        Query query = manager.createNamedQuery("Email.findByEmailAddress");
        

        query.setParameter("emailAdress", emailAddress);
        

        List<Mailbox> email =  query.getResultList();
        for (Mailbox e: email) {
            System.out.println(e.getCanvasaccountid() + " " + e.getEmailsender() + " " + e.getEmailtitle());
        }
        
        return email;
    }        
    
    //public List<Mailbox> readByIdAndEmailAddress(int id, String emailAddress){
        //Query Mailbox = manager.createNamedQuery("Email.findByIdAndEmailAddress");
        
        //query.setParameter("id", id);
        //query.setParameter("email address", emailAddress);
        
        //List<Mailbox> emails =  query.getResultList();
        //for (Mailbox email: emails) {
        //    System.out.println(email.getCanvasaccountid() + " " + email.getEmailsender() + " " + email.getEmailtitle());
        //}
        
        //return emails;
    //}        
    
    
    // Update operation from class code
//    public void update(Mailbox model) {
//        try {
//
//            Mailbox existingEmail = manager.find(Mailbox.class, model.canvasAccountId());
//
//            if (existingEmail != null) {
//                manager.getTransaction().begin();
//                
//                existingEmail.setEmailsender(model.getEmailsender());
//                existingEmail.setEmailtitle(model.getEmailtitle());
//                
//                manager.getTransaction().commit();
//            }
//        } catch (Exception ex) {
//            System.out.println(ex.getMessage());
//        }
//    }

    // Delete operation from class code
    public void delete(Mailbox email) {
        try {
            Mailbox existingEmail = manager.find(Mailbox.class, email.getCanvasaccountid());


            if (existingEmail != null) {
                
                manager.getTransaction().begin();

                manager.remove(existingEmail);
                
                manager.getTransaction().commit();
            }
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }
    }

}
