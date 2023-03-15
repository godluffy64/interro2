package fc.Application.MVC.Views;

import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Combo;
import org.eclipse.swt.widgets.Dialog;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Table;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.widgets.TableColumn;
import org.eclipse.swt.widgets.TableItem;

import fc.Application.MVC.Controllers.ListClientController;
import fc.Application.MVC.ViewModels.ClientViewModel;


public class ClientDetailsView extends Dialog {

	protected Object result;
	protected Shell shlListe;
	private Table table;
	private Table table2;
	
	public RunController m_Infrastructure;
	
	protected ClientViewModel[] getViewModel()
	{
		if (m_Infrastructure != null)
			return (ClientViewModel[])m_Infrastructure.m_ViewModel;
		else
			return new ClientViewModel[0];
	}
	
	/**
	 * Create the dialog.
	 * @param parent
	 * @param style
	 */
	public ClientDetailsView(Shell parent, int style) {
		super(parent, style);
		setText("SWT Dialog");
	}

	/**
	 * Open the dialog.
	 * @return the result
	 */
	public Object open() {
		createContents();
		shlListe.open();
		shlListe.layout();
		Display display = getParent().getDisplay();
		while (!shlListe.isDisposed()) {
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

		shlListe = new Shell(getParent(), getStyle());
		shlListe.setSize(450, 400);
		shlListe.setText("Détails du client");
		
		// LIST VIEW


		table = new Table(shlListe, SWT.BORDER | SWT.FULL_SELECTION);
		table.setBounds(150, 30, 100, 25);


		table2 = new Table(shlListe, SWT.BORDER | SWT.FULL_SELECTION);
		table2.setBounds(150, 70, 100, 25);

		Label lblNom = new Label(shlListe, SWT.NONE);
		lblNom.setBounds(20,30,60,20);
		lblNom.setText("Nom");

		Label lblPrenom = new Label(shlListe, SWT.NONE);
		lblPrenom.setBounds(20,70,60,20);
		lblPrenom.setText("Prenom");
		
		TableColumn tcProduit = new TableColumn(table, SWT.NONE);
		tcProduit.setWidth(150);
		tcProduit.setText("Produit");
		

		Button btnRevenir = new Button(shlListe, SWT.NONE);
		btnRevenir.setBounds(275, 300, 150, 30);
		btnRevenir.setText("Lite des clients");

		
	}
}
