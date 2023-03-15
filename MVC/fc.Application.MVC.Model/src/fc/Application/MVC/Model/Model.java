package fc.Application.MVC.Model;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.lang.ProcessBuilder.Redirect.Type;
import java.sql.Array;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Types;
import java.util.Scanner;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;

import javax.sql.rowset.serial.SerialArray;

import org.h2.tools.SimpleResultSet;


public class Model
{
	public Movie[] m_Movies;
	
    public Client[] m_Clients;
    private static Model s_ModelClient = null;

	private static Model s_Model = null;
	public static Model getModel()
	{
		if (s_Model == null)
		{
			Model m =  new Model();
			m.m_Movies = new Movie[] {
					new Movie(0, "Terminator", 1984),
					new Movie(1, "Alien", 1979)
			};
			s_Model = m;
		}
		
		return s_Model;
	}

    public static void CreateDatabase(){

        String connUrl = "jdbc:h2:./Database/h2database";
        String username = "sa";
        String password = "";
        

        try (Connection conn = DriverManager.getConnection(connUrl,username,password)){

			Statement stat = conn.createStatement();
            
            stat.execute("CREATE ALIAS NORTHWIND FOR \"fc.Application.MVC.Model.Model.getCSV\"");
        }
        catch (Exception e){
            e.printStackTrace(System.err);
        }


    }

	public static Model getClient(){

        // A décommenter afin de recréer la base de donnée.

        //Model.CreateDatabase();



		String connUrl = "jdbc:h2:./Database/h2database";
        String username = "sa";
        String password = "";
        

        try (Connection conn = DriverManager.getConnection(connUrl,username,password)){

            PreparedStatement prep = conn.prepareStatement("SELECT `LAST NAME`, `FIRST NAME` FROM NORTHWIND('Customers')");
                
            ResultSet rs = prep.executeQuery();


            Model m = new Model();
            ArrayList<String> listeLastName = new ArrayList<>();
            ArrayList<String> listeSurname = new ArrayList<>();
            while (rs.next()) {
                String LastName = rs.getString(1);
				String Surname = rs.getString(2);
                
                listeLastName.add(LastName);
                listeSurname.add(Surname);

            }       

            Client[] listeClient = new Client[listeLastName.size()];
            for(int i = 0; i<listeLastName.size();i++){
                listeClient[i] = new Client(listeLastName.get(i),listeSurname.get(i));
            }
            m.m_Clients = listeClient;

            s_ModelClient = m;



            
            prep.close();
        }
        catch (Exception e){
            e.printStackTrace(System.err);
        }
		return s_ModelClient;
	}		

	public static ResultSet getCSV(Connection conn, String NomDeTable) throws SQLException{
    
        ResultSet rs = new SimpleResultSet();

        String Path = "./Database/" + NomDeTable + ".csv";

        Scanner scanner;
        try {
            scanner = new Scanner(new File(Path));

            // Chaque case d'une ligne est séparé par ;
            // Chaque ligne est séparé par un retour à la ligne soit \n

            scanner.useDelimiter(";");


            String[] ligne = scanner.nextLine().split(";(?=(?:[^\"]*\"[^\"]*\")*[^\"]*$)", -1);

            // Supprime le premier élément du fichier CSV, utilisé pour indiquer le début des données en CSV
            ligne[0] = ligne[0].substring(1);


            for(int i = 0; i<ligne.length;i++){

                ((SimpleResultSet) rs).addColumn(ligne[i].toUpperCase(),Types.VARCHAR,10,0);
            }

            
            
            while(scanner.hasNext()){

                ligne = scanner.nextLine().split(";(?=(?:[^\"]*\"[^\"]*\")*[^\"]*$)", -1);

                ((SimpleResultSet) rs).addRow(ligne);
                
            }


            
            
            scanner.close();
            return rs; 

        }catch(Exception E){
            System.out.println(E);
            return null;
        } 
    }


    // public static Model getDetailsCommandes(String NomPrenom){

	// 	String connUrl = "jdbc:h2:./fc/Application/MVC/Model/base_de_donnees/h2database";
    //     String username = "sa";
    //     String password = "";
        

    //     try (Connection conn = DriverManager.getConnection(connUrl,username,password)){

    //         String[] NomComplet = NomPrenom.split(" ");

    //         String LastName = NomComplet[1];
    //         String Surname = NomComplet[2];

    //         PreparedStatement prep = conn.prepareStatement("SELECT `ORDER ID`, `ORDER DATE`,`Unit Price`,`Quantity` FROM NORTHWIND('Customers') WHERE "+ LastName+"");
                
    //         ResultSet rs = prep.executeQuery();

    //         while (rs.next()) {
    //             String LastName = rs.getString(1);
	// 			String FirstName = rs.getString(2);
    //         }       
            
    //         prep.close();
    //     }
    //     catch (Exception e){
    //         e.printStackTrace(System.err);
    //     }
	// 	return s_Model;
	// }		

}
