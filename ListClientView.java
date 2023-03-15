package fc.Application.MVC.Views;

import org.eclipse.swt.widgets.Combo;
import org.eclipse.swt.widgets.Dialog;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Table;
import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.TableColumn;
import org.eclipse.swt.widgets.TableItem;

import fc.Application.MVC.ViewModels.ClientViewModel;


public class ListClientView extends Dialog {

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
	public ListClientView(Shell parent, int style) {
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
		shlListe.setText("Liste des clients");
		
		// LIST VIEW

		Label lblClient = new Label(shlListe, SWT.NONE);
		lblClient.setBounds(50,30,60,20);
		lblClient.setText("Clients:");

		
		Combo combo = new Combo(shlListe, SWT.BORDER | SWT.READ_ONLY);
		
		combo.setSize(300,150);
		combo.setBounds(50,50,350,30);
		combo.add("Client 1");
		
		table = new Table(shlListe, SWT.BORDER | SWT.FULL_SELECTION);
		table.setBounds(50, 100, 650, 300);
		table.setHeaderVisible(true);
		table.setLinesVisible(true);
		
		TableColumn tcNum = new TableColumn(table, SWT.NONE);
		tcNum.setWidth(150);
		tcNum.setText("Num_commande");
		
		TableColumn tcDate = new TableColumn(table, SWT.NONE);
		tcDate.setWidth(150);
		tcDate.setText("Date");

		TableColumn tcNomComplet = new TableColumn(table, SWT.NONE);
		tcNomComplet.setWidth(150);
		tcNomComplet.setText("Nom et prénom");

		TableColumn tcMontantTotal = new TableColumn(table, SWT.NONE);
		tcMontantTotal.setWidth(150);
		tcMontantTotal.setText("Montant total");
		
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
