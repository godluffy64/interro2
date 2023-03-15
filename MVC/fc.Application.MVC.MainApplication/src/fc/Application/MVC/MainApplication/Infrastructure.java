package fc.Application.MVC.MainApplication;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Combo;
import org.eclipse.swt.widgets.Dialog;
import org.eclipse.swt.widgets.Shell;

import fc.Application.MVC.Controllers.ActionResult;
import fc.Application.MVC.Controllers.Controller;
import fc.Application.MVC.Controllers.EditMovieController;
import fc.Application.MVC.Controllers.ListClientController;
import fc.Application.MVC.Model.Model;

import fc.Application.MVC.Views.RunController;

public class Infrastructure
{
	protected Model m_Model;
	protected Shell m_Shell;

	protected Model m_ModelClient;
	
	public static void main(String[] args)
	{
		new Infrastructure().run();
	}
	
	public void run()
	{
		//m_Model = Model.getModel();

		// A utiliser si la base n'a pas été crée.

		m_ModelClient = Model.getClient();
		m_Shell = new Shell();
		m_Shell.setSize(450, 216);
		m_Shell.setText("Application L3 Genie Logiciel");
		m_Shell.open();
		m_Shell.setVisible(false);
		

		// CREER UN CONTROLLER EDITMOVIE
		runController(ListClientController.class, 1);
	}
	
	public void runController(Class<?> c, Object ... args)
	{
		RunController rc = new RunController();
		rc.m_Class = c;
		rc.m_Args = args;

		while (true)
		{
			if (!runController(rc))
				break;
		}
	}
		
	public boolean runController(RunController rc)
	{
		if (rc.m_Class == null)
			return false;
		
		Controller controller = createController(rc.m_Class);
		//controller.setModel(m_Model);
		controller.setModel(m_ModelClient);
		
		ActionResult result = controller.run(rc.m_Args);

		try
		{
			String viewName = result.m_Name.replaceAll("Controller", "View");

			Class<?> viewClass = Class.forName(viewName);
			Constructor<?> ctor = viewClass.getConstructor(Shell.class, int.class);
			Object dialog = ctor.newInstance(m_Shell, SWT.APPLICATION_MODAL | SWT.TITLE | SWT.BORDER | SWT.CLOSE); // SWT.NONE);
			Field field = dialog.getClass().getDeclaredField("m_Infrastructure");

			rc.m_ViewModel = result.m_ViewModel;

			field.set(dialog, rc);
			Method method = dialog.getClass().getMethod("open");
			rc.m_Class = null;
			method.invoke(dialog);
			return true;
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
		return false;
	}
	
	public Controller createController(Class c)
	{
		try
		{
			// CONTROLLER ICI
			Controller controller = (Controller)c.getConstructor().newInstance();
			return controller;





		}
		catch (InstantiationException | IllegalAccessException | IllegalArgumentException | InvocationTargetException
				| NoSuchMethodException | SecurityException e)
		{
			e.printStackTrace();
		}
		
		return null;
	}
}
