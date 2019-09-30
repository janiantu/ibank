package com.ibank.payment.repository;

import java.util.Optional;

import org.bitbucket.gt_tech.spring.data.querydsl.value.operators.ExpressionProviderFactory;
import org.springframework.data.querydsl.QueryDslPredicateExecutor;
import org.springframework.data.querydsl.binding.QuerydslBinderCustomizer;
import org.springframework.data.querydsl.binding.QuerydslBindings;
import org.springframework.data.repository.CrudRepository;
import com.ibank.payment.model.Payment;
import com.ibank.payment.model.QPayment;

public interface PaymentRepository extends CrudRepository<Payment, Long>, QueryDslPredicateExecutor<Payment>, QuerydslBinderCustomizer<QPayment> {
    
	Optional<Payment> findById(long id);
	
	@Override
	default void customize(QuerydslBindings bindings, QPayment root) {

		bindings.bind( root.id)
		  .all((path, values) -> ExpressionProviderFactory.getPredicate(path, values).orElse(null));
		bindings.bind( root.type)
		  .all((path, values) -> ExpressionProviderFactory.getPredicate(path, values).orElse(null));
		bindings.bind( root.currency)
		  .all((path, values) -> ExpressionProviderFactory.getPredicate(path, values).orElse(null));
		bindings.bind( root.amount)
		  .all((path, values) -> ExpressionProviderFactory.getPredicate(path, values).orElse(null));
		bindings.bind( root.debtorIban)
		  .all((path, values) -> ExpressionProviderFactory.getPredicate(path, values).orElse(null));
		bindings.bind( root.creditorIban)
		  .all((path, values) -> ExpressionProviderFactory.getPredicate(path, values).orElse(null));
		bindings.bind( root.status)
		  .all((path, values) -> ExpressionProviderFactory.getPredicate(path, values).orElse(null));
		bindings.bind( root.details)
		  .all((path, values) -> ExpressionProviderFactory.getPredicate(path, values).orElse(null));
		bindings.bind( root.bicCode)
		  .all((path, values) -> ExpressionProviderFactory.getPredicate(path, values).orElse(null));
		bindings.bind( root.transactionDate)
		  .all((path, values) -> ExpressionProviderFactory.getPredicate(path, values).orElse(null));
	}
	


}
