package db;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;

import javax.sql.DataSource;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public class DbMain {

    public static void main(String[] args) throws SQLException {
        ApplicationContext ctx = new ClassPathXmlApplicationContext("db.xml");
        //DataSource ds = (DataSource) ctx.getBean("dataSource");
        JdbcTemplate jdbcTemplate = (JdbcTemplate) ctx.getBean("jdbcTemplate");

//        update(jdbcTemplate);
//        insert(jdbcTemplate);
        querySingleObject(jdbcTemplate);
        queryMultipleObject(jdbcTemplate);

    }
    public static void update(JdbcTemplate jdbcTemplate){
        String sql = "update user set name=?  where name=?";
        jdbcTemplate.update(sql, "baohan-haha", "baohan");
    }
    public static void insert(JdbcTemplate jdbcTemplate){
        String sql = "insert into user values(?,?,?)";
        jdbcTemplate.update(sql, "baohan03", 25,"789");
    }
    public static void querySingleObject(JdbcTemplate jdbcTemplate){
        String sql = "Select * from user WHERE age=18";
        RowMapper<User> rm = new BeanPropertyRowMapper(User.class);
        User user = jdbcTemplate.queryForObject(sql,rm);
        System.out.println(user);
    }
    public static void queryMultipleObject(JdbcTemplate jdbcTemplate){
        String sql = "Select * from user";
        RowMapper<User> rm = new BeanPropertyRowMapper(User.class);
        List<User> userList = jdbcTemplate.query(sql,rm);
        System.out.println(userList);
    }

}
