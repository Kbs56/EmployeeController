package sample;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.stage.Stage;

public class Main extends Application {

    Stage window;
    Scene scene;
    Label titleLabel;
    Button addButton;
    Button deleteButton;
    Button editButton;
    Button testButton;
    final String blueColor = "#007aff";
    TableView<Employee> tableView;
    Helper helper = new Helper();

    @Override
    public void start(Stage primaryStage) throws Exception {
        window = primaryStage;
        window.setTitle("EmployerTrak® Developed By Kenneth Sheldon");

        titleLabel = new Label("EmployerTrak®");
        titleLabel.setFont(new Font(42));
        titleLabel.setTextFill(Color.web(blueColor));

        addButton = new Button("Add Employee");
        deleteButton = new Button("Delete Employee");
        editButton = new Button("Edit Employee");
        testButton = new Button("Take Test");
        addButton.setId("menuButtons");
        deleteButton.setId("menuButtons");
        editButton.setId("menuButtons");
        testButton.setId("menuButtons");

        HBox optionButtons = new HBox(10);
        optionButtons.setAlignment(Pos.BASELINE_CENTER);
        optionButtons.setPadding(new Insets(10, 20, 10, 20));
        optionButtons.getChildren().addAll(addButton, createSpacer(), deleteButton, createSpacer(), editButton, createSpacer(), testButton);

        HBox middleView = new HBox(10);
        tableView = new TableView<>();
        TableColumn<Employee, String> idColumn = new TableColumn<>("ID");
        idColumn.setCellValueFactory(new PropertyValueFactory<>("id"));
        idColumn.setMinWidth(200);
        TableColumn<Employee, String> fnameColumn = new TableColumn<>("First");
        fnameColumn.setCellValueFactory(new PropertyValueFactory<>("fname"));
        fnameColumn.setMinWidth(200);
        TableColumn<Employee, String> lnameColumn = new TableColumn<>("Last");
        lnameColumn.setCellValueFactory(new PropertyValueFactory<>("lname"));
        lnameColumn.setMinWidth(200);
        TableColumn<Employee, String> siteColumn = new TableColumn<>("Job Site");
        siteColumn.setCellValueFactory(new PropertyValueFactory<>("siteId"));
        siteColumn.setMinWidth(200);
        TableColumn<Employee, String> testColumn = new TableColumn<>("Test Passed");
        testColumn.setCellValueFactory(new PropertyValueFactory<>("testPassed"));
        testColumn.setMinWidth(200);
        tableView.setItems(Helper.getAllRecords());
        tableView.setId("tableView");
        tableView.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);
        tableView.getColumns().addAll(idColumn, fnameColumn, lnameColumn, siteColumn, testColumn);

        middleView.getChildren().addAll(createSpacer(), tableView, createSpacer());

        VBox layout = new VBox(10);
        layout.setAlignment(Pos.TOP_CENTER);
        layout.setPadding(new Insets(20, 20, 20, 20));
        layout.getChildren().addAll(titleLabel, createVerticalSpacer(), middleView, createVerticalSpacer(), optionButtons);

        addButton.setOnAction(actionEvent -> {
            try {
                addScreen();
            } catch (Exception e) {
                e.printStackTrace();
            }
        });

        deleteButton.setOnAction(actionEvent -> {
            try {
                deleteScreen();
            } catch (Exception e) {
                e.printStackTrace();
            }
        });

        editButton.setOnAction(actionEvent -> {
            try {
                editScreen();
            } catch (Exception e) {
                e.printStackTrace();
            }
        });

        testButton.setOnAction(actionEvent -> {
            Employee employee = tableView.getSelectionModel().getSelectedItem();
            try {
                helper.setTestScore(employee.getId(), 0);
            } catch (Exception e) {
                e.printStackTrace();
            }
            testScreen();
        });

        scene = new Scene(layout, 1200, 600);
        scene.getStylesheets().add("sample/Button.css");
        BackgroundFill background_fill = new BackgroundFill(Color.web("#2b2d2f"),
                CornerRadii.EMPTY, Insets.EMPTY);
        Background background = new Background(background_fill);
        layout.setBackground(background);
        window.setScene(scene);
        window.show();
    }

    public void addScreen() {
        Label titleLabel;
        Label idLabel;
        Label fnameLabel;
        Label lnameLabel;
        Label siteLabel;
        Label testLabel;
        TextField idField;
        TextField fnameField;
        TextField lnameField;
        TextField siteField;
        TextField testField;
        Button cancelButton;
        Button saveButton;
        Helper helper = new Helper();

        Stage stage = new Stage();
        stage.setTitle("Add Employee");
        VBox layout = new VBox(10);
        layout.setAlignment(Pos.CENTER);

        titleLabel = new Label("EmployerTrak®");
        titleLabel.setTextFill(Color.web(blueColor));
        titleLabel.setFont(new Font(38));
        titleLabel.setPadding(new Insets(10));

        HBox buttons = new HBox(10);
        buttons.setPadding(new Insets(10, 20, 20, 20));
        cancelButton = new Button("Cancel");
        saveButton = new Button("Save");
        cancelButton.setId("addButtons");
        saveButton.setId("addButtons");
        buttons.getChildren().addAll(createSpacer(), cancelButton, createSpacer(), saveButton, createSpacer());

        VBox leftBox = new VBox(10);
        leftBox.setPadding(new Insets(20));
        leftBox.setId("leftBox");
        idLabel = new Label("Employee ID:");
        fnameLabel = new Label("Employee First:");
        lnameLabel = new Label("Employee Last:");
        siteLabel = new Label("Employee Site:");
        testLabel = new Label("Test Passed (0 or 1):");
        leftBox.getChildren().addAll(idLabel, fnameLabel, lnameLabel, siteLabel, testLabel);

        VBox rightBox = new VBox(10);
        rightBox.setId("rightBox");
        rightBox.setPadding(new Insets(20));
        idField = new TextField();
        idField.setPrefWidth(300);
        fnameField = new TextField();
        fnameField.setPrefWidth(300);
        lnameField = new TextField();
        lnameField.setPrefWidth(300);
        siteField = new TextField();
        siteField.setPrefWidth(300);
        testField = new TextField();
        testField.setPrefWidth(300);
        rightBox.getChildren().addAll(idField, fnameField, lnameField, siteField, testField);

        HBox middle = new HBox(10);
        middle.getChildren().addAll(leftBox, rightBox);

        layout.getChildren().addAll(createVerticalSpacer(), titleLabel, createVerticalSpacer(), middle, createVerticalSpacer(), buttons, createVerticalSpacer());

        cancelButton.setOnAction(actionEvent -> stage.close());

        saveButton.setOnAction(actionEvent -> {
            try {
                helper.addEntry(Integer.parseInt(idField.getText()), fnameField.getText(), lnameField.getText(), siteField.getText(), testField.getText());
                stage.close();
                tableView.getItems().clear();
                tableView.getItems().addAll(Helper.getAllRecords());
            } catch (Exception e) {
                Alert alert = new Alert(Alert.AlertType.ERROR, "Error not a valid entry please check all fields\nNote, Two employees may not have the same ID");
                alert.showAndWait();
                e.printStackTrace();
            }
        });

        Scene scene = new Scene(layout, 600, 400);
        scene.getStylesheets().add("sample/Button.css");
        BackgroundFill background_fill = new BackgroundFill(Color.web("#2b2d2f"),
                CornerRadii.EMPTY, Insets.EMPTY);
        Background background = new Background(background_fill);
        layout.setBackground(background);
        stage.setScene(scene);
        stage.show();
    }

    public void deleteScreen() {
        Label titleLabel;
        Label deleteLabel;
        Label idLabel;
        Label nameLabel;
        Label siteLabel;
        Label testLabel;
        Button cancelButton;
        Button removeButton;
        Helper helper = new Helper();

        Employee employee = tableView.getSelectionModel().getSelectedItem();

        int id = employee.getId();
        String fname = employee.getFname();
        String lname = employee.getLname();
        int site = employee.getSiteId();
        String test = employee.getTestPassed();

        Stage stage = new Stage();
        stage.setTitle("Delete Employee");
        VBox layout = new VBox(10);
        layout.setAlignment(Pos.CENTER);

        titleLabel = new Label("EmployerTrak®");
        titleLabel.setTextFill(Color.web(blueColor));
        titleLabel.setFont(new Font(38));
        titleLabel.setPadding(new Insets(10));

        HBox buttons = new HBox(10);
        buttons.setPadding(new Insets(10, 20, 20, 20));
        cancelButton = new Button("Cancel");
        removeButton = new Button("Delete");
        cancelButton.setId("addButtons");
        removeButton.setId("addButtons");
        buttons.getChildren().addAll(createSpacer(), cancelButton, createSpacer(), removeButton, createSpacer());

        VBox info = new VBox(10);
        info.setAlignment(Pos.CENTER);
        info.setId("info");
        deleteLabel = new Label("Would you like to delete this Employee?");
        nameLabel = new Label("Employee: " + fname + " " + lname);
        idLabel = new Label("Employee ID: " + id);
        siteLabel = new Label("Employee Site: " + site);
        testLabel = new Label("Safety Approved: " + test);
        info.getChildren().addAll(deleteLabel, nameLabel, idLabel, siteLabel, testLabel);

        layout.getChildren().addAll(titleLabel, createVerticalSpacer(), info, createVerticalSpacer(), buttons);

        cancelButton.setOnAction(actionEvent -> stage.close());

        removeButton.setOnAction(actionEvent -> {
            try {
                helper.deleteEntry(fname);
                stage.close();
                tableView.getItems().clear();
                tableView.getItems().addAll(Helper.getAllRecords());
            } catch (Exception e) {
                e.printStackTrace();
            }
        });

        Scene scene = new Scene(layout, 600, 400);
        scene.getStylesheets().add("sample/Button.css");
        BackgroundFill background_fill = new BackgroundFill(Color.web("#2b2d2f"),
                CornerRadii.EMPTY, Insets.EMPTY);
        Background background = new Background(background_fill);
        layout.setBackground(background);
        stage.setScene(scene);
        stage.show();
    }

    public void editScreen() {
        Label titleLabel;
        Label idLabel;
        Label fnameLabel;
        Label lnameLabel;
        Label siteLabel;
        Label testLabel;
        TextField idField;
        TextField fnameField;
        TextField lnameField;
        TextField siteField;
        TextField testField;
        Button cancelButton;
        Button saveButton;
        Helper helper = new Helper();

        Employee employee = tableView.getSelectionModel().getSelectedItem();

        int id = employee.getId();
        String fname = employee.getFname();
        String lname = employee.getLname();
        int site = employee.getSiteId();
        String test = employee.getTestPassed();

        Stage stage = new Stage();
        stage.setTitle("Edit Employee");
        VBox layout = new VBox(10);
        layout.setAlignment(Pos.CENTER);

        titleLabel = new Label("EmployerTrak®");
        titleLabel.setTextFill(Color.web(blueColor));
        titleLabel.setFont(new Font(38));
        titleLabel.setPadding(new Insets(10));

        HBox buttons = new HBox(10);
        buttons.setPadding(new Insets(5, 20, 20, 20));
        cancelButton = new Button("Cancel");
        saveButton = new Button("Save");
        cancelButton.setId("addButtons");
        saveButton.setId("addButtons");
        buttons.getChildren().addAll(createSpacer(), cancelButton, createSpacer(), saveButton, createSpacer());

        VBox leftBox = new VBox(10);
        leftBox.setPadding(new Insets(20));
        leftBox.setId("leftBox");
        idLabel = new Label("Employee ID:");
        fnameLabel = new Label("Employee First:");
        lnameLabel = new Label("Employee Last:");
        siteLabel = new Label("Employee Site:");
        testLabel = new Label("Test Passed (0 or 1):");
        leftBox.getChildren().addAll(idLabel, fnameLabel, lnameLabel, siteLabel, testLabel);

        VBox rightBox = new VBox(10);
        rightBox.setId("rightBox");
        rightBox.setPadding(new Insets(20));
        idField = new TextField(Integer.toString(id));
        idField.setDisable(true);
        idField.setPrefWidth(300);
        fnameField = new TextField(fname);
        fnameField.setPrefWidth(300);
        lnameField = new TextField(lname);
        lnameField.setPrefWidth(300);
        siteField = new TextField(Integer.toString(site));
        siteField.setPrefWidth(300);
        testField = new TextField(test);
        testField.setDisable(true);
        testField.setPrefWidth(300);
        rightBox.getChildren().addAll(idField, fnameField, lnameField, siteField, testField);

        HBox middle = new HBox();
        middle.getChildren().addAll(leftBox, rightBox);

        layout.getChildren().addAll(titleLabel, createVerticalSpacer(), middle, createVerticalSpacer(), buttons);

        cancelButton.setOnAction(actionEvent -> stage.close());

        saveButton.setOnAction(actionEvent -> {
            if (fnameField.getText().trim().isEmpty() || lnameField.getText().trim().isEmpty() || siteField.getText().trim().isEmpty()) {
                Alert alert = new Alert(Alert.AlertType.ERROR, "Please enter a value for all available fields");
                alert.showAndWait();
            } else {
            try {
                helper.setFirstName(employee.getId(), fnameField.getText());
                helper.setLastName(employee.getId(), lnameField.getText());
                helper.setSiteId(employee.getId(), siteField.getText());
                stage.close();
                tableView.getItems().clear();
                tableView.getItems().addAll(Helper.getAllRecords());
            } catch (Exception e) {
                Alert alert = new Alert(Alert.AlertType.ERROR, "Error not a valid entry please check all fields\nNote, Two employees may not have the same ID");
                alert.showAndWait();
                e.printStackTrace();
            }
        }});

        Scene scene = new Scene(layout, 600, 400);
        scene.getStylesheets().add("sample/Button.css");
        BackgroundFill background_fill = new BackgroundFill(Color.web("#2b2d2f"),
                CornerRadii.EMPTY, Insets.EMPTY);
        Background background = new Background(background_fill);
        layout.setBackground(background);
        stage.setScene(scene);
        stage.show();
    }

    public void testScreen() {
        Helper helper = new Helper();

        Stage stage = new Stage();
        stage.setTitle("Employee Safety Test");
        VBox layout = new VBox(10);
        layout.setAlignment(Pos.TOP_CENTER);

        Employee employee = tableView.getSelectionModel().getSelectedItem();

        titleLabel = new Label("EmployerTrack®");
        titleLabel.setTextFill(Color.web(blueColor));
        titleLabel.setFont(new Font(38));
        titleLabel.setPadding(new Insets(10));

        HBox top = new HBox(10);
        Label testScoreLabel = new Label("Your Score: " + employee.getTestScore());
        testScoreLabel.setPadding(new Insets(5));
        Label questionLabel = new Label("Safety Test");
        questionLabel.setPadding(new Insets(5));
        top.setId("info");
        top.getChildren().addAll(testScoreLabel, createSpacer(), questionLabel);

        VBox questionNumberContainer = new VBox(10);
        Label questionNumber = new Label("Question 1/3");
        questionNumber.setPadding(new Insets(10));
        Label testQuestion = new Label("When should your hard hat be worn?");
        testQuestion.setAlignment(Pos.TOP_CENTER);
        questionNumberContainer.getChildren().addAll(questionNumber);

        questionNumberContainer.setId("info");
        testQuestion.setId("testQ");

        HBox answersContainer = new HBox(10);
        Button a = new Button("Always");
        Button b = new Button("Never");
        a.setId("menuButtons");
        b.setId("menuButtons");
        answersContainer.setAlignment(Pos.TOP_CENTER);
        answersContainer.getChildren().addAll(a, b);

        Button finishButton = new Button("Finish");
        finishButton.setId("menuButtons");
        finishButton.setAlignment(Pos.CENTER);


        a.setOnAction(actionEvent -> {
            employee.setTestScore(employee.getTestScore() + 1);
            testScoreLabel.setText("Your Score: " + employee.getTestScore());
            stage.close();
            test1Screen();
        });

        b.setOnAction(actionEvent -> {
            System.out.println("B clicked");
            stage.close();
            test1Screen();
        });

        finishButton.setOnAction(actionEvent -> {
            if (employee.getTestScore() >= 5) {
                try {
                    helper.setTestPassed(employee.getId(), 1);
                    employee.setTestScore(employee.getTestScore());
                    tableView.getItems().clear();
                    tableView.getItems().addAll(Helper.getAllRecords());
                    stage.close();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            } else {
                try {
                    helper.setTestPassed(employee.getId(), 0);
                    tableView.getItems().clear();
                    tableView.getItems().addAll(Helper.getAllRecords());
                    stage.close();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });

        layout.getChildren().addAll(top, questionNumberContainer, createSpacer(), testQuestion, createSpacer(), createVerticalSpacer(), answersContainer, createSpacer(), createVerticalSpacer(),
                finishButton, createVerticalSpacer());
        Scene scene = new Scene(layout, 600, 400);
        scene.getStylesheets().add("sample/Button.css");
        BackgroundFill background_fill = new BackgroundFill(Color.web("#2b2d2f"),
                CornerRadii.EMPTY, Insets.EMPTY);
        Background background = new Background(background_fill);
        layout.setBackground(background);
        stage.setScene(scene);
        stage.show();
    }

    public void test1Screen() {
        Helper helper = new Helper();

        Stage stage = new Stage();
        stage.setTitle("Employee Safety Test");
        VBox layout = new VBox(10);
        layout.setAlignment(Pos.TOP_CENTER);

        Employee employee = tableView.getSelectionModel().getSelectedItem();

        titleLabel = new Label("EmployerTrack®");
        titleLabel.setTextFill(Color.web(blueColor));
        titleLabel.setFont(new Font(38));
        titleLabel.setPadding(new Insets(10));

        HBox top = new HBox(10);
        Label testScoreLabel = new Label("Your Score: " + employee.getTestScore());
        testScoreLabel.setPadding(new Insets(5));
        Label questionLabel = new Label("Safety Test");
        questionLabel.setPadding(new Insets(5));
        top.setId("info");
        top.getChildren().addAll(testScoreLabel, createSpacer(), questionLabel);

        VBox questionNumberContainer = new VBox(10);
        Label questionNumber = new Label("Question 2/3");
        questionNumber.setPadding(new Insets(10));
        Label testQuestion = new Label("When is it okay to horseplay?");
        testQuestion.setAlignment(Pos.TOP_CENTER);
        questionNumberContainer.getChildren().addAll(questionNumber);

        questionNumberContainer.setId("info");
        testQuestion.setId("testQ");

        HBox answersContainer = new HBox(10);
        Button a = new Button("Always");
        Button b = new Button("Never");
        a.setId("menuButtons");
        b.setId("menuButtons");
        answersContainer.setAlignment(Pos.TOP_CENTER);
        answersContainer.getChildren().addAll(a, b);

        Button finishButton = new Button("Finish");
        finishButton.setId("menuButtons");
        finishButton.setAlignment(Pos.CENTER);


        a.setOnAction(actionEvent -> {
            stage.close();
            test2Screen();
        });

        b.setOnAction(actionEvent -> {
            employee.setTestScore(employee.getTestScore() + 1);
            testScoreLabel.setText("Your Score: " + employee.getTestScore());
            stage.close();
            test2Screen();
        });

        finishButton.setOnAction(actionEvent -> {
            if (employee.getTestScore() >= 3) {
                try {
                    helper.setTestPassed(employee.getId(), 1);
                    employee.setTestScore(employee.getTestScore());
                    tableView.getItems().clear();
                    tableView.getItems().addAll(Helper.getAllRecords());
                    stage.close();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            } else {
                try {
                    helper.setTestPassed(employee.getId(), 0);
                    tableView.getItems().clear();
                    tableView.getItems().addAll(Helper.getAllRecords());
                    stage.close();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });



        layout.getChildren().addAll(top, questionNumberContainer, createSpacer(), testQuestion, createSpacer(), createVerticalSpacer(), answersContainer, createSpacer(), createVerticalSpacer(),
                finishButton, createVerticalSpacer());
        Scene scene = new Scene(layout, 600, 400);
        scene.getStylesheets().add("sample/Button.css");
        BackgroundFill background_fill = new BackgroundFill(Color.web("#2b2d2f"),
                CornerRadii.EMPTY, Insets.EMPTY);
        Background background = new Background(background_fill);
        layout.setBackground(background);
        stage.setScene(scene);
        stage.show();
    }

    public void test2Screen() {
        Helper helper = new Helper();

        Stage stage = new Stage();
        stage.setTitle("Employee Safety Test");
        VBox layout = new VBox(10);
        layout.setAlignment(Pos.TOP_CENTER);

        Employee employee = tableView.getSelectionModel().getSelectedItem();

        titleLabel = new Label("EmployerTrack®");
        titleLabel.setTextFill(Color.web(blueColor));
        titleLabel.setFont(new Font(38));
        titleLabel.setPadding(new Insets(10));

        HBox top = new HBox(10);
        Label testScoreLabel = new Label("Your Score: " + employee.getTestScore());
        testScoreLabel.setPadding(new Insets(5));
        Label questionLabel = new Label("Safety Test");
        questionLabel.setPadding(new Insets(5));
        top.setId("info");
        top.getChildren().addAll(testScoreLabel, createSpacer(), questionLabel);

        VBox questionNumberContainer = new VBox(10);
        Label questionNumber = new Label("Question 3/3");
        questionNumber.setPadding(new Insets(10));
        Label testQuestion = new Label("When should you be on time?");
        testQuestion.setAlignment(Pos.TOP_CENTER);
        questionNumberContainer.getChildren().addAll(questionNumber);

        questionNumberContainer.setId("info");
        testQuestion.setId("testQ");

        HBox answersContainer = new HBox(10);
        Button a = new Button("Always");
        Button b = new Button("Never");
        a.setId("menuButtons");
        b.setId("menuButtons");
        answersContainer.setAlignment(Pos.TOP_CENTER);
        answersContainer.getChildren().addAll(a, b);

        Button finishButton = new Button("Finish");
        finishButton.setId("menuButtons");
        finishButton.setAlignment(Pos.CENTER);


        a.setOnAction(actionEvent -> {
            employee.setTestScore(employee.getTestScore() + 1);
            testScoreLabel.setText("Your Score: " + employee.getTestScore());
            stage.close();
            if (employee.getTestScore() >= 3) {
                try {
                    helper.setTestPassed(employee.getId(), 1);
                    employee.setTestScore(employee.getTestScore());
                    tableView.getItems().clear();
                    tableView.getItems().addAll(Helper.getAllRecords());
                    stage.close();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            } else {
                try {
                    helper.setTestPassed(employee.getId(), 0);
                    tableView.getItems().clear();
                    tableView.getItems().addAll(Helper.getAllRecords());
                    stage.close();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });

        b.setOnAction(actionEvent -> {
            stage.close();
            if (employee.getTestScore() >= 3) {
                try {
                    helper.setTestPassed(employee.getId(), 1);
                    employee.setTestScore(employee.getTestScore());
                    tableView.getItems().clear();
                    tableView.getItems().addAll(Helper.getAllRecords());
                    stage.close();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            } else {
                try {
                    helper.setTestPassed(employee.getId(), 0);
                    tableView.getItems().clear();
                    tableView.getItems().addAll(Helper.getAllRecords());
                    stage.close();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });

        finishButton.setOnAction(actionEvent -> {
            if (employee.getTestScore() >= 3) {
                try {
                    helper.setTestPassed(employee.getId(), 1);
                    employee.setTestScore(employee.getTestScore());
                    tableView.getItems().clear();
                    tableView.getItems().addAll(Helper.getAllRecords());
                    stage.close();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            } else {
                try {
                    helper.setTestPassed(employee.getId(), 0);
                    tableView.getItems().clear();
                    tableView.getItems().addAll(Helper.getAllRecords());
                    stage.close();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });



        layout.getChildren().addAll(top, questionNumberContainer, createSpacer(), testQuestion, createSpacer(), createVerticalSpacer(), answersContainer, createSpacer(), createVerticalSpacer(),
                finishButton, createVerticalSpacer());
        Scene scene = new Scene(layout, 600, 400);
        scene.getStylesheets().add("sample/Button.css");
        BackgroundFill background_fill = new BackgroundFill(Color.web("#2b2d2f"),
                CornerRadii.EMPTY, Insets.EMPTY);
        Background background = new Background(background_fill);
        layout.setBackground(background);
        stage.setScene(scene);
        stage.show();
    }

    private Node createSpacer() {
        final Region spacer = new Region();
        HBox.setHgrow(spacer, Priority.ALWAYS);
        return spacer;
    }

    private Node createVerticalSpacer() {
        final Region spacer = new Region();
        VBox.setVgrow(spacer, Priority.ALWAYS);
        return spacer;
    }

    public static void main(String[] args) {
        launch(args);
    }
}