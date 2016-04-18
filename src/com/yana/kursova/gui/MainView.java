/*
 * Created by JFormDesigner on Sun Apr 03 22:49:41 EEST 2016
 */

package com.yana.kursova.gui;

import java.awt.event.*;
import com.yana.kursova.dao.ShopDAO;
import com.yana.kursova.domain.Shop;

import javax.swing.*;
import javax.swing.table.AbstractTableModel;
import java.awt.*;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class MainView extends JFrame {

    private ShopsTableModel tableModel = getShopsTableModel();

    public MainView() {
        initComponents();

    }

    private void tableShopMouseClicked(MouseEvent e) {
        if ( e.getClickCount() == 2 ) {
            int selectedRow = tableShop.getSelectedRow();
            int shopId = tableModel.getShopFromRow( selectedRow ).getId();
            Goods goods = new Goods( this, shopId );
            goods.setVisible( true );
        }
    }

    private void textFieldSearchKeyPressed(KeyEvent e) {
        tableModel.search( textFieldSearch.getText() );
    }

    private void buttonAddTBActionPerformed(ActionEvent e) {
        AddNewShop newShop = new AddNewShop( this );
        newShop.setVisible( true );
    }

    private void buttonEditTBActionPerformed(ActionEvent e) {
        int selectedRow = tableShop.getSelectedRow();
        Shop shop = tableModel.getShopFromRow( selectedRow );
        AddNewShop newShop = new AddNewShop(this, shop );
        newShop.setVisible( true );
    }

    private void buttonDeleteTBActionPerformed(ActionEvent e) {
        int confirmDialog = JOptionPane.showConfirmDialog( this,
                "Ви дійсно бажаєте видалити могазин?", "Увага!",
                JOptionPane.YES_NO_OPTION );
        if ( confirmDialog == JOptionPane.YES_OPTION ) try {
            int selectedRow = tableShop.getSelectedRow();
            Shop shop = tableModel.getShopFromRow( selectedRow );
            new ShopDAO().deleteShop( shop.getId() );
            tableModel.removeRow( selectedRow );
        } catch ( SQLException e1 ) {
            e1.printStackTrace();
        }
    }

    private void initComponents() {
        // JFormDesigner - Component initialization - DO NOT MODIFY  //GEN-BEGIN:initComponents
        // Generated using JFormDesigner Evaluation license - Nicholas G
        menuBar = new JMenuBar();
        menu1 = new JMenu();
        menuItem1 = new JMenuItem();
        scrollPane = new JScrollPane();
        tableShop = new JTable(tableModel);
        toolBar = new JToolBar();
        buttonAddTB = new JButton();
        buttonDeleteTB = new JButton();
        buttonEditTB = new JButton();
        buttonPrintTB = new JButton();
        label1 = new JLabel();
        textFieldSearch = new JTextField();

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
            toolBar.setMaximumSize(new Dimension(1920, 41));
            toolBar.setPreferredSize(new Dimension(907, 41));

            //---- buttonAddTB ----
            buttonAddTB.setToolTipText("Add a new shop");
            buttonAddTB.setIcon(new ImageIcon(getClass().getResource("/com/yana/kursova/gui/icons/add.png")));
            buttonAddTB.setAlignmentY(0.0F);
            buttonAddTB.setBorderPainted(false);
            buttonAddTB.setBackground(Color.white);
            buttonAddTB.addActionListener(e -> buttonAddTBActionPerformed(e));
            toolBar.add(buttonAddTB);

            //---- buttonDeleteTB ----
            buttonDeleteTB.setBorderPainted(false);
            buttonDeleteTB.setIcon(new ImageIcon(getClass().getResource("/com/yana/kursova/gui/icons/delete.png")));
            buttonDeleteTB.setBackground(Color.white);
            buttonDeleteTB.setAlignmentY(0.0F);
            buttonDeleteTB.setToolTipText("Delete a shop");
            buttonDeleteTB.addActionListener(e -> buttonDeleteTBActionPerformed(e));
            toolBar.add(buttonDeleteTB);

            //---- buttonEditTB ----
            buttonEditTB.setIcon(new ImageIcon(getClass().getResource("/com/yana/kursova/gui/icons/edit.png")));
            buttonEditTB.setAlignmentY(0.0F);
            buttonEditTB.setBorderPainted(false);
            buttonEditTB.setBackground(Color.white);
            buttonEditTB.setToolTipText("Edit a shop");
            buttonEditTB.addActionListener(e -> buttonEditTBActionPerformed(e));
            toolBar.add(buttonEditTB);

            //---- buttonPrintTB ----
            buttonPrintTB.setSelectedIcon(new ImageIcon(getClass().getResource("/com/yana/kursova/gui/icons/print.png")));
            buttonPrintTB.setAlignmentY(0.0F);
            buttonPrintTB.setBorderPainted(false);
            buttonPrintTB.setToolTipText("Print table");
            buttonPrintTB.setBackground(Color.white);
            buttonPrintTB.setIcon(new ImageIcon(getClass().getResource("/com/yana/kursova/gui/icons/print.png")));
            toolBar.add(buttonPrintTB);

            //---- label1 ----
            label1.setText("\u041f\u043e\u0448\u0443\u043a \u043f\u043e \u043d\u0430\u0437\u0432\u0456: ");
            label1.setPreferredSize(new Dimension(630, 30));
            label1.setMaximumSize(new Dimension(1920, 30));
            label1.setHorizontalAlignment(SwingConstants.RIGHT);
            toolBar.add(label1);

            //---- textFieldSearch ----
            textFieldSearch.setMaximumSize(new Dimension(125, 30));
            textFieldSearch.setPreferredSize(new Dimension(125, 30));
            textFieldSearch.addKeyListener(new KeyAdapter() {
                @Override
                public void keyPressed(KeyEvent e) {
                    textFieldSearchKeyPressed(e);
                }
            });
            toolBar.add(textFieldSearch);
        }

        GroupLayout contentPaneLayout = new GroupLayout(contentPane);
        contentPane.setLayout(contentPaneLayout);
        contentPaneLayout.setHorizontalGroup(
            contentPaneLayout.createParallelGroup()
                .addComponent(toolBar, GroupLayout.Alignment.TRAILING, GroupLayout.DEFAULT_SIZE, 954, Short.MAX_VALUE)
                .addComponent(scrollPane, GroupLayout.Alignment.TRAILING, GroupLayout.DEFAULT_SIZE, 954, Short.MAX_VALUE)
        );
        contentPaneLayout.setVerticalGroup(
            contentPaneLayout.createParallelGroup()
                .addGroup(GroupLayout.Alignment.TRAILING, contentPaneLayout.createSequentialGroup()
                    .addComponent(toolBar, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                    .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                    .addComponent(scrollPane, GroupLayout.DEFAULT_SIZE, 426, Short.MAX_VALUE))
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
            JOptionPane.showMessageDialog( this, "Не вдалося ініціалізувати таблицю магазинів: " + e.getMessage() );
        }
        return new ShopsTableModel( new ArrayList<>() );
    }

    // JFormDesigner - Variables declaration - DO NOT MODIFY  //GEN-BEGIN:variables
    // Generated using JFormDesigner Evaluation license - Nicholas G
    private JMenuBar menuBar;
    private JMenu menu1;
    private JMenuItem menuItem1;
    private JScrollPane scrollPane;
    public static JTable tableShop;
    private JToolBar toolBar;
    private JButton buttonAddTB;
    private JButton buttonDeleteTB;
    private JButton buttonEditTB;
    private JButton buttonPrintTB;
    private JLabel label1;
    private JTextField textFieldSearch;
    // JFormDesigner - End of variables declaration  //GEN-END:variables
}
