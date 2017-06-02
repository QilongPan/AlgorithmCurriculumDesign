package theFourthSubject;

import javax.swing.*;
import java.awt.*;

public class TextPane extends JPanel {

	private static final long serialVersionUID = 1L;

	public JTextArea runResult = new JTextArea();		//结果显示区域

	public TextPane() {
		this.setLayout(new BorderLayout());
		this.runResult.setEditable(false);	//不可编辑的
		this.add(new JScrollPane(runResult));
	}

}
