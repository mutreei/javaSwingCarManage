package views;

import utils.VarifyInput;
import views.Actions.SubmitAddCarListener;

import javax.swing.*;
import java.awt.*;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;

public class AddCar extends JFrame {
    JFrame addCar;
    public JTextField plateInput = new JTextField(20);
    public JTextField modelInput = new JTextField(20);
    public JTextField ownerInput = new JTextField(20);
    public JTextField telInput = new JTextField(20);
    public JTextField colorInput = new JTextField(20);
    JPanel panel = new JPanel();
    JPanel mainpan = new JPanel();
    JButton addBtn = new JButton("入库");
    JButton cancelBtn = new JButton("取消");
    JPanel bottompan = new JPanel();
    JPanel top = new JPanel();
    JLabel title = new JLabel("汽车入库");
    SubmitAddCarListener submitAddCarListener;

    public AddCar() throws HeadlessException {
        addCar = new JFrame("添加汽车");
        setSize(500,400);
        setLocationRelativeTo(null);
        setResizable(false);

        add(panel);
        panel.setLayout(new BorderLayout());
        panel.add(top,BorderLayout.NORTH);
        top.setLayout(new FlowLayout(FlowLayout.CENTER,30,20));
        top.add(title);
        title.setFont(new Font("楷体",Font.BOLD,20));
        panel.add(mainpan,BorderLayout.CENTER);
        mainpan.add(plateInput);
        plateInput.setText("请输入车牌号");
        mainpan.add(ownerInput);
        ownerInput.setText("请输入车主名");
        mainpan.add(modelInput);
        modelInput.setText("请输入车型");
        mainpan.add(telInput);
        telInput.setText("请输入车主电话");
        mainpan.add(colorInput);
        colorInput.setText("请输入车辆颜色");
        mainpan.setLayout(new FlowLayout(FlowLayout.CENTER,60,30));
        panel.add(bottompan,BorderLayout.SOUTH);
        bottompan.add(addBtn);
        bottompan.add(cancelBtn);
        setVisible(true);

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
        ownerInput.addFocusListener(new FocusListener() {
            @Override
            public void focusGained(FocusEvent focusEvent) {
                if(ownerInput.getText().equals("请输入车主名")){
                    ownerInput.setText("");
                }
            }
            @Override
            public void focusLost(FocusEvent focusEvent) {
                if(VarifyInput.isEmpty(ownerInput.getText())){
                    ownerInput.setText("请输入车主名");
                }
            }
        });
        modelInput.addFocusListener(new FocusListener() {
            @Override
            public void focusGained(FocusEvent focusEvent) {
                if(modelInput.getText().equals("请输入车型")){
                    modelInput.setText("");
                }
            }
            @Override
            public void focusLost(FocusEvent focusEvent) {
                if(VarifyInput.isEmpty(modelInput.getText())){
                    modelInput.setText("请输入车型");
                }
            }
        });
        telInput.addFocusListener(new FocusListener() {
            @Override
            public void focusGained(FocusEvent focusEvent) {
                if(telInput.getText().equals("请输入车主电话")){
                    telInput.setText("");
                }
            }
            @Override
            public void focusLost(FocusEvent focusEvent) {
                if(VarifyInput.isEmpty(telInput.getText())){
                    telInput.setText("请输入车主电话");
                }
            }
        });
        colorInput.addFocusListener(new FocusListener() {
            @Override
            public void focusGained(FocusEvent focusEvent) {
                if(colorInput.getText().equals("请输入车辆颜色")){
                    colorInput.setText("");
                }
            }
            @Override
            public void focusLost(FocusEvent focusEvent) {
                if(VarifyInput.isEmpty(colorInput.getText())){
                    colorInput.setText("请输入车辆颜色");
                }
            }
        });
        cancelBtn.addActionListener(actionEvent -> dispose());
        submitAddCarListener = new SubmitAddCarListener();
        submitAddCarListener.setAddCar(this);
        addBtn.addActionListener(submitAddCarListener);
    }
}
