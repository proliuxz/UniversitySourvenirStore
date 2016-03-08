package sg.edu.nus.iss.usstore.gui;
import java.awt.BorderLayout;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JInternalFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.WindowConstants;
import javax.swing.border.LineBorder;

/**
* This code was edited or generated using CloudGarden's Jigloo
* SWT/Swing GUI Builder, which is free for non-commercial
* use. If Jigloo is being used commercially (ie, by a corporation,
* company or business for any purpose whatever) then you
* should purchase a license for each developer using Jigloo.
* Please visit www.cloudgarden.com for details.
* Use of Jigloo implies acceptance of these licensing terms.
* A COMMERCIAL LICENSE HAS NOT BEEN PURCHASED FOR
* THIS MACHINE, SO JIGLOO OR THIS CODE CANNOT BE USED
* LEGALLY FOR ANY CORPORATE OR COMMERCIAL PURPOSE.
*/
public class LoginScreen extends javax.swing.JFrame {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JLabel lblName;
	private JInternalFrame LoginFrame;
	private JLabel lblTeamName;
	private JLabel lblSchoolLogo;
	private JPanel FooterPanel;
	private JPasswordField PwdField;
	private JButton Login;
	private JLabel lblPwd;
	private JTextField txtName;
	
	private StoreApplication storeApp;
	
	public LoginScreen(StoreApplication storeApp)
	{
		super();
		this.storeApp=storeApp;
		initGUI();
	}
	
	private void initGUI() {
		try {
			this.setTitle("UNIVERSITY SOUVERIN STORE");
			BorderLayout thisLayout = new BorderLayout();
			setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
			getContentPane().setLayout(thisLayout);
			this.setMinimumSize(new java.awt.Dimension(383, 248));
				LoginFrame = new JInternalFrame();
				getContentPane().add(LoginFrame, BorderLayout.CENTER);
				LoginFrame.setTitle("Login Screen");
				GridBagLayout LoginFrameLayout = new GridBagLayout();
				LoginFrameLayout.rowWeights = new double[] {0.1, 0.1, 0.1, 0.1, 0.1, 0.1, 0.1, 0.1, 0.1};
				LoginFrameLayout.rowHeights = new int[] {7, 20, 20, 20, 20, 20, 7, 7, 7};
				LoginFrameLayout.columnWeights = new double[] {0.1, 0.1, 0.1, 0.1};
				LoginFrameLayout.columnWidths = new int[] {7, 20, 7, 7};
				LoginFrame.getContentPane().setLayout(LoginFrameLayout);
				LoginFrame.setPreferredSize(new java.awt.Dimension(266, 147));
				LoginFrame.setBounds(0, 0, 266, 147);
				{
					lblName = new JLabel();
					LoginFrame.getContentPane().add(lblName, new GridBagConstraints(1, 2, 1, 1, 0.0, 0.0, GridBagConstraints.EAST, GridBagConstraints.NONE, new Insets(0, 0, 0, 0), 0, 0));
					lblName.setText("UserName:");
				}
				{
					txtName = new JTextField();
					LoginFrame.getContentPane().add(txtName, new GridBagConstraints(2, 2, 1, 1, 0.0, 0.0, GridBagConstraints.WEST, GridBagConstraints.NONE, new Insets(0, 10, 0, 0), 0, 0));
					txtName.setPreferredSize(new java.awt.Dimension(125, 23));
					txtName.setSize(125, 23);
					txtName.setAutoscrolls(false);
					txtName.setMinimumSize(new java.awt.Dimension(125, 23));
				}
				{
					lblPwd = new JLabel();
					LoginFrame.getContentPane().add(lblPwd, new GridBagConstraints(1, 3, 1, 1, 0.0, 0.0, GridBagConstraints.EAST, GridBagConstraints.NONE, new Insets(5, 0, 0, 0), 0, 0));
					lblPwd.setText("Password:");
				}
				{
					Login = new JButton();
					LoginFrame.getContentPane().add(Login, new GridBagConstraints(1, 4, 2, 1, 0.0, 0.0, GridBagConstraints.CENTER, GridBagConstraints.NONE, new Insets(5, 0, 0, 0), 0, 0));
					Login.setText("Login");
					Login.setPreferredSize(new java.awt.Dimension(77, 23));
					Login.addActionListener(new ActionListener() {
						public void actionPerformed(ActionEvent evt) {
							LoginActionPerformed(evt);
						}
					});
				}
				{
					PwdField = new JPasswordField();
					LoginFrame.getContentPane().add(PwdField, new GridBagConstraints(2, 3, 1, 1, 0.0, 0.0, GridBagConstraints.WEST, GridBagConstraints.NONE, new Insets(5, 10, 0, 0), 0, 0));
					PwdField.setPreferredSize(new java.awt.Dimension(125, 23));
					PwdField.setSize(125, 23);
					PwdField.setMinimumSize(new java.awt.Dimension(125, 23));
				}
				LoginFrame.pack();
				LoginFrame.setVisible(true);
				LoginFrame.setBorder(new LineBorder(new java.awt.Color(0,0,0), 1, false));
				{
					FooterPanel = new JPanel();
					getContentPane().add(FooterPanel, BorderLayout.SOUTH);
					FooterPanel.setPreferredSize(new java.awt.Dimension(344, 63));
					{
						
						lblSchoolLogo = new JLabel();
						FooterPanel.add(lblSchoolLogo);		
						lblSchoolLogo.setPreferredSize(new java.awt.Dimension(238, 56));
						ImageIcon icon = new ImageIcon(this.getToolkit().getImage("images\\logo.png"));
						lblSchoolLogo.setIcon(icon);
					}
					{
						lblTeamName = new JLabel();
						FooterPanel.add(lblTeamName);
						lblTeamName.setText("<html>BY<br>SE23#2FT</html>");
						lblTeamName.setFont(new java.awt.Font("Verdana",1,20));
						lblTeamName.setPreferredSize(new java.awt.Dimension(119, 55));
						lblTeamName.setBackground(new java.awt.Color(255,255,255));
						lblTeamName.setHorizontalAlignment(SwingConstants.CENTER);
						lblTeamName.setHorizontalTextPosition(SwingConstants.CENTER);
					}
				}

			setIconImage(this.getToolkit().getImage("images\\app_icon.png"));
			
			pack();
			this.setSize(383, 248);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	@SuppressWarnings("deprecation")
	private void LoginActionPerformed(ActionEvent evt) {
		//System.out.println("Login.actionPerformed, event="+evt);
		//TODO add your code for Login.actionPerformed
		String msg;
		if(txtName.getText().equals("") || PwdField.getText().equals(""))
		{
			msg="UserName and Password fields cannot be blank";
			JOptionPane.showMessageDialog(this, msg, "Login status", JOptionPane.WARNING_MESSAGE);
		}
		else if (storeApp.login(txtName.getText(),PwdField.getText().toString()) == false)
		{
			msg="Invalid UserName/Password";
			JOptionPane.showMessageDialog(this, msg, "Login status", JOptionPane.ERROR_MESSAGE);
		}
	}

}
