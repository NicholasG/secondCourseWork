package com.yana.kursova;

import com.alee.laf.WebLookAndFeel;
import com.yana.kursova.gui.MainView;

public class Application {

    public static void main( String[] args ) {
        WebLookAndFeel.install();
        MainView view = new MainView();
        view.setVisible( true );
    }

}
