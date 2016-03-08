package sg.edu.nus.iss.usstore.gui;


import sg.edu.nus.iss.usstore.domain.Vendor;
import sg.edu.nus.iss.usstore.domain.Category;
import sg.edu.nus.iss.usstore.util.StringDocument;

import java.awt.Color;
import java.util.ArrayList;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JOptionPane;
import javax.swing.ListSelectionModel;
import javax.swing.table.DefaultTableModel;
public class VendorDialog extends javax.swing.JDialog {
	
/**
 * 
 * @author Avishek Kar Deb Barman
 *
 */

	private static final long serialVersionUID = 1L;
	private final String[] columnNames = new String [] {"Name", "Description", "Preference"};
			
	private StoreApplication manager;

    private String VendorName = new String();
    private String VendorDescription  = new String();
    private Category selectedCategory;
    private ArrayList<Category> CategoryList = new ArrayList<Category>();
    //private ArrayList<Vendor> UI_VendorList = new ArrayList<Vendor>();
    private DefaultTableModel tableModel = 
    		new javax.swing.table.DefaultTableModel(new Object [][] { },columnNames) {
        /**
		 * 
		 */
		private static final long serialVersionUID = 1L;
		public boolean isCellEditable(int rowIndex, int columnIndex) {
            return false;
        }
    };
    
    public VendorDialog(StoreApplication manager, String categoryCode) {
    	this.manager = manager;
    	
        initComponents();
        
        initData(categoryCode);
        
    }

    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jScrollPane1 = new javax.swing.JScrollPane();
        T_SSA_VendorTable = new javax.swing.JTable();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        TF_SSA_VendorName = new javax.swing.JTextField();
        TF_SSA_VendorName.setDocument(new StringDocument(50));
        TF_SSA_VendorDescription = new javax.swing.JTextField();
        TF_SSA_VendorDescription.setDocument(new StringDocument(50));
        BT_SSA_AddNewVendor = new javax.swing.JButton();
        BT_SSA_UpdateVendor = new javax.swing.JButton();
        BT_SSA_DeleteVendor = new javax.swing.JButton();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        TF_CategoryName = new javax.swing.JTextField();
        TF_CategoryCode = new javax.swing.JTextField();
        COMBO_Category = new javax.swing.JComboBox<String>();
        BT_SSA_UpVendor = new javax.swing.JButton();
        BT_SSA_DownVendor = new javax.swing.JButton();
        BT_SSA_Close = new javax.swing.JButton();

        setTitle("Vendor Manager");
        //setAlwaysOnTop(true);
        setLocationRelativeTo(null);
        setBounds(new java.awt.Rectangle(310, 100, 600, 400));
        setResizable(false);

        T_SSA_VendorTable.setFont(new java.awt.Font("Verdana", 0, 14)); // NOI18N
        T_SSA_VendorTable.setForeground(new java.awt.Color(51, 51, 51));
        T_SSA_VendorTable.setModel(tableModel);
        T_SSA_VendorTable.setSelectionBackground(Color.LIGHT_GRAY);
        T_SSA_VendorTable.getSelectionModel().setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        T_SSA_VendorTable.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                T_SSA_VendorTableMouseClicked(evt);
            }
        });
        T_SSA_VendorTable.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                T_SSA_VendorTableKeyPressed(evt);
            }
            public void keyReleased(java.awt.event.KeyEvent evt) {
                T_SSA_VendorTableKeyReleased(evt);
            }
        });
        
        jScrollPane1.setViewportView(T_SSA_VendorTable);

        jLabel1.setFont(new java.awt.Font("Verdana", 0, 12)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(51, 51, 51));
        jLabel1.setText("Vendor Name");

        jLabel2.setFont(new java.awt.Font("Verdana", 0, 12)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(51, 51, 51));
        jLabel2.setText("Description");

        TF_SSA_VendorName.setFont(new java.awt.Font("Verdana", 0, 12)); // NOI18N
        TF_SSA_VendorName.setForeground(new java.awt.Color(51, 51, 51));

        TF_SSA_VendorDescription.setFont(new java.awt.Font("Verdana", 0, 12)); // NOI18N
        TF_SSA_VendorDescription.setForeground(new java.awt.Color(51, 51, 51));

        BT_SSA_AddNewVendor.setFont(new java.awt.Font("Verdana", 0, 12)); // NOI18N
        BT_SSA_AddNewVendor.setForeground(new java.awt.Color(51, 51, 51));
        BT_SSA_AddNewVendor.setText("Add New");
        BT_SSA_AddNewVendor.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                BT_SSA_AddNewVendorMouseClicked(evt);
            }
        });

        BT_SSA_UpdateVendor.setFont(new java.awt.Font("Verdana", 0, 12)); // NOI18N
        BT_SSA_UpdateVendor.setForeground(new java.awt.Color(51, 51, 51));
        BT_SSA_UpdateVendor.setText("Update");
        BT_SSA_UpdateVendor.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                BT_SSA_UpdateVendorMouseClicked(evt);
            }
        });

        BT_SSA_DeleteVendor.setFont(new java.awt.Font("Verdana", 0, 12)); // NOI18N
        BT_SSA_DeleteVendor.setForeground(new java.awt.Color(51, 51, 51));
        BT_SSA_DeleteVendor.setText("Delete");
        BT_SSA_DeleteVendor.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                BT_SSA_DeleteVendorMouseClicked(evt);
            }
        });

        jLabel3.setFont(new java.awt.Font("Verdana", 1, 12)); // NOI18N
        jLabel3.setText("Category Name:");

        jLabel4.setFont(new java.awt.Font("Verdana", 1, 12)); // NOI18N
        jLabel4.setText("Category Code:");

        TF_CategoryName.setEditable(false);
        TF_CategoryName.setBackground(new java.awt.Color(51, 51, 51));
        TF_CategoryName.setFont(new java.awt.Font("Verdana", 2, 11)); // NOI18N
        TF_CategoryName.setForeground(new java.awt.Color(0, 255, 204));
        TF_CategoryName.setSelectedTextColor(new java.awt.Color(0, 0, 0));
        TF_CategoryName.setSelectionColor(new java.awt.Color(204, 204, 204));

        TF_CategoryCode.setEditable(false);
        TF_CategoryCode.setBackground(new java.awt.Color(51, 51, 51));
        TF_CategoryCode.setFont(new java.awt.Font("Verdana", 2, 11)); // NOI18N
        TF_CategoryCode.setForeground(new java.awt.Color(0, 255, 204));
        TF_CategoryCode.setSelectedTextColor(new java.awt.Color(0, 0, 0));
        TF_CategoryCode.setSelectionColor(new java.awt.Color(204, 204, 204));

        COMBO_Category.setFont(new java.awt.Font("Verdana", 0, 12)); // NOI18N
        COMBO_Category.setForeground(new java.awt.Color(51, 51, 51));
       
        COMBO_Category.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                COMBO_CategoryActionPerformed(evt);
            }
        });

        BT_SSA_UpVendor.setFont(new java.awt.Font("Verdana", 0, 12)); // NOI18N
        BT_SSA_UpVendor.setForeground(new java.awt.Color(51, 51, 51));
        BT_SSA_UpVendor.setText("Up");
        BT_SSA_UpVendor.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                BT_SSA_UpVendorMouseClicked(evt);
            }
        });

        BT_SSA_DownVendor.setFont(new java.awt.Font("Verdana", 0, 12)); // NOI18N
        BT_SSA_DownVendor.setForeground(new java.awt.Color(51, 51, 51));
        BT_SSA_DownVendor.setText("Down");
        BT_SSA_DownVendor.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                BT_SSA_DownVendorMouseClicked(evt);
            }
        });

        BT_SSA_Close.setFont(new java.awt.Font("Verdana", 0, 12)); // NOI18N
        BT_SSA_Close.setForeground(new java.awt.Color(51, 51, 51));
        BT_SSA_Close.setText("Close");
        BT_SSA_Close.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                BT_SSA_CloseMouseClicked(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(20, 20, 20)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(COMBO_Category, javax.swing.GroupLayout.PREFERRED_SIZE, 126, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jLabel4)
                        .addGap(18, 18, 18)
                        .addComponent(TF_CategoryCode, javax.swing.GroupLayout.PREFERRED_SIZE, 74, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addComponent(jLabel2)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(TF_SSA_VendorDescription, javax.swing.GroupLayout.PREFERRED_SIZE, 266, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addComponent(jLabel1)
                        .addGap(18, 18, 18)
                        .addComponent(TF_SSA_VendorName, javax.swing.GroupLayout.PREFERRED_SIZE, 266, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(BT_SSA_UpdateVendor, javax.swing.GroupLayout.PREFERRED_SIZE, 133, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(BT_SSA_AddNewVendor, javax.swing.GroupLayout.PREFERRED_SIZE, 133, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(BT_SSA_DeleteVendor, javax.swing.GroupLayout.PREFERRED_SIZE, 126, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(BT_SSA_DownVendor, javax.swing.GroupLayout.PREFERRED_SIZE, 122, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(BT_SSA_UpVendor, javax.swing.GroupLayout.PREFERRED_SIZE, 122, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(BT_SSA_Close, javax.swing.GroupLayout.PREFERRED_SIZE, 122, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(18, 18, 18)
                        .addComponent(jLabel3)
                        .addGap(18, 18, 18)
                        .addComponent(TF_CategoryName, javax.swing.GroupLayout.PREFERRED_SIZE, 272, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
            .addGroup(layout.createSequentialGroup()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 828, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 4, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 275, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel3)
                    .addComponent(TF_CategoryName, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel4)
                    .addComponent(TF_CategoryCode, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(COMBO_Category, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(TF_SSA_VendorName, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(BT_SSA_AddNewVendor)
                    .addComponent(BT_SSA_DeleteVendor)
                    .addComponent(BT_SSA_UpVendor))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(TF_SSA_VendorDescription, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(BT_SSA_UpdateVendor)
                    .addComponent(BT_SSA_DownVendor))
                .addGap(18, 18, 18)
                .addComponent(BT_SSA_Close)
                .addContainerGap(12, Short.MAX_VALUE))
        );

        pack();
        
        btnEnableCtl();
        setModal(true);
    }// </editor-fold>//GEN-END:initComponents

    private void BT_SSA_AddNewVendorMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_BT_SSA_AddNewVendorMouseClicked
    	if(this.init())
        {        
            if(!this.validAdd()){
            	String msg = "Duplicate vendor name is not possible";
           		JOptionPane.showMessageDialog(this, msg, "Alert",JOptionPane.WARNING_MESSAGE);
            }
            else
            {
                manager.addVendorForCategory(this.selectedCategory.getCode(), this.VendorName,this.VendorDescription);
                LoadTable();
                this.T_SSA_VendorTable.setRowSelectionInterval(
                		this.T_SSA_VendorTable.getRowCount() -1, this.T_SSA_VendorTable.getRowCount() -1);
                btnEnableCtl();
            }        
        }
    }//GEN-LAST:event_BT_SSA_AddNewVendorMouseClicked

    private void T_SSA_VendorTableMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_T_SSA_VendorTableMouseClicked
    	int selectedIndex = this.T_SSA_VendorTable.getSelectedRow();
        if(selectedIndex > -1)
        {
     	    this.TF_SSA_VendorName.setText(this.T_SSA_VendorTable.getValueAt(selectedIndex, 0).toString());
     	    this.TF_SSA_VendorDescription.setText(this.T_SSA_VendorTable.getValueAt(selectedIndex, 1).toString());
        }
        else{
        	this.TF_SSA_VendorName.setText("");
        	this.TF_SSA_VendorDescription.setText("");
        }
    	
    	btnEnableCtl();
    }//GEN-LAST:event_T_SSA_VendorTableMouseClicked

    private void T_SSA_VendorTableKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_T_SSA_VendorTableKeyPressed
    	int selectedIndex = this.T_SSA_VendorTable.getSelectedRow();
        if(selectedIndex > -1)
        {
     	    this.TF_SSA_VendorName.setText(this.T_SSA_VendorTable.getValueAt(selectedIndex, 0).toString());
     	    this.TF_SSA_VendorDescription.setText(this.T_SSA_VendorTable.getValueAt(selectedIndex, 1).toString());
        }
        else{
        	this.TF_SSA_VendorName.setText("");
        	this.TF_SSA_VendorDescription.setText("");
        }
    	
    	btnEnableCtl();
    }//GEN-LAST:event_T_SSA_VendorTableKeyPressed

    private void T_SSA_VendorTableKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_T_SSA_VendorTableKeyReleased
    	int selectedIndex = this.T_SSA_VendorTable.getSelectedRow();
        if(selectedIndex > -1)
        {
     	    this.TF_SSA_VendorName.setText(this.T_SSA_VendorTable.getValueAt(selectedIndex, 0).toString());
     	    this.TF_SSA_VendorDescription.setText(this.T_SSA_VendorTable.getValueAt(selectedIndex, 1).toString());
        }
        else{
        	this.TF_SSA_VendorName.setText("");
        	this.TF_SSA_VendorDescription.setText("");
        }
    	
    	btnEnableCtl();
    }//GEN-LAST:event_T_SSA_VendorTableKeyReleased

	private void BT_SSA_UpdateVendorMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_BT_SSA_UpdateVendorMouseClicked
    	int selectedIndex = this.T_SSA_VendorTable.getSelectedRow();
    	if(this.init())
	    {              
	    	if(!this.validUpd()){
	    		String msg = "Already exists a vendor named " + this.VendorName;
           		JOptionPane.showMessageDialog(this, msg, "Alert",JOptionPane.WARNING_MESSAGE);
	        
	        }
	    	else
            {
	    		String oldVendorName = this.tableModel.getValueAt(selectedIndex, 0).toString();
	            manager.updVendorForCategory(this.selectedCategory.getCode(), 
	            		oldVendorName,
	            		this.VendorName, 
	            		this.VendorDescription);
                LoadTable();
                this.T_SSA_VendorTable.setRowSelectionInterval(selectedIndex, selectedIndex);
                btnEnableCtl();
            } 
	    }
    }//GEN-LAST:event_BT_SSA_UpdateVendorMouseClicked

    private void BT_SSA_DeleteVendorMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_BT_SSA_DeleteVendorMouseClicked
    	
        int selectedIndex = this.T_SSA_VendorTable.getSelectedRow();
      
       	String vendorName = this.tableModel.getValueAt(selectedIndex, 0).toString();
       	String msg = "The vendor '" + vendorName + "' will be deleted";
       	int n = JOptionPane.showConfirmDialog(this, msg, "Confirmation",JOptionPane.YES_NO_OPTION);
       	if (n == 0){
       		// proceed deletion
           	manager.delVendorForCategory(this.selectedCategory.getCode(), vendorName);
           	LoadTable();
            btnEnableCtl();
       	}
       	
       
    }//GEN-LAST:event_BT_SSA_DeleteVendorMouseClicked

 
    private void COMBO_CategoryActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_COMBO_CategoryActionPerformed
        String categoryCode = this.COMBO_Category.getModel().getSelectedItem().toString();
        this.TF_SSA_VendorName.setText("");
        this.TF_SSA_VendorDescription.setText("");

        this.selectedCategory = getSelectedCategory(categoryCode);
        this.TF_CategoryCode.setText(this.selectedCategory.getCode());
        this.TF_CategoryName.setText(this.selectedCategory.getName());
            
        this.LoadTable();
        
        btnEnableCtl();
            
    }//GEN-LAST:event_COMBO_CategoryActionPerformed
    
    private void BT_SSA_UpVendorMouseClicked(java.awt.event.MouseEvent evt) {                                             
        // TODO add your handling code here:
    	int selectedIndex = this.T_SSA_VendorTable.getSelectedRow();
    	
        String upVendorName = this.tableModel.getValueAt(selectedIndex, 0).toString();
        String downVendorName = this.tableModel.getValueAt(selectedIndex - 1, 0).toString();
        manager.switchVendorPrefForCategory(this.selectedCategory.getCode(), 
        		upVendorName,
        		downVendorName);
        
        LoadTable();
        
        this.T_SSA_VendorTable.setRowSelectionInterval(selectedIndex - 1, selectedIndex - 1);

        btnEnableCtl();
    }                                            

    private void BT_SSA_DownVendorMouseClicked(java.awt.event.MouseEvent evt) {                                               
        // TODO add your handling code here:
    	int selectedIndex = this.T_SSA_VendorTable.getSelectedRow();
    	
        String upVendorName = this.tableModel.getValueAt(selectedIndex + 1, 0).toString();
        String downVendorName = this.tableModel.getValueAt(selectedIndex, 0).toString();
        manager.switchVendorPrefForCategory(this.selectedCategory.getCode(), 
        		upVendorName,
        		downVendorName);
        
        LoadTable();
        
        this.T_SSA_VendorTable.setRowSelectionInterval(selectedIndex + 1, selectedIndex + 1);
        
        btnEnableCtl();

    }                                              

    private void BT_SSA_CloseMouseClicked(java.awt.event.MouseEvent evt) {                                          
    	this.setVisible(false);
    	this.dispose();
    }      
   

    // Variables declaration - do not modify                     
    private javax.swing.JButton BT_SSA_AddNewVendor;
    private javax.swing.JButton BT_SSA_Close;
    private javax.swing.JButton BT_SSA_DeleteVendor;
    private javax.swing.JButton BT_SSA_DownVendor;
    private javax.swing.JButton BT_SSA_UpVendor;
    private javax.swing.JButton BT_SSA_UpdateVendor;
    private javax.swing.JComboBox<String> COMBO_Category;
    private javax.swing.JTextField TF_CategoryCode;
    private javax.swing.JTextField TF_CategoryName;
    private javax.swing.JTextField TF_SSA_VendorDescription;
    private javax.swing.JTextField TF_SSA_VendorName;
    private javax.swing.JTable T_SSA_VendorTable;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JScrollPane jScrollPane1;
    // End of variables declaration                      
        
    private void initData(String categoryCode){
    	
        this.CategoryList = this.manager.getCategoryList();
       
        this.selectedCategory = getSelectedCategory(categoryCode);
        this.TF_CategoryCode.setText(selectedCategory.getCode());
        this.TF_CategoryName.setText(selectedCategory.getName());
                
        this.LoadList();
        this.tableModel = (DefaultTableModel) this.T_SSA_VendorTable.getModel();//Creating Table model
        
        this.LoadTable();
    }
    
    private void LoadTable()
    {
        tableModel.setRowCount(0);
        if(this.selectedCategory.getVendorList() != null) 
	    {  
	        for(int i = 0;i < selectedCategory.getVendorList().size() ; i++)
	        {   
	            this.tableModel.addRow(
	            		new Object[]{
	            				selectedCategory.getVendorList().get(i).getName(),
	            				selectedCategory.getVendorList().get(i).getDescription(),
	            				selectedCategory.getVendorList().get(i).getPreference()
	            				}
	            		);
	        }
        }
    }
    
    
    private void LoadList()
    {
    	DefaultComboBoxModel<String> model = new DefaultComboBoxModel<String>();
    	
        for(int i = 0;i< this.CategoryList.size();i++){
        	String code = this.CategoryList.get(i).getCode();
        	model.addElement(code);
        	if (this.CategoryList.get(i) == this.selectedCategory){
        		model.setSelectedItem(code);
        	}
        		
        }
        COMBO_Category.setModel(model);
       
    }
    
    private boolean init() 
    {
        this.VendorName = this.TF_SSA_VendorName.getText().trim();
        this.VendorDescription = this.TF_SSA_VendorDescription.getText().trim();
        if(this.VendorName.isEmpty() || this.VendorDescription.isEmpty()){
        	String msg = "Vendor Name or Description should not be empty";
        	JOptionPane.showMessageDialog(this, msg, "Alert",JOptionPane.WARNING_MESSAGE);
        	return false;
        }

        return true;
    }

    private boolean validAdd() {

        boolean result = true;
    	
    	// duplicate check
        for(Vendor vendor : selectedCategory.getVendorList()){
        	if(vendor.getName().equals(this.VendorName)){
        		result = false;
            	break;
        	}
        }
        return result;
    }
    
    private boolean validUpd() {

        boolean result = true;
    	
        String selectedVendorName =  this.tableModel.getValueAt(this.T_SSA_VendorTable.getSelectedRow(),0).toString();
    	// duplicate check
        for(Vendor vendor : selectedCategory.getVendorList()){
        	// skip the one that be updated
        	if (vendor.getName().equals(selectedVendorName))continue;
        	
        	if(vendor.getName().equals(this.VendorName)){
        		result = false;
            	break;
        	}
        }
        return result;
    }
    
    private Category getSelectedCategory(String code){
    	Category result = null;
    	for(Category category : this.CategoryList){
    		if(category.getCode().equals(code)){
    			result = category;
    			break;
    		}
    	}
    	return result;
    	
    }
    
	private void btnEnableCtl(){
		int selectedIndex = this.T_SSA_VendorTable.getSelectedRow();
		if(selectedIndex > -1)
		{    	   
			this.BT_SSA_DeleteVendor.setEnabled(true);
			this.BT_SSA_UpdateVendor.setEnabled(true);
			
			this.BT_SSA_UpVendor.setEnabled(true);
			this.BT_SSA_DownVendor.setEnabled(true);
			
			if(selectedIndex == 0)
				this.BT_SSA_UpVendor.setEnabled(false);
				
			if(selectedIndex == this.T_SSA_VendorTable.getRowCount() - 1)
				this.BT_SSA_DownVendor.setEnabled(false);
					
		}else{
			this.BT_SSA_DeleteVendor.setEnabled(false);
			this.BT_SSA_UpdateVendor.setEnabled(false);
	        this.BT_SSA_UpVendor.setEnabled(false);
	        this.BT_SSA_DownVendor.setEnabled(false);
		}
	
	}

}
