/*
 * Created by JFormDesigner on Sun Apr 03 22:49:41 EEST 2016
 */

package com.yana.kursova.gui;

import java.awt.*;
import javax.swing.*;

/**
 * @author Nicholas G
 */
public class MainView extends JFrame {
    public MainView() {
        initComponents();
    }

    private void initComponents() {

        menuBar1 = new JMenuBar();
        menu1 = new JMenu();
        menuItem1 = new JMenuItem();
        buttonAdd = new JButton();
        buttonEdit = new JButton();
        buttonDelete = new JButton();

        //======== this ========
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setTitle("SHOPS");
        Container contentPane = getContentPane();

        //======== menuBar1 ========
        {

            //======== menu1 ========
            {
                menu1.setText("File");

                //---- menuItem1 ----
                menuItem1.setText("New...");
                menu1.add(menuItem1);
            }
            menuBar1.add(menu1);
        }
        setJMenuBar(menuBar1);

        //---- buttonAdd ----
        buttonAdd.setText("Add");

        //---- buttonEdit ----
        buttonEdit.setText("Edit");

        //---- buttonDelete ----
        buttonDelete.setText("Delete");

        GroupLayout contentPaneLayout = new GroupLayout(contentPane);
        contentPane.setLayout(contentPaneLayout);
        contentPaneLayout.setHorizontalGroup(
            contentPaneLayout.createParallelGroup()
                .addGroup(contentPaneLayout.createSequentialGroup()
                    .addComponent(buttonAdd, GroupLayout.PREFERRED_SIZE, 100, GroupLayout.PREFERRED_SIZE)
                    .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                    .addComponent(buttonEdit, GroupLayout.PREFERRED_SIZE, 100, GroupLayout.PREFERRED_SIZE)
                    .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                    .addComponent(buttonDelete, GroupLayout.PREFERRED_SIZE, 100, GroupLayout.PREFERRED_SIZE)
                    .addGap(0, 472, Short.MAX_VALUE))
        );
        contentPaneLayout.setVerticalGroup(
            contentPaneLayout.createParallelGroup()
                .addGroup(GroupLayout.Alignment.TRAILING, contentPaneLayout.createSequentialGroup()
                    .addContainerGap(400, Short.MAX_VALUE)
                    .addGroup(contentPaneLayout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                        .addComponent(buttonAdd)
                        .addComponent(buttonEdit)
                        .addComponent(buttonDelete))
                    .addContainerGap())
        );
        pack();
        setLocationRelativeTo(getOwner());

    }


    private JMenuBar menuBar1;
    private JMenu menu1;
    private JMenuItem menuItem1;
    private JButton buttonAdd;
    private JButton buttonEdit;
    private JButton buttonDelete;

}
