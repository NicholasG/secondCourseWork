package com.yana.kursova.dao.queries;

public final class GoodQueries {

    private static final String INSERT_QUERY = "INSERT INTO good " +
            "(name, type, manufacturer, article, price, scale, amount, color, specifications) " +
            "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)";
    private static final String UPDATE_QUERY = "UPDATE good " +
            "SET name = ?, type = ?, manufacturer = ?, article = ?, price = ?, scale = ?, " +
            "amount = ?, color = ?, specifications = ? " +
            "WHERE id = ?";
    private static final String DELETE_QUERY = "DELETE FROM good WHERE id = ?";
    private static final String SELECT_ALL_QUERY = "SELECT * FROM good";
    private static final String SELECT_ONE_BY_ID_QUERY = "SELECT * FROM good WHERE id = ?";
    private static final String SELECT_ALL_BY_NAME_QUERY = "SELECT * FROM good WHERE UPPER(name) LIKE UPPER(?)";
    private static final String SELECT_ALL_GOODS_BY_SHOP_ID = "SELECT good_id FROM good_has_shop WHERE shop_id = ?";
    private static final String DELETE_GOOD_FROM_SHOP_QUERY = "DELETE FROM good_has_shop " +
            "WHERE shop_id = ? AND good_id = ?";

    public static String getSelectAllByNameQuery() {
        return SELECT_ALL_BY_NAME_QUERY;
    }

    public static String getSelectOneByIdQuery() {
        return SELECT_ONE_BY_ID_QUERY;
    }

    public static String getInsertQuery() {
        return INSERT_QUERY;
    }

    public static String getUpdateQuery() {
        return UPDATE_QUERY;
    }

    public static String getDeleteQuery() {
        return DELETE_QUERY;
    }

    public static String getSelectAllQuery() {
        return SELECT_ALL_QUERY;
    }

    public static String getSelectAllGoodsByShopId() {
        return SELECT_ALL_GOODS_BY_SHOP_ID;
    }

    public static String getDeleteGoodFromShopQuery() {
        return DELETE_GOOD_FROM_SHOP_QUERY;
    }

}
