package sg.edu.nus.iss.usstore.dao;

import java.io.IOException;
import java.util.ArrayList;

import sg.edu.nus.iss.usstore.domain.Member;
import sg.edu.nus.iss.usstore.exception.DataFileException;
import sg.edu.nus.iss.usstore.exception.DataInputException;
import sg.edu.nus.iss.usstore.util.Util;

/**
 * provide Data Access to file for Member entity
 * 
 * @author  Achyut Suresh Rao
 * 
 */
public class MemberDao extends BaseDao {

	// datafile name
	private static final String C_File_Name = "Members.dat";
	// determine if the No. of fields of a record is correct
	private static final int C_Field_No = 3;

	/**
	 * 
	 * @return
	 * @throws DataInputException
	 * @throws IOException
	 * @throws DataFileException
	 */
	public ArrayList<Member> loadDataFromFile() throws IOException,
			DataFileException {
		ArrayList<String> stringList = null;

		stringList = super.loadStringFromFile(super.getcDatafolderpath()
				+ C_File_Name);

		ArrayList<Member> dataList = new ArrayList<Member>();

		StringBuffer errMsg = new StringBuffer();

		for (int lineNo = 0; lineNo < stringList.size(); lineNo++) {

			String line = stringList.get(lineNo);

			String[] fields = line.split(Util.C_Separator);

			// when the No. of fields of a record is less then C_Field_No, skip
			// this record
			if (fields.length != C_Field_No) {
				errMsg.append("datafile[" + C_File_Name + "] LineNo:"
						+ (lineNo + 1) + System.getProperty("line.separator"));
				continue;
			}

			try {
				String name = fields[0];
				String memberID = fields[1];
				int loyaltyPoint = Util.castInt(fields[2]);

				Member member = new Member(name, memberID, loyaltyPoint);

				dataList.add(member);
			} catch (DataInputException e) {
				errMsg.append("datafile[" + C_File_Name + "] LineNo:"
						+ (lineNo + 1) + System.getProperty("line.separator"));
			}
		}

		if (errMsg.length() > 0) {
			String exceptionMsg = "Following data in file is not correct:"
					+ System.getProperty("line.separator") + errMsg;
			throw new DataFileException(exceptionMsg);
		}

		return dataList;
	}

	/**
	 * 
	 * @param dataList
	 * @throws IOException
	 */
	public void saveDataToFile(ArrayList<Member> dataList) throws IOException {

		ArrayList<String> stringList = new ArrayList<String>();

		for (Member member : dataList) {
			StringBuffer line;

			line = new StringBuffer(member.getName().toUpperCase() + Util.C_Separator);
			line.append(member.getMemberID().toUpperCase() + Util.C_Separator);
			line.append(member.getLoyaltyPoint());

			stringList.add(line.toString());
		}

		super.saveStringToFile(super.getcDatafolderpath() + C_File_Name,
				stringList);

	}

}
