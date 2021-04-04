package eightpuzzle;

import Algoritmos.HillClimb;
import Funcoes.Transformacoes;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXRadioButton;
import java.io.File;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.ToggleGroup;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import javafx.stage.FileChooser;

public class TelaPrincipalController implements Initializable {
    
    private long start,finish;
    private int lista[];
    private ArrayList imagemLista;
    private int passosLista[][];
    private int passosPos,passosPosMax;
    static public File arq = null;
    private File arquivo;
    
    @FXML
    private GridPane gpimage;
    @FXML
    private ImageView im00;
    @FXML
    private ImageView im01;
    @FXML
    private ImageView im02;
    @FXML
    private ImageView im10;
    @FXML
    private ImageView im11;
    @FXML
    private ImageView im12;
    @FXML
    private ImageView im20;
    @FXML
    private ImageView im21;
    @FXML
    private ImageView im22;
    @FXML
    private JFXRadioButton rbAlg1;
    @FXML
    private JFXRadioButton rbAlg2;
    @FXML
    private JFXButton btEmbaralhar;
    @FXML
    private JFXButton evtResolver;
    @FXML
    private ToggleGroup group;
    @FXML
    private Label labelTempo;
    @FXML
    private Label labelpasso;
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
        reiniciaTela();
    }
    
    @FXML
    private void evtAbrir(ActionEvent event) {
        
        FileChooser fc=new FileChooser();
        ImageView imageview = new ImageView();
        arq = fc.showOpenDialog(null);
        Image img;
        if(arq != null)
        {
            lista = new int[]{9,1,2,3,4,5,6,7,8,9};
            imagemLista = new ArrayList();
            arquivo = arq;
            img = new Image(arq.toURI().toString());
            System.out.println(arq.toURI().toString());
            imagemLista = Transformacoes.Recortar(img);
            atualizaTela(lista);
            labelTempo.setText("");
        }
    }
    
    @FXML
    private void evtEmbaralhar(ActionEvent event) {
        
        if(imagemLista != null && !imagemLista.isEmpty())
        {
            labelTempo.setText("");
            labelpasso.setText("");
            lista = Funcoes.Transformacoes.Embaralhar(7);
            passosLista = new int[10][];

            atualizaTela(lista);
        }
    }
    
    private void atualizaTela(int lista[]) {
        
        ImageView imageview = new ImageView();
        for(int i=1;i<=9;i++)
        {
            gpimage.getChildren().get(i-1).setVisible(true);
            imageview = (ImageView)gpimage.getChildren().get(i-1);
            imageview.setImage((Image)imagemLista.get(lista[i]-1));
            imageview.setFitWidth(60);
            imageview.setFitHeight(60);
        }
        
    }
    
    @FXML
    private void evtLimpar(ActionEvent event) {
    
        reiniciaTela();
    }
        
    private void reiniciaTela() {
        
        labelTempo.setText("");
        labelpasso.setText("");
        passosPos = -1;
        for(int i =0;i<9;i++)
            gpimage.getChildren().get(i).setVisible(false);
    }
    
    @FXML
    private void evtResolver(ActionEvent event) {
        
        if(rbAlg1.isSelected() && imagemLista != null &&!imagemLista.isEmpty())
        {
            
            HillClimb hillclimb = new HillClimb(lista);
            start = System.currentTimeMillis();
            hillclimb.resolver();
            finish = System.currentTimeMillis();
            labelTempo.setText("Tempo: "+(double)(finish-start)/1000+"s");
            passosLista = hillclimb.getResultadoCaminho();
            lista = hillclimb.getResultado();
            atualizaTela(lista);
//            System.out.println(";");
            passosPosMax = passosPos = hillclimb.getProfundidade();
            labelpasso.setText("Passo "+passosPos + " de "+ passosPosMax );
        }
    }

    @FXML
    private void evtPassoAtras(ActionEvent event) {
        
        if(passosPos!= -1 && passosPos != 1)
        {
            passosPos--;
            atualizaTela(passosLista[passosPos-1]);
            labelpasso.setText("Passo "+passosPos + " de "+ passosPosMax );
        }   
    }

    @FXML
    private void evtPassoFrente(ActionEvent event) {
        
        if(passosPos != -1 && passosPos < passosPosMax)
        {
            passosPos++;
            atualizaTela(passosLista[passosPos-1]);
            labelpasso.setText("Passo "+passosPos + " de "+ passosPosMax );
        }
    }
}