/*=================================================================================================================
 *  #06.IDepartmentDAO.java
 *  - 인터페이스  → 의존성 주입을 위해 만드는 것 
 * ================================================================================================================
 * */
package com.test.mvc;

import java.sql.SQLException;
import java.util.ArrayList;

public interface IRegionDAO
{

	public ArrayList<Region> list()throws SQLException;
	public int add(Region region)throws SQLException;
	public int remove(String regionId)throws SQLException;
    public int modify(Region region)throws SQLException;
	public Region search(int regionId) throws SQLException;
	
	
}
