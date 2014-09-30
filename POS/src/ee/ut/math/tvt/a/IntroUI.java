package ee.ut.math.tvt.a;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import java.awt.BorderLayout;
import java.awt.GridBagLayout;
import javax.swing.JTextField;
import java.awt.GridBagConstraints;
import javax.swing.JLabel;
import java.awt.Insets;
import java.awt.Font;
import java.awt.Canvas;
import javax.swing.ImageIcon;
import java.awt.Panel;
import java.awt.Toolkit;

public class IntroUI {

	public JFrame frame;

	public IntroUI() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setIconImage(Toolkit.getDefaultToolkit().getImage("C:\\Users\\Gamexh\\Desktop\\atarkvara\\POS\\img\\logo.jpg"));
		frame.setBounds(100, 100, 450, 300);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		JLabel lblProjektijuht = new JLabel("Projektijuht");
		lblProjektijuht.setBounds(23, 24, 94, 14);
		lblProjektijuht.setFont(new Font("Tahoma", Font.BOLD, 11));
		frame.getContentPane().add(lblProjektijuht);
		
		JLabel lblErkiViidalepp = new JLabel("Erki Viidalepp");
		lblErkiViidalepp.setBounds(23, 49, 82, 14);
		frame.getContentPane().add(lblErkiViidalepp);
		
		JLabel lblMegaerkiutee = new JLabel("megaerki@ut.ee");
		lblMegaerkiutee.setBounds(177, 49, 99, 14);
		frame.getContentPane().add(lblMegaerkiutee);
		
		JLabel lblLiikmed = new JLabel("Liikmed");
		lblLiikmed.setBounds(23, 74, 82, 14);
		lblLiikmed.setFont(new Font("Tahoma", Font.BOLD, 11));
		frame.getContentPane().add(lblLiikmed);
		
		JLabel lblHannesHaljaste = new JLabel("Hannes Haljaste");
		lblHannesHaljaste.setBounds(23, 122, 99, 14);
		frame.getContentPane().add(lblHannesHaljaste);
		
		JLabel lblGamexhutee = new JLabel("gamexh@ut.ee");
		lblGamexhutee.setBounds(177, 122, 97, 14);
		frame.getContentPane().add(lblGamexhutee);
		
		JLabel lblKaarelViigi = new JLabel("Kaarel Viigi");
		lblKaarelViigi.setBounds(23, 97, 87, 14);
		frame.getContentPane().add(lblKaarelViigi);
		
		JLabel lblMeeskondA = new JLabel("Meeskond A");
		lblMeeskondA.setBounds(23, 215, 119, 31);
		lblMeeskondA.setFont(new Font("Vijaya", Font.PLAIN, 30));
		frame.getContentPane().add(lblMeeskondA);
		
		JLabel lblNewLabel = new JLabel("");
		lblNewLabel.setIcon(new ImageIcon("C:\\Users\\Gamexh\\Desktop\\atarkvara\\POS\\img\\logo.jpg"));
		lblNewLabel.setBounds(295, 150, 129, 96);
		frame.getContentPane().add(lblNewLabel);
		
		JLabel lblKasnakantgmailcom = new JLabel("kasnakant@gmail.com");
		lblKasnakantgmailcom.setBounds(177, 97, 171, 14);
		frame.getContentPane().add(lblKasnakantgmailcom);
	}
}
