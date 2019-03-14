package ProduseSQL;

import java.sql.*;
import javax.swing.*;
import javax.swing.border.EmptyBorder;
import net.proteanit.sql.DbUtils;
import java.awt.EventQueue;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.Font;
import java.awt.Color;


public class Tabel extends JFrame  {
	Connection con;
	private JPanel contentPane;
	private JTable table;
	private JTextField id_input;
	private JTextField den_input;
	private JTextField cat_input;
	private JTextField dep_input;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Tabel frame = new Tabel();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
	public void refreshApp() {
		try {
    		String afisare = "select * from produse";
    		PreparedStatement pstmt = con.prepareStatement(afisare);
    	    ResultSet rs = pstmt.executeQuery();
    		table.setModel(DbUtils.resultSetToTableModel(rs));
    	} catch(Exception ex) {
    		System.err.println("Eroare: " +ex);
    	}
	}
	/**
	 * Create the frame.
	 */

	public Tabel() {
		con = Conectare.conect();
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 777, 307);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(51, 204, 153));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(237, 60, 514, 181);
		contentPane.add(scrollPane);
		
		table = new JTable();
		scrollPane.setViewportView(table);
		
		JLabel lblIdul = new JLabel("ID-ul");
		lblIdul.setFont(new Font("Times New Roman", Font.BOLD | Font.ITALIC, 16));
		lblIdul.setBounds(23, 55, 51, 23);
		contentPane.add(lblIdul);
		
		id_input = new JTextField();
		id_input.setBounds(120, 58, 86, 20);
		contentPane.add(id_input);
		id_input.setColumns(10);
		
		den_input = new JTextField();
		den_input.setBounds(120, 93, 86, 20);
		contentPane.add(den_input);
		den_input.setColumns(10);
		
		cat_input = new JTextField();
		cat_input.setBounds(120, 124, 86, 20);
		contentPane.add(cat_input);
		cat_input.setColumns(10);
		
		dep_input = new JTextField();
		dep_input.setBounds(120, 155, 86, 20);
		contentPane.add(dep_input);
		dep_input.setColumns(10);
		
		JLabel lblDenumirea = new JLabel("Denumirea");
		lblDenumirea.setFont(new Font("Times New Roman", Font.BOLD | Font.ITALIC, 15));
		lblDenumirea.setBounds(23, 91, 86, 23);
		contentPane.add(lblDenumirea);
		
		JLabel lblCategoria = new JLabel("Categoria");
		lblCategoria.setFont(new Font("Times New Roman", Font.BOLD | Font.ITALIC, 15));
		lblCategoria.setBounds(27, 122, 72, 23);
		contentPane.add(lblCategoria);
		
		JLabel lblDepozitul = new JLabel("Depozitul");
		lblDepozitul.setFont(new Font("Times New Roman", Font.BOLD | Font.ITALIC, 15));
		lblDepozitul.setBounds(27, 153, 72, 23);
		contentPane.add(lblDepozitul);
		
		JButton btnAdauga = new JButton("Adauga");
		btnAdauga.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
		    		int id = Integer.parseInt(id_input.getText());
					String den = den_input.getText();
					String cat = cat_input.getText();
					int dep = Integer.parseInt(dep_input.getText());
		    		String adaugare = "insert into produse VALUES (?,?,?,?)";
		    		PreparedStatement pstmt = con.prepareStatement(adaugare);
		    		pstmt.setInt(1, id);
		    		pstmt.setString(2, den);
		    		pstmt.setString(3, cat);
		    		pstmt.setInt(4, dep);
		    		pstmt.executeUpdate();
		    		JOptionPane.showMessageDialog(btnAdauga, "Produsul a fost adaugat!");
		    	} catch(Exception ex) {
		    		System.err.println("Eroare "+ex);
		    	}
				refreshApp();
			}
		});
		btnAdauga.setFont(new Font("Times New Roman", Font.ITALIC, 16));
		btnAdauga.setBounds(23, 203, 89, 23);
		contentPane.add(btnAdauga);
		
		JButton btnElimina = new JButton("Sterge");
		btnElimina.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try{
			    	int idP = Integer.parseInt(id_input.getText());
			        String stergere = "DELETE FROM produse WHERE idProdus = ?";
			        PreparedStatement pstmt = con.prepareStatement(stergere);
			    	pstmt.setInt(1, idP);
			    	pstmt.executeUpdate();
			    	JOptionPane.showMessageDialog(btnElimina, "Produsul a fost eliminat!");
			    } catch(Exception ex) {
			    	System.err.println("Eroare "+ex);
			    }
				refreshApp();
			}
		});
		btnElimina.setFont(new Font("Times New Roman", Font.ITALIC, 16));
		btnElimina.setBounds(133, 203, 89, 23);
		contentPane.add(btnElimina);
		
		JLabel lblpentruStergereIntrodu = new JLabel("* pentru stergere introdu doar ID-ul produsului");
		lblpentruStergereIntrodu.setFont(new Font("Times New Roman", Font.BOLD, 10));
		lblpentruStergereIntrodu.setBounds(10, 228, 221, 40);
		contentPane.add(lblpentruStergereIntrodu);
		refreshApp();
	}
}
