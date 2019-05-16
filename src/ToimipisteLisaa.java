import org.eclipse.jface.dialogs.MessageDialog;
import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Text;
import org.eclipse.swt.widgets.Label;
import org.eclipse.wb.swt.SWTResourceManager;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.events.ModifyListener;
import org.eclipse.swt.events.ModifyEvent;


public class ToimipisteLisaa extends Shell {
	private Text postinumero;
	private Text lahiosoite;
	private Text paikkakunta;
	private Text nimi;
	private Text email;
	private Text puh_num;
	
	Toimipiste toimipiste = new Toimipiste();

	/**
	 * Launch the application.
	 * @param args
	 */
	public static void main(String args[]) {
		try {
			Display display = Display.getDefault();
			ToimipisteLisaa shell = new ToimipisteLisaa(display);
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
	public ToimipisteLisaa(Display display) {
		super(display, SWT.SHELL_TRIM);
		setBackground(SWTResourceManager.getColor(SWT.COLOR_WIDGET_LIGHT_SHADOW));
		setLayout(new GridLayout(1, false));
		
		Composite composite = new Composite(this, SWT.NONE);
		composite.setLayoutData(new GridData(SWT.LEFT, SWT.CENTER, true, false, 1, 1));
		composite.setBackground(SWTResourceManager.getColor(SWT.COLOR_WIDGET_LIGHT_SHADOW));
		composite.setLayout(new GridLayout(1, false));
		
		Label lblSytUudenToimipisteen = new Label(composite, SWT.NONE);
		lblSytUudenToimipisteen.setLayoutData(new GridData(SWT.CENTER, SWT.BOTTOM, false, false, 1, 1));
		lblSytUudenToimipisteen.setBackground(SWTResourceManager.getColor(SWT.COLOR_WIDGET_LIGHT_SHADOW));
		lblSytUudenToimipisteen.setText("Sy\u00F6t\u00E4 uuden toimipisteen tiedot");
		
		Label label = new Label(composite, SWT.SEPARATOR | SWT.HORIZONTAL);
		label.setLayoutData(new GridData(SWT.FILL, SWT.FILL, false, false, 1, 1));
		
		Composite composite_1 = new Composite(this, SWT.NONE);
		composite_1.setBackground(SWTResourceManager.getColor(SWT.COLOR_WIDGET_LIGHT_SHADOW));
		composite_1.setLayoutData(new GridData(SWT.LEFT, SWT.FILL, true, false, 1, 1));
		composite_1.setLayout(new GridLayout(2, false));
		
		Label lblSijainti = new Label(composite_1, SWT.NONE);
		lblSijainti.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, false, false, 1, 1));
		lblSijainti.setBackground(SWTResourceManager.getColor(SWT.COLOR_WIDGET_LIGHT_SHADOW));
		lblSijainti.setText("Paikkakunta");
		
		Label lblNimi = new Label(composite_1, SWT.NONE);
		lblNimi.setText("Nimi:");
		
		paikkakunta = new Text(composite_1, SWT.BORDER);
		paikkakunta.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, false, false, 1, 1));
		
		nimi = new Text(composite_1, SWT.BORDER);
		nimi.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false, 1, 1));
		
		Label lblKaupunki = new Label(composite_1, SWT.NONE);
		lblKaupunki.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, false, false, 1, 1));
		lblKaupunki.setBackground(SWTResourceManager.getColor(SWT.COLOR_WIDGET_LIGHT_SHADOW));
		lblKaupunki.setText("Osoite:");
		
		Label lblEmail = new Label(composite_1, SWT.NONE);
		lblEmail.setText("email");
		
		lahiosoite = new Text(composite_1, SWT.BORDER);
		lahiosoite.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, false, false, 1, 1));
		
		email = new Text(composite_1, SWT.BORDER);
		email.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false, 1, 1));
		
		Label lblPostinumero = new Label(composite_1, SWT.NONE);
		lblPostinumero.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, false, false, 1, 1));
		lblPostinumero.setBackground(SWTResourceManager.getColor(SWT.COLOR_WIDGET_LIGHT_SHADOW));
		lblPostinumero.setText("Postinumero:");
		
		Label lblPuhelinnumero = new Label(composite_1, SWT.NONE);
		lblPuhelinnumero.setText("Puhelinnumero");
		
		postinumero = new Text(composite_1, SWT.BORDER);
		postinumero.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, false, false, 1, 1));
		
		puh_num = new Text(composite_1, SWT.BORDER);
		puh_num.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false, 1, 1));
		
		Composite composite_2 = new Composite(this, SWT.NONE);
		composite_2.setBackground(SWTResourceManager.getColor(SWT.COLOR_WIDGET_LIGHT_SHADOW));
		composite_2.setLayoutData(new GridData(SWT.LEFT, SWT.CENTER, true, true, 1, 1));
		composite_2.setLayout(new GridLayout(1, false));
		
		Button btnLisTietokantaan = new Button(composite_2, SWT.NONE);
		btnLisTietokantaan.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				
				toimipiste.lisaa(nimi.getText(), puh_num.getText(), lahiosoite.getText(), postinumero.getText(), paikkakunta.getText(), email.getText());
				MessageDialog.openConfirm(Display.getDefault().getActiveShell(), "Lisäys", "Toimipisteen lisääminen onnistui");
				paikkakunta.setText("");
				nimi.setText("");
				email.setText("");
				lahiosoite.setText("");
				postinumero.setText("");
				puh_num.setText("");
				
			}
		});
		btnLisTietokantaan.setText("Lis\u00E4\u00E4 tietokantaan");
		createContents();
	}

	/**
	 * Create contents of the shell.
	 */
	protected void createContents() {
		setText("Toimipisteen lis\u00E4ys");
		setSize(600, 400);

	}

	@Override
	protected void checkSubclass() {
		// Disable the check that prevents subclassing of SWT components
	}
}
