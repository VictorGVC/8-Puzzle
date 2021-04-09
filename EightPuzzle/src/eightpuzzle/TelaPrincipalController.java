package eightpuzzle;

import Algoritmos.AEstrela;
import Algoritmos.HillClimb;
import Funcoes.Transformacoes;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXRadioButton;
import com.jfoenix.controls.JFXTextField;
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
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;
import javafx.stage.FileChooser;

public class TelaPrincipalController implements Initializable {
    
    private long start,finish;
    private int lista[];
    private ArrayList imagemLista;
    private ArrayList imagemFundoCinza;
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
    @FXML
    private Label labelvisitados;
    @FXML
    private JFXTextField txvezes;
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
        reiniciaTela();
    }
    
    @FXML
    private void evtAbrir(ActionEvent event) {
        
        FileChooser fc = new FileChooser();
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
            imagemFundoCinza = Transformacoes.RecortarCinza(img);
            imagemLista = Transformacoes.Recortar(img);
            atualizaTela(lista);
            labelTempo.setText("");
            labelvisitados.setText("");
        }
    }
    
    @FXML
    private void evtEmbaralhar(ActionEvent event) {
        
        if(txvezes.getText().length()>1 && txvezes.getText().charAt(0) == '"')
        {
            int aux;
            for(int i = 1; i < 10; i++)
            {
                if(txvezes.getText().charAt(i) == '9')
                {
                    lista[0] = i;
                }
                lista[i] = Integer.parseInt(txvezes.getText().charAt(i)+"");
            }
            labelvisitados.setText("");
            labelTempo.setText("");
            labelpasso.setText("");
            atualizaTela(lista);    
        }
        try {
            
            Integer.parseInt(txvezes.getText());

            if(imagemLista != null && !imagemLista.isEmpty())
            {
                labelvisitados.setText("");
                labelTempo.setText("");
                labelpasso.setText("");
                lista = Funcoes.Transformacoes.Embaralhar(Integer.parseInt(txvezes.getText()));

                atualizaTela(lista);
            }
        } catch (NumberFormatException e) {
            System.out.println("aoo deu aí em, coloca um numero!");
        }
        
    }
    
    private void atualizaTela(int lista[]) {
        
        ImageView imageview = new ImageView();
        
        for(int i = 1; i <= 9; i++)
        {
            gpimage.getChildren().get(i-1).setVisible(true);
            
            imageview = (ImageView)gpimage.getChildren().get(i-1);
            /*if(lista[i] == 9)
                imageview.setImage((Image)imagemFundoCinza.get(i-1));
            else*/
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
        
        labelvisitados.setText("");
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
            labelvisitados.setText("Visitados: "+hillclimb.getPassos());
            
            //System.out.println(";");
            passosPosMax = passosPos = hillclimb.getProfundidade();
            lista =  passosLista[passosPosMax-1].clone();
            labelpasso.setText("Passo "+passosPos + " de "+ passosPosMax );
            atualizaTela(lista); 
        }
        if(rbAlg2.isSelected() && imagemLista != null &&!imagemLista.isEmpty())
        {
            AEstrela estrela = new AEstrela(lista);
            
            start = System.currentTimeMillis();
            estrela.resolver();
            finish = System.currentTimeMillis();
            labelTempo.setText("Tempo: "+(double)(finish-start)/1000+"s");
            labelvisitados.setText("Visitados: "+estrela.getNumVisitados());
            
            passosLista = estrela.getResultadoCaminho();
            passosPosMax = passosPos = estrela.getProfundidade();
            lista = passosLista[passosPosMax-1].clone();
            labelpasso.setText("Passo "+passosPos + " de "+ passosPosMax );
            atualizaTela(lista);
        }
    }

    @FXML
    private void evtPassoAtras(ActionEvent event) {
        
        if(passosPos != -1 && passosPos > 1)
        {
            passosPos--;  
            labelpasso.setText("Passo "+passosPos + " de "+ passosPosMax );    
        }
        if(passosPos != -1)
        {
            atualizaTela(passosLista[passosPos-1]);
            lista = passosLista[passosPos-1];
        }
    }

    @FXML
    private void evtPassoFrente(ActionEvent event) {
        
        if(passosPos != -1 && passosPos < passosPosMax)
        {
            passosPos++;
            labelpasso.setText("Passo "+passosPos + " de "+ passosPosMax );
        }
        if(passosPos!= -1)
        {
            atualizaTela(passosLista[passosPos-1]);
            lista = passosLista[passosPos-1];
        }
    }

    @FXML
    private void evtJogar1(MouseEvent event) {
        
        if(imagemLista != null && !imagemLista.isEmpty())
        {
            int vazia = lista[0];
            int K = 1;
            if(vazia == 4 || vazia == 2)
            {
                int aux = lista[K];
                lista[K] = lista[vazia];
                lista[vazia] = aux;
                lista[0] = K;
                atualizaTela(lista);
            } 
        }
    }

    @FXML
    private void evtJogar2(MouseEvent event) {
        
        if(imagemLista != null && !imagemLista.isEmpty())
        {
            int vazia = lista[0];
            int K = 2;
            if(vazia == 1 || vazia == 3 || vazia == 5)
            {
                int aux = lista[K];
                lista[K] = lista[vazia];
                lista[vazia] = aux;
                lista[0] = K;
                atualizaTela(lista);
            }
        }
    }

    @FXML
    private void evtJogar3(MouseEvent event) {
        
        if(imagemLista != null && !imagemLista.isEmpty())
        {
            int vazia = lista[0];
            int K = 3;
            if(vazia == 2 || vazia == 6)
            {
                int aux = lista[K];
                lista[K] = lista[vazia];
                lista[vazia] = aux;
                lista[0] = K;
                atualizaTela(lista);
            } 
        }
    }

    @FXML
    private void evtJogar4(MouseEvent event) {
        
        if(imagemLista != null && !imagemLista.isEmpty())
        {
            int vazia = lista[0];
            int K = 4;
            if(vazia == 1 || vazia == 5 || vazia == 7)
            {
                int aux = lista[K];
                lista[K] = lista[vazia];
                lista[vazia] = aux;
                lista[0] = K;
                atualizaTela(lista);
            } 
        }
    }

    @FXML
    private void evtJogar5(MouseEvent event) {
        
        if(imagemLista != null && !imagemLista.isEmpty())
        {
            int vazia = lista[0];
            int K = 5;
            if(vazia == 2 || vazia == 4 || vazia == 6|| vazia==8)
            {
                int aux = lista[K];
                lista[K] = lista[vazia];
                lista[vazia] = aux;
                lista[0] = K;
                atualizaTela(lista);
            } 
        }
    }

    @FXML
    private void evtJogar6(MouseEvent event) {
        
        if(imagemLista != null && !imagemLista.isEmpty())
        {
            int vazia = lista[0];
            int K = 6;
            if(vazia == 3 || vazia == 5 || vazia == 9)
            {
                int aux = lista[K];
                lista[K] = lista[vazia];
                lista[vazia] = aux;
                lista[0] = K;
                atualizaTela(lista);
            } 
        }
    }

    @FXML
    private void evtJogar7(MouseEvent event) {
        
        if(imagemLista != null && !imagemLista.isEmpty())
        {
            int vazia = lista[0];
            int K = 7;
            if(vazia == 4 || vazia == 8)
            {
                int aux = lista[K];
                lista[K] = lista[vazia];
                lista[vazia] = aux;
                lista[0] = K;
                atualizaTela(lista);
            } 
        }
    }

    @FXML
    private void evtJogar8(MouseEvent event) {
        
        if(imagemLista != null && !imagemLista.isEmpty())
        {
            int vazia = lista[0];
            int K = 8;
            if(vazia == 9 || vazia == 5 || vazia == 7)
            {
                int aux = lista[K];
                lista[K] = lista[vazia];
                lista[vazia] = aux;
                lista[0] = K;
                atualizaTela(lista);
            } 
        }
    }

    @FXML
    private void evtJogar22(MouseEvent event) {
        
        if(imagemLista != null && !imagemLista.isEmpty())
        {
            int vazia = lista[0];
            int K = 9;
            if(vazia == 8 || vazia == 6)
            {
                int aux = lista[K];
                lista[K] = lista[vazia];
                lista[vazia] = aux;
                lista[0] = K;
                atualizaTela(lista);
            } 
        }
    }
}