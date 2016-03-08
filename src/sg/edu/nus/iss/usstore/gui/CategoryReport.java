package sg.edu.nus.iss.usstore.gui;
import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.RowSorter.SortKey;
import javax.swing.SortOrder;
import javax.swing.WindowConstants;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableCellRenderer;
import javax.swing.table.TableColumn;
import javax.swing.table.TableModel;
import javax.swing.table.TableRowSorter;

import sg.edu.nus.iss.usstore.domain.Category;
import sg.edu.nus.iss.usstore.domain.Vendor;


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
public class CategoryReport extends javax.swing.JFrame {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JScrollPane catReportScrollPane;
	private JTable CategoryListReportTable;
	
	private static final int num_col = 3;
	
	private ArrayList<Category> objList;
	private Object[][] objData;
	private String[] columns;
	private StoreApplication manager;
	
	public CategoryReport(StoreApplication manager) {
		super("Category Report");
		this.manager=manager;
		this.objList=manager.getCategoryList();
		columns = new String[num_col];
		objData = new Object[objList.size()][num_col];
		for(int i=0;i<objList.size();i++)
		{
			objData[i][0] = objList.get(i).getCode();
			objData[i][1] = objList.get(i).getName();
			String listStr = "";
			for(Vendor v:objList.get(i).getVendorList())
			{
				listStr = listStr+ v.getName() + " , ";
			}
			if(listStr.length() == 0)
				objData[i][2] = listStr;
			else
				objData[i][2] = listStr.substring(0, listStr.length()-3);
		}
		columns[0]="ID";
		columns[1]="Name";
		columns[2]="Vendor List";
		
		initGUI();
	}
	
	private void initGUI() {
		try {
			setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
			{
				catReportScrollPane = new JScrollPane();
				getContentPane().add(catReportScrollPane, BorderLayout.CENTER);
				catReportScrollPane.setPreferredSize(new java.awt.Dimension(697, 313));
				catReportScrollPane.setAutoscrolls(true);
				{
					TableModel CategoryListReportTableModel = new DefaultTableModel(objData,columns)
					{

						/**
						 * 
						 */
						private static final long serialVersionUID = 1L;

						@Override
						public boolean isCellEditable(int arg0, int arg1) {
							// TODO Auto-generated method stub
							return false;
						}
						
					};
					CategoryListReportTable = new JTable();
					catReportScrollPane.setViewportView(CategoryListReportTable);
					CategoryListReportTable.setModel(CategoryListReportTableModel);
					TableRowSorter<TableModel> sorter = new TableRowSorter<TableModel>(CategoryListReportTable.getModel());
					CategoryListReportTable.setRowSorter(sorter);
					List<SortKey> sortKeys = new ArrayList<SortKey>();
					sortKeys.add(new SortKey(0, SortOrder.ASCENDING));
					CategoryListReportTable.getRowSorter().setSortKeys(sortKeys);
				}
				Dimension size = CategoryListReportTable.getPreferredScrollableViewportSize();
				CategoryListReportTable.setPreferredScrollableViewportSize(new Dimension(Math.min(getPreferredSize().width, size.width), size.height));
			}
			resizeTableColumnWidth(CategoryListReportTable);
			pack();
			setLocationRelativeTo(null);
			this.addWindowListener(new WindowAdapter() {
				@SuppressWarnings("deprecation")
				public void windowClosed(WindowEvent evt) {
					System.out.println("this.windowClosed, event="+evt);
					//TODO add your code for this.windowClosed
					manager.getStoreWindow().setEnabled(true);
					manager.getStoreWindow().setVisible(true);
				}
			});
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	private void resizeTableColumnWidth(JTable table)
	{
		for (int column = 0; column < table.getColumnCount(); column++)
		{
		    TableColumn tableColumn = table.getColumnModel().getColumn(column);
		    int preferredWidth = tableColumn.getMinWidth();
		    int maxWidth = tableColumn.getMaxWidth();

		    for (int row = 0; row < table.getRowCount(); row++)
		    {
		        TableCellRenderer cellRenderer = table.getCellRenderer(row, column);
		        Component c = table.prepareRenderer(cellRenderer, row, column);
		        int width = c.getPreferredSize().width + table.getIntercellSpacing().width;
		        preferredWidth = Math.max(preferredWidth, width);

		        //  We've exceeded the maximum width, no need to check other rows

		        if (preferredWidth >= maxWidth)
		        {
		            preferredWidth = maxWidth;
		            break;
		        }
		    }
		    TableCellRenderer headerRenderer = table.getTableHeader().getDefaultRenderer(); 
		    Component headerComp = headerRenderer.getTableCellRendererComponent(table, tableColumn.getHeaderValue(), false, false, 0, column);
		    tableColumn.setPreferredWidth(Math.max(preferredWidth,headerComp.getMaximumSize().width));
		}
	}
}
