/*
 * Created by JFormDesigner on Sun Apr 03 22:49:41 EEST 2016
 */

package com.yana.kursova.gui;

import java.awt.event.*;
import com.yana.kursova.dao.ShopDAO;
import com.yana.kursova.domain.Shop;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class MainView extends JFrame {

    public MainView() {
        initComponents();

    }

    private void tableShopMouseClicked(MouseEvent e) {
        if ( e.getClickCount() == 2 ) {
            Goods goods = new Goods( this, tableShop.getSelectedRow() );
            goods.setVisible( true );
        }
    }

    private void initComponents() {
        // JFormDesigner - Component initialization - DO NOT MODIFY  //GEN-BEGIN:initComponents
        // Generated using JFormDesigner Evaluation license - Nicholas G
        menuBar = new JMenuBar();
        menu1 = new JMenu();
        menuItem1 = new JMenuItem();
        scrollPane = new JScrollPane();
        tableShop = new JTable(getShopsTableModel());
        toolBar = new JToolBar();
        buttonAddTB = new JButton();
        buttonDeleteTB = new JButton();
        buttonEditTB = new JButton();
        buttonPrintTB = new JButton();

        //======== this ========
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setTitle("SHOPS");
        setBackground(Color.white);
        Container contentPane = getContentPane();

        //======== menuBar ========
        {

            //======== menu1 ========
            {
                menu1.setText("File");

                //---- menuItem1 ----
                menuItem1.setText("New...");
                menu1.add(menuItem1);
            }
            menuBar.add(menu1);
        }
        setJMenuBar(menuBar);

        //======== scrollPane ========
        {
            scrollPane.setBackground(Color.white);

            //---- tableShop ----
            tableShop.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
            tableShop.setRowHeight(20);
            tableShop.addMouseListener(new MouseAdapter() {
                @Override
                public void mouseClicked(MouseEvent e) {
                    tableShopMouseClicked(e);
                }
            });
            scrollPane.setViewportView(tableShop);
        }

        //======== toolBar ========
        {
            toolBar.setFloatable(false);
            toolBar.setBackground(Color.white);
            toolBar.setAlignmentX(0.0F);
            toolBar.setBorderPainted(false);

            //---- buttonAddTB ----
            buttonAddTB.setToolTipText("Add a new shop");
            buttonAddTB.setIcon(new ImageIcon(getClass().getResource("/com/yana/kursova/gui/icons/add.png")));
            buttonAddTB.setAlignmentY(0.0F);
            buttonAddTB.setBorderPainted(false);
            buttonAddTB.setBackground(Color.white);
            toolBar.add(buttonAddTB);

            //---- buttonDeleteTB ----
            buttonDeleteTB.setBorderPainted(false);
            buttonDeleteTB.setIcon(new ImageIcon(getClass().getResource("/com/yana/kursova/gui/icons/delete.png")));
            buttonDeleteTB.setBackground(Color.white);
            buttonDeleteTB.setAlignmentY(0.0F);
            buttonDeleteTB.setToolTipText("Delete a shop");
            toolBar.add(buttonDeleteTB);

            //---- buttonEditTB ----
            buttonEditTB.setIcon(new ImageIcon(getClass().getResource("/com/yana/kursova/gui/icons/edit.png")));
            buttonEditTB.setAlignmentY(0.0F);
            buttonEditTB.setBorderPainted(false);
            buttonEditTB.setBackground(Color.white);
            buttonEditTB.setToolTipText("Edit a shop");
            toolBar.add(buttonEditTB);

            //---- buttonPrintTB ----
            buttonPrintTB.setSelectedIcon(new ImageIcon(getClass().getResource("/com/yana/kursova/gui/icons/print.png")));
            buttonPrintTB.setAlignmentY(0.0F);
            buttonPrintTB.setBorderPainted(false);
            buttonPrintTB.setToolTipText("Print table");
            buttonPrintTB.setBackground(Color.white);
            buttonPrintTB.setIcon(new ImageIcon(getClass().getResource("/com/yana/kursova/gui/icons/print.png")));
            toolBar.add(buttonPrintTB);
        }

        GroupLayout contentPaneLayout = new GroupLayout(contentPane);
        contentPane.setLayout(contentPaneLayout);
        contentPaneLayout.setHorizontalGroup(
            contentPaneLayout.createParallelGroup()
                .addComponent(toolBar, GroupLayout.Alignment.TRAILING, GroupLayout.DEFAULT_SIZE, 954, Short.MAX_VALUE)
                .addComponent(scrollPane, GroupLayout.DEFAULT_SIZE, 954, Short.MAX_VALUE)
        );
        contentPaneLayout.setVerticalGroup(
            contentPaneLayout.createParallelGroup()
                .addGroup(GroupLayout.Alignment.TRAILING, contentPaneLayout.createSequentialGroup()
                    .addComponent(toolBar, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                    .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                    .addComponent(scrollPane, GroupLayout.DEFAULT_SIZE, 436, Short.MAX_VALUE))
        );
        pack();
        setLocationRelativeTo(getOwner());
        // JFormDesigner - End of component initialization  //GEN-END:initComponents
    }

    private ShopsTableModel getShopsTableModel() {
        try {
            ShopDAO dao = new ShopDAO();
            final List<Shop> shops = dao.findAll();
            return new ShopsTableModel( shops );
        } catch ( Exception e ) {
            e.printStackTrace();
            JOptionPane.showMessageDialog( this, "Couldn't initialize table of shops: " + e.getMessage() );
        }
        return new ShopsTableModel( new ArrayList<>() );
    }

    // JFormDesigner - Variables declaration - DO NOT MODIFY  //GEN-BEGIN:variables
    // Generated using JFormDesigner Evaluation license - Nicholas G
    private JMenuBar menuBar;
    private JMenu menu1;
    private JMenuItem menuItem1;
    private JScrollPane scrollPane;
    private JTable tableShop;
    private JToolBar toolBar;
    private JButton buttonAddTB;
    private JButton buttonDeleteTB;
    private JButton buttonEditTB;
    private JButton buttonPrintTB;
    // JFormDesigner - End of variables declaration  //GEN-END:variables
}
