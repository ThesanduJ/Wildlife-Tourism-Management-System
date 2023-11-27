package lk.ijse.wildlifetourismmanagementsystem.model;

import lk.ijse.wildlifetourismmanagementsystem.db.DbConnection;
import lk.ijse.wildlifetourismmanagementsystem.dto.GuideDto;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class GuideModel {
    public boolean isAdd(GuideDto guideDto) throws SQLException {
        Connection connection= DbConnection.getInstance().getConnection();
        String sql="INSERT INTO tourist_guide VALUES(?,?,?,?,?,?,?)";
        PreparedStatement pstm=connection.prepareStatement(sql);
         pstm.setString(1, guideDto.getNic());
         pstm.setString(2, guideDto.getName());
         pstm.setString(3, guideDto.getMobile());
         pstm.setString(4, guideDto.getDate());
         pstm.setString(5, guideDto.getAddress());
         pstm.setString(6, guideDto.getPackageId());
         pstm.setString(7, guideDto.getEmail());
         
         return pstm.executeUpdate()>0;
    }

    public boolean isDeleted(String nic) throws SQLException {
        Connection connection=DbConnection.getInstance().getConnection();
        String sql="DELETE FROM tourist_guide WHERE guide_nic = ?";
        PreparedStatement pstm=connection.prepareStatement(sql);
        pstm.setString(1,nic);

        return  pstm.executeUpdate()>0;
    }

    public boolean isUpdated(GuideDto dto) throws SQLException {
        Connection connection=DbConnection.getInstance().getConnection();
        String sql="UPDATE tourist_guide SET name=?,phone_number=?,licence_expair_date=?,address=?,package_id=?,admin_email=? WHERE guide_nic=?";
        PreparedStatement pstm=connection.prepareStatement(sql);


        pstm.setString(1, dto.getName());
        pstm.setString(2, dto.getMobile());
        pstm.setString(3, dto.getDate());
        pstm.setString(4, dto.getAddress());
        pstm.setString(5, dto.getPackageId());
        pstm.setString(6, dto.getEmail());
        pstm.setString(7, dto.getNic());

        return pstm.executeUpdate()>0;
    }
}
