/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;
import java.util.Scanner;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javax.persistence.EntityManager;
import javax.persistence.Persistence;
import javax.persistence.Query;
import model.Email;

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
        
        Email email = new Email();
        

        email.setId(id);
        email.setEmailaddress(emailAddress);
        email.setEmailtitle(emailTitle);
        email.setEmailtext(emailText);
            
        create(email);

    }

    @FXML
    void deleteEmail(ActionEvent event) {
        Scanner input = new Scanner(System.in);
        
        System.out.println("Enter ID:");
        int id = input.nextInt();
        
        Email e = readById(id);
        System.out.println("we are deleting email number: "+ e.toString());
        delete(e);

    }
    

    @FXML
    void readByID(ActionEvent event) {
        Scanner input = new Scanner(System.in);
        
        System.out.println("Enter ID:");
        int id = input.nextInt();
        
        Email e = readById(id);
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
            
        List<Email> emails =  readByIdAndEmailAddress(id, address);

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
        
        Email email = new Email();
        
        email.setId(id);
        email.setEmailaddress(emailAddress);
        email.setEmailtitle(emailTitle);
                
        update(email);

    }
    

    @FXML
    private void handleButtonAction(ActionEvent event) {
        System.out.println("You clicked me!");
        label.setText("Hello World!");

        Query query = manager.createNamedQuery("Email.findAll");
        List<Email> data = query.getResultList();

        for (Email e : data) {
            System.out.println(e.getId() + " " + e.getEmailaddress() + " " + e.getEmailtitle());
        }
    }

    // Database manager Got from class code
    EntityManager manager;

    @Override //Got from class code
    public void initialize(URL url, ResourceBundle rb) {
        //database reference: "IntroJavaFXPU"
        manager = (EntityManager) Persistence.createEntityManagerFactory("IntroJavaFXPU").createEntityManager();

    }

    /*
    Implementing CRUD operations Got from class code
    */
    
    // Create operation //from class code
    public void create(Email email) {
        try {

            manager.getTransaction().begin();

            if (email.getId() != null) {
                
                manager.persist(email);

                manager.getTransaction().commit();
                
                System.out.println(email.toString() + " is created");
            }
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }
    }
    
    // Read Operations //from class code
    public List<Email> readAll(){
        Query query = manager.createNamedQuery("Email.findAll");
        List<Email> emails = query.getResultList();

        for (Email e : emails) {
            System.out.println(e.getId() + " " + e.getEmailaddress() + " " + e.getEmailtitle());
        }
        
        return emails;
    }
    
    //from class code
    public Email readById(int id){
        Query query = manager.createNamedQuery("Email.findById");
        

        query.setParameter("id", id);
        

        Email e = (Email) query.getSingleResult();
        if (e != null) {
            System.out.println(e.getId() + " " + e.getEmailaddress() + " " + e.getEmailtitle());
        }
        
        return e;
    }   
    
    //from class code
    public List<Email> readByEmailAddress(String emailAddress){
        Query query = manager.createNamedQuery("Email.findByEmailAddress");
        

        query.setParameter("emailAdress", emailAddress);
        

        List<Email> email =  query.getResultList();
        for (Email e: email) {
            System.out.println(e.getId() + " " + e.getEmailaddress() + " " + e.getEmailtitle());
        }
        
        return email;
    }        
    
    public List<Email> readByIdAndEmailAddress(int id, String emailAddress){
        Query query = manager.createNamedQuery("Email.findByIdAndEmailAddress");
        
        query.setParameter("id", id);
        query.setParameter("email address", emailAddress);
        
        List<Email> emails =  query.getResultList();
        for (Email email: emails) {
            System.out.println(email.getId() + " " + email.getEmailaddress() + " " + email.getEmailtitle());
        }
        
        return emails;
    }        
    
    
    // Update operation from class code
    public void update(Email model) {
        try {

            Email existingEmail = manager.find(Email.class, model.getId());

            if (existingEmail != null) {
                manager.getTransaction().begin();
                
                existingEmail.setEmailaddress(model.getEmailaddress());
                existingEmail.setEmailtitle(model.getEmailtitle());
                
                manager.getTransaction().commit();
            }
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }
    }

    // Delete operation from class code
    public void delete(Email email) {
        try {
            Email existingEmail = manager.find(Email.class, email.getId());


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
