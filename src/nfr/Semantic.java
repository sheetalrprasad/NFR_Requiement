package nfr;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.FileReader;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.swing.JTextArea;
import javax.swing.JButton;
import javax.swing.JFileChooser;

public class Semantic extends JFrame {

	private JPanel contentPane;
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Semantic frame = new Semantic();
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
	public Semantic() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 600, 600);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		final JTextArea textArea = new JTextArea();
		textArea.setBounds(26, 46, 527, 419);
		contentPane.add(textArea);
		
		
		JButton btnNewButton = new JButton("Home");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				CloseFrame();
				HomePage pg = new HomePage();
				pg.setVisible(true);
				System.out.println("Calculate semantic similarity");
			}
		});
		btnNewButton.setBounds(398, 494, 144, 56);
		contentPane.add(btnNewButton);
		
		JButton btnNewButton_1 = new JButton("Display semantic similarity");
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				try{
					FileReader writer = new FileReader("E:\\College Sheetal\\BE Project\\Code1\\NFRProject\\Project_NFR\\out.txt");
					BufferedReader bw = new BufferedReader(writer);
					textArea.read(bw, null);
					bw.close();
					textArea.requestFocus();
				}catch(Exception E){
					System.out.println(E.toString());
				}
			}
		});
		btnNewButton_1.setBounds(75, 494, 175, 56);
		contentPane.add(btnNewButton_1);
		
		
	}
	

	public void CloseFrame()
	{
		super.dispose();
	}

}
