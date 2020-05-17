package my.shinoasada;

// 创建一个窗口
// 输入你的学号、姓名，选择所在班级（班级以下拉列表的形式给出，共5个班，软件1--软件5）
// 完成信息填写后，点击按钮，弹出对话框，该提示框显示你的学号、姓名和班级，
// 以及确定按钮，点击确定按钮，对话框消失；关闭窗口

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class LoginDialog extends JFrame {
    private JButton cancelButton;
    private JButton loginButton;

    private JTextField studentIDInput;
    private JTextField studentNameInput;
    private JComboBox classSelectComboBox;

    private String[] classes = {"软件1", "软件2", "软件3", "软件4", "软件5"};

    private JLabel studentIDHintLabel;
    private JLabel studentNameHintLabel;
    private JLabel classSelectHintLabel;

    public LoginDialog() {
        cancelButton = new JButton();
        cancelButton.setText("Cancel");

        loginButton = new JButton();
        loginButton.setText("Login");

        JPanel buttonPanel = new JPanel();
        BoxLayout buttonLayout = new BoxLayout(buttonPanel, BoxLayout.X_AXIS);
        buttonPanel.setLayout(buttonLayout);
        buttonPanel.add(loginButton);
        buttonPanel.add(cancelButton);

        studentIDInput = new JTextField();
        studentIDHintLabel = new JLabel();
        studentIDHintLabel.setText("学号：");
        JPanel idInputPanel = new JPanel();
        BoxLayout idInputlayout = new BoxLayout(idInputPanel, BoxLayout.X_AXIS);
        idInputPanel.setLayout(idInputlayout);
        idInputPanel.add(studentIDHintLabel);
        idInputPanel.add(studentIDInput);

        studentNameInput = new JTextField();
        studentNameHintLabel = new JLabel();
        studentNameHintLabel.setText("姓名：");
        JPanel nameInputPanel = new JPanel();
        BoxLayout nameInputlayout = new BoxLayout(nameInputPanel, BoxLayout.X_AXIS);
        nameInputPanel.setLayout(nameInputlayout);
        nameInputPanel.add(studentNameHintLabel);
        nameInputPanel.add(studentNameInput);

        classSelectComboBox = new JComboBox(classes);
        classSelectHintLabel = new JLabel();
        classSelectHintLabel.setText("班级：");
        JPanel classSelectPanel = new JPanel();
        BoxLayout classSelectInputlayout = new BoxLayout(classSelectPanel, BoxLayout.X_AXIS);
        classSelectPanel.setLayout(classSelectInputlayout);
        classSelectPanel.add(classSelectHintLabel);
        classSelectPanel.add(classSelectComboBox);

        // Set listeners
        JFrame theFrame = this; // 不然this会被解释为ActionListener
        loginButton.addActionListener(
                new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        if (studentIDInput.getText().isEmpty() || studentNameInput.getText().isEmpty()) {
                            JOptionPane.showMessageDialog(
                                    theFrame,
                                    "请检查学号或姓名的输入是否正确",
                                    "学号或姓名输入错误",
                                    JOptionPane.WARNING_MESSAGE
                            );
                            return;
                        }
                        // What a dick!
                        JOptionPane.showMessageDialog(
                                theFrame,
                                studentIDInput.getText() + "\n" +
                                        studentNameInput.getText() + "\n" +
                                        classSelectComboBox.getSelectedItem(),
                                "登录信息确认",
                                JOptionPane.WARNING_MESSAGE
                        );
                    }
                }
        );

        cancelButton.addActionListener(
                new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        theFrame.dispose();
                    }
                }
        );

        this.getContentPane().setLayout(
                new BoxLayout(getContentPane(), BoxLayout.Y_AXIS)
        );
        this.add(idInputPanel);
        this.add(nameInputPanel);
        this.add(classSelectPanel);
        this.add(buttonPanel);
        this.setTitle("Java在线课程登录");
        this.setSize(300, 140);
        this.setResizable(false);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

    public static void main(String[] args) {
        LoginDialog dialog = new LoginDialog();
        dialog.setVisible(true);
    }
}