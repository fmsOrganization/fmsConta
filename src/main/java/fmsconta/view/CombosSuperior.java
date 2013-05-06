package fmsconta.view;
import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.*;

import javax.swing.*;

public class CombosSuperior extends JFrame implements ActionListener {
	
	protected JComboBox combo1;
	protected JComboBox combo2;
	protected JComboBox combo3;
	protected JComboBox combo4;
	protected JComboBox combo5;
	
	protected String[] general= {"GENERAL","Cambio empresa","Alta apertura","Fin ejercicio","Settings"};
	protected String[] informes={"INFORMES","Libro Mayor","Libro Diario","Sumas y Saldos","Cuenta de Resultados","Balance","Cambios patrimonio"};
	protected String[] operativa={"OPERATIVA","Asientos","Cuentas","Enlaces"};
	protected String[] clientes={"CLIENTES","Cuentas clientes","Listado clientes","Grabación facturas","Modificación facturas","Borrado facturas","Listado facturas","IVA emitidas","Previsiones"};
	protected String[] proveedores={"PROVEEDORES","Cuentas proveedores","Listado proveedores","Grabación facturas","Modificación facturas","Borrado facturas","Listado facturas","IVA recibidas","Previsiones"};

	
	public CombosSuperior() {
		
	    // ELEMENTOS DEL MENU
	
		// COMBOBOX PARA CADA MENU
		combo1=new JComboBox();
		combo2=new JComboBox();
		combo3=new JComboBox();
		combo4=new JComboBox();
		combo5=new JComboBox();
		
		// AÑADIMOS ELEMENTOS A LOS COMBOS
		for (int n=0;n<general.length;n++) combo1.addItem(general[n]);
		for (int n=0;n<informes.length;n++) combo2.addItem(informes[n]);
		for (int n=0;n<operativa.length;n++) combo3.addItem(operativa[n]);
		for (int n=0;n<clientes.length;n++) combo4.addItem(clientes[n]);
		for (int n=0;n<proveedores.length;n++) combo5.addItem(proveedores[n]);
	
		// CREAMOS LOS LISTENER DE LOS COMBOS
		combo1.addActionListener(this);
		combo2.addActionListener(this);
		combo3.addActionListener(this);
		combo4.addActionListener(this);
		combo5.addActionListener(this);
	}
	
	public void actionPerformed(ActionEvent e) {
		// lectura del boton pulsado
		Object source=e.getSource();
	/*	
		//if (source == combo1) JOptionPane.showMessageDialog(null, "combo1 usado");
		//if (source == combo2) JOptionPane.showMessageDialog(null, "combo2 usado");
		if (source == combo3) JOptionPane.showMessageDialog(null, "combo3 usado");
		if (source == combo4) JOptionPane.showMessageDialog(null, "combo4 usado");
		if (source == combo5) {
			JOptionPane.showMessageDialog(null, "combo5 usado");
			setVisible(false);
			JPanel panelvac=new JPanel();
			JLabel vacio=new JLabel("",SwingConstants.CENTER);
			
			panelvac.add(vacio);
			add(panelvac,BorderLayout.CENTER);
			setVisible(true);
		
		}
		*/
	}

}