package theFourthSubject;

import javax.swing.*;
import java.awt.*;

public class TextPane extends JPanel {

	private static final long serialVersionUID = 1L;

	public JTextArea runResult = new JTextArea();		//�����ʾ����

	public TextPane() {
		this.setLayout(new BorderLayout());
		this.runResult.setEditable(false);	//���ɱ༭��
		this.add(new JScrollPane(runResult));
	}

}
