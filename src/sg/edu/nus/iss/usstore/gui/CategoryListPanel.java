package sg.edu.nus.iss.usstore.gui;


import sg.edu.nus.iss.usstore.domain.Category;
import sg.edu.nus.iss.usstore.domain.Product;
import sg.edu.nus.iss.usstore.domain.Vendor;
import sg.edu.nus.iss.usstore.util.StringDocument;

import java.util.ArrayList;

import javax.swing.JOptionPane;
import javax.swing.ListSelectionModel;
import javax.swing.table.DefaultTableModel;

public class CategoryListPanel extends javax.swing.JPanel {

/**
 * 
 * @author Avishek Kar Deb Barman
 *
 */

	private static final long serialVersionUID = 1L;
	private final String[] columnNames = {"Code", "Name"};
	
	private StoreApplication manager;
	
    private String categoryName = new String();
    private String categoryCode  = new String();
    
    //private CategoryMgr CAT_MAN = new CategoryMgr();
    private ArrayList<Category> UI_CategoryList = new ArrayList<Category>();
    private DefaultTableModel tableModel = new DefaultTableModel();
    
    public CategoryListPanel(StoreApplication manager) {
    	this.manager = manager;
  
        initComponents();
        
        reloadData();
    }

    
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jScrollPane1 = new javax.swing.JScrollPane();
        T_SSA_CategoryTable = new javax.swing.JTable();
        BT_SSA_AddNewCategory = new javax.swing.JButton();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        TF_SSA_CategoryCode = new javax.swing.JTextField();
        TF_SSA_CategoryCode.setDocument(new StringDocument(3));
        TF_SSA_CategoryName = new javax.swing.JTextField();
        TF_SSA_CategoryName.setDocument(new StringDocument(50));
        BT_SSA_Update = new javax.swing.JButton();
        BT_SSA_Delete = new javax.swing.JButton();
        BT_SSA_ManageVendor = new javax.swing.JButton();
        BT_SSA_Back = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();

        setBounds(new java.awt.Rectangle(300, 100, 600, 400));   

        T_SSA_CategoryTable.setFont(new java.awt.Font("Verdana", 0, 14)); // NOI18N
        T_SSA_CategoryTable.setForeground(new java.awt.Color(12, 12, 12));
        
        T_SSA_CategoryTable.setModel(new javax.swing.table.DefaultTableModel(new Object [][] {},  columnNames ) {
            /**
			 * 
			 */
			private static final long serialVersionUID = 1L;
			@SuppressWarnings("rawtypes")
			Class[] types = new Class [] {
                java.lang.String.class, java.lang.String.class
            };
            boolean[] canEdit = new boolean [] {
                false, false
            };

            public Class<?> getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        T_SSA_CategoryTable.setAutoResizeMode(javax.swing.JTable.AUTO_RESIZE_ALL_COLUMNS);
        T_SSA_CategoryTable.setSelectionBackground(new java.awt.Color(51, 51, 51));
        T_SSA_CategoryTable.setSelectionForeground(new java.awt.Color(204, 204, 204));
        T_SSA_CategoryTable.getSelectionModel().setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        T_SSA_CategoryTable.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                T_SSA_CategoryTableMouseClicked(evt);
            }
        });
        T_SSA_CategoryTable.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                T_SSA_CategoryTableKeyPressed(evt);
            }
            public void keyReleased(java.awt.event.KeyEvent evt) {
                T_SSA_CategoryTableKeyReleased(evt);
            }
        });
        jScrollPane1.setViewportView(T_SSA_CategoryTable);

        BT_SSA_AddNewCategory.setFont(new java.awt.Font("Verdana", 0, 12)); // NOI18N
        BT_SSA_AddNewCategory.setForeground(new java.awt.Color(51, 51, 51));
        BT_SSA_AddNewCategory.setText("Add New");
        BT_SSA_AddNewCategory.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                BT_SSA_AddNewCategoryMouseClicked(evt);
            }
        });

        jLabel2.setFont(new java.awt.Font("Verdana", 0, 12)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(51, 51, 51));
        jLabel2.setText("Category Name");

        jLabel3.setFont(new java.awt.Font("Verdana", 0, 12)); // NOI18N
        jLabel3.setForeground(new java.awt.Color(51, 51, 51));
        jLabel3.setText("Category Code");

        TF_SSA_CategoryName.setFont(new java.awt.Font("Verdana", 0, 12)); // NOI18N
        TF_SSA_CategoryName.setForeground(new java.awt.Color(51, 51, 51));

        TF_SSA_CategoryCode.setFont(new java.awt.Font("Verdana", 0, 12)); // NOI18N
        TF_SSA_CategoryCode.setForeground(new java.awt.Color(51, 51, 51));

        BT_SSA_Update.setFont(new java.awt.Font("Verdana", 0, 12)); // NOI18N
        BT_SSA_Update.setForeground(new java.awt.Color(51, 51, 51));
        BT_SSA_Update.setText("Update");
        BT_SSA_Update.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                BT_SSA_UpdateMouseClicked(evt);
            }
        });

        BT_SSA_Delete.setFont(new java.awt.Font("Verdana", 0, 12)); // NOI18N
        BT_SSA_Delete.setForeground(new java.awt.Color(51, 51, 51));
        BT_SSA_Delete.setText("Delete");
        BT_SSA_Delete.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                BT_SSA_DeleteMouseClicked(evt);
            }
        });

        BT_SSA_ManageVendor.setFont(new java.awt.Font("Verdana", 0, 12)); // NOI18N
        BT_SSA_ManageVendor.setForeground(new java.awt.Color(51, 51, 51));
        BT_SSA_ManageVendor.setText("Manage Vendor");
        BT_SSA_ManageVendor.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                BT_SSA_ManageVendorMouseClicked(evt);
            }
        });

        BT_SSA_Back.setFont(new java.awt.Font("Verdana", 0, 12)); // NOI18N
        BT_SSA_Back.setForeground(new java.awt.Color(51, 51, 51));
        BT_SSA_Back.setText("Back");
        BT_SSA_Back.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                BT_SSA_BackMouseClicked(evt);
            }
        });

        jLabel1.setText("Category List");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel3)
                            .addComponent(jLabel2))
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(TF_SSA_CategoryName)
                            .addComponent(TF_SSA_CategoryCode))
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(BT_SSA_AddNewCategory, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(BT_SSA_Update, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(BT_SSA_Delete, javax.swing.GroupLayout.PREFERRED_SIZE, 131, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(BT_SSA_ManageVendor)
                                .addGap(27, 27, 27)
                                .addComponent(BT_SSA_Back, javax.swing.GroupLayout.PREFERRED_SIZE, 90, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGap(27, 27, 27))
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 86, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 770, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addContainerGap(20, Short.MAX_VALUE))))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(8, 8, 8)
                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 453, Short.MAX_VALUE)
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(BT_SSA_AddNewCategory)
                    .addComponent(BT_SSA_Delete)
                    .addComponent(TF_SSA_CategoryCode, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel3))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(TF_SSA_CategoryName, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(BT_SSA_Update)
                    .addComponent(BT_SSA_ManageVendor)
                    .addComponent(BT_SSA_Back))
                .addGap(27, 27, 27))
        );
      
        btnEnableCtl();

        //pack();
    }// </editor-fold>//GEN-END:initComponents

    private void BT_SSA_AddNewCategoryMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_BT_SSA_AddNewCategoryMouseClicked
      
        if(this.init())
        {
            if(!this.validAdd())
            {
            	String msg = "Duplicate Category ID `"+categoryCode+"`";
            	JOptionPane.showMessageDialog(this, msg, "Alert",JOptionPane.WARNING_MESSAGE);
            }
            else
            {
                this.manager.addCategory(this.categoryCode,this.categoryName, new ArrayList<Vendor>());
                reloadData();
                this.T_SSA_CategoryTable.setRowSelectionInterval(
                		this.T_SSA_CategoryTable.getRowCount() -1, this.T_SSA_CategoryTable.getRowCount() -1);
                btnEnableCtl();
            }           
        }
    }//GEN-LAST:event_BT_SSA_AddNewCategoryMouseClicked

    private void BT_SSA_UpdateMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_BT_SSA_UpdateMouseClicked
       if(this.init())
       {
           if(this.validUpd())
           {
        	   int selectRowIndex = this.T_SSA_CategoryTable.getSelectedRow();
        	   String code = this.tableModel.getValueAt(selectRowIndex,0).toString();
        	   String name = this.TF_SSA_CategoryName.getText().toString();
               this.manager.updCategory(code, name);
               reloadData();
               this.T_SSA_CategoryTable.setRowSelectionInterval(selectRowIndex, selectRowIndex);
               btnEnableCtl();
           }else{
        	   	String msg = "Category Code should not be changed";
           		JOptionPane.showMessageDialog(this, msg, "Alert",JOptionPane.WARNING_MESSAGE);
           }
       }
    }//GEN-LAST:event_BT_SSA_UpdateMouseClicked

    private void T_SSA_CategoryTableMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_T_SSA_CategoryTableMouseClicked
       int selectedIndex = this.T_SSA_CategoryTable.getSelectedRow();
       if(selectedIndex > -1)
       {
           this.TF_SSA_CategoryCode.setText(this.T_SSA_CategoryTable.getValueAt(selectedIndex, 0).toString());
           this.TF_SSA_CategoryName.setText(this.T_SSA_CategoryTable.getValueAt(selectedIndex, 1).toString());
       }
       btnEnableCtl();
    }//GEN-LAST:event_T_SSA_CategoryTableMouseClicked

    private void BT_SSA_DeleteMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_BT_SSA_DeleteMouseClicked
	   
	   	String code = this.tableModel.getValueAt(this.T_SSA_CategoryTable.getSelectedRow(),0).toString();
		if(validDel(code)){
			String msg = "Category '" + code + "' will be deleted";
			int n = JOptionPane.showConfirmDialog(this, msg, "Confirmation",JOptionPane.YES_NO_OPTION);
	       	if (n == 0){
	       		// proceed deletion
				manager.deleteCategoryByCode(code);
				reloadData();
				btnEnableCtl();
	       	}
		}else{
    	   	String msg = "This category `"+ code + "` is associated with some products";
       		JOptionPane.showMessageDialog(this, msg, "Alert",JOptionPane.WARNING_MESSAGE);
		}
    	   	
    }//GEN-LAST:event_BT_SSA_DeleteMouseClicked

    private void T_SSA_CategoryTableKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_T_SSA_CategoryTableKeyReleased
    	int selectedIndex = this.T_SSA_CategoryTable.getSelectedRow();
        if(selectedIndex > -1)
        {
            this.TF_SSA_CategoryCode.setText(this.T_SSA_CategoryTable.getValueAt(selectedIndex, 0).toString());
            this.TF_SSA_CategoryName.setText(this.T_SSA_CategoryTable.getValueAt(selectedIndex, 1).toString());
        }
        btnEnableCtl();
    }//GEN-LAST:event_T_SSA_CategoryTableKeyReleased

    private void T_SSA_CategoryTableKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_T_SSA_CategoryTableKeyPressed
    	int selectedIndex = this.T_SSA_CategoryTable.getSelectedRow();
        if(selectedIndex > -1)
        {
            this.TF_SSA_CategoryCode.setText(this.T_SSA_CategoryTable.getValueAt(selectedIndex, 0).toString());
            this.TF_SSA_CategoryName.setText(this.T_SSA_CategoryTable.getValueAt(selectedIndex, 1).toString());
        }
        btnEnableCtl();
    }//GEN-LAST:event_T_SSA_CategoryTableKeyPressed

    private void BT_SSA_ManageVendorMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_BT_SSA_ManageVendorMouseClicked
        int selectedIndex = this.T_SSA_CategoryTable.getSelectedRow();
        String categoryCode;
        
        if(this.T_SSA_CategoryTable.getRowCount() == 0){
        	String msg = "Need to have a category at least";
       		JOptionPane.showMessageDialog(this, msg, "Alert",JOptionPane.WARNING_MESSAGE);
        }else {
        
	        if(selectedIndex == -1){
	        	categoryCode = this.tableModel.getValueAt(0,0).toString();
	        }
	        else
	        { 
	        	categoryCode = this.tableModel.getValueAt(selectedIndex,0).toString();   
	        }
	        
	        VendorDialog vendorDlg = new VendorDialog(manager, categoryCode);
            vendorDlg.setVisible(true);
        }
    }//GEN-LAST:event_BT_SSA_ManageVendorMouseClicked
    
    private void BT_SSA_BackMouseClicked(java.awt.event.MouseEvent evt) {                                         
        // TODO add your handling code here:
    	manager.showMenuScreen();
    }   
    
   

    // Variables declaration - do not modify//GEN-BEGIN:variables

    private javax.swing.JButton BT_SSA_AddNewCategory;
    private javax.swing.JButton BT_SSA_Back;
    private javax.swing.JButton BT_SSA_Delete;
    private javax.swing.JButton BT_SSA_ManageVendor;
    private javax.swing.JButton BT_SSA_Update;
    private javax.swing.JTextField TF_SSA_CategoryCode;
    private javax.swing.JTextField TF_SSA_CategoryName;
    private javax.swing.JTable T_SSA_CategoryTable;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JScrollPane jScrollPane1;
    // End of variables declaration//GEN-END:variables
    
    private void reloadData() {
        
        this.UI_CategoryList = this.manager.getCategoryList();//Retriving Saved UI_CategoryList form File
        if(this.UI_CategoryList.isEmpty())
        	this.UI_CategoryList = new ArrayList<Category>();
        this.tableModel = (DefaultTableModel) this.T_SSA_CategoryTable.getModel();//Creating Table model
        
        this.LoadTable();
        
    }

    private void LoadTable()
    {
        
        tableModel.setRowCount(0);
        if(this.UI_CategoryList != null) 
        {  
            if(!this.UI_CategoryList.isEmpty())
            {
                for(int  i = 0; i < this.UI_CategoryList.size() ; i++)
                {   
                    this.tableModel.addRow(new Object[]{this.UI_CategoryList.get(i).getCode(),this.UI_CategoryList.get(i).getName()});
                }
            }
        }
        else
            System.out.println(this.UI_CategoryList);
        
        tableModel.fireTableDataChanged();
    }
    
    private boolean init() 
    {
    	this.categoryCode = this.TF_SSA_CategoryCode.getText().trim().toUpperCase();
        this.categoryName = this.TF_SSA_CategoryName.getText().trim();
       
        if(this.categoryName.isEmpty() || this.categoryCode.isEmpty()){
        	String msg = "Category Code or Name should not be empty";
       		JOptionPane.showMessageDialog(this, msg, "Alert",JOptionPane.WARNING_MESSAGE);
        }
      
        else if(this.categoryCode.length()!= 3){
        	String msg = "Category Code Should be 3 characters long";
       		JOptionPane.showMessageDialog(this, msg, "Alert",JOptionPane.WARNING_MESSAGE);
        }
        else
            return true;
        return false;
    }

    private boolean validAdd()
    {
    	boolean result = true;
    	
    	// duplicate check
        for(Category category : this.UI_CategoryList){
        	if(category.getCode().equals(this.categoryCode)){
        		result = false;
            	break;
        	}
        }
        return result;
    }
    
    private boolean validUpd()
    {
    	String originCode = this.tableModel.getValueAt(this.T_SSA_CategoryTable.getSelectedRow(),0).toString();
        return (originCode.equals(this.categoryCode));
    }
    
    private boolean validDel(String code)
    {
    	boolean result = true;
    	
    	// check whether any product in this category
        for(Product product : this.manager.getProductList()){
        	if(product.getCategory().getCode().equals(code)){
        		result = false;
            	break;
        	}
        }
        return result;
    }
    
    private void btnEnableCtl(){
    	int selectedIndex = this.T_SSA_CategoryTable.getSelectedRow();
		if(selectedIndex > -1)
		{ 	
	    	this.BT_SSA_Delete.setEnabled(true);
	    	this.BT_SSA_Update.setEnabled(true);
	    }
	    else{
		   this.BT_SSA_Delete.setEnabled(false);
		   this.BT_SSA_Update.setEnabled(false);
	    }
    }
}
