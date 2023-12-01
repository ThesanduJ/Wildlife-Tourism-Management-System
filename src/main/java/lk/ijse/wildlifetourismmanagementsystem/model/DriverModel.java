package lk.ijse.wildlifetourismmanagementsystem.model;

import lk.ijse.wildlifetourismmanagementsystem.db.DbConnection;
import lk.ijse.wildlifetourismmanagementsystem.dto.DriverDto;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class DriverModel {


    public boolean isAdd(DriverDto dto) throws SQLException {
        Connection connection= DbConnection.getInstance().getConnection();
        String sql="INSERT INTO jeep_driver VALUES(?,?,?,?,?,?,?)";
        PreparedStatement pstm=connection.prepareStatement(sql);

        pstm.setString(1, dto.getId());
        pstm.setString(2, dto.getNIC());
        pstm.setString(3, dto.getName());
        pstm.setString(4, dto.getP_id());
        pstm.setString(5, dto.getMobile());
        pstm.setString(6, dto.getEmail());
        pstm.setString(7, dto.getExpairDate());

        boolean isAdd=pstm.executeUpdate()>0;
        return isAdd;

    }
    public boolean isDeleted(String id) throws SQLException {
        Connection connection=DbConnection.getInstance().getConnection();
        String sql="DELETE FROM jeep_driver WHERE driver_id = ?";
        PreparedStatement pstm=connection.prepareStatement(sql);
        pstm.setString(1,id);

        return pstm.executeUpdate()>0;
    }

    public boolean isUpdate(DriverDto dto) throws SQLException {
        Connection connection=DbConnection.getInstance().getConnection();
        String sql="UPDATE jeep_driver SET  driver_nic=?,name=?,package_id=?,phone_number=?,admin_email=?,licence_expair_date=? WHERE driver_id=?";
        PreparedStatement pstm=connection.prepareStatement(sql);

        pstm.setString(1, dto.getNIC());
        pstm.setString(2, dto.getName());
        pstm.setString(3, dto.getP_id());
        pstm.setString(4, dto.getMobile());
        pstm.setString(5, dto.getEmail());
        pstm.setString(6, dto.getExpairDate());
        pstm.setString(7, dto.getId());

        return pstm.executeUpdate() > 0;
    }

}
