package fc.Application.MVC.Controllers;

import fc.Application.MVC.Model.Movie;
import fc.Application.MVC.ViewModels.MovieViewModel;

public class EditMovieController extends Controller
{
	@Override
	public ActionResult run(Object ... args)
	{
		Integer movieId = (Integer)args[0];
		
		Movie movie = m_Model.m_Movies[movieId];
		MovieViewModel movieViewModel = new MovieViewModel(movie.m_Id, movie.m_Title, movie.m_Year);
		
		return View(movieViewModel);
	}
}
