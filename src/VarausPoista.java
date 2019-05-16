import org.eclipse.jface.dialogs.MessageDialog;
import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Combo;
import org.eclipse.swt.widgets.List;
import org.eclipse.wb.swt.SWTResourceManager;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.widgets.Text;

public class VarausPoista extends Shell {
	private Text palvelut;
	private Text mokki;
	private Text toimipiste;
	private Text asiakasnimi;
	private Text alkupvm;
	private Text loppupvm;
	
	Varaus varaus = new Varaus();
	Asiakas asiakas = new Asiakas();
	;

	/**
	 * Launch the application.
	 * @param args
	 */
	public static void main(String args[]) {
		try {
			Display display = Display.getDefault();
			VarausPoista shell = new VarausPoista(display);
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
	public VarausPoista(Display display) {
		super(display, SWT.SHELL_TRIM);
		setLayout(new GridLayout(1, false));
		
		Composite composite = new Composite(this, SWT.NONE);
		composite.setBounds(0, 0, 64, 64);
		composite.setLayout(new GridLayout(2, false));
		
		Label lblValitseAsiakas = new Label(composite, SWT.NONE);
		lblValitseAsiakas.setText("Valitse asiakas");
		
		Label lblPoistettavaVaraus = new Label(composite, SWT.NONE);
		GridData gd_lblPoistettavaVaraus = new GridData(SWT.LEFT, SWT.CENTER, false, false, 1, 1);
		gd_lblPoistettavaVaraus.widthHint = 253;
		lblPoistettavaVaraus.setLayoutData(gd_lblPoistettavaVaraus);
		lblPoistettavaVaraus.setText("Valitse varaus");
	

		Combo combo_1 = new Combo(composite, SWT.READ_ONLY);
		Combo combo = new Combo(composite, SWT.READ_ONLY);
		combo_1.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				combo.removeAll();
				for(int i = 0; i<varaus.getListaAsiakasFiltteri(combo_1.getItem(combo_1.getSelectionIndex()).split(" ")[0]).size(); i++) {
					combo.add(varaus.getListaAsiakasFiltteri(combo_1.getItem(combo_1.getSelectionIndex()).split(" ")[0]).get(i).toString());
					}
				
				asiakas.otaTiedot(combo_1.getItem(combo_1.getSelectionIndex()).split(" ")[0]);
				asiakasnimi.setText(asiakas.getM_etunimi() + " " + asiakas.getM_sukunimi());
			
				
				
			}
		});
		GridData gd_combo_1 = new GridData(SWT.FILL, SWT.CENTER, true, false, 1, 1);
		gd_combo_1.widthHint = 159;
		combo_1.setLayoutData(gd_combo_1);
		
		
		combo.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, false, false, 1, 1));
		combo.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				
				//combo_1.removeAll();
				
					
				//t�h�n varmaan pit�� v�h�n muuttaa miten se hakee sen varauksen? t�ss� ota varaus combon sis�ll�n haku.
				varaus.otaTiedot(combo.getItem(combo.getSelectionIndex()).split(" ")[0]);
				
				/* t�h�n hakee varauksen palvelut, m�kin ja toimipisteen eli asettaa niihin tekstiboxeihin tiedot
				palvelut.setText();
				mokki.setText();
				toimipiste.setText();
				*/
				varaus.otaTiedot(combo.getItem(combo.getSelectionIndex()).split(" ")[0]);
				alkupvm.setText(varaus.getM_alku_pvm());
				loppupvm.setText(varaus.getM_loppu_pvm());
				toimipiste.setText(varaus.getM_toimipiste_ID());
				mokki.setText(varaus.getMokki());
				palvelut.setText(varaus.getPalvelu());
				
				
			}
		});
		
		
		combo.setText("Valitse varaus");
		for(int i = 0; i<asiakas.getLista().size(); i++) {
			combo_1.add(asiakas.getLista().get(i).toString());
			}
		
		Composite composite_1 = new Composite(this, SWT.NONE);
		composite_1.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, false, false, 1, 1));
		composite_1.setBounds(0, 0, 64, 64);
		composite_1.setLayout(new GridLayout(4, false));
		
		Label lblVarauksenTiedot = new Label(composite_1, SWT.NONE);
		lblVarauksenTiedot.setLayoutData(new GridData(SWT.LEFT, SWT.CENTER, false, false, 3, 1));
		lblVarauksenTiedot.setSize(132, 25);
		lblVarauksenTiedot.setText("Varauksen tiedot");
		new Label(composite_1, SWT.NONE);
		
		Label lblAsiakas = new Label(composite_1, SWT.NONE);
		lblAsiakas.setText("Asiakas");
		
		Label lblToimipiste = new Label(composite_1, SWT.NONE);
		lblToimipiste.setText("Toimipiste");
		
		Label lblNewLabel_3 = new Label(composite_1, SWT.NONE);
		lblNewLabel_3.setText("M\u00F6kki");
		
		Label lblPalvelut = new Label(composite_1, SWT.NONE);
		lblPalvelut.setText("Palvelu(t)");
		
		asiakasnimi = new Text(composite_1, SWT.BORDER | SWT.READ_ONLY);
		asiakasnimi.setBackground(SWTResourceManager.getColor(SWT.COLOR_WHITE));
		asiakasnimi.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false, 1, 1));
		
		toimipiste = new Text(composite_1, SWT.BORDER);
		toimipiste.setBackground(SWTResourceManager.getColor(SWT.COLOR_WHITE));
		toimipiste.setEditable(false);
		toimipiste.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false, 1, 1));
		
		mokki = new Text(composite_1, SWT.BORDER);
		mokki.setBackground(SWTResourceManager.getColor(SWT.COLOR_WHITE));
		mokki.setEditable(false);
		mokki.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false, 1, 1));
		
		palvelut = new Text(composite_1, SWT.BORDER);
		palvelut.setBackground(SWTResourceManager.getColor(SWT.COLOR_WHITE));
		palvelut.setEditable(false);
		palvelut.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false, 1, 1));
		
		Label lblVarauksenAlku = new Label(composite_1, SWT.NONE);
		lblVarauksenAlku.setLayoutData(new GridData(SWT.LEFT, SWT.CENTER, false, false, 2, 1));
		lblVarauksenAlku.setText("Varauksen alku");
		
		Label lblVarauksenLoppi = new Label(composite_1, SWT.NONE);
		lblVarauksenLoppi.setLayoutData(new GridData(SWT.LEFT, SWT.CENTER, false, false, 2, 1));
		lblVarauksenLoppi.setText("Varauksen loppu");
		
		alkupvm = new Text(composite_1, SWT.BORDER);
		alkupvm.setBackground(SWTResourceManager.getColor(SWT.COLOR_WHITE));
		alkupvm.setEditable(false);
		alkupvm.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false, 2, 1));
		
		loppupvm = new Text(composite_1, SWT.BORDER);
		loppupvm.setEditable(false);
		loppupvm.setBackground(SWTResourceManager.getColor(SWT.COLOR_WHITE));
		loppupvm.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false, 2, 1));
		
		Composite composite_2 = new Composite(this, SWT.NONE);
		composite_2.setBounds(0, 0, 64, 64);
		composite_2.setLayout(new GridLayout(1, false));
		
		Button btnPoistaVaraus = new Button(composite_2, SWT.NONE);
		btnPoistaVaraus.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				varaus.Piilota(combo.getItem(combo.getSelectionIndex()).split(" ")[0]);
				
				palvelut.setText("");
				mokki.setText("");
				toimipiste.setText("");
				asiakasnimi.setText("");
				alkupvm.setText("");
				loppupvm.setText("");
				combo.deselectAll();
				combo_1.deselectAll();
				 MessageDialog.openConfirm(Display.getDefault().getActiveShell(), "Poisto", "Varauksen poistaminen onnistui");
			}
		});
		btnPoistaVaraus.setSize(115, 35);
		btnPoistaVaraus.setText("Poista varaus");
		createContents();
	}

	/**
	 * Create contents of the shell.
	 */
	protected void createContents() {
		setText("Poista varaus");
		setSize(600, 400);

	}

	@Override
	protected void checkSubclass() {
		// Disable the check that prevents subclassing of SWT components
	}
}
