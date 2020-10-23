package HTMLValidator;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Frame {
    JFrame frame = new JFrame("HTML Validator");
    JButton submitBtn = new JButton("Submit");
    JLabel firstLabel = new JLabel("Enter Your HTML Code Here:");
    JTextArea textArea = new JTextArea();
    JOptionPane jOptionPane = new JOptionPane();
    ActionListener submitBtnActionListener = new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent e) {
            String str = textArea.getText();
            System.out.println(str);
            HTMLValidator validator = new HTMLValidator(str);
            boolean isvalid = validator.isValid();
            String out = "";
            if (isvalid)
                out += "HTML Script is Valid";
            else
                out += "HTML Script is not Valid";
            jOptionPane.showMessageDialog(frame, out);

        }
    };

    Frame() {

        frame.setSize(500, 500);
        submitBtn.setBounds(200, 400, 100, 50);
        submitBtn.addActionListener(submitBtnActionListener);
        firstLabel.setBounds(10, 10, 200, 20);
        textArea.setBounds(25, 30, 450, 300);

        frame.add(firstLabel);
        frame.add(submitBtn);
        frame.add(textArea);

        frame.setLayout(null);
        frame.setVisible(true);
    }

}
