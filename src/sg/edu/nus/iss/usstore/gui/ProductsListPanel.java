package sg.edu.nus.iss.usstore.gui;

import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.ListSelectionModel;
import javax.swing.RowFilter;
import javax.swing.SwingConstants;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableRowSorter;

import sg.edu.nus.iss.usstore.domain.*;
import sg.edu.nus.iss.usstore.util.TableColumnAdjuster;



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
/**
* cardName: productList
* @ Team FT2 - XIE JIABAO
*/
public class ProductsListPanel extends JPanel{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private final String[] columnNames = {"Id","Name","Description","Price","Quantity"};
	private JButton modifyButton;
	private JButton deleteButton;
	private JTextField filterText;
	private JTable productTable;
	private DefaultTableModel tableModel;
	private TableRowSorter<DefaultTableModel> sorter;
	private StoreApplication manager;
	
	public ProductsListPanel(StoreApplication manager){
		this.manager = manager;
		setLayout(new BorderLayout());
		add("North",createTopPanel());
		add("Center",createMiddlePanel(loadTableData(manager.getProductList())));
		add("South",createBottomPanel());
		setVisible(true);
	}
	
	private Object[][] loadTableData(ArrayList<Product> products){
		Object[][] data =  new Object[products.size()][5];
		Product p;
		for(int i=0;i<products.size();i++){
			p = products.get(i);
			data[i][0] = p.getProductId();
			data[i][1] = p.getName();
			data[i][2] = p.getBriefDescription();
			data[i][3] = p.getPrice();
			data[i][4] = p.getQuantityAvailable();
		}
		return data;
	}
	
	private JPanel createTopPanel(){
		JPanel panel = new JPanel(new FlowLayout(FlowLayout.CENTER));
		JLabel label = new JLabel("Product List");
		label.setFont(new Font("JPanel",Font.BOLD,26));
		panel.add(label);	
		return panel;
	}
	
	private Container createMiddlePanel(Object[][] data){
//		String[] columnNames = {"Id","Name","Category","Price","Quality"};
//		Object[][] data = {
//				{"1","cat","animal","$123","5"},
//				{"2","dog","animal","$123","5"},
//				{"3","cat","animal","$123","5"}
//		};
		
		JPanel panel = new JPanel(new GridBagLayout());
		GridBagConstraints c = new GridBagConstraints();
		c.fill = GridBagConstraints.BOTH;
		
		tableModel = new DefaultTableModel(data,columnNames){
			/**
			 * 
			 */
			private static final long serialVersionUID = 1L;
			@Override
			public boolean isCellEditable(int row, int column){
				return false;
			}
			@SuppressWarnings({ "unchecked", "rawtypes" })
			@Override
			public Class getColumnClass(int column){
				Class returnValue;
				if ((column >= 0) && (column < getColumnCount())) {  
		            returnValue = getValueAt(0, column).getClass();  
		        } else {  
		            returnValue = Object.class;  
		        }  
				return returnValue;
			}
		};
		productTable = new JTable(tableModel);
		sorter = new TableRowSorter<DefaultTableModel>(tableModel);
		productTable.setRowSorter(sorter);
		productTable.setAutoResizeMode(JTable.AUTO_RESIZE_ALL_COLUMNS);
		setTableFormat();
		productTable.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		productTable.getSelectionModel().addListSelectionListener(new ListSelectionListener() {
			
			@Override
			public void valueChanged(ListSelectionEvent arg0) {
				// TODO Auto-generated method stub
				if(productTable.getSelectionModel().isSelectionEmpty()){
					modifyButton.setEnabled(false);
					deleteButton.setEnabled(false);
				}else{
					modifyButton.setEnabled(true);
					deleteButton.setEnabled(true);
				}
			}
		});
		productTable.addMouseListener(new MouseListener() {
			
			@Override
			public void mouseReleased(MouseEvent arg0) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void mousePressed(MouseEvent arg0) {
				// TODO Auto-generated method stub
				int i = productTable.getSelectedRow();
				if(arg0.getClickCount() == 2 && i>=0){
					int rowIndex = productTable.convertRowIndexToModel(i);
					int columnIndex = productTable.getColumnModel().getColumnIndex("Id");
					String id = (String)tableModel.getValueAt(rowIndex, columnIndex);
					ProductDialog d = new ProductDialog(manager,"Modify Product", id);
					d.setVisible(true);
				}
			}
			
			@Override
			public void mouseExited(MouseEvent arg0) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void mouseEntered(MouseEvent arg0) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void mouseClicked(MouseEvent arg0) {
				// TODO Auto-generated method stub
				
			}
		});
		//productTable.setFillsViewportHeight(true);
		productTable.setAutoCreateRowSorter(false);
		
		JLabel label = new JLabel(" Filter Search: ",SwingConstants.TRAILING);
		//label.setBorder(BorderFactory.createLineBorder(Color.black));
		
		filterText = new JTextField(10);
		filterText.getDocument().addDocumentListener(new DocumentListener() {
			
			@Override
			public void removeUpdate(DocumentEvent arg0) {
				// TODO Auto-generated method stub
				newFilter();
			}
			
			@Override
			public void insertUpdate(DocumentEvent arg0) {
				// TODO Auto-generated method stub
				newFilter();
			}
			
			@Override
			public void changedUpdate(DocumentEvent arg0) {
				// TODO Auto-generated method stub
				newFilter();
			}
		});
		label.setLabelFor(filterText);
		
		JButton b = new JButton("RELOAD");
		b.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				filterText.setText("");
				refreshTable();
			}
		});
		
		JScrollPane p = new JScrollPane(productTable);
		
		c.gridx = 0;
		c.gridy = 0;
		c.weightx = 0;
		panel.add(label,c);
		c.gridx = 1;
		c.weightx = 1;
		panel.add(filterText,c);
		c.gridx = 2;
		c.weightx = 0;
		panel.add(b,c);
		c.gridx = 0;
		c.gridy = 1;
		c.weightx = 1;
		c.gridwidth = 3;
		panel.add(p,c);
		
		return panel;
	}
	
	private JPanel createBottomPanel(){
		JPanel p = new JPanel();
		JButton b = new JButton("Add");
		b.addActionListener(new ActionListener() {
			 
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				ProductDialog d = new ProductDialog(manager,"Add Product");				
				d.setVisible(true);
			}
		});
		p.add(b);
		
		modifyButton = new JButton("Modify");
		modifyButton.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent arg0) {
				// TODO Auto-generated method stub
				int rowIndex = productTable.convertRowIndexToModel(productTable.getSelectedRow());
				int columnIndex = productTable.getColumnModel().getColumnIndex("Id");
				String id = (String)tableModel.getValueAt(rowIndex, columnIndex);
				//int index = Integer.parseInt(id.substring(4));
				ProductDialog d = new ProductDialog(manager,"Modify Product", id);
				d.setVisible(true);
			}
		});
		modifyButton.setEnabled(false);
		p.add(modifyButton);
		
		deleteButton = new JButton("Delete");
		deleteButton.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent arg0) {
				deleteVendorMouseClicked(arg0);
				
			}
		});
		deleteButton.setEnabled(false);
		p.add(deleteButton);
		
		b = new JButton("Check Threshold");
		b.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent arg0) {
				// TODO Auto-generated method stub
				manager.getStoreWindow().changeCard("checkInventory");
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
	
	private void setTableFormat(){
		TableColumnAdjuster tca = new TableColumnAdjuster(productTable);
		tca.setColumnHeaderIncluded(true);
		tca.setColumnDataIncluded(true);
		//tca.setOnlyAdjustLarger(true);
		tca.adjustColumns();
	}
	
	private void newFilter() {
        RowFilter<DefaultTableModel, Object> rf = null;
        //If current expression doesn't parse, don't update.
        try {
            rf = RowFilter.regexFilter(filterText.getText());
        } catch (java.util.regex.PatternSyntaxException e) {
            return;
        }
        sorter.setRowFilter(rf);
        
        //sorter.setRowFilter(RowFilter.regexFilter(filterText.getText()));
    }
	
	public void refreshTable(){
		tableModel.setDataVector(loadTableData(manager.getProductList()), columnNames);
		tableModel.fireTableDataChanged();
		setTableFormat();
	}

	public JTable getProductTable() {
		return productTable;
	}

	public DefaultTableModel getTableModel() {
		return tableModel;
	}
	
	private boolean validDel(String productId){
		boolean result = true;
		
		for (Transaction trans : manager.getTransactionList()){
			for( TransactionItem ti : trans.getItemList()){
				if(ti.getProduct() == manager.getProductById(productId)){
					result = false;
					break;
				}
			}
		}
		
		return result;
	}

	private void deleteVendorMouseClicked(ActionEvent evt) {

		int rowIndex = productTable.convertRowIndexToModel(productTable.getSelectedRow());
		int columnIndex = productTable.getColumnModel().getColumnIndex("Id");
		String id = (String)tableModel.getValueAt(rowIndex, columnIndex);
		
		if(validDel(id)){
			String msg = "The product '" + id + "' will be deleted";
			int n = JOptionPane.showConfirmDialog(this, msg, "Confirmation",JOptionPane.YES_NO_OPTION);
	       	if (n == 0){
				manager.deleteProduct(id);
				this.refreshTable();
	       	}
		}
		else{
			String msg = "This product `"+ id + "` is associated with some transaction";
       		JOptionPane.showMessageDialog(this, msg, "Alert",JOptionPane.WARNING_MESSAGE);
		}
	}
}
