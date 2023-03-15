package fc.Application.MVC.Controllers;

public class UpdateMovieController extends Controller
{
	@Override
	public ActionResult run(Object ... args)
	{
		Integer movieNumber = (Integer)args[0];
		String titre = (String)args[1];
		Integer annee = (Integer)args[2];
		
		m_Model.m_Movies[movieNumber].m_Title = titre;
		m_Model.m_Movies[movieNumber].m_Year = annee;
		
		return View(movieNumber);
	}
}
