package dao;

import model.Card;

import java.sql.PreparedStatement;
import java.util.Date;
public abstract class CardDao extends AbstractDao{
    public abstract void prepareParams(PreparedStatement preparedStatement, Card object);

    public abstract void prepareParamsForUpdate(PreparedStatement preparedStatement, Card object);

    public abstract Card getCardByAccountID(int accID);
    public abstract boolean isCardExists(String cardNumber);
//    public abstract List<Card> getCardExpire(Date withinDate);
}
