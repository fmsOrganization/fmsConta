package fmsconta.view;

import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BoxLayout;
import javax.swing.Icon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTabbedPane;
import javax.swing.JTable;
import javax.swing.JTextField;

import fmsconta.control.ContaDAO;

public class ContSettings extends JFrame implements ActionListener, Settings  {
	
	// contenedores
	private JFrame ventanaListado;
	private JTabbedPane panTab;
	private JPanel panelUsu;
	private JPanel panelUsu2;
	private JPanel panelUsu3;
	
	// componentes
	private JTable tablaDatos;
	private JLabel n1=new JLabel("Datos generales de la empresa");
	private JLabel n2=new JLabel("Datos de IVA de la empresa");
	private JLabel n3=new JLabel("Datos de IRPF de la empresa");
	private JLabel l1=new JLabel("     IVA 1 de facturación");
	private JLabel l2=new JLabel("     IVA 2 de facturación");
	private JLabel l3=new JLabel("     IVA 3 de facturación");
	private JLabel l4=new JLabel("     IRPF de facturación");
	private JLabel l5=new JLabel("     Longitud cuentas contables ");
	
	private JLabel l1B=new JLabel("     IVA clientes general vigente");
	private JLabel l2B=new JLabel("     IVA clientes reducido vigente");
	private JLabel l3B=new JLabel("     IVA clientes superreducido vigente ");

	private JLabel l1C=new JLabel("     IRPF facturación vigente ");
	private JLabel l2C=new JLabel("     IRPF proveedores vigente ");

	private JTextField d1=new JTextField();
	private JTextField d2=new JTextField();
	private JTextField d3=new JTextField();
	private JTextField d4=new JTextField();
	private JTextField d5=new JTextField();

	private JTextField d1B=new JTextField();
	private JTextField d2B=new JTextField();
	private JTextField d3B=new JTextField();

	private JTextField d1C=new JTextField();
	private JTextField d2C=new JTextField();

	
	// graficos
	private Image abc;
	private Icon iconoW;
	private Icon iconoE;
	private Icon iconoD;
	
	// botones y controles
	private JButton modificar1;
	private JButton eliminar1;
	private JButton modificar2;
	private JButton eliminar2;
	
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
	 * de cada empresa relativos a su setting, iva e irpf
	 * 
	 * El constructor recibe los datos del usuario y empresa
	 ********************************************************* */
	
	public ContSettings(String datosEmpmain[], String datosUsuario[]){	
		
		// asignamos a variables de clase los datos recibidos
		this.datosUsuario=datosUsuario;
		datosEmp=datosEmpmain;
		keyEmpr=datosEmpmain[1];
		userCat =(int)Integer.parseInt(datosUsuario[6]);
		this.keyUser=datosUsuario[1];
		
		dao=new ContaDAO();
		String datos[][]=dao.leeSettings(keyEmpr);
		
		
		// primero construimos el panel para consultar
		// *******************************************
		
		datosGenerales(datos);
		
		// segundo construimos el panel para modificar-borrar
		// **************************************************
		
		datosIVA(datos);

		// tercero construimos el panel para creación nuevas empresas
		// **********************************************************
		datosIRPF(datos);

		// y finalmente agregamos los paneles al tabbedPane
		panTab=panelTabulado(panelUsu,"Settings",panelUsu2,"I.V.A.",panelUsu3,"I.R.P.F.");
			
		// finalmente añadimos los listener
		modificar1.addActionListener(this);
		eliminar1.addActionListener(this);
		modificar2.addActionListener(this);
		eliminar2.addActionListener(this);

		
	} //fin del constructor
	
	

	public ContSettings (){
		// constructor
	}
	

	
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		
	} // fin del metodo action Performed

	
	
	
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
	
	private void datosGenerales (String datos[][]) {
		
		String iva1="";
		String iva2="";
		String iva3="";
		String irpf1="";
		String longitud=String.valueOf(LongAuxiliares);
		
		// primero obtenemos los datos deseados
		for (int n=0;n<datos.length;n++) {
			if (datos[n][1].equals("1") && datos[n][4].equals("1") && datos[n][5].equals("1")){
				iva1=datos[n][2];
			}
			if (datos[n][1].equals("1") && datos[n][4].equals("1") && datos[n][5].equals("2")){
				iva2=datos[n][2];
			}
			if (datos[n][1].equals("1") && datos[n][4].equals("1") && datos[n][5].equals("3")){
				iva3=datos[n][2];
			}
			if (datos[n][1].equals("2") && datos[n][4].equals("1") && datos[n][5].equals("1")){
				irpf1=datos[n][2];
			}
		}
		
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
		panelUsuAux.setLayout(new GridLayout(5,2));
		panelUsuAux.setAlignmentY(CENTER_ALIGNMENT);
		panelUsuAux.setFont(Fuente2);
		panelUsuAux.setBackground(ColorFondo);
		
		// datos de la empresa activa actual
		if (iva1.isEmpty()) {
			d1.setText(" SIN I.V.A.");	// iva 1 de facturacion
		} else	d1.setText(iva1+" %");
		if (iva2.isEmpty()) {
			d2.setText(" SIN I.V.A.");	// iva 2 de facturacion
		} else	d2.setText(iva2+" %");
		if (iva3.isEmpty()) {
			d3.setText(" SIN I.V.A.");	// iva 3 de facturacion
		} else	d3.setText(iva3+" %");
		if (irpf1.isEmpty()) {
			d4.setText(" SIN I.R.P.F.");	// irpf de facturacion
		} else	d4.setText(irpf1+" %");
		d5.setText(longitud);	// longitud cuentas
		
		
		// definicion de celdas no editables
		d1.setEditable(false);
		d2.setEditable(false);
		d3.setEditable(false);
		d4.setEditable(false);
		d5.setEditable(false);

		
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

	
		// separadores en panel principal
		JLabel espacioA=new JLabel(" ");
		JLabel espacioB=new JLabel(" ");
		    
	    // creacion del panel inferior
	    JPanel south1=new JPanel();
	    south1.setBackground(ColorFondo);

	    
	    panelUsuAux.setVisible(true);
	    // agregamos componentes al panel principal
	    panelUsu.add(north1);
	    panelUsu.add(espacioA);
	    panelUsu.add(panelUsuAux);
	    panelUsu.add(espacioB);
	    panelUsu.add(south1);
		
	} // fin del metodo datosGenerales
	
	
	
	/* ************************************************************************
	 * Este metodo permite la consulta y modificacion de la tabla datos de IVA
	 * 
	 * Se reciben un string[] con datos de empresa, y un String con el manager
	 * Este metodo no tiene valor de retorno
	 ***************************************************************************/
	
	private void datosIVA (String datos[][]) {

		String iva[][]=new String[datos.length][3];
		
		String iva1="";
		String iva2="";
		String iva3="";

		
		// primero obtenemos los datos deseados
		int k=0;
		for (int n=0;n<datos.length;n++) {
			if (datos[n][1].equals("1")){
				iva[k][0]=datos[n][5];
				iva[k][1]=datos[n][2];
				iva[k][2]=datos[n][3];
				k++;
			}
			if (datos[n][1].equals("1") && datos[n][4].equals("1") && datos[n][5].equals("1")){
				iva1=datos[n][2];
			}
			if (datos[n][1].equals("1") && datos[n][4].equals("1") && datos[n][5].equals("2")){
				iva2=datos[n][2];
			}
			if (datos[n][1].equals("1") && datos[n][4].equals("1") && datos[n][5].equals("3")){
				iva3=datos[n][2];
			}
		}
		
		// creacion del panel principal
		panelUsu2=new JPanel();
		panelUsu2.setLayout(new BoxLayout(panelUsu2,BoxLayout.Y_AXIS));
		panelUsu2.setAlignmentY(CENTER_ALIGNMENT);
		panelUsu2.setBackground(ColorFondo);
		
		// titulo
		JPanel north1=new JPanel();
		north1.setLayout(new FlowLayout());
		north1.setBackground(ColorFondo);
	    // cambiar el font al titulo
	    n2.setFont(Fuente1);
		north1.add(n2);
		
		// creacion del panel rejilla datos
		JPanel panelUsuAux2 = new JPanel();
		panelUsuAux2.setLayout(new GridLayout(3+iva.length,2));
		panelUsuAux2.setAlignmentY(CENTER_ALIGNMENT);
		panelUsuAux2.setFont(Fuente2);
		panelUsuAux2.setBackground(ColorFondo);
		      
		// datos de la empresa activa actual
		if (iva1.isEmpty()) {
			d1B.setText(" SIN I.V.A.");	// iva 1 de facturacion
		} else	d1B.setText(iva1+" %");
		if (iva2.isEmpty()) {
			d2B.setText(" SIN I.V.A.");	// iva 2 de facturacion
		} else	d2B.setText(iva2+" %");
		if (iva3.isEmpty()) {
			d3B.setText(" SIN I.V.A.");	// iva 3 de facturacion
		} else	d3B.setText(iva3+" %");
		
		JLabel labelIVA[]=new JLabel[iva.length];
		JLabel labelIVA2[]=new JLabel[iva.length];
		
		// definicion de celdas no editables
		d1B.setEditable(false);
		d2B.setEditable(false);
		d3B.setEditable(false);
		
		// add de componentes al panel rejilla
		panelUsuAux2.add(l1B);
		panelUsuAux2.add(d1B);
		panelUsuAux2.add(l2B);
		panelUsuAux2.add(d2B);
		panelUsuAux2.add(l3B);
		panelUsuAux2.add(d3B);
		
		for (int j=0;j<k;j++) {
			labelIVA[j]=new JLabel(iva[j][1]);
			labelIVA2[j]=new JLabel("        "+iva[j][2]);
			panelUsuAux2.add(labelIVA2[j]);
			panelUsuAux2.add(labelIVA[j]);
		}
		

	
		// separadores en panel principal
		JLabel espacioA=new JLabel(" ");
		JLabel espacioB=new JLabel(" ");
		    
	    // creacion del panel inferior
	    JPanel south1=new JPanel();
	    south1.setBackground(ColorFondo);
	    modificar1=new JButton("Modificar");
	    modificar1.setToolTipText("modificar los datos");
	    eliminar1=new JButton("Eliminar");
	    eliminar1.setToolTipText("eliminar los datos");
	    south1.add(modificar1);
	    south1.add(eliminar1);
	    
	    panelUsuAux2.setVisible(true);
	    // agregamos componentes al panel principal
	    panelUsu2.add(north1);
	    panelUsu2.add(espacioA);
	    panelUsu2.add(panelUsuAux2);
	    panelUsu2.add(espacioB);
	    panelUsu2.add(south1);
		
	} // fin del metodo datosIVA
	
	
	
	/* ************************************************************************
	 * Este metodo permite la consulta de la tabla datos empresa
	 * Ningun dato puede ser modificado
	 * Se reciben un string[] con datos de empresa, y un String con el manager
	 * Este metodo no tiene valor de retorno
	 ***************************************************************************/
	
	private void datosIRPF (String datos[][]) {
		
		String irpf[][]=new String[datos.length][3];
		String irpf1="";
		String irpf2="";

		
		// primero obtenemos los datos deseados
		int k=0;
	
		for (int n=0;n<datos.length;n++) {
			
			if (datos[n][1].equals("2")){
				irpf[k][0]=datos[n][5];
				irpf[k][1]=datos[n][2];
				irpf[k][2]=datos[n][3];
				k++;
			}
			if (datos[n][1].equals("2") && datos[n][4].equals("1") && datos[n][5].equals("1")){
				irpf1=datos[n][2];
			}
			if (datos[n][1].equals("2") && datos[n][4].equals("2") && datos[n][5].equals("1")){
				irpf2=datos[n][2];
			}

		}
		
		// creacion del panel principal
		panelUsu3=new JPanel();
		panelUsu3.setLayout(new BoxLayout(panelUsu3,BoxLayout.Y_AXIS));
		panelUsu3.setAlignmentY(CENTER_ALIGNMENT);
		panelUsu3.setBackground(ColorFondo);
		
		// titulo
		JPanel north1=new JPanel();
		north1.setLayout(new FlowLayout());
		north1.setBackground(ColorFondo);
	    // cambiar el font al titulo
	    n3.setFont(Fuente1);
		north1.add(n3);
		
		// creacion del panel rejilla datos
		JPanel panelUsuAux3 = new JPanel();
		panelUsuAux3.setLayout(new GridLayout(2+k,2));
		panelUsuAux3.setAlignmentY(CENTER_ALIGNMENT);
		panelUsuAux3.setFont(Fuente2);
		panelUsuAux3.setBackground(ColorFondo);
		      
		// datos de la empresa activa actual
		if (irpf1.isEmpty()) {
			d1C.setText(" SIN I.R.P.F.");	// irpf de facturacion
		} else	d1C.setText(irpf1+" %");
		if (irpf2.isEmpty()) {
			d2C.setText(" SIN I.R.P.F.");	// irpf de facturacion
		} else	d2C.setText(irpf2+" %");

		JLabel labelIRPF[]=new JLabel[irpf.length];
		JLabel labelIRPF2[]=new JLabel[irpf.length];
		
		// definicion de celdas no editables
		d1C.setEditable(false);
		d2C.setEditable(false);
		
		// add de componentes al panel rejilla
		panelUsuAux3.add(l1C);
		panelUsuAux3.add(d1C);
		panelUsuAux3.add(l2C);
		panelUsuAux3.add(d2C);

		for (int j=0;j<k;j++) {
			labelIRPF[j]=new JLabel(irpf[j][1]);
			labelIRPF2[j]=new JLabel("        "+irpf[j][2]);
			panelUsuAux3.add(labelIRPF2[j]);
			panelUsuAux3.add(labelIRPF[j]);
		}
	
		// separadores en panel principal
		JLabel espacioA=new JLabel(" ");
		JLabel espacioB=new JLabel(" ");
		    
	    // creacion del panel inferior
	    JPanel south1=new JPanel();
	    south1.setBackground(ColorFondo);
	    modificar2=new JButton("Modificar");
	    modificar2.setToolTipText("modificar los datos");
	    eliminar2=new JButton("Eliminar");
	    eliminar2.setToolTipText("eliminar los datos");
	    south1.add(modificar2);
	    south1.add(eliminar2);
	    
	    panelUsuAux3.setVisible(true);
	    // agregamos componentes al panel principal
	    panelUsu3.add(north1);
	    panelUsu3.add(espacioA);
	    panelUsu3.add(panelUsuAux3);
	    panelUsu3.add(espacioB);
	    panelUsu3.add(south1);
		
	} // fin del metodo datosIRPF
	
} // ***************************************** FIN DE LA CLASE CONTSETTINGS
