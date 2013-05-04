package fmsconta.view;

import java.awt.Component;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.Insets;
import java.awt.Rectangle;
import java.awt.TextField;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Calendar;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.ScrollPaneConstants;
import javax.swing.ScrollPaneLayout;
import javax.swing.SwingConstants;

import fmsconta.control.ContaDAO;

public class ContListadoMayor extends JFrame implements ActionListener,Settings {
	
	
	private JPanel listadoMayor;
	
	private JLabel title1;
	private JLabel title2;
	private JLabel space1=new JLabel(" ");
	private JLabel space2=new JLabel(" ");
	private String datos2[][];
	public int sizeComponent=0;
	
	private JButton imprimir;
	private JButton cancelar;
	
	
	/* *************************************************************
	 * Realiza el listado del Mayor de Cuentas Auxiliares
	 * De cuenta a cuenta y entre fecha y fecha
	 * 
	 * Recibe como parametros el key de la empresa a listar,
	 * las cuentas inicial y final, las fechas inicial y final
	 * 
	 * Como constructor no devuelve valores
	 ************************************************************ */
	
	protected ContListadoMayor(String keyEmpresa, String nombreCompany,String ctaInicial,
			String ctaFinal, String fechaInicial, String fechaFinal) {
		
		listadoMayor=new JPanel();
		listadoMayor.setLayout(new BoxLayout(listadoMayor,BoxLayout.Y_AXIS));
		listadoMayor.setAlignmentX(LEFT_ALIGNMENT);
		listadoMayor.setBackground(ColorFondo);
		
		// titulo
		JPanel north1=new JPanel();
		north1.setLayout(new BoxLayout(north1,BoxLayout.Y_AXIS));
		north1.setBackground(ColorFondo);
		title1=new JLabel("LIBRO MAYOR ACUMULADO DE "+nombreCompany);

		// Obtiene el año actual
		title2=new JLabel("De cuenta: "+ctaInicial+" a "+ctaFinal+" y desde el "+fechaInicial+" al "+fechaFinal);
		
	    title1.setFont(Fuente1);
	    title2.setFont(Fuente3);

	    north1.setAlignmentX((float)1);
		north1.add(title1);
		north1.add(space1);
		north1.add(title2);
		north1.add(space2);
		
		// centro
		JPanel center1=new JPanel();
		center1.setLayout(new BoxLayout(center1,BoxLayout.Y_AXIS));
		center1.setBackground(ColorFondo);
		center1.setAlignmentY((float)1);

		// lee los datos
		ContaDAO dao=new ContaDAO();
		// obtenemos un array[n][10] compuesto y ordenado
		// para imprimir directamente, segun los datos facilitados
		datos2=dao.leeMayor(keyEmpresa, ctaInicial, ctaFinal, fechaInicial,fechaFinal);

		// mostraremos la informacion en JLabels dinamicos
		JLabel[][] lab=new JLabel[datos2.length][11];
					
		JPanel listadoDatos=new JPanel();
		listadoDatos.setLayout(new GridBagLayout());
		GridBagConstraints constraints;
		Insets alrededor=new Insets(5,5,5,5);
	
		
		for (int i=0;i<datos2.length;i++) {
			constraints=new GridBagConstraints();
			constraints.gridy=i;
			constraints.weightx=1;
			constraints.weighty=1;
			constraints.insets=alrededor;
			constraints.fill=GridBagConstraints.NONE;
			constraints.anchor=GridBagConstraints.WEST;

			for (int j=0;j<10;j++) {
				// creamos la etiqueta y le agregamos el dato
				lab[i][j]=new JLabel();
				lab[i][j].setHorizontalAlignment(SwingConstants.LEFT);
				lab[i][j].setFont(Fuente4);
				lab[i][j].setText(datos2[i][j]);
				if (j==7) {
					constraints.anchor=GridBagConstraints.EAST;
				}
				constraints.gridx=j;
				listadoDatos.add(lab[i][j],constraints);
			}
			
		}
		sizeComponent=(lab.length);
		
		center1.add(listadoDatos);

	    // creacion del panel de botones inferior
	    JPanel south1=new JPanel();
	    south1.setLayout(new FlowLayout());
	    south1.setBackground(ColorFondo);
	    imprimir=new JButton("Imprimir");
	    imprimir.setToolTipText("Abre una pantalla");
	    south1.add(imprimir);
	    cancelar=new JButton("Cancelar");
	    cancelar.setToolTipText("Borra la pantalla");
	    south1.add(cancelar);
	    
		listadoMayor.add(north1);
		listadoMayor.add(center1);
		listadoMayor.add(south1);
		
		imprimir.addActionListener(this);
		cancelar.addActionListener(this);
		
	} // fin del constructor
	
	
	
	/* ******************************************************************
	 * Este metodo simplemente retorna el JPanel al metodo que llama
	 ******************************************************************* */
	
	protected JPanel retorna() {
		
		return listadoMayor;
		
	} // fin del metodo

	

	/* ******************************************************************
	 * Este metodo implementa el actionPerformed
	 ******************************************************************* */
	
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		Object source=e.getSource();
		
		if (source == imprimir) {
			// si pulsa el boton imprimir muestra una pantalla independiente
			// donde podra elegir entre imprimir en impresora o en fichero de texto
			ShowInfoMayor nuevaVentana=new ShowInfoMayor(datos2,getTitle1(),getTitle2());
		}
		
		if (source == cancelar) {
			// solamente borra la pantalla
			listadoMayor.setVisible(false);
		}
		
	}

	
	
/* *************************
 *  Getters de los titulos
 *************************** */

	private String getTitle1() {
		return title1.getText();
	}


	private String getTitle2() {
		return title2.getText();
	}


} // ****************** FIN DE LA CLASE CONTLISTADOMAYOR
