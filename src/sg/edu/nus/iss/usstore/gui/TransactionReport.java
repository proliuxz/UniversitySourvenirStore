package sg.edu.nus.iss.usstore.gui;
import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import javax.swing.JComponent;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
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

import org.jdatepicker.JDateComponentFactory;
import org.jdatepicker.JDatePicker;

import sg.edu.nus.iss.usstore.domain.Transaction;
import sg.edu.nus.iss.usstore.domain.TransactionItem;


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
public class TransactionReport extends javax.swing.JFrame {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JScrollPane transactionReportScrollPane;
	private JTable transactionListReportTable;
	
	private static final int num_col = 6;
	private static final String DATE_FORMAT = "d-MM-yyyy";
	
	private ArrayList<Transaction> objList;
	private Object[][] objData;
	private String[] columns;
	private StoreApplication manager;
	
	private JDatePicker startDatePicker;
	private JDatePicker endDatePicker;
	private JPanel datePanel;
	private Calendar startDateCal;
	private Calendar endDateCal;
	private Date startDate;
	private Date endDate;
	
	public TransactionReport(StoreApplication manager) {
		super("Transaction Report");
		this.manager=manager;
		initGUI();
		updateGUI();
	}
	
	private void initGUI() {

		startDatePicker = new JDateComponentFactory().createJDatePicker();
		startDatePicker.setTextEditable(false);
		startDatePicker.setShowYearButtons(true);
		endDatePicker = new JDateComponentFactory().createJDatePicker();
		endDatePicker.setTextEditable(false);
		endDatePicker.setShowYearButtons(true);
		datePanel = new JPanel();
		JLabel lbl_startDate = new JLabel();
		lbl_startDate.setText("Start Date: ");
		datePanel.add(lbl_startDate);
	    datePanel.add((JComponent) startDatePicker);
		JLabel lbl_endDate = new JLabel();
		lbl_endDate.setText("End Date: ");
		datePanel.add(lbl_endDate);
	    datePanel.add((JComponent) endDatePicker);
	    this.getContentPane().add(datePanel, BorderLayout.NORTH);
		
		JButton btn_getReport = new JButton("Get Report");
		btn_getReport.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent arg0) {
				// TODO Auto-generated method stub
				updateGUI();
			}
		});
		datePanel.add(btn_getReport);
		transactionReportScrollPane = new JScrollPane();
		getContentPane().add(transactionReportScrollPane, BorderLayout.CENTER);
		transactionReportScrollPane.setPreferredSize(new java.awt.Dimension(697, 313));
		transactionReportScrollPane.setAutoscrolls(true);
	}
	
	private void updateGUI()
	{
	    if (startDatePicker.getModel().getValue() == null)
	    {
		    Date now = new Date();
		    String dateStr = new SimpleDateFormat(DATE_FORMAT).format(now);
		    String[] dateComp = dateStr.split("-");
		    
		    startDatePicker.getModel().setDay(Integer.valueOf(dateComp[0]));
		    startDatePicker.getModel().setMonth(Integer.valueOf(dateComp[1])-1);
		    startDatePicker.getModel().setYear(Integer.valueOf(dateComp[2]));
		    
		    startDatePicker.getModel().setSelected(true);
	    	
	    }
	    startDateCal = (Calendar) startDatePicker.getModel().getValue();
		startDate = startDateCal.getTime();
		
	    if (endDatePicker.getModel().getValue() == null)
	    {
		    Date now = new Date();
		    String dateStr = new SimpleDateFormat(DATE_FORMAT).format(now);
		    String[] dateComp = dateStr.split("-");
		    
		    endDatePicker.getModel().setDay(Integer.valueOf(dateComp[0]));
		    endDatePicker.getModel().setMonth(Integer.valueOf(dateComp[1])-1);
		    endDatePicker.getModel().setYear(Integer.valueOf(dateComp[2]));
		    
		    endDatePicker.getModel().setSelected(true);
	    }	
	    endDateCal = (Calendar) endDatePicker.getModel().getValue();
		endDate = endDateCal.getTime();
		
		if (startDate.after(endDate)) 
		{
            String msg="StartDate cannot be greater than EndDate";
            JOptionPane.showMessageDialog(this, msg, "Date Range Errorr", JOptionPane.WARNING_MESSAGE);
            return;
        }
		else
        {
			ArrayList<ArrayList<Object>> dataArr = new ArrayList<ArrayList<Object>>();
			Date currDate = (Date) startDate.clone();
			Calendar cal = (Calendar) startDateCal.clone();

			while(currDate.before(endDate) || currDate.compareTo(endDate) == 0)
			{
				
				this.objList=manager.getTransactionListByDate(setTimeToZero(currDate));
				for(Transaction t:objList)
				{
					for(TransactionItem item:t.getItemList())
					{
						ArrayList<Object> currTransData = new ArrayList<Object>();
						currTransData.add(t.getId());
						currTransData.add(sg.edu.nus.iss.usstore.util.Util.dateToString((Date)t.getDate()));
						currTransData.add(item.getProduct().getProductId());
						currTransData.add(item.getProduct().getName());
						currTransData.add(item.getProduct().getBriefDescription());
						currTransData.add(item.getQty());
						dataArr.add(currTransData);
					}
				}

				cal.add(Calendar.DATE, 1);
				currDate=cal.getTime();
			}
			
			columns = new String[num_col];
			objData = new Object[dataArr.size()][num_col];
			for(int i=0;i<dataArr.size();i++)
			{
				
					objData[i][0] = dataArr.get(i).get(0);
					objData[i][1] = dataArr.get(i).get(1);
					objData[i][2] = dataArr.get(i).get(2);
					objData[i][3] = dataArr.get(i).get(3);
					objData[i][4] = dataArr.get(i).get(4);
					objData[i][5] = dataArr.get(i).get(5);
				
			}
			columns[0]="ID";
			columns[1]="Date";
			columns[2]="Prod ID";
			columns[3]="Prod Name";
			columns[4]="Prod Desc";
			columns[5]="Prod Qty";
        }
		

		try {
			setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
			{
					TableModel transactionListReportTableModel = new DefaultTableModel(objData,columns)
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
							case 0: return Double.class;
							case 1: return String.class;
							case 2: return String.class;
							case 3: return String.class;
							case 4: return String.class;
							case 5: return Double.class;
							default: return String.class;
							}
						}
					};
					transactionListReportTable = new JTable();
					transactionReportScrollPane.setViewportView(transactionListReportTable);
					transactionListReportTable.setModel(transactionListReportTableModel);
					TableRowSorter<TableModel> sorter = new TableRowSorter<TableModel>(transactionListReportTable.getModel());
					transactionListReportTable.setRowSorter(sorter);
					List<SortKey> sortKeys = new ArrayList<SortKey>();
					sortKeys.add(new SortKey(0, SortOrder.ASCENDING));
					sortKeys.add(new SortKey(2, SortOrder.ASCENDING));
					transactionListReportTable.getRowSorter().setSortKeys(sortKeys);
				Dimension size = transactionListReportTable.getPreferredScrollableViewportSize();
				transactionListReportTable.setPreferredScrollableViewportSize(new Dimension(Math.min(getPreferredSize().width, size.width), size.height));
			}
			resizeTableColumnWidth(transactionListReportTable);
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
	
	private Date setTimeToZero(Date d)
	{
        Calendar cal = Calendar.getInstance();
        cal.setTime(d);
        cal.set(Calendar.HOUR_OF_DAY, 0);
        cal.set(Calendar.MINUTE, 0);
        cal.set(Calendar.SECOND, 0);
        cal.set(Calendar.MILLISECOND, 0);
        return cal.getTime();
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
