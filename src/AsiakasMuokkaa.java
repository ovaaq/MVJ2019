import org.eclipse.jface.dialogs.MessageDialog;
import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Combo;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Text;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.wb.swt.SWTResourceManager;



public class AsiakasMuokkaa extends Shell {
	private Text etunimi;
	private Text sukunimi;
	private Text email;
	private Text lahiosoite;
	private Text paikkakunta;
	private Text postinumero;
	private Text puh_nro;

	Asiakas asiakas = new Asiakas();
	/**
	 * Launch the application.
	 * @param args
	 */
	public static void main(String args[]) {
		try {
			Display display = Display.getDefault();
			AsiakasMuokkaa shell = new AsiakasMuokkaa(display);
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
	public AsiakasMuokkaa(Display display) {
		super(display, SWT.SHELL_TRIM);
		setLayout(new GridLayout(1, false));
		
		Label lblMuokkaaAsiakasTietoja = new Label(this, SWT.NONE);
		lblMuokkaaAsiakasTietoja.setFont(SWTResourceManager.getFont("Segoe UI", 12, SWT.NORMAL));
		lblMuokkaaAsiakasTietoja.setText("Muokkaa asiakkaan tietoja");
		
		Composite composite = new Composite(this, SWT.NONE);
		composite.setBounds(0, 0, 64, 64);
		composite.setLayout(new GridLayout(2, false));
		
		Label lblMuokattavaAsiakas = new Label(composite, SWT.NONE);
		GridData gd_lblMuokattavaAsiakas = new GridData(SWT.FILL, SWT.CENTER, false, false, 1, 1);
		gd_lblMuokattavaAsiakas.widthHint = 177;
		lblMuokattavaAsiakas.setLayoutData(gd_lblMuokattavaAsiakas);
		lblMuokattavaAsiakas.setSize(155, 25);
		lblMuokattavaAsiakas.setText("Muokattava asiakas:");
		
		
		Combo combo = new Combo(composite, SWT.READ_ONLY);
		combo.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				
				asiakas.otaTiedot(combo.getItem(combo.getSelectionIndex()).split(" ")[0]);
				paikkakunta.setText(asiakas.getM_paikkakunta());
				etunimi.setText(asiakas.getM_etunimi());
				sukunimi.setText(asiakas.getM_sukunimi());
				email.setText(asiakas.getM_email());
				lahiosoite.setText(asiakas.getM_lahiosoite());
				postinumero.setText(asiakas.getM_postinumero());
				puh_nro.setText(asiakas.getM_puh_nro());
			}
		});
		
		GridData gd_combo = new GridData(SWT.FILL, SWT.CENTER, false, false, 1, 1);
		gd_combo.widthHint = 189;
		combo.setLayoutData(gd_combo);
		combo.setSize(151, 33);
		combo.setText("Valitse asiakas");
		//tähän comboboxin sisältö
		for(int i = 0; i<asiakas.getLista().size(); i++) {
			combo.add(asiakas.getLista().get(i).toString());
			}
		
		
		
		Composite composite_1 = new Composite(this, SWT.NONE);
		composite_1.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, false, false, 1, 1));
		composite_1.setBounds(0, 0, 64, 64);
		composite_1.setLayout(new GridLayout(3, false));
		
		Label lblAsiakkaanTiedot = new Label(composite_1, SWT.NONE);
		lblAsiakkaanTiedot.setLayoutData(new GridData(SWT.LEFT, SWT.CENTER, false, false, 3, 1));
		lblAsiakkaanTiedot.setSize(131, 25);
		lblAsiakkaanTiedot.setText("Asiakkaan tiedot");
		
		Label lblEtunimi = new Label(composite_1, SWT.NONE);
		lblEtunimi.setText("Etunimi");
		new Label(composite_1, SWT.NONE);
		
		Label lblKatuosoite = new Label(composite_1, SWT.NONE);
		lblKatuosoite.setText("Katuosoite");
		
		etunimi = new Text(composite_1, SWT.BORDER);
		GridData gd_etunimi = new GridData(SWT.FILL, SWT.CENTER, false, false, 2, 1);
		gd_etunimi.widthHint = 154;
		etunimi.setLayoutData(gd_etunimi);
		etunimi.setSize(80, 31);
		
		lahiosoite = new Text(composite_1, SWT.BORDER);
		GridData gd_lahiosoite = new GridData(SWT.FILL, SWT.CENTER, true, false, 1, 1);
		gd_lahiosoite.widthHint = 180;
		lahiosoite.setLayoutData(gd_lahiosoite);
		
		Label lblSukunimi = new Label(composite_1, SWT.NONE);
		lblSukunimi.setText("Sukunimi");
		new Label(composite_1, SWT.NONE);
		
		Label lblPaikkakunta = new Label(composite_1, SWT.NONE);
		lblPaikkakunta.setText("Paikkakunta");
		
		sukunimi = new Text(composite_1, SWT.BORDER);
		sukunimi.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, false, false, 2, 1));
		sukunimi.setSize(80, 31);
		
		paikkakunta = new Text(composite_1, SWT.BORDER);
		paikkakunta.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false, 1, 1));
		
		Label lblShkposti = new Label(composite_1, SWT.NONE);
		lblShkposti.setText("S\u00E4hk\u00F6posti");
		new Label(composite_1, SWT.NONE);
		
		Label lblPostinumero = new Label(composite_1, SWT.NONE);
		lblPostinumero.setText("Postinumero");
		
		email = new Text(composite_1, SWT.BORDER);
		email.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, false, false, 2, 1));
		email.setSize(80, 31);
		
		postinumero = new Text(composite_1, SWT.BORDER);
		postinumero.setLayoutData(new GridData(SWT.FILL, SWT.TOP, true, true, 1, 1));
		
		Label lblPuhelinnumero = new Label(composite_1, SWT.NONE);
		lblPuhelinnumero.setText("Puhelinnumero");
		new Label(composite_1, SWT.NONE);
		new Label(composite_1, SWT.NONE);
		
		puh_nro = new Text(composite_1, SWT.BORDER);
		puh_nro.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false, 2, 1));
		new Label(composite_1, SWT.NONE);
		
		Composite composite_2 = new Composite(this, SWT.NONE);
		composite_2.setLayout(new GridLayout(1, false));
		
		Button PaivitaAsiakastiedot = new Button(composite_2, SWT.NONE);
		GridData gd_PaivitaAsiakastiedot = new GridData(SWT.LEFT, SWT.CENTER, false, false, 1, 1);
		gd_PaivitaAsiakastiedot.heightHint = 60;
		gd_PaivitaAsiakastiedot.widthHint = 170;
		PaivitaAsiakastiedot.setLayoutData(gd_PaivitaAsiakastiedot);
		PaivitaAsiakastiedot.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				try {
					asiakas.paivita(etunimi.getText(), sukunimi.getText(), postinumero.getText(), paikkakunta.getText(), lahiosoite.getText(), email.getText(), puh_nro.getText(), combo.getItem(combo.getSelectionIndex()).split(" ")[0]);
					
					combo.removeAll();
					for(int i = 0; i<asiakas.getLista().size(); i++) {
						combo.add(asiakas.getLista().get(i).toString());
						}
					paikkakunta.setText("");
					etunimi.setText("");
					sukunimi.setText("");
					email.setText("");
					lahiosoite.setText("");
					postinumero.setText("");
					puh_nro.setText("");
					
					MessageDialog.openConfirm(Display.getDefault().getActiveShell(), "Päivitys", "Päivitys onnistui");
				}catch(Exception e1) {
					e1.printStackTrace();
				}
			}
		});
		PaivitaAsiakastiedot.setSize(177, 35);
		PaivitaAsiakastiedot.setText("P\u00E4ivit\u00E4 asiakastiedot");
		createContents();
	}

	/**
	 * Create contents of the shell.
	 */
	protected void createContents() {
		setText("Muokkaa asiakastietoja");
		setSize(800, 550);

	}

	@Override
	protected void checkSubclass() {
		// Disable the check that prevents subclassing of SWT components
	}
}
