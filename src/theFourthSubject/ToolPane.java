package theFourthSubject;

import javax.swing.*;
import java.awt.*;

/*
 * 工具面板
 */
public class ToolPane extends JPanel {

	private static final long serialVersionUID = 1L;

	JButton add = new JButton(new ImageIcon("image/room.png"));
	JButton delete = new JButton(new ImageIcon("image/delete.png"));
	JButton run = new JButton(new ImageIcon("image/run.png"));

	public ToolPane() {
		this.setLayout(new BorderLayout());
		this.addTool();
	}

	private void addTool() {
		JToolBar tool = new JToolBar("MyTool");
		tool.setLayout(new GridLayout(1, 3, 80, 10));
		tool.setFloatable(true);	//设置为可以移动工具栏
		add.setBackground(Color.LIGHT_GRAY);
		delete.setBackground(Color.LIGHT_GRAY);
		run.setBackground(Color.LIGHT_GRAY);
		tool.add(add);
		tool.add(delete);
		tool.add(run);
		this.add(tool);
	}
}
