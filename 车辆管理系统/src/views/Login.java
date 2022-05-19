package views;

import controller.UserDao;
import model.User;
import utils.DButil;
import utils.VarifyInput;

import javax.swing.*;
import java.awt.*;
import java.sql.Connection;
import java.sql.SQLException;

public class Login extends JFrame{
    //组件
    JButton loginbutton = new JButton("登录");
    JButton restorebuton =new JButton("重置");
    public JTextField usernameContext = new JTextField(20);
    public JPasswordField passwordContext = new JPasswordField(20);
    JLabel labelTitle = new JLabel("车辆管理系统");
    JLabel labelUsername = new JLabel("username: ");
    JLabel labelPassword = new JLabel("password: ");

    //登录背景图片
    ImageIcon bgcImg = new ImageIcon("asset/login.png");
    //布局容器
    JPanel top = new JPanel();
    JPanel left = new JPanel();
    JPanel center = new JPanel();
    JPanel right = new JPanel();
    JPanel bottom = new JPanel();

    JPanel usernameInput = new JPanel();
    JPanel passwordInput = new JPanel();

    //用户登录验证类，数据库连接类
    UserDao userDao = new UserDao();
    DButil dButil = new DButil();


    public Login() throws HeadlessException {
        setTitle("登录");
        setBounds(300,200,400,300);
        setVisible(true);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setResizable(false);

        //创建并生成登录容器
        JPanel loginPanel = new JPanel();
        loginPanel.setLayout(new BorderLayout());
        add(loginPanel);

        //添加布局容器
        loginPanel.add(top, BorderLayout.NORTH);
        loginPanel.add(right,BorderLayout.WEST);
        loginPanel.add(center,BorderLayout.CENTER);
        loginPanel.add(left,BorderLayout.EAST);
        loginPanel.add(bottom,BorderLayout.SOUTH);

        // top容器
        top.add(labelTitle);
        labelTitle.setFont(new Font("楷体",Font.BOLD,20));
        top.setLayout(new FlowLayout(FlowLayout.CENTER,0,20));
        //right容器

        //center容器
        center.add(usernameInput);
        center.add(passwordInput);
        usernameInput.add(labelUsername);
        passwordInput.add(labelPassword);
        usernameInput.add(usernameContext);
        passwordInput.add(passwordContext);
        //right容器

        //bottom容器
        bottom.add(loginbutton);
        bottom.add(restorebuton);
        bottom.setLayout(new FlowLayout(FlowLayout.CENTER,10,40));


        //重置功能
        restorebuton.addActionListener(actionEvent -> {
            usernameContext.setText("");
            passwordContext.setText("");
            JOptionPane.showMessageDialog(null,"已重置");
        });


        //登录功能
        loginbutton.addActionListener(actionEvent -> {
            String username = usernameContext.getText();
            String password = new String(passwordContext.getPassword());
            if(VarifyInput.isEmpty(username)||VarifyInput.isEmpty(password)){
                JOptionPane.showMessageDialog(null,"用户名或密码为空!!","error",JOptionPane.ERROR_MESSAGE);
                return;
            }
            try {
                //获取数据库连接
                Connection conn = dButil.getCon();
                User user = new User(username,password);
                User currentUser = userDao.login(conn,user);
                if(currentUser==null){
                    JOptionPane.showMessageDialog(null,"用户名或密码错误","警告",JOptionPane.WARNING_MESSAGE);
                    return;
                }
                dispose();
                new MainTable();
            } catch (ClassNotFoundException | SQLException e) {
                throw new RuntimeException(e);
            }
        });
    }
}
