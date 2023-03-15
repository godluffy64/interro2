package fc.Application.MVC.Views;

import org.eclipse.swt.widgets.Combo;
import org.eclipse.swt.widgets.Dialog;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Table;
import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.TableColumn;
import org.eclipse.swt.widgets.TableItem;

import fc.Application.MVC.ViewModels.MovieViewModel;

public class ListMoviesView extends Dialog {

	protected Object result;
	protected Shell shlListeDesFilms;
	private Table table;
	
	public RunController m_Infrastructure;
	
	protected MovieViewModel[] getViewModel()
	{
		if (m_Infrastructure != null)
			return (MovieViewModel[])m_Infrastructure.m_ViewModel;
		else
			return new MovieViewModel[0];
	}
	
	/**
	 * Create the dialog.
	 * @param parent
	 * @param style
	 */
	public ListMoviesView(Shell parent, int style) {
		super(parent, style);
		setText("SWT Dialog");
	}

	/**
	 * Open the dialog.
	 * @return the result
	 */
	public Object open() {
		createContents();
		shlListeDesFilms.open();
		shlListeDesFilms.layout();
		Display display = getParent().getDisplay();
		while (!shlListeDesFilms.isDisposed()) {
			if (!display.readAndDispatch()) {
				display.sleep();
			}
		}
		return result;
	}

	/**
	 * Create contents of the dialog.
	 */
	private void createContents() {

		shlListeDesFilms = new Shell(getParent(), getStyle());
		shlListeDesFilms.setSize(450, 300);
		shlListeDesFilms.setText("Liste des films");
		// LIST VIEW

		Combo combo = new Combo(shlListeDesFilms, SWT.BORDER | SWT.READ_ONLY);
		
		combo.setSize(100,41);
		combo.add("element 1");
		
		table = new Table(shlListeDesFilms, SWT.BORDER | SWT.FULL_SELECTION);
		table.setBounds(10, 10, 424, 251);
		table.setHeaderVisible(true);
		table.setLinesVisible(true);
		
		TableColumn tcId = new TableColumn(table, SWT.NONE);
		tcId.setWidth(100);
		tcId.setText("Id");
		
		TableColumn tcTitre = new TableColumn(table, SWT.NONE);
		tcTitre.setWidth(100);
		tcTitre.setText("Titre");
		
		TableColumn tcAnnee = new TableColumn(table, SWT.NONE);
		tcAnnee.setWidth(100);
		tcAnnee.setText("Annee");
		


		// MOVIE VIEW MODEL EST ICI FAUT MODIFIER CA POUR VOIR LA BASE DE DONNEE
		MovieViewModel[] movies = getViewModel();
		
		for (MovieViewModel movie : movies)
		{
		    TableItem item = new TableItem(table, SWT.NONE);
		    item.setText(new String[] { ""+movie.m_Id, movie.m_Title, ""+movie.m_Year });
		}
	}
}
