package com.yana.kursova.dao;

import com.yana.kursova.domain.Good;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import static com.yana.kursova.dao.queries.GoodQueries.*;

public class GoodDAO {

    public int insertGood( Good good ) throws SQLException {
        try ( Connection connection = DataAccessUtil.createConnection() ) {
            PreparedStatement statement = connection.prepareStatement(
                    getInsertQuery(), Statement.RETURN_GENERATED_KEYS );
            statement.setString( 1, good.getName() );
            statement.setString( 2, good.getType() );
            statement.setString( 3, good.getManufacturer() );
            statement.setString( 4, good.getArticle() );
            statement.setFloat( 5, good.getPrice() );
            statement.setString( 6, good.getScale() );
            statement.setInt( 7, good.getAmount() );
            statement.setString( 8, good.getColor() );
            statement.setString( 9, good.getSpecifications() );

            statement.executeUpdate();
            return DataAccessUtil.getNewRowKey( statement );
        }
    }

    public void deleteGood( int id ) throws SQLException {
        try ( Connection connection = DataAccessUtil.createConnection() ) {
            PreparedStatement statement = connection.prepareStatement( getDeleteQuery() );

            statement.setInt( 1, id );
            statement.executeUpdate();
        }
    }

    public void updateGood( Good good ) throws SQLException {
        try ( Connection connection = DataAccessUtil.createConnection() ) {
            PreparedStatement statement = connection.prepareStatement( getUpdateQuery() );

            statement.setString( 1, good.getName() );
            statement.setString( 2, good.getType() );
            statement.setString( 3, good.getManufacturer() );
            statement.setString( 4, good.getArticle() );
            statement.setFloat( 5, good.getPrice() );
            statement.setString( 6, good.getScale() );
            statement.setInt( 7, good.getAmount() );
            statement.setString( 8, good.getColor() );
            statement.setString( 9, good.getSpecifications() );

            statement.executeUpdate();
        }

    }

    public List<Good> findAll() throws SQLException {
        try ( Connection connection = DataAccessUtil.createConnection() ) {
            PreparedStatement statement = connection.prepareStatement( getSelectAllQuery() );

            ResultSet rs = statement.executeQuery();
            List<Good> result = new ArrayList<>();
            while ( rs.next() ) result.add( getGoodFromRow( rs ) );
            return result;
        }

    }

    public Good findById( int id ) throws SQLException {
        try ( Connection connection = DataAccessUtil.createConnection() ) {
            PreparedStatement statement = connection.prepareStatement( getSelectOneByIdQuery() );
            statement.setInt( 1, id );

            ResultSet rs = statement.executeQuery();
            if ( rs.next() ) return getGoodFromRow( rs );
        }

        return null;
    }

    public List<Good> findAllByName( String name ) throws SQLException {
        try ( Connection connection = DataAccessUtil.createConnection() ) {
            PreparedStatement statement = connection.prepareStatement( getSelectAllByNameQuery() );
            statement.setString( 1, name + '%' );

            ResultSet rs = statement.executeQuery();
            List<Good> result = new ArrayList<>();
            while ( rs.next() ) result.add( getGoodFromRow( rs ) );
            return result;
        }
    }

    private Good getGoodFromRow( ResultSet rs ) throws SQLException {
        Good g = new Good();
        g.setId( rs.getInt( 1 ) );
        g.setName( rs.getString( 2 ) );
        g.setType( rs.getString( 3 ) );
        g.setManufacturer( rs.getString( 4 ) );
        g.setArticle( rs.getString( 5 ) );
        g.setPrice( rs.getFloat( 6 ) );
        g.setScale( rs.getString( 7 ) );
        g.setAmount( rs.getInt( 8 ) );
        g.setColor( rs.getString( 9 ) );
        g.setSpecifications( rs.getString( 10 ) );

        return g;
    }

}
