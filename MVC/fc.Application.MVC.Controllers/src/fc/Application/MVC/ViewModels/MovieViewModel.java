package fc.Application.MVC.ViewModels;

public class MovieViewModel
{
	public int m_Id;
	public String m_Title;
	public int m_Year;
	
	// EXEMPLE DE VIEW MODEL A FAIRE
	public MovieViewModel(int id, String title, int year)
	{
		m_Id = id;
		m_Title = title;
		m_Year = year;
	}
}
