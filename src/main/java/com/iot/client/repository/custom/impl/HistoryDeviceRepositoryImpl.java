package com.iot.client.repository.custom.impl;

import com.iot.client.builder.HistoryDeviceSearchBuilder;
import com.iot.client.dto.output.sqlcustom.HistoryDeviceOutPut;
import com.iot.client.entity.HistoryDeviceEntity;
import com.iot.client.repository.custom.HistoryDeviceRepositoryCustom;
import com.iot.client.utils.BuildMapUtils;
import com.iot.client.utils.repository.RepositoryCustomUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;
import java.util.Objects;

/**
 * @author datdv
 */
@Repository
public class HistoryDeviceRepositoryImpl extends RepositoryCustomUtils<HistoryDeviceEntity> implements HistoryDeviceRepositoryCustom {

    @Autowired
    private BuildMapUtils buildMapUtils;

    @Override
    public List<HistoryDeviceOutPut> findAllHistoryDeviceByCondition(HistoryDeviceSearchBuilder builder, Pageable pageable) {
        Map<String, Object> parameter = buildMapUtils.buildMapSearch(builder);
        StringBuilder sql = new StringBuilder();
        sql.append("SELECT ");
        sql.append("    hd.id AS history_device_id, ");
        sql.append("    ( ");
        sql.append("        CASE hd.deleted ");
        sql.append("        WHEN 0 THEN 'NOT DELETE' ");
        sql.append("        WHEN 1 THEN 'DELETED' ");
        sql.append("        END ");
        sql.append("    ) AS history_deleted, ");
        sql.append("    hd.start_date AS start_date, ");
        sql.append("    hd.end_date AS end_date, ");
        sql.append("    hd.total_payment AS total_payment, ");
        sql.append("    hd.total_time AS total_time, ");
        sql.append("    u.id AS user_id, ");
        sql.append("    u.fullname AS user_full_name_use, ");
        sql.append("    u.numberphone AS user_phone_number_use, ");
        sql.append("    u.email AS user_email_use, ");
        sql.append("    d.id AS device_id, ");
        sql.append("    d.code AS device_code, ");
        sql.append("    d.name AS device_name, ");
        sql.append("    pl.id AS price_level_id, ");
        sql.append("    pl.price AS price_level_price, ");
        sql.append("    pl.time_form AS price_level_time_form, ");
        sql.append("    pl.time_to AS price_level_time_to, ");
        sql.append("    ua.fullname AS user_admin_device_name ");
        buildSqlJoin(sql);
        buildSqlWhereClause(sql, builder);
        sql.append("ORDER BY hd.start_date DESC ");
        return this.getResultList(sql.toString(), parameter, "findAllHistoryDeviceByCondition", pageable);
    }

    @Override
    public Long countHistoryDeviceByCondition(HistoryDeviceSearchBuilder builder) {
        Map<String, Object> parameter = buildMapUtils.buildMapSearch(builder);
        StringBuilder sql = new StringBuilder();
        sql.append("SELECT ");
        sql.append("    COUNT(hd.id) ");
        buildSqlJoin(sql);
        buildSqlWhereClause(sql, builder);
        return Long.parseLong(this.getSingleResult(sql.toString(), parameter).toString());
    }

    private void buildSqlJoin(StringBuilder sql) {
        sql.append("FROM history_device hd ");
        sql.append("INNER JOIN users u ON hd.user_id = u.id ");
        sql.append("INNER JOIN devices d ON hd.device_id = d.id ");
        sql.append("LEFT JOIN users ua ON d.user_id = ua.id ");
        sql.append("INNER JOIN price_level pl ON hd.price_level_id = pl.id ");
        sql.append("WHERE 1=1 ");
    }

    private void buildSqlWhereClause(StringBuilder sql, HistoryDeviceSearchBuilder builder) {
        if (StringUtils.isNotEmpty(builder.getStartDate())) {
            sql.append("AND hd.start_date >= :startdate ");
        }
        if (StringUtils.isNotEmpty(builder.getStartToDate())) {
            sql.append("AND hd.start_date <= :starttodate ");
        }
        if (StringUtils.isNotEmpty(builder.getEndDate())) {
            sql.append("AND hd.end_date >= :enddate ");
        }
        if (StringUtils.isNotEmpty(builder.getEndToDate())) {
            sql.append("AND hd.end_date <= :endtodate ");
        }
        if (StringUtils.isNotEmpty(builder.getFullName())) {
            sql.append("AND u.fullname LIKE :fullname ");
        }
        if (StringUtils.isNotEmpty(builder.getNumberPhone())) {
            sql.append("AND u.numberphone LIKE :numberphone ");
        }
        if (StringUtils.isNotEmpty(builder.getEmail())) {
            sql.append("AND u.email LIKE :email ");
        }
        if (Objects.nonNull(builder.getPrice())) {
            sql.append("AND pl.price = :price ");
        }
        if (StringUtils.isNotEmpty(builder.getName())) {
            sql.append("AND d.name LIKE :name ");
        }
        if (Objects.nonNull(builder.getTimeForm())) {
            sql.append("AND pl.timeform >= :timeform ");
        }
        if (Objects.nonNull(builder.getTimeTo())) {
            sql.append("AND pl.timeto <= :timeto ");
        }
    }
}
