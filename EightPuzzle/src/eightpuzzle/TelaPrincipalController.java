/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package eightpuzzle;

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
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ToggleGroup;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

/**
 *
 * @author vicga
 */
public class TelaPrincipalController implements Initializable {
    
    private int lista[];
    private ArrayList imagemLista;
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
    
    
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
       
    }
    @FXML
    private void evtAbrir(ActionEvent event) {
        FileChooser fc=new FileChooser();
        ImageView imageview = new ImageView();
        arq = fc.showOpenDialog(null);
        Image img;
       if(arq!=null)
       {
           imagemLista = new ArrayList();
           arquivo = arq;
           img = new Image(arq.toURI().toString());
           System.out.println(arq.toURI().toString());
           imagemLista = Transformacoes.Recortar(img);
           for(int i =0; i<9;i++)
           {
                imageview = (ImageView)gpimage.getChildren().get(i);
                imageview.setImage((Image)imagemLista.get(i));
                imageview.setFitWidth(60);
                imageview.setFitHeight(60);
           }

        }
        
    }
    @FXML
    private void evtEmbaralhar(ActionEvent event)
    {
        ImageView imageview = new ImageView();
        lista = Funcoes.Transformacoes.Embaralhar(12);
        for(int i=1;i<=9;i++)
        {
            imageview = (ImageView)gpimage.getChildren().get(i-1);
            imageview.setImage((Image)imagemLista.get(lista[i]-1));
            imageview.setFitWidth(60);
            imageview.setFitHeight(60);
        }
    }

    @FXML
    private void evtLimpar(ActionEvent event) {
    }

    @FXML
    private void evtResolver(ActionEvent event) {
    }
}

    

