package fmsconta.control;

import java.awt.GridBagConstraints;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

import javax.print.Doc;
import javax.print.DocFlavor;
import javax.print.DocPrintJob;
import javax.print.PrintException;
import javax.print.PrintService;
import javax.print.PrintServiceLookup;
import javax.print.SimpleDoc;
import javax.swing.JLabel;
import javax.swing.SwingConstants;


public class PrinterInfo{
	
	private PrintService service;
	private DocFlavor flavor;
	private DocPrintJob job;
	private Doc documento;
	
	public PrinterInfo() {
		// builder
	}
	
	
	
	/* **************************************************************************
	 * Este metodo imprime un documento en formato String[][] en la impresora
	 * predeterminada por el sistema
	 * 
	 * Recibe como parametro el documento como String[][]
	 * Devuelve un TRUE/FALSE segun resultado de impresion
	 *************************************************************************** */
	
	public boolean imprimeImpresora (String documentoImprimible[][]) {
		
		service=PrintServiceLookup.lookupDefaultPrintService();
		
		flavor=DocFlavor.BYTE_ARRAY.AUTOSENSE;
		
		job=service.createPrintJob();
		String textoPrueba="EL cielo y tal y tal";
		
		byte textoBytes[]=textoPrueba.getBytes();
		
		documento=new SimpleDoc(textoBytes,flavor,null);
		
		try {
			job.print(documento, null);
		} catch (PrintException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			System.err.println("error al imprimir");
			return false;
		}
		
		return true;
		
	} // fin del metodo imprimeIMpresora
	
	
	
	/* **************************************************************************
	 * Este metodo contruye un fichero de texto como salida de impresion
	 * 
	 * Recibe como parametro el documento a imprimir como String[][]
	 * int[] formatos con la longitud de cada uno de los campos
	 * int[] posicion con la alineacion izq=1 o der=2 cada uno de los campos
	 * Devuelve un TRUE/FALSE segun resultado de generacion de fichero
	 *************************************************************************** */
	
public boolean imprimeFichero (String documentoImprimible[][],int formatos[],int posicion[],String nombreFichero,String rutaFichero) {
		
		File fichero;
		File persDir;
		FileWriter grabacion;
		PrintWriter grabFichero;
		
		fichero=null;
		persDir=null;
		grabacion=null;
		grabFichero=null;
		
		try {
			// crea el fichero y abre el objeto de impresion
			persDir=new File(rutaFichero);
			fichero=new File(persDir,nombreFichero);
			grabacion=new FileWriter(fichero);
			grabFichero=new PrintWriter(grabacion);
			
			// lee la informacion linea por linea y la imprime
			for (int i=0;i<documentoImprimible.length;i++) {
				String newLine="";
				int diferencia=0;
				//******************** FORMATEAMOS ELEMENTO POR ELEMENTO **** 
				
				for (int j=0;j<formatos.length;j++) {
					
					// eliminamos los null
					if (documentoImprimible[i][j]==null) documentoImprimible[i][j]="";
					// recortamos la longitud
					if (documentoImprimible[i][j].length()>formatos[j]) {
						documentoImprimible[i][j]=documentoImprimible[i][j].substring(0,formatos[j]+1);
					} else if (documentoImprimible[i][j].length()<formatos[j]) {
						// si es menor que la longitud predeterminada
						// rellenamos hasta la longitud deseada
						diferencia=formatos[j]-documentoImprimible[i][j].length();
						documentoImprimible[i][j]="  "+documentoImprimible[i][j];
						// lo rellenamos con espacios
						String espacios="";
						for (int n=0;n<diferencia;n++){
							espacios=espacios.concat(" ");
						}
						// segun se alinea a izquierda=1 o derecha=2 ponemos los espacios
						if (posicion[j]==1) {
							documentoImprimible[i][j]=documentoImprimible[i][j].concat(espacios);
						} else {
							documentoImprimible[i][j]=espacios.concat(documentoImprimible[i][j]);
						}
					}
					// y añadimos a la linea
					newLine+=documentoImprimible[i][j];
					
				}		

				// graba en fichero texto
				grabFichero.println(newLine);
			}
				
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			System.err.println("NO es posible crear el fichero.txt");
			e1.printStackTrace();
			// se sale con un false
			return false;
		} finally {
			try {
				// cerramos el fichero texto
				grabacion.close();
			} catch (IOException e) {
				System.err.println("Error al cerrar el fichero");
				// TODO Auto-generated catch block
				e.printStackTrace();
				// se sale con un false
				return false;
			}
		}
		
		return true;
		
	} // fin del metodo imprimeFichero
	
	
	
	
} // ************** FIN DE LA CLASS PRINTERINFO
