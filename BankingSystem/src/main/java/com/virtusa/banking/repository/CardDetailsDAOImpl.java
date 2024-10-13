package com.virtusa.banking.repository;

import java.util.List;

import javax.transaction.Transactional;

import org.hibernate.query.Query;
import org.springframework.stereotype.Repository;

import com.virtusa.banking.model.CardDetails;
import com.virtusa.banking.model.CardType;

@Repository
public class CardDetailsDAOImpl extends BaseRepository implements CardDetailsDao {

	@Override
	@Transactional
	public List<CardDetails> getCardsByAccountNumberAndType(long accountNumber, CardType cardType) {

		List<CardDetails> cards = null;
		try {

			Query<CardDetails> query = super.getHibernateTemplate().getSessionFactory().getCurrentSession().createQuery(
					"FROM CardDetails c WHERE c.account.accountNumber = :accountNumber AND c.cardType = :cardType",
					CardDetails.class);
			query.setParameter("accountNumber", accountNumber);
			query.setParameter("cardType", cardType);

			cards = query.getResultList();

		} catch (Exception e) {

			e.printStackTrace();
		}
		return cards;

	}

	@Override
	@Transactional
	public CardDetails getCardByCardNumber(long cardNumber) {

		CardDetails card = null;
		try {

			Query<CardDetails> query = super.getHibernateTemplate().getSessionFactory().getCurrentSession()
					.createQuery("FROM CardDetails c WHERE c.cardNumber = :cardNumber", CardDetails.class);
			query.setParameter("cardNumber", cardNumber);

			card = query.uniqueResult();

		} catch (Exception e) {

			e.printStackTrace();
		}
		return card;
	}
}
