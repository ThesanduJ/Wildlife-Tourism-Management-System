package lk.ijse.wildlifetourismmanagementsystem.model;

import lk.ijse.wildlifetourismmanagementsystem.db.DbConnection;
import lk.ijse.wildlifetourismmanagementsystem.dto.AdminDto;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class AdminModel {

    public boolean saveAdmin(AdminDto dto) throws SQLException {
        Connection connection= DbConnection.getInstance().getConnection();
        String sql="INSERT INTO admin_panel VALUES(?,?,?)";
        PreparedStatement pstm=connection.prepareStatement(sql);
        pstm.setString(1,dto.getUsername());
        pstm.setString(2, dto.getEmail());
        pstm.setString(3, dto.getPassword());

        boolean isSaved=pstm.executeUpdate()>0;
        return isSaved;
    }
    public boolean isValid(String username,String password) throws SQLException {
        Connection connection=DbConnection.getInstance().getConnection();
        String sql="SELECT * FROM admin_panel WHERE username=? AND password=?";
        PreparedStatement pstm=connection.prepareStatement(sql);

        pstm.setString(1,username);
        pstm.setString(2,password);

        ResultSet resultSet=pstm.executeQuery();
        return resultSet.next();
    }

    public boolean resetPassword(String username, String password) throws SQLException {
        Connection connection=DbConnection.getInstance().getConnection();
        String sql="UPDATE admin_panel SET password=? WHERE username=?";
        PreparedStatement pstm=connection.prepareStatement(sql);

        pstm.setString(1,password);
        pstm.setString(2,username);

        return pstm.executeUpdate()>0;
    }
}
