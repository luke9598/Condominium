package logic.controller.guicontroller.second.resident;

import javafx.collections.ObservableList;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.chart.BarChart;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.PieChart;
import javafx.scene.chart.XYChart;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.stage.Screen;
import logic.controller.applicationcontroller.ApartmentController;
import logic.controller.applicationcontroller.FeeController;
import logic.model.Apartment;
import logic.model.Fee;
import logic.model.UserSingleton;

import java.net.URL;
import java.sql.SQLException;
import java.util.Arrays;
import java.util.List;
import java.util.ResourceBundle;

import static logic.controller.guicontroller.second.general.Main2GUI.secondBorder;

public class AptInfoGUI implements Initializable {

    private static final UserSingleton sg = UserSingleton.getInstance();
    private final ChartGUI chart = new ChartGUI();
    private final ApartmentController aptController = new ApartmentController();
    private final FeeController feeController = new FeeController();
    private final List<String> seriesName = Arrays.asList("Admin","Parking","Elevator","Pet","Wifi");
    private static final String CHOOSE_CHART = "Choose Chart";
    private static final String BAR_CHART = "Bar Chart";
    private static final String PIE_CHART = "Pie Chart";
    private static final String LINE_CHART = "Line Chart";

    @FXML VBox gridVbox;

    @FXML TabPane chartPane = new TabPane();

    @FXML GridPane feeGrid;
    @FXML GridPane pastGrid;

    @FXML CheckBox lastMonthFee;

    @FXML Label tfAdmin = new Label();
    @FXML Label tfPark = new Label();
    @FXML Label tfElevator = new Label();
    @FXML Label tfPet = new Label();
    @FXML Label tfWifi = new Label();

    @FXML Label tfPastAdmin = new Label();
    @FXML Label tfPastPark = new Label();
    @FXML Label tfPastElevator = new Label();
    @FXML Label tfPastPet = new Label();
    @FXML Label tfPastWifi = new Label();

    private final List<Label> tfName = Arrays.asList(tfAdmin,tfPark,tfElevator,tfPet,tfWifi);
    private final List<Label> tfPastName = Arrays.asList(tfPastAdmin,tfPastPark,tfPastElevator,tfPastPet,tfPastWifi);


    @Override
    public void initialize(URL location, ResourceBundle resources) {
        setUp();
    }

    private void noFee(Label t){
        t.setText("Unavailable in your Condominium");
    }

    public List<Double> getList(Fee fee){
        return Arrays.asList(fee.getAdmin(),fee.getPark(),fee.getElevator(),fee.getPet(),fee.getWifi());
    }

    public GridPane setUpGrid(GridPane grid, List<Label> tfList){

        for (int i = 0; i < seriesName.size(); i++){
            grid.addRow(i,new Label(seriesName.get(i)+" :"),tfList.get(i));
        }
        grid.setVgap(20);
        grid.setHgap(70);
        return grid;
    }

    public void submitLastMonthFee(){
        List<Double> chartDataList = getList(sg.getPastfee());
        pastGrid.setVisible(lastMonthFee.isSelected());
        switch (chartPane.getSelectionModel().getSelectedItem().getText()){
            default:
            case CHOOSE_CHART:
                secondBorder.setRight(null);
                break;
            case(BAR_CHART):
                BarChart<String,Number> oldBarChart = (BarChart) chartPane.getSelectionModel().getSelectedItem().getContent();
                if (lastMonthFee.isSelected()){
                    XYChart.Series<String,Number> series2 = chart.newSeries(chartDataList, seriesName, "Outgoings Last Month");
                    oldBarChart.getData().add(series2);
                } else if (!lastMonthFee.isSelected() && oldBarChart.getData().size() == 2) {
                        oldBarChart.getData().remove(1);
                }
                break;
            case(PIE_CHART):
                Pane oldPieChart = (Pane) chartPane.getSelectionModel().getSelectedItem().getContent();
                HBox hBox = (HBox) oldPieChart.getChildren().get(0);
                if (lastMonthFee.isSelected()){
                    ObservableList<PieChart.Data> valueList = chart.value(chartDataList,seriesName);
                    PieChart pc = chart.newPieChart(valueList,"Outgoing Last Month");
                    hBox.getChildren().add(pc);
                } else if (!lastMonthFee.isSelected() && hBox.getChildren().size() == 2) {
                        hBox.getChildren().remove(1);
                }
                break;
            case(LINE_CHART):
                LineChart<String,Number> oldLineChart = (LineChart) chartPane.getSelectionModel().getSelectedItem().getContent();
                if (lastMonthFee.isSelected()){
                    XYChart.Series<String,Number> series2 = chart.newSeries(chartDataList, seriesName, "Outgoings Last Month");
                    oldLineChart.getData().add(series2);
                } else if (!lastMonthFee.isSelected() && oldLineChart.getData().size() == 2) {
                        oldLineChart.getData().remove(1);
                }
                break;
        }
    }

    public EventHandler<Event> typeChart(){
        List<Double> chartDataList = getList(sg.getFee());
        Tab tabChart = chartPane.getSelectionModel().getSelectedItem();
        String chartTitle = "Outgoing Current Month";
        switch (chartPane.getSelectionModel().getSelectedItem().getText()){
            default:
            case CHOOSE_CHART:
                secondBorder.setRight(null);
                break;
            case(BAR_CHART):
                BarChart<String, Number> bc = chart.barChart("Fees","","Outgoings");
                XYChart.Series<String, Number> bcSeries = chart.newSeries(chartDataList,seriesName,chartTitle);
                bc.getData().add(bcSeries);
                tabChart.setContent(bc);
                break;
            case(PIE_CHART):
                ObservableList<PieChart.Data> valueList = chart.value(chartDataList,seriesName);
                PieChart pc = chart.newPieChart(valueList,chartTitle);
                HBox hBox = new HBox(pc);
                Pane paneC = new Pane(hBox);
                tabChart.setContent(paneC);
                break;
            case(LINE_CHART):
                LineChart<String ,Number> lc = chart.newLineChart("Fees","","Outgoings");
                XYChart.Series<String, Number> lcSeries = chart.newSeries(chartDataList,seriesName,chartTitle);
                lc.getData().add(lcSeries);
                tabChart.setContent(lc);
                break;
        }
        submitLastMonthFee();
        return null;
    }

    public void setUp(){

        try {
            feeGrid = setUpGrid(feeGrid,tfName);
            pastGrid = setUpGrid(pastGrid,tfPastName);
            pastGrid.setVisible(false);
            Fee boolFee = feeController.setUpFees(sg.getAddress());
            final Apartment apartment = aptController.checkApartments(sg.getUserID(),sg.getAddress(),"apt_res");
            Fee currentFee = feeController.loadFees(apartment.getNumber(),"fee");
            sg.setFee(currentFee);
            Fee pastFee = feeController.loadFees(apartment.getNumber(),"pastfee" );
            sg.setPastfee(pastFee);

            if (boolFee.getAvailablePark()) { tfPark.setText(currentFee.getPark() + " €"); tfPastPark.setText(pastFee.getPark() + " €");}
            else {noFee(tfPark); noFee(tfPastPark);}
            if (boolFee.getAvailableElevator()) { tfElevator.setText(currentFee.getElevator() + " €"); tfPastElevator.setText(pastFee.getElevator()+" €"); }
            else {noFee(tfElevator); noFee(tfPastElevator);}
            if (boolFee.getAvailablePet()) { tfPet.setText(currentFee.getPet() + " €"); tfPastPet.setText(pastFee.getPet()+" €"); }
            else {noFee(tfPet); noFee(tfPastPet);}
            if (boolFee.getAvailableWifi()) { tfWifi.setText(currentFee.getWifi()+" €"); tfPastWifi.setText(pastFee.getWifi()+" €"); }
            else {noFee(tfWifi); noFee(tfPastWifi);}
            tfAdmin.setText(currentFee.getAdmin()+" €");
            tfPastAdmin.setText(pastFee.getAdmin()+" €");


            Label tfOwner = new Label(apartment.getOwner());
            Label tfNumber = new Label(apartment.getNumber());
            HBox box = new HBox(tfOwner,tfNumber);
            box.setSpacing(120);
            gridVbox.getChildren().add(0,box);
            gridVbox.setSpacing(20);

        } catch (SQLException e) {
            e.printStackTrace();
        }


        chartPane.setPrefHeight(Screen.getPrimary().getBounds().getHeight()-30);
        chartPane.setPrefWidth(Screen.getPrimary().getBounds().getWidth()/2);
        Tab barChartTab = new Tab(BAR_CHART);
        barChartTab.setClosable(false);
        Tab pieChartTab = new Tab(PIE_CHART);
        pieChartTab.setClosable(false);
        Tab lineChartTab = new Tab(LINE_CHART);
        lineChartTab.setClosable(false);


        chartPane.getTabs().addAll(barChartTab,pieChartTab,lineChartTab);

        chartPane.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> typeChart());
        barChartTab.setOnSelectionChanged(typeChart());

        lastMonthFee.setOnAction(event -> submitLastMonthFee());
        secondBorder.setRight(chartPane);
    }
}