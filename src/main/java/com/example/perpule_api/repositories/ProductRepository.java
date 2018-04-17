package com.example.perpule_api.repositories;

import com.example.perpule_api.helpers.RandomString;
import com.example.perpule_api.modals.ProductDataModal;
import com.example.perpule_api.modals.UserDBModal;
import com.example.perpule_api.modals.UserInputDataModal;
import com.example.perpule_api.modals.UserOutputDataModal;
import org.apache.log4j.Logger;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ProductRepository {

    private Connection connection = null;


    public ProductRepository() {
        String url = "jdbc:mysql://localhost:3306/perpule";
        String username = "root";
        String password = "joeydash";
        try {
            try {
                Class.forName("com.mysql.jdbc.Driver");
            } catch (ClassNotFoundException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
            connection = DriverManager.getConnection(url,username,password);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    public List<ProductDataModal> getAllProductData() {
        List<ProductDataModal> list = new ArrayList<>();
        String sql_query = "SELECT * FROM product_data";
        try {
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(sql_query);
            while(resultSet.next()){
                ProductDataModal productDataModal = new ProductDataModal();
                productDataModal.set_id(resultSet.getString(1));
                productDataModal.setProduct_name(resultSet.getString(2));
                productDataModal.setProduct_details(resultSet.getString(3));
                list.add(productDataModal);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return list;
    }

    public boolean createProduct(ProductDataModal productDataModal) {
        boolean isCreated = false;
        String sql_query = "INSERT INTO `product_data` (`product_name`, `product_details`) VALUES ('"+productDataModal.getProduct_name()+"','"+productDataModal.getProduct_details()+"');";
        try {
            PreparedStatement statement = connection.prepareStatement(sql_query);
            statement.executeUpdate();
            isCreated = true;

        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return isCreated;
    }

    public ProductDataModal getProduct(String id){
        ProductDataModal productDataModal = new ProductDataModal();
        String sql_query = "SELECT * FROM `product_data`  WHERE _id = "+id+"";
        try {
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(sql_query);
            if (!resultSet.next()){
                Logger.getLogger(getClass()).info("No product");
            }else {
                productDataModal.set_id(id);
                productDataModal.setProduct_name(resultSet.getString(2));
                productDataModal.setProduct_details(resultSet.getString(3));
            }
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return productDataModal;
    }

    public boolean deleteProduct(String id){
        boolean isDeleted = false;
        String sql_query = "DELETE * FROM `product_data`  WHERE _id = "+id+"";
        try {
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(sql_query);
            if (!resultSet.next()){
                Logger.getLogger(getClass()).info("No product");
            }else {
                isDeleted= true;
            }
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return isDeleted;
    }
}
