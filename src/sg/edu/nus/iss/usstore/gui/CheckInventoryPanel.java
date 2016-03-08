package sg.edu.nus.iss.usstore.gui;

import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.ListSelectionModel;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.DefaultTableModel;

import sg.edu.nus.iss.usstore.domain.Product;
import sg.edu.nus.iss.usstore.util.TableColumnAdjuster;

/*
 * cardName: checkInventory
 * @ Team FT2 - XIE JIABAO
 */

public class CheckInventoryPanel extends JPanel{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private final String[] columnNames = {"Id","Name","Available Quantity","Threshold","Order Quantity"};
	private StoreApplication manager;
	private JTable table;
	private DefaultTableModel tableModel;
	//private JButton fire = new JButton("Fire");
	
	public CheckInventoryPanel(StoreApplication manager){
		this.manager = manager;
		setLayout(new BorderLayout());
		add("North",createNorthPanel());
		add("Center",createCenterPanel());
		add("South",createSouthPanel());
		setVisible(true);
		//fire.setEnabled(false);
	}
	
	public JPanel createNorthPanel(){
		JPanel p = new JPanel(new FlowLayout());
		JLabel label = new JLabel("Replenish Inventory List");
		label.setFont(new Font("JPanel",Font.BOLD,26));
		p.add(label);
		return p;
	}
	
	public Container createCenterPanel(){
		tableModel = new DefaultTableModel(loadTableData(manager.checkInventory()),columnNames){
			/**
			 * 
			 */
			private static final long serialVersionUID = 1L;
			@Override
			public boolean isCellEditable(int row,int column){
				return false;
			}
			@Override
			public Class getColumnClass(int column){
				Class returnValue;
				if(column>=0 && column<getColumnCount()){
					returnValue = getValueAt(0, column).getClass();
				}else{
					returnValue = Object.class;
				}
				return returnValue;
			}
		};
		
		table = new JTable(tableModel);
		table.setAutoResizeMode(JTable.AUTO_RESIZE_ALL_COLUMNS);
		TableColumnAdjuster tca = new TableColumnAdjuster(table);
		tca.setColumnHeaderIncluded(true);
		tca.setColumnDataIncluded(true);
		tca.setOnlyAdjustLarger(true);
		tca.adjustColumns();
		table.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
//		table.getSelectionModel().addListSelectionListener(new ListSelectionListener() {
//			
//			@Override
//			public void valueChanged(ListSelectionEvent e) {
//				// TODO Auto-generated method stub
//				if(table.getSelectionModel().isSelectionEmpty()){
//					fire.setEnabled(false);
//				}else{
//					fire.setEnabled(true);
//				}
//			}
//
//		});
		table.setFillsViewportHeight(true);
		table.setAutoCreateRowSorter(true);
		JScrollPane p = new JScrollPane(table);
		return p;
	}
	
	private JPanel createSouthPanel(){
		JPanel p = new JPanel(new FlowLayout());
//		fire.addActionListener(new ActionListener() {
//			
//			@Override
//			public void actionPerformed(ActionEvent arg0) {
//				// TODO Auto-generated method stub
//				int rowIndex = table.convertRowIndexToModel(table.getSelectedRow());
//				table.remove(rowIndex);
//			}
//		});
//		p.add(fire);
		JButton b = new JButton("Generate Order List");
		b.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				OrderListDialog d = new OrderListDialog(manager);
				d.setVisible(true);
			}
		});
		p.add(b);
		
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
	
	private Object[][] loadTableData(ArrayList<Product> products){
		int length = products.size();
		Object[][] data;
		if(length==0){
			data = new Object[0][5];
		}else{
			data =  new Object[length][5];
			for(int i=0;i<length;i++){
				data[i][0] = products.get(i).getProductId();
				data[i][1] = products.get(i).getName();
				data[i][2] = products.get(i).getQuantityAvailable();
				data[i][3] = products.get(i).getReorderQuantity();
				data[i][4] = products.get(i).getOrderQuantity();
			}
		}
		return data;
	}
}
