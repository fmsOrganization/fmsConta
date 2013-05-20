package fmsconta.view;

import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Calendar;
import java.util.Date;

import javax.swing.BoxLayout;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTabbedPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.AbstractTableModel;

import fmsconta.control.CompanyFiles;
import fmsconta.control.ContaDAO;


public class ContEmpresa extends JFrame implements ActionListener, Settings{
	
	// contenedores
	private JFrame ventanaListado;
	private JTabbedPane panTab;
	private JPanel panelUsu;
	private JPanel panelUsu2;
	private JPanel panelUsu3;
	
	// componentes
	private JTable tablaDatos;
	private JLabel n1=new JLabel("Consulta de datos de la empresa");
	private JLabel n2=new JLabel("Modificacion de datos de la empresa");
	private JLabel n3=new JLabel("Creación de nueva empresa");
	private JLabel l1=new JLabel("     Nombre de la empresa");
	private JLabel l2=new JLabel("     Dirección");
	private JLabel l3=new JLabel("     Localidad");
	private JLabel l4=new JLabel("     Provincia");
	private JLabel l5=new JLabel("     Código postal");
	private JLabel l6=new JLabel("     N.I.F.");
	private JLabel l7=new JLabel("     Inicio ejercicio");
	private JLabel l8=new JLabel("     Final ejercicio");
	private JLabel l9=new JLabel("     Activa");
	private JLabel l10=new JLabel("     Manager");
	private JLabel l1B=new JLabel("     Nombre de la empresa");
	private JLabel l2B=new JLabel("     Dirección");
	private JLabel l3B=new JLabel("     Localidad");
	private JLabel l4B=new JLabel("     Provincia");
	private JLabel l5B=new JLabel("     Código postal");
	private JLabel l6B=new JLabel("     N.I.F.");
	private JLabel l7B=new JLabel("     Inicio ejercicio");
	private JLabel l8B=new JLabel("     Final ejercicio");
	private JLabel l9B=new JLabel("     Activa");
	private JLabel l10B=new JLabel("     Manager");
	private JLabel l1C=new JLabel("     Nombre de la empresa");
	private JLabel l2C=new JLabel("     Dirección");
	private JLabel l3C=new JLabel("     Localidad");
	private JLabel l4C=new JLabel("     Provincia");
	private JLabel l5C=new JLabel("     Código postal");
	private JLabel l6C=new JLabel("     N.I.F.");
	private JLabel l7C=new JLabel("     Inicio ejercicio");
	private JLabel l8C=new JLabel("     Final ejercicio");
	private JLabel l9C=new JLabel("     Activa");
	private JLabel l10C=new JLabel("     Manager");
	private JTextField d1=new JTextField();
	private JTextField d2=new JTextField();
	private JTextField d3=new JTextField();
	private JTextField d4=new JTextField();
	private JTextField d5=new JTextField();
	private JTextField d6=new JTextField();
	private JTextField d7=new JTextField();
	private JTextField d8=new JTextField();
	private JTextField d9=new JTextField();
	private JTextField d10=new JTextField();
	private JTextField d1B=new JTextField();
	private JTextField d2B=new JTextField();
	private JTextField d3B=new JTextField();
	private JTextField d4B=new JTextField();
	private JTextField d5B=new JTextField();
	private JTextField d6B=new JTextField();
	private JTextField d7B=new JTextField();
	private JTextField d8B=new JTextField();
	private JComboBox d9B=new JComboBox();
	private JTextField d10B=new JTextField();
	private JTextField d1C=new JTextField();
	private JTextField d2C=new JTextField();
	private JTextField d3C=new JTextField();
	private JTextField d4C=new JTextField();
	private JTextField d5C=new JTextField();
	private JTextField d6C=new JTextField();
	private JTextField d7C=new JTextField();
	private JTextField d8C=new JTextField();
	private JComboBox d9C=new JComboBox();
	private JTextField d10C=new JTextField();
	
	// graficos
	private Image abc;
	private Icon iconoW;
	private Icon iconoE;
	private Icon iconoD;
	
	// botones y controles
	private JButton imprimir;
	private JButton modificar;
	private JButton eliminar;
	private JButton crear;
	
	private String datosUsuario[];	// matriz con los datos del usuario
	private String datosEmp[];		// matriz con los datos de la empresa
	private String creaEmp[];		// matriz para creacion datos empresa
	private String keyEmpr;			// key de la empresa actual
	private int userCat;			// categoria que tiene el usuario 
	private String keyUser;			// key del usuario
	private ContaDAO dao;			// instanciar objeto tipo conexiones empresa actual
	private ContaDAO daoN;			// instanciar objeto tipo conexiones empresa nueva
	
	
	/* *********************************************************
	 * Este metodo permite mostrar, modificar y borrar los datos
	 * de cada empresa registrada para contabilidad
	 * El metodo recibe una matriz de datos de empresa, su key
	 * de DDBB, el String nombre del usuario y true si es manager
	 * y realiza las acciones pertinentes
	 ********************************************************* */
	
	public ContEmpresa(String datosEmpmain[], String datosUsuario[]){	
		
		// asignamos a variables de clase los datos recibidos
		this.datosUsuario=datosUsuario;
		datosEmp=datosEmpmain;
		keyEmpr=datosEmpmain[1];
		userCat =(int)Integer.parseInt(datosUsuario[6]);
		this.keyUser=datosUsuario[1];
		
		
		// primero construimos el panel para consultar
		// *******************************************
		
		consultarPanel(datosEmp,datosUsuario[2]);
		
		// segundo construimos el panel para modificar-borrar
		// **************************************************
		
		modificarPanel(datosEmp,datosUsuario[2],userCat);

		// tercero construimos el panel para creación nuevas empresas
		// **********************************************************
		creaPanel(datosEmp,datosUsuario[2],userCat);

		// y finalmente agregamos los paneles al tabbedPane
		panTab=panelTabulado(panelUsu,"Consultar",panelUsu2,"Modificar",panelUsu3,"Creación");
			
		// finalmente añadimos los listener
		imprimir.addActionListener(this);
		modificar.addActionListener(this);
		eliminar.addActionListener(this);
		crear.addActionListener(this);
		d9B.addActionListener(this);
		d9C.addActionListener(this);
		
	} //fin del constructor
	

	public ContEmpresa (){
		// constructor
	}
	
	
	
	/* ********************************************************************
	 * el accionPerformed lee los botones pulsados
	 * cada uno de los botones fabrica y muestra un jdialog independiente
	 * con la accion correspondiente realizada
	 ******************************************************************* */
	
	public void actionPerformed(ActionEvent e) {
		
		Object source=e.getSource();
		
		if (source==imprimir) {
			
			// abre una pantalla para imprimir en un JDialog
			
			JDialog listado=new JDialog(ventanaListado,"Listado de datos de Empresa",false);
			listado.setSize(900,600);
			listado.setLocationRelativeTo(rootPane);
			listado.setResizable(true);
			listado.setBackground(ColorBlanco);

			listado.getContentPane().setBackground(ColorBlanco);
			tablaDatos.setBackground(ColorBlanco);
			panelUsu.setBackground(ColorBlanco);
			listado.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
			
			listado.add(panelUsu);
			listado.setVisible(true);
			
		}
		
		if (source==modificar) {
			// añade los datos modificables de la pantalla visual 
			// a la tabla de datos y luego la graba en la DDBB
			if (aceptaModif()) {
				if (grabaModif()) {
					JOptionPane.showMessageDialog(null, "Empresa modificada");
				} else {
					JOptionPane.showMessageDialog(null, "Error en modificación de empresa");
				}
			}
			
		}
		
		if (source==eliminar) {
			// añade los datos modificables de la pantalla visual 
			// a la tabla de datos y luego la graba en la DDBB
			int resp=JOptionPane.showConfirmDialog(null, "¿Está seguro de querer borrar definitivamente la empresa?\n" +
					"No será posible recuperar los datos y desaparecerá toda la información","",JOptionPane.YES_NO_OPTION);
			if (resp==0 && verificaBorrado()) {
					if (borraEmpresa(this.keyEmpr)) {
						JOptionPane.showMessageDialog(null, "Empresa eliminada completamente");
					} else {
						JOptionPane.showMessageDialog(null, "No ha sido posible borrar la empresa");
					}
			}
			
		}
		
		if (source==crear) {
			// añade a la DDBB una nueva empresa
			// comprueba que cumple los requisitos para crear empresas
			if (cumpleRequisitos(this.keyUser,this.userCat)){
				// revisa si datos son OK y prepara tabla para grabar
				if (preparaGrabEmp()) {
					// graba los datos si no hay problemas
					if (grabaNuevaEmpr()) {
						JOptionPane.showMessageDialog(null,"Empresa creada","Creación de empresas",JOptionPane.INFORMATION_MESSAGE);
					} else {
						JOptionPane.showMessageDialog(null, "Error en creación de empresa");
					}
				}
			} else JOptionPane.showMessageDialog(null, "No es posible crear más empresas");
		}
		
		if (source==d9B) {
			// modificacion de empresas:
			d9B.getSelectedItem().toString();
		}
		
		if (source==d9C) {
			// creacion de empresas:
			d9C.getSelectedItem().toString();
		}
		
	} // fin del actionPerformed
	
	
	
	/* ****************************************************************
	 * el metodo retorna es el que devuelve a la pantalla principal
	 * el tabbedPane fabricado y listo para ser visualizado
	 * no recibe argumentos, y devuelve un jtabbedPane
	 ************************************************************** */

	public JTabbedPane retorna() {
		return panTab;
	}
	

	
	/* ******************************************************************************
	 * Este metodo crea un tabbedpane de tres pestañas con la informacion recibida
	 * recibe tres JPanel con sus string titulos correspondientes
	 * devuelve un objeto tabbedpane
	 **************************************************************************** */
	
	public JTabbedPane panelTabulado(JPanel tab1,String title1,JPanel tab2,String title2,JPanel tab3,String title3) {
		
		setTitle("");
		JTabbedPane menu=new JTabbedPane();
		menu.setBackground(ColorFondo);
		menu.addTab(title1,tab1);
		menu.addTab(title2,tab2);
		menu.addTab(title3,tab3);
		getContentPane().add(menu);
		if (userCat!=1) menu.setEnabledAt(2, false);
		return menu;
		
	} // fin del metodo panelTabulado
	
	
	
	/* ************************************************************************
	 * Este metodo permite la consulta de la tabla datos empresa
	 * Ningun dato puede ser modificado
	 * Se reciben un string[] con datos de empresa, y un String con el manager
	 * Este metodo no tiene valor de retorno
	 ***************************************************************************/
	
	private void consultarPanel (String datosEmp[], String nameUsuario) {
		
		// creacion del panel principal
		panelUsu=new JPanel();
		panelUsu.setLayout(new BoxLayout(panelUsu,BoxLayout.Y_AXIS));
		panelUsu.setAlignmentY(CENTER_ALIGNMENT);
		panelUsu.setBackground(ColorFondo);
		
		// titulo
		JPanel north1=new JPanel();
		north1.setLayout(new FlowLayout());
		north1.setBackground(ColorFondo);
	    // cambiar el font al titulo
	    n1.setFont(Fuente1);
		north1.add(n1);
		
		// creacion del panel rejilla datos
		JPanel panelUsuAux = new JPanel();
		panelUsuAux.setLayout(new GridLayout(10,2));
		panelUsuAux.setAlignmentY(CENTER_ALIGNMENT);
		panelUsuAux.setFont(Fuente2);
		panelUsuAux.setBackground(ColorFondo);
		      
		// datos de la empresa activa actual
		d1.setText(datosEmp[2]);	// nombre empresa
		d2.setText(datosEmp[3]);	// direccion
		d3.setText(datosEmp[4]);	// localidad
		d4.setText(datosEmp[5]);	// provincia
		d5.setText(datosEmp[6]);	// codigo postal
		d6.setText(datosEmp[7]);	// cif	
		
		// fecha inicio ejercicio
		int mes1=(int)Integer.parseInt(datosEmp[8].substring(3, 5));
		String nameMes1="";
		switch (mes1){
			case 1: 
			nameMes1="Enero";
			break;
			case 2: 
			nameMes1="Febrero";
			break;
			case 3: 
			nameMes1="Marzo";
			break;
			case 4: 
			nameMes1="Abril";
			break;
			case 5: 
			nameMes1="Mayo";
			break;
			case 6: 
			nameMes1="Junio";
			break;
			case 7: 
			nameMes1="Julio";
			break;
			case 8: 
			nameMes1="Agosto";
			break;
			case 9: 
			nameMes1="Septiembre";
			break;
			case 10: 
			nameMes1="Octubre";
			break;
			case 11: 
			nameMes1="Noviembre";
			break;
			case 12: 
			nameMes1="Diciembre";
			break;
		}
		d7.setText(datosEmp[8].substring(0, 3)+nameMes1);	
		
		// fecha fin ejercicio
		mes1=(int)Integer.parseInt(datosEmp[9].substring(3, 5));
		nameMes1="";
		switch (mes1){
			case 1: 
			nameMes1="Enero";
			break;
			case 2: 
			nameMes1="Febrero";
			break;
			case 3: 
			nameMes1="Marzo";
			break;
			case 4: 
			nameMes1="Abril";
			break;
			case 5: 
			nameMes1="Mayo";
			break;
			case 6: 
			nameMes1="Junio";
			break;
			case 7: 
			nameMes1="Julio";
			break;
			case 8: 
			nameMes1="Agosto";
			break;
			case 9: 
			nameMes1="Septiembre";
			break;
			case 10: 
			nameMes1="Octubre";
			break;
			case 11: 
			nameMes1="Noviembre";
			break;
			case 12: 
			nameMes1="Diciembre";
			break;
		}
		d8.setText(datosEmp[9].substring(0, 3)+nameMes1);	
		
		d9.setText(datosEmp[10]);	// activa
		if (d9.getText().equals("0")) {
			d9.setText("Inactiva");
		} else {d9.setText("Activa");}
		d10.setText(nameUsuario);		// nombre manager
		
		// definicion de celdas no editables
		d1.setEditable(false);
		d2.setEditable(false);
		d3.setEditable(false);
		d4.setEditable(false);
		d5.setEditable(false);
		d6.setEditable(false);
		d7.setEditable(false);
		d8.setEditable(false);
		d9.setEditable(false);
		d10.setEditable(false);
		
		// add de componentes al panel rejilla
		panelUsuAux.add(l1);
		panelUsuAux.add(d1);
		panelUsuAux.add(l2);
		panelUsuAux.add(d2);
		panelUsuAux.add(l3);
		panelUsuAux.add(d3);
		panelUsuAux.add(l4);
		panelUsuAux.add(d4);
		panelUsuAux.add(l5);
		panelUsuAux.add(d5);
		panelUsuAux.add(l6);
		panelUsuAux.add(d6);
		panelUsuAux.add(l7);
		panelUsuAux.add(d7);
		panelUsuAux.add(l8);
		panelUsuAux.add(d8);
		panelUsuAux.add(l9);
		panelUsuAux.add(d9);
		panelUsuAux.add(l10);
		panelUsuAux.add(d10);
	
		// separadores en panel principal
		JLabel espacioA=new JLabel(" ");
		JLabel espacioB=new JLabel(" ");
		    
	    // creacion del panel inferior
	    JPanel south1=new JPanel();
	    south1.setBackground(ColorFondo);
	    imprimir=new JButton("Imprimir");
	    imprimir.setToolTipText("abre una pantalla aparte");
	    south1.add(imprimir);
	    
	    panelUsuAux.setVisible(true);
	    // agregamos componentes al panel principal
	    panelUsu.add(north1);
	    panelUsu.add(espacioA);
	    panelUsu.add(panelUsuAux);
	    panelUsu.add(espacioB);
	    panelUsu.add(south1);
		
	} // fin del metodo consultarPanel
	
	

	/* *************************************************************************
	 * Este metodo permite la modificacion de la tabla datos empresa
	 * Algunos datos no pueden ser modificados y son solo informativos
	 * Otros datos solo pueden ser modificados por el manager de la empresa
	 * Finalmente, otros datos son modificables por todos
	 * Se reciben un string[] con datos de empresa, String con el manager
	 * y un boolean si el usuario es el manager
	 * Este metodo no retorna, modifica directamente las variables de clase
	 ************************************************************************** */
	
	private void modificarPanel (String datosEmp[], String nameUsuario, int categoria) {
		
		// creacion del panel principal
		panelUsu2=new JPanel();
		panelUsu2.setLayout(new BoxLayout(panelUsu2,BoxLayout.Y_AXIS));
		panelUsu2.setAlignmentX(CENTER_ALIGNMENT);
		panelUsu2.setBackground(ColorFondo);
		
		// titulo
		JPanel north2=new JPanel();
		north2.setLayout(new FlowLayout());
		north2.setBackground(ColorFondo);
	    // cambiar el font al titulo
	    n2.setFont(Fuente1);
		north2.add(n2);
		
		// creacion del panel rejilla datos
		JPanel panelUsuAux2 = new JPanel();
		panelUsuAux2.setLayout(new GridLayout(10,3));
		panelUsuAux2.setAlignmentY(CENTER_ALIGNMENT);
		panelUsuAux2.setFont(Fuente2);
		panelUsuAux2.setBackground(ColorFondo);
		      
		// datos de la empresa activa actual
		d1B.setText(datosEmp[2]);	// nombre empresa
		d2B.setText(datosEmp[3]);	// direccion
		d3B.setText(datosEmp[4]);	// localidad
		d4B.setText(datosEmp[5]);	// provincia
		d5B.setText(datosEmp[6]);	// codigo postal
		d6B.setText(datosEmp[7]);	// cif
		
		// fecha inicio ejercicio
		int mes1=(int)Integer.parseInt(datosEmp[8].substring(3, 5));
		String nameMes1="";
		switch (mes1){
			case 1: 
			nameMes1="Enero";
			break;
			case 2: 
			nameMes1="Febrero";
			break;
			case 3: 
			nameMes1="Marzo";
			break;
			case 4: 
			nameMes1="Abril";
			break;
			case 5: 
			nameMes1="Mayo";
			break;
			case 6: 
			nameMes1="Junio";
			break;
			case 7: 
			nameMes1="Julio";
			break;
			case 8: 
			nameMes1="Agosto";
			break;
			case 9: 
			nameMes1="Septiembre";
			break;
			case 10: 
			nameMes1="Octubre";
			break;
			case 11: 
			nameMes1="Noviembre";
			break;
			case 12: 
			nameMes1="Diciembre";
			break;
		}
		d7B.setText(datosEmp[8].substring(0, 3)+nameMes1);	
		
		// fecha fin ejercicio
		mes1=(int)Integer.parseInt(datosEmp[9].substring(3, 5));
		nameMes1="";
		switch (mes1){
			case 1: 
			nameMes1="Enero";
			break;
			case 2: 
			nameMes1="Febrero";
			break;
			case 3: 
			nameMes1="Marzo";
			break;
			case 4: 
			nameMes1="Abril";
			break;
			case 5: 
			nameMes1="Mayo";
			break;
			case 6: 
			nameMes1="Junio";
			break;
			case 7: 
			nameMes1="Julio";
			break;
			case 8: 
			nameMes1="Agosto";
			break;
			case 9: 
			nameMes1="Septiembre";
			break;
			case 10: 
			nameMes1="Octubre";
			break;
			case 11: 
			nameMes1="Noviembre";
			break;
			case 12: 
			nameMes1="Diciembre";
			break;
		}
		d8B.setText(datosEmp[9].substring(0, 3)+nameMes1);
		
		
		
		// actividad
		d9B.addItem("Activa");
		d9B.addItem("Inactiva");
		if (datosEmp[10].equals("0")) {
			d9B.setSelectedIndex(1);
		} else d9B.setSelectedIndex(0);

		d10B.setText(nameUsuario);		// nombre manager
		
		// definicion de celdas no editables
		d7B.setEditable(false);
		d8B.setEditable(false);
		d10B.setEditable(false);
		if (categoria!=1) {
			// los usuarios no manager tampoco puede modificar estas
			d1B.setEditable(false);
			d6B.setEditable(false);
			d9B.setEnabled(false);
		}
		
		// elaboracion de iconos
		abc=new ImageIcon(PathImageFiles+"warning.jpg").getImage();
		iconoW=new ImageIcon(abc.getScaledInstance(20,15,java.awt.Image.SCALE_SMOOTH));
		abc=new ImageIcon(PathImageFiles+"enable.jpg").getImage();
		iconoE=new ImageIcon(abc.getScaledInstance(20,15,java.awt.Image.SCALE_SMOOTH));
		abc=new ImageIcon(PathImageFiles+"disable.jpg").getImage();
		iconoD=new ImageIcon(abc.getScaledInstance(20,15,java.awt.Image.SCALE_SMOOTH));
		
		// definicion de comentarios edicion
		int tabulacion;
		if (categoria==1) {
			tabulacion=JLabel.LEFT;
		} else {
			tabulacion=JLabel.CENTER;
		}
		JLabel sp1=new JLabel("solo manager",iconoW,tabulacion);
		JLabel sp2=new JLabel("permitido",iconoE,JLabel.LEFT);
		JLabel sp3=new JLabel("permitido",iconoE,JLabel.LEFT);
		JLabel sp4=new JLabel("permitido",iconoE,JLabel.LEFT);
		JLabel sp5=new JLabel("permitido",iconoE,JLabel.LEFT);
		JLabel sp6=new JLabel("solo manager",iconoW,tabulacion);
		JLabel sp7=new JLabel("no permitido",iconoD,JLabel.CENTER);
		JLabel sp8=new JLabel("no permitido",iconoD,JLabel.CENTER);
		JLabel sp9=new JLabel("solo manager",iconoW,tabulacion);
		JLabel sp10=new JLabel("no permitido",iconoD,JLabel.CENTER);
		
		// add de componentes al panel rejilla
		panelUsuAux2.add(l1B);
		panelUsuAux2.add(d1B);
		panelUsuAux2.add(sp1);
		panelUsuAux2.add(l2B);
		panelUsuAux2.add(d2B);
		panelUsuAux2.add(sp2);
		panelUsuAux2.add(l3B);
		panelUsuAux2.add(d3B);
		panelUsuAux2.add(sp3);
		panelUsuAux2.add(l4B);
		panelUsuAux2.add(d4B);
		panelUsuAux2.add(sp4);
		panelUsuAux2.add(l5B);
		panelUsuAux2.add(d5B);
		panelUsuAux2.add(sp5);
		panelUsuAux2.add(l6B);
		panelUsuAux2.add(d6B);
		panelUsuAux2.add(sp6);
		panelUsuAux2.add(l7B);
		panelUsuAux2.add(d7B);
		panelUsuAux2.add(sp7);
		panelUsuAux2.add(l8B);
		panelUsuAux2.add(d8B);
		panelUsuAux2.add(sp8);
		panelUsuAux2.add(l9B);
		panelUsuAux2.add(d9B);
		panelUsuAux2.add(sp9);
		panelUsuAux2.add(l10B);
		panelUsuAux2.add(d10B);
		panelUsuAux2.add(sp10);
		
		// separadores en panel principal
		JLabel espacio1=new JLabel(" ");
		JLabel espacio2=new JLabel(" ");
	    
	    // creacion del panel de botones inferior
	    JPanel south2=new JPanel();
	    south2.setBackground(ColorFondo);
	    modificar=new JButton("Modificar");
	    modificar.setToolTipText("guarda los datos actuales");
	    eliminar=new JButton("Eliminar");
	    eliminar.setToolTipText("borra definitivamente la empresa");
	    if (categoria!=1) {
	    	// si no eres manager no puede borrar la empresa
	    	eliminar.setEnabled(false);
	    	eliminar.setToolTipText("solo permitido al manager");
	    }
	    south2.add(modificar);
	    south2.add(eliminar);
	    
	    // agregamos componentes al panel principal
	    panelUsu2.add(north2);
	    panelUsu2.add(espacio1);
	    panelUsu2.add(panelUsuAux2);
	    panelUsu2.add(espacio2);
	    panelUsu2.add(south2);
		
		
	} // fin del metodo modificarPanel
	
	
	
	/* ************************************************************************
	 * Este metodo permite la consulta de la tabla datos empresa
	 * Ningun dato puede ser modificado
	 * Se reciben un string[] con datos de empresa, String nombre de usuario y
	 * un int con la categoria del usuario (solo cat=1 puede crear)
	 * Este metodo no tiene valor de retorno
	 ***************************************************************************/
	
	private void creaPanel (String datosEmp[], String nameUsuario, int userCat) {
		
		// creacion del panel principal
		panelUsu3=new JPanel();
		panelUsu3.setLayout(new BoxLayout(panelUsu3,BoxLayout.Y_AXIS));
		panelUsu3.setAlignmentY(CENTER_ALIGNMENT);
		panelUsu3.setBackground(ColorFondo);
		
		// titulo
		JPanel north3=new JPanel();
		north3.setLayout(new FlowLayout());
		north3.setBackground(ColorFondo);
	    // cambiar el font al titulo
	    n3.setFont(Fuente1);
		north3.add(n3);
		
		// creacion del panel rejilla datos
		JPanel panelUsuAux3 = new JPanel();
		panelUsuAux3.setLayout(new GridLayout(10,2));
		panelUsuAux3.setAlignmentY(CENTER_ALIGNMENT);
		panelUsuAux3.setFont(Fuente2);
		panelUsuAux3.setBackground(ColorFondo);
		      
		// datos de la empresa activa actual
		d1C.setText("");	// nombre empresa
		d2C.setText("");	// direccion
		d3C.setText("");	// localidad
		d4C.setText("");	// provincia
		d5C.setText("");	// codigo postal
		d6C.setText("");	// cif
		d7C.setText("01-01");	// fecha inicio ejercicio
		d8C.setText("31-12");	// fecha fin ejercicio
		d9C.addItem("Activa");
		d9C.addItem("Inactiva");
		d10C.setText(nameUsuario);		// nombre manager
		d10C.setEnabled(false);
				
		abc=new ImageIcon(PathImageFiles+"warning.jpg").getImage();
		iconoW=new ImageIcon(abc.getScaledInstance(20,15,java.awt.Image.SCALE_SMOOTH));
		
		// definicion de comentarios edicion
		JLabel sc1=new JLabel("máx. longitud 40",iconoW,JLabel.LEFT);
		sc1.setToolTipText("Campo obligatorio");
		JLabel sc2=new JLabel("máx. longitud 50");
		JLabel sc3=new JLabel("máx. longitud 50");
		JLabel sc4=new JLabel("máx. longitud 20");
		JLabel sc5=new JLabel("longitud obligatoria 5",iconoW,JLabel.LEFT);
		sc5.setToolTipText("Campo obligatorio");
		JLabel sc6=new JLabel("longitud obligatoria 9",iconoW,JLabel.LEFT);
		sc6.setToolTipText("Campo obligatorio");
		JLabel sc7=new JLabel("Formato obligatorio DD-MM",iconoW,JLabel.LEFT);
		sc7.setToolTipText("Campo obligatorio");
		JLabel sc8=new JLabel("Formato obligatorio DD-MM",iconoW,JLabel.LEFT);
		sc8.setToolTipText("Campo obligatorio");
		JLabel sc9=new JLabel("Activa o Inactiva");
		JLabel sc10=new JLabel(" ");
		
		
		// add de componentes al panel rejilla
		panelUsuAux3.add(l1C);
		panelUsuAux3.add(d1C);
		panelUsuAux3.add(sc1);
		panelUsuAux3.add(l2C);
		panelUsuAux3.add(d2C);
		panelUsuAux3.add(sc2);
		panelUsuAux3.add(l3C);
		panelUsuAux3.add(d3C);
		panelUsuAux3.add(sc3);
		panelUsuAux3.add(l4C);
		panelUsuAux3.add(d4C);
		panelUsuAux3.add(sc4);
		panelUsuAux3.add(l5C);
		panelUsuAux3.add(d5C);
		panelUsuAux3.add(sc5);
		panelUsuAux3.add(l6C);
		panelUsuAux3.add(d6C);
		panelUsuAux3.add(sc6);
		panelUsuAux3.add(l7C);
		panelUsuAux3.add(d7C);
		panelUsuAux3.add(sc7);
		panelUsuAux3.add(l8C);
		panelUsuAux3.add(d8C);
		panelUsuAux3.add(sc8);
		panelUsuAux3.add(l9C);
		panelUsuAux3.add(d9C);
		panelUsuAux3.add(sc9);
		panelUsuAux3.add(l10C);
		panelUsuAux3.add(d10C);
		panelUsuAux3.add(sc10);
	
		// separadores en panel principal
		JLabel espacioY=new JLabel(" ");
		JLabel espacioZ=new JLabel(" ");
	    
	    // creacion del panel inferior
	    JPanel south3=new JPanel();
	    south3.setBackground(ColorFondo);
	    crear=new JButton("Crear empresa");
	    crear.setToolTipText("crea una empresa con los datos introducidos");
	    if (userCat!=1) {
	    	crear.setEnabled(false);
	    	crear.setToolTipText("Solo pueden crear empresas los managers");
	    }
	    south3.add(crear);
	    

	    // agregamos componentes al panel principal
	    panelUsu3.add(north3);
	    panelUsu3.add(espacioY);
	    panelUsu3.add(panelUsuAux3);
	    panelUsu3.add(espacioZ);
	    panelUsu3.add(south3);
		
		
	} // fin del metodo creaTabla
	
	
	
	/* ************************************************************************
	 * Este metodo realiza la modificacion de la tabla datos empresa
	 * Actuando sobre el string[] con los datos de empresa
	 * Este metodo no tiene parametros y no tiene valor de retorno
	 ************************************************************************** */
	
	private boolean aceptaModif() {
		
		// datos de la empresa actualizados a las casillas editables
		
		// reseteamos los posible resaltes backgrounds de errores anteriores
				d1B.setBackground(Color.WHITE);
				d2B.setBackground(Color.WHITE);
				d3B.setBackground(Color.WHITE);
				d4B.setBackground(Color.WHITE);
				d5B.setBackground(Color.WHITE);
				d6B.setBackground(Color.WHITE);

				// ************** nombre empresa ******************* 
				datosEmp[2]=d1B.getText();		// nombre empresa
							
				if ((datosEmp[2].equals("") || datosEmp[2].equals(null))) {
					JOptionPane.showMessageDialog(null, "nombre de empresa no rellenado");
					d1B.setBackground(Color.ORANGE);
					return false;
				}
				if (datosEmp[2].length()<3 || datosEmp[2].length()>40) {
					JOptionPane.showMessageDialog(null, "nombre de empresa de longitud inadecuada" );
					d1B.setBackground(Color.ORANGE);
					return false;
				}
				// ************** direccion ******************* 
				datosEmp[3]=d2B.getText();		// direccion		

				if (datosEmp[3].length()>50) {
					JOptionPane.showMessageDialog(null, "La dirección tiene longitud inadecuada" );
					d2B.setBackground(Color.ORANGE);
					return false;
				}
				// ************** localidad ******************* 
				datosEmp[4]=d3B.getText();		// localidad
				if (datosEmp[4].length()>50) {
					JOptionPane.showMessageDialog(null, "La localidad tiene longitud inadecuada" );
					d3B.setBackground(Color.ORANGE);
					return false;
				}
				
				// ************** provincia ******************* 
				datosEmp[5]=d4B.getText();		// provincia		
				if (datosEmp[5].length()>20) {
					JOptionPane.showMessageDialog(null, "La provincia tiene longitud inadecuada" );
					d4B.setBackground(Color.ORANGE);
					return false;
				}
				// ************** codigo postal ******************* 
				datosEmp[6]=d5B.getText();		// codigo postal
				if (datosEmp[6].length()>5) {
					JOptionPane.showMessageDialog(null, "El codigo postal debe tener 5 numeros max." );
					d5B.setBackground(Color.ORANGE);
					return false;
				}
				// ************** NIF ******************* 
				datosEmp[7]=d6B.getText();		// nif
							
				if ((datosEmp[7].equals("") || datosEmp[7].equals(null))) {
					JOptionPane.showMessageDialog(null, "Es obligatorio rellenar el NIF");
					d6B.setBackground(Color.ORANGE);
					return false;
				}
				if (datosEmp[7].length()!=9) {
					JOptionPane.showMessageDialog(null, "El NIF debe tener exactamente 9 dígitos" );
					d6B.setBackground(Color.ORANGE);
					return false;
				}
		
		if (d9B.getSelectedItem().toString().equals("Activa")) {
			datosEmp[10]="1";					// activa
		} else datosEmp[10]="0";	
			
		d1.setText(datosEmp[2]);
		d2.setText(datosEmp[3]);
		d3.setText(datosEmp[4]);
		d4.setText(datosEmp[5]);
		d5.setText(datosEmp[6]);
		d6.setText(datosEmp[7]);
		if (datosEmp[10].equals("1")) {
			d9.setText("Activa");
		} else d9.setText("Inactiva");
		

		return true;
		
	} // fin del metodo aceptaModif
	
	
	
	/* ************************************************************************
	 * Este metodo graba la modificacion de la tabla empresa en la DDBB
	 * 
	 * Este metodo no tiene parametros y devuelve valor de retorno true false
	 * segun haya ido la grabacion
	 ************************************************************************** */

	private boolean grabaModif() {
		
		// Instanciamos el metodo DAO para efectuar la grabacion
		
		dao=new ContaDAO();
		
		// graba los datos residentes en datosEmp en la DDBB con key keyEmpr
		if (dao.grabaEmpDB (keyEmpr, datosEmp, "UPDATE")){
			
		} else {
			// fallo al intentar grabar la empresa
			System.err.println("Fallo al intentar modificar los datos de empresa ");
			return false;
		}
		
		return true;
		
	} // fin del metodo grabaModif
	
	
	
	/* **************************************************************************
	 * Este metodo comprueba el cumplimiento de los requisitos para crear empresa
	 * El primer requisito es tener categoria manager = 1
	 * EL segundo requisito es no poder crear mas de 3 empresas
	 * retorna true o false segun cumpla o no los requisitos
	 ************************************************************************** */
	
	private boolean cumpleRequisitos(String keyUsuario, int categoria) {
		
		// si no es un manager no puede crear empresa
		if (categoria != 1) return false;
		
		// si tiene sitios vacios es que puede crear empresas
		if (this.datosUsuario[8].equals("") || this.datosUsuario[9].equals("")) {
			return true;
		}
		
		return false;
	} // FIN del metodo cumplerequisitos
	
	
	
	/* ************************************************************************
	 * Este metodo realiza la preparacion de la tabla empresa
	 * Actuando sobre el string[] con los datos de empresa
	 * Este metodo no recibe parametros
	 * retorna true o false segun todo este correcto o no
	 ************************************************************************** */
	
	private boolean preparaGrabEmp() {
		
		// elabora la tabla de empresa para grabacion en DDBB
		creaEmp=new String[12];
		
		// se limpian los avisos visuales de error
		d1C.setBackground(Color.WHITE);
		d2C.setBackground(Color.WHITE);
		d3C.setBackground(Color.WHITE);
		d4C.setBackground(Color.WHITE);
		d5C.setBackground(Color.WHITE);
		d6C.setBackground(Color.WHITE);
		d7C.setBackground(Color.WHITE);
		d8C.setBackground(Color.WHITE);
		d9C.setBackground(Color.WHITE);
		
		// creaEmp[0]=d1B.getText();		es autoincrement

		// Revision del nombre de empresa
		if (d1C.getText().length()<3 || d1C.getText().length()>40) {
			JOptionPane.showMessageDialog(null, "Nombre de empresa de longitud inadecuada");
			d1C.setBackground(Color.ORANGE);
			return false;
		} else creaEmp[2]=d1C.getText();		// nombre empresa
		
		// componemos el key empresa con las dos primeras letras de la empresa
		// mas un numero aleatorio de 3 cifras
		String aleatorio=String.valueOf(100+(int)(Math.floor((Math.random()*898)+1)));
		creaEmp[1]=creaEmp[2].substring(0, 2)+aleatorio;
		
		if (d2C.getText().length()>50) {
			JOptionPane.showMessageDialog(null, "Dirección tiene más de 50 caracteres");
			d2C.setBackground(Color.ORANGE);
			return false;
		} else creaEmp[3]=d2C.getText();		// direccion
		
		if (d3C.getText().length()>50) {
			JOptionPane.showMessageDialog(null, "Localidad tiene más de 50 caracteres");
			d3C.setBackground(Color.ORANGE);
			return false;
		} else creaEmp[4]=d3C.getText();		// localidad
		
		if (d4C.getText().length()>20) {
			JOptionPane.showMessageDialog(null, "Provincia tiene más de 20 caracteres");
			d4C.setBackground(Color.ORANGE);
			return false;
		} else creaEmp[5]=d4C.getText();		// provincia
		
		if (d5C.getText().length()!=5) {
			JOptionPane.showMessageDialog(null, "Código postal debe tener 5 caracteres");
			d5C.setBackground(Color.ORANGE);
			return false;
		} else creaEmp[6]=d5C.getText();		// codigo postal
		
		if (d6C.getText().length()!=9) {
			JOptionPane.showMessageDialog(null, "N.I.F. debe tener 9 caracteres");
			d6C.setBackground(Color.ORANGE);
			return false;
		} else creaEmp[7]=d6C.getText();		// cif
		

		
		// coge el año actual
		Calendar dat=Calendar.getInstance();
		int dato=dat.get(Calendar.YEAR);
		String yearAct=String.valueOf(dato);
		
		
		if (d7C.getText().length()!=5) {
			JOptionPane.showMessageDialog(null, "Formato fecha inicial: DD-MM 5 digitos obligatorio");
			d7C.setBackground(Color.ORANGE);
			return false;
		} 
		
		String dig1=d7C.getText().substring(0,1);
		String dig2=d7C.getText().substring(1,2);
		String dig3=d7C.getText().substring(3,4);
		String dig4=d7C.getText().substring(4,5);
		
		if (!(dig1.equals("0") || dig1.equals("1") || dig1.equals("2") || dig1.equals("3"))) {
			JOptionPane.showMessageDialog(null, "Formato fecha inicial: DD-MM 1");
			d7C.setBackground(Color.ORANGE);
			return false;
		}
		if (!(dig2.equals("0") || dig2.equals("1") || dig2.equals("2") || dig2.equals("3") ||
				dig2.equals("4") || dig2.equals("5") || dig2.equals("6") || dig2.equals("7") ||
				dig2.equals("8") || dig2.equals("9"))) {
			JOptionPane.showMessageDialog(null, "Formato fecha inicial: DD-MM 2");
			d7C.setBackground(Color.ORANGE);
			return false;
		}
		if (!(dig3.equals("0") || dig3.equals("1"))) {
			JOptionPane.showMessageDialog(null, "Formato fecha inicial: DD-MM 3");
			d7C.setBackground(Color.ORANGE);
			return false;
		}
		if (!(dig4.equals("0") || dig4.equals("1") || dig4.equals("2") || dig4.equals("3") ||
				dig4.equals("4") || dig4.equals("5") || dig4.equals("6") || dig4.equals("7") ||
				dig4.equals("8") || dig4.equals("9"))) {
			JOptionPane.showMessageDialog(null, "Formato fecha inicial: DD-MM 4");
			d7C.setBackground(Color.ORANGE);
			return false;
		}
		
		// comprobacion de correccion de fechas
		int dia=(int)Integer.parseInt(d7C.getText().substring(0,2));
		int mes=(int)Integer.parseInt(d7C.getText().substring(3,5));
		
		if (dia<1 || dia>31) {
			JOptionPane.showMessageDialog(null, "El día "+dia+" no es correcto");
			d7C.setBackground(Color.ORANGE);
			return false;
		}
		
		if (mes<1 || mes>12) {
			JOptionPane.showMessageDialog(null, "El mes "+mes+" no es correcto");
			d7C.setBackground(Color.ORANGE);
			return false;
		}
		
		if (mes==2 && dia>29) {
			JOptionPane.showMessageDialog(null, "La fecha "+dia+"-"+mes+" no es correcta");
			d7C.setBackground(Color.ORANGE);
			return false;
		}
		
		if ((mes==4 || mes==6 || mes==9 || mes==11 ) && dia>30) {
			JOptionPane.showMessageDialog(null, "La fecha "+dia+"-"+mes+" no es correcta");
			d7C.setBackground(Color.ORANGE);
			return false;
		}
		
		// si llega aqui ha pasado el text :)
		creaEmp[8]=d7C.getText()+"-"+yearAct;		// fecha inicio
		
		
		
		if (d8C.getText().length()!=5) {
			JOptionPane.showMessageDialog(null, "Formato fecha final: DD-MM 5 digitos obligatorio");
			d8C.setBackground(Color.ORANGE);
			return false;
		} 
		
		dig1=d8C.getText().substring(0,1);
		dig2=d8C.getText().substring(1,2);
		dig3=d8C.getText().substring(3,4);
		dig4=d8C.getText().substring(4,5);
		
		if (!(dig1.equals("0") || dig1.equals("1") || dig1.equals("2") || dig1.equals("3"))) {
			JOptionPane.showMessageDialog(null, "Formato fecha final: DD-MM");
			d8C.setBackground(Color.ORANGE);
			return false;
		}
		if (!(dig2.equals("0") || dig2.equals("1") || dig2.equals("2") || dig2.equals("3") ||
				dig2.equals("4") || dig2.equals("5") || dig2.equals("6") || dig2.equals("7") ||
				dig2.equals("8") || dig2.equals("9"))) {
			JOptionPane.showMessageDialog(null, "Formato fecha final: DD-MM");
			d8C.setBackground(Color.ORANGE);
			return false;
		}
		if (!(dig3.equals("0") || dig3.equals("1"))) {
			JOptionPane.showMessageDialog(null, "Formato fecha final: DD-MM");
			d8C.setBackground(Color.ORANGE);
			return false;
		}
		if (!(dig4.equals("0") || dig4.equals("1") || dig4.equals("2") || dig4.equals("3") ||
				dig4.equals("4") || dig4.equals("5") || dig4.equals("6") || dig4.equals("7") ||
				dig4.equals("8") || dig4.equals("9"))) {
			JOptionPane.showMessageDialog(null, "Formato fecha final: DD-MM");
			d8C.setBackground(Color.ORANGE);
			return false;
		}
		
		// comprobacion de correccion de fechas
		dia=(int)Integer.parseInt(d8C.getText().substring(0,2));
		mes=(int)Integer.parseInt(d8C.getText().substring(3,5));
		
		if (dia<1 || dia>31) {
			JOptionPane.showMessageDialog(null, "El día "+dia+" no es correcto");
			d8C.setBackground(Color.ORANGE);
			return false;
		}
		
		if (mes <1 || mes>12) {
			JOptionPane.showMessageDialog(null,"El mes "+mes+" no es correcto");
			d8C.setBackground(Color.ORANGE);
			return false;
		}
		
		if (mes==2 && dia>29) {
			JOptionPane.showMessageDialog(null, "La fecha "+dia+"-"+mes+" no es correcta");
			d8C.setBackground(Color.ORANGE);
			return false;
		}
		
		if ((mes==4 || mes==6 || mes==9 || mes==11 ) && dia>30) {
			JOptionPane.showMessageDialog(null, "La fecha "+dia+"-"+mes+" no es correcta");
			d8C.setBackground(Color.ORANGE);
			return false;
		}	
		
		// si llega aqui ha pasado el text :)
		creaEmp[9]=d8C.getText()+"-"+yearAct;		// fecha fin

		
		if (d9C.getSelectedItem().toString().equals("Activa")) {
			creaEmp[10]="1";					// activa
		} else creaEmp[10]="0";	
				
		creaEmp[11]=keyUser;			// key manager

		return true;
		
	} // fin del metodo preparaGrabEmp
	
	
	
	/* ************************************************************************
	 * Este metodo graba nueva empresa de la tabla empresa en la DDBB
	 * 
	 * Este metodo no tiene parametros y devuelve valor de retorno true false
	 * segun haya ido la grabacion
	 ************************************************************************** */

	private boolean grabaNuevaEmpr() {
		
		// instanciamos un nuevo contaDAO
		daoN=new ContaDAO();
		
		// graba los datos residentes en datosEmp en la DDBB con key keyEmpr
		if (this.daoN.grabaEmpDB (creaEmp[1], creaEmp, "INSERT")){
			
		} else {
			// fallo al intentar grabar la empresa
			System.err.println("Fallo al intentar grabar la nueva empresa ");
			return false;
			}
		
		// graba el dato de la nueva empresa creada en el user
		// primero define el registro donde grabar
		// segun sea la tercera o segunda empresa
		int oper=9;
		if (this.datosUsuario[8].equals("")) oper=8;
		// suministra los datos y procede a grabar
		daoN.grabaEmpresaUsu(this.keyUser, creaEmp[1], oper);
		
		// finalmente crea los ficheros de empresa
		CompanyFiles makeFile=new CompanyFiles(creaEmp[1]);
		
		if (!makeFile.creaFilesCompany()) {
			// si algo falla con la creación de ficheros
			JOptionPane.showMessageDialog(null,"Se produjo un error al crear los ficheros","Error de grabación",JOptionPane.WARNING_MESSAGE);
			return false;
		}
		
		return true;
		
	} // fin del metodo grabanuevaempr
	
	
	
	/* ************************************************************************
	 * Este metodo comprueba si es posible borrar la empresa de la DDBB
	 * 
	 * Este metodo no recibe parametros
	 * Devuelve true si es posible borrar o false en caso contrario
	 ************************************************************************** */

	private boolean verificaBorrado() {
		
		// este metodo deberia implementar un metodo de comprobacion
		// y verificacion de los datos de la empresa
		// para comprobar si es posible borrarse
		
		// LAS CONDICIONES DEBERIAN SER:
		// A) NO ES POSIBLE BORRAR SI LA EMPRESA TIENE INFORMACION GRABADA
		//    Y ANTES DE BORRAR NO HA HECHO COPIA DE SEGURIDAD
		// B) NO ES POSIBLE BORRAR SI NO ERES EL MANAGER DE EMPRESA

		return true;
		
	} // fin del metodo verificaborrado
	
	
	
	/* ************************************************************************
	 * Este metodo borra completamente la empresa de la DDBB
	 * Ademas elimina todos los ficheros relativos a la empresa
	 * 
	 * Este metodo recibe un String con el keyEmpresa de la empresa
	 * Devuelve true si consigue borrar o false en caso contrario
	 ************************************************************************** */

	private boolean borraEmpresa(String keyEmpresaBorrar) {
		
		
		//Borrado en el fichero de empresas
		// y el borrado en los clientes
		
		dao=new ContaDAO();
		if (dao.eraseCompany(keyEmpresaBorrar)){
			// si no ha habido problemas con el borrado de la empresa
			// continuamos con el borrado de ficheros
			CompanyFiles borraFich=new CompanyFiles(keyEmpresaBorrar);
			// si el borrado de ficheros no genera problemas
			// retorna con un true
			if (borraFich.deleteFilesCompany()) {
				return true;	
			} else {
				JOptionPane.showMessageDialog(null,"Se produjo un error al borrar los ficheros","Error de borrado",JOptionPane.WARNING_MESSAGE);
			}
		}
		// sale con false en cualquier situacion de problemas	
		return false;
		
	} // fin del metodo borraEmpresa
	
	
	
	/* *****************************************************************************
	 * Esta clase interna crea la tabla con los datos de la empresa para mostrar
	 * la informacion a mostrar a sido previamente leida y modificada (en su caso)
	 * dentro del metodo que la llama
	 * la clase es una extension y los metodos devuelven la informacion y longitud
	 * de los datos de la tabla
	 * la informacion devuelve un tipo object
	 ******************************************************************************* */
	
	public class CreaTabla extends AbstractTableModel {
		
		String nombreColum[]={"Empresa","Datos"};
		
		Object info[][]= {
				{"Nombre empresa",d1.getText()},
				{"Dirección",d2.getText()},
				{"Localidad",d3.getText()},
				{"Provincia",d4.getText()},
				{"Cod.Postal",d5.getText()},
				{"N.I.F.",d6.getText()},
				{"Inicio Ejercicio",d7.getText()},
				{"Final Ejercicio",d8.getText()},
				{"Activa",d9.getText()},
				{"Usuario Manager",d10.getText()},
		};

		
		public int getRowCount() {
			// TODO Auto-generated method stub
			return info.length;
		}

		public int getColumnCount() {
			// TODO Auto-generated method stub
			return nombreColum.length;
		}

		public Object getValueAt(int rowIndex, int nombreColum) {
			// TODO Auto-generated method stub
			return info[rowIndex][nombreColum];
		}

	}  // FIN DE CREATABLA
	
	

}
