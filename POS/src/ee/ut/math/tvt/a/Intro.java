//see teha ant targetiks.


package ee.ut.math.tvt.a;

import java.awt.EventQueue;

public class Intro {
		
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					IntroUI window = new IntroUI();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

}
