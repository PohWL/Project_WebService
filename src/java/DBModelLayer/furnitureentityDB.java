/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DBModelLayer;

import Entity.Furniture;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author junwe
 */
public class furnitureentityDB {
    private String stmt = "";
    private PreparedStatement ps;
    
    public List<Furniture> getFurnitureList(Long countryID) throws SQLException{
        List<Furniture> list = new ArrayList<>();
        try {
            Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/islandfurniture-it07?zeroDateTimeBehavior=convertToNull&user=root&password=12345");

            if (countryID == null) {
                stmt = "SELECT i.ID as id, i.NAME as name, f.IMAGEURL as imageURL, i.SKU as sku, i.DESCRIPTION as description, i.TYPE as type, i._LENGTH as length, i.WIDTH as width, i.HEIGHT as height, i.CATEGORY as category FROM itementity i, furnitureentity f where i.ID=f.ID and i.ISDELETED=FALSE;";
                ps = conn.prepareStatement(stmt);
            } else {
                stmt = "SELECT i.ID as id, i.NAME as name, f.IMAGEURL as imageURL, i.SKU as sku, i.DESCRIPTION as description, i.TYPE as type, i._LENGTH as length, i.WIDTH as width, i.HEIGHT as height, i.CATEGORY as category, ic.RETAILPRICE as price FROM itementity i, furnitureentity f, item_countryentity ic where i.ID=f.ID and i.ID=ic.ITEM_ID and i.ISDELETED=FALSE and ic.COUNTRY_ID=?;";
                ps = conn.prepareStatement(stmt);
                ps.setLong(1, countryID);
            }

            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                Furniture f = new Furniture();
                f.setId(rs.getLong("id"));
                f.setName(rs.getString("name"));
                f.setImageUrl(rs.getString("imageURL"));
                f.setSKU(rs.getString("sku"));
                f.setDescription(rs.getString("description"));
                f.setType(rs.getString("type"));
                f.setWidth(rs.getInt("width"));
                f.setHeight(rs.getInt("height"));
                f.setLength(rs.getInt("length"));
                f.setCategory(rs.getString("category"));
                if (countryID != null) {
                    f.setPrice(rs.getDouble("price"));
                }
                list.add(f);
            }
        } catch (Exception ex) {
            throw ex;
        }
        return list;
    }
    
    public List<Furniture> getFurnitureListByCategory(Long countryID, String category) throws SQLException{
        List<Furniture> furnitureList = new ArrayList<>();
        try {
            Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/islandfurniture-it07?zeroDateTimeBehavior=convertToNull&user=root&password=12345");

            if (countryID == null) {
                stmt = "SELECT i.ID as id, i.NAME as name, f.IMAGEURL as imageURL, i.SKU as sku, i.DESCRIPTION as description, i.TYPE as type, i._LENGTH as length, i.WIDTH as width, i.HEIGHT as height, i.CATEGORY as category FROM itementity i, furnitureentity f where i.ID=f.ID and i.ISDELETED=FALSE and i.CATEGORY=?;";
                ps = conn.prepareStatement(stmt);
                ps.setString(1, category);
            } else {
                stmt = "SELECT i.ID as id, i.NAME as name, f.IMAGEURL as imageURL, i.SKU as sku, i.DESCRIPTION as description, i.TYPE as type, i._LENGTH as length, i.WIDTH as width, i.HEIGHT as height, i.CATEGORY as category, ic.RETAILPRICE as price FROM itementity i, furnitureentity f, item_countryentity ic where i.ID=f.ID and i.ID=ic.ITEM_ID and i.ISDELETED=FALSE and ic.COUNTRY_ID=? and i.CATEGORY=?;";
                ps = conn.prepareStatement(stmt);
                ps.setLong(1, countryID);
                ps.setString(2, category);
            }
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                Furniture f = new Furniture();
                f.setId(rs.getLong("id"));
                f.setName(rs.getString("name"));
                f.setImageUrl(rs.getString("imageURL"));
                f.setSKU(rs.getString("sku"));
                f.setDescription(rs.getString("description"));
                f.setType(rs.getString("type"));
                f.setWidth(rs.getInt("width"));
                f.setHeight(rs.getInt("height"));
                f.setLength(rs.getInt("length"));
                f.setCategory(rs.getString("category"));
                if (countryID != null) {
                    f.setPrice(rs.getDouble("price"));
                }
                furnitureList.add(f);
            }
        } catch (Exception ex) {
            throw ex;
        }
        return furnitureList;
    }
}
