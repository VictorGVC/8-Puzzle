/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Funcoes;

import ClassesControle.Nodo;
import java.awt.image.BufferedImage;
import java.awt.image.WritableRaster;
import java.util.ArrayList;
import java.util.Collections;
import static java.util.Collections.sort;
import java.util.Comparator;
import java.util.List;
import javafx.embed.swing.SwingFXUtils;
import javafx.scene.image.Image;
import javafx.scene.image.WritableImage;

/**
 *
 * @author Heitor
 */
public class Transformacoes {
    
    public static ArrayList Recortar(Image img)
    {
        ArrayList imagemLista = new ArrayList();
        BufferedImage bimage;
        BufferedImage cimage;
        
        Image recorte;
        double height = img.getHeight()/3,width = img.getWidth()/3;
        cimage = new BufferedImage((int)width+1,(int)height+1,BufferedImage.TYPE_INT_RGB);
        BufferedImage dimage = new BufferedImage((int)width+1,(int)height+1,BufferedImage.TYPE_INT_RGB);

        int a=0,b=0;
        bimage = SwingFXUtils.fromFXImage(img,null);
        WritableRaster raster = bimage.getRaster();
        for(int k=0; k < 9; k++)
        {
            WritableRaster rasterC = cimage.getRaster();
            int[] pixel = {0, 0, 0, 0};
            for(int i = 0;i < height;i++)
            {
                for(int j = 0;j < width;j++)
                {
                    raster.getPixel((int) (j+(width*b)), (int) (i+(height*a)), pixel);
                    rasterC.setPixel(j,i,pixel);
                }
            }
            b++;
            if(b == 3)
            {
                b=0;
                a++;
            }
            if(a == 3)
            {
                
                imagemLista.add(tonsCinza(cimage,(int)height,(int)width));
            }
            else   
                imagemLista.add(SwingFXUtils.toFXImage(cimage, null));
            cimage = new BufferedImage((int)width+1,(int)height+1,BufferedImage.TYPE_INT_RGB);
  
        }

        return imagemLista;  
    }
    
    private  static WritableImage tonsCinza(BufferedImage bimage,int height,int width)
    {
      
        WritableRaster raster = bimage.getRaster();
        int[] pixel = {0, 0, 0, 0};
        int cinza;
        for(int i = 0;i < height;i++)
        {
            for(int j = 0;j < width;j++)
            {
                raster.getPixel(j, i, pixel);
                //cinza = (pixel[0]+pixel[1]+pixel[2])/3; falso cinza
                cinza =(int) (pixel[0] *.3+pixel[1]*.59+pixel[2]*.11);// verdadeiro cinza para modificação
                pixel[0] = pixel[1] = pixel[2] = cinza;
                raster.setPixel(j, i, pixel);
            }
        }
        return SwingFXUtils.toFXImage(bimage, null);
    }
    public static int[] Embaralhar(int movimentos){
        int lista[] = {9,1,2,3,4,5,6,7,8,9};
        for(int i =0;i<movimentos;i++)
        {
            lista = fazerMovimentoAleatorio(lista);
        }
        
        return lista;
        
    }
    private static int[] fazerMovimentoAleatorio(int lista[]){ 
        int vazia = lista[0];
        int aux;
        int random;
        System.out.print(vazia + " para ");
        switch(vazia){
            case 1:
                    random = (int)(Math.random()*2)%2 + 1;
                    aux = lista[vazia];
                    lista[vazia] = lista[random*2];
                    lista[random*2] = aux;
                    lista[0] = random*2;
                    break;
            case 2:
                   random = (int)(Math.random()*3)%3;
                   aux = lista[vazia];
                   lista[vazia] = lista[random*2+1];
                   lista[random*2+1] = aux;
                   lista[0] = random*2+1;
                   break;
            case 3:
                    
                    random = (int)(Math.random()*2)%2;
                    aux = lista[vazia];
                    lista[vazia] = lista[random*4+2];
                    lista[random*4+2] = aux;
                    lista[0] = random*4+2;
                    break;
            case 4:
                    random = (int)(Math.random()*3)%3;
                    aux = lista[vazia];
                    if(random == 0)
                    {
                        lista[vazia] = lista[1];
                        lista[1] = aux;
                        lista[0] = 1;
                    }
                    else
                    {
                        lista[vazia] = lista[random*2+3];
                        lista[random*2+3] = aux;
                        lista[0] = random*2+3;
                    }

                    break;
            case 5:
                    random = (int)(Math.random()*4)%4;
                    aux = lista[vazia];
                    lista[vazia] = lista[random*2+2];
                    lista[random*2+2] = aux;
                    lista[0] = random*2+2;
                    break;
            case 6:
                    random = (int)(Math.random()*3)%3;
                    aux = lista[vazia];
                    if(random == 0)
                    {
                        lista[vazia] = lista[9];
                        lista[9] = aux;
                        lista[0] = 9;
                    }
                    else
                    {
                        lista[vazia] = lista[random*2+1];
                        lista[random*2+1] = aux;
                        lista[0] = random*2+1;
                    }
                    break;
            case 7:
                    random = (int)(Math.random()*2)%2+1;
                    aux = lista[vazia];
                    lista[vazia] = lista[random*4];
                    lista[random*4] = aux;
                    lista[0] = random*4;
                    break;
            case 8:
                    random = (int)(Math.random()*3)%3+1;
                    aux = lista[vazia];
                    lista[vazia] = lista[random*2+3];
                    lista[random*2+3] = aux;
                    lista[0] = random*2+3;
                    break;
            case 9:
                    random = (int)(Math.random()*2)%2+1;
                    aux = lista[vazia];
                    lista[vazia] = lista[random*2+4];
                    lista[random*2+4] = aux;
                    lista[0] = random*2+4;
                    break;
            
        }
        System.out.println(lista[0]+"\n");
        return lista;
    }
    
    public static List<Nodo> getCaminhos(Nodo n)
    {
        List<Nodo> caminhos = new ArrayList();
        
        switch(n.getLista()[0])
        {
            case 1:
                    caminhos.add(new Nodo(fazerMovimento(n.getLista(),2)));
                    caminhos.add(new Nodo(fazerMovimento(n.getLista(),4)));
                    break;
            case 2:
                    caminhos.add(new Nodo(fazerMovimento(n.getLista(),1)));
                    caminhos.add(new Nodo(fazerMovimento(n.getLista(),3)));
                    caminhos.add(new Nodo(fazerMovimento(n.getLista(),5)));
                    break;
            case 3:
                    caminhos.add(new Nodo(fazerMovimento(n.getLista(),2)));
                    caminhos.add(new Nodo(fazerMovimento(n.getLista(),6)));
                    break;
            case 4:
                    caminhos.add(new Nodo(fazerMovimento(n.getLista(),1)));
                    caminhos.add(new Nodo(fazerMovimento(n.getLista(),5)));
                    caminhos.add(new Nodo(fazerMovimento(n.getLista(),7)));
                    break;
            case 5:
                    caminhos.add(new Nodo(fazerMovimento(n.getLista(),2)));
                    caminhos.add(new Nodo(fazerMovimento(n.getLista(),4)));
                    caminhos.add(new Nodo(fazerMovimento(n.getLista(),6)));
                    caminhos.add(new Nodo(fazerMovimento(n.getLista(),8)));
                    break;
            case 6:
                    caminhos.add(new Nodo(fazerMovimento(n.getLista(),3)));
                    caminhos.add(new Nodo(fazerMovimento(n.getLista(),5)));
                    caminhos.add(new Nodo(fazerMovimento(n.getLista(),9)));
                    break;
            case 7:
                    caminhos.add(new Nodo(fazerMovimento(n.getLista(),4)));
                    caminhos.add(new Nodo(fazerMovimento(n.getLista(),8)));
                    break;
            case 8:
                    caminhos.add(new Nodo(fazerMovimento(n.getLista(),5)));
                    caminhos.add(new Nodo(fazerMovimento(n.getLista(),7)));
                    caminhos.add(new Nodo(fazerMovimento(n.getLista(),9)));
                    break;
            case 9:
                    caminhos.add(new Nodo(fazerMovimento(n.getLista(),6)));
                    caminhos.add(new Nodo(fazerMovimento(n.getLista(),8)));
                    break;
        }
        return caminhos;
    }
    public static int calculaPos(Nodo n)
    {
        int soma =0;
        
        
        
        for(int i= 1;i<=9;i++)
        {
            if(n.getLista()[i] != i)
                soma++;
        }
        return soma;
    }
    public static int calculaDistancia(Nodo n)
    {
        int soma = 0,j=0,k=0;
        
        for (int i = 1; i < 10; i++) 
        {    
            switch(n.getLista()[i])
            {
                case 1:
                    soma += j+k;
                break;
                case 2:
                    soma += j+Math.abs(k-1);
                break;
                case 3:
                    soma += j+Math.abs(k-2);
                break;
                case 4:
                    soma += Math.abs(j-1)+k;
                break;
                case 5:
                    soma += Math.abs(j-1)+Math.abs(k-1);
                break;
                case 6:
                    soma += Math.abs(j-1)+Math.abs(k-2);
                break;
                case 7:
                    soma += Math.abs(j-2)+k;
                break;
                case 8:
                    soma += Math.abs(j-2)+Math.abs(k-1);
                break;
                case 9:
                    soma += Math.abs(j-2)+Math.abs(k-2);
                break;
                
            }
            
            k++;
            if(k==3)
            {
                k = 0;
                j++;
            }
        }
        
        return soma;
    }
    
    private static Nodo hillClimb(Nodo lanterior, Nodo atual,int profundidade[],int[] passoslista[])
    {
        Nodo result = new Nodo();
        List<Integer> del = new ArrayList();
        List<Nodo> caminhos = getCaminhos(atual);
        
        int i = 0;
        caminhos.sort(Comparator.comparing(Nodo::getValor));
        for (Nodo caminho : caminhos) 
        {
            caminho.setValor(calculaDistancia(caminho));
            if(atual.getValor() <= caminho.getValor() || caminho.getLista() == lanterior.getLista())
                del.add(i);
            i++;
        }
        del.sort(Comparator.reverseOrder());
        for (int d : del) 
            caminhos.remove(d);
        
        boolean b = true;
        for (int j = 0; j < caminhos.size() && b; j++) 
        {
            if(caminhos.get(j).getValor()==0)
            {
                
                result = caminhos.get(j);
                b = false;
            }
            else
            {
                profundidade[0]++;
                result = hillClimb(atual,caminhos.get(j),profundidade,passoslista);
                
                if(result.getValor() == 0)
                    b = false;
            }
        }
        
        return result;
    }
    
    public static int[] hillClimb(int lista[],int profundidade[],int[] passoslista[])
    {
        Nodo atual = new Nodo(lista);
        //atual.setLista(lista);
        
        
        int []result = new int[10];
        atual.setValor(calculaDistancia(atual));
        
        result = hillClimb(atual, atual,profundidade,passoslista).getLista();
        
        return result;
    }
    
    private static int[] fazerMovimento(int lista[],int pos)
    { 
        int aux;
        int vazia = lista[0];
        int []alist = new int[lista.length];
        alist = lista.clone();
        aux = lista[vazia];
        alist[vazia] = lista[pos];
        alist[pos] = aux;
        alist[0] = pos;
        return alist;
    }
}
