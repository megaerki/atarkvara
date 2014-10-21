package ee.ut.math.tvt.a;

import java.io.IOException;

import javax.swing.UIManager;

import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;

import ee.ut.math.tvt.salessystem.domain.controller.SalesDomainController;
import ee.ut.math.tvt.salessystem.domain.controller.impl.SalesDomainControllerImpl;
import ee.ut.math.tvt.salessystem.ui.ConsoleUI;
import ee.ut.math.tvt.salessystem.ui.SalesSystemUI;

public class Intro {
	
	private static final Logger log = Logger.getLogger(Intro.class);
	private static final String MODE = "console";
	
	public static void main(String[] args) throws IOException {
		PropertyConfigurator.configure("application.properties");
		
		final SalesDomainController domainController = new SalesDomainControllerImpl();
		
		//Lisa JGoodies windowsi v2ljan2gemine Java default asemel
		try {
			UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
		} catch (Exception e){
			e.printStackTrace();
		}
		
		if (args.length == 1 && args[0].equals(MODE)) {
			log.debug("Mode: " + MODE);

			ConsoleUI cui = new ConsoleUI(domainController);
			cui.run();
		} else {
			IntroUI introUI = new IntroUI();
			introUI.frame.setVisible(true);
			introUI.frame.setAlwaysOnTop(true);

			final SalesSystemUI ui = new SalesSystemUI(domainController);
			ui.setVisible(true);

			introUI.frame.setAlwaysOnTop(false);
			
			try {
				Thread.sleep(3000);
				log.info("Intro aken suletud");
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			introUI.frame.setVisible(false);
		}
	}
}
