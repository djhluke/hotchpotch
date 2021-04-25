package com.lxq.hotchpotch.common.tool.util;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.sql.*;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * 数据库连接操作工具类
 *
 * @author luxinqiang
 */
public class JdbcUtils {

    // 默认批量跟新数量
    public static final int BATCH_COUNT = 100;
    // 查询db2中所有的表及其对应的schema
    // CREATOR----schema模式名称
    // NAME ----表名称
    public static final String QUERY_SCHEMA_TABLE = "SELECT CREATOR,NAME FROM SYSIBM.SYSTABLES WHERE TYPE = 'T'";
    // 查询db2中所有表对应的schema
    public static final String QUERY_SCHEMA = "SELECT CREATOR FROM SYSIBM.SYSTABLES WHERE TYPE = 'T' GROUP BY CREATOR";
    // 查询表
    public static final String QUERY_TABLES = "SELECT NAME,TBSPACE FROM SYSIBM.SYSTABLES WHERE TYPE = 'T' AND CREATOR=?";
    // 查询表对应的表空间名称
    public static final String QUERY_TABLE_TBSPACE = "SELECT TBSPACE FROM SYSIBM.SYSTABLES WHERE TYPE = 'T' AND CREATOR=? AND NAME=?";
    // YGXD----REPORT_PROFESSION
    // 查询表约束
    public static final String QUERY_TABLE_CONSTRAINT = "SELECT NAME,COLNAMES,UNIQUERULE FROM \"SYSIBM\".SYSINDEXES WHERE CREATOR =? AND TBNAME =? AND UNIQUERULE IN('U','P')";
    // 查询索引
    // NAME----索引名称
    // COLNAMES----索引字段
    public static final String QUERY_TABLE_INDEX = "SELECT I.NAME,I.COLNAMES FROM \"SYSIBM\".SYSINDEXES I WHERE CREATOR = ? AND TBNAME=?";
    // 查询表的字段信息
    // NAME----字段英文名称
    // REMARKS----字段注解
    // COLTYPE---字段类型
    // NULLS---是否为空
    // LENGTH---字段长度
    public static final String QUERY_TABLE_INFO = "SELECT C.NAME,C.REMARKS,C.COLTYPE,C.NULLS,C.LENGTH FROM SYSIBM.SYSCOLUMNS C WHERE TBCREATOR = ? and TBNAME =?";
    // 查询字段的详细信息
    // NAME----字段英文名称
    // REMARKS----字段注解
    // TYPENAME----字段类型
    // LENGTH----字段长度
    // NULLS----字段是否能为空
    public static final String QUERY_FIELD_INFO = "SELECT C.NAME,C.REMARKS,C.TYPENAME,C.\"LENGTH\",C.\"NULLS\" FROM \"SYSIBM\".SYSCOLUMNS C WHERE TBCREATOR = ? and TBNAME =? and NAME =?";
    // 查询表下面的主键
    // COLNAME----主键名称
    public static final String QUERY_TABLE_KEY = "SELECT K.COLNAME FROM \"SYSIBM\".SYSKEYCOLUSE k WHERE TBCREATOR=? AND TBNAME = ?";
    public static final String QUERY_TABLE_KEY_INDEXS = "SELECT SC.\"IDENTITY\" ,SC.REMARKS,s.*,si.COLNAMES FROM SYSCAT.INDEXES si LEFT JOIN \r\n"
            + "(SELECT TBNAME,TBCREATOR,listagg(COLNAME,',') AS primaryKeys FROM  SYSIBM.SYSKEYCOLUSE sk\r\n"
            + "GROUP BY CONSTNAME,TBNAME,TBCREATOR\r\n" + ") s\r\n"
            + "ON si.TABSCHEMA = s.TBCREATOR AND si.TABNAME = s.TBNAME\r\n" + "LEFT JOIN SYSIBM.SYSCOLUMNS SC \r\n"
            + "ON SC.TBCREATOR = si.TABSCHEMA AND SC.TBNAME = si.TABNAME\r\n"
            + "WHERE si.TABSCHEMA =? AND si.TABNAME =? AND si.UNIQUERULE IN ('U','P')";
    // 根据模式名、表名、字段名查找注释和是否自增
    public static final String QUERY_IS_IDENTITY_REMARKS = "SELECT C.NAME,C.\"IDENTITY\",C.REMARKS FROM SYSIBM.SYSCOLUMNS C WHERE C.TBCREATOR =? AND C.TBNAME =? AND C.NAME =?";
    private static final SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss"); // 格式化日期

    // 单列模式 -- 懒汉模式（双重锁定）保证线程安全
    public static JdbcUtils db = null;

    // 1、Postgresql
    private static final String postgresql_driver;
    private static final String postgresql_url;
    private static final String postgresql_user;
    private static final String postgresql_password;
    // 2、Greenplum
    private static final String greenplum_driver;
    private static final String greenplum_url;
    private static final String greenplum_user;
    private static final String greenplum_password;

    private static String user = null;
    private static String password = null;
    private static String driver = null;
    private static String url = null;
    private static Connection conn = null;
    private static Properties p = null;

    // 读取配置文件且加载数据库驱动
    static {
        // 实例化一个properties对象用来解析配置文件
        p = new Properties();
        // 通过类加载器来读取配置文件，以字节流的形式进行读取
        // InputStream inputStream = JdbcUtils.class.getClassLoader().getResourceAsStream("config.properties");
        BufferedInputStream inputStream = null;
        try {
            String proFilePath = System.getProperty("user.dir") + File.separator + "config" + File.separator + "config.properties";
            System.out.println(proFilePath);
            inputStream = new BufferedInputStream(new FileInputStream(proFilePath));
            p.load(inputStream);
            // 读取配置文件
            driver = p.getProperty("driver-class-name");
            url = p.getProperty("url");
            user = p.getProperty("username");
            password = p.getProperty("password");
            // 加载驱动
            Class.forName(driver);
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        } finally {
            try {
                if (inputStream != null) {
                    inputStream.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    // 1、Postgresql
    static {
        postgresql_driver = ResourceBundle.getBundle("db").getString("postgresql_driver");
        postgresql_url = ResourceBundle.getBundle("db").getString("postgresql_url");
        postgresql_user = ResourceBundle.getBundle("db").getString("postgresql_user");
        postgresql_password = ResourceBundle.getBundle("db").getString("postgresql_password");
    }

    // 2、Greenplum
    static {
        greenplum_driver = ResourceBundle.getBundle("db").getString("greenplum_driver");
        greenplum_url = ResourceBundle.getBundle("db").getString("greenplum_url");
        greenplum_user = ResourceBundle.getBundle("db").getString("greenplum_user");
        greenplum_password = ResourceBundle.getBundle("db").getString("greenplum_password");
    }

    private JdbcUtils() {
    }

    public static JdbcUtils getInstance() {
        if (db == null) {
            synchronized (JdbcUtils.class) {
                if (db == null) {
                    db = new JdbcUtils();
                }
            }
        }
        return db;
    }

    // 建立数据库连接
    public static Connection getConn() {
        try {
            return DriverManager.getConnection(url, user, password);
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }

    // 关闭所有资源
    public static void close(ResultSet rs, PreparedStatement pst, Connection conn) {
        if (rs != null) {
            try {
                rs.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

        if (pst != null) {
            try {
                pst.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

        if (conn != null) {
            try {
                conn.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    // 关闭连接资源
    public static void close(Connection conn) {
        if (conn != null) {
            try {
                conn.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    // 数据集分类的编码
    @SuppressWarnings("null")
    public static String getCustoerCode() {
        String custoerCode = "";
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        String sql = "  SELECT MAX(MART_TYPE_CODE) FROM YGXDRISK.TB_MART_TYPE ";
        try {
            ps = conn.prepareStatement(sql);
            rs = ps.executeQuery();
            while (rs.next()) { // 当前记录指针移动到下一条记录上
                custoerCode = rs.getString(1);// 得到当前记录的第一个字段的值
                if ("".equals(custoerCode) || null == custoerCode) {
                    custoerCode = "S1000000";
                } else {
                    String a = custoerCode.substring(1, 8);
                    int b = Integer.valueOf(a);
                    int c = b + 1;
                    String d = String.valueOf(c);
                    custoerCode = "S" + d;
                }

            }
        } catch (SQLException e) {
            e.printStackTrace();

        } finally {
            try {
                if (rs != null) {
                    rs.close();
                }
                if (ps != null) {
                    ps.close();
                }
                if (conn != null) {
                    conn.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

        return custoerCode;

    }

    public List<Map<String, Object>> query(String sql, List<Object> params) {
        PreparedStatement pst = null;
        ResultSet rs = null;
        try {
            // 获得连接
            conn = getConn();
            pst = conn.prepareStatement(sql);
            if (null != params) {
                int paramsIndex = 1;
                for (Object object : params) {
                    pst.setObject(paramsIndex++, object);
                }
            }
            // 执行sql语句获取结果集对象
            rs = pst.executeQuery();
            // 获取结果集中的列的信息
            ResultSetMetaData rst = rs.getMetaData();
            // 获取结果集的列的数量
            int colum = rst.getColumnCount();
            // 创建list容器
            List<Map<String, Object>> rstList = new ArrayList<>();
            // 处理结果
            while (rs.next()) {
                Map<String, Object> m = new HashMap<>(16);
                for (int i = 1; i <= colum; i++) {
                    m.put(rst.getColumnName(i), rs.getObject(i));
                }
                rstList.add(m);
            }
            return rstList;
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        } finally {
            close(rs, pst, conn);
        }
    }

    // 1、Postgresql
    public static Connection getPostgresqlConnection() throws ClassNotFoundException, SQLException {
        // 加载数据库驱动
        Class.forName(postgresql_driver);
        // System.out.println("测试加载数据库成功");
        Connection con = DriverManager.getConnection(postgresql_url, postgresql_user, postgresql_password);
        // System.out.println("测试数据库链接成功");
        return con;
    }

    // 2、Greenplum
    public static Connection getGreenplumConnection() throws ClassNotFoundException, SQLException {
        // 加载数据库驱动
        Class.forName(greenplum_driver);
        // System.out.println("测试加载数据库成功");
        Connection con = DriverManager.getConnection(greenplum_url, greenplum_user, greenplum_password);
        // System.out.println("测试数据库链接成功");
        return con;
    }

    // 查询表中字段详细信息
    public List<Map<String, Object>> queryFieldsByTable(String schema, String table) {
        List<Object> params = new ArrayList<>();
        params.add(schema);
        params.add(table);
        List<Map<String, Object>> query = query(JdbcUtils.QUERY_TABLE_INFO, params);
        return query;
    }

    // 根据表信息查询表空间
    public String queryTbspaceByTable(String schema, String table) {
        List<Object> params = new ArrayList<>();
        params.add(schema);
        params.add(table);
        List<Map<String, Object>> query = query(JdbcUtils.QUERY_TABLE_TBSPACE, params);
        return query.size() == 0 ? "" : query.get(0).get("TBSPACE").toString();
    }

    public static void main(String[] args) {
        try {
            JdbcUtils.getPostgresqlConnection();
            System.out.println("连接成功......");
            System.out.println("=======================================");
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }

        // query_schema_table
        // query_table_constraint
        JdbcUtils instance = JdbcUtils.getInstance();

        List<List<Object>> list = new ArrayList<>();

        // instance.batchInsert(conn, "", list);

        List<Object> listMap = new ArrayList<>();
        // YGXD----REPORT_PROFESSION
        listMap.add("YGXD");
        listMap.add("INDEX_BASE");
        listMap.add("EN_INDEX");
        List<Map<String, Object>> query = instance.query(QUERY_IS_IDENTITY_REMARKS, listMap);
        List<Map<String, Object>> query2 = instance.query(QUERY_TABLE_CONSTRAINT, listMap);
        List<Map<String, Object>> query3 = instance.query(QUERY_TABLE_KEY, listMap);
        System.out.println(query);
    }

}
