/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Ukz
 */
public class AddFinisher extends javax.swing.JFrame {

    /**
     * Creates new form AddFinisher
     */
    public AddFinisher() {
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

        addButchText = new javax.swing.JLabel();
        jPanel1 = new javax.swing.JPanel();
        navButchText = new javax.swing.JLabel();
        nav1 = new javax.swing.JButton();
        nav2 = new javax.swing.JButton();
        nav3 = new javax.swing.JButton();
        nav4 = new javax.swing.JButton();
        nav5 = new javax.swing.JButton();
        nav6 = new javax.swing.JButton();
        nav7 = new javax.swing.JButton();
        nav8 = new javax.swing.JButton();
        nav9 = new javax.swing.JButton();
        nav10 = new javax.swing.JButton();
        jPanel10 = new javax.swing.JPanel();
        jLabel55 = new javax.swing.JLabel();
        jLabel56 = new javax.swing.JLabel();
        jLabel57 = new javax.swing.JLabel();
        jLabel58 = new javax.swing.JLabel();
        jLabel59 = new javax.swing.JLabel();
        submit8 = new javax.swing.JButton();
        clr8 = new javax.swing.JButton();
        butDate8 = new javax.swing.JTextField();
        earNum8 = new javax.swing.JTextField();
        totWeight16 = new javax.swing.JTextField();
        buyer8 = new javax.swing.JTextField();
        soldDate8 = new javax.swing.JTextField();
        jLabel60 = new javax.swing.JLabel();
        soldDate9 = new javax.swing.JTextField();
        jLabel61 = new javax.swing.JLabel();
        soldDate10 = new javax.swing.JTextField();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        addButchText.setBackground(new java.awt.Color(51, 153, 255));
        addButchText.setFont(new java.awt.Font("Verdana", 1, 24)); // NOI18N
        addButchText.setText("                            Add Finisher Item");

        navButchText.setFont(new java.awt.Font("Verdana", 0, 12)); // NOI18N
        navButchText.setText("  Navigation");

        nav1.setText("Home");

        nav2.setText("Add Breeding Item");

        nav3.setText("Add Farrowing Item");

        nav4.setText("Add Mortality Item");
        nav4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                nav4ActionPerformed(evt);
            }
        });

        nav5.setText("Add Inventory Item");
        nav5.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                nav5ActionPerformed(evt);
            }
        });

        nav6.setText("Add Finisher Sales Item");
        nav6.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                nav6ActionPerformed(evt);
            }
        });

        nav7.setText("Add Butchered Sales Item");

        nav8.setText("Add Salaries Expenses Item");
        nav8.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                nav8ActionPerformed(evt);
            }
        });

        nav9.setText("Add Miscellaneous Item");
        nav9.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                nav9ActionPerformed(evt);
            }
        });

        nav10.setText("Show Summary");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(62, 62, 62)
                        .addComponent(navButchText, javax.swing.GroupLayout.PREFERRED_SIZE, 85, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(nav1, javax.swing.GroupLayout.DEFAULT_SIZE, 195, Short.MAX_VALUE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(nav2, javax.swing.GroupLayout.DEFAULT_SIZE, 195, Short.MAX_VALUE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(nav3, javax.swing.GroupLayout.DEFAULT_SIZE, 195, Short.MAX_VALUE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(nav4, javax.swing.GroupLayout.DEFAULT_SIZE, 195, Short.MAX_VALUE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(nav5, javax.swing.GroupLayout.DEFAULT_SIZE, 195, Short.MAX_VALUE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(nav6, javax.swing.GroupLayout.DEFAULT_SIZE, 195, Short.MAX_VALUE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(nav7, javax.swing.GroupLayout.DEFAULT_SIZE, 195, Short.MAX_VALUE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(nav8, javax.swing.GroupLayout.DEFAULT_SIZE, 195, Short.MAX_VALUE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(nav9, javax.swing.GroupLayout.DEFAULT_SIZE, 195, Short.MAX_VALUE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(nav10, javax.swing.GroupLayout.DEFAULT_SIZE, 195, Short.MAX_VALUE)))
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addComponent(navButchText, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(nav1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(nav2)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(nav3)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(nav4)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(nav5)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(nav6)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(nav7)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(nav8)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(nav9)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(nav10)
                .addContainerGap(55, Short.MAX_VALUE))
        );

        jLabel55.setText("Date");

        jLabel56.setText("Buyer");

        jLabel57.setText("# of heads");

        jLabel58.setText("Total Weight");

        jLabel59.setText("Kilos less");

        submit8.setText("Submit");

        clr8.setText("Clear Fields");

        totWeight16.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                totWeight16ActionPerformed(evt);
            }
        });

        buyer8.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                buyer8ActionPerformed(evt);
            }
        });

        jLabel60.setText("Price per Kg");

        jLabel61.setText("Total Amount");

        javax.swing.GroupLayout jPanel10Layout = new javax.swing.GroupLayout(jPanel10);
        jPanel10.setLayout(jPanel10Layout);
        jPanel10Layout.setHorizontalGroup(
            jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel10Layout.createSequentialGroup()
                .addGap(62, 62, 62)
                .addGroup(jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel58, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(jPanel10Layout.createSequentialGroup()
                        .addGroup(jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                .addComponent(jLabel59, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(jLabel57, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(jLabel56, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(jLabel55, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(jLabel60, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                            .addComponent(jLabel61, javax.swing.GroupLayout.PREFERRED_SIZE, 158, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addComponent(soldDate10)
                    .addComponent(soldDate9)
                    .addComponent(soldDate8, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 94, Short.MAX_VALUE)
                    .addComponent(buyer8, javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(totWeight16, javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(earNum8, javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(butDate8, javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(submit8, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, 88, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(clr8)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel10Layout.setVerticalGroup(
            jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel10Layout.createSequentialGroup()
                .addGap(21, 21, 21)
                .addGroup(jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel55)
                    .addComponent(butDate8, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(earNum8, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel56))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(totWeight16, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel57))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(buyer8, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel58))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(soldDate8, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel59))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel60)
                    .addComponent(soldDate9, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel61)
                    .addComponent(soldDate10, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(91, 91, 91)
                .addGroup(jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(clr8)
                    .addComponent(submit8))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(addButchText, javax.swing.GroupLayout.PREFERRED_SIZE, 703, javax.swing.GroupLayout.PREFERRED_SIZE)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel10, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(addButchText, javax.swing.GroupLayout.PREFERRED_SIZE, 43, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel10, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void nav4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_nav4ActionPerformed
// TODO add your handling code here:
    }//GEN-LAST:event_nav4ActionPerformed

    private void nav5ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_nav5ActionPerformed
// TODO add your handling code here:
    }//GEN-LAST:event_nav5ActionPerformed

    private void nav6ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_nav6ActionPerformed
// TODO add your handling code here:
    }//GEN-LAST:event_nav6ActionPerformed

    private void nav8ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_nav8ActionPerformed
// TODO add your handling code here:
    }//GEN-LAST:event_nav8ActionPerformed

    private void nav9ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_nav9ActionPerformed
// TODO add your handling code here:
    }//GEN-LAST:event_nav9ActionPerformed

    private void totWeight16ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_totWeight16ActionPerformed
// TODO add your handling code here:
    }//GEN-LAST:event_totWeight16ActionPerformed

    private void buyer8ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_buyer8ActionPerformed
// TODO add your handling code here:
    }//GEN-LAST:event_buyer8ActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /*
         * Set the Nimbus look and feel
         */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /*
         * If Nimbus (introduced in Java SE 6) is not available, stay with the
         * default look and feel. For details see
         * http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(AddFinisher.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(AddFinisher.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(AddFinisher.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(AddFinisher.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /*
         * Create and display the form
         */
        java.awt.EventQueue.invokeLater(new Runnable() {

            public void run() {
                new AddFinisher().setVisible(true);
            }
        });
    }
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel addButchText;
    private javax.swing.JTextField butDate;
    private javax.swing.JTextField butDate1;
    private javax.swing.JTextField butDate2;
    private javax.swing.JTextField butDate3;
    private javax.swing.JTextField butDate4;
    private javax.swing.JTextField butDate5;
    private javax.swing.JTextField butDate6;
    private javax.swing.JTextField butDate7;
    private javax.swing.JTextField butDate8;
    private javax.swing.JTextField buyer;
    private javax.swing.JTextField buyer1;
    private javax.swing.JTextField buyer2;
    private javax.swing.JTextField buyer3;
    private javax.swing.JTextField buyer4;
    private javax.swing.JTextField buyer5;
    private javax.swing.JTextField buyer6;
    private javax.swing.JTextField buyer7;
    private javax.swing.JTextField buyer8;
    private javax.swing.JButton clr;
    private javax.swing.JButton clr1;
    private javax.swing.JButton clr2;
    private javax.swing.JButton clr3;
    private javax.swing.JButton clr4;
    private javax.swing.JButton clr5;
    private javax.swing.JButton clr6;
    private javax.swing.JButton clr7;
    private javax.swing.JButton clr8;
    private javax.swing.JTextField earNum;
    private javax.swing.JTextField earNum1;
    private javax.swing.JTextField earNum2;
    private javax.swing.JTextField earNum3;
    private javax.swing.JTextField earNum4;
    private javax.swing.JTextField earNum5;
    private javax.swing.JTextField earNum6;
    private javax.swing.JTextField earNum7;
    private javax.swing.JTextField earNum8;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel16;
    private javax.swing.JLabel jLabel17;
    private javax.swing.JLabel jLabel18;
    private javax.swing.JLabel jLabel19;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel20;
    private javax.swing.JLabel jLabel21;
    private javax.swing.JLabel jLabel22;
    private javax.swing.JLabel jLabel23;
    private javax.swing.JLabel jLabel24;
    private javax.swing.JLabel jLabel25;
    private javax.swing.JLabel jLabel26;
    private javax.swing.JLabel jLabel27;
    private javax.swing.JLabel jLabel28;
    private javax.swing.JLabel jLabel29;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel30;
    private javax.swing.JLabel jLabel31;
    private javax.swing.JLabel jLabel32;
    private javax.swing.JLabel jLabel33;
    private javax.swing.JLabel jLabel34;
    private javax.swing.JLabel jLabel35;
    private javax.swing.JLabel jLabel36;
    private javax.swing.JLabel jLabel37;
    private javax.swing.JLabel jLabel38;
    private javax.swing.JLabel jLabel39;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel40;
    private javax.swing.JLabel jLabel41;
    private javax.swing.JLabel jLabel42;
    private javax.swing.JLabel jLabel43;
    private javax.swing.JLabel jLabel44;
    private javax.swing.JLabel jLabel45;
    private javax.swing.JLabel jLabel46;
    private javax.swing.JLabel jLabel47;
    private javax.swing.JLabel jLabel48;
    private javax.swing.JLabel jLabel49;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel50;
    private javax.swing.JLabel jLabel51;
    private javax.swing.JLabel jLabel52;
    private javax.swing.JLabel jLabel53;
    private javax.swing.JLabel jLabel54;
    private javax.swing.JLabel jLabel55;
    private javax.swing.JLabel jLabel56;
    private javax.swing.JLabel jLabel57;
    private javax.swing.JLabel jLabel58;
    private javax.swing.JLabel jLabel59;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel60;
    private javax.swing.JLabel jLabel61;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel10;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JPanel jPanel6;
    private javax.swing.JPanel jPanel7;
    private javax.swing.JPanel jPanel8;
    private javax.swing.JPanel jPanel9;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JScrollPane jScrollPane4;
    private javax.swing.JScrollPane jScrollPane5;
    private javax.swing.JScrollPane jScrollPane6;
    private javax.swing.JScrollPane jScrollPane7;
    private javax.swing.JScrollPane jScrollPane8;
    private javax.swing.JButton nav1;
    private javax.swing.JButton nav10;
    private javax.swing.JButton nav2;
    private javax.swing.JButton nav3;
    private javax.swing.JButton nav4;
    private javax.swing.JButton nav5;
    private javax.swing.JButton nav6;
    private javax.swing.JButton nav7;
    private javax.swing.JButton nav8;
    private javax.swing.JButton nav9;
    private javax.swing.JLabel navButchText;
    private javax.swing.JTextArea notes;
    private javax.swing.JTextArea notes1;
    private javax.swing.JTextArea notes2;
    private javax.swing.JTextArea notes3;
    private javax.swing.JTextArea notes4;
    private javax.swing.JTextArea notes5;
    private javax.swing.JTextArea notes6;
    private javax.swing.JTextArea notes7;
    private javax.swing.JLabel notesText;
    private javax.swing.JLabel notesText1;
    private javax.swing.JLabel notesText2;
    private javax.swing.JLabel notesText3;
    private javax.swing.JLabel notesText4;
    private javax.swing.JLabel notesText5;
    private javax.swing.JLabel notesText6;
    private javax.swing.JLabel notesText7;
    private javax.swing.JTextField ppkg;
    private javax.swing.JTextField ppkg1;
    private javax.swing.JTextField ppkg2;
    private javax.swing.JTextField ppkg3;
    private javax.swing.JTextField ppkg4;
    private javax.swing.JTextField ppkg5;
    private javax.swing.JTextField ppkg6;
    private javax.swing.JTextField soldDate;
    private javax.swing.JTextField soldDate1;
    private javax.swing.JTextField soldDate10;
    private javax.swing.JTextField soldDate2;
    private javax.swing.JTextField soldDate3;
    private javax.swing.JTextField soldDate4;
    private javax.swing.JTextField soldDate5;
    private javax.swing.JTextField soldDate6;
    private javax.swing.JTextField soldDate7;
    private javax.swing.JTextField soldDate8;
    private javax.swing.JTextField soldDate9;
    private javax.swing.JButton submit;
    private javax.swing.JButton submit1;
    private javax.swing.JButton submit2;
    private javax.swing.JButton submit3;
    private javax.swing.JButton submit4;
    private javax.swing.JButton submit5;
    private javax.swing.JButton submit6;
    private javax.swing.JButton submit7;
    private javax.swing.JButton submit8;
    private javax.swing.JTextField totWeight1;
    private javax.swing.JTextField totWeight10;
    private javax.swing.JTextField totWeight11;
    private javax.swing.JTextField totWeight12;
    private javax.swing.JTextField totWeight13;
    private javax.swing.JTextField totWeight14;
    private javax.swing.JTextField totWeight15;
    private javax.swing.JTextField totWeight16;
    private javax.swing.JTextField totWeight2;
    private javax.swing.JTextField totWeight3;
    private javax.swing.JTextField totWeight4;
    private javax.swing.JTextField totWeight5;
    private javax.swing.JTextField totWeight6;
    private javax.swing.JTextField totWeight7;
    private javax.swing.JTextField totWeight8;
    private javax.swing.JTextField totWeight9;
    // End of variables declaration//GEN-END:variables
}
