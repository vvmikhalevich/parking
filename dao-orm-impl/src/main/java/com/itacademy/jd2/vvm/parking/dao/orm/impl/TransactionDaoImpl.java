package com.itacademy.jd2.vvm.parking.dao.orm.impl;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.itacademy.jd2.vvm.parking.dao.api.ITransactionDao;
import com.itacademy.jd2.vvm.parking.dao.api.entity.table.ITransaction;
import com.itacademy.jd2.vvm.parking.dao.api.filter.TransactionFilter;
import com.itacademy.jd2.vvm.parking.dao.orm.impl.entity.Transaction;

@Repository
public class TransactionDaoImpl extends AbstractDaoImpl<ITransaction, Integer> implements ITransactionDao {

	protected TransactionDaoImpl() {
		super(Transaction.class);
	}

	@Override
	public ITransaction createEntity() {
		final Transaction transaction = new Transaction();
		return transaction;
	}

	@Override
	public List<ITransaction> find(TransactionFilter filter) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public long getCount(TransactionFilter filter) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public ITransaction getFullInfo(Integer id) {
		// TODO Auto-generated method stub
		return null;
	}

}
