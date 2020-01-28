package part3;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextArea;
import java.util.Hashtable;

/**
 * Controller class of gui
 */
public class Controller {

    /**
     * Threads number
     */
    private int threads;
    /**
     * Matrix length
     */
    private int length;
    /**
     * Solution Method
     */
    private int solMethod;
    /**
     * Thread for textArea
     */
    private Thread one;
    /**
     * Value of matrix using by one thread
     */
    private static final Hashtable<Integer,String > results = new Hashtable<Integer,String>()
    {{  put(4,      "0");
        put(8,      "3");
        put(16,     "42");
        put(32,     "121");
        put(64,     "2006");
        put(128,    "31713");
        put(256,    "520069");}};
    /**
     * New Model
     */
    private Model model = new Model();

    /**
     * Combobox matrixLength
     */
    @FXML
    private ComboBox matrixLength;

    /**
     * Combobox threadSize
     */
    @FXML
    private ComboBox threadSize;

    /**
     * ComboBox method
     */
    @FXML
    private ComboBox mtd;

    /**
     * TextArea matrix textArea
     */
    @FXML
    public TextArea textArea;

    /**
     * TextArea timeText
     */
    @FXML
    public TextArea timeText;

    /**
     * TextArea  time matrix using by one thread
     */
    @FXML
    public TextArea beforeText;

    /**
     * start button onClick action
     * İnitialize texts
     * and all area are filled calls dft method
     * and used a method to change text
     * @throws InterruptedException when error occurs state wait
     */
    @FXML
    void startMethod() throws InterruptedException {
        textArea.setEditable(false);
        timeText.setEditable(false);
        beforeText.setEditable(false);

        Alert alert = new Alert(Alert.AlertType.WARNING);
        if(model.one!=null && model.one.isAlive()){
            alert.setContentText("Program still running");
            alert.showAndWait();
        }
        else if(threadSize.getSelectionModel().getSelectedItem()!=null &&
                matrixLength.getSelectionModel().getSelectedItem()!=null &&  mtd.getSelectionModel().getSelectedItem()!=null &&
                model.one==null  ||(model.one!=null && !model.one.isAlive())){
            timeText.setText("Running...");
            beforeText.setText("Running...");
            textArea.setText("Running...");
            threads     = Integer.parseInt(threadSize.getSelectionModel().getSelectedItem().toString());
            length      = Integer.parseInt(matrixLength.getSelectionModel().getSelectedItem().toString());
            solMethod   = mtd.getSelectionModel().getSelectedIndex();
            System.out.println("Selected index"+solMethod);
            model.performDFT(length,threads,(solMethod+1));
            setMatrixText();
        }
        else{
            alert.setContentText("All Areas must be filled");
            alert.showAndWait();
        }
    }

    /**
     * stop button action method
     * first suspend all threads and stops.
     * stop the dft's running thread
     */
    @FXML
    void stopMethod(){
        for(int i=0;i<threads;i++){
            model.dftSimulator.threads[i].suspend();
            model.dftSimulator.threads[i].stop();
        }
        model.one.stop();
        one.stop();
    }

    /**
     * When dft calculate a new thread created and textAres changes.
     * to remain responsive during the calculation
     */
    @FXML
    void setMatrixText(){
        one = new Thread() {
            public void run() {
                try {
                    //waits dft calculations ended
                    while(model.one.isAlive());
                    textArea.setText(model.dftSimulator.m3.toString());
                    beforeText.setText("For 1 Threads:  "+results.get(length)+" ms");
                    timeText.setText("Time is: "+model.dftSimulator.duration/1000000+" ms");
                } catch(Exception v) {
                    System.out.println(v);
                }
            }
        };
        one.start();
    }

    /**
     * Gets value from combobox
     */
    public void comboAction() {
        matrixLength.valueProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) { }
        });

        ObservableList<String> allOptions = FXCollections.observableArrayList();
        for(int i=1;i<Integer.parseInt(matrixLength.getSelectionModel().getSelectedItem().toString());){
            allOptions.add(String.valueOf(i));
            i*=2;
            if(i==16)
                break;
        }
        threadSize.setItems(allOptions);
    }
}
