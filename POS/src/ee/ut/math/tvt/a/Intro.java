//see teha ant targetiks.


package ee.ut.math.tvt.a;

import java.awt.EventQueue;

import javax.swing.UIManager;

public class Intro {
				
	public static void main(String[] args) {
		
		//Lisa JGoodies windowsi v2ljan2gemine Java default asemel
		try {
			UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
		} catch (Exception e){
			e.printStackTrace();
		}
		
		//Loo avaaken ja tee see n2htavaks
		try {
			IntroUI window = new IntroUI();
			window.frame.setVisible(true);
		} 
		catch (Exception e) {
			e.printStackTrace();
		}

	}
}
