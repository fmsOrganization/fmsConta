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
import javax.swing.JPanel;
import javax.swing.JTextField;

public class ContMnuBalance extends JFrame implements ActionListener, Settings {

	private static final long serialVersionUID = 1L;
	private JPanel menuBalance;
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
	 * Este metodo constructor implementa el menu de impresion de
	 * la Cuenta de Resultados
	 * 
	 * ************************************************************************** */
	
	public ContMnuBalance() {
		
		
		menuBalance=new JPanel();
		menuBalance.setBackground(ColorFondo);
		menuBalance.setPreferredSize(new Dimension(400,300));
		menuBalance.setLayout(new BoxLayout(menuBalance,BoxLayout.Y_AXIS));
		
		north=new JPanel();
		north.setBackground(ColorFondo);
		north.setLayout(new BoxLayout(north,BoxLayout.Y_AXIS));
		JLabel title1=new JLabel("Balance");
		title1.setFont(Fuente1);
		title1.setAlignmentX((float)0.5);
		north.add(title1);
		
		auxMenu=new JPanel();
		auxMenu.setBackground(ColorFondo);
		auxMenu.setLayout(new GridLayout(2,2));
		JLabel n1=new JLabel("Desde fecha");
		n1.setFont(Fuente3);
		JLabel n2=new JLabel("Hasta fecha");
		n2.setFont(Fuente3);
		

		r1=new JTextField("01-01-2012");
		r2=new JTextField("31-12-2012");
		
		auxMenu.add(n1);
		auxMenu.add(r1);
		auxMenu.add(n2);
		auxMenu.add(r2);

		south=new JPanel();
		south.setBackground(ColorFondo);
		south.setLayout(new FlowLayout());
		listar=new JButton("Listar Balance");
		listar.setToolTipText("Muestra el Balance entre las fechas seleccionadas");
		south.add(listar);
		cancelar=new JButton("Cancelar");
		cancelar.setToolTipText("Borra la pantalla");
		south.add(cancelar);
		
		menuBalance.add(space1);
		menuBalance.add(space2);
		menuBalance.add(north);
		menuBalance.add(space3);
		menuBalance.add(auxMenu);
		menuBalance.add(space4);
		menuBalance.add(south);
		
		menuBalance.setVisible(true);
		
		listar.addActionListener(this);
		cancelar.addActionListener(this);
		
	}
	
	
	
	/* ******************************************************************
	 * Este metodo simplemente retorna el JPanel a la pantalla principal
	 ******************************************************************* */
	
	public JPanel retorna() {
		return menuBalance;
	}
	
	
	
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		
	}

} // fin de la clase COntMnuBalance
