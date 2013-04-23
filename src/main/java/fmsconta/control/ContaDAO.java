package fmsconta.control;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.swing.JOptionPane;



public class ContaDAO {
	
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
        	conexionDB = DriverManager.getConnection("jdbc:mysql://localhost:3306/db393934124","root", "150653");
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
    
    
}
