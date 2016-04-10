package com.yana.kursova.gui;

import com.yana.kursova.domain.Shop;

import javax.swing.table.AbstractTableModel;
import java.util.List;

/**
 * Created by NicholasG on 10.04.2016.
 */
public class ShopsTableModel extends AbstractTableModel {

    private final String[] columns = { "ID", "Name", "Address",
            "Phone", "Chief", "Site", "Schedule" };

    private List<Shop> shops;

    public ShopsTableModel( List<Shop> shops ) {
        this.shops = shops;
    }

    public void addShop( Shop shop ) {
        shops.add( shop );
        fireTableRowsInserted( 0, shops.size() );
    }

    public Shop getShopFromRow( int rowIndex ) {
        return shops.get( rowIndex );
    }

    public void removeRow( int rowIndex ) {
        shops.remove( rowIndex );
        fireTableRowsDeleted( rowIndex, rowIndex );
    }

    public void refreshTable() {
        fireTableRowsUpdated( 0, shops.size() );
    }

    @Override
    public int getRowCount() {
        return shops.size();
    }

    @Override
    public int getColumnCount() {
        return columns.length;
    }

    @Override
    public Object getValueAt( int rowIndex, int columnIndex ) {
        Shop s = shops.get( rowIndex );
        switch ( columnIndex ) {
            case 0:
                return String.valueOf( s.getId() );
            case 1:
                return s.getName();
            case 2:
                return s.getAddress();
            case 3:
                return s.getPhone();
            case 4:
                return s.getChief();
            case 5:
                return s.getSite();
            case 6:
                return s.getSchedule();
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
