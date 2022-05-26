package com.iot.client.repository.custom.impl;

import com.iot.client.builder.HistoryPaymentBuilder;
import com.iot.client.dto.output.sqlcustom.HistoryPaymentOutPut;
import com.iot.client.entity.HistoryPaymentEntity;
import com.iot.client.repository.custom.HistoryPaymentRepositoryCustom;
import com.iot.client.utils.BuildMapUtils;
import com.iot.client.utils.repository.RepositoryCustomUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;
import java.util.Objects;

@Repository
public class HistoryPaymentRepositoryImpl extends RepositoryCustomUtils<HistoryPaymentEntity> implements HistoryPaymentRepositoryCustom {

    @Autowired
    private BuildMapUtils buildMapUtils;

    @Override
    public List<HistoryPaymentOutPut> findAllHistoryPayment(HistoryPaymentBuilder historyPaymentBuilder, Pageable pageable) {
        Map<String, Object> parameter = buildMapUtils.buildMapSearch(historyPaymentBuilder);
        StringBuilder sql = new StringBuilder();
        sql.append("SELECT ");
        sql.append("    hp.payment_type as history_payment_payment_type , ");
        sql.append("    hp.total as history_payment_total , ");
        sql.append("    hp.createddate as history_payment_createddate , ");
        sql.append("    hp.modifieddate as history_payment_modifieddate , ");
        sql.append("    hp.modifiedby as history_payment_modifiedby , ");
        sql.append("    u.email as users_email, ");
        sql.append("    u.fullname as users_fullname , ");
        sql.append("    u.numberphone as users_numberphone , ");
        sql.append("    u.username as users_username ");
        sql.append("FROM history_payment hp ");
        sql.append("JOIN users u ");
        sql.append("ON hp.user_id = u.id ");
        sql.append("WHERE 1=1 ");
        sql.append("AND hp.status = 1 ");
        sql.append("AND u.status = 1 ");
        buildSqlWhereClause(sql, historyPaymentBuilder);
        return this.getResultList(sql.toString(), parameter, "findAll", pageable);
    }

    @Override
    public Long countHistoryPayment(HistoryPaymentBuilder historyPaymentBuilder) {
        Map<String, Object> parameter = buildMapUtils.buildMapSearch(historyPaymentBuilder);
        StringBuilder sql = new StringBuilder();
        sql.append("SELECT ");
        sql.append("    COUNT(hp.id) ");
        sql.append("FROM history_payment hp ");
        sql.append("JOIN users u ");
        sql.append("ON hp.user_id = u.id ");
        sql.append("WHERE 1=1 ");
        sql.append("AND hp.status = 1 ");
        sql.append("AND u.status = 1 ");
        buildSqlWhereClause(sql, historyPaymentBuilder);
        return Long.parseLong(this.getSingleResult(sql.toString(), parameter).toString());
    }

    private void buildSqlWhereClause(StringBuilder sql, HistoryPaymentBuilder historyPaymentBuilder) {
        if (StringUtils.isNotEmpty(historyPaymentBuilder.getEmail())) {
            sql.append("AND u.email LIKE :email ");
        }
        if (StringUtils.isNotEmpty(historyPaymentBuilder.getFullName())) {
            sql.append("AND u.fullname LIKE :fullname ");
        }
        if (StringUtils.isNotEmpty(historyPaymentBuilder.getNumberPhone())) {
            sql.append("AND u.numberphone LIKE :numberphone ");
        }
        if (StringUtils.isNotEmpty(historyPaymentBuilder.getUsername())) {
            sql.append("AND u.username LIKE :username ");
        }
        if (Objects.nonNull(historyPaymentBuilder.getTotalFrom())) {
            sql.append("AND hp.total >= :totalfrom ");
        }
        if (Objects.nonNull(historyPaymentBuilder.getTotalTo())) {
            sql.append("AND hp.total <= :totalto ");
        }
        if (StringUtils.isNotEmpty(historyPaymentBuilder.getPaymentType())) {
            sql.append("AND hp.payment_type LIKE :paymenttype ");
        }
        if (StringUtils.isNotEmpty(historyPaymentBuilder.getCreatedDateFrom())) {
            sql.append("AND hp.createddate >= :createddatefrom ");
        }
        if (StringUtils.isNotEmpty(historyPaymentBuilder.getCreatedDateTo())) {
            sql.append("AND hp.createddate <= :createddateto ");
        }
    }
}
