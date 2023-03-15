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


public class DetailsCommandeView extends Dialog {

	protected Object result;
	protected Shell shlListe;
	private Table table;
	
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
	public DetailsCommandeView(Shell parent, int style) {
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
		shlListe.setSize(750, 600);
		shlListe.setText("Détails de la commande ");
		
		// LIST VIEW


		table = new Table(shlListe, SWT.BORDER | SWT.FULL_SELECTION);
		table.setBounds(25, 40, 700, 475);
		table.setHeaderVisible(true);
		table.setLinesVisible(true);
		
		TableColumn tcProduit = new TableColumn(table, SWT.NONE);
		tcProduit.setWidth(150);
		tcProduit.setText("Produit");
		
		TableColumn tcQuantite = new TableColumn(table, SWT.NONE);
		tcQuantite.setWidth(150);
		tcQuantite.setText("Quantite");

		TableColumn tcPrix = new TableColumn(table, SWT.NONE);
		tcPrix.setWidth(150);
		tcPrix.setText("Prix unitaire");

		TableColumn tcDiscount = new TableColumn(table, SWT.NONE);
		tcDiscount.setWidth(150);
		tcDiscount.setText("Discount");

		Button btnRevenir = new Button(shlListe, SWT.NONE);
		btnRevenir.setBounds(625, 520, 100, 30);
		btnRevenir.setText("Revenir");

		// COMMANDE CONTIENT LISTE COMMANDE DU CLIENT

		// MOVIE VIEW MODEL EST ICI FAUT MODIFIER CA POUR VOIR LA BASE DE DONNEE
		ClientViewModel[] Clients = getViewModel();
		
		for (ClientViewModel Client : Clients)
		{
		    TableItem item = new TableItem(table, SWT.NONE);
		    item.setText(new String[] { ""+Client.m_Id+" ", Client.m_Nom });
		}
	}
}
