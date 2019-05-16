import org.eclipse.jface.dialogs.MessageDialog;
import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Combo;
import org.eclipse.swt.layout.GridData;
import org.eclipse.wb.swt.SWTResourceManager;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.widgets.Text;

public class PalveluPoista extends Shell {

	PalveluJaMokki palvelu = new PalveluJaMokki();
	Toimipiste toimipiste = new Toimipiste();
	private Text nimi;
	private Text hinta;
	private Text kuvaus;
	private Text alv;
	private Text toimipiste_1;
	
	/**
	 * Launch the application.
	 * @param args
	 */
	public static void main(String args[]) {
		try {
			Display display = Display.getDefault();
			PalveluPoista shell = new PalveluPoista(display);
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
	public PalveluPoista(Display display) {
		super(display, SWT.SHELL_TRIM);
		setLayout(new GridLayout(1, false));
		
		Label lblPoistaPalveluJrjestelmst = new Label(this, SWT.NONE);
		lblPoistaPalveluJrjestelmst.setFont(SWTResourceManager.getFont("Segoe UI", 12, SWT.NORMAL));
		lblPoistaPalveluJrjestelmst.setText("Poista palvelu j\u00E4rjestelm\u00E4st\u00E4");
		
		Composite composite = new Composite(this, SWT.NONE);
		composite.setLayout(new GridLayout(1, false));
		
		Label lblPoistettavaPalvelu = new Label(composite, SWT.NONE);
		lblPoistettavaPalvelu.setBounds(0, 0, 81, 25);
		lblPoistettavaPalvelu.setText("Poistettava palvelu");
		
		Combo combo = new Combo(composite, SWT.READ_ONLY);
		combo.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, false, false, 1, 1));
		combo.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				
				palvelu.otaTiedot(combo.getItem(combo.getSelectionIndex()).split(" ")[0]);
				toimipiste.otaTiedot(palvelu.getM_toimipiste_ID());
				nimi.setText(palvelu.getM_nimi());
				hinta.setText(palvelu.getM_hinta());
				alv.setText(palvelu.getM_alv());
				kuvaus.setText(palvelu.getM_kuvaus());
				toimipiste_1.setText(toimipiste.getM_nimi());
			}
		});
		combo.setBounds(0, 0, 104, 33);
		combo.setText("Valitse palvelu");
		//tähän sisältö
		for(int i = 0; i<palvelu.getLista("palvelu").size(); i++) {
			combo.add(palvelu.getLista("palvelu").get(i).toString());
			}
		
		Composite composite_1 = new Composite(this, SWT.NONE);
		composite_1.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, false, false, 1, 1));
		composite_1.setLayout(new GridLayout(2, false));
		
		Label lblPalvelunTiedot = new Label(composite_1, SWT.NONE);
		lblPalvelunTiedot.setLayoutData(new GridData(SWT.LEFT, SWT.CENTER, false, false, 2, 1));
		lblPalvelunTiedot.setBounds(0, 0, 81, 25);
		lblPalvelunTiedot.setText("Palvelun tiedot");
		
		Label lblNimi = new Label(composite_1, SWT.NONE);
		lblNimi.setText("Nimi");
		new Label(composite_1, SWT.NONE);
		
		nimi = new Text(composite_1, SWT.BORDER);
		nimi.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false, 2, 1));
		
		Label lblKuvaus = new Label(composite_1, SWT.NONE);
		lblKuvaus.setText("Kuvaus");
		new Label(composite_1, SWT.NONE);
		
		kuvaus = new Text(composite_1, SWT.BORDER);
		kuvaus.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false, 2, 1));
		
		Label lblHinta = new Label(composite_1, SWT.NONE);
		lblHinta.setText("Hinta");
		
		Label lblAlv = new Label(composite_1, SWT.NONE);
		lblAlv.setText("Alv");
		
		hinta = new Text(composite_1, SWT.BORDER);
		hinta.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false, 1, 1));
		
		alv = new Text(composite_1, SWT.BORDER);
		alv.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false, 1, 1));
		
		Label lblToimipiste = new Label(composite_1, SWT.NONE);
		lblToimipiste.setText("Toimipiste");
		new Label(composite_1, SWT.NONE);
		
		toimipiste_1 = new Text(composite_1, SWT.BORDER | SWT.READ_ONLY);
		toimipiste_1.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false, 1, 1));
		new Label(composite_1, SWT.NONE);
		
		Composite composite_2 = new Composite(this, SWT.NONE);
		composite_2.setLayout(new GridLayout(1, false));
		
		Button btnPoistaPalvelu = new Button(composite_2, SWT.NONE);
		GridData gd_btnPoistaPalvelu = new GridData(SWT.LEFT, SWT.CENTER, false, false, 1, 1);
		gd_btnPoistaPalvelu.heightHint = 60;
		gd_btnPoistaPalvelu.widthHint = 170;
		btnPoistaPalvelu.setLayoutData(gd_btnPoistaPalvelu);
		btnPoistaPalvelu.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				palvelu.poista(combo.getItem(combo.getSelectionIndex()).split(" ")[0]);
				combo.removeAll();
				for(int i = 0; i<palvelu.getLista("palvelu").size(); i++) {
					combo.add(palvelu.getLista("palvelu").get(i).toString());
					}

				kuvaus.setText("");
				hinta.setText("");
				nimi.setText("");
				alv.setText("");
				toimipiste_1.setText("");
				
				MessageDialog.openConfirm(Display.getDefault().getActiveShell(), "Poisto", "Palvelun poistaminen onnistui");
			}
		});
		btnPoistaPalvelu.setBounds(0, 0, 105, 35);
		btnPoistaPalvelu.setText("Poista palvelu");
		createContents();
	}

	/**
	 * Create contents of the shell.
	 */
	protected void createContents() {
		setText("Poista palvelu");
		setSize(800, 600);

	}

	@Override
	protected void checkSubclass() {
		// Disable the check that prevents subclassing of SWT components
	}

}
