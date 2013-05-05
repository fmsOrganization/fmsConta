package fmsconta.view;

import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class ContMnuMayor extends JFrame implements ActionListener, Settings {
	
	private JPanel menuMayor;
	private JPanel auxMenu;
	private JPanel north;
	private JPanel south;
	private JTextField r1;
	private JTextField r2;
	private JTextField r3;
	private JTextField r4;
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
	
	public ContMnuMayor() {
		
		
		menuMayor=new JPanel();
		menuMayor.setBackground(ColorFondo);
		menuMayor.setPreferredSize(new Dimension(400,300));
		menuMayor.setLayout(new BoxLayout(menuMayor,BoxLayout.Y_AXIS));
		
		north=new JPanel();
		north.setBackground(ColorFondo);
		north.setLayout(new BoxLayout(north,BoxLayout.Y_AXIS));
		JLabel title1=new JLabel("Listado del Mayor");
		title1.setFont(Fuente1);
		title1.setAlignmentX((float)0.5);
		north.add(title1);
		
		auxMenu=new JPanel();
		auxMenu.setBackground(ColorFondo);
		auxMenu.setLayout(new GridLayout(4,2));
		JLabel n1=new JLabel("Desde cuenta");
		n1.setFont(Fuente3);
		JLabel n2=new JLabel("Hasta cuenta");
		n2.setFont(Fuente3);
		JLabel n3=new JLabel("Desde fecha");
		n3.setFont(Fuente3);
		JLabel n4=new JLabel("Desde fecha");
		n4.setFont(Fuente3);
		
		r1=new JTextField("10000000");
		r2=new JTextField("79999999");
		r3=new JTextField("01-01-2012");
		r4=new JTextField("31-12-2012");
		
		auxMenu.add(n1);
		auxMenu.add(r1);
		auxMenu.add(n2);
		auxMenu.add(r2);
		auxMenu.add(n3);
		auxMenu.add(r3);
		auxMenu.add(n4);
		auxMenu.add(r4);
		
		south=new JPanel();
		south.setBackground(ColorFondo);
		south.setLayout(new FlowLayout());
		listar=new JButton("Listar Mayor");
		south.add(listar);
		cancelar=new JButton("Cancelar");
		south.add(cancelar);
		
		menuMayor.add(space1);
		menuMayor.add(space2);
		menuMayor.add(north);
		menuMayor.add(space3);
		menuMayor.add(auxMenu);
		menuMayor.add(space4);
		menuMayor.add(south);
		
		menuMayor.setVisible(true);
		
		listar.addActionListener(this);
		cancelar.addActionListener(this);
		
	}
	
	
	
	/* ******************************************************************
	 * Este metodo simplemente retorna el JPanel a la pantalla principal
	 ******************************************************************* */
	
	public JPanel retorna() {
		return menuMayor;
	}

	
	
	/* ******************************************************************
	 * Este metodo implementa el actionPerformed
	 ******************************************************************* */

	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		Object source=e.getSource();
		
		if (source==listar) {
			menuMayor.setVisible(false);
			listaMayor=new ContListadoMayor("EC001","Ecovitalia",this.r1.getText(),this.r2.getText(),this.r3.getText(),this.r4.getText());
			menuMayor.remove(space1);
			menuMayor.remove(space2);
			menuMayor.remove(north);
			menuMayor.remove(space3);
			menuMayor.remove(auxMenu);
			menuMayor.remove(space4);
			menuMayor.remove(south);
			menuMayor.remove(south);
			
			menuMayor.add(listaMayor.retorna());
			menuMayor.setPreferredSize(new Dimension(850,(listaMayor.sizeComponent*20)+125));
			menuMayor.setAlignmentY(TOP_ALIGNMENT);
			menuMayor.setVisible(true);
		}
		
		if (source==cancelar) {
			menuMayor.setVisible(false);
		}
		
	}
}
