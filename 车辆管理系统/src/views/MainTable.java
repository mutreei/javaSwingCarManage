package views;

import controller.CarDao;
import model.Cars;
import utils.DButil;
import views.Actions.ExitListener;
import views.Actions.RemoveCarListener;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

/**
 * 显示主界面
 * 主界面组件：
 * 1. 按钮 汽车入库 显示新界面
 * 2. 按钮 汽车出库 直接请求删除该行
 * 3. 按钮 汽车查询 显示新界面
 * 4. 按钮 改写信息 显示新界面
 * 5. 按钮 刷新 重新请求数据repain表格
 * 6. 表格 显示数据库中所有数据
 *
 * 布局：BorderLayout
 * top 按钮
 * center 表格
 */
public class MainTable extends JFrame {
    JButton addCar; //汽车入库
    JButton subCar; //汽车出库
    JButton queryCar; //查询汽车
    JButton modifyCar; //修改汽车
    JButton exit; //退出功能
    Object[][] TableContent;
    Object[]  TableColumn;
    // 显示所有信息的表格
    public JTable table;
    //panel
    JPanel top;
    JPanel center;
    RemoveCarListener removeCarListener;
    ExitListener exitListener;
    public static DefaultTableModel tableModel; //创建表格模型

    public MainTable() throws HeadlessException, SQLException, ClassNotFoundException {
        addCar = new JButton("汽车入库");
        subCar = new JButton("汽车出库");
        queryCar = new JButton("汽车查询");
        modifyCar = new JButton("修改信息");
        exit = new JButton("退出");
        top = new JPanel();
        center = new JPanel();

        setSize(1000,600);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout());
        setBackground(Color.white);
        setResizable(false);
        add(top,BorderLayout.NORTH);
        top.setPreferredSize(new Dimension(1000,60));
        top.setBackground(Color.gray);
        top.add(addCar);
        top.add(subCar);
        top.add(queryCar);
        top.add(modifyCar);
        top.add(exit);

        //删除汽车信息
        removeCarListener = new RemoveCarListener();
        removeCarListener.setMainTable(this);
        subCar.addActionListener(removeCarListener);

        //刷新汽车信息
        exitListener = new ExitListener();
        exitListener.setMainTable(this);
        exit.addActionListener(exitListener);

        //添加汽车信息
        addCar.addActionListener(actionEvent -> new AddCar());

        //查询汽车信息
        queryCar.addActionListener(actionEvent -> new QueryCar("查询汽车"));

        //修改汽车信息
        modifyCar.addActionListener(actionEvent -> new ModifyCar());
//        addCar.addActionListener();
        //请求所有汽车数据
        DButil dButil = new DButil();
        Connection connection = dButil.getCon();
        List<Cars> allCars =  new CarDao().getAllCars(connection);
        dButil.closeConnection(connection);
        TableContent = new Object[allCars.size()][5];
        TableColumn = new Object[]{"车牌", "车型", "车主", "电话", "颜色"};
        for(int i=0; i<allCars.size(); i++){
            TableContent[i][0] = allCars.get(i).getPlate();
            TableContent[i][1] = allCars.get(i).getModel();
            TableContent[i][2] = allCars.get(i).getOwner();
            TableContent[i][3] = allCars.get(i).getTel();
            TableContent[i][4] = allCars.get(i).getColor();
        }
        add(center,BorderLayout.CENTER);
        table = new JTable(TableContent,TableColumn);
        table.setRowHeight(30);
        //设置表格模型
        tableModel = new DefaultTableModel(TableContent,TableColumn);
        table.setModel(tableModel);
        JScrollPane jsp = new JScrollPane(table);
        center.add(jsp);

        setVisible(true);
    }
}
