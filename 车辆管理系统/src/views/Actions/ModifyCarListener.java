package views.Actions;

import com.sun.tools.javac.Main;
import controller.CarDao;
import model.Cars;
import utils.DButil;
import utils.VarifyInput;
import views.MainTable;
import views.ModifyCar;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.SQLException;

/**
 * 提交修改汽车信息的事件
 */
public class ModifyCarListener implements ActionListener {
    ModifyCar modifyCar;

    public void setModifyCar(ModifyCar modifyCar) {
        this.modifyCar = modifyCar;
    }

    @Override
    public void actionPerformed(ActionEvent actionEvent) {
        DButil dButil = new DButil();
        try {
            Connection connection = dButil.getCon();
            //获取输入框数据发起请求更改数据
            String plate = modifyCar.plateInput.getText();
            String handledModel = modifyCar.modelInput.getText();
            String handledOwner = modifyCar.ownerInput.getText();
            String handledtel = modifyCar.telInput.getText();
            String handledColor = modifyCar.colorInput.getText();
            plate = plate.replace("请输入车牌号","");
            if(VarifyInput.isEmpty(plate)){
                JOptionPane.showMessageDialog(null,"未输入正确车牌号信息","警告",JOptionPane.WARNING_MESSAGE);
                return;
            }
            System.out.println(plate+"\n"+handledModel+"\n"+handledOwner+"\n"+handledtel+"\n"+handledColor);
            Cars handledTarget = new Cars(plate,handledModel,handledOwner,handledtel,handledColor);
            boolean SUCCESS =  new CarDao().modifyTarget(connection,handledTarget);
            if(SUCCESS){
                JOptionPane.showMessageDialog(null,"修改成功");
                modifyCar.dispose();
            }
            //修改视图
            //获取操作表格的行号
            int rowCount = 0;
            for(int i = 0; i< MainTable.tableModel.getRowCount(); i++){
                if(MainTable.tableModel.getValueAt(i,0)==plate){
                    return;
                }
            }
            MainTable.tableModel.setValueAt(handledModel,rowCount,1);
            MainTable.tableModel.setValueAt(handledOwner,rowCount,2);
            MainTable.tableModel.setValueAt(handledtel,rowCount,3);
            MainTable.tableModel.setValueAt(handledColor,rowCount,4);
        } catch (ClassNotFoundException | SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
