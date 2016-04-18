/*
 * Created by JFormDesigner on Wed Feb 10 15:54:40 EET 2016
 */

package com.yana.kursova.gui;

import java.awt.event.*;
import com.yana.kursova.dao.GoodDAO;
import com.yana.kursova.domain.Good;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.sql.SQLException;
import java.util.ArrayList;

import static com.yana.kursova.gui.GoodsTableModel.getGoodsTableModel;

public class Goods extends JDialog {

    public static int shopId;
    public static GoodsTableModel tableModel;

    public Goods( Frame owner ) {
        super( owner );
        initComponents();
        tableModel = getGoodsTableModel();
        initializeTable();
    }

    public Goods( Frame owner, int shopId ) {
        super( owner );
        initComponents();
        this.shopId = shopId;
        tableModel = getGoodsTableModel( shopId );
        initializeTable();
    }

    private void initializeTable() {
        table = new JTable( tableModel );
        table.setSelectionMode( ListSelectionModel.SINGLE_SELECTION );
        scrollPane1.setViewportView( table );
    }

    private void buttonAddActionPerformed( ActionEvent e ) {
        AddNewGood newGood = new AddNewGood( this );
        newGood.setVisible( true );
    }

    private void buttonEditActionPerformed( ActionEvent e ) {
        try {
            openEditForm();
        } catch ( SQLException e1 ) {
            e1.printStackTrace();
        }
    }

    private void openEditForm() throws SQLException {
        int selectedRow = table.getSelectedRow();
        int id = tableModel.getGoodFromRow( selectedRow ).getId();
        Good good = new GoodDAO().findById( id );
        AddNewGood newGood = new AddNewGood( this, good );
        newGood.setVisible( true );
    }

    private void buttonDeleteActionPerformed( ActionEvent e ) {
        int confirmDialog = JOptionPane.showConfirmDialog( this,
                "Ви дійсно бажаєте видалити товар?", "Увага!",
                JOptionPane.YES_NO_OPTION );
        if ( confirmDialog == JOptionPane.YES_OPTION ) try {
            int selectedRow = table.getSelectedRow();
            int goodId = tableModel.getGoodFromRow( selectedRow ).getId();
            new GoodDAO().deleteGoodFromShop( shopId, goodId );
            tableModel.removeRow( selectedRow );
        } catch ( SQLException e1 ) {
            e1.printStackTrace();
        }
    }

    private void textFieldSearchKeyPressed(KeyEvent e) {
        tableModel.search( textFieldSearch.getText() );
    }

    private void initComponents() {
        // JFormDesigner - Component initialization - DO NOT MODIFY  //GEN-BEGIN:initComponents
        // Generated using JFormDesigner Evaluation license - Nicholas G
        menuBar1 = new JMenuBar();
        menu1 = new JMenu();
        menuItem2 = new JMenuItem();
        scrollPane1 = new JScrollPane();
        table = new JTable();
        toolBar1 = new JToolBar();
        buttonAdd = new JButton();
        buttonDelete = new JButton();
        buttonEdit = new JButton();
        buttonPrint = new JButton();
        label1 = new JLabel();
        textFieldSearch = new JTextField();

        //======== this ========
        setBackground(Color.white);
        setTitle("GOODS");
        setResizable(false);
        setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
        Container contentPane = getContentPane();
        contentPane.setLayout(null);

        //======== menuBar1 ========
        {

            //======== menu1 ========
            {
                menu1.setText("text");

                //---- menuItem2 ----
                menuItem2.setText("text");
                menu1.add(menuItem2);
            }
            menuBar1.add(menu1);
        }
        setJMenuBar(menuBar1);

        //======== scrollPane1 ========
        {

            //---- table ----
            table.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
            scrollPane1.setViewportView(table);
        }
        contentPane.add(scrollPane1);
        scrollPane1.setBounds(0, 40, 950, 405);

        //======== toolBar1 ========
        {
            toolBar1.setFloatable(false);
            toolBar1.setBackground(Color.white);
            toolBar1.setBorderPainted(false);

            //---- buttonAdd ----
            buttonAdd.setIcon(new ImageIcon(getClass().getResource("/com/yana/kursova/gui/icons/add.png")));
            buttonAdd.setToolTipText("Add a new good");
            buttonAdd.setBorderPainted(false);
            buttonAdd.setBackground(Color.white);
            buttonAdd.addActionListener(e -> buttonAddActionPerformed(e));
            toolBar1.add(buttonAdd);

            //---- buttonDelete ----
            buttonDelete.setIcon(new ImageIcon(getClass().getResource("/com/yana/kursova/gui/icons/delete.png")));
            buttonDelete.setToolTipText("Remove");
            buttonDelete.setBorderPainted(false);
            buttonDelete.setBackground(Color.white);
            buttonDelete.addActionListener(e -> buttonDeleteActionPerformed(e));
            toolBar1.add(buttonDelete);

            //---- buttonEdit ----
            buttonEdit.setIcon(new ImageIcon(getClass().getResource("/com/yana/kursova/gui/icons/edit.png")));
            buttonEdit.setToolTipText("Edit");
            buttonEdit.setBorderPainted(false);
            buttonEdit.setBackground(Color.white);
            buttonEdit.addActionListener(e -> buttonEditActionPerformed(e));
            toolBar1.add(buttonEdit);

            //---- buttonPrint ----
            buttonPrint.setIcon(new ImageIcon(getClass().getResource("/com/yana/kursova/gui/icons/print.png")));
            buttonPrint.setToolTipText("Print table");
            buttonPrint.setBorderPainted(false);
            buttonPrint.setBackground(Color.white);
            toolBar1.add(buttonPrint);

            //---- label1 ----
            label1.setText("\u041f\u043e\u0448\u0443\u043a \u043f\u043e \u043d\u0430\u0437\u0432\u0456: ");
            label1.setHorizontalAlignment(SwingConstants.RIGHT);
            label1.setPreferredSize(new Dimension(630, 30));
            label1.setMaximumSize(new Dimension(630, 30));
            toolBar1.add(label1);

            //---- textFieldSearch ----
            textFieldSearch.setMaximumSize(new Dimension(125, 30));
            textFieldSearch.setPreferredSize(new Dimension(125, 30));
            textFieldSearch.addKeyListener(new KeyAdapter() {
                @Override
                public void keyPressed(KeyEvent e) {
                    textFieldSearchKeyPressed(e);
                }
            });
            toolBar1.add(textFieldSearch);
        }
        contentPane.add(toolBar1);
        toolBar1.setBounds(0, 0, 950, toolBar1.getPreferredSize().height);

        { // compute preferred size
            Dimension preferredSize = new Dimension();
            for(int i = 0; i < contentPane.getComponentCount(); i++) {
                Rectangle bounds = contentPane.getComponent(i).getBounds();
                preferredSize.width = Math.max(bounds.x + bounds.width, preferredSize.width);
                preferredSize.height = Math.max(bounds.y + bounds.height, preferredSize.height);
            }
            Insets insets = contentPane.getInsets();
            preferredSize.width += insets.right;
            preferredSize.height += insets.bottom;
            contentPane.setMinimumSize(preferredSize);
            contentPane.setPreferredSize(preferredSize);
        }
        pack();
        setLocationRelativeTo(getOwner());
        // JFormDesigner - End of component initialization  //GEN-END:initComponents
    }

    // JFormDesigner - Variables declaration - DO NOT MODIFY  //GEN-BEGIN:variables
    // Generated using JFormDesigner Evaluation license - Nicholas G
    private JMenuBar menuBar1;
    private JMenu menu1;
    private JMenuItem menuItem2;
    private JScrollPane scrollPane1;
    public static JTable table;
    private JToolBar toolBar1;
    private JButton buttonAdd;
    private JButton buttonDelete;
    private JButton buttonEdit;
    private JButton buttonPrint;
    private JLabel label1;
    private JTextField textFieldSearch;
    // JFormDesigner - End of variables declaration  //GEN-END:variables
}
