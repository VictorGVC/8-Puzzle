/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Funcoes;

import java.awt.image.BufferedImage;
import java.awt.image.WritableRaster;
import java.util.ArrayList;
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
    
    private static List<int[]> getCaminhos(int lista[])
    {
        List caminhos = new ArrayList();
        
        switch(lista[0])
        {
            case 1:
                    caminhos.add(fazerMovimento(lista,2));
                    caminhos.add(fazerMovimento(lista,4));
                    break;
            case 2:
                    caminhos.add(fazerMovimento(lista,1));
                    caminhos.add(fazerMovimento(lista,3));
                    caminhos.add(fazerMovimento(lista,5));
                    break;
            case 3:
                    caminhos.add(fazerMovimento(lista,2));
                    caminhos.add(fazerMovimento(lista,6));
                    break;
            case 4:
                    caminhos.add(fazerMovimento(lista,1));
                    caminhos.add(fazerMovimento(lista,5));
                    caminhos.add(fazerMovimento(lista,7));
                    break;
            case 5:
                    caminhos.add(fazerMovimento(lista,2));
                    caminhos.add(fazerMovimento(lista,4));
                    caminhos.add(fazerMovimento(lista,6));
                    caminhos.add(fazerMovimento(lista,8));
                    break;
            case 6:
                    caminhos.add(fazerMovimento(lista,3));
                    caminhos.add(fazerMovimento(lista,5));
                    caminhos.add(fazerMovimento(lista,9));
                    break;
            case 7:
                    caminhos.add(fazerMovimento(lista,4));
                    caminhos.add(fazerMovimento(lista,8));
                    break;
            case 8:
                    caminhos.add(fazerMovimento(lista,5));
                    caminhos.add(fazerMovimento(lista,7));
                    caminhos.add(fazerMovimento(lista,9));
                    break;
            case 9:
                    caminhos.add(fazerMovimento(lista,6));
                    caminhos.add(fazerMovimento(lista,8));
                    break;
        }
        return caminhos;
    }
    
    private static int calculaDistancia(int[] lista)
    {
        int soma = 0,j=0,k=0;
        
        for (int i = 1; i < 10; i++) 
        {    
            switch(lista[i])
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
    
    public static int[] hillClimb(int lista[])
    {
        int [] lanterior = {1,2};
        int []result = new int[1];
        List<Integer> del = new ArrayList();
        List<int[]> caminhos = getCaminhos(lista);
        int atual = calculaDistancia(lista);
        int i = 0;
        for (int[] caminho : caminhos) 
        {
            if(atual<=calculaDistancia(caminho) || caminho == lanterior)
                del.add(i);
            i++;
        }
        for (int d : del) 
            caminhos.remove(d);
        
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
