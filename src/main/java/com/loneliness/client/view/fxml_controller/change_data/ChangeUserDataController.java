package com.loneliness.client.view.fxml_controller.change_data;

import com.loneliness.client.controller.CommandProvider;
import com.loneliness.client.controller.ControllerException;
import com.loneliness.client.view.fxml_controller.Handler;
import com.loneliness.client.view.fxml_controller.WorkWithAlert;
import com.loneliness.entity.user.UserData;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.stage.Stage;

public class ChangeUserDataController implements Handler {
    @FXML
    private TextField loginField;
    @FXML
    private TextField passwordField;
    @FXML
    private TextField questionField;
    @FXML
    private TextField answerField;
    @FXML
    private Stage dialogStage;
    @FXML
    private SplitMenuButton typeButton;
    @FXML
    private RadioMenuItem admin;
    @FXML
    private RadioMenuItem client;
    @FXML
    private RadioMenuItem manager;
    @FXML
    private RadioMenuItem noType;
    @FXML
    private ToggleGroup typeGroup = new ToggleGroup();

    private String type;

    private UserData userData;

    private boolean okClicked = false;

    private String action;

    public boolean isOkClicked() {
        return okClicked;
    }

    public void setDialogStage(Stage dialogStage, String action) {
        this.dialogStage = dialogStage;
        this.action = action;
        admin.setToggleGroup(typeGroup);
        client.setToggleGroup(typeGroup);
        manager.setToggleGroup(typeGroup);
        noType.setToggleGroup(typeGroup);
    }

    private String getType() {
        if (admin.isSelected()) {
            return "ADMIN";
        } else if (client.isSelected()) {
            return "CLIENT";
        } else if (manager.isSelected()) {
            return "MANAGER";
        } else if (noType.isSelected()) {
            return "NO_TYPE";
        } else return "";
    }

    @Override
    public boolean isInputValid() {
        String errorMessage = "";

        if (loginField.getText() == null || loginField.getText().length() == 0) {
            errorMessage += "Не допустимый логин!\n";
        } else if (answerField.getText() == null || answerField.getText().length() == 0) {
            errorMessage += "Не допустимый ответ на вопрос!\n";
        } else if (passwordField.getText() == null || passwordField.getText().length() == 0) {
            errorMessage += "Не допустимый пароль!\n";
        } else if (questionField.getText() == null || questionField.getText().length() == 0) {
            errorMessage += "Не допустимый ответ!\n";
        } else if (type == null || type.length() == 0) {
            errorMessage += "Не допустимые права доступа!\n";
        }
        if (errorMessage.length() == 0) {
            return true;
        } else { // Показываем сообщение об ошибке.
            WorkWithAlert.getInstance().showAlert("Неверный ввод",
                    "Исправьте недопустимые поля", errorMessage, dialogStage, "ERROR");
            return false;
        }
    }

    @Override
    public void goBack() {
        okClicked = true;
        dialogStage.close();
    }

    @Override
    public void finishWork() {
        try {
            if (isInputValid()) {
                userData.setLogin(loginField.getText());
                userData.setPassword(passwordField.getText());
                userData.setType(getType());
                userData.setSecretQuestion(questionField.getText());
                userData.setSecretAnswer(answerField.getText());
                String answer = "";
                if (action.equals("update")) {
                    answer = (String) CommandProvider.getCommandProvider().getCommand("UPDATE_USER").execute(userData);
                } else if (action.equals("create")) {
                    answer = (String) CommandProvider.getCommandProvider().getCommand("CREATE_USER").execute(userData);
                }
                if (WorkWithAlert.getInstance().showAnswer(answer, dialogStage,"Обновления данных")) {
                    goBack();
                }
            }

        } catch (ControllerException e) {
            WorkWithAlert.getInstance().showAlert("Ошибка обновления данных",
                    "Неизвестная ошибка", e.getExceptionMessage().toString(), dialogStage, "ERROR");
        }
    }

    public void setData(UserData userData) {
        this.userData=userData;
        loginField.setText(userData.getLogin());
        passwordField.setText(userData.getPassword());
        questionField.setText(userData.getSecretQuestion());
        answerField.setText(userData.getSecretAnswer());
        if(userData.getType()!=null) {
            type=userData.getType().toString();
            setSelectedType(userData.getType().toString());
        }
        else type="";
    }

    @FXML
    private void setType() {
        if (admin.isSelected()) {
            type = "ADMIN";
        } else if (client.isSelected()) {
            type = "CLIENT";
        } else if (manager.isSelected()) {
            type = "MANAGER";
        } else if (noType.isSelected()) {
            type = "NO_TYPE";
        }
    }
    private void setSelectedType(String type){
        switch (type){
            case "ADMIN":
                admin.setSelected(true);
                break;
            case "CLIENT":
                client.setSelected(true);
                break;
            case "MANAGER":
                manager.setSelected(true);
                break;
            case "NO_TYPE":
                noType.setSelected(true);
                break;
        }
    }
}

