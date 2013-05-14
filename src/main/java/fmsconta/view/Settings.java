package fmsconta.view;

import java.awt.Color;
import java.awt.Font;

interface Settings extends PersonalSettings{

	final String PathImageFiles="src/main/java/fmsconta/pictures/";
	final Color ColorFondo=new Color(220,220,220);
	final Color ColorBlanco=new Color(255,255,255);
	final Font Fuente1=new Font("",Font.BOLD,20);
	final Font Fuente2=new Font("",Font.BOLD,16);
	final Font Fuente3=new Font("",Font.PLAIN,12);
	final Font Fuente4=new Font("",Font.PLAIN,8);
	
	final int LongAuxiliares=8;
	final int LongMinima=7;
	final int LongMaxima=9;
	
}
