package com.wz.druid;

import com.alibaba.druid.sql.SQLUtils;
import com.alibaba.druid.sql.ast.SQLStatement;
import com.alibaba.druid.sql.dialect.mysql.visitor.MySqlSchemaStatVisitor;
import com.alibaba.druid.util.JdbcConstants;

import java.sql.Statement;
import java.util.List;

public class DruidDemo {
    public static void main(String[] args) {
        String sql = "select name, age, sex from t_user";
        String dbType = JdbcConstants.MYSQL;
        String result = SQLUtils.format(sql, dbType);
        System.out.println(result);
        List<SQLStatement> list = SQLUtils.parseStatements(sql, dbType);
        for (int i = 0; i < list.size(); i++) {
            SQLStatement statement = list.get(i);
            MySqlSchemaStatVisitor visitor = new MySqlSchemaStatVisitor();
            statement.accept(visitor);
            System.out.println("表--->"+visitor.getTables());
            System.out.println("字段--->"+visitor.getColumns());
        }
    }
}
