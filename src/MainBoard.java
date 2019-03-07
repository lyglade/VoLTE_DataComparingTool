import java.awt.EventQueue;
import java.io.File;

import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.JList;
import javax.swing.JTextArea;
import javax.swing.JComboBox;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.JToggleButton;
import javax.swing.JTable;
import javax.swing.JFileChooser;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.JSeparator;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class MainBoard {

	private JFrame frame;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					MainBoard window = new MainBoard();
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
	public MainBoard() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 528, 394);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		String[] JCBList1 = { "»ªÎª", "ÖÐÐË", "°®Á¢ÐÅ" ,"Åµ»ùÑÇ","±´¶û"};
		JComboBox comboBox = new JComboBox(JCBList1);
		comboBox.setBounds(31, 85, 79, 23);		
		frame.getContentPane().add(comboBox);
		
		JButton btnNewButton = new JButton("\u5BFC\u5165HSS\u6570\u636E");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				JFileChooser fd = new JFileChooser();
				//fd.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
				fd.setMultiSelectionEnabled(true);
				fd.showOpenDialog(null);
				File f = fd.getSelectedFile();
				
				if(f != null){}
			}
		});
		btnNewButton.setBounds(120, 85, 113, 23);
		frame.getContentPane().add(btnNewButton);
		
		JLabel lblHss = new JLabel("HSS:");
		lblHss.setFont(new Font("Î¢ÈíÑÅºÚ", Font.BOLD, 17));
		lblHss.setBounds(31, 10, 54, 15);
		frame.getContentPane().add(lblHss);
		
		JButton btnNewButton_1 = new JButton("\u793A\u4F8B\u6587\u4EF6");
		btnNewButton_1.setBounds(336, 85, 93, 23);
		frame.getContentPane().add(btnNewButton_1);
		
		JSeparator separator = new JSeparator();
		separator.setBounds(10, 124, 492, 2);
		frame.getContentPane().add(separator);
		
		JLabel lblAs = new JLabel("AS:");
		lblAs.setFont(new Font("Î¢ÈíÑÅºÚ", Font.BOLD, 17));
		lblAs.setBounds(31, 143, 54, 15);
		frame.getContentPane().add(lblAs);
		
		JComboBox comboBox_1 = new JComboBox(JCBList1);
		comboBox_1.setBounds(31, 168, 79, 23);
		frame.getContentPane().add(comboBox_1);
		
		JButton btnNewButton_2 = new JButton("\u5BFC\u5165AS\u6570\u636E");
		btnNewButton_2.setBounds(120, 168, 113, 23);
		frame.getContentPane().add(btnNewButton_2);
		
		JButton button = new JButton("\u793A\u4F8B\u6587\u4EF6");
		button.setBounds(336, 168, 93, 23);
		frame.getContentPane().add(button);
		
		JSeparator separator_1 = new JSeparator();
		separator_1.setBounds(10, 239, 492, 2);
		frame.getContentPane().add(separator_1);
		
		JLabel lblEnumdns = new JLabel("ENUMDNS:");
		lblEnumdns.setFont(new Font("Î¢ÈíÑÅºÚ", Font.BOLD, 17));
		lblEnumdns.setBounds(31, 251, 152, 15);
		frame.getContentPane().add(lblEnumdns);
		
		JComboBox comboBox_2 = new JComboBox(JCBList1);
		comboBox_2.setBounds(31, 276, 79, 23);
		frame.getContentPane().add(comboBox_2);
		
		JButton button_1 = new JButton("\u5BFC\u5165AS\u6570\u636E");
		button_1.setBounds(120, 276, 113, 23);
		frame.getContentPane().add(button_1);
		
		JButton button_2 = new JButton("\u793A\u4F8B\u6587\u4EF6");
		button_2.setBounds(336, 276, 93, 23);
		frame.getContentPane().add(button_2);
	}
}
