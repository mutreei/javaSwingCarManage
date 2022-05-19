package views;

import controller.CarDao;
import model.Cars;
import utils.DButil;
import utils.VarifyInput;
import views.Actions.ModifyCarListener;

import javax.swing.*;
import java.awt.*;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.sql.Connection;
import java.sql.SQLException;

/**
 * 修改汽车信息的界面
 */
public class ModifyCar extends JFrame {
    JLabel title = new JLabel("修改汽车信息");
    JPanel top = new JPanel();
    JPanel plateBox = new JPanel();
    public JTextField plateInput = new JTextField(15);
    JButton searchTarget = new JButton("输入车牌查找");
    JPanel modelBox = new JPanel();
    JLabel modelLabel = new JLabel("车型:");
    public JTextField modelInput = new JTextField(20);
    JPanel ownerBox = new JPanel();;
    JLabel ownerLabel = new JLabel("车主:");
    public JTextField ownerInput = new JTextField(20);
    JPanel telBox = new JPanel();;
    JLabel telLabel = new JLabel("电话:");
    public JTextField telInput = new JTextField(20);
    JPanel colorBox = new JPanel();;
    JLabel colorLabel = new JLabel("颜色:");
    public JTextField colorInput = new JTextField(20);
    JPanel fun = new JPanel();
    JButton submitModify = new JButton("提交");
    JButton cancelModify = new JButton("取消");

    ModifyCarListener modifyCarListener;

    public ModifyCar() throws HeadlessException {
        setSize(300,300);
        setLocationRelativeTo(null);
        setLayout(new FlowLayout());
        setFont(new Font("微软雅黑",Font.PLAIN,20));
        setResizable(false);
        top.add(title);
        add(top);
        add(plateBox);
        plateBox.add(plateInput);
        plateBox.add(searchTarget);
        add(modelBox);
        modelBox.add(modelLabel);
        modelBox.add(modelInput);
        add(ownerBox);
        ownerBox.add(ownerLabel);
        ownerBox.add(ownerInput);
        add(telBox);
        telBox.add(telLabel);
        telBox.add(telInput);
        add(colorBox);
        colorBox.add(colorLabel);
        colorBox.add(colorInput);
        add(fun);
        fun.add(submitModify);
        fun.add(cancelModify);

        plateInput.addFocusListener(new FocusListener() {
            @Override
            public void focusGained(FocusEvent focusEvent) {
                if(plateInput.getText().equals("请输入车牌号")){
                    plateInput.setText("");
                }
            }
            @Override
            public void focusLost(FocusEvent focusEvent) {
                if(VarifyInput.isEmpty(plateInput.getText())){
                    plateInput.setText("请输入车牌号");
                }
            }
        });
        // 查询需要修改的信息
        searchTarget.addActionListener(actionEvent -> {

            DButil dButil = new DButil();
            try {
                Connection connection = dButil.getCon();
                String plate = plateInput.getText();
                Cars targetCar = new CarDao().findModifyTarget(connection,plate); //获取需要修改的汽车的信息
//                if(targetCar.)
                try{
                    modelInput.setText(targetCar.getModel());
                    ownerInput.setText(targetCar.getOwner());
                    telInput.setText(targetCar.getTel());
                    colorInput.setText(targetCar.getColor());
                    plateInput.setEditable(false);
                }catch (NullPointerException e){
                    JOptionPane.showMessageDialog(null,"请输入正确的车牌信息","警告",JOptionPane.WARNING_MESSAGE);
                }
                dButil.closeConnection(connection);
            } catch (ClassNotFoundException | SQLException e) {
                JOptionPane.showMessageDialog(this,"未找到改车牌的信息","警告",JOptionPane.ERROR_MESSAGE);
            }
        });

        //提交修改
        modifyCarListener = new ModifyCarListener();
        modifyCarListener.setModifyCar(this);
        submitModify.addActionListener(modifyCarListener);

        //放弃修改
        cancelModify.addActionListener(actionEvent -> dispose());
        setVisible(true);
    }

}
