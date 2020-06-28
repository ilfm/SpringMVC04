/*=================================================================================================================
 *  #06.IDepartmentDAO.java
 *  - 인터페이스  → 의존성 주입을 위해 만드는 것 
 * ================================================================================================================
 * */
package com.test.mvc;

import java.sql.SQLException;
import java.util.ArrayList;

public interface IDepartmentDAO
{

	public ArrayList<Department> list() throws SQLException;
	public int add(Department department) throws SQLException;
	public int remove(String departmentId) throws SQLException;
	public int modify(Department department) throws SQLException;
	
	
}
