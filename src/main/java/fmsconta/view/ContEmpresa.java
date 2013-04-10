package fmsconta.view;

import java.awt.Color;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BoxLayout;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTabbedPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.ScrollPaneLayout;
import javax.swing.border.Border;
import javax.swing.table.AbstractTableModel;

import fmsconta.view.ContUsuario.CreaTabla;
import fmsconta.control.ContaDAO;

public class ContEmpresa extends JFrame implements ActionListener{
	
	
	// contenedores
	private JFrame ventanaListado;
	private JTabbedPane panTab;
	private JPanel panelUsu;
	private JPanel panelUsu2;
	private JPanel panelUsu3;
	
	// componentes
	private JTable tablaDatos;
	private JLabel n0=new JLabel("Consulta de datos de la empresa");
	private JLabel n1=new JLabel("Modificacion de los datos de la empresa");
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
	private JTextField d9B=new JTextField();
	private JTextField d10B=new JTextField();
	private JTextField d1C=new JTextField();
	private JTextField d2C=new JTextField();
	private JTextField d3C=new JTextField();
	private JTextField d4C=new JTextField();
	private JTextField d5C=new JTextField();
	private JTextField d6C=new JTextField();
	private JTextField d7C=new JTextField();
	private JTextField d8C=new JTextField();
	private JTextField d9C=new JTextField();
	private JTextField d10C=new JTextField();
	
	// colores, fuentes, graficos
	private Color colorfondo=new Color(220,220,220);
	private Color colorBlanco=new Color(255,255,255);
	private Font fuente1=new Font("",Font.BOLD,20);
	private Font fuente2=new Font("",Font.PLAIN,16);
	private int sizeLetras=24;
	private String pathImageFiles="src/main/java/fmsconta/pictures/";
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
	
	//public ContEmpresa(String datosEmpmain[], String keyEmpresa, String nameUsuario, boolean isManager, String categoria, String keyUser){	
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
		
	} //fin del builder
	

	public ContEmpresa (){
		// builder
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
			listado.setBackground(colorBlanco);

			listado.getContentPane().setBackground(colorBlanco);
			tablaDatos.setBackground(colorBlanco);
			panelUsu.setBackground(colorBlanco);
			listado.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
			
			listado.add(panelUsu);
			listado.setVisible(true);
			
		}
		
		if (source==modificar) {
			// añade los datos modificables de la pantalla visual 
			// a la tabla de datos y luego la graba en la DDBB
			aceptaModif();
			if (grabaModif()) {
				JOptionPane.showMessageDialog(null, "Empresa modificada");
			} else {
				JOptionPane.showMessageDialog(null, "Error en modificación de empresa");
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
						JOptionPane.showMessageDialog(null, "Empresa creada");
					} else {
						JOptionPane.showMessageDialog(null, "Error en creación de empresa");
					}
				}
			}
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
		menu.setBackground(colorfondo);
		menu.addTab(title1,tab1);
		menu.addTab(title2,tab2);
		menu.addTab(title3,tab3);
		getContentPane().add(menu);
		return menu;
		
	} // fin del metodo panelTabulado
	
	
	
	/* ************************************************************************
	 * Este metodo permite la consulta de la tabla datos empresa
	 * Ningun dato puede ser modificado
	 * Se reciben un string[] con datos de empresa, y un String con el manager
	 * Este metodo no tiene valor de retorno
	 ***************************************************************************/
	
	public void consultarPanel (String datosEmp[], String nameUsuario) {
		
		// creacion del panel principal
		panelUsu=new JPanel();
		panelUsu.setLayout(new BoxLayout(panelUsu,BoxLayout.Y_AXIS));
		panelUsu.setAlignmentY(CENTER_ALIGNMENT);
		panelUsu.setBackground(colorfondo);
		
		// creacion del panel rejilla datos
		JPanel panelUsuAux = new JPanel();
		panelUsuAux.setLayout(new GridLayout(10,2));
		panelUsuAux.setAlignmentY(CENTER_ALIGNMENT);
		panelUsuAux.setFont(fuente2);
		panelUsuAux.setBackground(colorfondo);
		      
		// datos de la empresa activa actual
		d1.setText(datosEmp[2]);	// nombre empresa
		d2.setText(datosEmp[3]);	// direccion
		d3.setText(datosEmp[4]);	// localidad
		d4.setText(datosEmp[5]);	// provincia
		d5.setText(datosEmp[6]);	// codigo postal
		d6.setText(datosEmp[7]);	// cif
		d7.setText(datosEmp[8]);	// fecha inicio ejercicio
		d8.setText(datosEmp[9]);	// fecha fin ejercicio
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
		
	    // cambiar el font al titulo
	    n0.setFont(fuente1);
	    
	    // creacion del panel inferior
	    JPanel south=new JPanel();
	    south.setBackground(colorfondo);
	    imprimir=new JButton("Imprimir");
	    imprimir.setToolTipText("abre una pantalla aparte");
	    south.add(imprimir);
	    
	    panelUsuAux.setVisible(true);
	    // agregamos componentes al panel principal
	    panelUsu.add(n0);
	    panelUsu.add(espacioA);
	    panelUsu.add(panelUsuAux);
	    panelUsu.add(espacioB);
	    panelUsu.add(south);
		
		
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
	
	public void modificarPanel (String datosEmp[], String nameUsuario, int categoria) {
		
		// creacion del panel principal
		panelUsu2=new JPanel();
		panelUsu2.setLayout(new BoxLayout(panelUsu2,BoxLayout.Y_AXIS));
		panelUsu2.setAlignmentX(CENTER_ALIGNMENT);
		panelUsu2.setBackground(colorfondo);
		
		// creacion del panel rejilla datos
		JPanel panelUsuAux2 = new JPanel();
		panelUsuAux2.setLayout(new GridLayout(10,3));
		panelUsuAux2.setAlignmentY(CENTER_ALIGNMENT);
		panelUsuAux2.setFont(fuente2);
		panelUsuAux2.setBackground(colorfondo);
		      
		// datos de la empresa activa actual
		d1B.setText(datosEmp[2]);	// nombre empresa
		d2B.setText(datosEmp[3]);	// direccion
		d3B.setText(datosEmp[4]);	// localidad
		d4B.setText(datosEmp[5]);	// provincia
		d5B.setText(datosEmp[6]);	// codigo postal
		d6B.setText(datosEmp[7]);	// cif
		d7B.setText(datosEmp[8]);	// fecha inicio ejercicio
		d8B.setText(datosEmp[9]);	// fecha fin ejercicio
		d9B.setText(datosEmp[10]);	// activa
		if (d9B.getText().equals("0")) {
			d9B.setText("Inactiva");
		} else {d9B.setText("Activa");}
		d10B.setText(nameUsuario);		// nombre manager
		
		// definicion de celdas no editables
		d7B.setEditable(false);
		d8B.setEditable(false);
		d10B.setEditable(false);
		if (categoria!=1) {
			// los usuarios no manager tampoco puede modificar estas
			d1B.setEditable(false);
			d6B.setEditable(false);
			d9B.setEditable(false);
		}
		
		// elaboracion de iconos
		abc=new ImageIcon(pathImageFiles+"warning.jpg").getImage();
		iconoW=new ImageIcon(abc.getScaledInstance(20,15,java.awt.Image.SCALE_SMOOTH));
		abc=new ImageIcon(pathImageFiles+"enable.jpg").getImage();
		iconoE=new ImageIcon(abc.getScaledInstance(20,15,java.awt.Image.SCALE_SMOOTH));
		abc=new ImageIcon(pathImageFiles+"disable.jpg").getImage();
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
		
	    // ajustamos fuente del titulo
	    n1.setFont(fuente1);
	    
	    // creacion del panel de botones inferior
	    JPanel south2=new JPanel();
	    south2.setBackground(colorfondo);
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
	    panelUsu2.add(n1);
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
	
	public void creaPanel (String datosEmp[], String nameUsuario, int userCat) {
		
		//datosEmpCreac[]=new String[12];
		
		// creacion del panel principal
		panelUsu3=new JPanel();
		panelUsu3.setLayout(new BoxLayout(panelUsu3,BoxLayout.Y_AXIS));
		panelUsu3.setAlignmentY(CENTER_ALIGNMENT);
		panelUsu3.setBackground(colorfondo);
		
		// creacion del panel rejilla datos
		JPanel panelUsuAux3 = new JPanel();
		panelUsuAux3.setLayout(new GridLayout(10,2));
		panelUsuAux3.setAlignmentY(CENTER_ALIGNMENT);
		panelUsuAux3.setFont(fuente2);
		panelUsuAux3.setBackground(colorfondo);
		      
		// datos de la empresa activa actual
		d1C.setText("");	// nombre empresa
		d2C.setText("");	// direccion
		d3C.setText("");	// localidad
		d4C.setText("");	// provincia
		d5C.setText("");	// codigo postal
		d6C.setText("");	// cif
		d7C.setText("");	// fecha inicio ejercicio
		d8C.setText("");	// fecha fin ejercicio
		d9C.setText("Inactiva");	// activa
		d10C.setText(nameUsuario);		// nombre manager
		d10C.setEnabled(false);
				
		abc=new ImageIcon(pathImageFiles+"warning.jpg").getImage();
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
		JLabel sc7=new JLabel("longitud obligatoria 8",iconoW,JLabel.LEFT);
		sc7.setToolTipText("Campo obligatorio");
		JLabel sc8=new JLabel("longitud obligatoria 8",iconoW,JLabel.LEFT);
		sc8.setToolTipText("Campo obligatorio");
		JLabel sc9=new JLabel("1-Activa 0-Inactiva");
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
		
	    // cambiar el font al titulo
	    n3.setFont(fuente1);
	    
	    // creacion del panel inferior
	    JPanel south3=new JPanel();
	    south3.setBackground(colorfondo);
	    crear=new JButton("Crear empresa");
	    crear.setToolTipText("crea una empresa con los datos introducidos");
	    if (userCat!=1) {
	    	crear.setEnabled(false);
	    	crear.setToolTipText("Solo pueden crear empresas los managers");
	    }
	    south3.add(crear);
	    

	    // agregamos componentes al panel principal
	    panelUsu3.add(n3);
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
	
	private void aceptaModif() {
		
		// datos de la empresa actualizados a las casillas editables
		
		datosEmp[2]=d1B.getText();		// nombre empresa
		datosEmp[3]=d2B.getText();		// direccion
		datosEmp[4]=d3B.getText();		// localidad
		datosEmp[5]=d4B.getText();		// provincia
		datosEmp[6]=d5B.getText();		// codigo postal
		datosEmp[7]=d6B.getText();		// cif
		if (d9B.getText().equals("Inactiva")) {
			datosEmp[10]="0";
		} else {datosEmp[10]="1";}		// activa
			
		for(int i=0;i<datosEmp.length;i++) {
		System.out.print(datosEmp[i]+"-");
		}
		
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
			System.out.println("alright");
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
	}
	
	
	/* ************************************************************************
	 * Este metodo realiza la preparacion de la tabla empresa
	 * Actuando sobre el string[] con los datos de empresa
	 * Este metodo no recibe parametros
	 * retorna true o false segun todo este correcto o no
	 ************************************************************************** */
	
	private boolean preparaGrabEmp() {
		
		int numAleat=(int) (Math.floor(Math.random()*898))+100;
		// elabora la tabla de empresa para grabacion en DDBB
		// creaEmp[0]=d1B.getText();		es autoincrement
		// componemos el key empresa con las dos primeras letras de la empresa
		// mas un numero aleatorio de 3 cifras
		System.out.println("aleatorio "+numAleat);
		//creaEmp[1]=d1C.getText().substring(0, 1)+String.valueOf(numAleat) ;		// key empresa
		creaEmp=new String[12];
		creaEmp[1]="AA003";
		if (d1C.getText().length()<3 || d1C.getText().length()>40) {
			JOptionPane.showMessageDialog(null, "Nombre de empresa de longitud inadecuada");
			return false;
		} else creaEmp[2]=d1C.getText();		// nombre empresa
		
		if (d2C.getText().length()>50) {
			JOptionPane.showMessageDialog(null, "Dirección tiene más de 50 caracteres");
			return false;
		} else creaEmp[3]=d2C.getText();		// direccion
		
		if (d3C.getText().length()>50) {
			JOptionPane.showMessageDialog(null, "Localidad tiene más de 50 caracteres");
			return false;
		} else creaEmp[4]=d3C.getText();		// localidad
		
		if (d4C.getText().length()>20) {
			JOptionPane.showMessageDialog(null, "Provincia tiene más de 20 caracteres");
			return false;
		} else creaEmp[5]=d4C.getText();		// provincia
		
		if (d5C.getText().length()!=5) {
			JOptionPane.showMessageDialog(null, "Código postal debe tener 5 caracteres");
			return false;
		} else creaEmp[6]=d5C.getText();		// codigo postal
		
		if (d6C.getText().length()!=9) {
			JOptionPane.showMessageDialog(null, "N.I.F. debe tener 9 caracteres");
			return false;
		} else creaEmp[7]=d6C.getText();		// cif
		
		if (d7C.getText().length()!=8) {
			JOptionPane.showMessageDialog(null, "Formato fecha inicial: XX-XX-XX");
			return false;
		} else creaEmp[8]=d7C.getText();		// fecha inicio
		
		if (d8C.getText().length()!=8) {
			JOptionPane.showMessageDialog(null, "Formato fecha final: XX-XX-XX");
			return false;
		} else creaEmp[9]=d8C.getText();		// fecha fin
		
		if (!(d9C.getText().equals("0") || d9C.getText().equals("1"))) {
			JOptionPane.showMessageDialog(null, "Introduzca 1 para activa, 0 para inactiva");
			return false;
		} else creaEmp[10]=d9C.getText();		// activa
				
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
			System.out.println("alright");
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
		
		return true;
		
	} // fin del metodo grabanuevaempr
	
	
	
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
