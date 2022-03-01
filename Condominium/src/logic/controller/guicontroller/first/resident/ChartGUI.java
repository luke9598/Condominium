package logic.controller.guicontroller.first.resident;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.chart.*;
import javafx.stage.Screen;

import java.util.List;

public class ChartGUI {
    public BarChart<String,Number> buildBarChart(String xAxis, String yAxis, String title){
        final CategoryAxis categoryAxis = new CategoryAxis();
        final NumberAxis numberAxis = new NumberAxis();
        final BarChart<String,Number> bc = new BarChart<>(categoryAxis,numberAxis);
        bc.setTitle(title);
        categoryAxis.setLabel(xAxis);
        numberAxis.setLabel(yAxis);
        bc.setPrefWidth((Screen.getPrimary().getBounds().getWidth()/2));
        return bc;
    }

    public PieChart buildPieChart(ObservableList<PieChart.Data> valueList, String title){
        PieChart pieChart = new PieChart(valueList);
        pieChart.setTitle(title);
        pieChart.setPrefHeight((Screen.getPrimary().getBounds().getHeight()-100)/2);
        pieChart.setPrefWidth(Screen.getPrimary().getBounds().getWidth()/3);
        return pieChart;
    }

    public LineChart<String, Number> buildLineChart(String xAxis, String yAxis, String title){
        final CategoryAxis categoryAxis = new CategoryAxis();
        final NumberAxis numberAxis = new NumberAxis();
        LineChart<String, Number> lineChart = new LineChart<>(categoryAxis,numberAxis);
        categoryAxis.setLabel(xAxis);
        numberAxis.setLabel(yAxis);
        lineChart.setTitle(title);
        lineChart.setPrefWidth((Screen.getPrimary().getBounds().getWidth()/2));
        return lineChart;
    }

    public XYChart.Series<String,Number> newSeries(List<Double> dataList, List<String> seriesName, String name){
        XYChart.Series<String, Number> series = new XYChart.Series<>();
        series.setName(name);
        for (int i = 0;i < dataList.size(); i++){
            series.getData().add(new XYChart.Data<>(seriesName.get(i),dataList.get(i)));
        }
        return series;
    }

    public ObservableList<PieChart.Data> value(List<Double> dataList,List<String> seriesName){
        ObservableList<PieChart.Data> data = FXCollections.observableArrayList();
        for (int i = 0; i < dataList.size(); i++){
            data.add(new PieChart.Data(seriesName.get(i),dataList.get(i)));
        }
        return data;
    }
}
