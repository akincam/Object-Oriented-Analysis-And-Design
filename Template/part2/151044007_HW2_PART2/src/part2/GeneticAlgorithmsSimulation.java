package part2;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.concurrent.Task;
import javafx.scene.Scene;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;
import javafx.scene.control.Button;
import javafx.scene.control.ToolBar;
import javafx.scene.layout.FlowPane;
import javafx.stage.Stage;

import java.util.ArrayList;

/**
 * This class creates a java fx app
 * Creates three chart and show the max fittest value of given algorithms
 * @author Akin Cam
 */
public class GeneticAlgorithmsSimulation extends Application {
    /**
     * keeps the genetic algoritm threads
     */
    private ArrayList<Thread> algorithmThreads;
    /**
     * keeps the genetic gui threads
     */
    private ArrayList<Thread> guiThreads;
    /**
     * keeps charts
     */
    private ArrayList<LineChart<Number,Number>>             charts;
    /**
     * set the charts and provide update chart data
     */
    private ArrayList<XYChart.Series<Number, Number>>       series;
    /**
     * Genetic algorithm version 1,2,3
     */
    private int                                             versionNum = 0;
    /**
     * A container to keep charts
     */
    private FlowPane                                        root;
    /**
     * used to add buttons on window
     */
    private ToolBar                                         toolBar;
    /**
     * Algoritms start button
     */
    private Button                                          startButton;
    /**
     * Algoritms stop button
     */
    private Button                                          stopButton;
    /**
     * Algoritms pause button
     */
    private Button                                          pauseButton;
    /**
     * Keeps the genetic algoritms
     */
    private ArrayList<GeneticAlgorithm>                     geneticAlgorithms;
    /**
     * change the state pause - run
     */
    private Boolean                                         pauseBoo;
    /**
     * change the state stop - start
     */
    private Boolean                                         stopBoo;

    /**
     * Constructor
     */
    public GeneticAlgorithmsSimulation(){
        charts              = new ArrayList<>();
        series              = new ArrayList<>();
        root                = new FlowPane();
        toolBar             = new ToolBar();
        startButton         = new Button(Buttons.Start.toString());
        stopButton          = new Button(Buttons.Stop.toString());
        pauseButton         = new Button(Buttons.Pause.toString());
        geneticAlgorithms   = new ArrayList<>();
        algorithmThreads    = new ArrayList<>();
        guiThreads          = new ArrayList<>();
        pauseBoo            = false;
        stopBoo             = true;
        toolBar.getItems().addAll(startButton,stopButton,pauseButton);
    }

    /**
     * Main method to run application
     * @param args arguments
     */
    public static void main(String[] args) {
        launch(args);
    }

    /**
     * Starts the app and load the charts
     * @param st is a  top-level container
     */
    @Override
    public void start(Stage st) {
        geneticAlgorithms.add(new GeneticAlgorithmVersion1(30));
        geneticAlgorithms.add(new GeneticAlgorithmVersion2(30));
        geneticAlgorithms.add(new GeneticAlgorithmVersion3(30));

        st.setTitle("Genetic Algorithm Chart Animation");
        for(int i = 0;i<3;i++) {
            addChart(root);
        }

        root.getChildren().add(toolBar);
        Scene scene = new Scene(root, 1200, 800);
        st.setScene(scene);
        st.setResizable(false);
        st.show();


        setButtonBehaviour(false,true,true);


        /*if state pause resume
         * if state stop start again
         */
        startButton.setOnAction(e -> {
            int i = 0;
            setButtonBehaviour(true,false,false);
            startButton.setText(Buttons.Start.toString());
            if(pauseBoo){
                resumeThreadList(algorithmThreads);
                resumeThreadList(guiThreads);
                pauseBoo = false;
            }
            else{
                stopBoo = true;
                for(GeneticAlgorithm geneticAlgorithm : geneticAlgorithms){
                    startGeneticAlgorithm(geneticAlgorithm);
                    startGeneticAlgorithmGui(geneticAlgorithm,series.get(i++));
                }
            }

        });

        /*
         * stop button activities
         * stop threads
         * clear chartlists
         * clear threadlist
         */
        stopButton.setOnAction(e1->{
            setButtonBehaviour(false,true,true);
            stopBoo  = false;
            pauseBoo = false;
            stopThreadList(algorithmThreads);
            stopThreadList(guiThreads);
            clearCharts(series);
            algorithmThreads.clear();
            guiThreads.clear();

        });

        /*
          pause button
          set pauseBoo true to active runButton
         */
        pauseButton.setOnAction(e1->{
            setButtonBehaviour(false,true,false);
            startButton.setText(Buttons.Run.toString());
            pauseThreadList(algorithmThreads);
            pauseThreadList(guiThreads);
            pauseBoo = true;

        });

        /*
         * Kill threads when exit the app.
         */
        st.setOnCloseRequest(e -> {
            Platform.exit();
            System.exit(0);
        });
    }

    /**
     * Clear the all charts when stop button is clicked.
     * @param chartSeries chart arraylist
     */
    public void clearCharts(ArrayList<XYChart.Series<Number, Number>> chartSeries){
        for(XYChart.Series<Number, Number> sr : chartSeries) {
            sr.getData().clear();
            sr.setName("Max Fitness");
        }
    }
    /**
     * Stop all threads when stop button clicked
     * @param th thread list
     */
    public void stopThreadList(ArrayList<Thread> th){
        for(Thread t : th)
            t.stop();
    }

    /**
     * Pause all threads when pause button clicked
     * @param th thread list
     */
    public void pauseThreadList(ArrayList<Thread> th){
        for(Thread t : th)
            t.suspend();
    }
    /**
     * Resume all threads when run button clicked
     * @param th thread list
     */
    public void resumeThreadList(ArrayList<Thread> th){
        for(Thread t : th)
            t.resume();
    }

    /**
     * Override stop method
     * @throws Exception throw exception when not stop
     */
    @Override
    public void stop() throws Exception {
        super.stop();
    }

    /**
     * this method creates a thread to run a version of genetic algorithm
     * Add this thread to thread list.
     * @param geneticAlgorithm is a verison of genetic algorithm.
     */
    public void startGeneticAlgorithm(GeneticAlgorithm geneticAlgorithm){
        Thread t =new Thread(() -> {
            try {
                geneticAlgorithm.geneticAlgorithmSolver();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });
        t.setDaemon(true);
        algorithmThreads.add(t);
        t.start();
    }

    /**
     * This method creates a thread for every chart and sets the data come from a genetic algoritm.
     * Platform.runlater() is used to update gui with simple gui tasks.
     * Add this thread to thread list.
     * @param geneticAlgorithm geneticalgorithm type
     * @param series keeps the chart which is updates.
     */
    public void startGeneticAlgorithmGui(GeneticAlgorithm geneticAlgorithm, XYChart.Series<Number, Number> series){
        Task task = new Task<Void>() {
            @Override
            public Void call() throws Exception {
                while (stopBoo) {
                    Platform.runLater(() -> {
                        series.getData().add(new XYChart.Data<>(geneticAlgorithm.iteration, geneticAlgorithm.fittest));
                        series.setName(String.valueOf(geneticAlgorithm.fittest));
                    });
                    Thread.sleep(800);
                }
                return null;
            }
        };
        Thread th = new Thread(task);
        th.setDaemon(true);
        th.start();
    }

    /**
     * Creates new chart, sets the label names and title
     * @param label is the version of the genetic algorithm
     * @return a linechart for spesific version of algorithm
     */
    private LineChart<Number, Number> createNewChart(String label) {
        final NumberAxis xAxis = new NumberAxis();
        final NumberAxis yAxis = new NumberAxis();
        xAxis.setLabel("Iteration");
        yAxis.setLabel("Fittest Value");
        final LineChart<Number, Number> lineChart = new LineChart<>(xAxis, yAxis);
        lineChart.setTitle(label);
        lineChart.setAnimated(false);
        return lineChart;
    }

    /**
     * This method add chart to series to show screen
     * @param root is a pane which is flow pane
     */
    private void addChart(FlowPane root){
        charts.add(createNewChart("Version "+ ++versionNum));
        series.add(new XYChart.Series<>());
        series.get(series.size()-1).setName("Max fitness");
        charts.get(charts.size()-1).getData().add(series.get(series.size()-1));
        charts.get(charts.size()-1).setCreateSymbols(false);
        series.get(series.size()-1).getNode().setStyle("-fx-stroke: blue;");
        root.getChildren().add(charts.get(charts.size()-1));
    }

    /**
     * This method determines which button is enable or disable
     * @param startB start/run button
     * @param pauseB pause the algorithms button
     * @param stopB stops the all algoritms
     */
    private void setButtonBehaviour(boolean startB,boolean pauseB,boolean stopB){
        startButton.setDisable(startB);
        stopButton.setDisable(stopB);
        pauseButton.setDisable(pauseB);
    }

}