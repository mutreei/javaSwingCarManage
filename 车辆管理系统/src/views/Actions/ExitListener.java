package views.Actions;

import views.Login;
import views.MainTable;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ExitListener implements ActionListener {
    MainTable mainTable;
    public void setMainTable(MainTable mainTable) {
        this.mainTable = mainTable;
    }
    @Override
    public void actionPerformed(ActionEvent actionEvent) {
        mainTable.dispose();
        Login login = new Login();
        login.usernameContext.setText("");
        login.passwordContext.setText("");
    }
}
