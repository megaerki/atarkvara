package ee.ut.math.tvt.a;

import javax.swing.JFrame;
import javax.swing.JLabel;

import java.awt.Font;

import javax.swing.ImageIcon;

import org.apache.log4j.Logger;

import java.awt.Toolkit;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class IntroUI {
	
	static final Logger log = Logger.getLogger(IntroUI.class);

	public JFrame frame;

	public IntroUI() throws IOException {
		initialize();
		log.info("IntroUI aken avatud");
	}

	/**
	 * Initialize the contents of the frame.
	 * @throws IOException 
	 */
	private void initialize() throws IOException {
		frame = new JFrame();
		frame.setIconImage(Toolkit.getDefaultToolkit().getImage(IntroUI.class.getResource("/logo.jpg")));
		frame.setBounds(100, 100, 450, 300);
		frame.getContentPane().setLayout(null);
		
		JLabel lblProjektiJuhtLabel = new JLabel("Projektijuht");
		lblProjektiJuhtLabel.setBounds(23, 24, 94, 14);
		lblProjektiJuhtLabel.setFont(new Font("Tahoma", Font.BOLD, 11));
		frame.getContentPane().add(lblProjektiJuhtLabel);
		
		// Laeb propsid app.props failist.
		Properties props = new Properties();
		InputStream fis = Intro.class.getResourceAsStream("/application.properties");
		props.load(fis);
		fis.close();
		
		JLabel lblProjektiJuht = new JLabel(props.getProperty("projekt.juht")+", " + props.getProperty("projekt.juht.meil"));
		lblProjektiJuht.setBounds(23, 49, 220, 16);
		frame.getContentPane().add(lblProjektiJuht);
	 		
		JLabel lblLiikmed = new JLabel("Liikmed");
		lblLiikmed.setBounds(23, 74, 82, 14);
		lblLiikmed.setFont(new Font("Tahoma", Font.BOLD, 11));
		frame.getContentPane().add(lblLiikmed);
		
		JLabel lblProjektiLiige1 = new JLabel(props.getProperty("projekt.liige.1")+", "+props.getProperty("projekt.liige.1.meil"));
		lblProjektiLiige1.setBounds(23, 122, 220, 16);
		frame.getContentPane().add(lblProjektiLiige1);
		
		JLabel lblProjektiLiige2 = new JLabel(props.getProperty("projekt.liige.2")+", "+props.getProperty("projekt.liige.2.meil"));
		lblProjektiLiige2.setBounds(23, 97, 220, 16);
		frame.getContentPane().add(lblProjektiLiige2);
		
		JLabel lblMeeskondA = new JLabel("Meeskond A");
		lblMeeskondA.setBounds(90, 180, 119, 31);
		lblMeeskondA.setFont(new Font("Vijaya", Font.PLAIN, 30));
		frame.getContentPane().add(lblMeeskondA);
		
		JLabel lblLogo = new JLabel("");
		lblLogo.setIcon(new ImageIcon(IntroUI.class.getResource("/logo.jpg")));
		lblLogo.setBounds(320, 140, 129, 96);
		frame.getContentPane().add(lblLogo);
		
		Properties verProps = new Properties();
		InputStream ver = Intro.class.getResourceAsStream("/version.properties");
		verProps.load(ver);
		ver.close();
		
		JLabel lblNewLabelVersion = new JLabel("Versioon: " + verProps.getProperty("build.number"));
		lblNewLabelVersion.setBounds(320, 240, 200, 14);
		frame.getContentPane().add(lblNewLabelVersion);
	}
}
