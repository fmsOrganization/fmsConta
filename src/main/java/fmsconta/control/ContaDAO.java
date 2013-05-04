package fmsconta.control;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.swing.JOptionPane;



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
    	String cabecera2[]=new String[10];
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
    	
    	
    	cabecera2[0]="";
    	cabecera2[1]="Asiento";
    	cabecera2[2]="Apunte";
    	cabecera2[3]="Fecha";
    	cabecera2[4]="Documento";
    	cabecera2[5]="Concepto";
    	cabecera2[6]="Contrapartida";
    	cabecera2[7]="Debe";
    	cabecera2[8]="Haber";
    	cabecera2[9]="Saldo";
    	
    	
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
    		cabecera2[0]=namesCta[0][1];
    		datosLeidos.get(n).add(cabecera2[0]);
    		datosLeidos.get(n).add(cabecera2[1]);
    		datosLeidos.get(n).add(cabecera2[2]);
    		datosLeidos.get(n).add(cabecera2[3]);
    		datosLeidos.get(n).add(cabecera2[4]);
    		datosLeidos.get(n).add(cabecera2[5]);
    		datosLeidos.get(n).add(cabecera2[6]);
    		datosLeidos.get(n).add(cabecera2[7]);
    		datosLeidos.get(n).add(cabecera2[8]);
    		datosLeidos.get(n).add(cabecera2[9]);
    		
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
			    			cabecera2[0]=namesCta[c][1];
			    			break;
			    		}
		    			c++;
		    		}

		    		
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
		    		datosLeidos.get(n).add(cabecera2[9]);
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
    /*
    	int p1=Math.round((float)Float.parseFloat(cantidad)*100);
    	float p2=(float)p1/100;
    	System.out.println("Numeros: "+p1+" * "+p2);
    	*/
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
    		
    		// componemos el numero uniendo la parte entera y la decimal
    		nuevoNumero=cadena1+cadena2;
    		
    	}  else {
    		
    		// si no tiene separador decimal es tan facil como añadir
    		// la coma decimal y dos ceros
    		
    		nuevoNumero=cantidad+",00";
    		
    	}
    		
    	
    	return nuevoNumero;
    }
    
} // ********************************************************* FIN CLASE CONTADAO
