package lk.ijse.wildlifetourismmanagementsystem.model;

import lk.ijse.wildlifetourismmanagementsystem.db.DbConnection;
import lk.ijse.wildlifetourismmanagementsystem.dto.TouristDto;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
public class TouristModel {
    public boolean isAdd(TouristDto dto) throws SQLException {
        Connection connection= DbConnection.getInstance().getConnection();
        String sql="INSERT INTO tourist VALUES(?,?,?,?,?,?,?,?,?,?,?)";
        PreparedStatement pstm=connection.prepareStatement(sql);

        pstm.setString(1, dto.getTouristId());
        pstm.setString(2, dto.getNIC());
        pstm.setString(3, dto.getName());
        pstm.setString(4, dto.getAgeLevel());
        pstm.setString(5, dto.getEmail());
        pstm.setString(6, dto.getMobileNumber());
        pstm.setString(7, dto.getRegion());
        pstm.setString(8, dto.getCashierId());
        pstm.setString(9, dto.getAddress());
        pstm.setString(10, dto.getPassportId());
        pstm.setInt(11,dto.getAge());

        boolean isAdd=pstm.executeUpdate()>0;
        return isAdd;
    }

    public boolean isDelete(String touristId) throws SQLException {
        Connection connection=DbConnection.getInstance().getConnection();
        String sql="DELETE FROM tourist WHERE tourist_id=?";
        PreparedStatement pstm=connection.prepareStatement(sql);

        pstm.setString(1,touristId);

        return pstm.executeUpdate()>0;
    }

    public boolean isUpdate(TouristDto dto) throws SQLException {
        Connection connection=DbConnection.getInstance().getConnection();
        String sql="UPDATE tourist SET  nic=?,name=?,adult_or_child=?,tourist_email=?,phone_number=?,local_or_not=?,cashier_id=?,address=?,passport_number=?,age=? WHERE tourist_id=?";
        PreparedStatement pstm=connection.prepareStatement(sql);

        pstm.setString(1, dto.getNIC());
        pstm.setString(2, dto.getName());
        pstm.setString(3, dto.getAgeLevel());
        pstm.setString(4, dto.getEmail());
        pstm.setString(5, dto.getMobileNumber());
        pstm.setString(6, dto.getRegion());
        pstm.setString(7, dto.getCashierId());
        pstm.setString(8, dto.getAddress());
        pstm.setString(9, dto.getPassportId());
        pstm.setInt(10,dto.getAge());
        pstm.setString(11, dto.getTouristId());

        return pstm.executeUpdate()>0;
    }
}
