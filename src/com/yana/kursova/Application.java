package com.yana.kursova;

import com.yana.kursova.dao.GoodDAO;

import java.sql.SQLException;

public class Application {

    public static void main( String[] args ) {
        GoodDAO dao = new GoodDAO();
        try {
            dao.findAll().forEach( System.out::println );
            System.out.println( "--------------------" );
            System.out.println( dao.findById( 10 ) );
            System.out.println( "--------------------" );
            System.out.println( dao.findAllByName( "л" ) );
        } catch ( SQLException e ) {
            e.printStackTrace();
        }
    }

}
