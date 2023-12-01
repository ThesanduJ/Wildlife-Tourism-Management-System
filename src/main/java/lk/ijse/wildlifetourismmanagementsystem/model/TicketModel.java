package lk.ijse.wildlifetourismmanagementsystem.model;

import lk.ijse.wildlifetourismmanagementsystem.db.DbConnection;
import lk.ijse.wildlifetourismmanagementsystem.dto.TicketDto;
import lk.ijse.wildlifetourismmanagementsystem.dto.TouristDto;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class TicketModel {


    public static boolean updateQty(String ticketsId, int qty) throws SQLException {
        Connection connection=DbConnection.getInstance().getConnection();
        String sql="UPDATE tickets SET ticket_count = ticket_count - ? WHERE ticket_id = ?";
        PreparedStatement pstm=connection.prepareStatement(sql);
        pstm.setInt(1,qty);
        pstm.setString(2,ticketsId);

        return pstm.executeUpdate()>0;
    }

    public boolean addTicket(TicketDto dto) throws SQLException {
        Connection connection= DbConnection.getInstance().getConnection();
        String sql="INSERT INTO tickets VALUES(?,?,?,?,?,?)";
        PreparedStatement pstm=connection.prepareStatement(sql);

        pstm.setString(1, dto.getTicket_id());
        pstm.setDouble(2,dto.getPrice());
        pstm.setString(3, dto.getTicket_type());
        pstm.setString(4, dto.getCashier_id());
        pstm.setInt(5, dto.getTicketCount());
        pstm.setString(6, dto.getPackageId());

        boolean isSaved=pstm.executeUpdate()>0;
        return isSaved;
    }


    public List<TicketDto> loadAllTickets() throws SQLException {
        Connection connection = DbConnection.getInstance().getConnection();

        String sql = "SELECT * FROM tickets";
        PreparedStatement pstm = connection.prepareStatement(sql);

        List<TicketDto> ticketList = new ArrayList<>();

        ResultSet resultSet = pstm.executeQuery();
        while (resultSet.next()) {
            ticketList.add(new TicketDto(
                    resultSet.getString(1),
                    resultSet.getDouble(2),
                    resultSet.getString(3),
                    resultSet.getString(4),
                    resultSet.getInt(5),
                    resultSet.getString(6)
            ));
        }

        return ticketList;
    }

    public List<TouristDto> loadAllTourist() throws SQLException {
        Connection connection = DbConnection.getInstance().getConnection();

        String sql = "SELECT * FROM tourist";
        PreparedStatement pstm = connection.prepareStatement(sql);

        List<TouristDto> touristList = new ArrayList<>();

        ResultSet resultSet = pstm.executeQuery();
        while (resultSet.next()) {
            touristList.add(new TouristDto(
                    resultSet.getString(1),
                    resultSet.getString(2),
                    resultSet.getString(3),
                    resultSet.getString(4),
                    resultSet.getString(5),
                    resultSet.getString(6),
                    resultSet.getString(7),
                    resultSet.getString(8),
                    resultSet.getString(9)
            ));
        }

        return touristList;
    }


    public TicketDto searchPackage(String ticketID) throws SQLException {
        Connection connection=DbConnection.getInstance().getConnection();
        String sql="SELECT * FROM tickets WHERE ticket_id = ?";
        PreparedStatement pstm=connection.prepareStatement(sql);
        pstm.setString(1,ticketID);
        ResultSet resultSet=pstm.executeQuery();
        TicketDto dto=null;

        if(resultSet.next()) {
            String ticketId = resultSet.getString(1);
            double price = resultSet.getDouble(2);
            String ticketType = resultSet.getString(3);
            String cashierId = resultSet.getString(4);
            int ticketCount=resultSet.getInt(5);
            String packageId=resultSet.getString(6);

            dto = new TicketDto(ticketId, price, ticketType, cashierId,ticketCount,packageId);
        }
        return dto;
    }

    public boolean idDelete(String ticketId) throws SQLException {
        Connection connection=DbConnection.getInstance().getConnection();
        String sql="DELETE FROM tickets WHERE ticket_id = ?";
        PreparedStatement pstm=connection.prepareStatement(sql);

        pstm.setString(1,ticketId);
        return pstm.executeUpdate()>0;
    }

    public boolean isUpdate(TicketDto dto) throws SQLException {
        Connection connection=DbConnection.getInstance().getConnection();
        String sql="UPDATE tickets SET  price=?,ticket_type=?,cashier_id=?,ticket_count=?,package_id=? WHERE ticket_id=?";
        PreparedStatement pstm=connection.prepareStatement(sql);
        pstm.setDouble(1,dto.getPrice());
        pstm.setString(2, dto.getTicket_type());
        pstm.setString(3, dto.getCashier_id());
        pstm.setInt(4,dto.getTicketCount());
        pstm.setString(5,dto.getPackageId());
        pstm.setString(6, dto.getTicket_id());

        return pstm.executeUpdate()>0;
    }

}
