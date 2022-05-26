package com.iot.client.repository.custom.impl;

import com.iot.client.builder.DeviceBuilder;
import com.iot.client.dto.output.DeviceOutput;
import com.iot.client.entity.DeviceEntity;
import com.iot.client.repository.custom.DeviceRepositoryCustom;
import com.iot.client.utils.BuildMapUtils;
import com.iot.client.utils.repository.RepositoryCustomUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;

@Repository
public class DeviceRepositoryImpl extends RepositoryCustomUtils<DeviceEntity> implements DeviceRepositoryCustom {

    @Autowired
    private BuildMapUtils buildMapUtils;

    @Override
    public List<DeviceOutput> findAllDevice(DeviceBuilder deviceBuilder, Pageable pageable) {
        Map<String, Object> parameter = buildMapUtils.buildMapSearch(deviceBuilder);
        StringBuilder sql = new StringBuilder();
        buildSql(sql);
        buildSqlWhereClause(sql, deviceBuilder);
        sql.append("ORDER BY d.id  DESC ");
        return this.getResultList(sql.toString(), parameter, "findAllDevice", pageable);
    }

    @Override
    public Long countDevice(DeviceBuilder deviceBuilder) {
        Map<String, Object> parameter = buildMapUtils.buildMapSearch(deviceBuilder);
        StringBuilder sql = new StringBuilder();
        sql.append("SELECT ");
        sql.append("    COUNT(d.id) ");
        sql.append("FROM devices d ");
        sql.append("JOIN users u ON d.user_id = u.id ");
        sql.append("LEFT JOIN price_level_view plv ON d.id = plv.device_id ");
        sql.append("WHERE 1=1 ");
        buildSqlWhereClause(sql, deviceBuilder);
        return Long.parseLong(this.getSingleResult(sql.toString(), parameter).toString());
    }

    @Override
    public DeviceOutput findById(Long id) {
        Map<String, Object> parameter = new HashMap<>();
        StringBuilder sql = new StringBuilder();
        buildSql(sql);
        sql.append("AND d.id = :id ");
        parameter.put("id", id);
        return this.getSingleResult(sql.toString(), "findById", parameter);
    }

    private void buildSql(StringBuilder sql) {
        sql.append("SELECT ");
        sql.append("   d.id as device_id , ");
        sql.append("   d.\"name\" as device_name , ");
        sql.append("   d.code as device_code , ");
        sql.append("   d.qr_code as device_qr_code , ");
        sql.append("   d.status as device_status , ");
        sql.append("   d.usages as device_usages, ");
        sql.append("   d.status_transaction as device_status_transaction , ");
        sql.append("   plv.time_from_to as price_level_view_time_from_to , ");
        sql.append("   u.fullname as users_fullname , ");
        sql.append("   u.username as users_username , ");
        sql.append("   u.status as users_status , ");
        sql.append("   u.email as users_email , ");
        sql.append("   u.numberphone as users_numberphone , ");
        sql.append("   u.wallet as users_wallet ,");
        sql.append("   u.user_id_created as user_id_created,");
        sql.append("   u.focus_role user_focus_role ");
        sql.append("   FROM devices d ");
        sql.append("JOIN users u ON d.user_id = u.id ");
        sql.append("LEFT JOIN price_level_view plv ON d.id = plv.device_id ");
        sql.append("WHERE 1=1 ");
    }

    private void buildSqlWhereClause(StringBuilder sql, DeviceBuilder deviceBuilder) {
        if (StringUtils.isNotEmpty(deviceBuilder.getName())) {
            sql.append(" AND d.name LIKE :name ");
        }
        if (StringUtils.isNotEmpty(deviceBuilder.getCode())) {
            sql.append(" AND d.code LIKE :code ");
        }
        if (StringUtils.isNotEmpty(deviceBuilder.getQrCode())) {
            sql.append(" AND d.qr_code LIKE :qrcode ");
        }
        if (Objects.nonNull(deviceBuilder.getUsages())) {
            sql.append(" AND d.usages = :usages ");
        }
        if (StringUtils.isNotEmpty(deviceBuilder.getFullName())) {
            sql.append(" AND u.fullname LIKE :fullname ");
        }
        if (StringUtils.isNotEmpty(deviceBuilder.getUsername())) {
            sql.append(" AND u.username LIKE :username ");
        }
        if (StringUtils.isNotEmpty(deviceBuilder.getEmail())) {
            sql.append(" AND u.email LIKE :email ");
        }
        if (StringUtils.isNotEmpty(deviceBuilder.getNumberPhone())) {
            sql.append(" AND u.numberphone LIKE :numberphone ");
        }
        if (Objects.nonNull(deviceBuilder.getWallet())) {
            sql.append(" AND u.wallet = :wallet ");
        }
    }

}
