package nfr;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.UIManager;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.swing.JButton;
import javax.swing.JFileChooser;

public class Calculate extends JFrame {

	private JPanel contentPane;
	private JTextField textField;
	JTextArea textArea;
	
	
	private String fileGetName = null;
	private String fileAbsolutePath = null;
	

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Calculate frame = new Calculate();
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
	public Calculate() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 600, 600);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setBackground(UIManager.getColor("Button.background"));
		contentPane.setLayout(null);
		setContentPane(contentPane);
		
		JPanel panel = new JPanel();
		panel.setBorder(new LineBorder(new Color(0, 0, 0), 2, true));
		panel.setBackground(Color.LIGHT_GRAY);
		panel.setBounds(10, 11, 564, 97);
		contentPane.add(panel);
		panel.setLayout(null);
		
		JLabel calSem = new JLabel("Calculate Semantic Similarity");
		calSem.setBackground(Color.LIGHT_GRAY);
		calSem.setFont(new Font("Arial", Font.PLAIN, 20));
		calSem.setHorizontalAlignment(SwingConstants.CENTER);
		calSem.setBounds(0, 0, 564, 97);
		panel.add(calSem);
		
		textField = new JTextField();
		textField.setBounds(221, 162, 317, 48);
		contentPane.add(textField);
		textField.setColumns(10);
		
		textArea = new JTextArea();
		textArea.setBounds(41, 237, 505, 235);
		contentPane.add(textArea);
		
		
		
		JButton btnNewButton = new JButton("Browse");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
				JFileChooser chooser1 = new JFileChooser();
				FileNameExtensionFilter filter1 = new FileNameExtensionFilter("Text files", "txt");
				chooser1.setFileFilter(filter1);
				//fileName = chooser1.getSelectedFile();
				//System.out.println(chooser1.getParent());
				int returnVal = chooser1.showOpenDialog(getParent());
				
				if(returnVal == JFileChooser.APPROVE_OPTION)
				{
					textField.setText(chooser1.getSelectedFile().getAbsolutePath());
				}
				
				try{
					FileReader writer = new FileReader(chooser1.getSelectedFile().getAbsolutePath());
					BufferedReader bw = new BufferedReader(writer);
					textArea.read(bw, null);
					bw.close();
					textArea.requestFocus();
				}catch(Exception E){
					System.out.println(E.toString());
				}
			}
		});
		btnNewButton.setBounds(41, 159, 138, 55);
		contentPane.add(btnNewButton);
		
		
		
		JButton btnNewButton_1 = new JButton("Calculate");
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				CloseFrame();
				/*try {
					wfileName=Sema.main(fileName);
				} catch (FileNotFoundException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}*/
				WuPalmerAlgo wu = new WuPalmerAlgo();
				try {
					WuPalmerAlgo.main(null);
				} catch (FileNotFoundException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				Semantic sem = new Semantic();
				sem.setVisible(true);
				System.out.println("Calculate semantic similarity");
			}
		});
		btnNewButton_1.setBounds(400, 501, 138, 36);
		contentPane.add(btnNewButton_1);
		
		
		
	}
	public void CloseFrame()
	{
		super.dispose();
	}
}
