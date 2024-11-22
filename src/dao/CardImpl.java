package dao;

import model.Account;
import model.Card;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;

public class CardImpl extends CardDao{
    @Override
    public String getTableName() {
        return "cards";
    }

    @Override
    public Card converToObject(ResultSet resultset) {
        Card card = null;
        try{
            int id = resultset.getInt("id");
            String number = resultset.getString("number");
            int cardType = resultset.getInt("card_type");
            Date expireDate = resultset.getDate("expire_date");;
            String secCode = resultset.getString("security_code");
            int acc_id = resultset.getInt("account_id");

            card = new Card();
            card.setId(id);
            card.setCardNumber(number);
            card.setCardType(cardType);
            card.setCardExpireDate((java.sql.Date) expireDate);
            card.setSecurityCode(secCode);

            try{
                AccountDao accountDao = new AccountDao();
                Account account = accountDao.getById(acc_id);
                card.setAccountId(account);
            }catch (Exception e){
                System.out.print("Error: "+e.getMessage());
//                e.printStackTrace();
            }

        }catch (SQLException e){
            System.out.print("SQL Exception for : "+e.getMessage());
        }
        return card;
    }

    @Override
    public String getInsertQuery() {
        // insert into cards (card_type,expire_date,security_code)
        return "insert into "+this.getTableName()+" (card_type,expire_date,security_code)"+"values (?,?,?)";
    }

    @Override
    public String getUpdateQuery() {
        return "update"+this.getTableName()+" set ";
    }

    @Override
    public String getDeleteQuery() {
        return "delete from "+this.getTableName()+" where id = ?";
    }

    @Override
    public void prepareParams(PreparedStatement preparedStatement, Object object) {

    }

    @Override
    public void prepareParamsForUpdate(PreparedStatement preparedStatement, Object object) {

    }

    @Override
    public void prepareParams(PreparedStatement preparedStatement, Card object) {
        try{
            preparedStatement.setString(1,object.getCardNumber());
            preparedStatement.setInt(2,object.getCardType());
            preparedStatement.setDate(3,object.getCardExpireDate());
            preparedStatement.setString(4,object.getSecurityCode());
            preparedStatement.setObject(5,object.getAccountId());
        }catch(SQLException e){
            System.out.print("SQL Exception for: "+e.getMessage());
        }
    }

    @Override
    public void prepareParamsForUpdate(PreparedStatement preparedStatement, Card object) {
        try {
            preparedStatement.setInt(1, object.getCardType());
            preparedStatement.setDate(2, object.getCardExpireDate());
            preparedStatement.setString(3, object.getSecurityCode());
//            preparedStatement.setInt(4, object.getId());
        } catch (SQLException e) {
            System.out.print("SQL Exception for: " + e.getMessage());
        }
    }



    @Override
    public Card getCardByAccountID(int accID) {
        Card card = null;
        String query = "select * from "+ this.getTableName()+" where account_id = ?";
        // select * from cards where account_id = ?
        try (Connection connection = connectionFactory.createConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(query)){
             preparedStatement.setInt(1,accID);
                try(ResultSet resultSet = preparedStatement.executeQuery()){
                if (resultSet.next()){
                    int card_id  = resultSet.getInt("id");
                    return (Card) getById(card_id);
                }
            }
        } catch (SQLException e){
            System.out.print("SQL Exception for: "+e.getMessage());
        }finally {
            this.connectionFactory.closeConnection();
        }
        return card;
    }

    @Override
    public boolean isCardExists(String cardNumber) {
        if(cardNumber == null || cardNumber.trim().isEmpty()){
            return  false;
        }
//        boolean flag =  false;
        String query = "SELECT number FROM "+this.getTableName()+" WHERE number = ?";
        try(Connection connection = connectionFactory.createConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(query)){
            preparedStatement.setString(1,cardNumber);
            ResultSet resultSet = preparedStatement.executeQuery();
            return  resultSet.next();
        }catch (SQLException e){
            System.out.print("SQL Exception from isCardExists: "+e.getMessage());
            return false;
        }
    }

//    @Override
//    public List<Card> getCardExpire(Date withinDate) {
//        List<Card> cards = new ArrayList<>();
//        String query = "SELECT * FROM " + this.getTableName() + " WHERE expire_date <= ?";
//        try (Connection connection = connectionFactory.createConnection();
//             PreparedStatement preparedStatement = connection.prepareStatement(query)) {
//            preparedStatement.setDate(1, new java.sql.Date(withinDate.getTime()));
//            ResultSet resultSet = preparedStatement.executeQuery();
//            while (resultSet.next()) {
//                cards.add(this.converToObject(resultSet));
//            }
//        } catch (SQLException e) {
//            System.out.print("SQL Exception for: " + e.getMessage());
//        }
//        return cards;
//    } // expireဖြစ်ဖို့ ဘယ်နရက်ကျန်သေးတယ်ဆိုတာတွက်ဖို့
//    }

}
