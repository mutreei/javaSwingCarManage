package views.Actions;

import controller.CarDao;
import model.Cars;
import utils.DButil;
import utils.VarifyInput;
import views.AddCar;
import views.MainTable;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.SQLException;

/**
 * 用于提交添加汽车信息
 */
public class SubmitAddCarListener implements ActionListener {
    AddCar addCar;

    public void setAddCar(AddCar addCar) {
        this.addCar = addCar;
    }

    @Override
    public void actionPerformed(ActionEvent actionEvent) {
        DButil dButil = new DButil();
        try {
            Connection connection = dButil.getCon();
            String plate = addCar.plateInput.getText();
            String model = addCar.modelInput.getText();
            String owner = addCar.ownerInput.getText();
            String tel = addCar.telInput.getText();
            String color = addCar.colorInput.getText();
            plate = plate.replace("请输入车牌号","");
            if( VarifyInput.isEmpty(plate)){
                JOptionPane.showMessageDialog(addCar,"车牌号不能为空！","出错了",JOptionPane.ERROR_MESSAGE);
                return;
            }
            Cars car = new Cars(plate,model,owner,tel,color);
            new CarDao().addCar(connection,car);
            Object[] addRow = {plate,model,owner,tel,color};
            MainTable.tableModel.addRow(addRow);
            dButil.closeConnection(connection);
            addCar.dispose();
        } catch (ClassNotFoundException | SQLException e) {
            JOptionPane.showMessageDialog(addCar,"该汽车信息已存在，无法添加该信息","出错了",JOptionPane.ERROR_MESSAGE);
        }
    }
}
