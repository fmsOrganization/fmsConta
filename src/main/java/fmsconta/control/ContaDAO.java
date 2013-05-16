package fmsconta.control;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.swing.JOptionPane;

import fmsconta.view.PantallaPrincipal;



public class ContaDAO implements SystemDates{
	
	private static Connection conexionDB;
	private String cgu;
	private String lpd;
	
	
	public ContaDAO () {
		// builder
	}
    
	
    
    /* **************************************************************
     * Este metodo sirve para crear una conexion a la DB del usuario
     * No recibe argumentos
     * Si no hay problemas devuelve un objeto Connection
     * Si hay errores devuelve null
     ****************************************************************/
    
    public Connection ConnectDB () {
        
        // CONECTOR CON LAS BASES DE DATOS
        // DEVUELVE UN OBJETO CONNECTION SI LO CONSIGUE Y NULL SI FALLA
        
        try {
        	conexionDB = DriverManager.getConnection(NameDB, UserDB, PassDB);
        } catch (SQLException ex) {
            System.out.println("Error conectando a la base de datos");
            Logger.getLogger(ContaDAO.class.getName()).log(Level.SEVERE, null, ex);
            JOptionPane.showMessageDialog(null, "No conecta con Base de Datos: salga de la aplicación");
            return null;
        }
        	
          return conexionDB;
       } // fin del metodo connectDB *******************************************
    
    
    
    /* *********************************
     * Este metodo devuelve conexiones
     * está pensado para multihilo
    *********************************** */
	
    protected Connection consigueConexion() {
        // devuelve una conexion
    	// pensado para multihilo
        return conexionDB;
    }    
    
    
    
    /* **********************************************************************************
     * este metodo sirve para leer en las tablas correspondientes los datos solicitados
     * a un login y password determinado.
     * Recibe un nombre de usuario y la password en formato String
     * Devuelve un null si hay errores
     * Si el login-pasword son correctos, devuelve un String[15]
     ********************************************************************************** */
    
    public String[] idUserDB (String login, String password) {
        
    //  este metodo comprueba el login de un usuario y devuelve su clave de datos
    // antes de ejecutar este metodo, es preferible comprobar con idExist
    	
    // se crea una conexion con
    // se recibe el login y el password suministrado
    // devuelve el keyUser con los datos de usuario
    // o null si no encuentra al usuario
    
    	// crea una conexion
    	Connection con=ConnectDB();
    	
    	Statement st=null;
    	try {
    		st = con.createStatement();
    	} catch (SQLException ex) {
    		Logger.getLogger(ContaDAO.class.getName()).log(Level.SEVERE, null, ex);
    		// si hay algun error cerramos la conexion y return null
    		try {
				con.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
    		return null;
    	}
          
    	// preparamos un String para recuperar todos los datos de usuario
    	String keyUser[]=new String[15];
    	
    	ResultSet rs=null; 
    	try {   
    		rs = st.executeQuery("SELECT * FROM c_usuario WHERE usuario='"+login+"' AND password='"+password+"' ");
    	} catch (SQLException ex) {
    		Logger.getLogger(ContaDAO.class.getName()).log(Level.SEVERE, null, ex);
    		// si hay algun error cerramos la conexion y return null
    		try {
				con.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
    		return null;
    	} 
       
    	try {
    		
    		while (rs.next()){ 
    			// lee todos los datos y los transforma a String
    			keyUser[0] = String.valueOf(rs.getInt(1));
    			keyUser[1] = rs.getString(2);
    			keyUser[2] = rs.getString(3);
    			keyUser[3] = rs.getString(4);
    			keyUser[4] = rs.getString(5);
    			keyUser[5] = rs.getString(6);
    			keyUser[6] = String.valueOf(rs.getInt(7));
    			keyUser[7] = rs.getString(8);
    			keyUser[8] = rs.getString(9);
    			keyUser[9] = rs.getString(10);
    			keyUser[10] = rs.getString(11);
    			keyUser[11] = String.valueOf(rs.getInt(12));
    			keyUser[12] = String.valueOf(rs.getInt(13));
    			keyUser[13] = rs.getString(14);
    			keyUser[14] = rs.getString(15);
    		}
    		
    	} catch (SQLException ex) {
    		// si hay algun error cerramos la conexion y return null
    		try {
				con.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
    		return null;
    	} 

    	// si todo ha ido bien
    	// cerramos la conexion y retornamos el String[]
    	try {
			con.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    	
    	return keyUser;
    
    } // fin de idUserDB ***********************************

    
    
    /* ***************************************************************
     * este metodo sirve para comprobar en la tabla correspondiente 
     * si existe un login y password determinado.
     * Recibe un nombre de usuario y la password en formato String
     * Devuelve un boolean como respuesta
     * Encontrado = TRUE ; no encontrado = FALSE
    ****************************************************************** */
    
    public boolean idExist (String login, String password) {
        
        //  este metodo comprueba el login de un usuario y devuelve true o false
            
        // se recibe el login y el password suministrado
        // devuelve un boolean confirmando o denegando
        
    	// crea una conexion
    	Connection con=ConnectDB();
    	
        Statement st=null;
        try {
        	st = con.createStatement();
        	} catch (SQLException ex) {
        		Logger.getLogger(ContaDAO.class.getName()).log(Level.SEVERE, null, ex);
        		// si hay algun error cerramos la conexion y return false
        		try {
    				con.close();
    			} catch (SQLException e) {
    				// TODO Auto-generated catch block
    				e.printStackTrace();
    			}
        		return false;
        } 
              
        ResultSet rs=null;      	
        try {   
        	rs = st.executeQuery("SELECT * FROM c_usuario WHERE usuario='"+login+"' AND password='"+password+"' ");
        	} catch (SQLException ex) {
        		Logger.getLogger(ContaDAO.class.getName()).log(Level.SEVERE, null, ex);
        		// si hay algun error cerramos la conexion y return false
        		try {
    				con.close();
    			} catch (SQLException e) {
    				// TODO Auto-generated catch block
    				e.printStackTrace();
    			}
        		return false;
        } 
        	 	
        boolean userRet=false;
        try {
        		// lee el primer dato, si existe
        		while (rs.next()){ 
        			// si el primer dato es distinto de null
        			// es que existe la coincidencia
        			if (!(rs.getString(1).equals(null))) {
        				userRet=true;
        				this.lpd=rs.getString(14);
        				this.cgu=rs.getString(15);
     
        			}
        		}
        	} catch (SQLException ex) {
        		// si hay algun error cerramos la conexion y return false
        		try {
    				con.close();
    			} catch (SQLException e) {
    				// TODO Auto-generated catch block
    				e.printStackTrace();
    		}
        	return false;
        } 
         
        // todo ha ido bien, cerramos la conexion y retornamos el String[]
        try {
    			con.close();
    			} catch (SQLException e) {
    				// TODO Auto-generated catch block
    				e.printStackTrace();
    			}
        return userRet;
        
    } // fin de idExist ***********************************    
    
    
    
    /* ***************************************************************
     * este metodo sirve para comprobar en DDBB 
     * si existe un keyUser determinado
     * Recibe un String con el keyUser
     * Devuelve un boolean como respuesta
     * Encontrado = TRUE ; no encontrado = FALSE
    ****************************************************************** */
    
    public boolean idKeyExist (String keyUser) {
        
        //  este metodo comprueba el key y devuelve true o false
            
    	// crea una conexion
    	Connection con=ConnectDB();
    	
        Statement st=null;
        try {
        	st = con.createStatement();
        	} catch (SQLException ex) {
        		Logger.getLogger(ContaDAO.class.getName()).log(Level.SEVERE, null, ex);
        		// si hay algun error cerramos la conexion y return false
        		try {
    				con.close();
    			} catch (SQLException e) {
    				// TODO Auto-generated catch block
    				e.printStackTrace();
    			}
        		return false;
        } 
              
        ResultSet rs=null;      	
        try {   
        	rs = st.executeQuery("SELECT * FROM c_usuario WHERE keyuser='"+keyUser+"'");
        	} catch (SQLException ex) {
        		Logger.getLogger(ContaDAO.class.getName()).log(Level.SEVERE, null, ex);
        		// si hay algun error cerramos la conexion y return false
        		try {
    				con.close();
    			} catch (SQLException e) {
    				// TODO Auto-generated catch block
    				e.printStackTrace();
    			}
        	return false;
        } 
       	
        boolean userRet=false;
        	// si rs no es null es que hay una lectura, y entonces retornamos
        	// true que implica que existe ese idExist
        
		// lee el primer dato, si existe
        try {
    		while (rs.next()){ 
    			// si el primer dato es distinto de null
    			// es que existe la coincidencia
    			if (!(rs.getString(1).equals(null))) {
    				userRet=true;
    			}
    		}
    	} catch (SQLException ex) {
    		// si hay algun error cerramos la conexion y return false
    		try {
				con.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
		}
    	return false;
    }
         
        // todo ha ido bien, cerramos la conexion y retornamos el String[]
        try {
    			con.close();
    			} catch (SQLException e) {
    				// TODO Auto-generated catch block
    				e.printStackTrace();
    			}
        return userRet;
        
    } // fin de idKeyExist ***********************************    
    
    
    
    /* ***************************************************************
     * este metodo sirve para eliminar en DDBB un usuario determinado 
     *
     * Recibe un String con el keyUser
     * Devuelve un boolean como respuesta
     * borrado = TRUE ; no borrado = FALSE
    ****************************************************************** */
    
    public boolean eraseUser (String keyUser) {
        
        //  este metodo comprueba el key y devuelve true o false
            
    	// crea una conexion
    	Connection con=ConnectDB();
    	
        Statement st=null;
        try {
        	st = con.createStatement();
        	} catch (SQLException ex) {
        		Logger.getLogger(ContaDAO.class.getName()).log(Level.SEVERE, null, ex);
        		// si hay algun error cerramos la conexion y return false
        		try {
    				con.close();
    			} catch (SQLException e) {
    				// TODO Auto-generated catch block
    				e.printStackTrace();
    			}
        		return false;
        	} 
              
        int rs=0;      	
        try {   
        	rs = st.executeUpdate("UPDATE c_usuario WHERE keyuser='"+keyUser+"' LIMIT 1");
        	} catch (SQLException ex) {
        		Logger.getLogger(ContaDAO.class.getName()).log(Level.SEVERE, null, ex);
        		// si hay algun error cerramos la conexion y return false
        		try {
    				con.close();
    			} catch (SQLException e) {
    				// TODO Auto-generated catch block
    				e.printStackTrace();
    			}
        	return false;
        	} 
        
         
        // todo ha ido bien, cerramos la conexion y retornamos el String[]
        try {
    			con.close();
    			} catch (SQLException e) {
    				// TODO Auto-generated catch block
    				e.printStackTrace();
    				return false;
    			}
        return true;
        
    } // fin de eraseUser *********************************** 
    
    
   
    /* **********************************************************************************
     * este metodo sirve para leer en la tabla correspondiente los datos de la empresa
     * 
     * Recibe un el key de empresa en formato String
     * Devuelve un null si hay errores
     * Si el keyEmp es correcto, devuelve un String[12]
     ********************************************************************************** */
    
    public String[] idEmpDB (String keyEmp) {
           
    	// crea una conexion
    	Connection con=ConnectDB();
    	
    	Statement st=null;
    	try {
    		st = con.createStatement();
    	} catch (SQLException ex) {
    		Logger.getLogger(ContaDAO.class.getName()).log(Level.SEVERE, null, ex);
    		// si hay algun error cerramos la conexion y return null
    		try {
				con.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
    		return null;
    	}
          
    	// preparamos un String para recuperar todos los datos de usuario
    	String datosEmpr[]=new String[12];
    	
    	ResultSet rs=null; 
    	try {   
    		rs = st.executeQuery("SELECT * FROM c_empresas WHERE keyempresa='"+keyEmp+"'");
    	} catch (SQLException ex) {
    		Logger.getLogger(ContaDAO.class.getName()).log(Level.SEVERE, null, ex);
    		// si hay algun error cerramos la conexion y return null
    		try {
				con.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
    		return null;
    	} 
       
    
    	try {
    		
    		while (rs.next()){ 
    			// lee todos los datos y los transforma a String
    			datosEmpr[0] = String.valueOf(rs.getInt(1));
    			datosEmpr[1] = rs.getString(2);
    			datosEmpr[2] = rs.getString(3);
    			datosEmpr[3] = rs.getString(4);
    			datosEmpr[4] = rs.getString(5);
    			datosEmpr[5] = rs.getString(6);
    			datosEmpr[6] = rs.getString(7);
    			datosEmpr[7] = rs.getString(8);
    			datosEmpr[8] = rs.getString(9);
    			datosEmpr[9] = rs.getString(10);
    			datosEmpr[10] =String.valueOf(rs.getInt(11));
    			datosEmpr[11] = rs.getString(12);
    		}
    		
    	} catch (SQLException ex) {
    		// si hay algun error cerramos la conexion y return null
    		try {
				con.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
    		return null;
    	} 

    	// si todo ha ido bien
    	// cerramos la conexion y retornamos el String[]
    	try {
			con.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    	
    	return datosEmpr;
    
    } // fin de idEmpDB ***********************************
    
    
    
    /* **********************************************************************************
     * este metodo sirve para buscar en la DDBB los datos de las empresas de un usuario
     * 
     * Recibe un key de user en formato String
     * Devuelve un null si hay errores
     * Si el keyUser es correcto, devuelve un String[n][12] siendo n el num.de empresas
     ********************************************************************************** */
    
    public String[][] buscaEmpresasUsuDB (String keyUser) {
           
    	// crea una conexion
    	Connection con=ConnectDB();
    	
    	Statement st=null;
    	try {
    		st = con.createStatement();
    	} catch (SQLException ex) {
    		Logger.getLogger(ContaDAO.class.getName()).log(Level.SEVERE, null, ex);
    		// si hay algun error cerramos la conexion y return null
    		try {
				con.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
    		return null;
    	}
          
    	// preparamos un String para recuperar todos los datos de usuario
    	String datosEmpr[][]=new String[3][12];
    	
    	ResultSet rs=null; 
    	try {   
    		rs = st.executeQuery("SELECT * FROM c_empresas WHERE keymanager='"+keyUser+"'");
    	} catch (SQLException ex) {
    		Logger.getLogger(ContaDAO.class.getName()).log(Level.SEVERE, null, ex);
    		// si hay algun error cerramos la conexion y return null
    		try {
				con.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
    		return null;
    	} 
       
    
    	try {
    		int n=0;
    		while (rs.next()){ 
    			// lee todos los datos y los transforma a String
    			datosEmpr[n][0] = String.valueOf(rs.getInt(1));
    			datosEmpr[n][1] = rs.getString(2);
    			datosEmpr[n][2] = rs.getString(3);
    			datosEmpr[n][3] = rs.getString(4);
    			datosEmpr[n][4] = rs.getString(5);
    			datosEmpr[n][5] = rs.getString(6);
    			datosEmpr[n][6] = rs.getString(7);
    			datosEmpr[n][7] = rs.getString(8);
    			datosEmpr[n][8] = rs.getString(9);
    			datosEmpr[n][9] = rs.getString(10);
    			datosEmpr[n][10] =String.valueOf(rs.getInt(11));
    			datosEmpr[n][11] = rs.getString(12);
    			n++;
    		}
    		
    	} catch (SQLException ex) {
    		// si hay algun error cerramos la conexion y return null
    		try {
				con.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
    		return null;
    	} 

    	// si todo ha ido bien
    	// cerramos la conexion y retornamos el String[]
    	try {
			con.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    	
    	return datosEmpr;
    
    } // fin de BuscaEmpresasUsuDB ***********************************
    
    
    
    /* **********************************************************************************
     * este metodo sirve para buscar en la DDBB los usuarios gestionados por un manager
     * 
     * Recibe un key de user en formato String
     * Instancia el metodo buscaEmpresasUsuDB para encontrar las empresas
     * y luego busca en los usuarios en la DDBB
     * 
     * Devuelve un null si hay errores
     * Si el keyUser es correcto, devuelve un String[n][15] siendo n el num.de usuarios
     ********************************************************************************** */
    
    public String[][] buscaUsuariosManagerDB (String keyUser) {
         
    	// busca las empresas del manager
    	String empMan[][]=buscaEmpresasUsuDB(keyUser);
    	// si no hay empresas no hay usuarios gestionados: salimos con null
    	if (empMan==null) return null;
    	
    	// crea una conexion
    	Connection con=ConnectDB();
    	
    	Statement st=null;
    	try {
    		st = con.createStatement();
    	} catch (SQLException ex) {
    		Logger.getLogger(ContaDAO.class.getName()).log(Level.SEVERE, null, ex);
    		// si hay algun error cerramos la conexion y return null
    		try {
				con.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
    		return null;
    	}
          
    	// preparamos un String para recuperar todos los datos de usuario
    	// consideramos el maximo de casos posibles que son 6 usuarios
    	String datosUser[][]=new String[6][16];
    	
    	ResultSet rs=null;
    	
    	int i=0; // contador de usuarios grabados
    	int n=0; // contador de empresas
    	String empbusc;
    while (n<3 && empMan[n][1]!="") {	
    	empbusc=empMan[n][1]; // empresa a buscar en fichero usuarios
    	try { 
    		// se busca a usuarios que no sean el manager y tenga como empresa la administrada
    		rs = st.executeQuery("SELECT * FROM c_usuario WHERE keyUser!='"+keyUser+"' && " +
    				"(emp1='"+empbusc+"' || emp2='"+empbusc+"' || emp3='"+empbusc+"')");
    	} catch (SQLException ex) {
    		Logger.getLogger(ContaDAO.class.getName()).log(Level.SEVERE, null, ex);
    		// si hay algun error cerramos la conexion y return null
    		try {
				con.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
    		return null;
    	} 
       
    
    	try {
    		while (rs.next()){ 
    			// lee todos los datos y los transforma a String
    			datosUser[i][0] = String.valueOf(rs.getInt(1));
    			datosUser[i][1] = rs.getString(2);
    			datosUser[i][2] = rs.getString(3);
    			datosUser[i][3] = rs.getString(4);
    			datosUser[i][4] = rs.getString(5);
    			datosUser[i][5] = rs.getString(6);
    			datosUser[i][6] = String.valueOf(rs.getInt(7));
    			datosUser[i][7] = rs.getString(8);
    			datosUser[i][8] = rs.getString(9);
    			datosUser[i][9] = rs.getString(10);
    			datosUser[i][10] = rs.getString(11);
    			datosUser[i][11] = String.valueOf(rs.getInt(12));
    			datosUser[i][12] = String.valueOf(rs.getInt(13));
    			datosUser[i][13] = rs.getString(14);
    			datosUser[i][14] = rs.getString(15);
    			datosUser[i][15] = "";	// en blanco, sin uso en este metodo
    			i++;
    		}
    		
    	} catch (SQLException ex) {
    		// si hay algun error cerramos la conexion y return null
    		try {
				con.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
    		return null;
    	} 
    	n++;
    } // fin del WHILE
    
    	// si todo ha ido bien
    	// cerramos la conexion y retornamos el String[]
    	try {
			con.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    	
    	return datosUser;
    
    } // fin de BuscaUsuariosManagerDB ***********************************
    
    
    
    /* **********************************************************************************
     * Este metodo sirve para grabar en la DDBB los datos de la empresa
     * 
     * Recibe el key de empresa en formato String,un String[] con los datos
     * y un muy importante String oper ("INSERT" o "UPDATE") segun corresponda
     * Devuelve un true o false si hay errores 
     ********************************************************************************** */
    
    public boolean grabaEmpDB (String keyEmp, String datosEmp[], String oper) {
           
    	// crea una conexion
    	Connection con=ConnectDB();
    	
    	Statement st=null;
    	try {
    		st = con.createStatement();
    	} catch (SQLException ex) {
    		Logger.getLogger(ContaDAO.class.getName()).log(Level.SEVERE, null, ex);
    		// si hay algun error cerramos la conexion y return null
    		try {
				con.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
    		return false;
    	}
    	
    	// recoge los valores a grabar o actualizar
      //  int id=(int)Integer.parseInt(datosEmp[0]); realmente es un autoincrement
        String key=datosEmp[1];
    	String nom=datosEmp[2];
    	String dir=datosEmp[3];
    	String loc=datosEmp[4];
    	String pro=datosEmp[5];
    	String cpo=datosEmp[6];
    	String cif=datosEmp[7];
    	String ini=datosEmp[8];
    	String fin=datosEmp[9];
    	int act=(int)Integer.parseInt(datosEmp[10]);
    	String man=datosEmp[11];
    	
    	int rs=0;
    	try {   
    		if (oper.equals("UPDATE")) {
    			rs = st.executeUpdate("UPDATE c_empresas SET keyempresa='"+key+"', " +
    				"nombre='"+nom+"', direccion='"+dir+"', localidad='"+loc+"', provincia='"+pro+"'," +
    				" codpostal='"+cpo+"', cif='"+cif+"', fechainicio='"+ini+"',fechafin='"+fin+"'," +
    						" activa='"+act+"',keymanager='"+man+"' WHERE keyempresa='"+keyEmp+"' LIMIT 1");
    		} else if (oper.equals("INSERT")) {
    			rs = st.executeUpdate("INSERT c_empresas SET keyempresa='"+key+"', " +
        				"nombre='"+nom+"', direccion='"+dir+"', localidad='"+loc+"', provincia='"+pro+"'," +
        				" codpostal='"+cpo+"', cif='"+cif+"', fechainicio='"+ini+"',fechafin='"+fin+"'," +
        						" activa='"+act+"',keymanager='"+man+"'");
    		} else System.err.println("No se recibió orden de Update o Insert. Operación no efectuada ");
    			
    	} catch (SQLException ex) {
    		Logger.getLogger(ContaDAO.class.getName()).log(Level.SEVERE, null, ex);
    		// si hay algun error cerramos la conexion y return null
    		try {
				con.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
    		return false;
    	} 

    	// si todo ha ido bien
    	// cerramos la conexion y retornamos el String[]
    	try {
			con.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return false;
		}
    	
    	return true;
    
    }  // fin de grabaEmpDB ***********************************
    
    
    
    /* ********************************************************************
     * este metodo sirve para grabar en la tabla del usuario
     * la empresa que acaba de crear
     * 
     * Recibe como argumentos el keyUser y el KeyEmpr y no devuelve nada
     ********************************************************************** */
    
    public void grabaEmpresaUsu (String keyUser, String keyEmpr, int position) {
           
    	// crea una conexion
    	Connection con=ConnectDB();
    	
    	Statement st=null;
    	try {
    		st = con.createStatement();
    	} catch (SQLException ex) {
    		Logger.getLogger(ContaDAO.class.getName()).log(Level.SEVERE, null, ex);
    		// si hay algun error cerramos la conexion y return null
    		try {
				con.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
    	}
    	
    	// recoge los valores a grabar o actualizar

    	int rs=0;
    	
    	try {
    		if (position==8) {
    			rs = st.executeUpdate("UPDATE c_usuario SET emp2='"+keyEmpr+"' WHERE keyuser='"+keyUser+"' LIMIT 1");
    		} else rs = st.executeUpdate("UPDATE c_usuario SET emp3='"+keyEmpr+"' WHERE keyuser='"+keyUser+"' LIMIT 1");
    			
    	} catch (SQLException ex) {
    		Logger.getLogger(ContaDAO.class.getName()).log(Level.SEVERE, null, ex);
    		// si hay algun error cerramos la conexion y return null
    		try {
				con.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
    	} 

    	// si todo ha ido bien
    	// cerramos la conexion y retornamos el String[]
    	try {
			con.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    
    }  // fin de grabaEmpresaUsu ***********************************
    
    
    
    /* **********************************************************************************
     * este metodo sirve para grabar en la DDBB la aceptación de la LPD y de las CGU
     * 
     * Recibe como argumento el login y el password y no devuelve nada 
     ********************************************************************************** */
    
    public void grabaCGULPD (String login, String pass) {
           
    	// crea una conexion
    	Connection con=ConnectDB();
    	
    	Statement st=null;
    	try {
    		st = con.createStatement();
    	} catch (SQLException ex) {
    		Logger.getLogger(ContaDAO.class.getName()).log(Level.SEVERE, null, ex);
    		// si hay algun error cerramos la conexion y return null
    		try {
				con.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
    	}
    	
    	// recoge los valores a grabar o actualizar
    	String lpd="Y";
    	String cgu="Y";
    	int rs=0;
    	
    	try { 
    		rs = st.executeUpdate("UPDATE c_usuario SET lpd='"+lpd+"', " +
    			"cgu='"+cgu+"' WHERE usuario='"+login+"' AND password='"+pass+"' LIMIT 1");
    			
    	} catch (SQLException ex) {
    		Logger.getLogger(ContaDAO.class.getName()).log(Level.SEVERE, null, ex);
    		// si hay algun error cerramos la conexion y return null
    		try {
				con.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
    	} 

    	// si todo ha ido bien
    	// cerramos la conexion y retornamos el String[]
    	try {
			con.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    
    }  // fin de grabaCGULPD ***********************************
     
   
    
    /* *************************************************************
     * Este metodo comprueba si la lpd y las cgu estaban aceptadas
     * ambas han obtenido el valor en idExist()
     * retorna true si cumplen y false si no cumplen 
     *************************************************************** */
    
    public boolean cumpleCGULPD() {
    	
    	if ((this.cgu.equals("Y"))&&(this.lpd.equals("Y"))) {
    		return true;
    	}
    	
    	return false;
    }
    
    
    
    /* **********************************************************************************
     * Este metodo sirve para grabar en la DDBB los datos del usuario
     * 
     * Recibe como parametros un array con los datos de usuario
     * un muy importante String oper ("INSERT" o "UPDATE") segun corresponda
     * la categoria del usuario  siendo 1=manager, etc
     * 
     * Devuelve un true o false si hay errores 
     ********************************************************************************** */
    
    public boolean grabaUsuDB (String datosUsu[], String oper, int categoria) {
           
    	// crea una conexion
    	Connection con=ConnectDB();
    	
    	Statement st=null;
    	try {
    		st = con.createStatement();
    	} catch (SQLException ex) {
    		Logger.getLogger(ContaDAO.class.getName()).log(Level.SEVERE, null, ex);
    		// si hay algun error cerramos la conexion y return null
    		try {
				con.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
    		return false;
    	}
    	
    	// recoge los valores a grabar o actualizar
      //  int id=(int)Integer.parseInt(datosUsu[0]); realmente es un autoincrement
        String key=datosUsu[1];
    	String nom=datosUsu[2];
    	String log=datosUsu[3];
    	String pas=datosUsu[4];
    	String ema=datosUsu[5];
    	int cat;
    	if (datosUsu[6].equals("") || datosUsu[6]==null) {
    		cat=0;
    	} else cat=(int) Integer.parseInt(datosUsu[6]);
    	String em1=datosUsu[7];
    	String em2=datosUsu[8];
    	String em3=datosUsu[9];
    	String sel=datosUsu[10];
    	int fec;
    	if (datosUsu[11].equals("") || datosUsu[11]==null) {
    		fec=0;
    	} else  fec=(int) Integer.parseInt(datosUsu[11]);
    	int act;
    	if (datosUsu[12].equals("") || datosUsu[12]==null) {
    		act=0;
    	} else  act=(int) Integer.parseInt(datosUsu[12]);
    	String lpd=datosUsu[13];
    	String cgu=datosUsu[14];
    	
    	int rs=0;
    	try {   
    		if (oper.equals("UPDATE")) {
    			rs = st.executeUpdate("UPDATE c_usuario SET keyuser='"+key+"', " +
    				"nombre='"+nom+"', usuario='"+log+"', password='"+pas+"', email='"+ema+"'," +
    				" categoria='"+cat+"', emp1='"+em1+"', emp2='"+em2+"',emp3='"+em3+"'," +
    				" empselec='"+sel+"', yearselec='"+fec+"', activo='"+act+"',lpd='"+lpd+"'," +
    						" cgu='"+cgu+"' WHERE keyuser='"+key+"' LIMIT 1");
    		} else if (oper.equals("INSERT")) {
    			rs = st.executeUpdate("INSERT c_usuario SET keyuser='"+key+"', " +
        				"nombre='"+nom+"', usuario='"+log+"', password='"+pas+"', email='"+ema+"'," +
        				" categoria='"+cat+"', emp1='"+em1+"', emp2='"+em2+"',emp3='"+em3+"'," +
        				" empselec='"+sel+"', yearselec='"+fec+"', activo='"+act+"',lpd='"+lpd+"'," +
        						" cgu='"+cgu+"' ");
    		} else System.err.println("No se recibió orden de Update o Insert. Operación no efectuada ");
    			
    	} catch (SQLException ex) {
    		Logger.getLogger(ContaDAO.class.getName()).log(Level.SEVERE, null, ex);
    		// si hay algun error cerramos la conexion y return null
    		try {
				con.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
    		return false;
    	} 

    	// si todo ha ido bien
    	// cerramos la conexion y retornamos el String[]
    	try {
			con.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return false;
		}
    	
    	return true;
    
    }  // fin de grabaUsuDB ***********************************
    
    
    
    /* ***************************************************************
     * Este metodo sirve para comprobar en la tabla correspondiente 
     * cuantos usuarios tiene una empresa
     * 
     * Recibe un parametro String con el keyEmpresa a buscar 
     * Devuelve un INT como respuesta del numero de usuarios
     * Si hay algun error devuelve -1
    ****************************************************************** */
    
    public int numUserAutorizados(String keyEmpresa) {
        
        //  este metodo comprueba los usuarios en una empresa
        
    	// crea una conexion
    	Connection con=ConnectDB();
    	
        Statement st=null;
        try {
        	st = con.createStatement();
        	} catch (SQLException ex) {
        		Logger.getLogger(ContaDAO.class.getName()).log(Level.SEVERE, null, ex);
        		// si hay algun error cerramos la conexion y return false
        		try {
    				con.close();
    			} catch (SQLException e) {
    				// TODO Auto-generated catch block
    				e.printStackTrace();
    			}
        		return -1;
        } 
              
        ResultSet rs=null;      	
        try {   
        	rs = st.executeQuery("SELECT * FROM c_usuario WHERE emp1='"+keyEmpresa+"' || emp2='"+keyEmpresa+"' || emp3='"+keyEmpresa+"'");
        	} catch (SQLException ex) {
        		Logger.getLogger(ContaDAO.class.getName()).log(Level.SEVERE, null, ex);
        		// si hay algun error cerramos la conexion y return false
        		try {
    				con.close();
    			} catch (SQLException e) {
    				// TODO Auto-generated catch block
    				e.printStackTrace();
    			}
        		return -1;
        } 
        	 	
        int i=0; // numero de usuarios con esa empresa
        try {
        		// lee el primer dato, si existe
        	
        		while (rs.next()){ 
        			// si el primer dato es distinto de null
        			// es que existe la coincidencia
        			if (!(rs.getString(1).equals(null))) {
        				// en i acumulamos el numero de usuarios
        				i++;
        			}
        			
        		}
        	} catch (SQLException ex) {
        		// si hay algun error cerramos la conexion y return false
        		try {
    				con.close();
    			} catch (SQLException e) {
    				// TODO Auto-generated catch block
    				e.printStackTrace();
    		}
        	return -1;
        } 
         
        // todo ha ido bien, cerramos la conexion y retornamos el String[]
        try {
    			con.close();
    			} catch (SQLException e) {
    				// TODO Auto-generated catch block
    				e.printStackTrace();
    				return -1;
    			}
        return i;
        
    } // fin de numUserAutorizados *********************************** 
    
    
    
    /* ************************************************************************************
     * este metodo devuelve un string de datos con los nombres de las empresas del usuario
     * 
     * Recibe un array con los datos del usuario
     * Devuelve un null si hay errores
     * Si el keyEmp es correcto, devuelve un String[3] con nombres
     ********************************************************************************** */
    
    public String[] showNamesCompDB (String user[]) {
           
    	// crea una conexion
    	Connection con=ConnectDB();
    	
    	Statement st=null;
    	try {
    		st = con.createStatement();
    	} catch (SQLException ex) {
    		Logger.getLogger(ContaDAO.class.getName()).log(Level.SEVERE, null, ex);
    		// si hay algun error cerramos la conexion y return null
    		try {
				con.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
    		return null;
    	}
          
    	// cogemos los keyEmpr del array del usuario
    	String emp1=user[7];
    	String emp2=user[8];
    	String emp3=user[9];
    	// preparamos un String para devolver la informacion solicitada
    	String nameEmpr[]=new String[3];
    	
    	ResultSet rs=null; 
    	try {   
    		rs = st.executeQuery("SELECT * FROM c_empresas WHERE keyempresa='"+emp1+"' || keyempresa='"+emp2+"' || keyempresa='"+emp3+"'");
    	} catch (SQLException ex) {
    		Logger.getLogger(ContaDAO.class.getName()).log(Level.SEVERE, null, ex);
    		// si hay algun error cerramos la conexion y return null
    		try {
				con.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
    		return null;
    	} 
       
    
    	try {
    		int n=0;
    		while (rs.next()){ 
    			// lee todos los nombres y los transforma a String
    			nameEmpr[n] = rs.getString(3);
    			n++;
    		}
    		
    	} catch (SQLException ex) {
    		// si hay algun error cerramos la conexion y return null
    		try {
				con.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
    		return null;
    	} 

    	// si todo ha ido bien
    	// cerramos la conexion y retornamos el String[]
    	try {
			con.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    	
    	return nameEmpr;
    
    } // fin de showNamesCompDB ***********************************
    
    
    
    /* ***************************************************************
     * Este metodo sirve para eliminar en DDBB una empresa determinada 
     *
     * A) Borra la empresa del fichero de empresas
     * B) Borra la keyEmpresa de los usuarios del fichero de usuarios
     * C) A implementar: el borrado de los ficheros de la empresa
     * 
     * Recibe un String con el keyEmpresa de la empresa a borrar
     * Devuelve un boolean como respuesta
     * borrado = TRUE ; no borrado = FALSE
    ****************************************************************** */
    
    public boolean eraseCompany (String keyEmp) {
        
        //  este metodo comprueba el key y devuelve true o false
            
    	// crea una conexion
    	Connection con=ConnectDB();
    	
        Statement st=null;
        try {
        	st = con.createStatement();
        	} catch (SQLException ex) {
        		Logger.getLogger(ContaDAO.class.getName()).log(Level.SEVERE, null, ex);
        		// si hay algun error cerramos la conexion y return false
        		try {
    				con.close();
    			} catch (SQLException e) {
    				// TODO Auto-generated catch block
    				e.printStackTrace();
    			}
        		return false;
        	} 
              
        int rs=0;      	
        // Primero borramos la empresa del fichero de empresas
        try {   
        	rs = st.executeUpdate("DELETE FROM c_empresas WHERE keyempresa='"+keyEmp+"' LIMIT 1");
        	} catch (SQLException ex) {
        		Logger.getLogger(ContaDAO.class.getName()).log(Level.SEVERE, null, ex);
        		// si hay algun error cerramos la conexion y return false
        		try {
    				con.close();
    			} catch (SQLException e) {
    				// TODO Auto-generated catch block
    				e.printStackTrace();
    			}
        	return false;
        	} 
        
        // segundo, borramos las referencias a esa empresa del fichero de usuarios
        // es un proceso en cuatro pasos
        
        // primero borramos la aparicion en ultima posicion de empresa
        try {   
        	rs = st.executeUpdate("UPDATE c_usuario SET emp3='' WHERE emp3='"+keyEmp+"' LIMIT 3");
        	} catch (SQLException ex) {
        		Logger.getLogger(ContaDAO.class.getName()).log(Level.SEVERE, null, ex);
        		// si hay algun error cerramos la conexion y return false
        		try {
    				con.close();
    			} catch (SQLException e) {
    				// TODO Auto-generated catch block
    				e.printStackTrace();
    			}
        	return false;
        	} 
        // luego borramos la aparicion en segunda posicion de empresa
        // para no dejar huecos, grabamos la tercera posicion en la segunda y borramos la tercera
        try {   
        	rs = st.executeUpdate("UPDATE c_usuario SET emp2=emp3, emp3='' WHERE emp2='"+keyEmp+"' LIMIT 3");
        	} catch (SQLException ex) {
        		Logger.getLogger(ContaDAO.class.getName()).log(Level.SEVERE, null, ex);
        		// si hay algun error cerramos la conexion y return false
        		try {
    				con.close();
    			} catch (SQLException e) {
    				// TODO Auto-generated catch block
    				e.printStackTrace();
    			}
        	return false;
        	} 
      
        // siguiente, borramos la aparicion en primera posicion de empresa
        // para no dejar huecos, desplazamos la 3ª posicion en la 2ª y la 2ª en la 1ª
        try {   
        	rs = st.executeUpdate("UPDATE c_usuario SET emp1=emp2, emp2=emp3, emp3='' WHERE emp1='"+keyEmp+"' LIMIT 3");
        	} catch (SQLException ex) {
        		Logger.getLogger(ContaDAO.class.getName()).log(Level.SEVERE, null, ex);
        		// si hay algun error cerramos la conexion y return false
        		try {
    				con.close();
    			} catch (SQLException e) {
    				// TODO Auto-generated catch block
    				e.printStackTrace();
    			}
        	return false;
        	} 
        
        // por ultimo, llega lo dificil: borrar la empselec
        // para ello se le añade la primera empresa como seleccionada
        try {   
        	rs = st.executeUpdate("UPDATE c_usuario SET empselec=emp1 WHERE empselec='"+keyEmp+"' LIMIT 3");
        	} catch (SQLException ex) {
        		Logger.getLogger(ContaDAO.class.getName()).log(Level.SEVERE, null, ex);
        		// si hay algun error cerramos la conexion y return false
        		try {
    				con.close();
    			} catch (SQLException e) {
    				// TODO Auto-generated catch block
    				e.printStackTrace();
    			}
        	return false;
        	} 
        
        
        
        try {
    			con.close();
    			} catch (SQLException e) {
    				// TODO Auto-generated catch block
    				e.printStackTrace();
    				return false;
    			}
        return true;
        
    } // fin de eraseCompany ***********************************
    
    
    
    /* **************************************************************************************
     * Metodo que lee los datos del diario para conformar el mayor solicitado
     * 
     * Recibe como parametros el key de la empresa, la cuenta inicial y la cuenta final
     * a listar, y el intervalo de fecha inicial y final
     * 
     * Devuelve un Array[n][10] con un listado ya confeccionado con cabeceras y sumatorios
     * 
     * PROCEDIMIENTO:
     * A) Con los datos recibidos se componen los datos de la busqueda a realizar
     *    se compone el fichero del diario de la empresa correspondiente, se preparan los
     *    filtros para la busqueda y se hace un SELECT en la DDBB
     * B) Se preparan las cabeceras y los finales que se imprimiran con cada cuenta
     * C) Se leen y componen en el formato adecuado de salida los distintos movimientos
     *    de cada cuenta. Hay que tener muy en cuenta el caso de el cambio de cuenta para
     *    el tema de acumulados y cabeceras 
     * D) Se transforma el ArrayList en un array[n][10] para devolver la informacion
     * 
     **************************************************************************************** */
    
    public String[][] leeMayor(String keyEmp,String cuentaIni,String cuentaFin,String fechaIni,String fechaFin) {   	
    	
    	// creamos el arrayList bidimensional 	
    	ArrayList<ArrayList<String>> datosLeidos=new ArrayList<ArrayList<String>>();
    	// leemos las variables filtro - modificamos la fecha a formato date
    	String fec1=fechaIni.substring(6)+fechaIni.substring(2, 6)+fechaIni.substring(0, 2);
    	String fec2=fechaFin.substring(6)+fechaFin.substring(2, 6)+fechaFin.substring(0, 2);
    	String cta1=cuentaIni;
    	String cta2=cuentaFin;
    	
    	// primero componemos dinamicamente el nombre del fichero diario
    	// del cual leeremos los datos
    	String anno=fec1.substring(2, 4);
    	String fichero="c_"+keyEmp+anno+"diario";
    	
    	// para obtener el nombre de las cuentas contables
    	// instanciamos el metodo getNamesCtas
    	// y recibimos un array con los numeros y nombres de cuentas
    	String namesCta[][]=getNamesCtas(keyEmp,cta1,cta2);
    	
    	if (namesCta==null) {
    		System.err.println("NO HAY NOMBRES PARA LEER");
    	}
    	
    	
    	// instanciamos una conexion
    	Connection con= ConnectDB();
    	// creamos un objeto statement
    	Statement st=null;
    	try {
			st=con.createStatement();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    	// creamos un objeto resulset para recoger los datos
    	ResultSet rs=null;
    	
    	try {
			rs=st.executeQuery("SELECT * FROM "+fichero+" WHERE fecha>='"+fec1+"' && fecha<='"+fec2+"' && cuenta>='"+cta1+"' && cuenta<='"+cta2+"' ORDER BY cuenta,fecha,numasto,numapunte ");
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			System.out.println("Error intentando select en fichero "+fichero);
			e.printStackTrace();
		}
    	
    	// crea los distintos string[] para cada cuenta
    	// toda cuenta auxiliar tiene dos lineas de cabecera
    	// n lineas de datos
    	// y finalmente tres lineas de cierre de cuenta
    	String cabecera1[]=new String[10];
    	String cabecera5[]=new String[10];
    	String datos[]=new String[10];
    	String context[]=new String[10];
    	String sumas1[]=new String[10];
    	String sumas2[]=new String[10];
    	
    	// dando formato a datos prefijados de los String
    	cabecera1[0]="Mayor de la cuenta: ";
    	cabecera1[2]="Desde: ";
    	cabecera1[3]=fec1;
    	cabecera1[4]="Hasta: ";
    	cabecera1[5]=fec2;
    	
    	
    	cabecera5[0]="";
    	cabecera5[1]="Asiento";
    	cabecera5[2]="Apunte";
    	cabecera5[3]="Fecha";
    	cabecera5[4]="Documento";
    	cabecera5[5]="Concepto";
    	cabecera5[6]="Contrapartida";
    	cabecera5[7]="Debe";
    	cabecera5[8]="Haber";
    	cabecera5[9]="Saldo";
    	
    	
    	context[6]="____________";
    	context[7]="____________";
    	context[8]="____________";
    	sumas1[5]="Sumas........... ";
    	sumas2[6]="Saldo Final..... ";
    	sumas2[7]="................ ";
    	sumas2[8]="................ ";
    	
    	
    	int n=0;	// MOVIMIENTOS LEIDOS
    	int c=0;	// NOMBRE DE CUENTAS LEIDOS
    	
    	try {
    		String cuentaOld=""; 			// guarda la cuenta leida ultima
    		float sumasdebe=0;				// aqui acumula sumas al debe
    		float sumashaber=0;				// aqui acumula sumas al haber
    		float acumula=0;				// aqui acumula los saldos
    		
    		
    		// graba la primera cabecera y empieza
    		datosLeidos.add(new ArrayList<String>());
    		datosLeidos.get(n).add(cabecera1[0]);
    		datosLeidos.get(n).add(cabecera1[1]);
    		datosLeidos.get(n).add(cabecera1[2]);
    		datosLeidos.get(n).add(cabecera1[3]);
    		datosLeidos.get(n).add(cabecera1[4]);
    		datosLeidos.get(n).add(cabecera1[5]);
    		datosLeidos.get(n).add(cabecera1[6]);
    		datosLeidos.get(n).add(cabecera1[7]);
    		datosLeidos.get(n).add(cabecera1[8]);
    		datosLeidos.get(n).add(cabecera1[9]);
    		
    		n++;
    		datosLeidos.add(new ArrayList<String>());
    		// se le asigna automaticamente el primer nombre
    		// solo fallaria si la cuenta no estuviese creada
    		// cosa que no puede ser porque tiene movimientos
    		cabecera5[0]=namesCta[0][1];
    		datosLeidos.get(n).add(cabecera5[0]);
    		datosLeidos.get(n).add(cabecera5[1]);
    		datosLeidos.get(n).add(cabecera5[2]);
    		datosLeidos.get(n).add(cabecera5[3]);
    		datosLeidos.get(n).add(cabecera5[4]);
    		datosLeidos.get(n).add(cabecera5[5]);
    		datosLeidos.get(n).add(cabecera5[6]);
    		datosLeidos.get(n).add(cabecera5[7]);
    		datosLeidos.get(n).add(cabecera5[8]);
    		datosLeidos.get(n).add(cabecera5[9]);
    		
			while(rs.next()) {
				
				//******** AQUI SE LEE UN MOVIMIENTO Y ALMACENA EN MATRIZ
				datos[0]=String.valueOf(rs.getInt(1));
				datos[1]=String.valueOf(rs.getInt(2));
				datos[2]=String.valueOf(rs.getDate(3));
				datos[3]=rs.getString(5);
				datos[4]=rs.getString(6);
				datos[5]=rs.getString(7);
				datos[9]=rs.getString(4);
				// si fuera el primer movimiento debe cambiar
				// la variable de control cuentaOld
				if (cuentaOld.isEmpty()) cuentaOld=datos[9];
				
				
				if ((cuentaOld.equals(datos[9]))){ 
					// si el movimiento leido es de la misma cuenta que el anterior movimiento
					// leido, entonces lo integra en la misma cuenta
					
					// acumulacion de saldos
					if (rs.getString(8).equals("1")){
						// es un movimiento al debe
						datos[6]=dosdecimales(rs.getString(9));
						datos[7]=dosdecimales("0");
						// lo suma al saldo
						sumasdebe+=(float)Float.parseFloat(rs.getString(9));
						acumula+=(float)Float.parseFloat(rs.getString(9));
					} else {
						// es un movimiento al haber
						datos[6]=dosdecimales("0");
						datos[7]=dosdecimales(rs.getString(9));
						// lo resta del saldo
						sumashaber+=(float)Float.parseFloat(rs.getString(9));
						acumula-=(float)Float.parseFloat(rs.getString(9));
					}
					datos[8]=dosdecimales(String.valueOf(acumula));
				
				// ******* AQUI SE AÑADE AL ARRAY LIST CADA MOVIMIENTO
					n++;
					datosLeidos.add(new ArrayList<String>());
					datosLeidos.get(n).add(datos[9]);
					datosLeidos.get(n).add(datos[0]);
					datosLeidos.get(n).add(datos[1]);
					datosLeidos.get(n).add(datos[2]);
					datosLeidos.get(n).add(datos[3]);
					datosLeidos.get(n).add(datos[4]);
	    			datosLeidos.get(n).add(datos[5]);
	    			datosLeidos.get(n).add(datos[6]);
	    			datosLeidos.get(n).add(datos[7]);
	    			datosLeidos.get(n).add(datos[8]);
				} else {
				
					// si el movimiento no coincide con la cuenta del movimiento anterior
					// se cambia el control (cuentaOld), se cierra la cuenta y se abre
					// una cabecera nueva
						
					// cambia el cuentaOld
					cuentaOld=datos[9];
						
					// cuando cambia de cuenta guarda los acumulados
					sumas1[6]=dosdecimales(String.valueOf(sumasdebe));
					sumas1[7]=dosdecimales(String.valueOf(sumashaber));
					sumas1[8]="";
					sumasdebe=0;
					sumashaber=0;
					sumas2[8]=dosdecimales(String.valueOf(acumula));
					acumula=0;
					
					// acumulacion de los nuevos saldos
					if (rs.getString(8).equals("1")){
						// es un movimiento al debe
						datos[6]=dosdecimales(rs.getString(9));
						datos[7]=dosdecimales("0");
						// lo suma al saldo
						sumasdebe=(float)Float.parseFloat(rs.getString(9));
						acumula=(float)Float.parseFloat(rs.getString(9));
					} else {
						// es un movimiento al haber
						datos[6]=dosdecimales("0");
						datos[7]=dosdecimales(rs.getString(9));
						// lo resta del saldo
						sumashaber=(float)Float.parseFloat(rs.getString(9));
						acumula=-(float)Float.parseFloat(rs.getString(9));
					}
					datos[8]=dosdecimales(String.valueOf(acumula));
					
					// añade los acumulados al array
					n++;
					datosLeidos.add(new ArrayList<String>());
					datosLeidos.get(n).add(context[9]);
		    		datosLeidos.get(n).add(context[0]);
		    		datosLeidos.get(n).add(context[1]);
		    		datosLeidos.get(n).add(context[2]);
		    		datosLeidos.get(n).add(context[3]);
		    		datosLeidos.get(n).add(context[4]);
		    		datosLeidos.get(n).add(context[5]);
		    		datosLeidos.get(n).add(context[6]);
		    		datosLeidos.get(n).add(context[7]);
		    		datosLeidos.get(n).add(context[8]);
					n++;
					datosLeidos.add(new ArrayList<String>());
					datosLeidos.get(n).add(sumas1[9]);
		    		datosLeidos.get(n).add(sumas1[0]);
		    		datosLeidos.get(n).add(sumas1[1]);
		    		datosLeidos.get(n).add(sumas1[2]);
		    		datosLeidos.get(n).add(sumas1[3]);
		    		datosLeidos.get(n).add(sumas1[4]);
		    		datosLeidos.get(n).add(sumas1[5]);
		    		datosLeidos.get(n).add(sumas1[6]);
		    		datosLeidos.get(n).add(sumas1[7]);
		    		datosLeidos.get(n).add(sumas1[8]);
					n++;
					datosLeidos.add(new ArrayList<String>());
					datosLeidos.get(n).add(sumas2[9]);
		    		datosLeidos.get(n).add(sumas2[0]);
		    		datosLeidos.get(n).add(sumas2[1]);
		    		datosLeidos.get(n).add(sumas2[2]);
		    		datosLeidos.get(n).add(sumas2[3]);
		    		datosLeidos.get(n).add(sumas2[4]);
		    		datosLeidos.get(n).add(sumas2[5]);
		    		datosLeidos.get(n).add(sumas2[6]);
		    		datosLeidos.get(n).add(sumas2[7]);
		    		datosLeidos.get(n).add(sumas2[8]);
					// monta la siguiente cabecera
		    		n++;
		    		datosLeidos.add(new ArrayList<String>());
		    		datosLeidos.get(n).add(cabecera1[0]);
		    		datosLeidos.get(n).add(cabecera1[1]);
		    		datosLeidos.get(n).add(cabecera1[2]);
		    		datosLeidos.get(n).add(cabecera1[3]);
		    		datosLeidos.get(n).add(cabecera1[4]);
		    		datosLeidos.get(n).add(cabecera1[5]);
		    		datosLeidos.get(n).add(cabecera1[6]);
		    		datosLeidos.get(n).add(cabecera1[7]);
		    		datosLeidos.get(n).add(cabecera1[8]);
		    		datosLeidos.get(n).add(cabecera1[9]);
		    		n++;
		    		
		    		// si el encontramos el numero de la cuenta en el 
		    		// array de nombres, le ponemos nombre a la cuenta
		    		c=0;
		    		while ( c < namesCta.length) {
			    		if (namesCta[c][0].equals(datos[9])) {
			    			cabecera5[0]=namesCta[c][1];
			    			break;
			    		}
		    			c++;
		    		}

		    		
		    		datosLeidos.add(new ArrayList<String>());
		    		datosLeidos.get(n).add(cabecera5[0]);
		    		datosLeidos.get(n).add(cabecera5[1]);
		    		datosLeidos.get(n).add(cabecera5[2]);
		    		datosLeidos.get(n).add(cabecera5[3]);
		    		datosLeidos.get(n).add(cabecera5[4]);
		    		datosLeidos.get(n).add(cabecera5[5]);
		    		datosLeidos.get(n).add(cabecera5[6]);
		    		datosLeidos.get(n).add(cabecera5[7]);
		    		datosLeidos.get(n).add(cabecera5[8]);
		    		datosLeidos.get(n).add(cabecera5[9]);
					n++;
					datosLeidos.add(new ArrayList<String>());
					datosLeidos.get(n).add(datos[9]);
		    		datosLeidos.get(n).add(datos[0]);
		    		datosLeidos.get(n).add(datos[1]);
		    		datosLeidos.get(n).add(datos[2]);
		    		datosLeidos.get(n).add(datos[3]);
		    		datosLeidos.get(n).add(datos[4]);
		    		datosLeidos.get(n).add(datos[5]);
		    		datosLeidos.get(n).add(datos[6]);
		    		datosLeidos.get(n).add(datos[7]);
		    		datosLeidos.get(n).add(datos[8]);

				}
				
				
			} // fin del while
			
			// hay que grabar la ultima cuenta
			// cuando cambia de cuenta guarda los acumulados
			sumas1[6]=dosdecimales(String.valueOf(sumasdebe));
			sumas1[7]=dosdecimales(String.valueOf(sumashaber));
			sumas1[8]="";
			sumasdebe=0;
			sumashaber=0;
			sumas2[8]=dosdecimales(String.valueOf(acumula));
			acumula=0;
			// añade los acumulados al array final
			datosLeidos.add(new ArrayList<String>());
			datosLeidos.get(n).add(context[9]);
    		datosLeidos.get(n).add(context[0]);
    		datosLeidos.get(n).add(context[1]);
    		datosLeidos.get(n).add(context[2]);
    		datosLeidos.get(n).add(context[3]);
    		datosLeidos.get(n).add(context[4]);
    		datosLeidos.get(n).add(context[5]);
    		datosLeidos.get(n).add(context[6]);
    		datosLeidos.get(n).add(context[7]);
    		datosLeidos.get(n).add(context[8]);
			n++;
			datosLeidos.add(new ArrayList<String>());
			datosLeidos.get(n).add(sumas1[9]);
    		datosLeidos.get(n).add(sumas1[0]);
    		datosLeidos.get(n).add(sumas1[1]);
    		datosLeidos.get(n).add(sumas1[2]);
    		datosLeidos.get(n).add(sumas1[3]);
    		datosLeidos.get(n).add(sumas1[4]);
    		datosLeidos.get(n).add(sumas1[5]);
    		datosLeidos.get(n).add(sumas1[6]);
    		datosLeidos.get(n).add(sumas1[7]);
    		datosLeidos.get(n).add(sumas1[8]);
			n++;
			datosLeidos.add(new ArrayList<String>());
			datosLeidos.get(n).add(sumas2[9]);
    		datosLeidos.get(n).add(sumas2[0]);
    		datosLeidos.get(n).add(sumas2[1]);
    		datosLeidos.get(n).add(sumas2[2]);
    		datosLeidos.get(n).add(sumas2[3]);
    		datosLeidos.get(n).add(sumas2[4]);
    		datosLeidos.get(n).add(sumas2[5]);
    		datosLeidos.get(n).add(sumas2[6]);
    		datosLeidos.get(n).add(sumas2[7]);
    		datosLeidos.get(n).add(sumas2[8]);
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
 
    	// en el final, se transforma el arrayList en un array[n][10]
    	String lectura[][]=new String[datosLeidos.size()][10];
    	
    	for (int i=0;i<datosLeidos.size();i++) {
    		lectura[i]=datosLeidos.get(i).toArray(new String[10]);
    	}
    	// se devuelve el array con la informacion a listar
    	return lectura;
    	
    } // *************** fin del metodo leeMayor



    /* ************************************************************************
     * Este metodo devuelve un array con los numeros y nombres de las cuentas
     * 
     *  Recibe como parametro el keyEmpr de la empresa a buscar, y los numeros
     *  de cuenta inicial y final para buscar sus nombres
     *  
     *  Devuelve un array [n][2] con los nombres de las cuentas existentes
     ************************************************************************* */

    private String[][] getNamesCtas(String keyEmpr,String cta1, String cta2) {
	// TODO Auto-generated method stub
	
	// primero componemos dinamicamente el nombre del fichero diario
	// del cual leeremos los datos
	String fichero="c_"+keyEmpr+"pgc";
	    	
	// asignamos en fase de pruebas el nombre del fichero
//	String fichero="c_a0011pgc";
	
	// instanciamos una conexion
	Connection con= ConnectDB();
	// creamos un objeto statement
	Statement st=null;
	try {
		st=con.createStatement();
	} catch (SQLException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
	// creamos un objeto resulset para recoger los datos
	ResultSet rs=null;
	
	try {
		rs=st.executeQuery("SELECT * FROM "+fichero+" WHERE cuenta>='"+cta1+"' && cuenta<='"+cta2+"' ORDER BY cuenta");
	} catch (SQLException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}

	// creamos el arraylist para guardar los valores leidos
	ArrayList<ArrayList<String>> lista=new ArrayList<ArrayList<String>>(); 
	
	int k=0;
	try {
		while (rs.next()) {
			lista.add(new ArrayList<String>());
			lista.get(k).add(rs.getString(1));
			lista.get(k).add(rs.getString(2));
			k++;
		}
	} catch (SQLException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
	
	// obtenida la informacion, pasamos el arraylist
	// a una matriz para return de los datos
	String names[][]=new String[lista.size()][2];
	for (int i=0;i<lista.size();i++) {
		names[i]=lista.get(i).toArray(new String[2]);
	}
	
	return names;
	
    } // ******** fin del metodo getnamectas
    
    
    
    /* **********************************************************************
     * Este metodo transforma los numeros en numero formato decimal español
     * con dos decimales (p.ej 3.1 -> 3,10  o  25.317 -> 25,32)
     * 
     * Recibe un parametro String con el numero a transformar
     * Devuelve un String con el numero ya transformado
     * 
     * PROCEDIMIENTO:
     * 
     ************************************************************************* */
    
    private String dosdecimales(String cantidad) {
    
    	// creamos la variable de retorno
    	String nuevoNumero="0,00";
    	
    	// redondeamos el numero a dos decimales matematicos
    	float p1=Math.round((float)Float.parseFloat(cantidad)*100);
    	float p2=(float)p1/100;
    	
    	// tomaos el set de formato de numeros italiano, semejante al español
    	NumberFormat nf=NumberFormat.getInstance(Locale.ITALIAN);
    	// indicamos que queremos siempre el mismo numero de decimales
    	nf.setMinimumFractionDigits(2);
    	nf.setMaximumFractionDigits(2);
    	// devolvemos el numero formateado 
    	nuevoNumero=nf.format(p2);
    	
    	
    	/*
    	String nuevoNumero="0";
    	
    	// primero comprobamos si tiene el separador de decimal
    	if (cantidad.contains(".")) {
    		int unaMas=0;
    		// localizamos el punto decimal
    		int puntoDec=cantidad.lastIndexOf(".");
    		// y obtenemos dos cadenas, la parte entera y la parte decimal
    		String cadena1=cantidad.substring(0, puntoDec);
    		String cadena2=cantidad.substring(puntoDec+1);
    		// la parte decimal hay que dejarla en dos decimales
    		// primero el caso de más de tres decimales
    		if (cadena2.length()>2) {
    			// cogemos el 3ºdecimal para redondeo
    			int tercerDec=(int)Integer.parseInt(cadena2.substring(2, 3));
    			int segundoDec=(int)Integer.parseInt(cadena2.substring(1, 2));
    			int primerDec=(int)Integer.parseInt(cadena2.substring(0, 1));
    			// si el tercer decimal es mayor que 5 redondeamos hacia arriba
    			if (tercerDec>=5) segundoDec+=1;
    			// si el segundo decimal es diez (era 9 + 1 de redondeo)
    			// hay que ponerlo a 0 y llevarse uno
    			if (segundoDec==10) {
    				segundoDec=0;
    				primerDec+=1;
    			}
    			// si el primer decimal es diez (era 9 + 1 de redondeo)
    			// hay que ponerlo a 0 y llevarse uno
    			if (primerDec==10) {
    				primerDec=0;
    				unaMas=1;
    			}
    		
    			cadena2=","+String.valueOf(primerDec)+String.valueOf(segundoDec);
    		}
    		
    		// en el caso de que haya solo un decimal
    		if (cadena2.length()==1) {
    			
    			cadena2=","+cadena2+"0";
    			
    		}
    		
    		// en el caso de dos decimales solo hay que poner la coma
    		if (cadena2.length()==2) {
    			
    			cadena2=","+cadena2;
    			
    		}
    		
    		// ahora toca la parte entera
    		// primero comprobamos si nos hemos llevado una por el redondeo
    		if (unaMas==1) {
    			int entera=(int)Integer.parseInt(cadena1);
    			entera+=1;
    			cadena1=String.valueOf(entera);
    		}
    		
    		// finalmente, ponemos el punto de miles a la parte entera
    		int longCadena=cadena1.length();
    		if (cadena1.length()>9) {
    			cadena1=cadena1.substring(0, 3)+"."+cadena1.substring(3,6)+"."+cadena1.substring(3,6);
    		}
    		
    		// componemos el numero uniendo la parte entera y la decimal
    		nuevoNumero=cadena1+cadena2;
    		
    	}  else {
    		
    		// si no tiene separador decimal es tan facil como añadir
    		// la coma decimal y dos ceros
    		
    		nuevoNumero=cantidad+",00";
    		
    	}
    		*/
    	
    	return nuevoNumero;
    }
    
    
    
    
    
    
    
    
    /* **************************************************************************************
     * Metodo que lee los datos del diario para conformar el libro Diario
     * 
     * Recibe como parametros el key de la empresa, la fecha inicial y la fecha final
     * 
     * Devuelve un Array[n][9] con un listado ya confeccionado con cabeceras y sumatorios
     * 
     * PROCEDIMIENTO:
     * A) Con los datos recibidos se componen los datos de la busqueda a realizar
     *    se compone el fichero del diario de la empresa correspondiente, se preparan los
     *    filtros para la busqueda y se hace un SELECT en la DDBB
     * B) Se construyen las cabeceras de cada pagina, teniendo en cuenta las lineas por pagina
     *    introducidas como opcion
     * C) Se leen y componen en el formato adecuado de salida los distintos movimientos
     *    teniendo en cuenta que hay que preparar un final de linea con sumatorios 
     * D) Se transforma el ArrayList en un array[n][9] para devolver la informacion
     * 
     **************************************************************************************** */
    
    public String[][] leeDiario(String keyEmp,String nombreEmp,String fechaIni,String fechaFin,String numLineas) {   	
    	
    	// numero de lineas que tendra nuestro listado
    	int lineasListado=(int)Integer.parseInt(numLineas);
    	
    	// creamos el arrayList bidimensional 	
    	ArrayList<ArrayList<String>> datosLeidos=new ArrayList<ArrayList<String>>();
    	// leemos las variables filtro - modificamos la fecha a formato date
    	String fec1=fechaIni.substring(6)+fechaIni.substring(2, 6)+fechaIni.substring(0, 2);
    	String fec2=fechaFin.substring(6)+fechaFin.substring(2, 6)+fechaFin.substring(0, 2);
    	
    	// primero componemos dinamicamente el nombre del fichero diario
    	// del cual leeremos los datos
    	String anno=fec1.substring(2, 4);
    	String fichero="c_"+keyEmp+anno+"diario";
    	
    	// para obtener el nombre de las cuentas contables
    	// instanciamos el metodo getNamesCtas
    	// y recibimos un array con los numeros y nombres de cuentas
    	// de todo el plan contable
    	String namesCta[][]=getNamesCtas(keyEmp,"1","999999999");
    	
    	if (namesCta==null) {
    		System.err.println("NO HAY NOMBRES PARA LEER");
    	}
    	
    	
    	// instanciamos una conexion
    	Connection con= ConnectDB();
    	// creamos un objeto statement
    	Statement st=null;
    	try {
			st=con.createStatement();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    	// creamos un objeto resulset para recoger los datos
    	ResultSet rs=null;
    	
    	try {
			rs=st.executeQuery("SELECT * FROM "+fichero+" WHERE fecha>='"+fec1+"' && fecha<='"+fec2+"' ORDER BY fecha,numasto,numapunte ");
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			System.err.println("Error intentando select en fichero "+fichero);
			e.printStackTrace();
		}
    	
    	// crea los distintos string[] 
    	// toda pagina tiene seis lineas de cabecera
    	// n lineas de datos
    	// y finalmente una linea de cierre de pagina
    	String cabecera1[]=new String[9];
    	String cabecera2[]=new String[9];
    	String cabecera3[]=new String[9];
    	String cabecera4[]=new String[9];
    	String cabecera5[]=new String[9];
    	String pie1[]=new String[9];
    	String datos[]=new String[9];
    	String sumas1[]=new String[9];
    	
    	// dando formato a datos prefijados de los String
    	cabecera1[4]="LIBRO DIARIO DE CONTABILIDAD";
    	
    	cabecera2[0]="EMPRESA ";
    	cabecera2[4]=nombreEmp; // NOMBRE EMPRESA
    	cabecera2[7]="Ejercicio: ";
    	cabecera2[8]=fec1.substring(0, 4); // AÑO
    	
    	cabecera3[2]="NIF: ";	// NIF DE LA EMPRESA
    	cabecera3[7]="Del: "+fechaIni;
    	cabecera3[8]="Al: "+fechaFin;
    		
    	cabecera5[0]="Asiento";
    	cabecera5[1]="Fecha";
    	cabecera5[2]="Documento";
    	cabecera5[3]="Cuenta";
    	cabecera5[4]="Descripción";
    	cabecera5[5]="Concepto";
    	cabecera5[6]="Contrapartida";
    	cabecera5[7]="Debe";
    	cabecera5[8]="Haber";
    	
    	sumas1[0]="Página ";
    	sumas1[1]=""; 	// numero de pagina
    	sumas1[5]="Sumas debe-haber ........... ";
    	sumas1[7]="";  	// sumas acumuladas debe
    	sumas1[8]="";	// sumas acumuladas haber
    	
    	
    	
    	
    	int n=0;		// LECTURAS DEL FICHERO
    	int mov=0;		// MOVIMIENTOS DE CADA PAGINA
    	int pagina=0;	// PAGINAS DEL LISTADO
    	int c=0;		// CONTROL DE NOMBRE DE CUENTAS LEIDAS
    	
    	try {
    		
    		float acumuladebe=0;				// aqui acumula los saldos
    		float acumulahaber=0;				// aqui acumula los saldos
    		
    		// graba la primera cabecera y empieza
    		datosLeidos.add(new ArrayList<String>());
    		datosLeidos.get(n).add(cabecera1[0]);
    		datosLeidos.get(n).add(cabecera1[1]);
    		datosLeidos.get(n).add(cabecera1[2]);
    		datosLeidos.get(n).add(cabecera1[3]);
    		datosLeidos.get(n).add(cabecera1[4]);
    		datosLeidos.get(n).add(cabecera1[5]);
    		datosLeidos.get(n).add(cabecera1[6]);
    		datosLeidos.get(n).add(cabecera1[7]);
    		datosLeidos.get(n).add(cabecera1[8]);
    		mov++;
    		
    		n++;
    		datosLeidos.add(new ArrayList<String>());
    		datosLeidos.get(n).add(cabecera2[0]);
    		datosLeidos.get(n).add(cabecera2[1]);
    		datosLeidos.get(n).add(cabecera2[2]);
    		datosLeidos.get(n).add(cabecera2[3]);
    		datosLeidos.get(n).add(cabecera2[4]);
    		datosLeidos.get(n).add(cabecera2[5]);
    		datosLeidos.get(n).add(cabecera2[6]);
    		datosLeidos.get(n).add(cabecera2[7]);
    		datosLeidos.get(n).add(cabecera2[8]);
    		mov++;
    		
    		n++;
    		datosLeidos.add(new ArrayList<String>());
    		datosLeidos.get(n).add(cabecera3[0]);
    		datosLeidos.get(n).add(cabecera3[1]);
    		datosLeidos.get(n).add(cabecera3[2]);
    		datosLeidos.get(n).add(cabecera3[3]);
    		datosLeidos.get(n).add(cabecera3[4]);
    		datosLeidos.get(n).add(cabecera3[5]);
    		datosLeidos.get(n).add(cabecera3[6]);
    		datosLeidos.get(n).add(cabecera3[7]);
    		datosLeidos.get(n).add(cabecera3[8]);
    		mov++;
    		
    		n++;
    		datosLeidos.add(new ArrayList<String>());
    		datosLeidos.get(n).add(cabecera4[0]);
    		datosLeidos.get(n).add(cabecera4[1]);
    		datosLeidos.get(n).add(cabecera4[2]);
    		datosLeidos.get(n).add(cabecera4[3]);
    		datosLeidos.get(n).add(cabecera4[4]);
    		datosLeidos.get(n).add(cabecera4[5]);
    		datosLeidos.get(n).add(cabecera4[6]);
    		datosLeidos.get(n).add(cabecera4[7]);
    		datosLeidos.get(n).add(cabecera4[8]);
    		mov++;
    		
    		n++;
    		datosLeidos.add(new ArrayList<String>());
    		datosLeidos.get(n).add(cabecera5[0]);
    		datosLeidos.get(n).add(cabecera5[1]);
    		datosLeidos.get(n).add(cabecera5[2]);
    		datosLeidos.get(n).add(cabecera5[3]);
    		datosLeidos.get(n).add(cabecera5[4]);
    		datosLeidos.get(n).add(cabecera5[5]);
    		datosLeidos.get(n).add(cabecera5[6]);
    		datosLeidos.get(n).add(cabecera5[7]);
    		datosLeidos.get(n).add(cabecera5[8]);
    		mov++;
    		
    		// *** COMIENZA LA LECTURA DE LOS DATOS
    		
			while(rs.next()) {
				
				//******** AQUI SE LEE UN MOVIMIENTO Y ALMACENA EN MATRIZ AUXILIAR
			
				datos[0]=String.valueOf(rs.getInt(1));
				datos[1]=String.valueOf(rs.getDate(3));
				datos[2]=rs.getString(5);
				datos[3]=rs.getString(4);
				
	    		// si el encontramos el numero de la cuenta en el 
	    		// array de nombres, le ponemos nombre a la cuenta
	    		c=0;
	    		while ( c < namesCta.length) {
		    		if (namesCta[c][0].equals(datos[3])) {
		    			datos[4]=namesCta[c][1];
		    			break;
		    		}
	    			c++;
	    		}

				datos[5]=rs.getString(6);
				datos[6]=rs.getString(7);
				// acumulacion de saldos
				if (rs.getString(8).equals("1")){
					// es un movimiento al debe
					datos[7]=dosdecimales(String.valueOf(rs.getFloat(9)));
					datos[8]=dosdecimales("0");
					// lo suma al saldo
					acumuladebe+=rs.getFloat(9);
				} else {
					// es un movimiento al haber
					datos[7]=dosdecimales("0");
					datos[8]=dosdecimales(String.valueOf(rs.getFloat(9)));
					// lo resta del saldo
					acumulahaber+=rs.getFloat(9);
				}
				
				
				
				if ( mov < (lineasListado-3)) { 
					// si el movimiento leido es menor de n movimientos
					// entonces lo integra en la misma pagina
					// y si es mayor, hace una pagina nueva
					mov++;
					
				// ******* AQUI SE AÑADE AL ARRAY LIST CADA MOVIMIENTO
					n++;
					datosLeidos.add(new ArrayList<String>());
					datosLeidos.get(n).add(datos[0]);
					datosLeidos.get(n).add(datos[1]);
					datosLeidos.get(n).add(datos[2]);
					datosLeidos.get(n).add(datos[3]);
					datosLeidos.get(n).add(datos[4]);
	    			datosLeidos.get(n).add(datos[5]);
	    			datosLeidos.get(n).add(datos[6]);
	    			datosLeidos.get(n).add(datos[7]);
	    			datosLeidos.get(n).add(datos[8]);
	    			
				} else {
				
					// si el movimiento supera el numero de lineas
					// entonces cierra la pagina y abre una pagina nueva
					mov=0;
					
					// cuando cambia de cuenta guarda los acumulados
					pagina++;
					sumas1[1]=String.valueOf(pagina);
					sumas1[7]=dosdecimales(String.valueOf(acumuladebe));
					sumas1[8]=dosdecimales(String.valueOf(acumulahaber));
					
					// Añade la ultima linea de datos
					n++;
					datosLeidos.add(new ArrayList<String>());
					datosLeidos.get(n).add(datos[0]);
					datosLeidos.get(n).add(datos[1]);
					datosLeidos.get(n).add(datos[2]);
					datosLeidos.get(n).add(datos[3]);
					datosLeidos.get(n).add(datos[4]);
	    			datosLeidos.get(n).add(datos[5]);
	    			datosLeidos.get(n).add(datos[6]);
	    			datosLeidos.get(n).add(datos[7]);
	    			datosLeidos.get(n).add(datos[8]);
	    			
	    			// separacion
	        		n++;
	        		datosLeidos.add(new ArrayList<String>());
	        		datosLeidos.get(n).add(pie1[0]);
	        		datosLeidos.get(n).add(pie1[1]);
	        		datosLeidos.get(n).add(pie1[2]);
	        		datosLeidos.get(n).add(pie1[3]);
	        		datosLeidos.get(n).add(pie1[4]);
	        		datosLeidos.get(n).add(pie1[5]);
	        		datosLeidos.get(n).add(pie1[6]);
	        		datosLeidos.get(n).add(pie1[7]);
	        		datosLeidos.get(n).add(pie1[8]);
	    			
					// añade los acumulados al array
					n++;
					datosLeidos.add(new ArrayList<String>());
		    		datosLeidos.get(n).add(sumas1[0]);
		    		datosLeidos.get(n).add(sumas1[1]);
		    		datosLeidos.get(n).add(sumas1[2]);
		    		datosLeidos.get(n).add(sumas1[3]);
		    		datosLeidos.get(n).add(sumas1[4]);
		    		datosLeidos.get(n).add(sumas1[5]);
		    		datosLeidos.get(n).add(sumas1[6]);
		    		datosLeidos.get(n).add(sumas1[7]);
		    		datosLeidos.get(n).add(sumas1[8]);
		    		
					// monta la siguiente cabecera
		    		// graba la primera cabecera y empieza
		    		n++;
		    		datosLeidos.add(new ArrayList<String>());
		    		datosLeidos.get(n).add(cabecera1[0]);
		    		datosLeidos.get(n).add(cabecera1[1]);
		    		datosLeidos.get(n).add(cabecera1[2]);
		    		datosLeidos.get(n).add(cabecera1[3]);
		    		datosLeidos.get(n).add(cabecera1[4]);
		    		datosLeidos.get(n).add(cabecera1[5]);
		    		datosLeidos.get(n).add(cabecera1[6]);
		    		datosLeidos.get(n).add(cabecera1[7]);
		    		datosLeidos.get(n).add(cabecera1[8]);
		    		
		    		n++;
		    		datosLeidos.add(new ArrayList<String>());
		    		datosLeidos.get(n).add(cabecera2[0]);
		    		datosLeidos.get(n).add(cabecera2[1]);
		    		datosLeidos.get(n).add(cabecera2[2]);
		    		datosLeidos.get(n).add(cabecera2[3]);
		    		datosLeidos.get(n).add(cabecera2[4]);
		    		datosLeidos.get(n).add(cabecera2[5]);
		    		datosLeidos.get(n).add(cabecera2[6]);
		    		datosLeidos.get(n).add(cabecera2[7]);
		    		datosLeidos.get(n).add(cabecera2[8]);
		    		
		    		n++;
		    		datosLeidos.add(new ArrayList<String>());
		    		datosLeidos.get(n).add(cabecera3[0]);
		    		datosLeidos.get(n).add(cabecera3[1]);
		    		datosLeidos.get(n).add(cabecera3[2]);
		    		datosLeidos.get(n).add(cabecera3[3]);
		    		datosLeidos.get(n).add(cabecera3[4]);
		    		datosLeidos.get(n).add(cabecera3[5]);
		    		datosLeidos.get(n).add(cabecera3[6]);
		    		datosLeidos.get(n).add(cabecera3[7]);
		    		datosLeidos.get(n).add(cabecera3[8]);
		    		
		    		n++;
		    		datosLeidos.add(new ArrayList<String>());
		    		datosLeidos.get(n).add(cabecera4[0]);
		    		datosLeidos.get(n).add(cabecera4[1]);
		    		datosLeidos.get(n).add(cabecera4[2]);
		    		datosLeidos.get(n).add(cabecera4[3]);
		    		datosLeidos.get(n).add(cabecera4[4]);
		    		datosLeidos.get(n).add(cabecera4[5]);
		    		datosLeidos.get(n).add(cabecera4[6]);
		    		datosLeidos.get(n).add(cabecera4[7]);
		    		datosLeidos.get(n).add(cabecera4[8]);
		    		
		    		n++;
		    		datosLeidos.add(new ArrayList<String>());
		    		datosLeidos.get(n).add(cabecera5[0]);
		    		datosLeidos.get(n).add(cabecera5[1]);
		    		datosLeidos.get(n).add(cabecera5[2]);
		    		datosLeidos.get(n).add(cabecera5[3]);
		    		datosLeidos.get(n).add(cabecera5[4]);
		    		datosLeidos.get(n).add(cabecera5[5]);
		    		datosLeidos.get(n).add(cabecera5[6]);
		    		datosLeidos.get(n).add(cabecera5[7]);
		    		datosLeidos.get(n).add(cabecera5[8]);
				} 
				
				
			} // fin del while
			
			// hay que grabar la ultima pagina

			pagina++;
			sumas1[1]=String.valueOf(pagina);
			sumas1[7]=dosdecimales(String.valueOf(acumuladebe));
			sumas1[8]=dosdecimales(String.valueOf(acumulahaber));
			
    		n++;
    		datosLeidos.add(new ArrayList<String>());
    		datosLeidos.get(n).add(pie1[0]);
    		datosLeidos.get(n).add(pie1[1]);
    		datosLeidos.get(n).add(pie1[2]);
    		datosLeidos.get(n).add(pie1[3]);
    		datosLeidos.get(n).add(pie1[4]);
    		datosLeidos.get(n).add(pie1[5]);
    		datosLeidos.get(n).add(pie1[6]);
    		datosLeidos.get(n).add(pie1[7]);
    		datosLeidos.get(n).add(pie1[8]);
			
			// añade los acumulados al array
			n++;
			datosLeidos.add(new ArrayList<String>());
    		datosLeidos.get(n).add(sumas1[0]);
    		datosLeidos.get(n).add(sumas1[1]);
    		datosLeidos.get(n).add(sumas1[2]);
    		datosLeidos.get(n).add(sumas1[3]);
    		datosLeidos.get(n).add(sumas1[4]);
    		datosLeidos.get(n).add(sumas1[5]);
    		datosLeidos.get(n).add(sumas1[6]);
    		datosLeidos.get(n).add(sumas1[7]);
    		datosLeidos.get(n).add(sumas1[8]);
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
 
    	// en el final, se transforma el arrayList en un array[n][10]
    	String lectura[][]=new String[datosLeidos.size()][9];
    	
    	for (int i=0;i<datosLeidos.size();i++) {
    		lectura[i]=datosLeidos.get(i).toArray(new String[9]);
    	}
    	// se devuelve el array con la informacion a listar
    	return lectura;
    	
    } // *************** fin del metodo leeDiario
    
    
    
    /* **************************************************************************************
     * Metodo que lee los datos del diario para conformar el balance de sumas y saldos
     * 
     * Recibe como parametros el key de la empresa, la cuenta inicial y la cuenta final
     * a listar, y el intervalo de fecha inicial y final
     * 
     * Devuelve un Array[n][6] con un listado ya confeccionado con cabeceras y sumatorios
     * 
     * PROCEDIMIENTO:
     * A) Con los datos recibidos se componen los datos de la busqueda a realizar
     *    se compone el fichero del diario de la empresa correspondiente, se preparan los
     *    filtros para la busqueda y se hace un SELECT en la DDBB
     * B) Se preparan las cabeceras y los finales que se imprimiran con cada cuenta
     * C) Se leen y componen en el formato adecuado de salida los distintos movimientos
     *    de cada cuenta. Hay que tener muy en cuenta el caso de el cambio de cuenta para
     *    el tema de acumulados y cabeceras 
     * D) Se transforma el ArrayList en un array[n][10] para devolver la informacion
     * 
     **************************************************************************************** */
    
    public String[][] leeSumasySaldos(String keyEmp,String cuentaIni,String cuentaFin,String fechaIni,String fechaFin, boolean nivel3) {   	
    	
    	// creamos el arrayList bidimensional 	
    	ArrayList<ArrayList<String>> datosLeidos=new ArrayList<ArrayList<String>>();
    	// leemos las variables filtro - modificamos la fecha a formato date
    	String fec1=fechaIni.substring(6)+fechaIni.substring(2, 6)+fechaIni.substring(0, 2);
    	String fec2=fechaFin.substring(6)+fechaFin.substring(2, 6)+fechaFin.substring(0, 2);
    	String cta1=cuentaIni;
    	String cta2=cuentaFin;
    	
    	// primero componemos dinamicamente el nombre del fichero diario
    	// del cual leeremos los datos
    	String anno=fec1.substring(2, 4);
    	String fichero="c_"+keyEmp+anno+"diario";

    	
    	// para obtener el nombre de las cuentas contables
    	// instanciamos el metodo getNamesCtas
    	// y recibimos un array con los numeros y nombres de cuentas

    	// se modifica el primer parametro para que coja tambien
    	// la cuenta de mayor
    	String namesCta[][]=getNamesCtas(keyEmp,cta1.substring(0, 3),cta2);
    	
    	if (namesCta==null) {
    		System.err.println("NO HAY NOMBRES PARA LEER");
    	}
    	
    	
    	// instanciamos una conexion
    	Connection con= ConnectDB();
    	// creamos un objeto statement
    	Statement st=null;
    	try {
			st=con.createStatement();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    	// creamos un objeto resulset para recoger los datos
    	ResultSet rs=null;
    	
    	try {
			rs=st.executeQuery("SELECT * FROM "+fichero+" WHERE fecha>='"+fec1+"' && fecha<='"+fec2+"' && cuenta>='"+cta1+"' && cuenta<='"+cta2+"' ORDER BY cuenta,fecha,numasto,numapunte ");
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			System.err.println("Error intentando select en fichero "+fichero);
			e.printStackTrace();
		}

    	// si han elegido cuentas de nivel 3, se procede a leer
    	// las cuentas a nivel 3
    	String ctasNivel3[][] = null;
    	if (nivel3) {
    		//recibimos un array con sumas y saldos a nivel 3
    		ctasNivel3=leeSumasySaldosNivel3(rs,namesCta);
    		System.out.println("longitud "+ctasNivel3.length);
    	}
    	
    	// crea los distintos string[] para cada cuenta
    	// toda cuenta auxiliar tiene dos lineas de cabecera
    	// n lineas de datos
    	// y finalmente tres lineas de cierre de cuenta
    	String cabecera1[]=new String[6];
    	String cabecera2[]=new String[6];
    	String cabecera3[]=new String[6];
    	String datos[]=new String[6];
    	String sumas1[]=new String[6];
    	String sumas2[]=new String[6];
    	
    	// dando formato a datos prefijados de los String
    	cabecera1[0]="Fecha: "+PantallaPrincipal.Today;
    	cabecera1[1]="Empresa: "+PantallaPrincipal.Company;
    	cabecera1[2]=" ";
    	cabecera1[3]=" ";
    	cabecera1[4]=" ";
    	cabecera1[5]=" ";
    	
    	cabecera2[0]="";
    	cabecera2[1]="Balance de Sumas y Saldos";
    	cabecera2[2]="Desde: ";
    	cabecera2[3]=fechaIni;
    	cabecera2[4]="Hasta: ";
    	cabecera2[5]=fechaFin;
    	
    	cabecera3[0]="Cuenta";
    	cabecera3[1]="Descripción";
    	cabecera3[2]="Sumas Debe";
    	cabecera3[3]="Sumas Haber";
    	cabecera3[4]="Saldos Debe";
    	cabecera3[5]="Saldos Haber";

    	sumas1[0]=" ";
    	sumas1[1]="     SUMAS TOTALES........ ";
    	sumas1[2]=" ";
    	sumas1[3]=" ";
    	sumas1[4]=" ";
    	sumas1[5]=" ";
    	
    	sumas2[0]=" ";
    	sumas2[1]=" ";
    	sumas2[2]=" ";
    	sumas2[3]=" ";
    	sumas2[4]="Diferencia";
    	sumas2[5]=" ";
    	
    	
    	int n=0;	// MOVIMIENTOS LEIDOS
    	int c=0;	// NUMERO DE CUENTAS LEIDAS
    
		String cuentaOld=""; 			// guarda la cuenta leida ultima
		float acumuladoDebe=0;			// aqui acumula sumas al debe del balance
		float acumuladoHaber=0;			// aqui acumula sumas al haber del balance
		float saldosDebe=0;				// aqui acumula saldos acumulados debe del balance
		float saldosHaber=0;			// aqui acumula saldos acumulados haber del balance
		float sumasdebe=0;				// aqui acumula sumas al debe de cada cuenta
		float sumashaber=0;				// aqui acumula sumas al haber de cada cuenta
		float acumula=0;				// aqui acumula los saldos de cada cuenta
		
		int counterNivel3=0;			// contador del nivel 3
    	
    	try {
    		
    		// graba la primera cabecera y empieza
    		datosLeidos.add(new ArrayList<String>());
    		datosLeidos.get(n).add(cabecera1[0]);
    		datosLeidos.get(n).add(cabecera1[1]);
    		datosLeidos.get(n).add(cabecera1[2]);
    		datosLeidos.get(n).add(cabecera1[3]);
    		datosLeidos.get(n).add(cabecera1[4]);
    		datosLeidos.get(n).add(cabecera1[5]);	
    		n++;
    		datosLeidos.add(new ArrayList<String>());
    		datosLeidos.get(n).add(cabecera2[0]);
    		datosLeidos.get(n).add(cabecera2[1]);
    		datosLeidos.get(n).add(cabecera2[2]);
    		datosLeidos.get(n).add(cabecera2[3]);
    		datosLeidos.get(n).add(cabecera2[4]);
    		datosLeidos.get(n).add(cabecera2[5]);
    		n++;
    		datosLeidos.add(new ArrayList<String>());
    		datosLeidos.get(n).add(cabecera3[0]);
    		datosLeidos.get(n).add(cabecera3[1]);
    		datosLeidos.get(n).add(cabecera3[2]);
    		datosLeidos.get(n).add(cabecera3[3]);
    		datosLeidos.get(n).add(cabecera3[4]);
    		datosLeidos.get(n).add(cabecera3[5]);
    		
    		// devolvemos el punto del rs al punto inicial
    		rs.beforeFirst();
			while(rs.next()) {
				
				//******** AQUI SE LEE UN MOVIMIENTO Y ALMACENA EN MATRIZ
				datos[0]=rs.getString(4);
				// si fuera el primer movimiento debe cambiar
				// la variable de control cuentaOld
				if (cuentaOld.isEmpty()||cuentaOld.equals("")) cuentaOld=datos[0];
								
				if ((cuentaOld.equals(datos[0]))){ 
					// si el movimiento leido es de la misma cuenta que el anterior movimiento
					// leido, entonces lo integra en la misma cuenta
					
					// acumulacion de saldos
					if (rs.getString(8).equals("1")){
						// es un movimiento al debe;
						// lo suma al saldo
						sumasdebe+=(float)Float.parseFloat(rs.getString(9));
						//acumula+=(float)Float.parseFloat(rs.getString(9));
					} else {
						// es un movimiento al haber
						// lo resta del saldo
						sumashaber+=(float)Float.parseFloat(rs.getString(9));
					//	acumula-=(float)Float.parseFloat(rs.getString(9));
					}
				

				} else {
				
					// si la cuenta no coincide con la cuenta del movimiento anterior
					// se cambia el control (cuentaOld), se cierra la cuenta y se 
					// imprime la linea del balance
						
					// antes de nada, comprobamos si tenemos que copiar la cuenta 
					// de nivel 3 en el arrayList
					
					if (nivel3) {
						System.out.println("num "+counterNivel3+"**"+ctasNivel3[counterNivel3][0]+"**");
						if ((ctasNivel3[counterNivel3][0].substring(0, 3)).equals(cuentaOld.substring(0, 3))) {
							// esto funciona porque las cuentas de nivel 3 
							// estan por orden de numero al igual que las ctas auxiliares
							n++;
							datosLeidos.add(new ArrayList<String>());
				    		datosLeidos.get(n).add(ctasNivel3[counterNivel3][0]+"****");
				    		datosLeidos.get(n).add("****"+ctasNivel3[counterNivel3][1]);
				    		datosLeidos.get(n).add(ctasNivel3[counterNivel3][2]+" **");
				    		datosLeidos.get(n).add(ctasNivel3[counterNivel3][3]+" **");
				    		datosLeidos.get(n).add(ctasNivel3[counterNivel3][4]+" **");
				    		datosLeidos.get(n).add(ctasNivel3[counterNivel3][5]+" **");
				    		counterNivel3++;
				    		//if (counterNivel3>=ctasNivel3.length) counterNivel3=ctasNivel3.length-1;
						}
					}
					
					
		    		// si el encontramos el numero de la cuenta en el 
		    		// array de nombres, le ponemos nombre a la cuenta
		    		c=0;
		    		while ( c < namesCta.length) {
			    		if (namesCta[c][0].equals(cuentaOld)) {
			    			datos[1]=namesCta[c][1];
			    			break;
			    		}
		    			c++;
		    		}
						
					// cuando cambia de cuenta guarda los acumulados
					datos[2]=dosdecimales(String.valueOf(sumasdebe));
					datos[3]=dosdecimales(String.valueOf(sumashaber));
					acumula=(sumasdebe-sumashaber);
					if (acumula>0) {
						datos[4]=dosdecimales(String.valueOf(acumula));
						datos[5]="0,00";
						saldosDebe+=acumula;
					} else {
						datos[4]="0,00";
						datos[5]=dosdecimales(String.valueOf(-acumula));
						saldosHaber+=(-acumula);
					}
								
					// operaciones sobre acumulados
					acumuladoDebe+=sumasdebe;
					acumuladoHaber+=sumashaber;
					sumasdebe=0;
					sumashaber=0;				
					acumula=0;

					// imprime la linea
					n++;
					datosLeidos.add(new ArrayList<String>());
		    		datosLeidos.get(n).add(cuentaOld);
		    		datosLeidos.get(n).add(datos[1]);
		    		datosLeidos.get(n).add(datos[2]);
		    		datosLeidos.get(n).add(datos[3]);
		    		datosLeidos.get(n).add(datos[4]);
		    		datosLeidos.get(n).add(datos[5]);

					// cambia el cuentaOld
					cuentaOld=datos[0];
					
					// y ahora guardamos la nueva informacion
					// acumulacion de saldos
					if (rs.getString(8).equals("1")){
						// es un movimiento al debe;
						// lo suma al saldo
						sumasdebe+=(float)Float.parseFloat(rs.getString(9));

					} else {
						// es un movimiento al haber
						// lo resta del saldo
						sumashaber+=(float)Float.parseFloat(rs.getString(9));

					}
				}
		
			} // fin del while
			
			// hay que grabar la ultima cuenta
			// cuando cambia de cuenta guarda los acumulados
			// cambia el cuentaOld
			cuentaOld=datos[0];
			
			if (nivel3 && counterNivel3!=ctasNivel3.length) {
				System.out.println("num "+counterNivel3+"**"+ctasNivel3[counterNivel3][0]+"**");
				if ((ctasNivel3[counterNivel3][0].substring(0, 3)).equals(cuentaOld.substring(0, 3))) {
					// esto funciona porque las cuentas de nivel 3 
					// estan por orden de numero al igual que las ctas auxiliares
					n++;
					datosLeidos.add(new ArrayList<String>());
		    		datosLeidos.get(n).add(ctasNivel3[counterNivel3][0]+"****");
		    		datosLeidos.get(n).add("****"+ctasNivel3[counterNivel3][1]);
		    		datosLeidos.get(n).add(ctasNivel3[counterNivel3][2]+" **");
		    		datosLeidos.get(n).add(ctasNivel3[counterNivel3][3]+" **");
		    		datosLeidos.get(n).add(ctasNivel3[counterNivel3][4]+" **");
		    		datosLeidos.get(n).add(ctasNivel3[counterNivel3][5]+" **");

				}
			}

				
			// cuando cambia de cuenta guarda los acumulados
			datos[2]=dosdecimales(String.valueOf(sumasdebe));
			datos[3]=dosdecimales(String.valueOf(sumashaber));
			acumula=(sumasdebe-sumashaber);
			if (acumula>0) {
				datos[4]=dosdecimales(String.valueOf(acumula));
				datos[5]="0,00";
				saldosDebe+=acumula;
			} else {
				datos[5]=dosdecimales(String.valueOf(-acumula));
				datos[4]="0,00";
				saldosHaber+=(-acumula);
			}
			
			// operaciones sobre acumulados
			acumuladoDebe+=sumasdebe;
			acumuladoHaber+=sumashaber;
			
			sumasdebe=0;
			sumashaber=0;				
			acumula=0;

    		// si el encontramos el numero de la cuenta en el 
    		// array de nombres, le ponemos nombre a la cuenta
    		c=0;
    		while ( c < namesCta.length) {
	    		if (namesCta[c][0].equals(datos[0])) {
	    			datos[1]=namesCta[c][1];
	    			break;
	    		}
    			c++;
    		}

			// imprime la ultima linea
			n++;
			datosLeidos.add(new ArrayList<String>());
    		datosLeidos.get(n).add(datos[0]);
    		datosLeidos.get(n).add(datos[1]);
    		datosLeidos.get(n).add(datos[2]);
    		datosLeidos.get(n).add(datos[3]);
    		datosLeidos.get(n).add(datos[4]);
    		datosLeidos.get(n).add(datos[5]);		
			
    		// preparamos info
    		sumas1[2]=dosdecimales(String.valueOf(acumuladoDebe));
    		sumas1[3]=dosdecimales(String.valueOf(acumuladoHaber));
    		sumas1[4]=dosdecimales(String.valueOf(saldosDebe));
    		sumas1[5]=dosdecimales(String.valueOf(saldosHaber));
			// añade los acumulados al array final
    		n++;
    		datosLeidos.add(new ArrayList<String>());
    		datosLeidos.get(n).add(sumas1[0]);
    		datosLeidos.get(n).add(sumas1[1]);
    		datosLeidos.get(n).add(sumas1[2]);
    		datosLeidos.get(n).add(sumas1[3]);
    		datosLeidos.get(n).add(sumas1[4]);
    		datosLeidos.get(n).add(sumas1[5]);
    		// preparamos info
    		
    		sumas2[5]=dosdecimales(String.valueOf(-(saldosHaber-saldosDebe)));
    		// añade los acumulados al array final
			n++;
			datosLeidos.add(new ArrayList<String>());
    		datosLeidos.get(n).add(sumas2[0]);
    		datosLeidos.get(n).add(sumas2[1]);
    		datosLeidos.get(n).add(sumas2[2]);
    		datosLeidos.get(n).add(sumas2[3]);
    		datosLeidos.get(n).add(sumas2[4]);
    		datosLeidos.get(n).add(sumas2[5]);
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
 
    	// en el final, se transforma el arrayList en un array[n][10]
    	String lectura[][]=new String[datosLeidos.size()][6];
    	
    	for (int i=0;i<datosLeidos.size();i++) {
    		lectura[i]=datosLeidos.get(i).toArray(new String[6]);
    	}
    	// se devuelve el array con la informacion a listar
    	return lectura;
    	
    } //  fin del metodo leeSumasySaldos
    
    
    
    /* **************************************************************************************
     * Metodo que lee los datos del diario para conformar el balance de sumas y saldos
     * 
     * Recibe como parametros el key de la empresa, la cuenta inicial y la cuenta final
     * a listar, y el intervalo de fecha inicial y final
     * 
     * Devuelve un Array[n][6] con un listado ya confeccionado con cabeceras y sumatorios
     * 
     * PROCEDIMIENTO:
     * A) Con los datos recibidos se componen los datos de la busqueda a realizar
     *    se compone el fichero del diario de la empresa correspondiente, se preparan los
     *    filtros para la busqueda y se hace un SELECT en la DDBB
     * B) Se preparan las cabeceras y los finales que se imprimiran con cada cuenta
     * C) Se leen y componen en el formato adecuado de salida los distintos movimientos
     *    de cada cuenta. Hay que tener muy en cuenta el caso de el cambio de cuenta para
     *    el tema de acumulados y cabeceras 
     * D) Se transforma el ArrayList en un array[n][10] para devolver la informacion
     * 
     **************************************************************************************** */
    
    public String[][] leeSumasySaldosNivel3(ResultSet rs,String namesCta[][]) {   	
    	
    	// creamos el arrayList bidimensional 	
    	ArrayList<ArrayList<String>> datosNivel3=new ArrayList<ArrayList<String>>();
    	
    	// crea los distintos string[] para cada cuenta
    	// toda cuenta auxiliar tiene dos lineas de cabecera
    	// n lineas de datos
    	// y finalmente tres lineas de cierre de cuenta

    	String cnivel3[]=new String[6];
    	
    	cnivel3[0]="0";
    	cnivel3[1]="0";
    	cnivel3[2]="0";
    	cnivel3[3]="0";
    	cnivel3[4]="0";
    	cnivel3[5]="0";
    	
    	
    	int n=0;	// MOVIMIENTOS LEIDOS
    	int c=0;	// NUMERO DE CUENTAS LEIDAS
    
		String cuentaNew="";			// guarda la cuenta auxiliar leida nueva
		String cuentaOld3=""; 			// guarda la cuenta leida ultima
		
		float acumula=0;				// aqui acumula los saldos de cada cuenta
		float saldosDebe3=0;			// aqui acumula saldos acumulados debe del balance nivel 3
		float saldosHaber3=0;			// aqui acumula saldos acumulados haber del balance nivel 3
		float sumasDebe3=0;				// aqui acumula sumas al debe de cada cuenta nivel 3
		float sumasHaber3=0;			// aqui acumula sumas al haber de cada cuenta nivel 3
    	
    	try {
    		
			while(rs.next()) {
				
				//******** AQUI SE LEE UN MOVIMIENTO Y ALMACENA EN MATRIZ
				cuentaNew=rs.getString(4).substring(0, 3);
				// si fuera el primer movimiento debe cambiar
				// la variable de control cuentaOld
				if (cuentaOld3.isEmpty()||cuentaOld3.equals("")) {
					cuentaOld3=cuentaNew;
				}
								
				if ((cuentaOld3.equals(cuentaNew))){ 
					// si el movimiento leido es de la misma cuenta que el anterior movimiento
					// leido, entonces lo integra en la misma cuenta
					
					// acumulacion de saldos
					if (rs.getString(8).equals("1")){
						// es un movimiento al debe;
						// lo suma al saldo
						sumasDebe3+=(float)Float.parseFloat(rs.getString(9));
					} else {
						// es un movimiento al haber
						// lo resta del saldo
						sumasHaber3+=(float)Float.parseFloat(rs.getString(9));
					}
				

				} else {
				
					// si la cuenta no coincide con la cuenta del movimiento anterior
					// se cambia el control (cuentaOld), se cierra la cuenta y se 
					// imprime la linea del balance
						
		    		// si el encontramos el numero de la cuenta en el 
		    		// array de nombres, le ponemos nombre a la cuenta
		    		c=0;
		    		while ( c < namesCta.length) {
			    		if (namesCta[c][0].equals(cuentaOld3)) {
			    			System.out.println(namesCta[c][0]+namesCta[c][1]);
			    			cnivel3[1]=namesCta[c][1];
			    			break;
			    		}
		    			c++;
		    		}
						
					// cuando cambia de cuenta guarda los acumulados
					acumula=(sumasDebe3-sumasHaber3);
					if (acumula>0) {
						saldosDebe3=acumula;
					} else {
						saldosHaber3=-acumula;
					}
					
					cnivel3[0]=String.valueOf(cuentaOld3);
					cnivel3[2]=dosdecimales(String.valueOf(sumasDebe3));
					cnivel3[3]=dosdecimales(String.valueOf(sumasHaber3));
					cnivel3[4]=dosdecimales(String.valueOf(saldosDebe3));
					cnivel3[5]=dosdecimales(String.valueOf(saldosHaber3));
						
					// imprime la linea			
					datosNivel3.add(new ArrayList<String>());
			    	datosNivel3.get(n).add(cnivel3[0]);
			    	datosNivel3.get(n).add(cnivel3[1]);
			    	datosNivel3.get(n).add(cnivel3[2]);
			    	datosNivel3.get(n).add(cnivel3[3]);
			    	datosNivel3.get(n).add(cnivel3[4]);
			    	datosNivel3.get(n).add(cnivel3[5]);
			    	n++;
					
					// operaciones sobre acumulados				
					acumula=0;
					sumasDebe3=0;
					sumasHaber3=0;
					saldosDebe3=0;
					saldosHaber3=0;

					// cambia el cuentaOld
					cuentaOld3=cuentaNew;
					
					// y ahora guardamos la nueva informacion
					// acumulacion de saldos
					if (rs.getString(8).equals("1")){
						// es un movimiento al debe;
						// lo suma al saldo
						sumasDebe3+=(float)Float.parseFloat(rs.getString(9));
					} else {
						// es un movimiento al haber
						// lo resta del saldo
						sumasHaber3+=(float)Float.parseFloat(rs.getString(9));
					}
				}							
			} // fin del while
			
			// hay que grabar la ultima cuenta
			// cuando cambia de cuenta guarda los acumulados

			// si el encontramos el numero de la cuenta en el 
    		// array de nombres, le ponemos nombre a la cuenta
    		c=0;
    		while ( c < namesCta.length) {
	    		if (namesCta[c][0].equals(cuentaOld3)) {
	    			cnivel3[1]=namesCta[c][1];
	    			break;
	    		}
    			c++;
    		}
				
			// cuando cambia de cuenta guarda los acumulados
			acumula=(sumasDebe3-sumasHaber3);
			if (acumula>0) {
				saldosDebe3=acumula;
			} else {
				saldosHaber3=-acumula;
			}
			
			cnivel3[0]=String.valueOf(cuentaOld3);
			cnivel3[2]=dosdecimales(String.valueOf(sumasDebe3));
			cnivel3[3]=dosdecimales(String.valueOf(sumasHaber3));
			cnivel3[4]=dosdecimales(String.valueOf(saldosDebe3));
			cnivel3[5]=dosdecimales(String.valueOf(saldosHaber3));
				
			// imprime la linea			
			datosNivel3.add(new ArrayList<String>());
	    	datosNivel3.get(n).add(cnivel3[0]);
	    	datosNivel3.get(n).add(cnivel3[1]);
	    	datosNivel3.get(n).add(cnivel3[2]);
	    	datosNivel3.get(n).add(cnivel3[3]);
	    	datosNivel3.get(n).add(cnivel3[4]);
	    	datosNivel3.get(n).add(cnivel3[5]);

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
 
    	// en el final, se transforma el arrayList en un array[n][6]
    	String lectura[][]=new String[datosNivel3.size()][6];
    	
    	for (int i=0;i<datosNivel3.size();i++) {
    		lectura[i]=datosNivel3.get(i).toArray(new String[6]);
    	}
    	// se devuelve el array con la informacion a listar
    	return lectura;
    	
    } //  fin del metodo leeSumasySaldosNivel3
    
    
    
    /* **************************************************************************************
     * Metodo que lee los datos del diario para conformar el balance de sumas y saldos
     * 
     * Recibe como parametros el key de la empresa, la cuenta inicial y la cuenta final
     * a listar, y el intervalo de fecha inicial y final
     * 
     * Devuelve un Array[n][6] con un listado ya confeccionado con cabeceras y sumatorios
     * 
     * PROCEDIMIENTO:
     * A) Con los datos recibidos se componen los datos de la busqueda a realizar
     *    se compone el fichero del diario de la empresa correspondiente, se preparan los
     *    filtros para la busqueda y se hace un SELECT en la DDBB
     * B) Se preparan las cabeceras y los finales que se imprimiran con cada cuenta
     * C) Se leen y componen en el formato adecuado de salida los distintos movimientos
     *    de cada cuenta. Hay que tener muy en cuenta el caso de el cambio de cuenta para
     *    el tema de acumulados y cabeceras 
     * D) Se transforma el ArrayList en un array[n][10] para devolver la informacion
     * 
     **************************************************************************************** */
    
    public String[][] leeResultados(String keyEmp,String fechaIni, String fechaFin) {   	
    	
    	// creamos el arrayList bidimensional 	
    	ArrayList<ArrayList<String>> datosLeidos=new ArrayList<ArrayList<String>>();
    	// leemos las variables filtro - modificamos la fecha a formato date
    	String fec1=fechaIni.substring(6)+fechaIni.substring(2, 6)+fechaIni.substring(0, 2);
    	String fec2=fechaFin.substring(6)+fechaFin.substring(2, 6)+fechaFin.substring(0, 2);
    	// asignamos manualmente los límites de cuentas de resultados
    	String cta1="600";
    	String cta2="799999999";
    	
    	// primero componemos dinamicamente el nombre del fichero diario
    	// del cual leeremos los datos
    	String anno=fec1.substring(2, 4);
    	String fichero="c_"+keyEmp+anno+"diario";

    	
    	// para obtener el nombre de las cuentas contables
    	// instanciamos el metodo getNamesCtas
    	// y recibimos un array con los numeros y nombres de cuentas

    	// se modifica el primer parametro para que coja tambien
    	// la cuenta de mayor
    	String namesCta[][]=getNamesCtas(keyEmp,cta1.substring(0, 3),cta2);
    	
    	if (namesCta==null) {
    		System.err.println("NO HAY NOMBRES PARA LEER");
    	}
    	
    	
    	// instanciamos una conexion
    	Connection con= ConnectDB();
    	// creamos un objeto statement
    	Statement st=null;
    	try {
			st=con.createStatement();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    	// creamos un objeto resulset para recoger los datos
    	ResultSet rs=null;
    	
    	try {
			rs=st.executeQuery("SELECT * FROM "+fichero+" WHERE fecha>='"+fec1+"' && fecha<='"+fec2+"' && cuenta>='"+cta1+"' && cuenta<='"+cta2+"' ORDER BY cuenta,fecha,numasto,numapunte ");
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			System.err.println("Error intentando select en fichero "+fichero);
			e.printStackTrace();
		}

    	// La cuenta de resultado se ejecuta con cuentas de nivel 3
    	// las cuentas a nivel 3
    	String ctasNivel3[][] = null;
    	//recibimos un array con sumas y saldos a nivel 3
    	ctasNivel3=leeSumasySaldosNivel3(rs,namesCta);
    
    	
    	// crea los distintos string[] para cada cuenta
    	// toda cuenta auxiliar tiene cinco lineas de cabecera
    	// n lineas de datos
    	String cabecera1[]=new String[5];
    	String cabecera2[]=new String[5];
    	String cabecera3[]=new String[5];
    	String cabecera4[]=new String[5];
    	String cabecera5[]=new String[5];
    	
    	String sumas1[]=new String[5];
    	String sumas2[]=new String[5];
    	String sumas3[]=new String[5];
    	String sumas4[]=new String[5];
    	String sumas5[]=new String[5];
    	String sumas6[]=new String[5];
    	String sumas7[]=new String[5];
    	String sumas8[]=new String[5];
    	String sumas9[]=new String[5];
    	String sumas10[]=new String[5];
    	String sumas11[]=new String[5];
    	String sumas12[]=new String[5];
    	String sumas13[]=new String[5];
    	String sumas14[]=new String[5];
    	String sumas15[]=new String[5];
    	String sumas16[]=new String[5];
    	String sumas17[]=new String[5];
    	String sumas18[]=new String[5];
    	
    	String sumasA[]=new String[5];
    	String sumasB[]=new String[5];
    	String sumasC[]=new String[5];
    	String sumasD[]=new String[5];
    	
    	// dando formato a datos prefijados de los String
    	cabecera1[0]="Fecha: "+PantallaPrincipal.Today;
    	cabecera1[1]="Empresa: "+PantallaPrincipal.Company;
    	cabecera1[2]=" ";
    	cabecera1[3]=" ";
    	cabecera1[4]=" ";
    	
    	cabecera2[0]="";
    	cabecera2[1]="CUENTA DE PÉRDIDAS Y GANANCIAS PYMES EJERCICIO "+PantallaPrincipal.Year;
    	cabecera2[2]=" ";
    	cabecera2[3]=" ";
    	cabecera2[4]=" ";
    	
    	cabecera3[0]=" ";
    	cabecera3[1]=" ";
    	cabecera3[2]=" ";
    	cabecera3[3]=" ";
    	cabecera3[4]=" ";

    	cabecera4[0]="Nº CUENTAS";
    	cabecera4[1]=" ";
    	cabecera4[2]="AÑO "+PantallaPrincipal.Year;
    	cabecera4[3]="AÑO "+String.valueOf((int)Integer.parseInt(PantallaPrincipal.Year)-1);
    	cabecera4[4]=" ";
    	
    	cabecera5[0]=" ";
    	cabecera5[1]=" ";
    	cabecera5[2]=" ";
    	cabecera5[3]=" ";
    	cabecera5[4]=" ";
    	
    	sumas1[0]=" ";
    	sumas1[1]=" 1. Importe neto de la cifra de negocio";
    	sumas1[2]=" ";
    	sumas1[3]=" ";
    	sumas1[4]=" ";
    	
    	sumas2[0]=" 2. Variación existencias prod. terminados o curso";
    	sumas2[1]=" ";
    	sumas2[2]=" ";
    	sumas2[3]=" ";
    	sumas2[4]=" ";

    	sumas3[0]=" ";
    	sumas3[1]=" 3. Trabajos realizados para su activo";
    	sumas3[2]=" ";
    	sumas3[3]=" ";
    	sumas3[4]=" ";
    	
    	sumas4[0]=" ";
    	sumas4[1]=" 4. Aprovisionamientos";
    	sumas4[2]=" ";
    	sumas4[3]=" ";
    	sumas4[4]=" ";
    	
    	sumas5[0]=" ";
    	sumas5[1]=" 5. Otros ingresos de explotación ";
    	sumas5[2]=" ";
    	sumas5[3]=" ";
    	sumas5[4]=" ";
    	
    	sumas6[0]=" ";
    	sumas6[1]=" 6. Gastos de personal ";
    	sumas6[2]=" ";
    	sumas6[3]=" ";
    	sumas6[4]=" ";
    	
    	sumas7[0]=" ";
    	sumas7[1]=" 7. Otros gastos de explotación ";
    	sumas7[2]=" ";
    	sumas7[3]=" ";
    	sumas7[4]=" ";
    	
    	sumas8[0]=" ";
    	sumas8[1]=" 8. Amortización del inmovilizado ";
    	sumas8[2]=" ";
    	sumas8[3]=" ";
    	sumas8[4]=" ";
    	
    	sumas9[0]=" ";
    	sumas9[1]=" 9. Imputación subvenciones inmov. no financiero ";
    	sumas9[2]=" ";
    	sumas9[3]=" ";
    	sumas9[4]=" ";
    	
    	sumas10[0]=" ";
    	sumas10[1]="10. Excesos de provisiones ";
    	sumas10[2]=" ";
    	sumas10[3]=" ";
    	sumas10[4]=" ";
    	
    	sumas11[0]=" ";
    	sumas11[1]="11. Deterioro rsdo. por enajenación inmovilizado ";
    	sumas11[2]=" ";
    	sumas11[3]=" ";
    	sumas11[4]=" ";
    	
    	sumas12[0]=" ";
    	sumas12[1]="12. Otros resultados ";
    	sumas12[2]=" ";
    	sumas12[3]=" ";
    	sumas12[4]=" ";
    	
    	sumas13[0]=" ";
    	sumas13[1]="13. Ingresos financieros ";
    	sumas13[2]=" ";
    	sumas13[3]=" ";
    	sumas13[4]=" ";
    	
    	sumas14[0]=" ";
    	sumas14[1]="14. Gastos financieros ";
    	sumas14[2]=" ";
    	sumas14[3]=" ";
    	sumas14[4]=" ";
    	
    	sumas15[0]=" ";
    	sumas15[1]="15. Variación valor razonable instr. financieros ";
    	sumas15[2]=" ";
    	sumas15[3]=" ";
    	sumas15[4]=" ";
    	
    	sumas16[0]=" ";
    	sumas16[1]="16. Diferencias de cambio ";
    	sumas16[2]=" ";
    	sumas16[3]=" ";
    	sumas16[4]=" ";
    	
    	sumas17[0]=" ";
    	sumas17[1]="17. Deterioro y resultado enajenación instr.financieros ";
    	sumas17[2]=" ";
    	sumas17[3]=" ";
    	sumas17[4]=" ";
    	
    	sumas18[0]=" ";
    	sumas18[1]="18. Impuesto sobre beneficios ";
    	sumas18[2]=" ";
    	sumas18[3]=" ";
    	sumas18[4]=" ";
 
    	sumasA[0]=" ";
    	sumasA[1]="A) RESULTADO DE EXPLOTACION";
    	sumasA[2]=" ";
    	sumasA[3]=" ";
    	sumasA[4]=" ";
    	
    	sumasB[0]=" ";
    	sumasB[1]="B) RESULTADO FINANCIERO";
    	sumasB[2]=" ";
    	sumasB[3]=" ";
    	sumasB[4]=" ";
    	
    	sumasC[0]=" ";
    	sumasC[1]="C) RESULTADO ANTES DE IMPUESTOS";
    	sumasC[2]=" ";
    	sumasC[3]=" ";
    	sumasC[4]=" ";
    	
    	sumasD[0]=" ";
    	sumasD[1]="D) RESULTADO DEL EJERCICIO";
    	sumasD[2]=" ";
    	sumasD[3]=" ";
    	sumasD[4]=" ";
    	
    	
    	int n=0;	// MOVIMIENTOS LEIDOS
    

    	
    	try {
    		

    		
    		// devolvemos el punto del rs al punto inicial
    		rs.beforeFirst();
			while(rs.next()) {
				

		
			} // fin del while
			
			
    		// grabamos las cabeceras
    		datosLeidos.add(new ArrayList<String>());
    		datosLeidos.get(n).add(cabecera1[0]);
    		datosLeidos.get(n).add(cabecera1[1]);
    		datosLeidos.get(n).add(cabecera1[2]);
    		datosLeidos.get(n).add(cabecera1[3]);
    		datosLeidos.get(n).add(cabecera1[4]);	
    		n++;
    		datosLeidos.add(new ArrayList<String>());
    		datosLeidos.get(n).add(cabecera2[0]);
    		datosLeidos.get(n).add(cabecera2[1]);
    		datosLeidos.get(n).add(cabecera2[2]);
    		datosLeidos.get(n).add(cabecera2[3]);
    		datosLeidos.get(n).add(cabecera2[4]);
    		n++;
    		datosLeidos.add(new ArrayList<String>());
    		datosLeidos.get(n).add(cabecera3[0]);
    		datosLeidos.get(n).add(cabecera3[1]);
    		datosLeidos.get(n).add(cabecera3[2]);
    		datosLeidos.get(n).add(cabecera3[3]);
    		datosLeidos.get(n).add(cabecera3[4]);
    		n++;
    		datosLeidos.add(new ArrayList<String>());
    		datosLeidos.get(n).add(cabecera4[0]);
    		datosLeidos.get(n).add(cabecera4[1]);
    		datosLeidos.get(n).add(cabecera4[2]);
    		datosLeidos.get(n).add(cabecera4[3]);
    		datosLeidos.get(n).add(cabecera4[4]);
    		n++;
    		datosLeidos.add(new ArrayList<String>());
    		datosLeidos.get(n).add(cabecera5[0]);
    		datosLeidos.get(n).add(cabecera5[1]);
    		datosLeidos.get(n).add(cabecera5[2]);
    		datosLeidos.get(n).add(cabecera5[3]);
    		datosLeidos.get(n).add(cabecera5[4]);
			
    		// grabamos las lineas
    		n++;
    		datosLeidos.add(new ArrayList<String>());
    		datosLeidos.get(n).add(sumas1[0]);
    		datosLeidos.get(n).add(sumas1[1]);
    		datosLeidos.get(n).add(sumas1[2]);
    		datosLeidos.get(n).add(sumas1[3]);
    		datosLeidos.get(n).add(sumas1[4]);
			n++;
			datosLeidos.add(new ArrayList<String>());
    		datosLeidos.get(n).add(sumas2[0]);
    		datosLeidos.get(n).add(sumas2[1]);
    		datosLeidos.get(n).add(sumas2[2]);
    		datosLeidos.get(n).add(sumas2[3]);
    		datosLeidos.get(n).add(sumas2[4]);
			n++;
			datosLeidos.add(new ArrayList<String>());
    		datosLeidos.get(n).add(sumas3[0]);
    		datosLeidos.get(n).add(sumas3[1]);
    		datosLeidos.get(n).add(sumas3[2]);
    		datosLeidos.get(n).add(sumas3[3]);
    		datosLeidos.get(n).add(sumas3[4]);
			n++;
			datosLeidos.add(new ArrayList<String>());
    		datosLeidos.get(n).add(sumas4[0]);
    		datosLeidos.get(n).add(sumas4[1]);
    		datosLeidos.get(n).add(sumas4[2]);
    		datosLeidos.get(n).add(sumas4[3]);
    		datosLeidos.get(n).add(sumas4[4]);
			n++;
			datosLeidos.add(new ArrayList<String>());
    		datosLeidos.get(n).add(sumas5[0]);
    		datosLeidos.get(n).add(sumas5[1]);
    		datosLeidos.get(n).add(sumas5[2]);
    		datosLeidos.get(n).add(sumas5[3]);
    		datosLeidos.get(n).add(sumas5[4]);
			n++;
			datosLeidos.add(new ArrayList<String>());
    		datosLeidos.get(n).add(sumas6[0]);
    		datosLeidos.get(n).add(sumas6[1]);
    		datosLeidos.get(n).add(sumas6[2]);
    		datosLeidos.get(n).add(sumas6[3]);
    		datosLeidos.get(n).add(sumas6[4]);
			n++;
			datosLeidos.add(new ArrayList<String>());
    		datosLeidos.get(n).add(sumas7[0]);
    		datosLeidos.get(n).add(sumas7[1]);
    		datosLeidos.get(n).add(sumas7[2]);
    		datosLeidos.get(n).add(sumas7[3]);
    		datosLeidos.get(n).add(sumas7[4]);
			n++;
			datosLeidos.add(new ArrayList<String>());
    		datosLeidos.get(n).add(sumas8[0]);
    		datosLeidos.get(n).add(sumas8[1]);
    		datosLeidos.get(n).add(sumas8[2]);
    		datosLeidos.get(n).add(sumas8[3]);
    		datosLeidos.get(n).add(sumas8[4]);
			n++;
			datosLeidos.add(new ArrayList<String>());
    		datosLeidos.get(n).add(sumas9[0]);
    		datosLeidos.get(n).add(sumas9[1]);
    		datosLeidos.get(n).add(sumas9[2]);
    		datosLeidos.get(n).add(sumas9[3]);
    		datosLeidos.get(n).add(sumas9[4]);
			n++;
			datosLeidos.add(new ArrayList<String>());
    		datosLeidos.get(n).add(sumas10[0]);
    		datosLeidos.get(n).add(sumas10[1]);
    		datosLeidos.get(n).add(sumas10[2]);
    		datosLeidos.get(n).add(sumas10[3]);
    		datosLeidos.get(n).add(sumas10[4]);
			n++;
			datosLeidos.add(new ArrayList<String>());
    		datosLeidos.get(n).add(sumas11[0]);
    		datosLeidos.get(n).add(sumas11[1]);
    		datosLeidos.get(n).add(sumas11[2]);
    		datosLeidos.get(n).add(sumas11[3]);
    		datosLeidos.get(n).add(sumas11[4]);
			n++;
			datosLeidos.add(new ArrayList<String>());
    		datosLeidos.get(n).add(sumas12[0]);
    		datosLeidos.get(n).add(sumas12[1]);
    		datosLeidos.get(n).add(sumas12[2]);
    		datosLeidos.get(n).add(sumas12[3]);
    		datosLeidos.get(n).add(sumas12[4]);
    		
			n++;
			datosLeidos.add(new ArrayList<String>());
    		datosLeidos.get(n).add(sumasA[0]);
    		datosLeidos.get(n).add(sumasA[1]);
    		datosLeidos.get(n).add(sumasA[2]);
    		datosLeidos.get(n).add(sumasA[3]);
    		datosLeidos.get(n).add(sumasA[4]);
    		
			n++;
			datosLeidos.add(new ArrayList<String>());
    		datosLeidos.get(n).add(sumas13[0]);
    		datosLeidos.get(n).add(sumas13[1]);
    		datosLeidos.get(n).add(sumas13[2]);
    		datosLeidos.get(n).add(sumas13[3]);
    		datosLeidos.get(n).add(sumas13[4]);
			n++;
			datosLeidos.add(new ArrayList<String>());
    		datosLeidos.get(n).add(sumas14[0]);
    		datosLeidos.get(n).add(sumas14[1]);
    		datosLeidos.get(n).add(sumas14[2]);
    		datosLeidos.get(n).add(sumas14[3]);
    		datosLeidos.get(n).add(sumas14[4]);
			n++;
			datosLeidos.add(new ArrayList<String>());
    		datosLeidos.get(n).add(sumas15[0]);
    		datosLeidos.get(n).add(sumas15[1]);
    		datosLeidos.get(n).add(sumas15[2]);
    		datosLeidos.get(n).add(sumas15[3]);
    		datosLeidos.get(n).add(sumas15[4]);
			n++;
			datosLeidos.add(new ArrayList<String>());
    		datosLeidos.get(n).add(sumas16[0]);
    		datosLeidos.get(n).add(sumas16[1]);
    		datosLeidos.get(n).add(sumas16[2]);
    		datosLeidos.get(n).add(sumas16[3]);
    		datosLeidos.get(n).add(sumas16[4]);
			n++;
			datosLeidos.add(new ArrayList<String>());
    		datosLeidos.get(n).add(sumas17[0]);
    		datosLeidos.get(n).add(sumas17[1]);
    		datosLeidos.get(n).add(sumas17[2]);
    		datosLeidos.get(n).add(sumas17[3]);
    		datosLeidos.get(n).add(sumas17[4]);
    		
			n++;
			datosLeidos.add(new ArrayList<String>());
    		datosLeidos.get(n).add(sumasB[0]);
    		datosLeidos.get(n).add(sumasB[1]);
    		datosLeidos.get(n).add(sumasB[2]);
    		datosLeidos.get(n).add(sumasB[3]);
    		datosLeidos.get(n).add(sumasB[4]);
    		
			n++;
			datosLeidos.add(new ArrayList<String>());
    		datosLeidos.get(n).add(sumasC[0]);
    		datosLeidos.get(n).add(sumasC[1]);
    		datosLeidos.get(n).add(sumasC[2]);
    		datosLeidos.get(n).add(sumasC[3]);
    		datosLeidos.get(n).add(sumasC[4]);
    		
			n++;
			datosLeidos.add(new ArrayList<String>());
    		datosLeidos.get(n).add(sumas18[0]);
    		datosLeidos.get(n).add(sumas18[1]);
    		datosLeidos.get(n).add(sumas18[2]);
    		datosLeidos.get(n).add(sumas18[3]);
    		datosLeidos.get(n).add(sumas18[4]);
    		
			n++;
			datosLeidos.add(new ArrayList<String>());
    		datosLeidos.get(n).add(sumasD[0]);
    		datosLeidos.get(n).add(sumasD[1]);
    		datosLeidos.get(n).add(sumasD[2]);
    		datosLeidos.get(n).add(sumasD[3]);
    		datosLeidos.get(n).add(sumasD[4]);
    		
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
 
    	// en el final, se transforma el arrayList en un array[n][10]
    	String lectura[][]=new String[datosLeidos.size()][6];
    	
    	for (int i=0;i<datosLeidos.size();i++) {
    		lectura[i]=datosLeidos.get(i).toArray(new String[6]);
    	}
    	// se devuelve el array con la informacion a listar
    	return lectura;
    	
    } //  fin del metodo leeResultados
    
    
    
} // ********************************************************* FIN CLASE CONTADAO
