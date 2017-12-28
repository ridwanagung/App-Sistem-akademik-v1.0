/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Tools;

import java.sql.*; // 
import javax.swing.JOptionPane;

public class KoneksiDB {
    
    public  Connection getConnection() throws SQLException{
        Connection cnn; // deklarasi class connection nama method
        try{
            String server = "jdbc:mysql://localhost/dbsiakadv1_161530019"; //nama server da loksi daatabase
            String drever = "com.mysql.jdbc.Driver"; // nama driver koneksi 
            Class.forName(drever); // eksekusi driver koneksi DB
            cnn = DriverManager.getConnection(server, "root", "");// inisialisasi variable cnn
            return cnn;
            
        }catch(SQLException | ClassNotFoundException se){ // fungsi catcch : untuk menampiilakn error didalam try
            JOptionPane.showMessageDialog(null, "Error Koneksi Database: " +se);
            return null;
        }
    
        
    }
    
}
