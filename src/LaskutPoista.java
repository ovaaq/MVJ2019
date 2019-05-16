import java.awt.Desktop;
import java.io.File;
import java.io.IOException;

import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Table;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.widgets.Text;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Combo;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;

public class LaskutPoista extends Shell {

	/**
	 * Launch the application.
	 * @param args
	 */
	public static void main(String args[]) {
		try {
			Display display = Display.getDefault();
			LaskutPoista shell = new LaskutPoista(display);
			shell.open();
			shell.layout();
			while (!shell.isDisposed()) {
				if (!display.readAndDispatch()) {
					display.sleep();
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Create the shell.
	 * @param display
	 */
	public LaskutPoista(Display display) {
		super(display, SWT.SHELL_TRIM);
		setLayout(new GridLayout(2, false));
		
		Composite composite = new Composite(this, SWT.NONE);
		composite.setLayout(new GridLayout(1, false));
		
		Label lblLaskunLhetys = new Label(composite, SWT.NONE);
		lblLaskunLhetys.setText("Laskun l\u00E4hetys");
		new Label(this, SWT.NONE);
		new Label(this, SWT.NONE);
		new Label(this, SWT.NONE);
		
		Label lblValitseAsiakas = new Label(this, SWT.NONE);
		lblValitseAsiakas.setText("Valitse asiakas");
		
		Label lblValitseLasku = new Label(this, SWT.NONE);
		lblValitseLasku.setText("Valitse Lasku");
		
		Combo combo = new Combo(this, SWT.READ_ONLY);
		Combo combo_1 = new Combo(this, SWT.READ_ONLY);
		Asiakas asiakas = new Asiakas();
		Varaus varaus = new Varaus();
		for(int i = 0; i<asiakas.getLista().size(); i++) {
			combo.add(asiakas.getLista().get(i).toString());
			}
		
		combo.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				asiakas.otaTiedot(combo.getItem(combo.getSelectionIndex()).split(" ")[0]);
				combo_1.removeAll();
				for(int i = 0; i<varaus.getListaAsiakasFiltteri(asiakas.getM_asiakas_ID()).size(); i++) {
					combo_1.add(varaus.getListaAsiakasFiltteri(asiakas.getM_asiakas_ID()).get(i).toString());
					}
			}
		});
		combo.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, false, false, 1, 1));
		
		
		combo_1.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
			}
		});
		combo_1.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, false, false, 1, 1));
		new Label(this, SWT.NONE);
		new Label(this, SWT.NONE);
		Bill bill = new Bill();
		Button btnLhetPaperi = new Button(this, SWT.NONE);
		btnLhetPaperi.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				System.out.println(combo_1.getItem(combo_1.getSelectionIndex()).split(" ")[0]);
				bill.CreatePDF(combo_1.getItem(combo_1.getSelectionIndex()).split(" ")[0], "kirje");
				
				if (Desktop.isDesktopSupported()) {
				    try {
				        File myFile = new File("//home//ovaaq//eclipse-workspace//MokkiVarausJarjestelma.zip_expanded//MokkiVarausJarjestelma//lasku.pdf");
				        Desktop.getDesktop().open(myFile);
				    } catch (IOException ex) {
				        // no application registered for PDFs
				    }
				}
			}
		});
		btnLhetPaperi.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, false, false, 1, 1));
		btnLhetPaperi.setText("L\u00E4het\u00E4 paperi");
		
		Button btnLhetEmail = new Button(this, SWT.NONE);
		btnLhetEmail.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				
				System.out.println(combo_1.getItem(combo_1.getSelectionIndex()).split(" ")[0]);
				bill.CreatePDF(combo_1.getItem(combo_1.getSelectionIndex()).split(" ")[0], "sähköposti");
				
				if (Desktop.isDesktopSupported()) {
				    try {
				        File myFile = new File("//home//ovaaq//eclipse-workspace//MokkiVarausJarjestelma.zip_expanded//MokkiVarausJarjestelma//lasku.pdf");
				        Desktop.getDesktop().open(myFile);
				    } catch (IOException ex) {
				        // no application registered for PDFs
				    }
				}
			}
			
		});
		btnLhetEmail.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, false, false, 1, 1));
		btnLhetEmail.setText("L\u00E4het\u00E4 email");
		createContents();
	}

	/**
	 * Create contents of the shell.
	 */
	protected void createContents() {
		setText("L\u00E4hetys");
		setSize(800, 600);

	}

	@Override
	protected void checkSubclass() {
		// Disable the check that prevents subclassing of SWT components
	}

}
