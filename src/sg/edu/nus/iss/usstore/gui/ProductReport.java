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

import sg.edu.nus.iss.usstore.domain.Product;


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
public class ProductReport extends javax.swing.JFrame {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JScrollPane prodReportScrollPane;
	private JTable productListReportTable;
	
	private static final int num_col = 8;
	
	private ArrayList<Product> objList;
	private Object[][] objData;
	private String[] columns;
	
	private StoreApplication manager;
	
	public ProductReport(StoreApplication manager) {
		super("Product Report");
		this.manager=manager;
		this.objList=manager.getProductList();
		columns = new String[num_col];
		objData = new Object[objList.size()][num_col];
		for(int i=0;i<objList.size();i++)
		{
			objData[i][0] = objList.get(i).getProductId();
			objData[i][1] = Long.valueOf(objList.get(i).getBarCodeNumber());
			objData[i][2] = objList.get(i).getName();
			objData[i][3] = objList.get(i).getCategory().getName();
			objData[i][4] = objList.get(i).getPrice();
			objData[i][5] = objList.get(i).getQuantityAvailable();
			objData[i][6] = objList.get(i).getBriefDescription();
			objData[i][7] = objList.get(i).getReorderQuantity();
		}
		columns[0]="ID";
		columns[1]="BarCode";
		columns[2]="Name";
		columns[3]="Category";
		columns[4]="Price";
		columns[5]="Qty Avail";
		columns[6]="Desc";
		columns[7]="Reorder Qty";
		
		initGUI();
	}
	
	private void initGUI() {
		try {
			setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
			{
				prodReportScrollPane = new JScrollPane();
				getContentPane().add(prodReportScrollPane, BorderLayout.CENTER);
				prodReportScrollPane.setPreferredSize(new java.awt.Dimension(697, 313));
				prodReportScrollPane.setAutoscrolls(true);
				{
					TableModel productListReportTableModel = new DefaultTableModel(objData,columns)
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

						@Override
						public Class<?> getColumnClass(int colNum) {
							// TODO Auto-generated method stub
							switch(colNum)
							{
							case 0: return String.class;
							case 1: return Long.class;
							case 2: return String.class;
							case 3: return String.class;
							case 4: return Double.class;
							case 5: return Integer.class;
							case 6: return String.class;
							case 7: return Integer.class;
							default: return String.class;
							}
						}
					};
					productListReportTable = new JTable();
					prodReportScrollPane.setViewportView(productListReportTable);
					productListReportTable.setModel(productListReportTableModel);
					TableRowSorter<TableModel> sorter = new TableRowSorter<TableModel>(productListReportTable.getModel());
					productListReportTable.setRowSorter(sorter);
					List<SortKey> sortKeys = new ArrayList<SortKey>();
					sortKeys.add(new SortKey(3, SortOrder.ASCENDING));
					productListReportTable.getRowSorter().setSortKeys(sortKeys);
				}
				Dimension size = productListReportTable.getPreferredScrollableViewportSize();
				productListReportTable.setPreferredScrollableViewportSize(new Dimension(Math.min(getPreferredSize().width, size.width), size.height));
			}
			resizeTableColumnWidth(productListReportTable);
			pack();
			this.setSize(900, 313);
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
