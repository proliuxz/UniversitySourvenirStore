package sg.edu.nus.iss.usstore.util;

import java.text.DecimalFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import sg.edu.nus.iss.usstore.exception.DataInputException;

/**
 * 
 * @author Xu Minsheng
 *
 */
public class Util {

	public static final String C_Separator = ",";
		
	public static final String C_Date_Format = "yyyy-MM-dd";
	
	public static final DecimalFormat df = new DecimalFormat("0.00");
	
	/**
	 * throw Exception when Component's text contains "," 
	 * 
	 * @return Component's text
	 * @throws DataInputException 
	 */
	public static String examineString(String s) throws DataInputException{
		
		if (s.contains(C_Separator)){
			
			throw new DataInputException(s + " contains unexpected char(',')");
		}else{
			return s;
		}
		
	}
	
	/**
	 * String cast to int
	 * 
	 * @return cast text to integer
	 * @throws DataInputException 
	 */
	public static int castInt(String s) throws DataInputException{
		
		int result;
		
		try{
			result = Integer.parseInt(s);			
		}catch(NumberFormatException e){
			throw new DataInputException(s + " is not integer");
		}

		return result;
		
	}
	
	/**
	 * String cast to double
	 * 
	 * @return cast text to double
	 * @throws DataInputException 
	 */
	public static double castDouble(String s) throws DataInputException{
		
		double result;
		
		try{
			result = Double.parseDouble(s);
		
		}catch(NumberFormatException e){
			throw new DataInputException(s + " is not double");
		}

		return result;
	}
	
	/**
	 * String cast to date
	 * 
	 * @return cast text to date
	 * @throws DataInputException 
	 */
	public static Date castDate(String s) throws DataInputException{
		
		Date result;
		SimpleDateFormat sdf = new SimpleDateFormat(C_Date_Format);
		
		try{
			result = sdf.parse(s);
			
		}catch(ParseException e){
			throw new DataInputException(s + " is not a valid date");
		}

		return result;
	}
	
	/**
	 * Cast date to String in yyyy-MM-dd format
	 * 
	 * @param date
	 * @return String
	 */
	public static String dateToString(Date date) {
		SimpleDateFormat sdf = new SimpleDateFormat(C_Date_Format);
		return sdf.format(date);
	}
	
	/**
	 *  @author tanuj
	 * @param date
	 * @param period
	 * @return
	 */
	
	
	
	
	public static Date addDays(Date date,int period){
		Calendar cal = Calendar.getInstance();
		cal.setTime(date);
		cal.add(Calendar.DATE, period);
		return cal.getTime();
	}
	
	/**
	 * 
	 * @param price
	 * @return
	 */
	public static String priceFormat(double price)
	{
		return df.format(price);
	}
	
	
	
	
/**
 * Getter for virtual field 'startDateText' (yyyy/dd/mm representation of the startDate)
 * @return
 */
//public Static String getStartDateText() {
//	return startDate.toString();
//}

/**
 * Setter for virtual field 'startDateText' (yyyy/dd/mm representation of the startDate)
 * @param startDateText
 */
//public void setStartDateText(String startDateText) {
	//this.startDate = DateDay.parse(startDateText);
//}
}
	
	

