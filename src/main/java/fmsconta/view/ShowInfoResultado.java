package fmsconta.view;

import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Calendar;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.SwingConstants;

import fmsconta.control.PrinterInfo;

public class ShowInfoResultado  extends JFrame implements ActionListener, Settings {

	private JFrame auxWindow;
	private JScrollPane panelScroll;
	private JPanel listadoDatos;
	private JPanel listadoSumas;
	private JLabel title1;
	private JLabel title2;
	private JLabel space1=new JLabel(" ");
	private JLabel space2=new JLabel(" ");
	private String datos2[][];
	private String fecha;
	
	private JButton impress;
	private JButton fichero;
	private JButton cerrar;

	
	
	
	public ShowInfoResultado () {
		// constructor
	}
		
		
	public ShowInfoResultado (String datos[][],String title1,String title2){
			
		// recupera los datos recibidos como parametros
		
		// Obtiene la fecha
		Calendar dat=Calendar.getInstance();
		int ye=dat.get(Calendar.YEAR);
		int mo=dat.get(Calendar.MONTH);
		int da=dat.get(Calendar.DAY_OF_MONTH);
		
		if (da>9) {
			if (mo>9) {
				this.fecha=String.valueOf(da)+"-"+String.valueOf(mo)+"-"+String.valueOf(ye-2000);
			} else {
				this.fecha=String.valueOf(da)+"-0"+String.valueOf(mo)+"-"+String.valueOf(ye-2000);
			}
		} else if (mo>9) {
					this.fecha="0"+String.valueOf(da)+"-"+String.valueOf(mo)+"-"+String.valueOf(ye-2000);
				} else {
					this.fecha="0"+String.valueOf(da)+"-0"+String.valueOf(mo)+"-"+String.valueOf(ye-2000);
				}
			
		
		this.title1=new JLabel(title1+"                             fecha:"+fecha);
		this.title2=new JLabel(title2);
		
		this.datos2=datos;
		
			// creamos primero el marco de la ventana auxiliar
			auxWindow=new JFrame("Listado de la Cuenta de Resultados");
			auxWindow.setMinimumSize(new Dimension(700,500));
			auxWindow.setPreferredSize(new Dimension(1000,700));
			auxWindow.setMaximumSize(new Dimension(1000,700));
			auxWindow.setResizable(false);
			auxWindow.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
			auxWindow.getContentPane().setBackground(ColorBlanco);
			
			panelScroll=new JScrollPane();
			panelScroll.setPreferredSize(new Dimension(1000,500));
			panelScroll.setMaximumSize(new Dimension(1000,700));
			
			listadoSumas=new JPanel();
			listadoSumas.setLayout(new BoxLayout(listadoSumas,BoxLayout.Y_AXIS));
			listadoSumas.setAlignmentX(LEFT_ALIGNMENT);
			listadoSumas.setBackground(ColorBlanco);
			
			// titulo
			JPanel north1=new JPanel();
			north1.setLayout(new BoxLayout(north1,BoxLayout.Y_AXIS));
			north1.setAlignmentX(LEFT_ALIGNMENT);
			north1.setBackground(ColorBlanco);	
		    this.title1.setFont(Fuente1);
		    this.title2.setFont(Fuente2);
		    this.title1.setHorizontalAlignment(SwingConstants.LEFT);
		    this.title1.setAlignmentX(LEFT_ALIGNMENT);
		    north1.setAlignmentX((float)1);
			north1.add(this.title1);
			north1.add(space1);
			north1.add(this.title2);
			north1.add(space2);
			
			// centro
			JPanel center1=new JPanel();
			center1.setLayout(new BoxLayout(center1,BoxLayout.Y_AXIS));
			center1.setBackground(ColorBlanco);
		    // creacion del panel de botones inferior
		    JPanel south1=new JPanel();
		    south1.setBackground(ColorBlanco);
		    south1.setLayout(new FlowLayout());
		    impress=new JButton("Imprimir");
		    impress.setToolTipText("Abre una pantalla");
		    south1.add(impress);
		    fichero=new JButton("Fichero texto");
		    fichero.setToolTipText("Crea un fichero de texto");
		    south1.add(fichero);
		    cerrar=new JButton("Cerrar ventana");
		    cerrar.setToolTipText("Cierra la ventana");
		    south1.add(cerrar);
			
			// mostraremos la informacion en JLabels dinamicos
			JLabel[][] lab=new JLabel[datos2.length][6];
								
			listadoDatos=new JPanel();
			listadoDatos.setLayout(new GridBagLayout());
			listadoDatos.setBackground(ColorBlanco);
			GridBagConstraints constraints;
			Insets alrededor=new Insets(5,5,5,5);
				
					
			for (int i=0;i<datos2.length;i++) {
				constraints=new GridBagConstraints();
				constraints.gridy=i;
				constraints.weightx=1;
				constraints.weighty=1.5;
				constraints.insets=alrededor;
				constraints.fill=GridBagConstraints.NONE;
				constraints.anchor=GridBagConstraints.WEST;

				for (int j=0;j<5;j++) {
					// creamos la etiqueta y le agregamos el dato
					lab[i][j]=new JLabel();
					lab[i][j].setHorizontalAlignment(SwingConstants.LEFT);
					lab[i][j].setFont(Fuente4);
					lab[i][j].setText(datos2[i][j]);
					if (j>1) {
						constraints.anchor=GridBagConstraints.EAST;
					}
					constraints.gridx=j;
					listadoDatos.add(lab[i][j],constraints);
				}
						
			}
					
			center1.add(listadoDatos);
					
			listadoSumas.add(north1);
			listadoSumas.add(center1);
			listadoSumas.add(south1);
					
			listadoSumas.setVisible(true);
			panelScroll.setViewportView(listadoSumas);
			panelScroll.getViewport().setView(listadoSumas);
			auxWindow.add(panelScroll);
			auxWindow.setVisible(true);
			
			impress.addActionListener(this);
			fichero.addActionListener(this);
			cerrar.addActionListener(this);
			
	}  // fin del constructor showinfo

	
	
	/* ******************************************************
	 * Lectura de los botones del listado generado
	 * 
	 * Imprimir genera un listado de impresora
	 * Fichero genera un fichero de texto preformateado
	 * Cancelar cierra la pantalla
	 *******************************************************/
	
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
Object source=e.getSource();
		
		if (source==impress) {
			// si pulsa imprimir crea un objeto de tipo PrinterInfo
			// que enviara un documento a la impresora default
			PrinterInfo imprime=new PrinterInfo();
			if (imprime.imprimeImpresora(datos2)) {
				JOptionPane.showMessageDialog(null, "Impresión realizada");
			} else JOptionPane.showMessageDialog(null, "Error de impresión");
		}
		
		if (source==fichero) {
			// si pulsa fichero se generara un fichero tipo txt
			// con la informacion
			String nombreFichero="Resultado"+this.fecha+".txt";
			PrinterInfo imprime=new PrinterInfo();
			// llamamos al metodo imprimeFichero con los datos, la longitud de los datos
			// el nombre del fichero y la ruta, variable final de PersonalSettings
			int longitudes[]={12,50,12,15,15};
			int alineacion[]={1,1,2,2,2};
			if (imprime.imprimeFichero(datos2,longitudes,alineacion,nombreFichero,PathPersonalFiles)) {
				JOptionPane.showMessageDialog(null, "Fichero de texto generado");
			} else JOptionPane.showMessageDialog(null, "Error al generar el fichero de texto");	
		}
		
		if (source==cerrar) {
			// si pulsa cerrar se cierra la ventana
			// con la informacion
			auxWindow.dispose();
		}
		
	} // fin del metodo actionListener

} // ****************** FIN DE LA CLASS SHOWINFORESULTADO
