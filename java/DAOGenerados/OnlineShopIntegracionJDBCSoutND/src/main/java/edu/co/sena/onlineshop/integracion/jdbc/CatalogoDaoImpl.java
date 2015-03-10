/*
 * This source file was generated by FireStorm/DAO.
 * 
 * If you purchase a full license for FireStorm/DAO you can customize this header file.
 * 
 * For more information please visit http://www.codefutures.com/products/firestorm
 */

package edu.co.sena.onlineshop.integracion.jdbc;

import edu.co.sena.onlineshop.integracion.dao.*;
import edu.co.sena.onlineshop.integracion.factory.*;
import edu.co.sena.onlineshop.integracion.dto.*;
import edu.co.sena.onlineshop.integracion.exceptions.*;
import java.sql.Connection;
import java.util.Collection;
import java.sql.PreparedStatement;
import java.sql.Statement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Time;
import java.util.List;
import java.util.Iterator;
import java.util.ArrayList;

public class CatalogoDaoImpl extends AbstractDAO implements CatalogoDao
{
	/** 
	 * The factory class for this DAO has two versions of the create() method - one that
takes no arguments and one that takes a Connection argument. If the Connection version
is chosen then the connection will be stored in this attribute and will be used by all
calls to this DAO, otherwise a new Connection will be allocated for each operation.
	 */
	protected java.sql.Connection userConn;

	/** 
	 * All finder methods in this class use this SELECT constant to build their queries
	 */
	protected final String SQL_SELECT = "SELECT ID_CATALOGO, NOMBRE_CATALOGO, DECRIPCION FROM " + getTableName() + "";

	/** 
	 * Finder methods will pass this value to the JDBC setMaxRows method
	 */
	protected int maxRows;

	/** 
	 * SQL INSERT statement for this table
	 */
	protected final String SQL_INSERT = "INSERT INTO " + getTableName() + " ( ID_CATALOGO, NOMBRE_CATALOGO, DECRIPCION ) VALUES ( ?, ?, ? )";

	/** 
	 * SQL UPDATE statement for this table
	 */
	protected final String SQL_UPDATE = "UPDATE " + getTableName() + " SET ID_CATALOGO = ?, NOMBRE_CATALOGO = ?, DECRIPCION = ? WHERE ID_CATALOGO = ?";

	/** 
	 * SQL DELETE statement for this table
	 */
	protected final String SQL_DELETE = "DELETE FROM " + getTableName() + " WHERE ID_CATALOGO = ?";

	/** 
	 * Index of column ID_CATALOGO
	 */
	protected static final int COLUMN_ID_CATALOGO = 1;

	/** 
	 * Index of column NOMBRE_CATALOGO
	 */
	protected static final int COLUMN_NOMBRE_CATALOGO = 2;

	/** 
	 * Index of column DECRIPCION
	 */
	protected static final int COLUMN_DECRIPCION = 3;

	/** 
	 * Number of columns
	 */
	protected static final int NUMBER_OF_COLUMNS = 3;

	/** 
	 * Index of primary-key column ID_CATALOGO
	 */
	protected static final int PK_COLUMN_ID_CATALOGO = 1;

	/** 
	 * Inserts a new row in the catalogo table.
	 */
	public CatalogoPk insert(Catalogo dto) throws CatalogoDaoException
	{
		long t1 = System.currentTimeMillis();
		// declare variables
		final boolean isConnSupplied = (userConn != null);
		Connection conn = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;
		
		try {
			// get the user-specified connection or get a connection from the ResourceManager
			conn = isConnSupplied ? userConn : ResourceManager.getConnection();
		
			stmt = conn.prepareStatement( SQL_INSERT, Statement.RETURN_GENERATED_KEYS );
			int index = 1;
			stmt.setInt( index++, dto.getIdCatalogo() );
			stmt.setString( index++, dto.getNombreCatalogo() );
			stmt.setString( index++, dto.getDecripcion() );
			System.out.println( "Executing " + SQL_INSERT + " with DTO: " + dto );
			int rows = stmt.executeUpdate();
			long t2 = System.currentTimeMillis();
			System.out.println( rows + " rows affected (" + (t2-t1) + " ms)" );
		
			// retrieve values from auto-increment columns
			rs = stmt.getGeneratedKeys();
			if (rs != null && rs.next()) {
				dto.setIdCatalogo( rs.getInt( 1 ) );
			}
		
			reset(dto);
			return dto.createPk();
		}
		catch (Exception _e) {
			_e.printStackTrace();
			throw new CatalogoDaoException( "Exception: " + _e.getMessage(), _e );
		}
		finally {
			ResourceManager.close(stmt);
			if (!isConnSupplied) {
				ResourceManager.close(conn);
			}
		
		}
		
	}

	/** 
	 * Updates a single row in the catalogo table.
	 */
	public void update(CatalogoPk pk, Catalogo dto) throws CatalogoDaoException
	{
		long t1 = System.currentTimeMillis();
		// declare variables
		final boolean isConnSupplied = (userConn != null);
		Connection conn = null;
		PreparedStatement stmt = null;
		
		try {
			// get the user-specified connection or get a connection from the ResourceManager
			conn = isConnSupplied ? userConn : ResourceManager.getConnection();
		
			System.out.println( "Executing " + SQL_UPDATE + " with DTO: " + dto );
			stmt = conn.prepareStatement( SQL_UPDATE );
			int index=1;
			stmt.setInt( index++, dto.getIdCatalogo() );
			stmt.setString( index++, dto.getNombreCatalogo() );
			stmt.setString( index++, dto.getDecripcion() );
			stmt.setInt( 4, pk.getIdCatalogo() );
			int rows = stmt.executeUpdate();
			reset(dto);
			long t2 = System.currentTimeMillis();
			System.out.println( rows + " rows affected (" + (t2-t1) + " ms)" );
		}
		catch (Exception _e) {
			_e.printStackTrace();
			throw new CatalogoDaoException( "Exception: " + _e.getMessage(), _e );
		}
		finally {
			ResourceManager.close(stmt);
			if (!isConnSupplied) {
				ResourceManager.close(conn);
			}
		
		}
		
	}

	/** 
	 * Deletes a single row in the catalogo table.
	 */
	public void delete(CatalogoPk pk) throws CatalogoDaoException
	{
		long t1 = System.currentTimeMillis();
		// declare variables
		final boolean isConnSupplied = (userConn != null);
		Connection conn = null;
		PreparedStatement stmt = null;
		
		try {
			// get the user-specified connection or get a connection from the ResourceManager
			conn = isConnSupplied ? userConn : ResourceManager.getConnection();
		
			System.out.println( "Executing " + SQL_DELETE + " with PK: " + pk );
			stmt = conn.prepareStatement( SQL_DELETE );
			stmt.setInt( 1, pk.getIdCatalogo() );
			int rows = stmt.executeUpdate();
			long t2 = System.currentTimeMillis();
			System.out.println( rows + " rows affected (" + (t2-t1) + " ms)" );
		}
		catch (Exception _e) {
			_e.printStackTrace();
			throw new CatalogoDaoException( "Exception: " + _e.getMessage(), _e );
		}
		finally {
			ResourceManager.close(stmt);
			if (!isConnSupplied) {
				ResourceManager.close(conn);
			}
		
		}
		
	}

	/** 
	 * Returns the rows from the catalogo table that matches the specified primary-key value.
	 */
	public Catalogo findByPrimaryKey(CatalogoPk pk) throws CatalogoDaoException
	{
		return findByPrimaryKey( pk.getIdCatalogo() );
	}

	/** 
	 * Returns all rows from the catalogo table that match the criteria 'ID_CATALOGO = :idCatalogo'.
	 */
	public Catalogo findByPrimaryKey(int idCatalogo) throws CatalogoDaoException
	{
		Catalogo ret[] = findByDynamicSelect( SQL_SELECT + " WHERE ID_CATALOGO = ?", new Object[] {  new Integer(idCatalogo) } );
		return ret.length==0 ? null : ret[0];
	}

	/** 
	 * Returns all rows from the catalogo table that match the criteria ''.
	 */
	public Catalogo[] findAll() throws CatalogoDaoException
	{
		return findByDynamicSelect( SQL_SELECT + " ORDER BY ID_CATALOGO", null );
	}

	/** 
	 * Returns all rows from the catalogo table that match the criteria 'ID_CATALOGO = :idCatalogo'.
	 */
	public Catalogo[] findWhereIdCatalogoEquals(int idCatalogo) throws CatalogoDaoException
	{
		return findByDynamicSelect( SQL_SELECT + " WHERE ID_CATALOGO = ? ORDER BY ID_CATALOGO", new Object[] {  new Integer(idCatalogo) } );
	}

	/** 
	 * Returns all rows from the catalogo table that match the criteria 'NOMBRE_CATALOGO = :nombreCatalogo'.
	 */
	public Catalogo[] findWhereNombreCatalogoEquals(String nombreCatalogo) throws CatalogoDaoException
	{
		return findByDynamicSelect( SQL_SELECT + " WHERE NOMBRE_CATALOGO = ? ORDER BY NOMBRE_CATALOGO", new Object[] { nombreCatalogo } );
	}

	/** 
	 * Returns all rows from the catalogo table that match the criteria 'DECRIPCION = :decripcion'.
	 */
	public Catalogo[] findWhereDecripcionEquals(String decripcion) throws CatalogoDaoException
	{
		return findByDynamicSelect( SQL_SELECT + " WHERE DECRIPCION = ? ORDER BY DECRIPCION", new Object[] { decripcion } );
	}

	/**
	 * Method 'CatalogoDaoImpl'
	 * 
	 */
	public CatalogoDaoImpl()
	{
	}

	/**
	 * Method 'CatalogoDaoImpl'
	 * 
	 * @param userConn
	 */
	public CatalogoDaoImpl(final java.sql.Connection userConn)
	{
		this.userConn = userConn;
	}

	/** 
	 * Sets the value of maxRows
	 */
	public void setMaxRows(int maxRows)
	{
		this.maxRows = maxRows;
	}

	/** 
	 * Gets the value of maxRows
	 */
	public int getMaxRows()
	{
		return maxRows;
	}

	/**
	 * Method 'getTableName'
	 * 
	 * @return String
	 */
	public String getTableName()
	{
		return "onlineshop.catalogo";
	}

	/** 
	 * Fetches a single row from the result set
	 */
	protected Catalogo fetchSingleResult(ResultSet rs) throws SQLException
	{
		if (rs.next()) {
			Catalogo dto = new Catalogo();
			populateDto( dto, rs);
			return dto;
		} else {
			return null;
		}
		
	}

	/** 
	 * Fetches multiple rows from the result set
	 */
	protected Catalogo[] fetchMultiResults(ResultSet rs) throws SQLException
	{
		Collection resultList = new ArrayList();
		while (rs.next()) {
			Catalogo dto = new Catalogo();
			populateDto( dto, rs);
			resultList.add( dto );
		}
		
		Catalogo ret[] = new Catalogo[ resultList.size() ];
		resultList.toArray( ret );
		return ret;
	}

	/** 
	 * Populates a DTO with data from a ResultSet
	 */
	protected void populateDto(Catalogo dto, ResultSet rs) throws SQLException
	{
		dto.setIdCatalogo( rs.getInt( COLUMN_ID_CATALOGO ) );
		dto.setNombreCatalogo( rs.getString( COLUMN_NOMBRE_CATALOGO ) );
		dto.setDecripcion( rs.getString( COLUMN_DECRIPCION ) );
	}

	/** 
	 * Resets the modified attributes in the DTO
	 */
	protected void reset(Catalogo dto)
	{
	}

	/** 
	 * Returns all rows from the catalogo table that match the specified arbitrary SQL statement
	 */
	public Catalogo[] findByDynamicSelect(String sql, Object[] sqlParams) throws CatalogoDaoException
	{
		// declare variables
		final boolean isConnSupplied = (userConn != null);
		Connection conn = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;
		
		try {
			// get the user-specified connection or get a connection from the ResourceManager
			conn = isConnSupplied ? userConn : ResourceManager.getConnection();
		
			// construct the SQL statement
			final String SQL = sql;
		
		
			System.out.println( "Executing " + SQL );
			// prepare statement
			stmt = conn.prepareStatement( SQL );
			stmt.setMaxRows( maxRows );
		
			// bind parameters
			for (int i=0; sqlParams!=null && i<sqlParams.length; i++ ) {
				stmt.setObject( i+1, sqlParams[i] );
			}
		
		
			rs = stmt.executeQuery();
		
			// fetch the results
			return fetchMultiResults(rs);
		}
		catch (Exception _e) {
			_e.printStackTrace();
			throw new CatalogoDaoException( "Exception: " + _e.getMessage(), _e );
		}
		finally {
			ResourceManager.close(rs);
			ResourceManager.close(stmt);
			if (!isConnSupplied) {
				ResourceManager.close(conn);
			}
		
		}
		
	}

	/** 
	 * Returns all rows from the catalogo table that match the specified arbitrary SQL statement
	 */
	public Catalogo[] findByDynamicWhere(String sql, Object[] sqlParams) throws CatalogoDaoException
	{
		// declare variables
		final boolean isConnSupplied = (userConn != null);
		Connection conn = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;
		
		try {
			// get the user-specified connection or get a connection from the ResourceManager
			conn = isConnSupplied ? userConn : ResourceManager.getConnection();
		
			// construct the SQL statement
			final String SQL = SQL_SELECT + " WHERE " + sql;
		
		
			System.out.println( "Executing " + SQL );
			// prepare statement
			stmt = conn.prepareStatement( SQL );
			stmt.setMaxRows( maxRows );
		
			// bind parameters
			for (int i=0; sqlParams!=null && i<sqlParams.length; i++ ) {
				stmt.setObject( i+1, sqlParams[i] );
			}
		
		
			rs = stmt.executeQuery();
		
			// fetch the results
			return fetchMultiResults(rs);
		}
		catch (Exception _e) {
			_e.printStackTrace();
			throw new CatalogoDaoException( "Exception: " + _e.getMessage(), _e );
		}
		finally {
			ResourceManager.close(rs);
			ResourceManager.close(stmt);
			if (!isConnSupplied) {
				ResourceManager.close(conn);
			}
		
		}
		
	}

}