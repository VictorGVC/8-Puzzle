/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Funcoes;

import java.awt.image.BufferedImage;
import java.awt.image.WritableRaster;
import java.util.ArrayList;
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
}
