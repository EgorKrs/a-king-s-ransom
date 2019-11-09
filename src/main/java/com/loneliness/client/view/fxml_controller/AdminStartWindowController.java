package com.loneliness.client.view.fxml_controller;

import com.loneliness.client.PathManager;
import com.loneliness.client.controller.CommandProvider;
import com.loneliness.client.controller.ControllerException;
import com.loneliness.client.view.PrimaryStage;
import com.loneliness.client.view.ViewException;
import com.loneliness.entity.OrderData;
import com.loneliness.entity.ProviderData;
import com.loneliness.entity.transmission.Transmission;
import com.loneliness.entity.user.UserData;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.io.IOException;
import java.time.LocalDate;
import java.util.concurrent.ConcurrentHashMap;


public class AdminStartWindowController implements CRUD_Controller {
    @FXML
    private Stage dialogStage;
    private int[] indexOfCurrentValue = {0, 20};
    private String dataType;


    @FXML private Label providerEmailLabel;
    @FXML private TableView<UserData> usersTable;
    @FXML private TableColumn<UserData, String> usersLoginColumn;
    @FXML private TableColumn<UserData, String> usersTypeColumn;
    @FXML private Text userLogin;
    @FXML private Text userPassword;
    @FXML private Text userQuestion;
    @FXML private Text userAnswer;
    @FXML private Text type;
    private ObservableList<UserData> usersData = FXCollections.observableArrayList();



    @FXML private TableView<ProviderData> providerTable;
    @FXML private TableColumn<ProviderData, String> providerLocationColumn;
    @FXML private TableColumn<ProviderData, Integer> providerRatingColumn;
    private ObservableList<ProviderData> providersData = FXCollections.observableArrayList();
    @FXML private Text providerName;
    @FXML private Text providerLocation;
    @FXML private Text providerRating;


    @FXML private Text orderCustomerId;
    @FXML private Text orderName;
    @FXML private Text orderDateOfReceiving;
    @FXML private Text orderDateOfCompletion;
    @FXML private Text orderStatus;
    @FXML private Text orderPayment;
    @FXML private TableView<OrderData> orderTable;
    @FXML private TableColumn<OrderData, String> orderDateOfCompletionColumn;
    @FXML private TableColumn<OrderData, String> orderStatusColumn;
    private ObservableList<OrderData> ordersData = FXCollections.observableArrayList();

    @FXML
    private void setDataTypeUsers() {
        if (dataType != null) {
            if (!dataType.equals("users")) {
                dataType = "users";
                indexOfCurrentValue[0] = 0;
                indexOfCurrentValue[1] = 20;
                initialize();
            }
        }
    }

    @FXML
    private void setDataTypeProviders() {
        if(dataType!=null) {
            if (!dataType.equals("providers")) {
                dataType = "providers";
                indexOfCurrentValue[0] = 0;
                indexOfCurrentValue[1] = 20;
                initialize();
            }
        }
    }

    @FXML
    private void setDataTypeProductInStock() {
        if(dataType!=null) {
            if (!dataType.equals("product_in_stock")) {
                dataType = "product_in_stock";
                indexOfCurrentValue[0] = 0;
                indexOfCurrentValue[1] = 20;
                initialize();
            }
        }
    }

    @FXML
    private void setDataTypeOrders() {
        if(dataType!=null) {
            if (!dataType.equals("orders")) {
                dataType = "orders";
                indexOfCurrentValue[0] = 0;
                indexOfCurrentValue[1] = 20;
                initialize();
            }
        }
    }

    @FXML
    private void addTwentyNode() {
        indexOfCurrentValue[0] += 20;
        indexOfCurrentValue[1] += 20;
        update();
    }

    @FXML
    private void removeTwentyNode() {
        indexOfCurrentValue[0] -= 20;
        indexOfCurrentValue[1] -= 20;
        if (indexOfCurrentValue[0] < 0) {
            indexOfCurrentValue[0] = 0;
            indexOfCurrentValue[1] = 20;
        }
        update();
    }

    public ObservableList<UserData> setAndGetUsersData(ConcurrentHashMap<Integer, UserData> map) {
        usersData.addAll(map.values());
        return usersData;
    }
    public ObservableList<ProviderData> setAndGetProvidersData(ConcurrentHashMap<Integer, ProviderData> map) {
        providersData.addAll(map.values());
        return providersData;
    }

    public ObservableList<OrderData> setAndGetOrdersData(ConcurrentHashMap<Integer, OrderData> map) {
        ordersData.addAll(map.values());
        return ordersData;
    }

    private void fillText(UserData userData) {
        if (userData == null) {
            userLogin.setText("");
            userPassword.setText("");
            userQuestion.setText("");
            userAnswer.setText("");
            type.setText("");
        } else {
            userLogin.setText(userData.getLogin());
            userPassword.setText(userData.getPassword());
            userQuestion.setText(userData.getSecretQuestion());
            userAnswer.setText(userData.getSecretAnswer());
            type.setText(userData.getType().toString());
        }
    }
        private void fillText(ProviderData providerData) {
            if (providerData == null) {
                providerName.setText("");
                providerLocation.setText("");
                providerRating.setText("");
                providerEmailLabel.setText("");
            } else {
                providerName.setText(providerData.getName());
                providerLocation.setText(providerData.getLocation());
                providerRating.setText(String.valueOf(providerData.getRating()));
                providerEmailLabel.setText(providerData.getEmail());
            }

        }
    private void fillText(OrderData orderData) {
        if (orderData == null) {
            orderCustomerId.setText("");
            orderName.setText("");
            orderDateOfReceiving.setText("");
            orderDateOfCompletion.setText("");
            orderStatus.setText("");
            orderPayment.setText("");
        } else {
            orderCustomerId.setText(String.valueOf(orderData.getCustomerId()));
            orderName.setText(orderData.getOrderName());
            orderDateOfReceiving.setText(orderData.getDateOfReceiving().toString());
            orderDateOfCompletion.setText(orderData.getDateOfCompletion().toString());
            orderStatus.setText(orderData.getStatus().toString());
            orderPayment.setText(orderData.getPayment().toString());
        }
    }


    @Override
    public boolean update() {
        try {
            Transmission transmission = new Transmission();
            transmission.setFirstIndex(indexOfCurrentValue[0]);
            transmission.setLastIndex(indexOfCurrentValue[1]);
            if(dataType==null){
                dataType="users";
            }
            switch (dataType) {
                case "users":
                    usersData.clear();
                    transmission.setCommand("RECEIVE_ALL_USERS_IN_LIMIT");
                    setAndGetUsersData((ConcurrentHashMap<Integer, UserData>) CommandProvider.
                            getCommandProvider().getCommand("RECEIVE_ALL_USERS_IN_LIMIT")
                            .execute(transmission));
                    usersTable.refresh();
                    usersTable.setItems(usersData);
                    return true;
                case "providers":
                    providersData.clear();
                    transmission.setCommand("RECEIVE_ALL_PROVIDERS_IN_LIMIT");
                    setAndGetProvidersData((ConcurrentHashMap<Integer, ProviderData>)CommandProvider.
                            getCommandProvider().getCommand("RECEIVE_ALL_PROVIDERS_IN_LIMIT")
                            .execute(transmission));
                    providerTable.refresh();
                    providerTable.setItems(providersData);
                    return true;
                case "orders":
                    ordersData.clear();
                    transmission.setCommand("RECEIVE_ALL_ORDERS_IN_LIMIT");
                    setAndGetOrdersData((ConcurrentHashMap<Integer, OrderData>)CommandProvider.
                            getCommandProvider().getCommand("RECEIVE_ALL_ORDERS_IN_LIMIT")
                            .execute(transmission));
                    orderTable.refresh();
                    orderTable.setItems(ordersData);
                    return true;

            }

        } catch (ControllerException e) {
            WorkWithAlert.getInstance().showAlert("Ошибка обновленя",
                    "Неизвестная ошибка", "Попробуйте повторить действие позже",
                    this.dialogStage, "ERROR");
        }
        return false;
    }

    @FXML
    private void initialize() {
        if(dataType==null){
            dataType="users";
        }
        switch (dataType) {
            case "users":
                usersTable.getSelectionModel().selectedItemProperty().addListener((
                        (observableValue, userData, newUserData) -> fillText(newUserData)));

                usersLoginColumn.setCellValueFactory(userDataStringCellDataFeatures -> userDataStringCellDataFeatures.
                        getValue().loginPropertyProperty());
                usersTypeColumn.setCellValueFactory(userDataStringCellDataFeatures -> userDataStringCellDataFeatures.
                        getValue().typePropertyProperty());
                UserData userData = null;
                fillText(userData);
                break;
            case "providers":
                providerTable.getSelectionModel().selectedItemProperty().addListener((
                        (observableValue, providerData, newProviderData) -> fillText(newProviderData)));

                providerLocationColumn.setCellValueFactory(providerDataStringCellDataFeatures ->
                        providerDataStringCellDataFeatures.getValue().locationStringProperty());
                providerRatingColumn.setCellValueFactory(providerDataIntegerCellDataFeatures ->
                        providerDataIntegerCellDataFeatures.getValue().ratingIntegerProperty().asObject());
                ProviderData providerData = null;
                fillText(providerData);
                break;
            case "orders":
                orderTable.getSelectionModel().selectedItemProperty().addListener((
                        (observableValue, OrderData, newOrderData) -> fillText(newOrderData)));

                orderDateOfCompletionColumn.setCellValueFactory(providerDataStringCellDataFeatures ->
                        providerDataStringCellDataFeatures.getValue().dateOfCompletionStringProperty());
                orderStatusColumn.setCellValueFactory(providerDataIntegerCellDataFeatures ->
                        providerDataIntegerCellDataFeatures.getValue().statusStringProperty());
                OrderData orderData = null;
                fillText(orderData);
                break;
        }
        update();
    }

    @FXML
    private boolean searchHandler() {
        Stage dialogStage = null;
        try {
                    dialogStage = WorkWithFXMLLoader.getInstance().createStage(PathManager.getInstance().
                            getSearchUserData(), "Поиск данных");
                    SearchWindowController controller = WorkWithFXMLLoader.getInstance().getLoader().getController();
                    switch (dataType) {
                        case "users":
                            controller.setDialogStageUser(dialogStage, usersTable, usersData, dataType);
                            dialogStage.showAndWait();
                            return controller.isOkClicked();
                        case "providers":
                            controller.setDialogStageProviders(dialogStage, providerTable, providersData, dataType);
                            dialogStage.showAndWait();
                            return controller.isOkClicked();
                        case "orders":
                            controller.setDialogStageOrders(dialogStage, orderTable, ordersData, dataType);
                            dialogStage.showAndWait();
                            return controller.isOkClicked();

                    }
        } catch (ViewException e) {
            WorkWithAlert.getInstance().showAlert("Поиск данных",
                    "Поиск невозможен", "Что то пошло не так",
                    this.dialogStage, "ERROR");
        }
        return false;
    }

    @Override
    public boolean addHandler() {
        Stage dialogStage = null;
        try {
            switch (dataType) {
                case "users":
                    dialogStage = WorkWithFXMLLoader.getInstance().createStage(PathManager.getInstance().
                            getChangeUSerData(), "Добавления данных");
                    ChangeUserDataController changeUserDataController = WorkWithFXMLLoader.getInstance().getLoader().getController();
                    changeUserDataController.setData(new UserData());
                    changeUserDataController.setDialogStage(dialogStage, "create");
                    dialogStage.showAndWait();
                    return changeUserDataController.isOkClicked();
                case "providers":
                    dialogStage = WorkWithFXMLLoader.getInstance().createStage(PathManager.getInstance().
                            getChangeProviderData(), "Добавления данных");
                    ChangeProviderDataController changeProviderDataController = WorkWithFXMLLoader.getInstance().getLoader().getController();
                    changeProviderDataController.setData(new ProviderData());
                    changeProviderDataController.setDialogStage(dialogStage, "create");
                    dialogStage.showAndWait();
                    return changeProviderDataController.isOkClicked();
                case "orders":
                    dialogStage = WorkWithFXMLLoader.getInstance().createStage(PathManager.getInstance().
                            getChangeOrderData(), "Добавления данных");
                    ChangeOrderDataController changeOrderDataController = WorkWithFXMLLoader.getInstance().getLoader().getController();
                    changeOrderDataController.setData(new OrderData());
                    changeOrderDataController.setDialogStage(dialogStage, "create");
                    dialogStage.showAndWait();
                    return changeOrderDataController.isOkClicked();

            }
        } catch (ViewException e) {
            WorkWithAlert.getInstance().showAlert("Добавления данных",
                    "Поиск невозможен", "Что то пошло не так",
                    this.dialogStage, "ERROR");
        }
        return false;
    }

    @Override
    public boolean deleteHandler() {
        int selectedIndex;
        switch (dataType) {
            case "users":
                selectedIndex = usersTable.getSelectionModel().getSelectedIndex();
                if (selectedIndex >= 0) {
                    try {
                        UserData userData = new UserData();
                        userData.setId(usersData.get(selectedIndex).getId());
                        if ((Boolean) CommandProvider.getCommandProvider().getCommand("DELETE_USER").execute(userData)) {
                            usersTable.getItems().remove(selectedIndex);
                            WorkWithAlert.getInstance().showAlert("Удаление пользователя",
                                    "Успех", "Данные сохранены", dialogStage, "INFORMATION");
                        } else {
                            WorkWithAlert.getInstance().showAlert("Удаление пользователя",
                                    "Удаление невозможен", "Что то пошло не так",
                                    this.dialogStage, "ERROR");
                        }
                    } catch (ControllerException e) {
                        WorkWithAlert.getInstance().showAlert("Ошибка обновленя",
                                "Неизвестная ошибка", "Попробуйте повторить действие позже",
                                this.dialogStage, "ERROR");
                    }

                } else {
                    // Ничего не выбрано.
                    WorkWithAlert.getInstance().showAlert("Удаление пользователя",
                            "Удаление невозможно", "Выберите пользователя для удаления",
                            this.dialogStage, "ERROR");
                }
                break;
            case "providers":
                selectedIndex = providerTable.getSelectionModel().getSelectedIndex();
                if (selectedIndex >= 0) {
                    try {
                        ProviderData providerData=new ProviderData();
                        providerData.setId(providersData.get(selectedIndex).getId());
                        if ((Boolean) CommandProvider.getCommandProvider().getCommand("DELETE_PROVIDER").execute(providerData)) {
                           providerTable.getItems().remove(selectedIndex);
                            WorkWithAlert.getInstance().showAlert("Удаление поставщика",
                                    "Успех", "Данные сохранены", dialogStage, "INFORMATION");
                        } else {
                            WorkWithAlert.getInstance().showAlert("Удаление поставщика",
                                    "Удаление невозможен", "Что то пошло не так",
                                    this.dialogStage, "ERROR");
                        }
                    } catch (ControllerException e) {
                        WorkWithAlert.getInstance().showAlert("Ошибка обновленя",
                                "Неизвестная ошибка", "Попробуйте повторить действие позже",
                                this.dialogStage, "ERROR");
                    }

                } else {
                    // Ничего не выбрано.
                    WorkWithAlert.getInstance().showAlert("Удаление поставщика",
                            "Удаление невозможно", "Выберите пользователя для удаления",
                            this.dialogStage, "ERROR");
                }
                break;
            case "orders":
                selectedIndex = orderTable.getSelectionModel().getSelectedIndex();
                if (selectedIndex >= 0) {
                    try {
                        OrderData orderData=new OrderData();
                        orderData.setId(providersData.get(selectedIndex).getId());
                        if ((Boolean) CommandProvider.getCommandProvider().getCommand("DELETE_ORDER").execute(orderData)) {
                            orderTable.getItems().remove(selectedIndex);
                            WorkWithAlert.getInstance().showAlert("Удаление заказы",
                                    "Успех", "Данные сохранены", dialogStage, "INFORMATION");
                        } else {
                            WorkWithAlert.getInstance().showAlert("Удаление заказы",
                                    "Удаление невозможен", "Что то пошло не так",
                                    this.dialogStage, "ERROR");
                        }
                    } catch (ControllerException e) {
                        WorkWithAlert.getInstance().showAlert("Ошибка обновленя",
                                "Неизвестная ошибка", "Попробуйте повторить действие позже",
                                this.dialogStage, "ERROR");
                    }

                } else {
                    // Ничего не выбрано.
                    WorkWithAlert.getInstance().showAlert("Удаление поставщика",
                            "Удаление невозможно", "Выберите пользователя для удаления",
                            this.dialogStage, "ERROR");
                }
                break;
        }
        return false;
    }

    @Override
    public boolean changeHandler() {
        try {
            switch (dataType) {
                case "users":
                    UserData userData = getSelectedUserModel();
                    if (userData != null) {
                        Stage dialogStage = null;

                        dialogStage = WorkWithFXMLLoader.getInstance().createStage(PathManager.getInstance().
                                getChangeUSerData(), "Добавления пользователя");
                        ChangeUserDataController controller = WorkWithFXMLLoader.getInstance().getLoader().getController();
                        controller.setDialogStage(dialogStage, "update");
                        controller.setData(userData);
                        dialogStage.showAndWait();
                        return controller.isOkClicked();
                    }
                    return false;
                case "providers":
                    ProviderData providerData = getSelectedProvidersModel();
                    if (providerData != null) {
                        dialogStage = WorkWithFXMLLoader.getInstance().createStage(PathManager.getInstance().
                                getChangeProviderData(), "Добавления данных");
                        ChangeProviderDataController changeProviderDataController = WorkWithFXMLLoader.getInstance().getLoader().getController();
                        changeProviderDataController.setData(providerData);
                        changeProviderDataController.setDialogStage(dialogStage, "update");
                        dialogStage.showAndWait();
                        return changeProviderDataController.isOkClicked();
                    }
                    return false;
                case "orders":
                    OrderData orderData = getSelectedOrdersModel();
                    if (orderData != null) {
                        dialogStage = WorkWithFXMLLoader.getInstance().createStage(PathManager.getInstance().
                                getChangeOrderData(), "Добавления данных");
                        ChangeOrderDataController changeOrderDataController = WorkWithFXMLLoader.getInstance().getLoader().getController();
                        changeOrderDataController.setData(orderData);
                        changeOrderDataController.setDialogStage(dialogStage, "update");
                        dialogStage.showAndWait();
                        return changeOrderDataController.isOkClicked();
                    }
                    return false;
            }
        } catch (ViewException e) {
            WorkWithAlert.getInstance().showAlert("Неизвестная ошибка",
                    "Нарушение целостности программы", "Попробуйте повторить действие позже" +
                            " или принудительно закройте программу",
                    this.dialogStage, "ERROR");
        }
        return false;
    }

    @Override
    public boolean goBack() {
        try {
            PrimaryStage.getInstance().changeStage(FXMLLoader.load(getClass().getResource(PathManager.
                    getInstance().getAuthorisationFormController())));
        } catch (IOException e) {
            WorkWithAlert.getInstance().showAlert("Неизвестная ошибка",
                    "Нарушение целостности программы", "Попробуйте повторить действие позже" +
                            " или принудительно закройте программу",
                    this.dialogStage, "ERROR");
        }
        return false;
    }

    private UserData getSelectedUserModel() {
        UserData selectedUser=usersTable.getSelectionModel().getSelectedItem();
        if (selectedUser == null) {
            WorkWithAlert.getInstance().showAlert("Отсутствие выбора",
                    "Данные не выбраны", "Пожалуйста выберите данные в таблице.",
                    this.dialogStage, "ERROR");
            return null;
        } else {
            return selectedUser;
        }
    }

    private ProviderData getSelectedProvidersModel() {
        ProviderData selectedProvider=providerTable.getSelectionModel().getSelectedItem();
        if (selectedProvider == null) {
            WorkWithAlert.getInstance().showAlert("Отсутствие выбора",
                    "Данные не выбраны", "Пожалуйста выберите данные в таблице.",
                    this.dialogStage, "ERROR");
            return null;
        } else {
            return selectedProvider;
        }
    }

    private OrderData getSelectedOrdersModel() {
        OrderData selectedOrder=orderTable.getSelectionModel().getSelectedItem();
        if (selectedOrder == null) {
            WorkWithAlert.getInstance().showAlert("Отсутствие выбора",
                    "Данные не выбраны", "Пожалуйста выберите данные в таблице.",
                    this.dialogStage, "ERROR");
            return null;
        } else {
            return selectedOrder;
        }
    }
}
