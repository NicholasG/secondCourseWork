/*
 * Created by JFormDesigner on Sun Apr 03 22:49:41 EEST 2016
 */

package com.yana.kursova.gui;

import com.yana.kursova.dao.ShopDAO;
import com.yana.kursova.domain.Shop;
import com.yana.kursova.excel.ExcelFileFilter;
import com.yana.kursova.excel.ExcelUtils;
import com.yana.kursova.gui.popup.MyPopupMenu;
import com.yana.kursova.gui.table.model.ShopsTableModel;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.awt.print.PrinterException;
import java.io.File;
import java.sql.SQLException;
import java.text.MessageFormat;
import java.util.ArrayList;
import java.util.List;

public class MainView extends JFrame {

    private JScrollPane scrollPane;
    static JTable tableShop;
    private JToolBar toolBar;
    private JButton buttonAddTB;
    private JButton buttonDeleteTB;
    private JButton buttonEditTB;
    private JButton buttonPrintTB;
    private JButton buttonExcelExport;
    private JLabel label1;
    private JTextField textFieldSearch;

    private ShopsTableModel tableModel = getShopsTableModel();

    public MainView() {
        initComponents();
    }

    private void tableShopMouseClicked( MouseEvent e ) {
        if ( e.getClickCount() == 2 && e.getButton() == MouseEvent.BUTTON1 ) {
            int selectedRow = tableShop.getSelectedRow();
            int shopId = tableModel.getShopFromRow( selectedRow ).getId();
            Goods goods = new Goods( this, shopId );
            goods.setVisible( true );
        } else if ( e.getButton() == MouseEvent.BUTTON3 ) {
            MyPopupMenu popupMenu = new MyPopupMenu();
            popupMenu.setAddActionListener( this::buttonAddTBActionPerformed );
            popupMenu.setEditActionListener( this::buttonEditTBActionPerformed );
            popupMenu.setDeleteActionListener( this::buttonDeleteTBActionPerformed );
            popupMenu.show( tableShop, e.getX(), e.getY() );
        }
    }

    private void buttonAddTBActionPerformed( ActionEvent e ) {
        AddNewShop newShop = new AddNewShop( this );
        newShop.setVisible( true );
    }

    private void buttonEditTBActionPerformed( ActionEvent e ) {
        int selectedRow = tableShop.getSelectedRow();
        Shop shop = tableModel.getShopFromRow( selectedRow );
        AddNewShop newShop = new AddNewShop( this, shop );
        newShop.setVisible( true );
    }

    private void buttonDeleteTBActionPerformed( ActionEvent e ) {
        int confirmDialog = JOptionPane.showConfirmDialog( this,
                "Ви дійсно бажаєте видалити магазин?", "Увага!",
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

    private void buttonPrintTBActionPerformed( ActionEvent e ) {
        MessageFormat headerFormat = new MessageFormat( "Сторінка {0}" );
        MessageFormat footerFormat = new MessageFormat( "- {0} -" );
        try {
            tableShop.print( JTable.PrintMode.FIT_WIDTH, headerFormat, footerFormat );
        } catch ( PrinterException e1 ) {
            e1.printStackTrace();
        }
    }

    private void buttonExcelExportActionPerformed( ActionEvent e ) {
        JFileChooser fileChooser = new JFileChooser();
        ExcelFileFilter filter = new ExcelFileFilter();
        fileChooser.addChoosableFileFilter( filter );
        if ( fileChooser.showSaveDialog( this ) == JFileChooser.APPROVE_OPTION ) {
            File selectedFile = fileChooser.getSelectedFile();
            if ( !filter.accept( selectedFile ) ) {
                String s = selectedFile.getPath() + ".xlsx";
                selectedFile = new File( s );
                ExcelUtils.getInstance()
                        .exportShops( selectedFile, tableModel.getShops() );
            } else {
                ExcelUtils.getInstance()
                        .exportShops( selectedFile, tableModel.getShops() );
            }
        }
    }

    private void textFieldSearchKeyTyped( KeyEvent e ) {
        if ( e.getKeyChar() != KeyEvent.VK_BACK_SPACE ) {
            String name = textFieldSearch.getText() + e.getKeyChar();
            tableModel.search( name );
        } else {
            String name = textFieldSearch.getText();
            tableModel.search( name );
        }
    }

    private void initComponents() {
        scrollPane = new JScrollPane();
        tableShop = new JTable( tableModel );
        toolBar = new JToolBar();
        buttonAddTB = new JButton();
        buttonDeleteTB = new JButton();
        buttonEditTB = new JButton();
        buttonPrintTB = new JButton();
        buttonExcelExport = new JButton();
        label1 = new JLabel();
        textFieldSearch = new JTextField();

        //======== this ========
        setDefaultCloseOperation( WindowConstants.EXIT_ON_CLOSE );
        setTitle( "\u041c\u0410\u0413\u0410\u0417\u0418\u041d\u0418" );
        setBackground( Color.white );
        Container contentPane = getContentPane();

        //======== scrollPane ========
        {
            scrollPane.setBackground( Color.white );

            //---- tableShop ----
            tableShop.setSelectionMode( ListSelectionModel.SINGLE_SELECTION );
            tableShop.setRowHeight( 20 );
            tableShop.setFont( new Font( "Times New Roman", Font.PLAIN, 12 ) );
            tableShop.addMouseListener( new MouseAdapter() {
                @Override
                public void mouseClicked( MouseEvent e ) {
                    tableShopMouseClicked( e );
                }
            } );
            scrollPane.setViewportView( tableShop );
        }

        //======== toolBar ========
        {
            toolBar.setFloatable( false );
            toolBar.setBackground( Color.white );
            toolBar.setAlignmentX( 0.0F );
            toolBar.setBorderPainted( false );
            toolBar.setMaximumSize( new Dimension( 1920, 41 ) );
            toolBar.setPreferredSize( new Dimension( 907, 41 ) );

            //---- buttonAddTB ----
            buttonAddTB.setToolTipText( "\u0414\u043e\u0434\u0430\u0442\u0438" );
            buttonAddTB.setIcon( new ImageIcon( getClass().getResource( "/com/yana/kursova/gui/icons/add.png" ) ) );
            buttonAddTB.setAlignmentY( 0.0F );
            buttonAddTB.setBorderPainted( false );
            buttonAddTB.setBackground( Color.white );
            buttonAddTB.addActionListener( this::buttonAddTBActionPerformed );
            toolBar.add( buttonAddTB );

            //---- buttonDeleteTB ----
            buttonDeleteTB.setBorderPainted( false );
            buttonDeleteTB.setIcon( new ImageIcon( getClass().getResource( "/com/yana/kursova/gui/icons/delete.png" ) ) );
            buttonDeleteTB.setBackground( Color.white );
            buttonDeleteTB.setAlignmentY( 0.0F );
            buttonDeleteTB.setToolTipText( "\u0412\u0438\u0434\u0430\u043b\u0438\u0442\u0438" );
            buttonDeleteTB.addActionListener( this::buttonDeleteTBActionPerformed );
            toolBar.add( buttonDeleteTB );

            //---- buttonEditTB ----
            buttonEditTB.setIcon( new ImageIcon( getClass().getResource( "/com/yana/kursova/gui/icons/edit.png" ) ) );
            buttonEditTB.setAlignmentY( 0.0F );
            buttonEditTB.setBorderPainted( false );
            buttonEditTB.setBackground( Color.white );
            buttonEditTB.setToolTipText( "\u0420\u0435\u0434\u0430\u0433\u0443\u0432\u0430\u0442\u0438" );
            buttonEditTB.addActionListener( this::buttonEditTBActionPerformed );
            toolBar.add( buttonEditTB );

            //---- buttonPrintTB ----
            buttonPrintTB.setSelectedIcon( new ImageIcon( getClass().getResource( "/com/yana/kursova/gui/icons/print.png" ) ) );
            buttonPrintTB.setAlignmentY( 0.0F );
            buttonPrintTB.setBorderPainted( false );
            buttonPrintTB.setToolTipText( "\u0414\u0440\u0443\u043a" );
            buttonPrintTB.setBackground( Color.white );
            buttonPrintTB.setIcon( new ImageIcon( getClass().getResource( "/com/yana/kursova/gui/icons/print.png" ) ) );
            buttonPrintTB.addActionListener( this::buttonPrintTBActionPerformed );
            toolBar.add( buttonPrintTB );

            //---- buttonExcelExport ----
            buttonExcelExport.setIcon( new ImageIcon( getClass().getResource( "/com/yana/kursova/gui/icons/Excel.png" ) ) );
            buttonExcelExport.addActionListener( this::buttonExcelExportActionPerformed );
            toolBar.add( buttonExcelExport );

            //---- label1 ----
            label1.setText( "\u041f\u043e\u0448\u0443\u043a \u043f\u043e \u043d\u0430\u0437\u0432\u0456: " );
            label1.setPreferredSize( new Dimension( 630, 30 ) );
            label1.setMaximumSize( new Dimension( 1920, 30 ) );
            label1.setHorizontalAlignment( SwingConstants.RIGHT );
            toolBar.add( label1 );

            //---- textFieldSearch ----
            textFieldSearch.setMaximumSize( new Dimension( 125, 30 ) );
            textFieldSearch.setPreferredSize( new Dimension( 125, 30 ) );
            textFieldSearch.addKeyListener( new KeyAdapter() {
                @Override
                public void keyTyped( KeyEvent e ) {
                    textFieldSearchKeyTyped( e );
                }
            } );
            toolBar.add( textFieldSearch );
        }

        GroupLayout contentPaneLayout = new GroupLayout( contentPane );
        contentPane.setLayout( contentPaneLayout );
        contentPaneLayout.setHorizontalGroup(
                contentPaneLayout.createParallelGroup()
                        .addComponent( toolBar, GroupLayout.Alignment.TRAILING, GroupLayout.DEFAULT_SIZE, 954, Short.MAX_VALUE )
                        .addComponent( scrollPane, GroupLayout.Alignment.TRAILING, GroupLayout.DEFAULT_SIZE, 954, Short.MAX_VALUE )
        );
        contentPaneLayout.setVerticalGroup(
                contentPaneLayout.createParallelGroup()
                        .addGroup( GroupLayout.Alignment.TRAILING, contentPaneLayout.createSequentialGroup()
                                .addComponent( toolBar, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE )
                                .addPreferredGap( LayoutStyle.ComponentPlacement.RELATED )
                                .addComponent( scrollPane, GroupLayout.DEFAULT_SIZE, 449, Short.MAX_VALUE ) )
        );
        pack();
        setLocationRelativeTo( getOwner() );
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

}
