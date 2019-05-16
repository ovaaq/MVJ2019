import org.eclipse.jface.dialogs.MessageDialog;
import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Text;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.wb.swt.SWTResourceManager;

public class AsiakasLisaa extends Shell {
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
			AsiakasLisaa shell = new AsiakasLisaa(display);
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
	public AsiakasLisaa(Display display) {
		super(display, SWT.SHELL_TRIM);
		setLayout(new GridLayout(1, false));
		
		Composite composite = new Composite(this, SWT.NONE);
		composite.setBounds(0, 0, 64, 64);
		composite.setLayout(new GridLayout(1, false));
		
		Label lblLisUudenAsiakkaan = new Label(composite, SWT.NONE);
		lblLisUudenAsiakkaan.setFont(SWTResourceManager.getFont("Segoe UI", 12, SWT.NORMAL));
		lblLisUudenAsiakkaan.setSize(226, 25);
		lblLisUudenAsiakkaan.setText("Lis\u00E4\u00E4 uuden asiakkaan tiedot");
		
		Composite composite_1 = new Composite(this, SWT.NONE);
		composite_1.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, false, false, 1, 1));
		composite_1.setBounds(0, 0, 64, 64);
		composite_1.setLayout(new GridLayout(4, false));
		
		Label lblEtunimi = new Label(composite_1, SWT.NONE);
		lblEtunimi.setSize(59, 25);
		lblEtunimi.setText("Etunimi");
		new Label(composite_1, SWT.NONE);
		
		Label lblShkposti = new Label(composite_1, SWT.NONE);
		lblShkposti.setText("Katuosoite");
		new Label(composite_1, SWT.NONE);
		
		etunimi = new Text(composite_1, SWT.BORDER);
		GridData gd_etunimi = new GridData(SWT.FILL, SWT.CENTER, false, false, 2, 1);
		gd_etunimi.widthHint = 173;
		etunimi.setLayoutData(gd_etunimi);
		etunimi.setToolTipText("");
		etunimi.setSize(80, 31);
		
		lahiosoite = new Text(composite_1, SWT.BORDER);
		GridData gd_lahiosoite = new GridData(SWT.FILL, SWT.CENTER, true, false, 2, 1);
		gd_lahiosoite.widthHint = 179;
		lahiosoite.setLayoutData(gd_lahiosoite);
		
		Label lblSukunimi = new Label(composite_1, SWT.NONE);
		lblSukunimi.setSize(73, 25);
		lblSukunimi.setText("Sukunimi");
		new Label(composite_1, SWT.NONE);
		
		Label lblKaupunki = new Label(composite_1, SWT.NONE);
		lblKaupunki.setText("Paikkakunta");
		new Label(composite_1, SWT.NONE);
		
		sukunimi = new Text(composite_1, SWT.BORDER);
		sukunimi.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, false, false, 2, 1));
		sukunimi.setSize(80, 31);
		
		paikkakunta = new Text(composite_1, SWT.BORDER);
		paikkakunta.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false, 2, 1));
		
		Label lblOsoite = new Label(composite_1, SWT.NONE);
		lblOsoite.setSize(52, 25);
		lblOsoite.setText("S\u00E4hk\u00F6posti");
		new Label(composite_1, SWT.NONE);
		
		Label lblPostinumero = new Label(composite_1, SWT.NONE);
		lblPostinumero.setText("Postinumero");
		new Label(composite_1, SWT.NONE);
		
		email = new Text(composite_1, SWT.BORDER);
		email.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, false, false, 2, 1));
		email.setSize(80, 31);
		
		postinumero = new Text(composite_1, SWT.BORDER);
		postinumero.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false, 2, 1));
		
		Label lblPuh = new Label(composite_1, SWT.NONE);
		lblPuh.setText("Puhelinnumero");
		new Label(composite_1, SWT.NONE);
		new Label(composite_1, SWT.NONE);
		new Label(composite_1, SWT.NONE);
		
		puh_nro = new Text(composite_1, SWT.BORDER);
		puh_nro.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, false, false, 2, 1));
		new Label(composite_1, SWT.NONE);
		new Label(composite_1, SWT.NONE);
		
		Composite composite_2 = new Composite(this, SWT.NONE);
		composite_2.setBounds(0, 0, 64, 64);
		composite_2.setLayout(new GridLayout(1, false));
		
		Button LisääAsiakas = new Button(this, SWT.NONE);
		GridData gd_LisääAsiakas = new GridData(SWT.LEFT, SWT.CENTER, false, false, 1, 2);
		gd_LisääAsiakas.widthHint = 140;
		gd_LisääAsiakas.heightHint = 60;
		LisääAsiakas.setLayoutData(gd_LisääAsiakas);
		LisääAsiakas.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				asiakas.lisaa(etunimi.getText(), sukunimi.getText(), postinumero.getText(), paikkakunta.getText(), lahiosoite.getText(), email.getText(), puh_nro.getText());
				MessageDialog.openConfirm(Display.getDefault().getActiveShell(), "Lisäys", "Asiakkaan lisääminen onnistui");
				paikkakunta.setText("");
				etunimi.setText("");
				sukunimi.setText("");
				email.setText("");
				lahiosoite.setText("");
				postinumero.setText("");
				puh_nro.setText("");
			}
		});
		LisääAsiakas.setSize(177, 35);
		LisääAsiakas.setText("Lis\u00E4\u00E4 asiakas");
		createContents();
		
	}

	/**
	 * Create contents of the shell.
	 */
	protected void createContents() {
		setText("Lis\u00E4\u00E4 asiakas");
		setSize(800, 550);

	}

	@Override
	protected void checkSubclass() {
		// Disable the check that prevents subclassing of SWT components
	}
}
