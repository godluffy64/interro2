package fc.Application.MVC.Views;

import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Dialog;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Shell;

import fc.Application.MVC.Controllers.ListMoviesController;

public class UpdateMovieView extends Dialog {

	protected Object result;
	protected Shell shlEditionRussie;
	
	public RunController m_Infrastructure;
	
	protected Integer getViewModel()
	{
		if (m_Infrastructure != null)
			return (Integer)m_Infrastructure.m_ViewModel;
		else
			return -1;
	}

	/**
	 * Create the dialog.
	 * @param parent
	 * @param style
	 */
	public UpdateMovieView(Shell parent, int style) {
		super(parent, style);
		setText("SWT Dialog");
	}

	/**
	 * Open the dialog.
	 * @return the result
	 */
	public Object open() {
		createContents();
		shlEditionRussie.open();
		shlEditionRussie.layout();
		Display display = getParent().getDisplay();
		while (!shlEditionRussie.isDisposed()) {
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
		shlEditionRussie = new Shell(getParent(), getStyle());
		shlEditionRussie.setSize(470, 262);
		shlEditionRussie.setText("Edition r\u00E9ussie");
		
		Label lblLeFilm = new Label(shlEditionRussie, SWT.NONE);
		lblLeFilm.setBounds(153, 68, 36, 15);
		lblLeFilm.setText("Le film");
		
		Label lblXx = new Label(shlEditionRussie, SWT.NONE);
		lblXx.setBounds(195, 68, 21, 15);
		lblXx.setText("" + getViewModel());
		
		Label lblABient = new Label(shlEditionRussie, SWT.NONE);
		lblABient.setText("a bien \u00E9t\u00E9 modifi\u00E9");
		lblABient.setBounds(222, 68, 100, 15);
		
		Button btnRevenirLa = new Button(shlEditionRussie, SWT.NONE);
		btnRevenirLa.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				m_Infrastructure.runController(shlEditionRussie, ListMoviesController.class);
			}
		});
		btnRevenirLa.setBounds(153, 105, 149, 25);
		btnRevenirLa.setText("Revenir \u00E0 la liste des films");

	}

}
