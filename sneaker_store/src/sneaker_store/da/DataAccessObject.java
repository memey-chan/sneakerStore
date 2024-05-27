/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package sneaker_store.da;

import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

/**
 *
 * @author Robert
 */
public class DataAccessObject {
    String driver = "com.mysql.jdbc.Driver";
    String url = "jdbc:mysql://localhost/sneaker_store";
    String username = "root";
    String password = "";
    
    public String newShoes (String name, double price, int stock, String imagePath) {
        String query = "insert into shoes (shoeName, shoePrice, shoeStock, imgPath) values (?,?,?,?)";
        try {
            Class.forName(driver);
            PreparedStatement conn = DriverManager.getConnection(url, username, password).prepareStatement(query);
            conn.setString(1, name);
            conn.setDouble(2, price);
            conn.setInt(3, stock);
            conn.setString(4, imagePath);
            conn.execute();
            
        }
        catch(Exception e){
            e.printStackTrace();
            System.err.println(e.getMessage());
            
        }
        return "True";
        
    }
        public String updateShoes (double price, int stock, String imagePath, String name) {
        String query = "update shoes SET shoePrice = ?, shoeStock = ?, imgPath = ? WHERE shoeName = ?";
        try {
            Class.forName(driver);
            PreparedStatement conn = DriverManager.getConnection(url, username, password).prepareStatement(query);
            conn.setDouble(1, price);
            conn.setInt(2, stock);
            conn.setString(3, imagePath);
            conn.setString(4, name);
            conn.execute();
            
        }
        catch(Exception e){
            e.printStackTrace();
            System.err.println(e.getMessage());
            
        }
        return "True";
        
    }
    
    public String removeShoes (String name) {
        String query = "delete from shoes where shoeName = ?";
        try {
            Class.forName(driver);
            PreparedStatement conn = DriverManager.getConnection(url, username, password).prepareStatement(query);
            conn.setString(1, name);
            conn.execute();
            
        }
        catch(Exception e){
            e.printStackTrace();
            System.err.println(e.getMessage());
            
        }
        return "True";
        
    }
    
    public ArrayList<Shoe> showShoes () {
        ArrayList <Shoe> Shoes = new ArrayList();
        String query = "select * from shoes";
        try {
            Class.forName(driver);
            PreparedStatement conn = DriverManager.getConnection(url, username, password).prepareStatement(query);
            ResultSet rs = conn.executeQuery();
            while (rs.next()) {
                Shoe shoe = new Shoe();
                shoe.setId(rs.getInt("id"));
                shoe.setName(rs.getString("shoeName"));
                shoe.setStock(rs.getInt("shoeStock"));
                shoe.setPrice(rs.getInt("shoePrice"));
                shoe.setImagePath(rs.getString("imgPath"));
                
                Shoes.add(shoe);
            }
        }
        catch(Exception e){
            e.printStackTrace();
            System.err.println(e.getMessage());
            
        }
        return Shoes;
        
    }
    
    
}
