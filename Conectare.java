package ProduseSQL;
import java.sql.*;
import javax.swing.*;

import javax.swing.JOptionPane;
public class Conectare {
	Connection con;
 
    public static Connection conect() {
    	try {
    		Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/arobs","root","Cantbeth1s");
    		JOptionPane.showMessageDialog(null, "Te-ai conectat la baza de date din SQL!");
    	    return con;
    	} catch(Exception ex) {
    		System.err.println("Eroare: "+ex);
    		return null;
    	}
    } 
}
