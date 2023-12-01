package lk.ijse.wildlifetourismmanagementsystem.model;
import lk.ijse.wildlifetourismmanagementsystem.db.DbConnection;
import lk.ijse.wildlifetourismmanagementsystem.dto.FinanceDto;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;


public class FinanceModel {


//    public boolean placeBill(FinanceDto dto) throws SQLException {
//        Connection connection=null;
//        try {
//
//            connection=DbConnection.getInstance().getConnection();
//            connection.setAutoCommit(false);
//
//            boolean isPlace=Bill.placeBill(dto.getTouristIds(),dto.getTicketsId(),dto.getPackagesId(),dto.getTicketsPrice(),dto.getPackagesPrice(),dto.getPaidAmount(),dto.getQty());
//
//            if (isPlace){
//                boolean updateQty=updateQty(dto);
//                if (updateQty){
//                    connection.commit();
//                    return true;
//                }else {
//                    connection.rollback();
//                }
//            }else {
//                connection.rollback();
//            }
//        } catch (SQLException e) {
//            if (connection!=null) connection.rollback();
//            e.printStackTrace();
//        }finally {
//            if (connection!=null) connection.setAutoCommit(true);
//        }
//        return false;
//    }

    public boolean placeBill(FinanceDto dto) throws SQLException {
        try (Connection connection = DbConnection.getInstance().getConnection()) {
            connection.setAutoCommit(false);

            boolean isPlace = FinanceModel.placeTicket(dto.getTouristIds(), dto.getTicketsId(), dto.getPackagesId(), dto.getTicketsPrice(), dto.getPackagesPrice(), dto.getPaidAmount(), dto.getQty());

            if (isPlace) {
                boolean updateQty = updateQty(dto);
                if (updateQty) {
                    connection.commit();
                    return true;
                } else {
                    connection.rollback();
                }
            } else {
                connection.rollback();
            }
        } catch (SQLException e) {
            handleSQLException(e);
        }

        return false;
    }

    private static boolean placeTicket(String touristIds, String ticketsId, String packagesId, double ticketsPrice, double packagesPrice, double paidAmount, int qty) throws SQLException {

        Connection connection=DbConnection.getInstance().getConnection();
        String sql="INSERT INTO finance_details values(?,?,?,?,?,?,?)";
        PreparedStatement pstm=connection.prepareStatement(sql);

        pstm.setString(1,touristIds);
        pstm.setString(2,ticketsId);
        pstm.setString(3,packagesId);
        pstm.setDouble(4,ticketsPrice);
        pstm.setDouble(5,packagesPrice);
        pstm.setDouble(6,paidAmount);
        pstm.setInt(7,qty);

        return pstm.executeUpdate()>0;
    }

    private void handleSQLException(SQLException e) {
        // Log the exception appropriately
        System.err.println("Error occurred while placing bill: " + e.getMessage());
        // Perform any necessary cleanup tasks, such as closing resources or notifying users
        e.printStackTrace();
    }


    private static boolean updateQty(FinanceDto dto) throws SQLException {
        boolean isUpdate=TicketModel.updateQty(dto.getTicketsId(),dto.getQty());

        if(!isUpdate){
            return false;
        }
        return true;
    }
}
