import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.JFrame;

import operation.Timer;

public class Tester {
	public static void main(String[] args) {
		Timer timer = new Timer();
		JFrame frame = new JFrame("Sample Popup");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.addKeyListener(new KeyListener() {
			@Override
			public void keyTyped(KeyEvent e) {
				if (e.getKeyChar()==('a')) {
					timer.pause();
					System.out.println("pause");
				}
				if (e.getKeyChar() == 'e') {
					timer.resume();
				}
			}

			@Override
			public void keyPressed(KeyEvent e) {
			}

			@Override
			public void keyReleased(KeyEvent e) {
			}
		});
		frame.setVisible(true);
		frame.requestFocusInWindow();
		timer.start();
		while (1 == 1) {
			System.out.println(timer.getMinuteFormat());

			try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}

}
