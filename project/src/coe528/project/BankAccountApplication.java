/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package coe528.project;

import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox; 
import javafx.stage.Stage;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.text.*;
/**
 *
 * @author lttran
 */
public class BankAccountApplication extends Application {
    private static Manager manager = new Manager();
    @Override
    public void start(final Stage primaryStage) {
        primaryStage.setTitle("Banking Application");
        
        GridPane grid = new GridPane();
        grid.setAlignment(Pos.CENTER);
        grid.setHgap(10);
        grid.setVgap(10);

        grid.add(new Text("Welcome"), 0, 0, 2, 1);

        Button btn1 = new Button("Sign in as manager");
        HBox hbBtn1 = new HBox(10);
        hbBtn1.getChildren().add(btn1);
        grid.add(hbBtn1, 0, 1);
        
        Button btn2 = new Button("Sign in as user");
        HBox hbBtn2 = new HBox(10);
        hbBtn2.getChildren().add(btn2);
        grid.add(hbBtn2, 0, 2);
        
        Button close = new Button("Close");
        HBox hbBtn3 = new HBox(10);
        hbBtn3.getChildren().add(close);
        grid.add(hbBtn3, 0, 3);
        
        Scene scene = new Scene(grid, 300, 275);
        primaryStage.setScene(scene);
        
        close.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent e) {
                primaryStage.close();
            }
        });
     
        btn1.setOnAction(new EventHandler<ActionEvent>(){
          @Override
          public void handle(ActionEvent e) {
                final Stage mstage = new Stage(); 
                mstage.setTitle("Manager Login"); 

                final GridPane grid = new GridPane();
                grid.setAlignment(Pos.CENTER);
                grid.setHgap(10);
                grid.setVgap(10);
                
                grid.add(new Label("User Name:"), 0, 1);
                final TextField userTextField = new TextField();
                grid.add(userTextField, 1, 1);
                grid.add(new Label("Password:"), 0, 2);
                final PasswordField pwBox = new PasswordField();
                grid.add(pwBox, 1, 2);

                Button btn = new Button("Login");
                HBox hbBtn = new HBox(10);
                hbBtn.getChildren().add(btn);
                grid.add(hbBtn, 1, 3);
                
                Button back = new Button("Logout");
                HBox hbBtn2 = new HBox(10);
                hbBtn2.getChildren().add(back);
                grid.add(hbBtn2, 1, 4);
                
                back.setOnAction(new EventHandler<ActionEvent>() {
                @Override
                public void handle(ActionEvent e) {
                    mstage.close();
                }
                });

                btn.setOnAction(new EventHandler<ActionEvent>() {
                @Override
                public void handle(ActionEvent e) {
                    String name = userTextField.getText();
                    String pass = pwBox.getText();
                    if(name.equals("") || pass.equals("")){
                        grid.add(new Text("Please enter again"),1,5);
                    }else{
                        try {
                            if (manager.logIn(name,pass)){
                                final Stage stage = new Stage();
                                stage.setTitle("Manager Window");
                                
                                GridPane grid = new GridPane();
                                grid.setAlignment(Pos.CENTER);
                                grid.setHgap(10);
                                grid.setVgap(10);
                               
                                grid.add(new Text("Options"), 0, 0, 2, 1);
                                
                                Button btn1 = new Button("Add Customer");
                                HBox hbBtn1 = new HBox(10);
                                hbBtn1.getChildren().add(btn1);
                                grid.add(hbBtn1, 0, 1);
                                
                                Button btn2 = new Button("Delete Customer");
                                HBox hbBtn2 = new HBox(10);
                                hbBtn2.getChildren().add(btn2);
                                grid.add(hbBtn2, 0, 2);
                                
                                Button btn3 = new Button("Back");
                                HBox hbBtn3 = new HBox(10);
                                hbBtn3.getChildren().add(btn3);
                                grid.add(hbBtn3, 1, 3);
                                
                                btn1.setOnAction(new EventHandler<ActionEvent>() {
                                    @Override
                                    public void handle(ActionEvent e) {
                                        final Stage stage = new Stage();
                                        stage.setTitle("Add Customer");
                                        
                                        final GridPane grid = new GridPane();
                                        grid.setAlignment(Pos.CENTER);
                                        grid.setHgap(10);
                                        grid.setVgap(10);
                                        
                                        Label userName = new Label("User Name:");
                                        grid.add(userName, 0, 1);
                                        
                                        final TextField userTextField = new TextField();
                                        grid.add(userTextField, 1, 1);
                                        Label pw = new Label("Password:");
                                        grid.add(pw, 0, 2);
                                        final PasswordField pwBox = new PasswordField();
                                        grid.add(pwBox, 1, 2);
                                        
                                        final Button btn = new Button("Add");
                                        HBox hbBtn = new HBox(10);
                                        hbBtn.getChildren().add(btn);
                                        grid.add(hbBtn, 1, 3);
                                        
                                        final TextField actiontarget = new TextField();
                                        grid.add(actiontarget, 1, 5);
                                        actiontarget.setDisable(true);
                                        
                                        btn.setOnAction(new EventHandler<ActionEvent>() {
                                            @Override
                                            public void handle(ActionEvent e) {
                                                String name = userTextField.getText();
                                                String pass = pwBox.getText();
                                                if(name.equals("") || pass.equals("")){
                                                    actiontarget.clear();
                                                    actiontarget.setText("Please try again");
                                                }else{
                                                try {
                                                    if (manager.addCustomer(name,pass)){
                                                        actiontarget.clear();
                                                        actiontarget.setText("New customer added");
                                                    }else{
                                                        actiontarget.clear();
                                                        actiontarget.setText("Add unsuccessful, try again");
                                                    }
                                                } catch (IOException ex) {
                                                    Logger.getLogger(BankAccountApplication.class.getName()).log(Level.SEVERE, null, ex);
                                                }
                                               }
                                            }
                                        });
                                        
                                        Button btn2 = new Button("Back");
                                        HBox hbBtn2 = new HBox(10);
                                        hbBtn2.getChildren().add(btn2);
                                        grid.add(hbBtn2, 1, 4);
                                        
                                        btn2.setOnAction(new EventHandler<ActionEvent>() {
                                            @Override
                                            public void handle(ActionEvent e) {
                                                stage.close();
                                            }
                                        });
                                        
                                        Scene scene = new Scene(grid, 300, 275);
                                        stage.setScene(scene);
                                        stage.show();
                                    }
                                });
                                
                                btn2.setOnAction(new EventHandler<ActionEvent>() {
                                    @Override
                                    public void handle(ActionEvent e) {
                                        final Stage stage = new Stage();
                                        stage.setTitle("Delete Customer");
                                        
                                        final GridPane grid = new GridPane();
                                        grid.setAlignment(Pos.CENTER);
                                        grid.setHgap(10);
                                        grid.setVgap(10);
                                        
                                        Label userName = new Label("User Name:");
                                        grid.add(userName, 0, 1);
                                        final TextField userTextField = new TextField();
                                        grid.add(userTextField, 1, 1);
                                        
                                        Button btn = new Button("Delete");
                                        HBox hbBtn = new HBox(10);
                                        hbBtn.getChildren().add(btn);
                                        grid.add(hbBtn, 1, 2);
                                        
                                        Button btn2 = new Button("Back");
                                        HBox hbBtn2 = new HBox(10);
                                        hbBtn2.getChildren().add(btn2);
                                        grid.add(hbBtn2, 1, 3);
                                        
                                        final TextField actiontarget = new TextField();
                                        grid.add(actiontarget, 1, 4);
                                        actiontarget.setDisable(true);
                                        
                                        btn.setOnAction(new EventHandler<ActionEvent>() {
                                            @Override
                                            public void handle(ActionEvent e) {
                                                String name = userTextField.getText();
                                                
                                                try {
                                                    if (manager.deleteCustomer(name)){
                                                        actiontarget.clear();
                                                        actiontarget.setText("Customer deleted");
                                                    }else{
                                                        actiontarget.clear();
                                                        actiontarget.setText("Please try again");
                                                    }
                                                } catch (IOException ex) {
                                                    Logger.getLogger(BankAccountApplication.class.getName()).log(Level.SEVERE, null, ex);
                                                }
                                                
                                            }
                                        });
                                        
                                        btn2.setOnAction(new EventHandler<ActionEvent>() {
                                            @Override
                                            public void handle(ActionEvent e) {
                                                stage.close();
                                            }
                                        });
                                        
                                        Scene scene = new Scene(grid, 300, 275);
                                        stage.setScene(scene);
                                        stage.show();
                                    }
                                });
                                
                                btn3.setOnAction(new EventHandler<ActionEvent>() {
                                    @Override
                                    public void handle(ActionEvent e) {
                                        stage.close();
                                        mstage.toFront();
                                    }
                                });
                                
                                Scene scene = new Scene(grid, 300, 275);
                                stage.setScene(scene);
                                stage.show();
                                
                            }else{
                                grid.add(new Text("Wrong username and/or password"),1,5);
                            }
                        } catch (IOException ex) {
                            Logger.getLogger(BankAccountApplication.class.getName()).log(Level.SEVERE, null, ex);
                        }
                   }
               }
            });
          Scene scene = new Scene(grid, 300, 275);
                mstage.setScene(scene);         
                mstage.show(); 
          }
        });


        btn2.setOnAction(new EventHandler<ActionEvent>(){
            @Override
            public void handle(ActionEvent e) {
                final Stage cstage = new Stage(); 
                cstage.setTitle("User Login"); 

                final GridPane grid = new GridPane();
                grid.setAlignment(Pos.CENTER);
                grid.setHgap(10);
                grid.setVgap(10);

                Label userName = new Label("User Name:");
                grid.add(userName, 0, 1);
                final TextField userTextField = new TextField();
                grid.add(userTextField, 1, 1);
                Label pw = new Label("Password:");
                grid.add(pw, 0, 2);

                final PasswordField pwBox = new PasswordField();
                grid.add(pwBox, 1, 2);

                Button btn = new Button("Login");
                HBox hbBtn = new HBox(10);
                hbBtn.getChildren().add(btn);
                grid.add(hbBtn, 1, 3);
                
                Button back = new Button("Logout");
                HBox hbBtn2 = new HBox(10);
                hbBtn2.getChildren().add(back);
                grid.add(hbBtn2, 1, 4);
                
                back.setOnAction(new EventHandler<ActionEvent>() {
                @Override
                public void handle(ActionEvent e) {
                    cstage.close();
                }
                });
                
                btn.setOnAction(new EventHandler<ActionEvent>() {
                    @Override
                    public void handle(ActionEvent e) {
                        String name = userTextField.getText();
                        String pass = pwBox.getText();
                        if (name.equals("") || pass.equals("")){
                            grid.add(new Text("Please enter both name and password"), 1, 5);
                        }else{
                            final Customer a = new Customer(name,pass);
                        if (a.logIn()){
                            final Stage stage = new Stage();
                            stage.setTitle("Customer Window");

                            GridPane grid = new GridPane();
                            grid.setAlignment(Pos.CENTER);
                            grid.setHgap(10);
                            grid.setVgap(10);
                            grid.add(new Text("Options"), 0, 0, 2, 1);

                            Button btn1 = new Button("Deposit");
                            HBox hbBtn1 = new HBox(10);
                            hbBtn1.getChildren().add(btn1);
                            grid.add(hbBtn1, 0, 1);

                            Button btn2 = new Button("Withdraw");
                            HBox hbBtn2 = new HBox(10);
                            hbBtn2.getChildren().add(btn2);
                            grid.add(hbBtn2, 0, 2);

                            Button btn3 = new Button("Online Purchase");
                            HBox hbBtn3 = new HBox(10);
                            hbBtn3.getChildren().add(btn3);
                            grid.add(hbBtn3, 0, 3);

                            Button btn4 = new Button("Get Balance");
                            HBox hbBtn4 = new HBox(10);
                            hbBtn4.getChildren().add(btn4);
                            grid.add(hbBtn4, 0, 4);

                            Button btn5 = new Button("Back");
                            HBox hbBtn5 = new HBox(10);
                            hbBtn5.getChildren().add(btn5);
                            grid.add(hbBtn5, 1, 5);
                            
                            final TextField textarea = new TextField();
                            grid.add(textarea, 1, 4);
                            textarea.setDisable(true);
                                    
                            btn1.setOnAction(new EventHandler<ActionEvent>(){
                                @Override
                                public void handle(ActionEvent e){
                                    final Stage stage = new Stage();
                                    stage.setTitle("Deposit");
                                    
                                    final GridPane grid = new GridPane();
                                    grid.setAlignment(Pos.CENTER);
                                    grid.setHgap(10);
                                    grid.setVgap(10);

                                    grid.add(new Label("Enter Amount:"), 0, 1);
                                    final TextField userTextField = new TextField();
                                    grid.add(userTextField, 1, 1);
                                    
                                    Button btn = new Button("OK");
                                    HBox hbBtn = new HBox(10);
                                    hbBtn.getChildren().add(btn);
                                    grid.add(hbBtn, 1, 2);
                                    
                                    final TextField text = new TextField();
                                    grid.add(text, 1, 4);
                                    text.setDisable(true);
                              
                                    btn.setOnAction(new EventHandler<ActionEvent>(){
                                        @Override
                                        public void handle(ActionEvent e){
                                            String amount = userTextField.getText();
                                            if(amount.equals("")){
                                                text.clear();
                                                text.setText("Please enter an amount");
                                            }else{
                                                double num = Double.parseDouble(amount);
                                                if (num < 0){
                                                    text.clear();
                                                    text.setText("Please enter a different amount");
                                                }else{
                                                    try {
                                                    a.depositMoney(num);
                                                    } catch (IOException ex) {
                                                        Logger.getLogger(BankAccountApplication.class.getName()).log(Level.SEVERE, null, ex);
                                                    }
                                                    text.clear();
                                                    text.setText("Deposited");
                                                }
                                            }
                                        }
                                    });
                                    
                                    Button btn2 = new Button("Back");
                                    HBox hbBtn2 = new HBox(10);
                                    hbBtn2.getChildren().add(btn2);
                                    grid.add(hbBtn2, 1, 3);
                                    
                                    btn2.setOnAction(new EventHandler<ActionEvent>() {
                                        @Override
                                        public void handle(ActionEvent e) {
                                            stage.close();
                                            textarea.clear();
                                        }
                                    });
                                    
                                    Scene scene = new Scene(grid, 300, 275);
                                    stage.setScene(scene);         
                                    stage.show();
                                }
                            });
                            
                            btn2.setOnAction(new EventHandler<ActionEvent>(){
                                @Override
                                public void handle(ActionEvent e){
                                    final Stage stage = new Stage();
                                    stage.setTitle("Withdraw"); 
                    
                                    final GridPane grid = new GridPane();
                                    grid.setAlignment(Pos.CENTER);
                                    grid.setHgap(10);
                                    grid.setVgap(10);

                                    grid.add(new Label("Enter Amount:"), 0, 1);
                                    final TextField userTextField = new TextField();
                                    grid.add(userTextField, 1, 1);
                                    
                                    Button btn = new Button("OK");
                                    HBox hbBtn = new HBox(10);
                                    hbBtn.getChildren().add(btn);
                                    grid.add(hbBtn, 1, 2);
                                    
                                    final TextField text = new TextField();
                                    grid.add(text, 1, 4);
                                    text.setDisable(true);
                                    
                                    btn.setOnAction(new EventHandler<ActionEvent>(){
                                        @Override
                                        public void handle(ActionEvent e){
                                            String amount = userTextField.getText();
                                            if(amount.equals("")){
                                                text.clear();
                                                text.setText("Please enter an amount");
                                            }else{
                                                double num = Double.parseDouble(amount);
                                                try {
                                                    if (a.withdrawMoney(num)){
                                                        text.clear();
                                                        text.setText("Withdrawn");
                                                    }else{
                                                        text.clear();
                                                        text.setText("Please enter a different amount");
                                                    }
                                                } catch (IOException ex) {
                                                    Logger.getLogger(BankAccountApplication.class.getName()).log(Level.SEVERE, null, ex);
                                                }
                                            }
                                        }
                                    });
                                    
                                    Button btn2 = new Button("Back");
                                    HBox hbBtn2 = new HBox(10);
                                    hbBtn2.getChildren().add(btn2);
                                    grid.add(hbBtn2, 1, 3);
                                    
                                    btn2.setOnAction(new EventHandler<ActionEvent>() {
                                        @Override
                                        public void handle(ActionEvent e) {
                                            stage.close();
                                            textarea.clear();
                                        }
                                    });
                                    
                                    Scene scene = new Scene(grid, 300, 275);
                                    stage.setScene(scene);         
                                    stage.show();
                                }
                            });
                            
                            btn3.setOnAction(new EventHandler<ActionEvent>(){
                                @Override
                                public void handle(ActionEvent e){
                                    final Stage stage = new Stage();
                                    stage.setTitle("Online Purchase"); 
                    
                                    final GridPane grid = new GridPane();
                                    grid.setAlignment(Pos.CENTER);
                                    grid.setHgap(10);
                                    grid.setVgap(10);

                                    Label userName = new Label("Enter Price:");
                                    grid.add(userName, 0, 1);
                                    final TextField userTextField = new TextField();
                                    grid.add(userTextField, 1, 1);
                                    
                                    Button btn = new Button("OK");
                                    HBox hbBtn = new HBox(10);
                                    hbBtn.getChildren().add(btn);
                                    grid.add(hbBtn, 1, 2);
                                    
                                    final TextField text = new TextField();
                                    grid.add(text, 1, 4);
                                    text.setDisable(true);
                                    
                                    btn.setOnAction(new EventHandler<ActionEvent>(){
                                        @Override
                                        public void handle(ActionEvent e){
                                            String amount = userTextField.getText();
                                            if(amount.equals("")){
                                                text.clear();
                                                text.setText("Please enter an amount");
                                            }else{
                                                double num = Double.parseDouble(amount);
                                                try {
                                                    if (a.purchaseOnline(num)){
                                                        text.clear();
                                                        text.setText("Purchase done");
                                                    }else
                                                    {
                                                        text.clear();
                                                        text.setText("Please enter a different amount");
                                                    }
                                                } catch (IOException ex) {
                                                    Logger.getLogger(BankAccountApplication.class.getName()).log(Level.SEVERE, null, ex);
                                                }
                                            }
                                        }
                                    });
                                    
                                    Button btn2 = new Button("Back");
                                    HBox hbBtn2 = new HBox(10);
                                    hbBtn2.getChildren().add(btn2);
                                    grid.add(hbBtn2, 1, 3);
                                    
                                    btn2.setOnAction(new EventHandler<ActionEvent>() {
                                        @Override
                                        public void handle(ActionEvent e) {
                                            stage.close();
                                            textarea.clear();
                                        }
                                    });
                                    
                                    Scene scene = new Scene(grid, 300, 275);
                                    stage.setScene(scene);         
                                    stage.show();
                                }
                            });
                            
                            btn4.setOnAction(new EventHandler<ActionEvent>() {
                                        @Override
                                        public void handle(ActionEvent e) {
                                            String balance = " ";
                                            balance = " " + a.getBalance();
                                            textarea.setText(balance);
                                        }
                            });
                            
                            btn5.setOnAction(new EventHandler<ActionEvent>() {
                                        @Override
                                        public void handle(ActionEvent e){
                                            stage.close();
                                            cstage.toFront();
                                        }
                            });

                            Scene scene = new Scene(grid, 300, 275);
                            stage.setScene(scene);         
                            stage.show();
                        }else{
                            grid.add(new Text("Wrong username and/or password"), 1, 5);
                            }
                         }
                        }
                    });
                
                Scene scene = new Scene(grid, 300, 275);
                cstage.setScene(scene);         
                cstage.show(); 
            }
        });
        primaryStage.show();
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }
    
}

