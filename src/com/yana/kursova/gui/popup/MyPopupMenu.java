package com.yana.kursova.gui.popup;

import javax.swing.*;
import java.awt.event.ActionListener;

/**
 * Created by NicholasG on 29.04.2016.
 */
public class MyPopupMenu extends JPopupMenu {

    private JMenuItem add = new JMenuItem( "Додати" );
    private JMenuItem edit = new JMenuItem( "Редагувати" );
    private JMenuItem delete = new JMenuItem( "Видалити" );

    public MyPopupMenu() {
        add( add );
        add( edit );
        add( delete );
    }

    public void setAddActionListener( ActionListener listener ) {
        add.addActionListener( listener );
    }

    public void setEditActionListener( ActionListener listener ) {
        edit.addActionListener( listener );
    }

    public void setDeleteActionListener( ActionListener listener ) {
        delete.addActionListener( listener );
    }

}
