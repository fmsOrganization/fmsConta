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
	 * Devuelve un TRUE/FALSE segun resultado de generacion de fichero
	 *************************************************************************** */
	
	public boolean imprimeFichero (String documentoImprimible[][],String nombreFichero,String rutaFichero) {
		
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
				// eliminamos los null
				if (documentoImprimible[i][0]==null) documentoImprimible[i][0]="";
				// recortamos la longitud
				if (documentoImprimible[i][0].length()>30) {
					documentoImprimible[i][0]=documentoImprimible[i][0].substring(0,31);
				} else if (documentoImprimible[i][0].length()<30) {
					// si es menor que la longitud predeterminada
					// rellenamos hasta la longitud deseada
					diferencia=30-documentoImprimible[i][0].length();
					documentoImprimible[i][0]="  "+documentoImprimible[i][0];
					for (int n=2;n<diferencia;n++){
						documentoImprimible[i][0]=documentoImprimible[i][0].concat(" ");
					}
				}
				// y añadimos a la linea
				newLine+=documentoImprimible[i][0];
				
				
				// eliminamos los null
				if (documentoImprimible[i][1]==null) documentoImprimible[i][1]="";
				// recortamos la longitud
				if (documentoImprimible[i][1].length()>10) {
					documentoImprimible[i][1]=documentoImprimible[i][1].substring(0,11);
				} else if (documentoImprimible[i][1].length()<10) {
					// si es menor que la longitud predeterminada
					// rellenamos hasta la longitud deseada
					diferencia=10-documentoImprimible[i][1].length();
					for (int n=0;n<diferencia;n++){
						documentoImprimible[i][1]=documentoImprimible[i][1].concat(" ");
					}
				}
				// y añadimos a la linea
				newLine+=documentoImprimible[i][1]+" ";
				
				
				// eliminamos los null
				if (documentoImprimible[i][2]==null) documentoImprimible[i][2]="";
				// recortamos la longitud
				if (documentoImprimible[i][2].length()>10) {
					documentoImprimible[i][2]=documentoImprimible[i][2].substring(0,11);
				} else if (documentoImprimible[i][2].length()<10) {
					// si es menor que la longitud predeterminada
					// rellenamos hasta la longitud deseada
					diferencia=10-documentoImprimible[i][2].length();
					for (int n=0;n<diferencia;n++){
						documentoImprimible[i][2]=documentoImprimible[i][2].concat(" ");
					}
				}
				// y añadimos a la linea
				newLine+=documentoImprimible[i][2]+" ";
				
				
				// eliminamos los null
				if (documentoImprimible[i][3]==null) documentoImprimible[i][3]="";
				// recortamos la longitud
				if (documentoImprimible[i][3].length()>10) {
					documentoImprimible[i][3]=documentoImprimible[i][3].substring(0,11);
				} else if (documentoImprimible[i][3].length()<10) {
					// si es menor que la longitud predeterminada
					// rellenamos hasta la longitud deseada
					diferencia=10-documentoImprimible[i][3].length();
					for (int n=0;n<diferencia;n++){
						documentoImprimible[i][3]=documentoImprimible[i][3].concat(" ");
					}
				}
				// y añadimos a la linea
				newLine+=documentoImprimible[i][3]+" ";
				
				
				// eliminamos los null
				if (documentoImprimible[i][4]==null) documentoImprimible[i][4]="";
				// recortamos la longitud
				if (documentoImprimible[i][4].length()>10) {
					documentoImprimible[i][4]=documentoImprimible[i][4].substring(0,11);
				} else if (documentoImprimible[i][4].length()<10) {
					// si es menor que la longitud predeterminada
					// rellenamos hasta la longitud deseada
					diferencia=10-documentoImprimible[i][4].length();
					for (int n=0;n<diferencia;n++){
						documentoImprimible[i][4]=documentoImprimible[i][4].concat(" ");
					}
				}
				// y añadimos a la linea
				newLine+=documentoImprimible[i][4]+" ";
				
				
				// eliminamos los null
				if (documentoImprimible[i][5]==null) documentoImprimible[i][5]="";
				// recortamos la longitud
				if (documentoImprimible[i][5].length()>30) {
					documentoImprimible[i][5]=documentoImprimible[i][5].substring(0,31);
				} else if (documentoImprimible[i][5].length()<30) {
					// si es menor que la longitud predeterminada
					// rellenamos hasta la longitud deseada
					diferencia=30-documentoImprimible[i][5].length();
					for (int n=0;n<diferencia;n++){
						documentoImprimible[i][5]=documentoImprimible[i][5].concat(" ");
					}
				}
				// y añadimos a la linea
				newLine+=documentoImprimible[i][5]+" ";
				
				
				// eliminamos los null
				if (documentoImprimible[i][6]==null) documentoImprimible[i][6]="";
				// recortamos la longitud
				if (documentoImprimible[i][6].length()>13) {
					documentoImprimible[i][6]=documentoImprimible[i][6].substring(0,14);
				} else if (documentoImprimible[i][6].length()<13) {
					// si es menor que la longitud predeterminada
					// rellenamos hasta la longitud deseada
					diferencia=13-documentoImprimible[i][6].length();
					for (int n=0;n<diferencia;n++){
						documentoImprimible[i][6]=documentoImprimible[i][6].concat(" ");
					}
				}
				// y añadimos a la linea
				newLine+=documentoImprimible[i][6]+" ";
		

				// eliminamos los null
				if (documentoImprimible[i][7]==null) documentoImprimible[i][7]="";
				// recortamos la longitud
				if (documentoImprimible[i][7].length()>10) {
					documentoImprimible[i][7]=documentoImprimible[i][7].substring(0,11);
				} else if (documentoImprimible[i][7].length()<10) {
					// si es menor que la longitud predeterminada
					// rellenamos hasta la longitud deseada
					diferencia=10-documentoImprimible[i][7].length();
					String espacios="";
					for (int n=0;n<diferencia;n++){
						espacios=espacios.concat(" ");
					}
					documentoImprimible[i][7]=espacios.concat(documentoImprimible[i][7]);
				}
				// y añadimos a la linea
				newLine+=documentoImprimible[i][7]+" ";
				
				
				// eliminamos los null
				if (documentoImprimible[i][8]==null) documentoImprimible[i][8]="";
				// recortamos la longitud
				if (documentoImprimible[i][8].length()>10) {
					documentoImprimible[i][8]=documentoImprimible[i][8].substring(0,11);
				} else if (documentoImprimible[i][8].length()<10) {
					// si es menor que la longitud predeterminada
					// rellenamos hasta la longitud deseada
					diferencia=10-documentoImprimible[i][8].length();
					String espacios="";
					for (int n=0;n<diferencia;n++){
						espacios=espacios.concat(" ");
					}
					documentoImprimible[i][8]=espacios.concat(documentoImprimible[i][8]);
				}
				// y añadimos a la linea
				newLine+=documentoImprimible[i][8]+" ";
				
				
				// eliminamos los null
				if (documentoImprimible[i][9]==null) documentoImprimible[i][9]="";
				// recortamos la longitud
				if (documentoImprimible[i][9].length()>10) {
					documentoImprimible[i][9]=documentoImprimible[i][9].substring(0,11);
				} else if (documentoImprimible[i][9].length()<10) {
					// si es menor que la longitud predeterminada
					// rellenamos hasta la longitud deseada
					diferencia=10-documentoImprimible[i][9].length();
					String espacios="";
					for (int n=0;n<diferencia;n++){
						espacios=espacios.concat(" ");
					}
					documentoImprimible[i][9]=espacios.concat(documentoImprimible[i][9]);
				}
				// y añadimos a la linea
				newLine+=documentoImprimible[i][9]+" ";

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
	

}
