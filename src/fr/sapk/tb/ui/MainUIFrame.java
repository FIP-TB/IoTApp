/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fr.sapk.tb.ui;

import fr.sapk.tb.CustomControlPoint;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.cybergarage.upnp.Device;
import org.cybergarage.upnp.DeviceList;

/**
 *
 * @author sapk
 */
public class MainUIFrame extends javax.swing.JFrame {

    static CustomControlPoint ctrlPoint;

    /**
     * Creates new form MainUIFrame
     */
    public MainUIFrame() {
        initComponents();
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        ButtonLampe = new javax.swing.JButton();
        ButtonLampe1 = new javax.swing.JButton();
        ButtonLampe2 = new javax.swing.JButton();
        ButtonLampe3 = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        jList1 = new javax.swing.JList<>();
        jButton1 = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        ButtonLampe.setText("Scan");
        ButtonLampe.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ButtonScanActionPerformed(evt);
            }
        });

        ButtonLampe1.setText("Lampe On");
        ButtonLampe1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ButtonLampeOnActionPerformed(evt);
            }
        });

        ButtonLampe2.setText("Beep");
        ButtonLampe2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ButtonBeepActionPerformed(evt);
            }
        });

        ButtonLampe3.setText("Lampe Off");
        ButtonLampe3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ButtonLampe3ButtonLampeOffActionPerformed(evt);
            }
        });

        jList1.setModel(new javax.swing.AbstractListModel<String>() {
            String[] strings = { };
            public int getSize() { return strings.length; }
            public String getElementAt(int i) { return strings[i]; }
        });
        jScrollPane1.setViewportView(jList1);

        jButton1.setText("Refresh List");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                RefreshButtonActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane1)
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(ButtonLampe1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(ButtonLampe, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(ButtonLampe3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(ButtonLampe2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jButton1, javax.swing.GroupLayout.DEFAULT_SIZE, 100, Short.MAX_VALUE))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(16, 16, 16)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane1)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(ButtonLampe)
                        .addGap(18, 18, 18)
                        .addComponent(jButton1)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 63, Short.MAX_VALUE)
                        .addComponent(ButtonLampe1)
                        .addGap(18, 18, 18)
                        .addComponent(ButtonLampe3)
                        .addGap(18, 18, 18)
                        .addComponent(ButtonLampe2)))
                .addContainerGap())
        );

        ButtonLampe.getAccessibleContext().setAccessibleName("Lampe");

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void ButtonScanActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ButtonScanActionPerformed
        ctrlPoint.search();

    }//GEN-LAST:event_ButtonScanActionPerformed

    private void ButtonLampeOnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ButtonLampeOnActionPerformed
        ctrlPoint.AllumeLampe();
    }//GEN-LAST:event_ButtonLampeOnActionPerformed

    private void ButtonBeepActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ButtonBeepActionPerformed
        ctrlPoint.Beep();
    }//GEN-LAST:event_ButtonBeepActionPerformed

    private void ButtonLampe3ButtonLampeOffActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ButtonLampe3ButtonLampeOffActionPerformed
        ctrlPoint.EteindreLampe();
    }//GEN-LAST:event_ButtonLampe3ButtonLampeOffActionPerformed

    private void RefreshButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_RefreshButtonActionPerformed

        DeviceList rootDevList = ctrlPoint.getDeviceList();
        int nRootDevs = rootDevList.size();
        String[] l = new String[nRootDevs];

        for (int n = 0; n < nRootDevs; n++) {
            Device dev = rootDevList.getDevice(n);
            String devName = dev.getFriendlyName();
            l[n] = devName;
        }
        jList1.setModel(new javax.swing.AbstractListModel() {
            String[] strings = l;
            //{"Test 1", "Test 2", "Test 3", "Test 4", "Test 5"};

            public int getSize() {
                return strings.length;
            }

            public String getElementAt(int i) {
                return strings[i];
            }
        });
    }//GEN-LAST:event_RefreshButtonActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(MainUIFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(MainUIFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(MainUIFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(MainUIFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new MainUIFrame().setVisible(true);
            }
        });
        ctrlPoint = new CustomControlPoint();
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton ButtonLampe;
    private javax.swing.JButton ButtonLampe1;
    private javax.swing.JButton ButtonLampe2;
    private javax.swing.JButton ButtonLampe3;
    private javax.swing.JButton jButton1;
    private javax.swing.JList<String> jList1;
    private javax.swing.JScrollPane jScrollPane1;
    // End of variables declaration//GEN-END:variables
}
