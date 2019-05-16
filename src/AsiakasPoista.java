import org.eclipse.jface.dialogs.MessageDialog;
import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Combo;
import org.eclipse.wb.swt.SWTResourceManager;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.widgets.Text;

public class AsiakasPoista extends Shell {
	private Text puh_nro;
	private Text etunimi;
	private Text sukunimi;
	private Text email;
	private Text lahiosoite;
	private Text paikkakunta;
	private Text postinumero;

	Asiakas asiakas = new Asiakas();
	
	/**
	 * Launch the application.
	 * @param args
	 */
	public static void main(String args[]) {
		try {
			Display display = Display.getDefault();
			AsiakasPoista shell = new AsiakasPoista(display);
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
	public AsiakasPoista(Display display) {
		super(display, SWT.SHELL_TRIM);
		setLayout(new GridLayout(1, false));
		
		Composite composite = new Composite(this, SWT.NONE);
		composite.setBounds(0, 0, 64, 64);
		composite.setLayout(new GridLayout(2, false));
		
		Label lblPoistaAsiakasTietokannasta = new Label(composite, SWT.NONE);
		lblPoistaAsiakasTietokannasta.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, false, false, 2, 1));
		lblPoistaAsiakasTietokannasta.setFont(SWTResourceManager.getFont("Segoe UI", 12, SWT.NORMAL));
		lblPoistaAsiakasTietokannasta.setText("Poista asiakas tietokannasta");
		
		Label lblAsiakas = new Label(composite, SWT.NONE);
		lblAsiakas.setSize(147, 25);
		lblAsiakas.setText("Poistettava asiakas:");
		
		Combo combo = new Combo(composite, SWT.READ_ONLY);
		GridData gd_combo = new GridData(SWT.FILL, SWT.CENTER, false, false, 1, 1);
		gd_combo.widthHint = 215;
		combo.setLayoutData(gd_combo);
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
		combo.setSize(151, 33);
		combo.setText("Valitse asiakas");
		//tähän combo-boxin sisätlö eli valittavat asiakkaat
		for(int i = 0; i<asiakas.getLista().size(); i++) {
			combo.add(asiakas.getLista().get(i).toString());
			}
		
		Composite composite_1 = new Composite(this, SWT.NONE);
		GridData gd_composite_1 = new GridData(SWT.LEFT, SWT.CENTER, false, false, 1, 1);
		gd_composite_1.widthHint = 416;
		composite_1.setLayoutData(gd_composite_1);
		composite_1.setBounds(0, 0, 64, 64);
		composite_1.setLayout(new GridLayout(4, false));
		
		Label lblAsiakkaanTiedot = new Label(composite_1, SWT.NONE);
		lblAsiakkaanTiedot.setLayoutData(new GridData(SWT.LEFT, SWT.CENTER, false, false, 4, 1));
		lblAsiakkaanTiedot.setSize(131, 25);
		lblAsiakkaanTiedot.setText("Asiakkaan tiedot");
		
		Label lblEtunimi = new Label(composite_1, SWT.NONE);
		lblEtunimi.setText("Etunimi");
		new Label(composite_1, SWT.NONE);
		
		Label lblKatuosoite = new Label(composite_1, SWT.NONE);
		lblKatuosoite.setText("Katuosoite");
		new Label(composite_1, SWT.NONE);
		
		etunimi = new Text(composite_1, SWT.BORDER | SWT.READ_ONLY);
		etunimi.setBackground(SWTResourceManager.getColor(SWT.COLOR_WHITE));
		GridData gd_etunimi = new GridData(SWT.FILL, SWT.CENTER, true, false, 2, 1);
		gd_etunimi.widthHint = 186;
		etunimi.setLayoutData(gd_etunimi);
		
		lahiosoite = new Text(composite_1, SWT.BORDER | SWT.READ_ONLY);
		lahiosoite.setBackground(SWTResourceManager.getColor(SWT.COLOR_WHITE));
		GridData gd_lahiosoite = new GridData(SWT.FILL, SWT.CENTER, true, false, 2, 1);
		gd_lahiosoite.widthHint = 249;
		lahiosoite.setLayoutData(gd_lahiosoite);
		
		Label lblSukunimi = new Label(composite_1, SWT.NONE);
		lblSukunimi.setText("Sukunimi");
		new Label(composite_1, SWT.NONE);
		
		Label lblPaikkakunta = new Label(composite_1, SWT.NONE);
		lblPaikkakunta.setText("Paikkakunta");
		new Label(composite_1, SWT.NONE);
		
		sukunimi = new Text(composite_1, SWT.BORDER | SWT.READ_ONLY);
		sukunimi.setBackground(SWTResourceManager.getColor(SWT.COLOR_WHITE));
		sukunimi.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false, 2, 1));
		
		paikkakunta = new Text(composite_1, SWT.BORDER | SWT.READ_ONLY);
		paikkakunta.setBackground(SWTResourceManager.getColor(SWT.COLOR_WHITE));
		paikkakunta.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false, 2, 1));
		
		Label lblShkposti = new Label(composite_1, SWT.NONE);
		lblShkposti.setText("S\u00E4hk\u00F6posti");
		new Label(composite_1, SWT.NONE);
		
		Label lblPostinumero = new Label(composite_1, SWT.NONE);
		lblPostinumero.setText("Postinumero");
		new Label(composite_1, SWT.NONE);
		
		email = new Text(composite_1, SWT.BORDER | SWT.READ_ONLY);
		email.setBackground(SWTResourceManager.getColor(SWT.COLOR_WHITE));
		email.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false, 2, 1));
		
		postinumero = new Text(composite_1, SWT.BORDER | SWT.READ_ONLY);
		postinumero.setBackground(SWTResourceManager.getColor(SWT.COLOR_WHITE));
		postinumero.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false, 2, 1));
		
		Label lblPuhelinnumero = new Label(composite_1, SWT.NONE);
		lblPuhelinnumero.setText("Puhelinnumero");
		new Label(composite_1, SWT.NONE);
		new Label(composite_1, SWT.NONE);
		new Label(composite_1, SWT.NONE);
		
		puh_nro = new Text(composite_1, SWT.BORDER | SWT.READ_ONLY);
		puh_nro.setBackground(SWTResourceManager.getColor(SWT.COLOR_WHITE));
		puh_nro.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false, 2, 1));
		new Label(composite_1, SWT.NONE);
		new Label(composite_1, SWT.NONE);
		
		Composite composite_2 = new Composite(this, SWT.NONE);
		composite_2.setBounds(0, 0, 64, 64);
		composite_2.setLayout(new GridLayout(1, false));
		
		Button PoistaAsiakas = new Button(composite_2, SWT.NONE);
		GridData gd_PoistaAsiakas = new GridData(SWT.FILL, SWT.CENTER, false, false, 1, 1);
		gd_PoistaAsiakas.heightHint = 60;
		gd_PoistaAsiakas.widthHint = 140;
		PoistaAsiakas.setLayoutData(gd_PoistaAsiakas);
		PoistaAsiakas.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				
				asiakas.poista(combo.getItem(combo.getSelectionIndex()).split(" ")[0]);
				
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
				
				MessageDialog.openConfirm(Display.getDefault().getActiveShell(), "Poisto", "Asiakkaan poistaminen onnistui");
			}
		});
		PoistaAsiakas.setSize(177, 35);
		PoistaAsiakas.setText("Poista asiakas");
		createContents();
	}

	/**
	 * Create contents of the shell.
	 */
	protected void createContents() {
		setText("Poista asiakas");
		setSize(800, 600);

	}

	@Override
	protected void checkSubclass() {
		// Disable the check that prevents subclassing of SWT components
	}

}
