package nfr;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.filechooser.FileNameExtensionFilter;

import org.apache.commons.io.FileUtils;

import com.entopix.maui.main.MauiTopicExtractor;
import com.entopix.maui.util.*;

import javax.swing.border.BevelBorder;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFileChooser;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.SwingConstants;
import java.awt.Font;
import java.awt.SystemColor;
import java.awt.Color;
import javax.swing.JTabbedPane;
import javax.swing.JTextField;
import java.awt.event.ActionListener;
import java.io.*;
import java.util.List;
import java.awt.event.ActionEvent;
import javax.swing.JTextArea;
import javax.swing.JScrollPane;
import javax.swing.border.LineBorder;

public class ExtractKeywords extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = -6481119815920561402L;
	private JPanel contentPane;
	private JTextField txtBoxFilePath;
	
	private String fileGetName = null;
	private String fileAbsolutePath = null;
	
	static String location = new String("-l");
	static String testLocationPath = new String("data/test");
	static String model = new String("-m");
	static String modelPath = new String("model/Model_1");
	static String vocabulary = new String("-v");
	static String vocabularyPath = new String("vocabulary/csTermsManual.rdf");
	static String vocabFormat = new String("-f");
	static String vocabFormatType = new String("skos");
	static String number = new String("-n");
	static String numberAmount = new String();
	
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					ExtractKeywords frame = new ExtractKeywords();
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
	public ExtractKeywords() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 600, 600);
		contentPane = new JPanel();
		contentPane.setBorder(new BevelBorder(BevelBorder.LOWERED, null, null, null, null));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JPanel panel = new JPanel();
		panel.setBorder(new LineBorder(new Color(0, 0, 0), 2, true));
		panel.setBounds(10, 11, 564, 69);
		panel.setBackground(SystemColor.inactiveCaption);
		panel.setForeground(Color.WHITE);
		contentPane.add(panel);
		panel.setLayout(null);
		
		JLabel lblTestModel = new JLabel("Test Model");
		lblTestModel.setFont(new Font("Arial", Font.PLAIN, 16));
		lblTestModel.setHorizontalAlignment(SwingConstants.CENTER);
		lblTestModel.setBounds(0, 0, 564, 69);
		panel.add(lblTestModel);
		
		JTabbedPane tabbedPane = new JTabbedPane(JTabbedPane.TOP);
		tabbedPane.setBounds(10, 91, 564, 393);
		contentPane.add(tabbedPane);
		
		JPanel addToTestSet = new JPanel();
		addToTestSet.setBackground(SystemColor.inactiveCaption);
		tabbedPane.addTab("Add File to Test Set", null, addToTestSet, null);
		addToTestSet.setLayout(null);
		
		txtBoxFilePath = new JTextField();
		txtBoxFilePath.setBounds(176, 11, 373, 42);
		addToTestSet.add(txtBoxFilePath);
		txtBoxFilePath.setColumns(10);
		txtBoxFilePath.setEditable(false);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 64, 539, 229);
		addToTestSet.add(scrollPane);
		
		final JTextArea fileContents = new JTextArea();
		scrollPane.setViewportView(fileContents);
		fileContents.setEditable(false);
		fileContents.setLineWrap(true);
		fileContents.setWrapStyleWord(true);
		
		JButton btnBrowseFile = new JButton("Browse");
		btnBrowseFile.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				JFileChooser chooser1 = new JFileChooser();
				FileNameExtensionFilter filter1 = new FileNameExtensionFilter("Text Files", "txt");
				chooser1.setFileFilter(filter1);
				//System.out.println(chooser1.getParent());
				int returnVal = chooser1.showOpenDialog(getParent());
				
				if(returnVal == JFileChooser.APPROVE_OPTION)
				{
					txtBoxFilePath.setText(chooser1.getSelectedFile().getAbsolutePath());
					fileGetName = chooser1.getSelectedFile().getName();
					fileAbsolutePath = chooser1.getSelectedFile().getAbsolutePath();
					System.out.println(fileGetName+"\n"+fileAbsolutePath);
				}
				
				try
				{
					FileReader writer = new FileReader(chooser1.getSelectedFile().getAbsolutePath());
					BufferedReader bw = new BufferedReader(writer);
					fileContents.read(bw, null);
					bw.close();
					//textArea.setText("");
					fileContents.requestFocus();
				}catch(Exception E)
				{
					E.printStackTrace();
				}
			}
		});
		btnBrowseFile.setBounds(10, 11, 156, 42);
		addToTestSet.add(btnBrowseFile);
		

		final JComboBox comboBox = new JComboBox();
		
		
		JButton btnAddToTestSet = new JButton("Add to Test Set");
		btnAddToTestSet.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				if(txtBoxFilePath.getText().equals(""))
				{
					JOptionPane.showMessageDialog(null, "No file selected!");
				}
				
				File srcFile = new File(fileAbsolutePath);
				File destDir = new File("data/test");
				
				try
				{
					FileUtils.copyFileToDirectory(srcFile, destDir);
				}
				catch(Exception E)
				{
					E.printStackTrace();
					return;
				}
				
				JOptionPane.showMessageDialog(null, "File " + fileGetName + " added to test set.");
				comboBox.addItem(fileGetName);
			}
		});
		btnAddToTestSet.setBounds(10, 304, 539, 50);
		addToTestSet.add(btnAddToTestSet);
		
		
		JPanel extractKeyphrases = new JPanel();
		extractKeyphrases.setBackground(SystemColor.inactiveCaption);
		tabbedPane.addTab("Extract Keyphrases", null, extractKeyphrases, null);
		extractKeyphrases.setLayout(null);
		
		comboBox.setBounds(10, 56, 539, 50);
		extractKeyphrases.add(comboBox);
		comboBox.setEditable(false);
		comboBox.addItem("All files");
		comboBox.setAlignmentX(CENTER_ALIGNMENT);
		comboBox.setAlignmentY(CENTER_ALIGNMENT);
		
		JLabel lblSelectFile = new JLabel("Select file to extract keyphrases");
		lblSelectFile.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblSelectFile.setBackground(Color.WHITE);
		lblSelectFile.setHorizontalAlignment(SwingConstants.CENTER);
		lblSelectFile.setBounds(10, 11, 539, 34);
		extractKeyphrases.add(lblSelectFile);

		final JTextField termAmount = new JTextField();
		final JTextArea termsExtracted = new JTextArea();
		
		JButton btnExtractTerms = new JButton("Extract Terms");
		btnExtractTerms.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				String outputFileDir = new String("output/");
				File outputDir = new File(outputFileDir);
				outputDir.mkdirs();
				
				if(comboBox.getSelectedItem().equals("All files"))
				{
					String numberAmount = new String(termAmount.getText());
					String[] testOptions = new String[]{location, testLocationPath, model, modelPath, vocabulary, vocabularyPath, vocabFormat, vocabFormatType, number, numberAmount };
					//System.out.println(Arrays.toString(testOptions));
					
					MauiTopicExtractor topicExtractor  = new MauiTopicExtractor();
					try {
						topicExtractor.setOptions(testOptions);
						topicExtractor.loadModel();
						
						FileWriter fw;
						BufferedWriter bw;
						
						List <MauiDocument> testFiles = topicExtractor.loadDocuments();
						List <MauiTopics> testTopics = topicExtractor.extractTopics(testFiles);
						
						termsExtracted.setText("");
						
						int fileCount = 0;
						
						for(MauiTopics topics: testTopics)
						{
							File testFolder = new File(testLocationPath);
							File fileList[] = testFolder.listFiles();
							
							File outputFile = new File(outputFileDir + fileList[fileCount].getName());
							System.out.println(outputFile.getAbsolutePath());
							fw = new FileWriter(outputFile);
							bw = new BufferedWriter(fw);
							bw.write("");
							
							fileCount++;
							System.out.println(topics.getFilePath());
							for(Topic topic: topics.getTopics())
							{
								System.out.println(topic.getTitle());
								termsExtracted.append("\t" + topic.getTitle() + "\n");
								bw.append(topic.getTitle() + "\n");
								
							}
							bw.close();
							fw.close();
							
						}
						
					} catch (Exception e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
				}
				else
				{
					String numberAmount = new String(termAmount.getText());
					String[] testOptions = new String[]{location, testLocationPath, model, modelPath, vocabulary, vocabularyPath, vocabFormat, vocabFormatType, number, numberAmount };
					//System.out.println(Arrays.toString(testOptions));
					MauiTopicExtractor topicExtractor  = new MauiTopicExtractor();
					
					File srcDir = new File("data/test");
					File tempCpyDir = new File("data/tempCpy");
					File tempDir = new File("data/temp");
					File selectedFile = new File("data/test/" + comboBox.getSelectedItem().toString());
					File tempCpyFile = new File("data/tempCpy/" + comboBox.getSelectedItem().toString());
					
					try
					{
						FileUtils.copyFileToDirectory(selectedFile, tempCpyDir);
						FileUtils.copyDirectory(srcDir, tempDir);
						//FileUtils.deleteQuietly(srcDir);
						FileUtils.cleanDirectory(srcDir);
						FileUtils.copyFileToDirectory(tempCpyFile, srcDir);
						
						topicExtractor.setOptions(testOptions);
						topicExtractor.loadModel();
						
						List <MauiDocument> testFiles = topicExtractor.loadDocuments();
						List <MauiTopics> testTopics = topicExtractor.extractTopics(testFiles);
						
						FileWriter fw;
						BufferedWriter bw;
						File outputFile = new File(outputFileDir + comboBox.getSelectedItem().toString());

						termsExtracted.setText("");
						for(MauiTopics topics: testTopics)
						{
							fw = new FileWriter(outputFile);
							bw = new BufferedWriter(fw);
							bw.write("");
							
							for(Topic topic: topics.getTopics())
							{
								System.out.println(topic.getTitle());
								termsExtracted.append("\t" + topic.getTitle() + "\n");
								bw.append(topic.getTitle() + "\n");
							}
							
							bw.close();
							fw.close();
						}
						
						FileUtils.cleanDirectory(srcDir);
						FileUtils.copyDirectory(tempDir, srcDir);
						//FileUtils.cleanDirectory(tempCpyFile);
						//FileUtils.cleanDirectory(tempDir);
						FileUtils.deleteDirectory(tempCpyDir);
						FileUtils.deleteDirectory(tempDir);
						
					}
					catch(Exception E)
					{
						E.printStackTrace();
					}
				}
			}
		});
		btnExtractTerms.setBounds(305, 117, 244, 50);
		extractKeyphrases.add(btnExtractTerms);
		
		JScrollPane scrollPane_1 = new JScrollPane();
		scrollPane_1.setBounds(10, 178, 539, 176);
		extractKeyphrases.add(scrollPane_1);
		//JTextArea termsExtracted = new JTextArea();
		scrollPane_1.setViewportView(termsExtracted);
		
		termAmount.setBounds(225, 117, 70, 50);
		extractKeyphrases.add(termAmount);
		termAmount.setColumns(10);
		
		JLabel lblTermstoExtract = new JLabel("Number of terms to extract");
		lblTermstoExtract.setHorizontalAlignment(SwingConstants.CENTER);
		lblTermstoExtract.setBounds(10, 117, 205, 50);
		extractKeyphrases.add(lblTermstoExtract);
		
		JButton button = new JButton("Home");
		button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				CloseFrame();
				HomePage hp = new HomePage();
				hp.setVisible(true);
			}
		});
		button.setBounds(10, 495, 232, 56);
		contentPane.add(button);
		
		//=============	CODE TO INITIALISE COMBOBOX WITH LIST OF DOCUMENTS IN TEST DIRECTORY
		File folder = new File(testLocationPath);
		File files[] = folder.listFiles();
		
		for(int i = 0; i < files.length; i++)
		{
			System.out.println("File: " + files[i].getName());
			comboBox.addItem(files[i].getName());
		}
		//============= END=============
	}
	
	public void CloseFrame()
	{
		super.dispose();
	}
}
