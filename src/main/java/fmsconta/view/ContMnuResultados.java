package fmsconta.view;

import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class ContMnuResultados implements ActionListener, Settings {

	
	private static final long serialVersionUID = 1L;
	private JPanel menuResultados;
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
	
	public ContMnuResultados() {
		
		
		menuResultados=new JPanel();
		menuResultados.setBackground(ColorFondo);
		menuResultados.setPreferredSize(new Dimension(400,300));
		menuResultados.setLayout(new BoxLayout(menuResultados,BoxLayout.Y_AXIS));
		
		north=new JPanel();
		north.setBackground(ColorFondo);
		north.setLayout(new BoxLayout(north,BoxLayout.Y_AXIS));
		JLabel title1=new JLabel("Cuenta de Resultados");
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
		listar=new JButton("Listar Resultados");
		listar.setToolTipText("Muestra la cuenta de Resultados entre las fechas seleccionadas");
		south.add(listar);
		cancelar=new JButton("Cancelar");
		cancelar.setToolTipText("Borra la pantalla");
		south.add(cancelar);
		
		menuResultados.add(space1);
		menuResultados.add(space2);
		menuResultados.add(north);
		menuResultados.add(space3);
		menuResultados.add(auxMenu);
		menuResultados.add(space4);
		menuResultados.add(south);
		
		menuResultados.setVisible(true);
		
		listar.addActionListener(this);
		cancelar.addActionListener(this);
		
	}
	
	
	
	/* ******************************************************************
	 * Este metodo simplemente retorna el JPanel a la pantalla principal
	 ******************************************************************* */
	
	public JPanel retorna() {
		return menuResultados;
	}

	
	
	
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub

	}

} // ***************************** fin de la class ContMnuResultados
