package sg.edu.nus.iss.usstore.gui;

import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;

import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.DefaultTableModel;

import sg.edu.nus.iss.usstore.domain.Member;
import sg.edu.nus.iss.usstore.domain.Transaction;
import sg.edu.nus.iss.usstore.util.TableColumnAdjuster;

/**
 * @author Achyut Suresh Rao
 */

public class MemberListPanel extends JPanel {

	private static final long serialVersionUID = 1L;
	private final String[] columnNames = { "Name", "Member ID",
			"Loyalty Points" };

	private StoreApplication manager;
	private JButton modifyButton;
	private JButton deleteButton;
	private JTable memberTable;
	private DefaultTableModel tableModel;

	public MemberListPanel(StoreApplication manager) {
		this.manager = manager;
		setLayout(new BorderLayout());
		add("North", createTopPanel());
		add("Center", createMiddlePanel(loadTableData(manager.getMemberList())));
		add("South", createBottomPanel());
		refreshTable();
		setVisible(true);
	}

	private Object[][] loadTableData(ArrayList<Member> memberList) {
		Object[][] data = new Object[memberList.size()][3];
		Member member;
		for (int i = 0; i < memberList.size(); i++) {
			member = memberList.get(i);
			data[i][0] = member.getName().toUpperCase();
			data[i][1] = member.getMemberID().toUpperCase();
			data[i][2] = member.getLoyaltyPoint();
		}
		return data;
	}

	private JPanel createTopPanel() {
		JPanel p = new JPanel(new BorderLayout());
		JPanel panel = new JPanel(new FlowLayout(FlowLayout.LEFT));
		panel.add(new JLabel("Member List"));
		p.add("Center", panel);
		JButton b = new JButton("Refresh");
		b.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				refreshTable();
			}
		});
		panel = new JPanel(new FlowLayout(FlowLayout.RIGHT));
		panel.add(b);
		p.add("East", panel);

		return p;
	}

	private Container createMiddlePanel(Object[][] data) {

		tableModel = new DefaultTableModel(data, columnNames) {

			private static final long serialVersionUID = 1L;

			@Override
			public boolean isCellEditable(int row, int column) {
				return false;
			}
		};

		memberTable = new JTable(tableModel);
		memberTable.setAutoResizeMode(JTable.AUTO_RESIZE_ALL_COLUMNS);
		TableColumnAdjuster tca = new TableColumnAdjuster(memberTable);
		tca.setColumnHeaderIncluded(true);
		tca.setColumnDataIncluded(true);
		tca.adjustColumns();
		memberTable.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		memberTable.getSelectionModel().addListSelectionListener(
				new ListSelectionListener() {

					@Override
					public void valueChanged(ListSelectionEvent arg0) {
						// TODO Auto-generated method stub
						if (memberTable.getSelectionModel().isSelectionEmpty()) {
							modifyButton.setEnabled(false);
							deleteButton.setEnabled(false);
						} else {
							modifyButton.setEnabled(true);
							deleteButton.setEnabled(true);
						}
					}
				});
		;
		memberTable.setFillsViewportHeight(true);
		memberTable.setAutoCreateRowSorter(true);

		JScrollPane p = new JScrollPane(memberTable);

		return p;
	}

	private JPanel createBottomPanel() {
		JPanel p = new JPanel();
		JButton b = new JButton("Add");
		b.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				MemberDialog memberDlg = new MemberDialog(manager, "Add Member");
				memberDlg.setVisible(true);
				refreshTable();
			}
		});
		p.add(b);

		modifyButton = new JButton("Modify");
		modifyButton.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				// TODO Auto-generated method stub
				String id = (String) memberTable.getValueAt(
						memberTable.getSelectedRow(), 1);
				MemberDialog memberDlg = new MemberDialog(manager,
						"Modify Member", id);

				memberDlg.setVisible(true);
				refreshTable();
			}
		});
		modifyButton.setEnabled(false);
		p.add(modifyButton);

		deleteButton = new JButton("Delete");
		deleteButton.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				// TODO Auto-generated method stub
				if (manager.getMemberList().size() > 10) {
					String id = (String) memberTable.getValueAt(
							memberTable.getSelectedRow(), 1);
					if (validDel(id)) {
						String msg = "Member '" + id + "' will be deleted";
						int n = JOptionPane.showConfirmDialog(null, msg,
								"Confirmation", JOptionPane.YES_NO_OPTION);
						if (n == 0) {
							// proceed deletion
							manager.removeMember(id);
							refreshTable();

						}
					} else {

						String msg = "This Member " + id
								+ " is associated with some transaction!";
						JOptionPane.showMessageDialog(null, msg, "Cannot Delete!", JOptionPane.WARNING_MESSAGE);
					}

				} else {
					JOptionPane.showMessageDialog(null,
							"Cannot Delete - Minimum threshold reached!",
							"Alert", JOptionPane.WARNING_MESSAGE);
				}
			}
		});
		deleteButton.setEnabled(false);
		p.add(deleteButton);

		b = new JButton("Back");
		b.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				// TODO Auto-generated method stub
				manager.getStoreWindow().changeCard("mainScreen");
			}
		});
		p.add(b);
		return p;
	}

	public void refreshTable() {
		tableModel.setDataVector(loadTableData(manager.getMemberList()),
				columnNames);
		tableModel.fireTableDataChanged();
	}

	public boolean validDel(String id) {
		boolean result = true;
		for (Transaction trans : this.manager.getTransactionList()) {
			if (trans.getCustomer() == this.manager.getMemberById(id)) {
				result = false;
				break;
			}
		}
		return result;
	}

}
