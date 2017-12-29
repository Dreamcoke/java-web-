package dao;

import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;

import cn.itcast.jdbc.TxQueryRunner;
import model.User;

public class UserDao {
	private  QueryRunner qr=new TxQueryRunner();
	public  User find(String name)
    {
        try {
            String sql = "select * from login where name=?";
            return qr.query(sql, new BeanHandler<User>(User.class), name);
        }catch (Exception e)
        {
            throw new RuntimeException(e);
        }
    }
}
