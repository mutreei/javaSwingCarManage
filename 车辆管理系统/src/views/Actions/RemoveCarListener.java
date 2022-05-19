package views.Actions;

import controller.CarDao;
import utils.DButil;
import views.MainTable;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.SQLException;

public class RemoveCarListener implements ActionListener {
    MainTable mainTable;
    public void setMainTable(MainTable mainTable) {
        this.mainTable = mainTable;
    }
    @Override
    public void actionPerformed(ActionEvent actionEvent) {
        //重新绘制窗口
        try {
            try {
                Connection connection = new DButil().getCon();
                //根据行数删除数据
                int count = mainTable.table.getSelectedRow();
                String plate =mainTable.table.getValueAt(count,0).toString();
                //表格模型删除对应列
                mainTable.tableModel.removeRow(count);
                boolean SUCCESS = new CarDao().subCar(connection,plate);
                if(SUCCESS){
                    JOptionPane.showMessageDialog(null,"删除成功");
                }else {
                    JOptionPane.showMessageDialog(null,"删除失败","出现异常",JOptionPane.ERROR_MESSAGE);
                }
            }catch (ArrayIndexOutOfBoundsException e){
                //没有选中车辆则抛出异常
                JOptionPane.showMessageDialog(null,"没有选中车辆！","警告",JOptionPane.WARNING_MESSAGE);
            }
        } catch (ClassNotFoundException | SQLException e) {
            JOptionPane.showMessageDialog(null,"没有选中车辆！","",JOptionPane.WARNING_MESSAGE);
        }
    }
}
