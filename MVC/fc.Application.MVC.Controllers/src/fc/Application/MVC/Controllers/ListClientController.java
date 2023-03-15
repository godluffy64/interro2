package fc.Application.MVC.Controllers;

import fc.Application.MVC.Model.Client;
import fc.Application.MVC.ViewModels.ClientViewModel;

public class ListClientController extends Controller
{
	@Override
	public ActionResult run(Object ... args)
	{

		// Doit Afficher les CLients

		// AFFICHE LA LISTE DES MOVIES AVEC MOVIEVIEWMODEL
		
		Client[] Clients = m_Model.m_Clients;
		
		ClientViewModel[] clientViewModel = new ClientViewModel[Clients.length]; // (movie.m_Id, movie.m_Title, movie.m_Year);


		for (int i=0; i < Clients.length; i++)
		{
			Client client = Clients[i];
			clientViewModel[i] = new ClientViewModel(client.m_LastName, client.m_Surname);
		}
		
		return View(clientViewModel);
	}
}
