package fmsconta.view;

import java.awt.FlowLayout;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

import fmsconta.control.ContaDAO;

public class ContListadoDiario extends JFrame implements ActionListener, Settings {
	
	
	private JPanel listadoDiario;
	
	private JLabel title1;
	private JLabel title2;
	private JLabel space1=new JLabel(" ");
	private JLabel space2=new JLabel(" ");
	private String datos2[][];
	public int sizeComponent=0;
	
	private JButton imprimir;
	private JButton cancelar;
	
	
	/* *************************************************************
	 * Realiza el listado del Diario 
	 * De cuenta a cuenta y entre fecha y fecha
	 * Aspectos de impresion: lineas por hoja y Diario Oficial
	 * 
	 * Recibe como parametros el key de la empresa a listar,
	 * las cuentas inicial y final, las fechas inicial y final
	 * el numero de lineas por hoja, y si desea Diario Oficial
	 * 
	 * Como constructor no devuelve valores
	 * 
	 * OPERATIVA:
	 * A)
	 ************************************************************ */
	
	protected ContListadoDiario(String keyEmpresa, String nombreCompany, String fechaInicial, 
			String fechaFinal, String lineas, String Oficial) {
		
		listadoDiario=new JPanel();
		listadoDiario.setLayout(new BoxLayout(listadoDiario,BoxLayout.Y_AXIS));
		listadoDiario.setAlignmentX(LEFT_ALIGNMENT);
		listadoDiario.setBackground(ColorFondo);
		
		// titulo
		JPanel north1=new JPanel();
		north1.setLayout(new BoxLayout(north1,BoxLayout.Y_AXIS));
		north1.setBackground(ColorFondo);
		title1=new JLabel("LIBRO DIARIO DE "+nombreCompany);

		// Obtiene el año actual
		title2=new JLabel("Del "+fechaInicial+" al "+fechaFinal);
		
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
		// obtenemos un array[n][9] compuesto y ordenado
		// para imprimir directamente, segun los datos facilitados
		datos2=dao.leeDiario(keyEmpresa,nombreCompany.toUpperCase(),fechaInicial,fechaFinal,lineas);

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

			for (int j=0;j<9;j++) {
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
	    
		listadoDiario.add(north1);
		listadoDiario.add(center1);
		listadoDiario.add(south1);
		
		imprimir.addActionListener(this);
		cancelar.addActionListener(this);
		
	} // fin del constructor
	
	
	
	/* ******************************************************************
	 * Este metodo simplemente retorna el JPanel al metodo que llama
	 ******************************************************************* */
	
	protected JPanel retorna() {
		
		return listadoDiario;
		
	} // fin del metodo

	
	
	
	

	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub

		Object source=e.getSource();
		
		if (source == imprimir) {
			// si pulsa el boton imprimir muestra una pantalla independiente
			// donde podra elegir entre imprimir en impresora o en fichero de texto
			ShowInfoDiario nuevaVentana=new ShowInfoDiario(datos2,title1.getText(),title2.getText());
		}
		
		if (source == cancelar) {
			// solamente borra la pantalla
			listadoDiario.setVisible(false);
		}
		
	}

}
