
package codbrayle;
import com.itextpdf.text.BadElementException;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Image;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfWriter;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
//librerias ajenas a itext
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.filechooser.FileNameExtensionFilter;
import sun.applet.Main;
//import javax.swing.text.Document;


public class EXPORTAR implements add {
    public String letra;
    
    public void letra(String leta) {
        this.letra = leta;
    }
    
    private File ruta_destino=null;
    
    public void EXPORTAR(){    
    }
    
    /* metodo que hace uso de la clase itext para manipular archivos PDF*/
    public void crear_PDF(String cadena){
        //abre ventana de dialogo "guardar"
        //char[] abc={'a','b','c','d','e','f','g','h','i','j','k','l','m','n','o','p','q','r','s','t','u','v','w','x','y','z',};
        String a="abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ";    
        char[] abc =a.toCharArray();
        
        char[] cad =cadena.toCharArray();
              String ex=".png";
              String de="";
              float x=20;
              float y=700;
        Colocar_Destino();
        //si destino es diferente de null
        if(this.ruta_destino!=null){
            try {
                // se crea instancia del documento
                Document mipdf = new Document() {};
                // se establece una instancia a un documento EXPORTAR
                 PdfWriter.getInstance(mipdf, new FileOutputStream(this.ruta_destino + ".pdf"));
                mipdf.open();
                String du="";
                
                for(int i=0; i<cad.length;i++){
                   // for (int j=0;j<abc.length;j++ ){
                   
                        de=(new StringBuffer().append(cad[i])).toString();
                        //du=(new StringBuffer().append(abc[j])).toString();
                        if(de.compareTo(" ")!=0){
                        
                        Image im = Image.getInstance(de+ex);
                         im.setAbsolutePosition(x ,y );
                        x=x+50;
                        mipdf.add(im);
                        
                        }
                        else {
                             y=y-100;
                             x=20;
                        }
                        
                        if (y<25){
                            mipdf.newPage();
                            y=740;
                        }
                        
                }
                mipdf.close(); //se cierra el PDF&
                JOptionPane.showMessageDialog(null,"Documento PDF creado");
            } catch (DocumentException | FileNotFoundException exh) {
                Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, exh);
            } catch (IOException ex1) {
                      Logger.getLogger(EXPORTAR.class.getName()).log(Level.SEVERE, null, ex1);
                  }            
        }        
    }
    /* abre la ventana de dialogo GUARDAR*/
    public void Colocar_Destino(){
       FileNameExtensionFilter filter = new FileNameExtensionFilter("Archivo PDF","pdf","PDF");
       JFileChooser fileChooser = new JFileChooser();       
       fileChooser.setFileFilter(filter);
       int result = fileChooser.showSaveDialog(null);
       if ( result == JFileChooser.APPROVE_OPTION ){   
           this.ruta_destino = fileChooser.getSelectedFile().getAbsoluteFile();
        }
    }    

    @Override
    public String Obtener() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
}