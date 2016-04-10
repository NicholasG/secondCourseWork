package com.yana.kursova.gui;

import com.yana.kursova.domain.Good;

import javax.swing.table.AbstractTableModel;
import java.util.List;

/**
 * Created by NicholasG on 10.04.2016.
 */
public class GoodsTableModel extends AbstractTableModel {

    private final String[] columns = { "ID", "Name", "Type",
            "Manufacturer", "Article", "Price",
            "Scale", "Amount", "Color", "Specifications" };

    private List<Good> goods;

    public GoodsTableModel( List<Good> goods ) {
        this.goods = goods;
    }

    public void addGood( Good good ) {
        goods.add( good );
        fireTableRowsInserted( 0, goods.size() );
    }

    public Good getGoodFromRow( int rowIndex ) {
        return goods.get( rowIndex );
    }

    public void removeRow( int rowIndex ) {
        goods.remove( rowIndex );
        fireTableRowsDeleted( rowIndex, rowIndex );
    }

    public void refreshTable() {
        fireTableRowsUpdated( 0, goods.size() );
    }

    @Override
    public int getRowCount() {
        return goods.size();
    }

    @Override
    public int getColumnCount() {
        return columns.length;
    }

    @Override
    public Object getValueAt( int rowIndex, int columnIndex ) {
        Good g = goods.get( rowIndex );
        switch ( columnIndex ) {
            case 0:
                return String.valueOf( g.getId() );
            case 1:
                return g.getName();
            case 2:
                return g.getType();
            case 3:
                return g.getManufacturer();
            case 4:
                return g.getArticle();
            case 5:
                return String.valueOf( g.getPrice() );
            case 6:
                return g.getScale();
            case 7:
                return String.valueOf( g.getAmount() );
            case 8:
                return g.getColor();
            case 9:
                return g.getSpecifications();
            default:
                return null;
        }
    }

    @Override
    public String getColumnName( int column ) {
        return columns[column];
    }

    @Override
    public Class<?> getColumnClass( int columnIndex ) {
        return String.class;
    }

}
