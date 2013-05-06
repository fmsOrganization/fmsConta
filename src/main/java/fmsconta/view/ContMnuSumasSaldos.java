package fmsconta.view;

import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class ContMnuSumasSaldos extends JFrame implements ActionListener, Settings {

	private static final long serialVersionUID = 1L;
	private JPanel menuSumas;
	private JPanel auxMenu;
	private JPanel north;
	private JPanel south;
	private JTextField r1;
	private JTextField r2;
	private JTextField r3;
	private JTextField r4;
	private JTextField r5;
	private JCheckBox r6;
	private JCheckBox r7;
	private JLabel space1=new JLabel(" ");
	private JLabel space2=new JLabel(" ");
	private JLabel space3=new JLabel(" ");
	private JLabel space4=new JLabel(" ");
	private JButton listar;
	private JButton cancelar;
	
	private ContListadoMayor listaMayor;
	
	
	
	/* ****************************************************************************
	 * 
	 * Este metodo constructor implementa el menu de impresion del Mayor contable
	 * 
	 * ************************************************************************** */
	
	public ContMnuSumasSaldos() {
		
		
		menuSumas=new JPanel();
		menuSumas.setBackground(ColorFondo);
		menuSumas.setPreferredSize(new Dimension(400,300));
		menuSumas.setLayout(new BoxLayout(menuSumas,BoxLayout.Y_AXIS));
		
		north=new JPanel();
		north.setBackground(ColorFondo);
		north.setLayout(new BoxLayout(north,BoxLayout.Y_AXIS));
		JLabel title1=new JLabel("Balance de Sumas y Saldos");
		title1.setFont(Fuente1);
		title1.setAlignmentX((float)0.5);
		north.add(title1);
		
		auxMenu=new JPanel();
		auxMenu.setBackground(ColorFondo);
		auxMenu.setLayout(new GridLayout(7,2));
		JLabel n1=new JLabel("Desde cuenta");
		n1.setFont(Fuente3);
		JLabel n2=new JLabel("Hasta cuenta");
		n2.setFont(Fuente3);
		JLabel n3=new JLabel("Desde fecha");
		n3.setFont(Fuente3);
		JLabel n4=new JLabel("Hasta fecha");
		n4.setFont(Fuente3);
		JLabel n5=new JLabel("Nivel cuentas");
		n5.setFont(Fuente3);
		JLabel n6=new JLabel("Cuentas nivel 3");
		n6.setFont(Fuente3);
		JLabel n7=new JLabel("Solo con saldo");
		n7.setFont(Fuente3);
		
		r1=new JTextField("10000000");
		r2=new JTextField("79999999");
		r3=new JTextField("01-01-2012");
		r4=new JTextField("31-12-2012");
		r5=new JTextField("8");
		r6=new JCheckBox();
		r6.setBackground(ColorFondo);
		r7=new JCheckBox();
		r7.setBackground(ColorFondo);
		
		auxMenu.add(n1);
		auxMenu.add(r1);
		auxMenu.add(n2);
		auxMenu.add(r2);
		auxMenu.add(n3);
		auxMenu.add(r3);
		auxMenu.add(n4);
		auxMenu.add(r4);
		auxMenu.add(n5);
		auxMenu.add(r5);
		auxMenu.add(n6);
		auxMenu.add(r6);
		auxMenu.add(n7);
		auxMenu.add(r7);
		
		south=new JPanel();
		south.setBackground(ColorFondo);
		south.setLayout(new FlowLayout());
		listar=new JButton("Listar Balance");
		listar.setToolTipText("Muestra Sumas y Saldos en los parámetros seleccionados");
		south.add(listar);
		cancelar=new JButton("Cancelar");
		cancelar.setToolTipText("Borra la pantalla");
		south.add(cancelar);
		
		menuSumas.add(space1);
		menuSumas.add(space2);
		menuSumas.add(north);
		menuSumas.add(space3);
		menuSumas.add(auxMenu);
		menuSumas.add(space4);
		menuSumas.add(south);
		
		menuSumas.setVisible(true);
		
		listar.addActionListener(this);
		cancelar.addActionListener(this);
		
	}
	
	
	
	/* ******************************************************************
	 * Este metodo simplemente retorna el JPanel a la pantalla principal
	 ******************************************************************* */
	
	public JPanel retorna() {
		return menuSumas;
	}

	
	
	/* ******************************************************************
	 * Este metodo implementa el actionPerformed
	 ******************************************************************* */

	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		Object source=e.getSource();
		
		if (source==listar) {
			menuSumas.setVisible(false);
			listaMayor=new ContListadoMayor("EC001","Ecovitalia",this.r1.getText(),this.r2.getText(),this.r3.getText(),this.r4.getText());
			menuSumas.remove(space1);
			menuSumas.remove(space2);
			menuSumas.remove(north);
			menuSumas.remove(space3);
			menuSumas.remove(auxMenu);
			menuSumas.remove(space4);
			menuSumas.remove(south);
			menuSumas.remove(south);
			
			menuSumas.add(listaMayor.retorna());
			menuSumas.setPreferredSize(new Dimension(850,(listaMayor.sizeComponent*20)+125));
			menuSumas.setAlignmentY(TOP_ALIGNMENT);
			menuSumas.setVisible(true);
		}
		
		if (source==cancelar) {
			menuSumas.setVisible(false);
		}
		
	} 
}   // ***************** fin de la class ContMnuSumasSaldos
