package fc.Application.MVC.Model;

public class Movie
{
	public int m_Id;
	public String m_Title;
	public int m_Year;
	
	public Movie(int id, String title, int year)
	{
		m_Id = id;
		m_Title = title;
		m_Year = year;
	}
}
