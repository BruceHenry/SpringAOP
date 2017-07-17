package db;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;

import javax.sql.DataSource;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class DbMain {
    public static void main(String[] args) throws SQLException {
        ApplicationContext ctx = new ClassPathXmlApplicationContext("db.xml");
        //DataSource ds = (DataSource) ctx.getBean("dataSource");
        JdbcTemplate jdbcTemplate = (JdbcTemplate) ctx.getBean("jdbcTemplate");
        NamedParameterJdbcTemplate namedParameterJdbcTemplate = (NamedParameterJdbcTemplate) ctx.getBean("namedParameterJdbcTemplate");


//        update(jdbcTemplate);
//        insert(jdbcTemplate);
//
//        batchUpdate(jdbcTemplate);
//
//        NamedParaJDBC(namedParameterJdbcTemplate);
//        NamedParaJDBC2(namedParameterJdbcTemplate);
//
//        querySingleObject(jdbcTemplate);
//        delete(jdbcTemplate);
        queryMultipleObject(jdbcTemplate);
    }

    //update single row
    public static void update(JdbcTemplate jdbcTemplate){
        String sql = "update user set name=?  where name=?";
        jdbcTemplate.update(sql, "baohan-haha", "baohan");
    }
    public static void delete(JdbcTemplate jdbcTemplate){
        String sql = "delete from user where name=?;";
        jdbcTemplate.update(sql,"baohan");
    }

    //update or insert multiple rows
    public static void batchUpdate(JdbcTemplate jdbcTemplate){
        String sql = "insert into user values(?,?,?)";
        List<Object[]> list = new ArrayList<>();
        list.add(new Object[]{"baohan06",36,"666"});
        list.add(new Object[]{"baohan07",37,"777"});
        jdbcTemplate.batchUpdate(sql,list);
    }

    //insert single row
    public static void insert(JdbcTemplate jdbcTemplate){
        String sql = "insert into user values(?,?,?)";
        jdbcTemplate.update(sql, "baohan03", 25,"789");
    }

    //query single object, return a object (result must be exactly one)
    public static void querySingleObject(JdbcTemplate jdbcTemplate){
        String sql = "Select * from user WHERE age=20";
        RowMapper<User> rm = new BeanPropertyRowMapper(User.class);
        User user = jdbcTemplate.queryForObject(sql,rm);
        System.out.println(user);
    }

    //query multiple object
    public static void queryMultipleObject(JdbcTemplate jdbcTemplate){
        String sql = "Select * from user";
        RowMapper<User> rm = new BeanPropertyRowMapper(User.class);
        List<User> userList = jdbcTemplate.query(sql,rm);
        System.out.println(userList);
    }

    //named parameter JDBC template using map
    public static void NamedParaJDBC(NamedParameterJdbcTemplate namedParameterJdbcTemplate){
        String sql = "INSERT INTO user values(:name,:age,:password)";
        Map<String,Object> map = new HashMap();
        map.put("name","baohan04");
        map.put("age",18);
        map.put("password","123456");
        namedParameterJdbcTemplate.update(sql,map);
    }

    //named parameter JDBC template using object
    public static void NamedParaJDBC2(NamedParameterJdbcTemplate namedParameterJdbcTemplate){
        String sql = "INSERT INTO user values(:name,:age,:password)";
        User user = new User("baohan05",19,"2424");
        SqlParameterSource sp = new BeanPropertySqlParameterSource(user);
        namedParameterJdbcTemplate.update(sql,sp);
    }
}
