package com.example.csv_to_db;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

@SpringBootApplication
public class CsvToDbApplication extends Detector{

	public static void main(String[] args) {
		//Run application server on Access http://localhost:8080/ from spring boot MVN command: mvn spring-boot:run
		SpringApplication.run(CsvToDbApplication.class, args);
		System.out.println("Hello people");

		/*
		//TEST application bellow this comment
		// JDBC driver name and database URL
		final String JDBC_DRIVER = "org.h2.Driver";
		final String DB_URL = "jdbc:h2:~/test";

		//  Database credentials
		final String USER = "sa";
		final String PASS = "";
		Connection conn = null;
		Statement stmt = null;
		int line_count = 0;
		//SQL querry to create database
		String sql_create_db="";
		//create first line and line to add to SQL databse
		String db_first_line="";
		String db_line="";

		//First line of csv file
		String first_line = "";
		//Second line of csv file
		String second_line = "";

		//change name to lowercase for database insertion
		String csv_file="electronic.csv";
		String csv_file_db = csv_file.toLowerCase();
		csv_file_db = csv_file_db.replace(".", "");


		//Reading String from file
		try {
			//reading file
			BufferedReader bReader = new BufferedReader(new FileReader(csv_file));
			String line = "";
			//read one line at a time
			while ((line = bReader.readLine()) != null) {
				//split lines and place string values in array
				String[] array = line.split(",+");
				//SQL pavadinimu masyvas
				try {
					//if not end of file read one line at a time
					if (line != null) {
						//detect if first line
						if (line_count == 0) {
							int i = 0;
							for (String result : array) {
								first_line += array[i] + ", ";
								i++;
							}
							System.out.println(first_line);
						}
						//detect if second line
						//adding values to insert statement
						if (line_count == 1) {
							//array counter
							int j = 0;
							//last number counter
							int sk = 1;
							String[] db_array = first_line.split(",+");
							for (String result : array) {
								second_line += "'" + array[j] + "', ";
								if (isInt(array[j])) {
									if (array.length==sk) {
										sql_create_db += db_array[j] + " INT";
										db_first_line+=array[j];
									}
									else {
										sql_create_db += db_array[j] + " INT, ";
										db_first_line+=array[j]+", ";
									}
								}
								else if (isDouble(array[j])) {
									if (array.length==sk) {
										sql_create_db += db_array[j] + " DOUBLE";
										db_first_line+=array[j];
									}
									else {
										sql_create_db += db_array[j] + " DOUBLE, ";
										db_first_line+=array[j]+", ";
									}
								}
								else {
									if (array.length==sk) {
										sql_create_db += db_array[j] + " VARCHAR(100)";
										db_first_line+="'"+ array[j] + "' ";
									}
									else {
										sql_create_db += db_array[j] + " VARCHAR(100), ";
										db_first_line+="'"+ array[j] + "', ";
									}
								}
								j++;
								sk++;
							}
							//Working with H2 database
							System.out.println(db_first_line);
							System.out.println("Database querry:"+sql_create_db);
							//creating database from csv file
							String db_created=tableCreate(csv_file_db, sql_create_db);
							System.out.println(second_line);
							System.out.println(db_created);
							//insert first line into database
							System.out.println("Inserting first line in given database...");
							try {
								conn = DriverManager.getConnection(DB_URL, USER, PASS);
							} catch (SQLException e) {
								throw new RuntimeException(e);
							}
							try {
								stmt = conn.createStatement();
							} catch (SQLException e) {
								throw new RuntimeException(e);
							}
							//line=line.replace(" ", "");
							String sql = "INSERT INTO "+ csv_file_db +" VALUES (" + db_first_line + ");";
							try {
								stmt.executeUpdate(sql);
							} catch (SQLException e) {
								throw new RuntimeException(e);
							}
							System.out.println("First line inserted");
							//close connection
							try {
								stmt.close();
							} catch (SQLException e) {
								throw new RuntimeException(e);
							}
							try {
								conn.close();
							} catch (SQLException e) {
								throw new RuntimeException(e);
							}
						}
						if (line_count>1) {
							try {
								//Working with H2 database
								db_line = "";
								// STEP 1: Register JDBC driver
								Class.forName(JDBC_DRIVER);

								//STEP 2: Open a connection
								System.out.println("Connecting to database...");
								conn = DriverManager.getConnection(DB_URL, USER, PASS);

								//adding values to insert statement
								int k=0;
								int sk2=1;
								String[] database_array = line.split(",+");
								for (String result : database_array) {
									if (database_array.length==sk2) {
										if (isInt(database_array[k]) || isDouble(database_array[k])) {
											db_line +=database_array[k];
										}
										else {
											database_array[k] = database_array[k].replace(",", "");
											db_line += "'" + database_array[k] + "' ";
										}
									}
									else {
										if (isInt(database_array[k]) || isDouble(database_array[k])) {
											db_line += database_array[k] + ", ";
										} else {
											database_array[k] = database_array[k].replace(",", "");
											db_line += "'" + database_array[k] + "', ";
										}
										k++;
										sk2++;
									}
								}

								//STEP 3: Execute a query
								System.out.println("Inserting data in given database...");
								stmt = conn.createStatement();
								//line=line.replace(" ", "");
								String sql = "INSERT INTO "+ csv_file_db +" VALUES (" + db_line + ");";
								stmt.executeUpdate(sql);

								// STEP 4: Clean-up environment
								stmt.close();
								conn.close();
								db_line="";
								System.out.println("Data inserted");
							} catch (SQLException se) {
								//Handle errors for JDBC
								se.printStackTrace();
							} catch (Exception e) {
								//Handle errors for Class.forName
								e.printStackTrace();
							} finally {
								//finally block used to close resources
								try {
									if (stmt != null) stmt.close();
								} catch (SQLException se2) {
								} // nothing we can do
								try {
									if (conn != null) conn.close();
								} catch (SQLException se) {
									se.printStackTrace();
								} //end finally try
							} //end try
							System.out.println("Goodbye!");
						}
						line_count++;
						for (String result : array) {
							System.out.println(result);
						}
					}
				} finally {
					if (bReader == null) {
						bReader.close();
					}
				}
			}
			//catch exceptions
		} catch (FileNotFoundException ex) {
			ex.printStackTrace();
		} catch (IOException e) {
			throw new RuntimeException(e);
		}
	*/

	}
}
