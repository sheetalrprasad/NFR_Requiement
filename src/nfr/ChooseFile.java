package nfr;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.filechooser.FileNameExtensionFilter;

import org.apache.commons.io.FileUtils;

import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.JFileChooser;

import java.awt.event.ActionListener;
import java.io.*;
import java.awt.event.ActionEvent;

import javax.swing.JTextArea;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.SwingConstants;
import javax.swing.JScrollPane;

import com.entopix.maui.filters.MauiFilter;
import com.entopix.maui.main.*;
import javax.swing.border.CompoundBorder;

public class ChooseFile extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField filePath;
	private JButton createLearningModel;
	
	private String fileName = null;
	private String fileDir = null;
	
	static String location = new String("-l");
	static String trainLocationPath = new String("data/train");
	static String model = new String("-m");
	static String modelPath = new String("model/Model_1");
	static String vocabulary = new String("-v");
	static String vocabularyPath = new String("vocabulary/csTermsManual.rdf");
	static String vocabFormat = new String("-f");
	static String vocabFormatType = new String("skos");

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					ChooseFile frame = new ChooseFile();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	public ChooseFile() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 600, 600);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		filePath = new JTextField();
		filePath.setHorizontalAlignment(SwingConstants.CENTER);
		filePath.setBounds(10, 11, 564, 56);
		contentPane.add(filePath);
		filePath.setColumns(10);
		
		/*
		textArea = new JTextArea();
		textArea.setBounds(10, 78, 544, 493);
		contentPane.add(textArea);
		textArea.setLineWrap(true);
		*/
		
		final JLabel fileStatus = new JLabel("");
		fileStatus.setHorizontalAlignment(SwingConstants.CENTER);
		fileStatus.setBounds(10, 145, 564, 28);
		contentPane.add(fileStatus);
		
		JButton browseFileButton = new JButton("Browse");
		browseFileButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
				JFileChooser chooser1 = new JFileChooser();
				FileNameExtensionFilter filter1 = new FileNameExtensionFilter("Text Files & Key files", "txt", "key");
				chooser1.setFileFilter(filter1);
				//System.out.println(chooser1.getParent());
				int returnVal = chooser1.showOpenDialog(getParent());
				
				if(returnVal == JFileChooser.APPROVE_OPTION)
				{
					fileStatus.setText("");
					filePath.setText(chooser1.getSelectedFile().getAbsolutePath());
					fileName = chooser1.getSelectedFile().getName();
					fileDir = chooser1.getSelectedFile().getAbsolutePath();
					//fileStatus.setText(fileName);
					
				}
				
				/*
				try
				{
					FileReader writer = new FileReader(chooser1.getSelectedFile().getAbsolutePath());
					BufferedReader bw = new BufferedReader(writer);
					textArea.read(bw, null);
					bw.close();
					//textArea.setText("");
					textArea.requestFocus();
				}catch(Exception E)
				{
					E.printStackTrace();
				}
				*/
			}
		});
		browseFileButton.setBounds(10, 78, 232, 56);
		contentPane.add(browseFileButton);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setViewportBorder(new CompoundBorder());
		scrollPane.setBounds(10, 184, 564, 299);
		contentPane.add(scrollPane);
		//fileNamesArea.add(scrollPane);
		
		final JTextArea fileNamesArea = new JTextArea();
		scrollPane.setViewportView(fileNamesArea);
		fileNamesArea.setText("Current documents in training dataset:\n\n");
		fileNamesArea.setVisible(true);
		fileNamesArea.setEditable(false);
		
		JButton addToTrainingSet = new JButton("Add file to Training Set");
		addToTrainingSet.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
				if(fileName == null || fileName == "")
				{
					JOptionPane.showMessageDialog(null, "No file selected!");
					return;
				}
				
				fileStatus.setText(fileName + " has been added to the training data set");
				fileNamesArea.append(fileName + "\n");
				
				File srcFile = new File(fileDir);
				File destDir = new File("data/train");
				
				try{
					FileUtils.copyFileToDirectory(srcFile, destDir);
					
				}catch(Exception E)
				{
					E.printStackTrace();
				}
			}
		});
		addToTrainingSet.setBounds(352, 78, 222, 56);
		contentPane.add(addToTrainingSet);
		
		createLearningModel = new JButton("Create Learning Model");
		createLearningModel.setBounds(352, 494, 222, 56);
		contentPane.add(createLearningModel);
		
		JButton goToHome = new JButton("Home");
		goToHome.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {

				CloseFrame();
				HomePage hp = new HomePage();
				hp.setVisible(true);
			}
		});
		goToHome.setBounds(10, 494, 232, 56);
		contentPane.add(goToHome);
		createLearningModel.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				File trainFile = new File(trainLocationPath);
				if(getNumberOfFiles(trainFile) == 0)
				{
					JOptionPane.showMessageDialog(null, "No input documents to train!");
					return;
				}
				
				MauiModelBuilder modelBuilder = new MauiModelBuilder();
				modelBuilder.setBasicFeatures(true);
				String[] trainParameters =  new String[]{location, trainLocationPath, model, modelPath, vocabulary, vocabularyPath, vocabFormat, vocabFormatType};
				
				try
				{
					modelBuilder.setOptions(trainParameters);
					MauiFilter mauiFilter = modelBuilder.buildModel();
					modelBuilder.saveModel(mauiFilter);
					JOptionPane.showMessageDialog(null, "Learning Model Built successfully");
				}
				catch(Exception E)
				{
					E.printStackTrace();
				}
			}
		});
		
	}
	
	public int getNumberOfFiles(File file)
	{
		int fileCount = 0;
		File files[] = file.listFiles();
		
		for(File f : files)
		{
			if(f.isDirectory())
				fileCount += getNumberOfFiles(f);
			else
				fileCount++;
		}
		
		return fileCount;
	}
	
	public void CloseFrame()
	{
		super.dispose();
	}
}
