package controller;

import model.Cars;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

/**
 * 汽车dao类
 */
public class CarDao {
    /**
     * 获取所有汽车信息
     * @param connection
     * @return
     * @throws SQLException
     */
    public List<Cars> getAllCars(Connection connection) throws SQLException {
        List<Cars> resCar = new ArrayList<Cars>();
        Cars currentCar = null;
        String sql = "select * from cars";
        Statement statement = connection.createStatement();
        ResultSet res = statement.executeQuery(sql);
        while(res.next()){
            String plate = res.getString("plate");
            String model = res.getString("model");
            String owner = res.getString("owner");
            String tel = res.getString("tel");
            String color = res.getString("color");
            currentCar = new Cars(plate,model,owner,tel,color);
            resCar.add(currentCar);
        }
        return resCar;
    }

    /**
     * 插入汽车数据，汽车入库
     * @param connection
     * @param cars
     * @throws SQLException
     */
    public void addCar(Connection connection, Cars cars) throws SQLException {
        String sql = "insert into cars values (\"plate\",\"model\",\"owner\",\"tel\",\"color\")";
        sql = sql.replace("plate",cars.getPlate());
        sql = sql.replace("model",cars.getModel());
        sql = sql.replace("owner",cars.getOwner());
        sql = sql.replace("tel",cars.getTel());
        sql = sql.replace("color",cars.getColor());
        System.out.println(sql);
        Statement statement = connection.createStatement();
        statement.executeUpdate(sql);
    }

    /**
     * 删除汽车数据
     * @param connection
     * @param plate
     * @return
     * @throws SQLException
     */
    public boolean subCar(Connection connection,String plate) throws SQLException {
        String sql = "delete from cars where plate = \"xxx\";";
        sql = sql.replace("xxx",plate);
        Statement statement = connection.createStatement();
        int row = statement.executeUpdate(sql);
        System.out.println(sql);
        if(row!=0){
            return true;
        }else {
            return false;
        }
    }

    /**
     * 查询汽车数据
     * @param connection
     * @param condition
     * @param type
     * @return
     * @throws SQLException
     */
    public List<Cars> findCar(Connection connection, String condition, int type) throws SQLException {
        String sql = null;
        List<Cars> resCar = new ArrayList<>();
        Cars currentCar = null;
        switch (type){
            case 1:
                sql = "select *from cars where plate like \"%condition%\"";
                break;
            case 2:
                sql = "select *from cars where model like \"%condition%\"";
                break;
            case 3:
                sql = "select *from cars where owner like \"%condition%\"";
                break;
            case 4:
                sql = "select *from cars where tel like \"%condition%\"";
                break;
            case 5:
                sql = "select *from cars where color like \"%condition%\"";
                break;
            default:
                return resCar;
        }
        sql = sql.replace("condition",condition);
        System.out.println(sql);
        Statement statement = connection.createStatement();
        ResultSet res = statement.executeQuery(sql);
        while(res.next()){
            String plate = res.getString("plate");
            String model = res.getString("model");
            String owner = res.getString("owner");
            String tel = res.getString("tel");
            String color = res.getString("color");
            currentCar = new Cars(plate,model,owner,tel,color);
            resCar.add(currentCar);
        }
        return resCar;
    }

    /**
     * 查询单条学生信息 用于修改
     * @param connection
     * @param p
     * @return
     * @throws SQLException
     */
    public Cars findModifyTarget(Connection connection, String p) throws SQLException {
        Cars TargetCar = null;
        String sql = "select *from cars where plate = \"???\";";
        sql = sql.replace("???",p);
        System.out.println(sql);
        Statement statement = connection.createStatement();
        ResultSet res = statement.executeQuery(sql);
        while(res.next()){
            String plate = res.getString("plate");
            String model = res.getString("model");
            String owner = res.getString("owner");
            String tel = res.getString("tel");
            String color = res.getString("color");
            TargetCar = new Cars(plate,model,owner,tel,color);
        }
        return TargetCar;
    }

    /**
     * 更新数据
     * @param connection
     * @param handledTarget
     * @return
     * @throws SQLException
     */
    public boolean modifyTarget(Connection connection, Cars handledTarget) throws SQLException {
        String sql = "update cars set model=\"@\",owner=\"#\",tel=\"$\",color=\"%\" where plate=\"^\";";
        sql = sql.replace("@",handledTarget.getModel());
        sql = sql.replace("#",handledTarget.getOwner());
        sql = sql.replace("$",handledTarget.getTel());
        sql = sql.replace("%",handledTarget.getColor());
        sql = sql.replace("^",handledTarget.getPlate());
        System.out.println(sql);
        Statement statement = connection.createStatement();
        //执行更新返回影响行数
        int res = statement.executeUpdate(sql);
        if(res>0){
            return true;
        }else {
            return false;
        }
    }

    public static void main(String[] args) throws SQLException, ClassNotFoundException {
//        Connection connection =new DButil().getCon();
//        List<Cars> cars = findCar(connection,"车",2);
//        for(int i=0; i<cars.size(); i++){
//            System.out.println(cars.get(i).getPlate());
//        }
        String sql = "update cars set model=\"@\",owner=\"#\",tel=\"$\",color=\"%\" where plate=\"^\";";
        sql = sql.replace("@","1");
        sql = sql.replace("#","####");
        sql = sql.replace("$","$$$$");
        System.out.println(sql);
    }
}
