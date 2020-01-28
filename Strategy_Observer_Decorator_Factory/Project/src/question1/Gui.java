package question1;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.TextField;
import java.awt.event.ActionEvent;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;

/**
 * Kullanıcı ıcın Graphıcal User Interface olusturulmustur.
 * @author Akin Cam
 */
public class Gui extends JFrame{
    /**
     * Solver Methods sınıfından implement edılen sınıfların kullanacağı input
     * arayüzden alınan ınput buraya uyarlanmısıtır.
     */
    private double[][] linearSolverInput;
    /**
     * Kullanıcından arayüz tarafından alınan input
     */
    private TextField[][]       guiInput;
    /**
     * matris satır sayısı
     */
    private int                 row;
    /**
     * matris sutun sayısı
     */
    private int                 col;
    /**
     * Arayüz icin ana panel
     */
    private JPanel              mainPanel;
    /**
     * Matris boyutunu secmek ıcın Spinner
     */
    private JComboBox<Integer>  matrixSizeSpinner;
    /**
     * matris alınan panel
     */
    private JPanel              panel;
    /**
     * LinearSolverDeluxe değişkeni
     */
    private LinearSolverDeluxe  linearSolverDeluxe;

    /**
     * Ana ekranı olusturmak ıcın kullanılır.
     * Baslık set edılır.
     */
    public Gui(){
        super();
        this.setTitle("Linear Solver Deluxe");
        panel = new JPanel();
        InitializeGui();
    }

    /**
     * Ekran oranını hangı panellerın bulunacagını ekran resize durumu belirlenir.
     */
    private void InitializeGui() {
        mainPanel = new JPanel();
        mainPanel.setPreferredSize(new Dimension(800, 600));
        this.setResizable(false);
        this.add(mainPanel);
        mainPanel.setLayout(new FlowLayout());
        mainPanel.add(matrixPreferencesPanel());
        this.pack();
        this.setVisible(true);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

    }

    /**
     * Ana Frame uzerine yeni panel eklenir.
     * Bu panel uzerinde matris boyutunun secılmesı saglanır.
     * Metod secim ekranı, performSolver() methodunun cagrılacagı buton olusturulur.
     * Matrıs buyuklugu ıcın sadece bir sayı alınır. Cunku kare olmayan matrıslerın lineer cozumu yoktur.
     * @return bu olusturulan panel döndürülür
     */
    private JPanel matrixPreferencesPanel(){
        JPanel panel             = new JPanel(new FlowLayout());
        JComboBox solutionType     = new JComboBox<>();
        Types[] solutionTypes    = Types.values();
        Integer[] inputSpinner   = new Integer[9];
        JButton matrixSolverButton     = new JButton("Çöz");
        panel.setPreferredSize(new Dimension(800, 50));
        for(int i = 0; i < 9; i++) //matris buyuklugunun 2 ile 10 arasında olması ıcın.
            inputSpinner[i] = i+2;

        matrixSizeSpinner = new JComboBox<>(inputSpinner);
        JLabel label = new JLabel("Kare Matris Boyutu: ");
        label.setForeground(Color.black);
        for(int i = 0; i < Types.size; i++) {//Saglanan cozumlerın ısımlerı
            solutionType.addItem(solutionTypes[i]);
        }
        solutionType.addActionListener((ae) -> {//hangi cozum tipinin secildiği
            try {
                setType((Types) solutionType.getSelectedItem());
            } catch (Exception e) {
                e.printStackTrace();
            }
        });
        panel.add(solutionType);
        matrixSolverButton.setVisible(false);
        matrixSolverButton.addActionListener((ActionEvent ae) -> {//bu buton ıle verilen matris ilk olarak Ax = b seklinde 2 matrise ayrılır.
            try {
                setType((Types) solutionType.getSelectedItem());
                linearSolverInput = new double[row][col+1];
                for(int i = 0; i < row; i++){
                    for(int j = 0; j < row; j++) {
                        linearSolverInput[i][j] = Double.parseDouble(guiInput[i][j].getText());
                    }

                    linearSolverInput[i][row]=Double.parseDouble(guiInput[i][row+1].getText());
                }
                linearSolverDeluxe.performSolver(linearSolverInput);
            } catch (Exception e) {//ınput doğru değil se kullanıcı uyarılır(Rakam değil ise)
                warningAlert(e.getMessage());
            }
        });
        JButton generateMatrix = new JButton("Oluştur");

        generateMatrix.addActionListener((ActionEvent ae) -> {
            row = (int) matrixSizeSpinner.getSelectedItem();
            col = row;
            mainPanel.remove(this.panel);
            this.panel = matrixInputPanel();
            mainPanel.add(this.panel);
            setContentPane(mainPanel);
            matrixSolverButton.setVisible(true);
        });
        panel.add(label);
        panel.add(matrixSizeSpinner);
        panel.add(generateMatrix);
        panel.add(matrixSolverButton);
        return panel;
    }

    /**
     * Input panel olusturulur.
     * Normal frame in uzerine yeni bir frame olusturulur. Olusturulan panel uzerine verilen bilgilerle matrıs return edılır.
     * @return olusturulan panel return edilir.
     */
    private JPanel matrixInputPanel(){
        JPanel newPanel = new JPanel(new BorderLayout());
        JPanel panel    = new JPanel();
        newPanel.setPreferredSize(new Dimension(800, 600));
        panel.setLayout(null);
        panel.setPreferredSize(new Dimension(col * 50, row * 30));
        JScrollPane scroll = new JScrollPane(panel);
        scroll.setPreferredSize(new Dimension(800, 600));
        createMatrix(panel);
        newPanel.add(scroll, BorderLayout.EAST);
        return newPanel;
    }

    /**
     * Girdiler sonucunda alınan verilerle matrısı olusturan method
     * @param panel hangi panel uzerine olusturulacagı belırlenır.
     */
    private void createMatrix(JPanel panel){
        JLabel[][] matrixLabel  = new JLabel[row][col+2];
        guiInput                = new TextField[row][col+2];
        for(int i = 0; i < row; i++){//kare matrıs olusturulur.
            matrixLabel[i][0] = new JLabel();
            matrixLabel[i][0].setBounds(5, 32*i+30, 30, 24);//boyut belirlenir.
            panel.add(matrixLabel[i][0]);
            for(int j = 0; j < col; j++){
                guiInput[i][j] = new TextField();
                guiInput[i][j].setBounds(48*j+45, 32*i+30, 30, 24);
                panel.add(guiInput[i][j]);
            }
        }
        for(int i = 0; i < row; i++){//esittir "=" ısaretını eklemek ıcın kullanılır.
            for(int j = col; j < col+1; j++){
                guiInput[i][j] = new TextField("=");
                guiInput[i][j].setBounds(48*j+45, 32*i+30, 30, 24);
                guiInput[i][j].setEditable(false);
                guiInput[i][j].setBackground(Color.yellow);
                panel.add(guiInput[i][j]);
            }
            for(int k = col+1; k < col+2; k++){//Ax = b seklındekı yapının vector(b) bolumu eklenır.
                guiInput[i][k] = new TextField();
                guiInput[i][k].setBounds(48*k+45, 32*i+30, 30, 24);
                panel.add(guiInput[i][k]);
            }
        }
    }

    /**
     * Hangi cozum yontemıne gore secım yapılacapı belırlenır
     * @param type çözüm metodunu belirler.
     */
    private void setType(Types type){
        if(type == Types.GaussElimination) {
            linearSolverDeluxe = new LinearSolverDeluxe(new GaussianElimination());
        }
        else if(type == Types.MatrixInversion){
            linearSolverDeluxe = new LinearSolverDeluxe(new MatrixInversion());
        }
        else{

        }
    }

    /**
     *
     * Kullanıcı rakam dısında bir input girdiğinde kullanıcı uyarılır.
     * @param title Hata mesajı
     */
    public  void warningAlert(String title){
        JOptionPane.showMessageDialog(new JFrame(),
            "Girdiler Sayı Olmalıdır!!",
                title,
                1);
    }

    /**
     * GaussElimination ve MatrisInversion sonucunda toString metodunun return kullanıcıya arayuz ıle gosterılır.
     * @param title GaussElimination ve MatrisInversion toString return degerı
     */
    public static void printResult(String title){
        JOptionPane.showMessageDialog(new JFrame(),
                title,
                title,
                1);
    }
}
