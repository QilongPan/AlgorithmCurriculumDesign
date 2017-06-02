package basicSubject;

import java.awt.Graphics;
import javax.swing.JPanel;

public class FractalTree extends JPanel {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private int startX = 300;
	private int startY = 600;
	private int endX = 300;
	private int endY = 0;
	private int startAngle = 90;
	private double turnAngle = 30;
	private int stopLength = 50;

	protected void paintComponent(Graphics g) {
		super.paintComponent(g);
		g.drawLine(startX, startY, endX, endY);
		paintLine(g, startY - endY, startAngle, startX, startY);
	}

	public void paintLine(Graphics g, double length, double angle, int x, int y) {
		if (length - stopLength > 0) {
			int startXA = (int) (x + length * Math.cos(angle * Math.PI / 180)
					/ 3);
			int startYA = (int) (y - length * Math.sin(angle * Math.PI / 180)
					/ 3);
			int startXB = (int) (x + length * Math.cos(angle * Math.PI / 180)
					/ 3 * 2);
			int startYB = (int) (y - length * Math.sin(angle * Math.PI / 180)
					/ 3 * 2);
			paintRightLeaf(g, length / 3 * 2, angle - turnAngle, startXA,
					startYA);
			paintLeftLeaf(g, length / 3, angle + turnAngle, startXB, startYB);
		}
	}

	// »­ÓÒ±ßÒ¶×Ó
	public void paintRightLeaf(Graphics g, double length, double angle,
			int startX, int startY) {
		int endX = (int) (startX + length * Math.cos(angle * Math.PI / 180));
		int endY = (int) (startY - length * Math.sin(angle * Math.PI / 180));
		g.drawLine(startX, startY, endX, endY);
		paintLine(g, length, angle, startX, startY);
	}

	// »­×ó±ßÒ¶×Ó
	public void paintLeftLeaf(Graphics g, double length, double angle,
			int startX, int startY) {
		int endX = (int) (startX + length * Math.cos(angle * Math.PI / 180));
		int endY = (int) (startY - length * Math.sin(angle * Math.PI / 180));
		g.drawLine(startX, startY, endX, endY);
		paintLine(g, length, angle, startX, startY);
	}

}
