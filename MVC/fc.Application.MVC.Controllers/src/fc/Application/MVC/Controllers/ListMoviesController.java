package fc.Application.MVC.Controllers;

import fc.Application.MVC.Model.Movie;
import fc.Application.MVC.ViewModels.MovieViewModel;

public class ListMoviesController extends Controller
{
	@Override
	public ActionResult run(Object ... args)
	{

		// AFFICHE LA LISTE DES MOVIES AVEC MOVIEVIEWMODEL
		
		Movie[] movies = m_Model.m_Movies;
		
		MovieViewModel[] movieViewModel = new MovieViewModel[movies.length]; // (movie.m_Id, movie.m_Title, movie.m_Year);


		for (int i=0; i < movies.length; i++)
		{
			Movie movie = movies[i];
			movieViewModel[i] = new MovieViewModel(movie.m_Id, movie.m_Title, movie.m_Year);
		}
		
		return View(movieViewModel);
	}
}
