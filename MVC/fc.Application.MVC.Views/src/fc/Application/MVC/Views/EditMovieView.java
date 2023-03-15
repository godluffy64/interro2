package fc.Application.MVC.Views;

import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Dialog;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Text;

import fc.Application.MVC.Controllers.UpdateMovieController;
import fc.Application.MVC.ViewModels.MovieViewModel;

public class EditMovieView extends Dialog {

	protected Object result;
	protected Shell shlEditMovie;
	private Text txtTitre;
	private Text txtAnnee;
	
	public RunController m_Infrastructure;
	
	protected MovieViewModel getViewModel()
	{
		return (MovieViewModel)m_Infrastructure.m_ViewModel;
	}

	/**
	 * Create the dialog.
	 * @param parent
	 * @param style
	 */
	public EditMovieView(Shell parent, int style) {
		super(parent, style);
		setText("SWT Dialog");
	}

	/**
	 * Open the dialog.
	 * @return the result
	 */
	public Object open() {
		createContents();
		shlEditMovie.open();
		shlEditMovie.layout();
		Display display = getParent().getDisplay();
		while (!shlEditMovie.isDisposed()) {
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
		shlEditMovie = new Shell(getParent(), getStyle());
		shlEditMovie.setSize(365, 229);
		shlEditMovie.setText("Edit movie");
		
		Button btnSauver = new Button(shlEditMovie, SWT.NONE);
		btnSauver.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				String nouveauTitre = txtTitre.getText();
				int nouvelleAnnee = Integer.parseInt(txtAnnee.getText());
				
				m_Infrastructure.runController(shlEditMovie, UpdateMovieController.class, getViewModel().m_Id, nouveauTitre, nouvelleAnnee);
			}
		});
		btnSauver.setBounds(135, 114, 75, 25);
		btnSauver.setText("Sauver");
		
		txtTitre = new Text(shlEditMovie, SWT.BORDER);
		txtTitre.setBounds(154, 38, 76, 21);
		txtTitre.setText(getViewModel().m_Title);
		
		txtAnnee = new Text(shlEditMovie, SWT.BORDER);
		txtAnnee.setBounds(154, 68, 76, 21);
		txtAnnee.setText("" + getViewModel().m_Year);
		
		Label lblTitre = new Label(shlEditMovie, SWT.NONE);
		lblTitre.setBounds(93, 41, 55, 15);
		lblTitre.setText("Titre");
		
		Label lblAnne = new Label(shlEditMovie, SWT.NONE);
		lblAnne.setBounds(93, 71, 55, 15);
		lblAnne.setText("Ann\u00E9e");

	}
}
