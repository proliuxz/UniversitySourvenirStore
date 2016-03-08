/**
 * 
 */
package sg.edu.nus.iss.usstore.domain;

import java.io.IOException;
import java.util.ArrayList;

import sg.edu.nus.iss.usstore.dao.StoreKeeperDao;

/**
 * @author CHARAN
 *
 */
public class StoreKeeperMgr {
	
	private ArrayList<StoreKeeper> storeKeeperList;
	private StoreKeeperDao userDao;
	
	public StoreKeeperMgr()
	{
		userDao = new StoreKeeperDao();
		storeKeeperList = new ArrayList<StoreKeeper>();
	}
	
	public void loadData() throws IOException{
		storeKeeperList = userDao.loadDataFromFile();
	}
	
	public boolean checkAuthority(String userName,String Password)
	{
		boolean isValidUser = false;
		if(userName == null || Password == null || userName.trim().equals("") || Password.trim().equals(""))
			return isValidUser;
		for(StoreKeeper user:storeKeeperList)
		{
			if(user.getUserName().equalsIgnoreCase(userName) && user.getPassword().equalsIgnoreCase(Password))
			{
				isValidUser = true;
				break;
			}				
		}
		return isValidUser;
	}

}
