package sg.edu.nus.iss.usstore.util;

import javax.swing.text.AttributeSet;
import javax.swing.text.BadLocationException;
import javax.swing.text.PlainDocument;

/*
 * Limit input comma to JTextField
 * 
 *  @ XIE JIABAO
 */

public class StringDocument extends PlainDocument{

/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private int limitedLength;
	
	public StringDocument(){
		super();
	}
	
	public StringDocument(int length){
		super();
		this.limitedLength = length;
	}
	
	@Override
	public void insertString(int offset, String str, AttributeSet a) throws BadLocationException{
		if(str == null){
			return;
		}
		if(limitedLength<=0){
			char[] upper = str.toCharArray();  
            int length = 0;
            Character comma = new Character(',');
            for (int i = 0; i < upper.length; i++)
            {  
            	if (!comma.equals(upper[i]))
                {  
                    upper[length++] = upper[i];
                }  
            }  
            super.insertString(offset, new String(upper,0,length),a);
		}else{
			if(this.getLength()+str.length()<=limitedLength){
				char[] upper = str.toCharArray();  
				int length = 0;  
				Character comma = new Character(',');
				for (int i = 0; i < upper.length; i++)
				{  
					if (!comma.equals(upper[i]))
					{  
						upper[length++] = upper[i];  
					}  
				}  
				super.insertString(offset, new String(upper,0,length),a);
			}
		}
	}

	public int getLimitedLength() {
		return limitedLength;
	}
}
