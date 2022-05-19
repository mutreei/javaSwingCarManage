package views.Actions;

import controller.CarDao;
import model.Cars;
import utils.DButil;
import utils.VarifyInput;
import views.QueryCar;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

/**
 * 汽车查询
 * 根据不同条件查询数据
 * 数据显示到表格中
 */
public class SearchCarListener implements ActionListener {
    QueryCar queryCar;

    public void setQueryCar(QueryCar queryCar) {
        this.queryCar = queryCar;
    }

    @Override
    public void actionPerformed(ActionEvent actionEvent) {
        //清除所有tablemodel中的数据
        queryCar.tableModel.setRowCount(0);
        DButil dButil = new DButil();
        try {
            Connection connection = dButil.getCon();
            String condition = queryCar.conditions.getText();
            int type = queryCar.comboBox.getSelectedIndex();
            if(type==0 || VarifyInput.isEmpty(condition)){
                JOptionPane.showMessageDialog(null,"没有使用条件！","警告",JOptionPane.WARNING_MESSAGE);
            }
            List<Cars> queryRes = new CarDao().findCar(connection,condition,type);
            Object TableContent[][] = new Object[queryRes.size()][5];
            for(int i=0; i<queryRes.size();i++){
                TableContent[i][0] = queryRes.get(i).getPlate();
                TableContent[i][1] = queryRes.get(i).getModel();
                TableContent[i][2] = queryRes.get(i).getOwner();
                TableContent[i][3] = queryRes.get(i).getTel();
                TableContent[i][4] = queryRes.get(i).getColor();
                queryCar.tableModel.addRow(TableContent[i]);
            }
            dButil.closeConnection(connection);
        } catch (ClassNotFoundException | SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
