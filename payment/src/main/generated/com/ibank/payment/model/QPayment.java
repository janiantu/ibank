package com.ibank.payment.model;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.Generated;
import com.querydsl.core.types.Path;


/**
 * QPayment is a Querydsl query type for Payment
 */
@Generated("com.querydsl.codegen.EntitySerializer")
public class QPayment extends EntityPathBase<Payment> {

    private static final long serialVersionUID = 407459907L;

    public static final QPayment payment = new QPayment("payment");

    public final NumberPath<Double> amount = createNumber("amount", Double.class);

    public final StringPath bicCode = createString("bicCode");

    public final NumberPath<Double> cancellationFee = createNumber("cancellationFee", Double.class);

    public final StringPath creditorIban = createString("creditorIban");

    public final StringPath currency = createString("currency");

    public final StringPath debtorIban = createString("debtorIban");

    public final StringPath details = createString("details");

    public final NumberPath<Long> id = createNumber("id", Long.class);

    public final StringPath status = createString("status");

    public final DateTimePath<java.time.LocalDateTime> transactionDate = createDateTime("transactionDate", java.time.LocalDateTime.class);

    public final StringPath type = createString("type");

    public QPayment(String variable) {
        super(Payment.class, forVariable(variable));
    }

    public QPayment(Path<? extends Payment> path) {
        super(path.getType(), path.getMetadata());
    }

    public QPayment(PathMetadata metadata) {
        super(Payment.class, metadata);
    }

}

