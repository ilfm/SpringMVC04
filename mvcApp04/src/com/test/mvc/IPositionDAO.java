/*=================================================================================================================
 *  #08.IPositionDAO.java
 *  - 인터페이스  → 의존성 주입을 위해 만드는 것 
 * ================================================================================================================
 * */
package com.test.mvc;

import java.sql.SQLException;
import java.util.ArrayList;

public interface IPositionDAO
{

	public ArrayList<Position> list()throws SQLException;
	public int add(Position position)throws SQLException;
	public int remove(String positionId)throws SQLException;
    public int modify(Position position)throws SQLException;	
	
	
}
