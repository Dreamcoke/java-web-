package dao;

import cn.itcast.jdbc.TxQueryRunner;
import model.Customer;
import model.PageBean;

import org.apache.commons.collections.buffer.CircularFifoBuffer;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;
import org.apache.commons.dbutils.handlers.ScalarHandler;

import java.util.ArrayList;
import java.util.List;
import java.util.spi.CurrencyNameProvider;


public class CustomerDao
{
    private QueryRunner qr=new TxQueryRunner();

    public void add(Customer c)
    {
        try {
            String sql = "insert into information values(?,?,?,?,?,?)";
            Object[] params = {c.getId(), c.getName(), c.getGender(),
                    c.getPhone(), c.getEmail(), c.getDescription()};
            qr.update(sql, params);
        }catch (Exception e)
        {
            throw new RuntimeException(e);
        }
    }


    public PageBean<Customer> findAll(int pc, int pr)
    {
        try{
            /*
             *1.他需要创建pageBean对象pb
             * 2.设置pb的pc和pr
             * 3.得到tr，设置给pb
             * 4.得到beanList设置给pb
             * 最后返回给pb
             */
            PageBean<Customer> pb=new PageBean<>();
            pb.setPc(pc);
            pb.setPr(pr);

            String sql="select count(*) from information";//获取数据库总数据条数
           //qr.query()返回object类型 ，先转成 number类型 然后 在转为 int类型
            Number number=(Number) qr.query(sql,new ScalarHandler<>());
            int tr=number.intValue();
            pb.setTr(tr);

            sql="select * from information order by name limit ?,?";
            Object[] params={(pc-1)*pr,pr};//当前页码pc,每页纪录数pr
            List<Customer> beanList=qr.query(sql,new BeanListHandler<>(Customer.class),params);

            pb.setBeanList(beanList);

            return pb;
        }catch (Exception e)
        {
            throw new RuntimeException(e);
        }
    }

    public Customer find(String id)
    {
        try {
            String sql = "select * from information where id=?";
            return qr.query(sql, new BeanHandler<Customer>(Customer.class), id);
        }catch (Exception e)
        {
            throw new RuntimeException(e);
        }
    }

    public void edit(Customer customer)
    {
        try{
            String sql="update information set name=?,gender=?,phone=?,email=?,description=? where id=?";
            Object[] params={customer.getName(),customer.getGender(),customer.getPhone(),customer.getEmail(),customer.getDescription(),customer.getId()};

            qr.update(sql,params);
        }catch (Exception e)
        {
            throw new RuntimeException(e);
        }
    }

    public void delete(String id)
    {
        try {
            String sql = "delete from information where id=?";

            qr.update(sql, id);
        }catch (Exception e)
        {
            throw new RuntimeException(e);
        }
    }



    public PageBean<Customer> query(Customer customer,int pc,int pr) {


        try {
            PageBean<Customer> pb=new PageBean<>();
            pb.setPc(pc);
            pb.setPr(pr);

            StringBuilder cntSql = new StringBuilder("select count(*) from information ");
            StringBuilder whereSql=new StringBuilder(" where 1=1 ");
            List<Object> params = new ArrayList<>();

            String name = customer.getName();
            if (name != null && !name.trim().isEmpty()) {
                whereSql.append("and name like ?");
                params.add("%"+name+"%");
            }

            String gender = customer.getGender();
            if (gender != null && !gender.trim().isEmpty()) {
                whereSql.append("and gender=?");
                params.add(gender);
            }

            String phone = customer.getPhone();
            if (phone != null && !phone.trim().isEmpty()) {
                whereSql.append("and phone like ?");
                params.add("%"+phone+"%");
            }

            String email = customer.getEmail();
            if (email != null && !email.trim().isEmpty()) {
                whereSql.append("and email like ?");
                params.add("%"+email+"%");
            }

            Number num=qr.query(cntSql.append(whereSql).toString(),new ScalarHandler<>(),params.toArray());

            int tr=num.intValue();
            pb.setTr(tr);

            StringBuilder sql=new StringBuilder("select * from information ");
            StringBuilder lmitSql=new StringBuilder(" limit ?,?");

            params.add((pc-1)*pr);
            params.add(pr);

            List<Customer> beanList=qr.query(sql.append(whereSql).append(lmitSql).toString(),new BeanListHandler<Customer>(Customer.class),params.toArray());
            pb.setBeanList(beanList);

            return pb;
        }catch (Exception e)
        {
            throw new RuntimeException(e);
        }

    }
}
