package nfr;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.SwingConstants;
import java.awt.Font;
import java.io.File;
import java.awt.Color;
import javax.swing.border.LineBorder;
import javax.swing.UIManager;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class HomePage extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = -4463399997211169886L;
	private JPanel contentPane;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					HomePage frame = new HomePage();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public HomePage() {
		
		File dataDir = new File("data/");
		File dataTestDir = new File("data/test/");
		File dataTrainDir = new File("data/train/");
		File modelDir = new File("model/");
		File vocabularyDir = new File("vocabulary/");
		File outputDir = new File("output/");
		
		dataDir.mkdirs();
		dataTestDir.mkdirs();
		dataTrainDir.mkdirs();
		modelDir.mkdirs();
		vocabularyDir.mkdirs();
		outputDir.mkdirs();
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 600, 600);
		contentPane = new JPanel();
		contentPane.setBackground(UIManager.getColor("Button.background"));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JPanel panel = new JPanel();
		panel.setBorder(new LineBorder(new Color(0, 0, 0), 2, true));
		panel.setBackground(Color.LIGHT_GRAY);
		panel.setBounds(10, 11, 564, 97);
		contentPane.add(panel);
		panel.setLayout(null);
		
		JLabel lblNfrExtractor = new JLabel("NFR Extractor");
		lblNfrExtractor.setBackground(Color.LIGHT_GRAY);
		lblNfrExtractor.setFont(new Font("Arial", Font.PLAIN, 20));
		lblNfrExtractor.setHorizontalAlignment(SwingConstants.CENTER);
		lblNfrExtractor.setBounds(0, 0, 564, 97);
		panel.add(lblNfrExtractor);
		
		JButton btnTrainModel = new JButton("Create Learning Model");
		btnTrainModel.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				CloseFrame();
				ChooseFile cf = new ChooseFile();
				cf.setVisible(true);
			}
		});
		btnTrainModel.setBounds(10, 119, 564, 61);
		contentPane.add(btnTrainModel);
		
		JButton btnExtractTerms = new JButton("Extract Terms");
		btnExtractTerms.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				CloseFrame();
				ExtractKeywords ek = new ExtractKeywords();
				ek.setVisible(true);
			}
		});
		btnExtractTerms.setBounds(10, 191, 564, 61);
		contentPane.add(btnExtractTerms);
		
		JButton calculateSimilarity = new JButton("Calculate Semantic Similarity");
		calculateSimilarity.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				CloseFrame();
				Calculate cal = new Calculate();
				cal.setVisible(true);
				System.out.println("Calculate semantic similarity");
			}
		});
		calculateSimilarity.setBounds(10, 263, 564, 61);
		contentPane.add(calculateSimilarity);
	}

	public void CloseFrame()
	{
		super.dispose();
	}
}
