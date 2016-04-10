/*
 * Created by JFormDesigner on Wed Feb 10 15:54:40 EET 2016
 */

package com.yana.kursova.gui;

import com.yana.kursova.dao.GoodDAO;
import com.yana.kursova.domain.Good;

import java.awt.*;
import java.util.*;
import javax.swing.*;

/**
 * @author Nicholas G
 */
public class GoodsInShop extends JDialog {
    public GoodsInShop(Frame owner) {
        super(owner);
        initComponents();
    }

    public GoodsInShop(Dialog owner) {
        super(owner);
        initComponents();
    }

    private void initComponents() {
        // JFormDesigner - Component initialization - DO NOT MODIFY  //GEN-BEGIN:initComponents
        // Generated using JFormDesigner Evaluation license - Nicholas G

        //======== this ========
        Container contentPane = getContentPane();
        contentPane.setLayout(null);

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

    private GoodsTableModel getGoodsTableModel() {
        try {
            GoodDAO dao = new GoodDAO();
            final java.util.List<Good> goods = dao.findAll();
            return new GoodsTableModel( goods );
        } catch ( Exception e ) {
            e.printStackTrace();
            JOptionPane.showMessageDialog( this, "Couldn't initialize table of goods: " + e.getMessage() );
        }
        return new GoodsTableModel( new ArrayList<>() );
    }

    // JFormDesigner - Variables declaration - DO NOT MODIFY  //GEN-BEGIN:variables
    // Generated using JFormDesigner Evaluation license - Nicholas G
    // JFormDesigner - End of variables declaration  //GEN-END:variables
}
