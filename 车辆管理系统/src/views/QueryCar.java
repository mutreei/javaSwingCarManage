package views;

import views.Actions.SearchCarListener;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;

public class QueryCar extends JFrame{
    JPanel panel = new JPanel();//面板
    JLabel label = new JLabel("搜索条件：");
    public JComboBox comboBox = new JComboBox();//创建下拉列表
    public JTextField conditions = new JTextField(30); //条件字段
    JButton searchBtn = new JButton("查询");
    JTable table = new JTable();   //显示查询结果
    public DefaultTableModel tableModel; //查询结果表模型
    JPanel tablePanel = new JPanel();

    public QueryCar(String title) {
        setTitle(title);
        setSize(500,300);
        setLocationRelativeTo(null);
        setResizable(false);
        add(panel);
        panel.add(label);
        comboBox.addItem("--请选择查询条件--");
        comboBox.addItem("车牌");
        comboBox.addItem("车型");
        comboBox.addItem("车主");
        comboBox.addItem("电话");
        comboBox.addItem("颜色");
        panel.add(comboBox);
        panel.add(conditions);
        panel.add(searchBtn);
        panel.add(tablePanel);

        //点击查询将结果渲染到表格
        SearchCarListener searchCarListener = new SearchCarListener();
        searchCarListener.setQueryCar(this);
        searchBtn.addActionListener(searchCarListener);

        tableModel = new DefaultTableModel();
        tableModel.addColumn("plate");
        tableModel.addColumn("model");
        tableModel.addColumn("owner");
        tableModel.addColumn("tel");
        tableModel.addColumn("color");
        table.setModel(tableModel);
        JScrollPane jsp = new JScrollPane(table);
        tablePanel.add(jsp);
        setVisible(true);
    }
}
