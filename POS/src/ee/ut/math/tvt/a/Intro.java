//see teha ant targetiks.


package ee.ut.math.tvt.a;

import java.awt.EventQueue;

import javax.swing.UIManager;

import org.apache.log4j.Logger;




public class Intro {
	
	
 
	
	static Logger log = Logger.getLogger(Intro.class);
	//static String MODE = "console";

	
	public static void main(String[] args) {
		
		
		//if (args.length == 1 && args[0].equals(MODE)) {
		//	log.debug("Mode: " + MODE);
		//}
		log.info("Hello World, I am logger");
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
