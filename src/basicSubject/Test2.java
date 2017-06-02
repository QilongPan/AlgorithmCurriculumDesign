package basicSubject;

import javax.swing.JFrame;

/*
 * 实现分形树
 */
public class Test2 extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public Test2() {
		add(new FractalTree());
	}

	public static void main(String[] args) {
		Test2 frame = new Test2();
		frame.setTitle("分形树");
		frame.setSize(700, 700);
		frame.setLocationRelativeTo(null);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setVisible(true);
	}

}
