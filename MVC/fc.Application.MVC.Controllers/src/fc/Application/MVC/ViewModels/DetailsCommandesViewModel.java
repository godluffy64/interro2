package fc.Application.MVC.ViewModels;

import java.sql.Date;

public class DetailsCommandesViewModel 
{
    public int m_Num;
    public Date m_Date;
    public String m_Nom;
    public String m_Prenom;
    public Double m_Montant;

    public DetailsCommandesViewModel(int num,Date d, String nom, String  prenom, Double montant )
	{
		m_Num = num;
		m_Date = d;
		m_Nom = nom;
        m_Prenom = prenom;
        m_Montant = montant;
	}

}


/*
 * - numéro de commande
- date de la commande
- le nom et prénom du client
- le montant total de la commande
 */
