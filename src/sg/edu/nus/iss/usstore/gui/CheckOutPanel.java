//CheckOutPanel.java
package sg.edu.nus.iss.usstore.gui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Vector;

import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.event.TableModelEvent;
import javax.swing.event.TableModelListener;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumn;

import sg.edu.nus.iss.usstore.device.BarCodeReader;
import sg.edu.nus.iss.usstore.domain.Member;
import sg.edu.nus.iss.usstore.domain.Product;
import sg.edu.nus.iss.usstore.domain.Public;
import sg.edu.nus.iss.usstore.domain.Transaction;
import sg.edu.nus.iss.usstore.domain.TransactionItem;
import sg.edu.nus.iss.usstore.util.DigitDocument;
import sg.edu.nus.iss.usstore.util.IntDocument;

public class CheckOutPanel extends JPanel
{
	/**
	 * The CheckOutPanel
	 * 
	 * @author Liu Xinzhuo
	 * @author A0136010A
	 * @version 0.9
	 */
	private static final long serialVersionUID = 1L;
	//define UI param
	private JLabel JlgetMemberName;
	private JLabel JlTotalPriceNum;
	private JLabel JlDiscountNum;
	private JLabel JlDiscountedPriceNum;
	private JLabel JlLoyalPointNum;
	private JLabel JlRestNum;
	private JLabel JlChangeNum;
	private JLabel jlTips;
	private JTextField JtBarCodeID;
	private JTextField JtQuantity;
	private JTextField JtMemberID;
	private JTextField JtPaidNum;
	private JTextField JtCashNum;
	private JTable table;
	private JButton JbFinish;
	private JButton JbBack;
	private JButton JbMemberSubmit;
	private JButton JbProductSubmit;
	private JRadioButton jb1;
	private JRadioButton jb2;
	private JRadioButton jb3;
	private JRadioButton jb4;
	private TableColumn column;
	private BarCodeReader br = new BarCodeReader();
	private DecimalFormat df = new DecimalFormat("0.00");
	private DefaultTableModel defaultModel;
	
	private double tempChange;
	private Product product = null;
	private int scrollpanelwidth = 600;
	private int scrollpanelheight = 230;
	private int flag = 0;
	private String tempBarCode;
	private Vector<Object> vector = new Vector<Object>();
	private Listener listener = new Listener();
	private StoreApplication sa = null;
	private Transaction transaction;
	//define Error massage
	private final String ERR_MSG_MEMBER_NOT_EXIST = "Invalid MemberID!";
	private final String ERR_MSG_PRODCUT_NOT_EXIST = "Invalid Bar Code!";
	private final String ERR_MSG_BARCODE_ERROR = "Invalid Bar Code!";
	private final String ERR_MSG_QUANTITY_FORMAT_ERROR = "Quantity Format Error";
	private final String ERR_MSG_QUANTITY_NOT_ENOUGH = "Quantity is not Enough!";
	private final String ERR_MSG_POINT_FORMAT_ERROR = "Point Format Error!";
	private final String ERR_MSG_POINT_NOT_ENOUGH = "Point is not Enough!";
	private final String ERR_MSG_CASH_FORMAT_ERROR = "Cash Format Error!";
	private final String ERR_MSG_CASH_NOT_ENOUGH = "Cash is not enough!";
	private final String ERR_MSG_SELECT_ROW = "Select a Row!";

	private final String TIPS_1 = "If you choose Bar Code Reader', please enter data in console screen!";
	public static JLabel jlTitle;

	// set Transaction
	public Transaction getTransaction()
	{
		return transaction;
	}

	// get Transaction
	public void setTransaction(Transaction transaction)
	{
		this.transaction = transaction;
	}

	/**
	 * control radio Buttons' select
	 */
	public void setRadioButton()
	{
		int sum = 0;
		if (jb1.isSelected() == true)
		{
			sum = sum + 1;
		}
		if (jb2.isSelected() == true)
		{
			sum = sum + 2;
		}
		if (jb3.isSelected() == true)
		{
			sum = sum + 4;
		}
		if (jb4.isSelected() == true)
		{
			sum = sum + 8;
		}
		if (sum == 5)
		{
			JtMemberID.setEnabled(true);
			JtBarCodeID.setEnabled(true);
			JtMemberID.setEditable(true);
			JtQuantity.setEnabled(true);
			JbMemberSubmit.setEnabled(true);
			JtQuantity.setText(null);
			JbProductSubmit.setText("Add   Product");
			JbProductSubmit.setEnabled(true);
		} else if (sum == 9)
		{
			JtMemberID.setEnabled(true);
			JtMemberID.setText(br.scanMemberId());
			JtMemberID.setEditable(false);
			JtBarCodeID.setEnabled(false);
			JtQuantity.setEnabled(false);
			JtQuantity.setText("1");
			JbProductSubmit.setText("Scan Product");
			JbMemberSubmit.setEnabled(true);
			JbProductSubmit.setEnabled(true);
				
		} else if (sum == 6)
		{
	
			JtMemberID.setEnabled(false);
			JtBarCodeID.setEnabled(true);
			JtQuantity.setEnabled(true);
			JtQuantity.setText(null);
			JbMemberSubmit.setEnabled(false);
			JbProductSubmit.setText("Add   Product");
			JbProductSubmit.setEnabled(true);
		} else if (sum == 10)
		{

			JtMemberID.setEnabled(false);
			JtBarCodeID.setEnabled(false);
			JtQuantity.setEnabled(false);
			JtQuantity.setText("1");
			JbProductSubmit.setText("Scan Product");
			JbMemberSubmit.setEnabled(false);
			JbProductSubmit.setEnabled(true);
		}
	}
	/**
	 * Get the number of product which is already in cart
	 * @param product
	 * @return Integer
	 */
	public int getCartNum(Product product)
	{
		int inCart = 0;
		ArrayList<TransactionItem> cart= transaction.getItemList();
		for(int i = 0; i< cart.size();i++)
		{
			if(product.equals(cart.get(i).getProduct()))
				inCart = cart.get(i).getQty();
		}
		return inCart;
	}
	
	/**
	 *  SET Output Value
	 */
	public void setOutputValue()
	{
		JlTotalPriceNum.setText(df.format(transaction.calcTotalPrice()));
		if (transaction.getDiscount()==null)
			JlDiscountNum.setText("0.00");
		else
		JlDiscountNum.setText(Double.toString(transaction.getDiscount()
				.getPercent()));
		JlDiscountedPriceNum
				.setText(df.format(transaction.calcDiscountPrice()));
		JlRestNum.setText(df.format(transaction.calcRest()));
		if (transaction.calcChange() >= 0)
			JlChangeNum.setText(df.format(transaction.calcChange()));
		if (transaction.getCustomer() instanceof Member)
		{
			Member member = (Member) transaction.getCustomer();
			JlgetMemberName.setText(member.name);
			if (member.getLoyaltyPoint() == 0)
				JlLoyalPointNum.setText("0");
			else
			{
				JlLoyalPointNum.setText(Integer.toString(member
						.getLoyaltyPoint()));
			}
			JtPaidNum.setEnabled(true);
		} else
		{
			JlgetMemberName.setText("PUBLIC");
			JtPaidNum.setEnabled(false);
		}
		JbFinishControl();
	}

	/**
	 * Control the useable of Finish Button
	 */
	public void JbFinishControl()
	{
		if (transaction.calcChange() < 0)
		{
			JbFinish.setEnabled(false);
		} else if (jlTitle.getText() == ERR_MSG_CASH_FORMAT_ERROR
				|| jlTitle.getText() == ERR_MSG_CASH_NOT_ENOUGH
				|| jlTitle.getText() == ERR_MSG_POINT_FORMAT_ERROR
				|| jlTitle.getText() == ERR_MSG_POINT_NOT_ENOUGH)
		{
			JbFinish.setEnabled(false);
		} else if (transaction.getItemList().size() == 0)
		{
			JbFinish.setEnabled(false);
		} else
		{
			JbFinish.setEnabled(true);
		}
	}

	/**
	 * table data binding
	 */
	public void tableDataBinding()
	{
		flag = 1;
		ArrayList<TransactionItem> itemList = transaction.getItemList();
		Vector<Object> dataVector = defaultModel.getDataVector();
		dataVector.clear();

		for (int i = 0; i < itemList.size(); i++)
		{
			Vector<Object> subVector = new Vector<Object>();
			subVector.add(i + 1);
			TransactionItem transactionitem = itemList.get(i);
			product = transactionitem.getProduct();
			subVector.add(product.getBarCodeNumber());
			subVector.add(product.getName());
			subVector.add(Integer.toString(transactionitem.getQty()));
			subVector.add(product.getPrice());
			subVector.add(transactionitem.calculateAmount());
			defaultModel.addRow(subVector);
		}
		table.validate();
		table.repaint();
		flag = 0;
		setOutputValue();
	}

	/**
	 * Add product to table
	 * @param arrayList
	 * @param qty
	 */
	public void addProduct(ArrayList<TransactionItem> arrayList, int qty)
	{
		int productAddFlag = -1;
		for (int m = 0; m < arrayList.size(); m++)
		{
			if (arrayList.get(m).getProduct() == product)
			{
				productAddFlag = m;
			}
		}
		if (productAddFlag == -1)
		{
			arrayList
					.add(new TransactionItem(product, product.getPrice(), qty));
		} else
		{
			TransactionItem tempTransactionItem = arrayList.get(productAddFlag);
			tempTransactionItem.setQty(tempTransactionItem.getQty() + qty);
		}
	}

	/**
	 * cancel this check out
	 */
	public void cancelAll()
	{
		// refresh data
		{
			transaction = new Transaction();
			sa.setBillCustomer(transaction, "");
			vector = defaultModel.getDataVector();
			vector.clear();
			table.validate();
			table.repaint();
		}
		// refresh param
		{
			flag = 0;
		}
		// refresh UI
		{
			JlgetMemberName.setText("PUBLIC");
			JlTotalPriceNum.setText(Double.toString(transaction
					.calcTotalPrice()));
			if (transaction.getDiscount()==null)
				JlDiscountNum.setText("0.00");
			else
				JlDiscountNum.setText(Double.toString(transaction.getDiscount()
						.getPercent()));
			JlDiscountedPriceNum.setText(Double.toString(transaction
					.calcDiscountPrice()));
			JlLoyalPointNum.setText("0");
			JlRestNum.setText(Double.toString(transaction.calcRest()));
			JlChangeNum.setText(Double.toString(transaction.calcChange()));
			JtBarCodeID.setText(null);
			JtQuantity.setText(null);
			JtMemberID.setText(null);
			JtPaidNum.setText(null);
			JtPaidNum.setEnabled(false);
			JtCashNum.setText(null);
			jlTitle.setForeground(Color.BLACK);
			jlTitle.setText("Check Out");
			jb2.setSelected(true);
			jb3.setSelected(true);
			JtMemberID.setEnabled(false);
			JbMemberSubmit.setEnabled(false);
		}
	}

	/**
	 * draw UI & set some param
	 * @param sa
	 */
	public CheckOutPanel(StoreApplication sa)
	{
		this.sa = sa;
		// OPeration
		JPanel jpOperation = new JPanel();
		this.add(jpOperation, BorderLayout.NORTH);
		// Title
		jlTitle = new JLabel("Check Out!");
		jlTitle.setFont(new Font("Bauhaus 93", Font.PLAIN, 30));
		jlTitle.setHorizontalAlignment(SwingConstants.CENTER);
		jpOperation.setLayout(new GridLayout(3, 1));
		JPanel jpTitleSelect = new JPanel();
		JPanel jpInput = new JPanel();
		JPanel jpOutput = new JPanel();
		jpTitleSelect.setLayout(new GridLayout(2, 1));
		JPanel jpinputSelect = new JPanel();

		jpinputSelect.setLayout(new FlowLayout(FlowLayout.LEFT));
		JLabel jlDateEntity = new JLabel("Date Entry Type:");
		jlTips = new JLabel();
		jlTips.setForeground(Color.BLUE);
		ButtonGroup bg2 = new ButtonGroup();
		jb3 = new JRadioButton("Manual");
		jb4 = new JRadioButton("Bar Code Reader");
		jb3.setActionCommand("jb3");
		jb3.addActionListener(listener);
		jb4.setActionCommand("jb4");
		jb4.addActionListener(listener);
		bg2.add(jb3);
		bg2.add(jb4);
		jb3.setSelected(true);
		jpinputSelect.add(jlDateEntity);
		jpinputSelect.add(jb3);
		jpinputSelect.add(jb4);
		jpinputSelect.add(jlTips);
		jlTips.setText(TIPS_1);
		
		jpTitleSelect.add(jlTitle);
		jpTitleSelect.add(jpinputSelect);
		jpOperation.add(jpTitleSelect);
		jpOperation.add(jpInput);
		jpOperation.add(jpOutput);

		// Input
		jpOperation.add(jpInput);
		jpInput.setLayout(new GridLayout(2, 2));
		JPanel jp1 = new JPanel();
		JPanel jp2 = new JPanel();
		JPanel jp3 = new JPanel();
		JPanel jp4 = new JPanel();

		// jp1
		JLabel JlMemberID = new JLabel(" MEMBER ID");
		JtMemberID = new JTextField(10);
		JtMemberID.setEnabled(false);
		ButtonGroup bg1 = new ButtonGroup();
		jb1 = new JRadioButton("Member");
		jb2 = new JRadioButton("PUBLIC");
		jb1.setActionCommand("jb1");
		jb1.addActionListener(listener);
		jb2.setActionCommand("jb2");
		jb2.addActionListener(listener);
		bg1.add(jb1);
		bg1.add(jb2);
		jb2.setSelected(true);
		jp1.setLayout(new FlowLayout(FlowLayout.LEFT));
		jp1.add(jb2);
		jp1.add(jb1);
		jp1.add(JlMemberID);
		jp1.add(JtMemberID);
		jpInput.add(jp1);

		// jp2
		JLabel JlMemberName = new JLabel("NAME        ");
		JlgetMemberName = new JLabel("PUBLIC");
		JbMemberSubmit = new JButton("Set   Member");
		JbMemberSubmit.setEnabled(false);
		JbMemberSubmit.setActionCommand("JbMemberSubmit");
		JbMemberSubmit.addActionListener(listener);
		jp2.setLayout(new GridLayout(1, 2));
		JPanel jp2_1 = new JPanel();
		JPanel jp2_2 = new JPanel();
		jp2_1.setLayout(new FlowLayout(FlowLayout.LEFT));
		jp2_2.setLayout(new FlowLayout(FlowLayout.LEFT));
		jp2_1.add(JlMemberName);
		jp2_1.add(JlgetMemberName);
		jp2_2.add(JbMemberSubmit);
		jp2.add(jp2_1);
		jp2.add(jp2_2);
		jpInput.add(jp2);

		// jp3
		JLabel JlBarCodeID = new JLabel("Bar   Code  ");
		JtBarCodeID = new JTextField(24);
		jp3.setLayout(new FlowLayout(FlowLayout.LEFT));
		jp3.add(JlBarCodeID);
		jp3.add(JtBarCodeID);
		jpInput.add(jp3);

		// jp4
		jp4.setLayout(new GridLayout(1, 2));
		JPanel jp4_1 = new JPanel();
		JPanel jp4_2 = new JPanel();
		jp4_1.setLayout(new FlowLayout(FlowLayout.LEFT));
		jp4_2.setLayout(new FlowLayout(FlowLayout.LEFT));
		JLabel JlQuantity = new JLabel("QUANTITY");
		JtQuantity = new JTextField(6);
		JtQuantity.setDocument(new IntDocument());
		JbProductSubmit = new JButton("Add   Product");
		tempBarCode = JtBarCodeID.getText();
		product = sa.getProductByBarCode(tempBarCode);
		JbProductSubmit.setActionCommand("JbProductSubmit");
		JbProductSubmit.addActionListener(listener);
		jp4_1.add(JlQuantity);
		jp4_1.add(JtQuantity);
		jp4_2.add(JbProductSubmit);
		jp4.add(jp4_1);
		jp4.add(jp4_2);
		jpInput.add(jp4);

		// output
		jpOperation.add(jpOutput);
		jpOutput.setLayout(new GridLayout(2, 4));
		JPanel jp5 = new JPanel();
		JPanel jp6 = new JPanel();
		JPanel jp7 = new JPanel();
		JPanel jp8 = new JPanel();
		JPanel jp9 = new JPanel();
		JPanel jp10 = new JPanel();
		JPanel jp11 = new JPanel();
		JPanel jp12 = new JPanel();

		// jp5
		jp5.setLayout(new FlowLayout(FlowLayout.LEFT));
		JLabel JlTotalPrice = new JLabel("Total Price:");
		JlTotalPriceNum = new JLabel("00.00");
		jp5.add(JlTotalPrice);
		jp5.add(JlTotalPriceNum);
		jpOutput.add(jp5);

		// jp6
		jp6.setLayout(new FlowLayout(FlowLayout.LEFT));
		JLabel JlDiscount = new JLabel("Discount:");
		JlDiscountNum = new JLabel("00.00");
		jp6.add(JlDiscount);
		jp6.add(JlDiscountNum);
		jpOutput.add(jp6);

		// jp7
		jp7.setLayout(new FlowLayout(FlowLayout.LEFT));
		JLabel JlDiscountedPrice = new JLabel("DiscountedPrice:");
		JlDiscountedPriceNum = new JLabel("00.00");
		jp7.add(JlDiscountedPrice);
		jp7.add(JlDiscountedPriceNum);
		jpOutput.add(jp7);

		// jp8
		jp8.setLayout(new FlowLayout(FlowLayout.LEFT));
		JLabel JlLoyalPoint = new JLabel("LOYAL POINT");
		JlLoyalPointNum = new JLabel("0");
		jp8.add(JlLoyalPoint);
		jp8.add(JlLoyalPointNum);
		jpOutput.add(jp8);

		// jp9
		jp9.setLayout(new FlowLayout(FlowLayout.LEFT));
		JLabel JlPaid = new JLabel("Redeemed Point");
		JtPaidNum = new JTextField(6);
		JtPaidNum.setEnabled(false);
		JtPaidNum.setDocument(new DigitDocument());
		JtPaidNum.getDocument().addDocumentListener(new DocumentListener()
		{
			//inset Update
			public void insertUpdate(DocumentEvent e)
			{
				String tempLoyalPaid = JtPaidNum.getText();
				int tempLoyalPaidNum = Integer.valueOf(tempLoyalPaid)
						.intValue();
				if (tempLoyalPaidNum < 0)
				{
					jlTitle.setForeground(Color.RED);
					jlTitle.setText(ERR_MSG_POINT_FORMAT_ERROR);
					JlChangeNum.setText("**.**");
				} else
				{
					Member member = (Member) transaction.getCustomer();
					if (member.getLoyaltyPoint() >= tempLoyalPaidNum)
					{
						if (jlTitle.getText() == ERR_MSG_POINT_NOT_ENOUGH)
						{
							jlTitle.setForeground(Color.BLACK);
							jlTitle.setText("Check Out");
						}
						transaction.setRedeemedLoyaltyPoint(tempLoyalPaidNum);
						JlRestNum.setText(df.format(transaction.calcRest()));
					} else
					{
						jlTitle.setForeground(Color.RED);
						jlTitle.setText(ERR_MSG_POINT_NOT_ENOUGH);
					}
				}
				setOutputValue();
			}
			//remove Update
			public void removeUpdate(DocumentEvent e)
			{

				if (JtPaidNum.getText().length() != 0)
				{
					String tempLoyalPaid = JtPaidNum.getText();

					int tempLoyalPaidNum = Integer.valueOf(tempLoyalPaid)
							.intValue();
					if (tempLoyalPaidNum < 0)
					{
						jlTitle.setForeground(Color.RED);
						jlTitle.setText(ERR_MSG_POINT_FORMAT_ERROR);
						JlChangeNum.setText("**.**");
					} else
					{
						Member member = (Member) transaction.getCustomer();
						if (member.getLoyaltyPoint() >= tempLoyalPaidNum)
						{
							if (jlTitle.getText() == ERR_MSG_POINT_NOT_ENOUGH)
							{
								jlTitle.setForeground(Color.BLACK);
								jlTitle.setText("Check Out");
							}
							transaction
									.setRedeemedLoyaltyPoint(tempLoyalPaidNum);
							JlRestNum.setText(df.format(transaction.calcRest()));
						} else
						{
							jlTitle.setForeground(Color.RED);
							jlTitle.setText(ERR_MSG_POINT_NOT_ENOUGH);
						}
					}
				} else
				{
					transaction.setRedeemedLoyaltyPoint(0);
				}
				setOutputValue();
			}
			public void changedUpdate(DocumentEvent e)
			{
			}
		});
		jp9.add(JlPaid);
		jp9.add(JtPaidNum);
		jpOutput.add(jp9);

		// jp10
		jp10.setLayout(new FlowLayout(FlowLayout.LEFT));
		JLabel JlRest = new JLabel("REST:");
		JlRestNum = new JLabel("00.00");
		jp10.add(JlRest);
		jp10.add(JlRestNum);
		jpOutput.add(jp10);

		// jp11
		jp11.setLayout(new FlowLayout(FlowLayout.LEFT));
		JLabel JlCash = new JLabel("Cash:");
		JtCashNum = new JTextField(6);
		JtCashNum.setDocument(new DigitDocument());
		JtCashNum.getDocument().addDocumentListener(new DocumentListener()
		{
			//insert Update
			public void insertUpdate(DocumentEvent e)
			{
				String ScashNum = JtCashNum.getText();
				double DcashNum = Double.valueOf(ScashNum).doubleValue();
				transaction.setCashAmount(DcashNum);
				if (DcashNum > 0)
				{
					tempChange = transaction.calcChange();
					if (tempChange >= 0)
					{
						JlChangeNum.setText(df.format(tempChange));
						if (jlTitle.getText() == ERR_MSG_CASH_NOT_ENOUGH
								|| jlTitle.getText() == ERR_MSG_CASH_FORMAT_ERROR)
						{
							jlTitle.setForeground(Color.BLACK);
							jlTitle.setText("Check Out");
						}
					} else
					{
						jlTitle.setForeground(Color.RED);
						jlTitle.setText(ERR_MSG_CASH_NOT_ENOUGH);
						JlChangeNum.setText("**.**");
					}
				} else
				{
					jlTitle.setForeground(Color.RED);
					jlTitle.setText(ERR_MSG_CASH_FORMAT_ERROR);
					JlChangeNum.setText("**.**");
				}
				setOutputValue();
			}
			//remove Update
			public void removeUpdate(DocumentEvent e)
			{
				if (JtCashNum.getText().length() != 0)
				{
					String ScashNum = JtCashNum.getText();
					double DcashNum = Double.valueOf(ScashNum).doubleValue();
					transaction.setCashAmount(DcashNum);
					if (DcashNum > 0)
					{
						double tempChange = transaction.calcChange();
						if (tempChange > 0)
						{
							JlChangeNum.setText(df.format(tempChange));
							if (jlTitle.getText() == ERR_MSG_CASH_NOT_ENOUGH
									|| jlTitle.getText() == ERR_MSG_CASH_FORMAT_ERROR)
							{
								jlTitle.setForeground(Color.BLACK);
								jlTitle.setText("Check Out");
								JbFinish.setEnabled(true);
							}
						} else
						{
							jlTitle.setForeground(Color.RED);
							jlTitle.setText(ERR_MSG_CASH_NOT_ENOUGH);
							JlChangeNum.setText("**.**");
						}
					} else
					{
						jlTitle.setForeground(Color.RED);
						jlTitle.setText(ERR_MSG_CASH_FORMAT_ERROR);
						JlChangeNum.setText("**.**");
					}
				} else
				{
					transaction.setCashAmount(0);
				}
				setOutputValue();
			}

			public void changedUpdate(DocumentEvent e)
			{
			}
		});
		jp11.add(JlCash);
		jp11.add(JtCashNum);
		jpOutput.add(jp11);

		// jp12
		jp12.setLayout(new FlowLayout(FlowLayout.LEFT));
		JLabel JlChange = new JLabel("CHANGE:");
		JlChangeNum = new JLabel("00.00");
		jp12.add(JlChange);
		jp12.add(JlChangeNum);
		jpOutput.add(jp12);

		// Table
		String[] tableTitle = { "Num", "Bar Code", "Product",
				"Quantity(Editable)", "Price", "Total" };
		defaultModel = new DefaultTableModel(null, tableTitle)
		{
			/**
			 * override defaultModel to set editable
			 */
			private static final long serialVersionUID = 1L;

			public boolean isCellEditable(int row, int column)
			{
				if (column == 3)
					return true;
				else
					return false;
			}
		};
		table = new JTable(defaultModel);
		for (int i = 0; i < table.getColumnCount(); i++)
		{
			column = table.getColumnModel().getColumn(i);
			if (i == 0)
			{
				column.setPreferredWidth(scrollpanelwidth / 16);
			}
			if (i == 1 || i == 2)
			{
				column.setPreferredWidth(scrollpanelwidth / 4);
			}
			if (i == 3)
			{
				column.setPreferredWidth(scrollpanelwidth * 3 / 16);
			}
			if (i == 4 || i == 5)
			{
				column.setPreferredWidth(scrollpanelheight / 8);
			}
		}
		defaultModel.addTableModelListener(new TableModelListener()
		{	
			//Override the TableModelListener to make the Quantity editable
			@Override
			public void tableChanged(TableModelEvent e)
			{
				int row = e.getFirstRow();
				if (flag == 0)
				{
					int num = Integer.valueOf(
							(String) defaultModel.getValueAt(row, 3))
							.intValue();
					transaction.getItemList().get(row).setQty(num);
					tableDataBinding();
				}
				setOutputValue();
			}
		});

		DefaultTableCellRenderer tcr = new DefaultTableCellRenderer()
		{
			/**
			 * Override Cell Renderer to change color of the table
			 */
			private static final long serialVersionUID = 1L;

			public Component getTableCellRendererComponent(JTable table,
					Object value, boolean isSelected, boolean hasFocus,
					int row, int column)
			{

				if (row % 2 == 0)
					setBackground(Color.WHITE);
				else if (row % 2 == 1)
					setBackground(new Color(206, 231, 255));
				if (column == 3)
					setBackground(new Color(160, 255, 160));
				return super.getTableCellRendererComponent(table, value,
						isSelected, hasFocus, row, column);

			}
		};

		for (int i = 0; i <= 5; i++)
		{
			table.getColumn(tableTitle[i]).setCellRenderer(tcr);
		}

		table.setPreferredScrollableViewportSize(new Dimension(
				scrollpanelwidth, scrollpanelheight));

		JScrollPane scrollPane = new JScrollPane();
		scrollPane.getViewport().setBackground(Color.white);
		scrollPane.setViewportView(table);

		this.add(scrollPane, BorderLayout.CENTER);

		JPanel jpButton = new JPanel();
		jpButton.setLayout(new GridLayout(7, 1));
		JButton JbDelete = new JButton("Delete");
		JLabel JlBlank1 = new JLabel(" ");
		JbDelete.setActionCommand("JbDelete");
		JbDelete.addActionListener(listener);
		JButton JbCancel = new JButton("Cancel");
		JbCancel.setActionCommand("JbCancel");
		JbCancel.addActionListener(listener);
		JLabel JlBlank2 = new JLabel(" ");
		JbFinish = new JButton("Finish");
		JbFinish.setActionCommand("JbFinish");
		JbFinish.addActionListener(listener);
		JbFinish.setEnabled(false);
		JbBack = new JButton("Back");
		JbBack.setActionCommand("JbBack");
		JbBack.addActionListener(listener);

		jpButton.add(JbDelete);
		jpButton.add(JbCancel);
		jpButton.add(JbFinish);
		jpButton.add(JlBlank1);
		jpButton.add(JlBlank2);
		jpButton.add(JbBack);
		this.add(jpButton, BorderLayout.EAST);
	}

	// Button ActionListener
	class Listener implements ActionListener
	{
		public void actionPerformed(ActionEvent e)
		{
			if (e.getActionCommand().equals("JbMemberSubmit"))
			{
				String MemberID = null;
				MemberID = JtMemberID.getText();
				if (JtMemberID.getText().length() == 0)
				{
					jlTitle.setForeground(Color.RED);
					jlTitle.setText(ERR_MSG_MEMBER_NOT_EXIST);
					if(jb1.isSelected()&&jb4.isSelected())
					JtMemberID.setText(br.scanMemberId());
				} else
				{
					transaction = sa.setBillCustomer(transaction, MemberID);
					if (transaction.getCustomer() == null)
					{
						transaction = sa.setBillCustomer(transaction, "");
						jlTitle.setForeground(Color.RED);
						jlTitle.setText(ERR_MSG_MEMBER_NOT_EXIST);
						if(jb1.isSelected()&&jb4.isSelected())
							JtMemberID.setText(br.scanMemberId());
					} else
					{
						if (jlTitle.getText() == ERR_MSG_MEMBER_NOT_EXIST)
						{
							jlTitle.setForeground(Color.BLACK);
							jlTitle.setText("Check Out");
						}
					}
				}
				setOutputValue();
			}
			if (e.getActionCommand().equals("JbProductSubmit"))
			{
				flag = 1;
				if (jb4.isSelected() == true)
				{
					tempBarCode = br.scanProductBarCode();
					JtQuantity.setText("1");
				} else
				{
					tempBarCode = JtBarCodeID.getText();
				}
				product = sa.getProductByBarCode(tempBarCode);
				String tempqty = JtQuantity.getText();
				if (tempBarCode.length() == 0||product==null)
				{
					jlTitle.setForeground(Color.RED);
					jlTitle.setText(ERR_MSG_BARCODE_ERROR);
				} else if (tempqty.length() == 0)
				{
					jlTitle.setForeground(Color.RED);
					jlTitle.setText(ERR_MSG_QUANTITY_FORMAT_ERROR);
				} else if (Integer.valueOf(JtQuantity.getText()).intValue() < 1)
				{
					jlTitle.setForeground(Color.RED);
					jlTitle.setText(ERR_MSG_QUANTITY_FORMAT_ERROR);
				} else if (product.getQuantityAvailable() < (Integer
						.parseInt(tempqty)+getCartNum(product)))
				{
					jlTitle.setForeground(Color.RED);
					jlTitle.setText(ERR_MSG_QUANTITY_NOT_ENOUGH + "Only " +product.getQuantityAvailable() +" is Available !");
				} else
				{
					int intqty = Integer.parseInt(tempqty);
					if (jlTitle.getText() == ERR_MSG_PRODCUT_NOT_EXIST
							|| jlTitle.getText() == ERR_MSG_BARCODE_ERROR
							|| jlTitle.getText().substring(0,8).equals("Quantity"))
					{
						jlTitle.setForeground(Color.BLACK);
						jlTitle.setText("Check Out");
					}
					if (product == null)
					{
						jlTitle.setForeground(Color.RED);
						jlTitle.setText(ERR_MSG_PRODCUT_NOT_EXIST);
						return;
					}
					ArrayList<TransactionItem> tempTransactionList = transaction
							.getItemList();
					addProduct(tempTransactionList, intqty);
					tableDataBinding();
					JtBarCodeID.setText(null);
					JtQuantity.setText(null);
				}
				flag = 0;
				setOutputValue();
			}
			if (e.getActionCommand().equals("JbDelete"))
			{
				if (table.getSelectedRow() == -1)
				{
					jlTitle.setForeground(Color.RED);
					jlTitle.setText("Select a row");
				} else
				{
					if (jlTitle.getText() == ERR_MSG_SELECT_ROW)
					{
						jlTitle.setForeground(Color.BLACK);
						jlTitle.setText("Check Out");
					}
					int rowcount = defaultModel.getRowCount();
					if (rowcount > 0)
					{
						transaction.getItemList()
								.remove(table.getSelectedRow());
						tableDataBinding();
					}
					table.revalidate();
				}
				setOutputValue();
			}
			if (e.getActionCommand().equals("JbCancel"))
			{

				String msg = "Cancel this Transaction ?";
				int n = JOptionPane.showConfirmDialog(null, msg,
						"Confirmation", JOptionPane.YES_NO_OPTION);
				if (n == 0)
				{
					cancelAll();
				}
			}
			if (e.getActionCommand().equals("JbFinish"))
			{
				if (transaction.getCustomer().name == null)
				{
					transaction.setCustomer(new Public());
				}
				transaction.setDate(new Date());
				// deal with the problem getLoyaltypoint==0 & reset the
				// loyaltypoint
				if (transaction.getCustomer() instanceof Member)
				{
					Member member = (Member) transaction.getCustomer();
					if (member.getLoyaltyPoint() == -1)
						member.setLoyaltyPoint(0);
				}
				JFrame confirm = new CheckOutConfirmFrame(
						sa.confirmPayment(transaction), sa);
				confirm.setVisible(true);
				cancelAll();
			}
			if (e.getActionCommand().equals("JbBack"))
			{
				String msg = "Back to Main Screen?";
				int n = JOptionPane.showConfirmDialog(null, msg,
						"Confirmation", JOptionPane.YES_NO_OPTION);
				if (n == 0)
				{
					cancelAll();
					sa.getStoreWindow().changeCard("mainScreen");
				}
			}
			if (e.getActionCommand().equals("jb1"))
			{
				setRadioButton();
				setOutputValue();
			}
			if (e.getActionCommand().equals("jb2"))
			{
				transaction = sa.setBillCustomer(transaction, "");
				if (jlTitle.getText() == ERR_MSG_MEMBER_NOT_EXIST)
				{
					jlTitle.setForeground(Color.BLACK);
					jlTitle.setText("Check Out");
				}
				setRadioButton();
				setOutputValue();
			}
			if (e.getActionCommand().equals("jb3"))
			{
				setRadioButton();
				setOutputValue();
			}
			if (e.getActionCommand().equals("jb4"))
			{
				setRadioButton();
				setOutputValue();
			}
		}
	}
}// /~
