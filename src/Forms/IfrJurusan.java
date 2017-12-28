/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Forms;

import Tools.KoneksiDB;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author acer
 */
public class IfrJurusan extends javax.swing.JInternalFrame {

    // Untuk koneksi DB
    Connection _Cnn;
    KoneksiDB getCnn = new KoneksiDB();
    
    // deklarasi variabel
    String vkd_jur, vjurusan;
    String sqlselect, sqlinsert, sqldelete;
    DefaultTableModel tbljurusan;
    /**
     * Creates new form IfrUser
     */
    public IfrJurusan() {
        initComponents();
        
        clearInput(); disableInput();
        setTabelJurusan(); showDataJurusan();
    }
    
    private void clearInput(){
        txtKdJur.setText("");
        txtJurusan.setText("");
        btnTambah.setText("Tambah");
        btnSimpan.setText("Simpan");
        btnTambah.setIcon(new javax.swing.ImageIcon(getClass().
                getResource("/Icons/trans-add.png")));
    }
    
    private void disableInput(){
        txtKdJur.setEnabled(false);
        txtJurusan.setEnabled(false);
        btnSimpan.setEnabled(false);
        btnHapus.setEnabled(false);
    }
    
    private void enableInput(){
        txtKdJur.setEnabled(true);
        txtJurusan.setEnabled(true);
        btnSimpan.setEnabled(true);
       
    }
    
    private void setTabelJurusan(){ //untuk mengatur tmpilan tabel
        String[] kolom1 = {"KD. Jur", "Jurusan"};
        tbljurusan = new DefaultTableModel (null, kolom1){
            Class[] types =  new Class[]{
                java.lang.String.class,
                java.lang.String.class
            };
            public Class getColumnClass(int columnIndex){
                return types [columnIndex];
            }
            
            //agar tabel tidak bisa di edit
            public boolean isCellEditTable(int row, int col ){
                int cola = tbljurusan.getColumnCount();
                return (col < cola)? false : true;
            }
        };
        tbDataJurusan.setModel(tbljurusan);
        tbDataJurusan.getColumnModel().getColumn(0).setPreferredWidth(75);
        tbDataJurusan.getColumnModel().getColumn(1).setPreferredWidth(250);
    }
    
    private void clearTabelJurusan(){ //menghubungkan isi tabel
        int row = tbljurusan.getRowCount(); //vairbel row di beri nilai jumlah 
        for (int i=0; i<row; i++ ){
            tbljurusan.removeRow(0); //menghapus record atau baris
        }
    }
    
    private void showDataJurusan(){ //menampilkan datajurusan ke dalam  tabel jurusan
        try {
            _Cnn = null;
            _Cnn = getCnn.getConnection(); //mmembuka koneksiDB
            sqlselect = "select * from tbjurusan"; // query sql Select
            Statement stat = _Cnn.createStatement(); // membuat statemennt untuk mejalankan query
            ResultSet res = stat.executeQuery(sqlselect); //menjalankan query sql select yang hasilnuya di tampung pada variabel res
            clearTabelJurusan();
            while(res.next()){ //perulangan while untuk mmenampilkan data hasil query select  
                vkd_jur = res.getString("kd_jur"); // memberiakn nilai pada variabel vkd_jur diman nilainya adalah kolom Kd_jur pada tabel jurussn
                vjurusan = res.getString("jurusan");
                
                Object[] data = {vkd_jur, vjurusan}; //membuat object array untuk menampung data record 
                tbljurusan.addRow(data);//menyisipkan baris sesuai array data  
                
            }
            lblRecord.setText("Record :" + tbDataJurusan.getRowCount()); //menampilkan jumlah baris
            
        }catch(SQLException ex){
            JOptionPane.showMessageDialog(this, "Error method showDataJurusan : "+ex); 
        }
    }
    
    private void aksiSimpan(){
        if(txtKdJur.getText().equals("") || txtJurusan.getText().equals("")){
            JOptionPane.showMessageDialog(this, "Mohon lengkapi data!",
                    "Informasi", JOptionPane.INFORMATION_MESSAGE);
        }else{
            vkd_jur = txtKdJur.getText();
            vjurusan = txtJurusan.getText();
            try{
                _Cnn = null;
                _Cnn = getCnn.getConnection();
                if(btnSimpan.getText().equals("Simpan")){
                    sqlinsert = "insert into tbjurusan set kd_jur='"+vkd_jur+"', "
                             + " jurusan='"+vjurusan+"' ";
                }else if(btnSimpan.getText().equals("Ubah")){
                    sqlinsert = "update tbjurusan set "
                    + "jurusan='"+vjurusan+"' where kd_jur='"+vkd_jur+"' ";
                }
                Statement stat = _Cnn.createStatement();
                stat.executeUpdate(sqlinsert);
                JOptionPane.showMessageDialog(this, "Data berhasil disimpan!",
                        "Informasi", JOptionPane.INFORMATION_MESSAGE);
                showDataJurusan(); clearInput(); disableInput();
            }catch(SQLException ex){
               JOptionPane.showMessageDialog(this, "Error method aksiSimpan() : "+ ex);
            }
            
            
        }
    }
    
    private void aksiHapus(){
        int jawab = JOptionPane.showConfirmDialog(this, "Apakah anda akan menghapus data ini? Kode "+vkd_jur,
                "Konfirmasi", JOptionPane.YES_NO_OPTION);
        if(jawab == JOptionPane.YES_OPTION){
            try {
                _Cnn = null;
                _Cnn = getCnn.getConnection();
                sqldelete = "delete from tbjurusan where kd_jur='"+vkd_jur+"' ";
                Statement stat = _Cnn.createStatement();
                stat.executeUpdate(sqldelete);
                JOptionPane.showMessageDialog(this, "Data berhasil dihapus!",
                        "Informasi", JOptionPane.INFORMATION_MESSAGE);
                showDataJurusan(); clearInput(); disableInput();
                
            } catch (SQLException ex) {
                JOptionPane.showMessageDialog(this, "Error method aksiHapus() : "+ex);
            }
        }
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        txtKdJur = new javax.swing.JTextField();
        txtJurusan = new javax.swing.JTextField();
        jPanel2 = new javax.swing.JPanel();
        btnTambah = new javax.swing.JButton();
        btnSimpan = new javax.swing.JButton();
        btnHapus = new javax.swing.JButton();
        lblRecord = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jScrollPane2 = new javax.swing.JScrollPane();
        tbDataJurusan = new javax.swing.JTable();

        setBorder(null);
        setClosable(true);
        setTitle(".: Form Jurusan");
        setFrameIcon(new javax.swing.ImageIcon(getClass().getResource("/Icons/Admin-Schoolar-Icon.png"))); // NOI18N

        jPanel1.setBorder(javax.swing.BorderFactory.createTitledBorder(new javax.swing.border.LineBorder(new java.awt.Color(0, 0, 0), 1, true), "Input Data"));
        jPanel1.setOpaque(false);

        txtKdJur.setBorder(javax.swing.BorderFactory.createTitledBorder(new javax.swing.border.LineBorder(new java.awt.Color(0, 0, 0), 1, true), "Kode Jurusan :"));
        txtKdJur.setOpaque(false);

        txtJurusan.setBorder(javax.swing.BorderFactory.createTitledBorder(new javax.swing.border.LineBorder(new java.awt.Color(0, 0, 0), 1, true), "Jurusan :"));
        txtJurusan.setOpaque(false);
        txtJurusan.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtJurusanActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(txtKdJur, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(txtJurusan, javax.swing.GroupLayout.DEFAULT_SIZE, 312, Short.MAX_VALUE)
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtKdJur, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtJurusan, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jPanel2.setBorder(javax.swing.BorderFactory.createTitledBorder(new javax.swing.border.LineBorder(new java.awt.Color(0, 0, 0), 1, true), "Navigasi"));
        jPanel2.setOpaque(false);

        btnTambah.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Icons/trans-add.png"))); // NOI18N
        btnTambah.setText("Tambah");
        btnTambah.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnTambahActionPerformed(evt);
            }
        });

        btnSimpan.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Icons/save-black.png"))); // NOI18N
        btnSimpan.setText("Simpan");
        btnSimpan.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSimpanActionPerformed(evt);
            }
        });

        btnHapus.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Icons/trans-hapus.png"))); // NOI18N
        btnHapus.setText("Hapus");
        btnHapus.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnHapusActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(btnTambah, javax.swing.GroupLayout.PREFERRED_SIZE, 96, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(btnSimpan, javax.swing.GroupLayout.DEFAULT_SIZE, 102, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(btnHapus, javax.swing.GroupLayout.PREFERRED_SIZE, 92, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(138, 138, 138))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                .addGap(5, 5, 5)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnTambah, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnSimpan, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(btnHapus, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(5, 5, 5))
        );

        lblRecord.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        lblRecord.setText("Record : 0");

        jLabel2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Icons/logo.png"))); // NOI18N
        jLabel2.setText("jLabel2");

        jLabel3.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel3.setText("Form Jurusan");

        jLabel5.setText("Form Ini di gunakan untuk mengolah data jurusan");

        jScrollPane2.setBorder(javax.swing.BorderFactory.createTitledBorder("Tabel Data Jurusan : Klik 2x untuk mengubah / menghapus data"));

        tbDataJurusan.setBorder(javax.swing.BorderFactory.createTitledBorder(new javax.swing.border.LineBorder(new java.awt.Color(0, 0, 0), 1, true)));
        tbDataJurusan.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null},
                {null, null}
            },
            new String [] {
                "Kode Jurusan", "Jurusan"
            }
        ));
        tbDataJurusan.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tbDataJurusanMouseClicked(evt);
            }
        });
        jScrollPane2.setViewportView(tbDataJurusan);
        if (tbDataJurusan.getColumnModel().getColumnCount() > 0) {
            tbDataJurusan.getColumnModel().getColumn(0).setHeaderValue("Kode Jurusan");
            tbDataJurusan.getColumnModel().getColumn(1).setHeaderValue("Jurusan");
        }

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(lblRecord, javax.swing.GroupLayout.PREFERRED_SIZE, 70, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(9, 9, 9))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 49, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel3)
                            .addGroup(layout.createSequentialGroup()
                                .addGap(40, 40, 40)
                                .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 275, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 255, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 49, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel3)
                        .addGap(11, 11, 11)
                        .addComponent(jLabel5)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel4)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(1, 1, 1)
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 252, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(13, 13, 13)
                .addComponent(lblRecord)
                .addGap(5, 5, 5))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnTambahActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnTambahActionPerformed
        if(btnTambah.getText().equals ("Tambah")){
            clearInput();
            enableInput();
            btnTambah.setText("Batal");
            btnTambah.setIcon(new javax.swing.ImageIcon(getClass()
            .getResource("/Icons/delete.png")));
            
        }else if(btnTambah.getText().equals("Batal")){
            clearInput();
            disableInput();
            btnTambah.setIcon(new javax.swing.ImageIcon(getClass().
                    getResource("/Icons/trans-add.png")));
        }
        
    }//GEN-LAST:event_btnTambahActionPerformed

    private void btnHapusActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnHapusActionPerformed
        if (txtKdJur.getText().equals("")){
            JOptionPane.showMessageDialog(this, "Informasi", 
                    "Anda belum memilih data yang akan dihapus", JOptionPane.INFORMATION_MESSAGE);
        }else{
            aksiHapus();
        }
    }//GEN-LAST:event_btnHapusActionPerformed

    private void btnSimpanActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSimpanActionPerformed
      aksiSimpan();
    }//GEN-LAST:event_btnSimpanActionPerformed

    private void txtJurusanActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtJurusanActionPerformed
        aksiSimpan();
    }//GEN-LAST:event_txtJurusanActionPerformed

    private void tbDataJurusanMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tbDataJurusanMouseClicked
        if(evt.getClickCount() == 2){
            int row = tbDataJurusan.getSelectedRow();
            vkd_jur = tbDataJurusan.getValueAt(row, 0).toString();
            vjurusan = tbDataJurusan.getValueAt(row, 1).toString();
            
            txtKdJur.setText(vkd_jur); txtJurusan.setText(vjurusan);
            enableInput();
            txtKdJur.setEnabled(false);
            btnHapus.setEnabled(true);
            btnSimpan.setText("Ubah");
            
        }
    }//GEN-LAST:event_tbDataJurusanMouseClicked


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnHapus;
    private javax.swing.JButton btnSimpan;
    private javax.swing.JButton btnTambah;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JLabel lblRecord;
    private javax.swing.JTable tbDataJurusan;
    private javax.swing.JTextField txtJurusan;
    private javax.swing.JTextField txtKdJur;
    // End of variables declaration//GEN-END:variables
}
/**
 * 1. pahami UX dari form jurusan 
 * 2. deklarasikan variabel nama komponen pada form jurusan 
 * 3. deklaraikan object untuk koneksiDB
 * 4. deklarasikan variabel2 yang dibutuhkan
 * 5. buat method2 yan dibutuhkan sesuai dengan UX\
 * 6. Panggil method pada masing2 event komponen
 */