package sg.edu.nus.iss.usstore.gui;

import java.awt.BorderLayout;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.util.Calendar;

import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

import org.jdatepicker.JDateComponentFactory;
import org.jdatepicker.JDatePicker;

import sg.edu.nus.iss.usstore.domain.MemberDiscount;
import sg.edu.nus.iss.usstore.domain.OcassionalDiscount;
import sg.edu.nus.iss.usstore.exception.DataInputException;
import sg.edu.nus.iss.usstore.util.IntDocument;
import sg.edu.nus.iss.usstore.util.StringDocument;
import sg.edu.nus.iss.usstore.util.Util;
/**
 * 
 * @author tanuj
 *
 */
public class DiscountDialog extends JDialog {
	private static final long serialVersionUID = 1L;
	private final JPanel contentPanel = new JPanel();
	private JTextField DiscountCode;
	private JTextField DiscountDescription;
	private JTextField StartDate;
	private JTextField Period;
	private JTextField Percent;
	private JTextField Applicable;
	private JDatePicker startDatePicker;

	private JRadioButton rdbtnOcaDisc;
	private JRadioButton rdbtnMemDisc;
	
	private StoreApplication manager;
	private boolean ifMember;

	/**
	 * Create the dialog.
	 */
	public DiscountDialog(StoreApplication manager){
		super(manager.getStoreWindow(),"Add New Discount");
		this.manager = manager;
		initGUI();
		createTopButton();
		createAddButton();
		rdbtnMemDisc.setSelected(true);
	}
	
	public DiscountDialog(StoreApplication manager, String code,boolean ifMember){
		super(manager.getStoreWindow());
		this.manager = manager;
		this.ifMember = ifMember;
		initGUI();
		createModiyButton();
		createDeleteButton();
		if(ifMember){
			setTitle("Member Discount");
			StartDate.setVisible(true);
			((JComponent)startDatePicker).setVisible(false);
			Period.setEditable(false);
			Period.setText("ALWAYS");
			Applicable.setText("M");
			MemberDiscount m = (MemberDiscount)manager.getDiscountByCode(code);
			DiscountCode.setText(m.getDiscountCode());
			DiscountDescription.setText(m.getDiscountDescription());
			Percent.setText(Integer.toString(m.getPercent()));
		}else{
			setTitle("Ocassional Discount");
			Applicable.setText("A");
			OcassionalDiscount o = (OcassionalDiscount)manager.getDiscountByCode(code);
			DiscountCode.setText(o.getDiscountCode());
			DiscountDescription.setText(o.getDiscountDescription());
			Percent.setText(Integer.toString(o.getPercent()));
			StartDate.setVisible(false);
			((JComponent)startDatePicker).setVisible(true);
			
			Calendar cal = Calendar.getInstance();
			cal.setTime(o.getStartDate());;
			startDatePicker.getModel().setDate(cal.get(Calendar.YEAR), cal.get(Calendar.MONTH), cal.get(Calendar.DATE));
			Period.setDocument(new IntDocument());
			Period.setText(Integer.toString(o.getPeriod()));
		}
		DiscountCode.setEditable(false);
	}
	
	private void createTopButton(){
		rdbtnMemDisc = new JRadioButton("Member Discount");
		rdbtnMemDisc.addItemListener(new ItemListener() {
			
			@Override
			public void itemStateChanged(ItemEvent e) {
				// TODO Auto-generated method stub
				if(e.getStateChange() == ItemEvent.SELECTED){
					Applicable.setText("M");
					StartDate.setText("ALWAYS");
					StartDate.setVisible(true);
					((JComponent)startDatePicker).setVisible(false);
					Period.setEditable(false);
					Period.setText("ALWAYS");
					ifMember = true;
				}
				
					
			}
		});
		
		rdbtnMemDisc.setFont(new Font("Tahoma", Font.PLAIN, 15));
		rdbtnMemDisc.setBounds(6, 7, 160, 23);
		contentPanel.add(rdbtnMemDisc);

		rdbtnOcaDisc = new JRadioButton("Ocassional Discount");
		rdbtnOcaDisc.setFont(new Font("Tahoma", Font.PLAIN, 15));
		rdbtnOcaDisc.setBounds(187, 7, 200, 23);
		contentPanel.add(rdbtnOcaDisc);
		rdbtnOcaDisc.addItemListener(new ItemListener() {
			
			@Override
			public void itemStateChanged(ItemEvent e) {
				// TODO Auto-generated method stub
				if(e.getStateChange() == ItemEvent.SELECTED){
					Applicable.setText("A");
				    StartDate.setVisible(false);
				    Period.setText(null);
				    ((JComponent)startDatePicker).setVisible(true);
				    Calendar d = Calendar.getInstance();
				    startDatePicker.getModel().setDate(d.get(Calendar.YEAR),d.get(Calendar.MONTH),d.get(Calendar.DATE));
				    Period.setEditable(true);
				    ifMember = false;
				}
			}
		});

		ButtonGroup group = new ButtonGroup();
		group.add(rdbtnMemDisc);
		group.add(rdbtnOcaDisc);
	}

	private void initGUI() {
		setBounds(100, 100, 450, 300);
		setModal(true);
		setLocationRelativeTo(null);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(null);

		JLabel lblDiscountcode = new JLabel("Discount Code:");
		lblDiscountcode.setBounds(6, 38, 100, 25);
		contentPanel.add(lblDiscountcode);

		DiscountCode = new JTextField();
		DiscountCode.setBounds(140, 38, 200, 25);
		DiscountCode.setDocument(new StringDocument());
		contentPanel.add(DiscountCode);
		DiscountCode.setColumns(10);

		JLabel lblDiscountDescription = new JLabel("Discount Description :");
		lblDiscountDescription.setBounds(6, 63, 125, 25);
		contentPanel.add(lblDiscountDescription);

		DiscountDescription = new JTextField();
		DiscountDescription.setBounds(140, 63, 200, 25);
		DiscountDescription.setDocument(new StringDocument());
		contentPanel.add(DiscountDescription);
		DiscountDescription.setColumns(10);

		JLabel lblPercent = new JLabel("Percent :");
		lblPercent.setBounds(6, 88, 99, 25);
		contentPanel.add(lblPercent);
		
		Percent = new JTextField();
		Percent.setBounds(140, 88, 200, 25);
		Percent.setColumns(10);
		contentPanel.add(Percent);
		
		JLabel lblStartDate = new JLabel("Start Date :");
		lblStartDate.setBounds(6, 114, 99, 28);
		contentPanel.add(lblStartDate);
		
		startDatePicker = new JDateComponentFactory().createJDatePicker();
		startDatePicker.setTextEditable(false);
		startDatePicker.setShowYearButtons(true);
		((JComponent)startDatePicker).setBounds(140,114,200,28);
		contentPanel.add((JComponent)startDatePicker);
		
		StartDate = new JTextField();
		StartDate.setText("ALWAYS");
		StartDate.setEditable(false);
		StartDate.setBounds(140, 114, 200, 28);
		contentPanel.add(StartDate);
		StartDate.setColumns(10);

		JLabel lblPeriod = new JLabel("Period :");
		lblPeriod.setBounds(6, 143, 99, 25);
		contentPanel.add(lblPeriod);
		
		Period = new JTextField();
		Period.setBounds(140, 143, 200, 25);
		contentPanel.add(Period);
		Period.setColumns(10);

		JLabel lblApplicable = new JLabel("Applicable :");
		lblApplicable.setBounds(6, 170, 109, 25);
		contentPanel.add(lblApplicable);
		
		Applicable = new JTextField();
		Applicable.setBounds(140, 170, 200, 25);
		contentPanel.add(Applicable);
		Applicable.setColumns(10);
		Applicable.setEditable(false);
		
		Percent.setDocument(new IntDocument(2));

		JButton btnCancel = new JButton("Cancel");
		btnCancel.setBounds(250, 200, 89, 25);
		btnCancel.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent arg0) {
				// TODO Auto-generated method stub
				dispose();
			}
		});
		contentPanel.add(btnCancel);

	}
	
	private void createAddButton(){
		JButton ADDButton = new JButton("ADD");
		ADDButton.setBounds(100, 200, 89, 25);
		contentPanel.add(ADDButton);
		ADDButton.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				try {
					if (valifyData()){
						if(ifMember){
							manager.addMemberDiscount(DiscountCode.getText(), DiscountDescription.getText(), Util.castInt(Percent.getText()), Applicable.getText());
						}else{
							Calendar cal = (Calendar)startDatePicker.getModel().getValue();
							//Date date = new Date(startDatePicker.getModel().getYear()-1900,startDatePicker.getModel().getMonth(),startDatePicker.getModel().getDay());
							manager.addOcassionalDiscount(DiscountCode.getText(), DiscountDescription.getText(),cal.getTime(), Util.castInt(Period.getText()),Util.castInt(Percent.getText()), Applicable.getText());
						}
						manager.getStoreWindow().getDiscountListPanel().refreshTable();
						setVisible(false);
						dispose();
						
					}
						
				} catch (DataInputException e1) {
					// TODO Auto-generated catch block
					//e1.printStackTrace();
					JOptionPane.showMessageDialog(getParent(), e1.getMessage(),"Error",JOptionPane.WARNING_MESSAGE);
				}
			}
		});

	}

	private void createModiyButton(){
		JButton MButton = new JButton("MODIFY");
		MButton.setBounds(30, 200, 89, 25);
		contentPanel.add(MButton);
		MButton.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				try {
					if(valifyData()){
						if(ifMember){
							manager.modifyMemberDiscount(DiscountCode.getText(), DiscountDescription.getText(), Util.castInt(Percent.getText()));
						}else{
							Calendar cal = (Calendar)startDatePicker.getModel().getValue();
							//Date date = new Date(startDatePicker.getModel().getYear()-1900,startDatePicker.getModel().getMonth(),startDatePicker.getModel().getDay());
							manager.modifyOcassionalDiscount(DiscountCode.getText(), DiscountDescription.getText(), cal.getTime(), Util.castInt(Period.getText()), Util.castInt(Percent.getText()));
						}
						manager.getStoreWindow().getDiscountListPanel().refreshTable();
						setVisible(false);
						dispose();
					}
				} catch (DataInputException e1) {
					// TODO Auto-generated catch block
					//e1.printStackTrace();
					JOptionPane.showMessageDialog(getParent(),e1.getMessage(),"Error",JOptionPane.WARNING_MESSAGE);
				}
			}
		});

	}

	private void createDeleteButton(){
		JButton btnDelete = new JButton("DELETE");
		btnDelete.setBounds(140, 200, 89, 25);
		btnDelete.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent arg0) {
				// TODO Auto-generated method stub
				deleteVendorMouseClicked();
				
			}
		});
		contentPanel.add(btnDelete);
	}

	private boolean valifyData() throws DataInputException{
		if(DiscountCode.getText().isEmpty()){
			throw new DataInputException("Code is void");
		}else if(DiscountDescription.getText().isEmpty()){
			throw new DataInputException("Description is void");
		}else if(Percent.getText().isEmpty()){
			throw new DataInputException("Percemt code is void");
		}else if(StartDate.getText().isEmpty()){
			throw new DataInputException("Start date code is void");
		}else if(Period.getText().isEmpty()){
			throw new DataInputException("Period is void");
		}
		return true;
	}

	private void deleteVendorMouseClicked() {
		String code = DiscountCode.getText();
		String msg = "The discount '" + code + "' will be deleted";
		int n = JOptionPane.showConfirmDialog(this, msg, "Confirmation",JOptionPane.YES_NO_OPTION);
	    if (n == 0){
			manager.deleteDiscount(code);
			manager.getStoreWindow().getDiscountListPanel().refreshTable();
			dispose();
	    }
	}
}
