package theFourthSubject;

import javax.swing.*;
import javax.swing.border.BevelBorder;
import javax.swing.border.Border;

import java.awt.*;
import java.awt.event.*;

public class MainFrame extends JFrame implements ActionListener, MouseListener {

	private static final long serialVersionUID = 1L;
	// 主面板
	public DrawPane dpane = new DrawPane();			//区域面板
	public ToolPane tpane = new ToolPane();			//工具面板
	public TextPane textpane = new TextPane();		//结果面板
	// 边界
	public static Border border = BorderFactory.createBevelBorder(
			BevelBorder.LOWERED, Color.BLACK, Color.DARK_GRAY);
	TheLocationMap locationMap = new TheLocationMap();
	Test3 test3 = new Test3();
	public MainFrame() {
		this.setTitle("超市选址");
		this.setSize(900, 600);
		this.setVisible(true);
		this.setLocationRelativeTo(null);
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);		//3
		this.makePane();	//显示面板
		this.addAction();
	}

	private void makePane() {
		JPanel left = new JPanel(new BorderLayout(5, 10));
		left.add(dpane);
		left.add(tpane, BorderLayout.SOUTH);
		JSplitPane jspane = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT, left,
			textpane);	//分割面板
		jspane.setDividerLocation(this.getWidth() / 4 * 3);
		this.add(jspane);
	}

	public void addAction() {
		this.tpane.add.addActionListener(this);	//增加单位
		this.tpane.delete.addActionListener(this);	//删除单位
		this.tpane.run.addActionListener(this);	//超市选址
		this.dpane.addMouseListener(this);	
	}

	// 按钮监听
	@SuppressWarnings("static-access")
	public void actionPerformed(ActionEvent e) {

		if (e.getSource().equals(tpane.add)) {		
			Cursor cursor = Toolkit.getDefaultToolkit().createCustomCursor(
					new ImageIcon("image/room.png").getImage(),
					new Point(10, 20), "stick");
			this.dpane.setCursor(cursor);
			this.dpane.add_flag = true;
		} 
		else if (e.getSource().equals(tpane.delete)) {
			this.dpane.setCursor(new Cursor(Cursor.HAND_CURSOR));
			this.dpane.delete_flag = true;
		} else if (e.getSource().equals(tpane.run)) {
			locationMap.getData(DrawPane.roomList);
			textpane.runResult.setText(test3.floyed(locationMap));
		}
	}

	public void mouseClicked(MouseEvent e) {

		if (e.getButton() == MouseEvent.BUTTON3) {
			this.dpane.setCursor(Cursor.getDefaultCursor());
			this.dpane.concat_flag = false;
			this.dpane.add_flag = false;
			this.dpane.delete_flag = false;
			this.dpane.select_flag = false;
		} else if (e.getButton() == MouseEvent.BUTTON1) {
			if (this.dpane.add_flag) {
				this.dpane.setCursor(Cursor.getDefaultCursor());
				this.dpane.addNewRoom(e.getX(), e.getY());
				this.dpane.add_flag = false;
			}
		}
	}

	@Override
	public void mousePressed(MouseEvent e) {
	}

	@Override
	public void mouseReleased(MouseEvent e) {
	}

	@Override
	public void mouseEntered(MouseEvent e) {
	}

	@Override
	public void mouseExited(MouseEvent e) {
	}

	public static void main(String args[]) {
		MainFrame mf = new MainFrame();
		mf.validate();
	}
}
