package sg.edu.nus.iss.usstore.gui;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;
import javax.swing.border.EmptyBorder;

import sg.edu.nus.iss.usstore.domain.*;
import sg.edu.nus.iss.usstore.util.StringDocument;

/**
 * @author Achyut Suresh Rao
 */

public class MemberDialog extends JDialog {

	private static final long serialVersionUID = 1L;
	private StoreApplication manager;
	private StoreWindow mainScreen;
	private int index;

	private JTextField name;
	private JTextField memberID;
	private JTextField loyaltyPoint;

	/**
	 * add mode
	 * 
	 * @param manager
	 * @param title
	 */
	public MemberDialog(StoreApplication manager, String title) {
		super(manager.getStoreWindow(), title);
		this.manager = manager;
		this.mainScreen = manager.getStoreWindow();
		initGUI();
		add("South", createAddBottomPanel());
	}

	/**
	 * modify mode
	 * 
	 * @param manager
	 * @param title
	 * @param id
	 */
	public MemberDialog(StoreApplication manager, String title, String id) {
		super(manager.getStoreWindow(), title);
		this.manager = manager;
		this.mainScreen = manager.getStoreWindow();
		this.index = manager.getMemberList().indexOf(manager.getMemberById(id));
		initGUI();
		memberID.setEditable(false);
		add("South", createModifyBottomPanel());
		Member m = manager.getMemberList().get(index);
		setData(m.getName(), m.getMemberID(), m.getLoyaltyPoint());
	}

	private void initGUI() {
		try {
			setLayout(new BorderLayout());
			add("Center", createCenterPanel());
			setSize(400, 160);
			setLocationRelativeTo(null);
			setModal(true);
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	private JPanel createCenterPanel() {
		JPanel p = new JPanel(new BorderLayout());
		p.setBorder(new EmptyBorder(10, 10, 0, 10));

		JPanel panel = new JPanel(new GridLayout(3, 1));
		panel.add(new JLabel("Name: "));
		panel.add(new JLabel("Member Id: "));
		panel.add(new JLabel("Loyalty Points: "));
		p.add("West", panel);

		name = new JTextField();
		name.setDocument(new StringDocument());
		memberID = new JTextField();
		memberID.setDocument(new StringDocument());
		loyaltyPoint = new JTextField();

		panel = new JPanel(new GridLayout(3, 1));
		panel.add(name);
		panel.add(memberID);
		panel.add(loyaltyPoint);
		p.add("Center", panel);

		return p;
	}

	public void setData(String memberName, String id, int loyalty) {

		name.setText(memberName);
		memberID.setText(id);
		loyaltyPoint.setText(Integer.toString(loyalty));

	}

	public boolean validateData() {
		if (name.getText().isEmpty() || memberID.getText().isEmpty()
				|| loyaltyPoint.getText().isEmpty()) {

			return false;
		} else {
			return true;
		}

	}

	private JPanel createAddBottomPanel() {
		// TODO Auto-generated method stub
		JPanel panel = new JPanel(new FlowLayout());
		JButton button = new JButton("Add");
		loyaltyPoint.setEditable(false);
		loyaltyPoint.setText("-1");
		button.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				if (validMemberId()) {
					if (validateData()) {
						manager.registerMember(getNameText(), getIdText(), -1);
						mainScreen.getMemberPanel().refreshTable();
						dispose();
					} else {
						System.out.println("invalid data");
						JOptionPane.showMessageDialog(new JFrame(),
								"Enter All/Correct Details");

					}

				} else {
					JOptionPane.showMessageDialog(null, "Duplicate Member ID detected",
							"Alert", JOptionPane.WARNING_MESSAGE);
				}
			}
		});
		panel.add(button);
		button = new JButton("Cancel");
		button.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				// TODO Auto-generated method stub
				dispose();
			}
		});
		panel.add(button);
		return panel;
	}

	private JPanel createModifyBottomPanel() {
		// TODO Auto-generated method stub
		JPanel panel = new JPanel(new FlowLayout());
		JButton button = new JButton("Modify");
		loyaltyPoint.setEditable(false);
		button.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				
				if (validateData()) {
					manager.modifyMember(getNameText(), getIdText(),
							getLoyaltyText(), index);

					mainScreen.getMemberPanel().refreshTable();
					dispose();
				} else {

					JOptionPane.showMessageDialog(null, "Enter All/Correct Details",
							"Alert", JOptionPane.WARNING_MESSAGE);
				}
				
			}
		});
		panel.add(button);

		button = new JButton("Cancel");
		button.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				// TODO Auto-generated method stub
				dispose();
			}
		});
		panel.add(button);
		return panel;
	}

	public String getIdText() {
		return memberID.getText();
	}

	public String getNameText() {
		return name.getText();

	}

	public int getLoyaltyText() {
		return Integer.parseInt(this.loyaltyPoint.getText());
	}

	public boolean validMemberId() {
		boolean result = true;
		for (Member mem : this.manager.getMemberList()) {
			if (mem.getMemberID().equals(getIdText())) {
				result = false;
				break;
			}

		}
		return result;
	}

}
