import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JFrame;
import javax.swing.JTextField;

import com.xl.util.UIUtil;

import java.awt.BorderLayout;
import java.awt.Dimension;

import javax.swing.Box;
import javax.swing.JButton;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JLabel;
import java.awt.Component;
/*
 * Atlas图集拆分
 * 风的影子
 */
public class SplitWindow {

	private JFrame frame;
	private JTextField textField;
	private JTextField textField_1;
	private JTextArea textArea;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		UIUtil.setWindowsStyle();
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					SplitWindow window = new SplitWindow();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public SplitWindow() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 450, 300);
		frame.setTitle("AtlasSplit v1.0 - 风的影子");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(new BorderLayout(0, 0));
		
		Box horizontalBox = Box.createHorizontalBox();
		frame.getContentPane().add(horizontalBox, BorderLayout.NORTH);
		
		JLabel lblNewLabel = new JLabel("图片路径：");
		horizontalBox.add(Box.createRigidArea(new Dimension(10, 20)));
		horizontalBox.add(lblNewLabel);
		
		
		textField = new JTextField();
		textField.setToolTipText("输入图片路径");
		horizontalBox.add(textField);
		textField.setColumns(10);
		
		JButton btnNewButton = new JButton("开始分割");
		horizontalBox.add(btnNewButton);
		
		Box horizontalBox_1 = Box.createHorizontalBox();
		frame.getContentPane().add(horizontalBox_1, BorderLayout.SOUTH);
		
		Component rigidArea = Box.createRigidArea(new Dimension(10, 20));
		horizontalBox_1.add(rigidArea);
		
		JLabel lblNewLabel_1 = new JLabel("输出目录：");
		horizontalBox_1.add(lblNewLabel_1);
		
		textField_1 = new JTextField();
		textField_1.setToolTipText("输出目录");
		textField_1.setText("pack_out\\");
		horizontalBox_1.add(textField_1);
		textField_1.setColumns(10);
//		horizontalBox.add(Box.createRigidArea(new Dimension(10, 20)));
		
		Box verticalBox = Box.createVerticalBox();
		frame.getContentPane().add(verticalBox, BorderLayout.CENTER);
		textArea = new JTextArea();
		JScrollPane scrollPane = new JScrollPane(textArea);
		verticalBox.add(scrollPane);
		
		
		
		btnNewButton.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				System.out.println("开始拆分");
			    AtlasImageSplite.outPath = textField_1.getText();
			    AtlasImageSplite.imageFilePath = textField.getText();
			    String name = AtlasImageSplite.imageFilePath.substring(0,AtlasImageSplite.imageFilePath.length()-4);
			    AtlasImageSplite.atlasFilePath = name+".atlas";
			    String args[] = new String[0];
			    try {
					String retext = AtlasImageSplite.main(args);
					textArea.setText(retext);
				} catch (Exception e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				
			}
		});
	}

}
