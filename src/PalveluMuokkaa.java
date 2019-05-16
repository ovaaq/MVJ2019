import org.eclipse.jface.dialogs.MessageDialog;
import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Combo;
import org.eclipse.swt.widgets.Text;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.wb.swt.SWTResourceManager;

public class PalveluMuokkaa extends Shell {
	private Text nimi;
	private Text hinta;

	PalveluJaMokki palvelu = new PalveluJaMokki();
	Toimipiste toimipiste = new Toimipiste();
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
			PalveluMuokkaa shell = new PalveluMuokkaa(display);
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
	public PalveluMuokkaa(Display display) {
		super(display, SWT.SHELL_TRIM);
		setLayout(new GridLayout(1, false));
		
		Composite composite = new Composite(this, SWT.NONE);
		composite.setLayout(new GridLayout(1, false));
		
		Label lblMuokkaaPalvelunTietoja = new Label(composite, SWT.NONE);
		lblMuokkaaPalvelunTietoja.setFont(SWTResourceManager.getFont("Segoe UI", 12, SWT.NORMAL));
		lblMuokkaaPalvelunTietoja.setText("Muokkaa palvelun tietoja");
		
		Label lblValitseMuokattavaPalvelu = new Label(composite, SWT.NONE);
		lblValitseMuokattavaPalvelu.setBounds(0, 0, 81, 25);
		lblValitseMuokattavaPalvelu.setText("Muokattava palvelu");
		
		Combo combo = new Combo(composite, SWT.READ_ONLY);
		GridData gd_combo = new GridData(SWT.LEFT, SWT.CENTER, false, false, 1, 1);
		gd_combo.widthHint = 120;
		combo.setLayoutData(gd_combo);
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
		nimi.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, false, false, 2, 1));
		nimi.setBounds(0, 0, 80, 31);
		
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
		
		toimipiste_1 = new Text(composite_1, SWT.BORDER | SWT.READ_ONLY);
		toimipiste_1.setText("toimipiste");
		toimipiste_1.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false, 1, 1));
		new Label(composite_1, SWT.NONE);
		
		Composite composite_2 = new Composite(this, SWT.NONE);
		composite_2.setLayout(new GridLayout(1, false));
		
		Button btnPivitTiedot = new Button(composite_2, SWT.NONE);
		GridData gd_btnPivitTiedot = new GridData(SWT.LEFT, SWT.CENTER, false, false, 1, 1);
		gd_btnPivitTiedot.widthHint = 170;
		gd_btnPivitTiedot.heightHint = 60;
		btnPivitTiedot.setLayoutData(gd_btnPivitTiedot);
		btnPivitTiedot.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				try {
					palvelu.paivita(null, null, kuvaus.getText(), "palvelu", hinta.getText(), nimi.getText(), alv.getText(), toimipiste.getM_toimipiste_ID(), combo.getItem(combo.getSelectionIndex()).split(" ")[0]);
					combo.removeAll();
					for(int i = 0; i<palvelu.getLista("palvelu").size(); i++) {
						combo.add(palvelu.getLista("palvelu").get(i).toString());
						}
				
					kuvaus.setText("");
					hinta.setText("");
					nimi.setText("");
					alv.setText("");
					toimipiste_1.setText("");
					MessageDialog.openConfirm(Display.getDefault().getActiveShell(), "Päivitys", "Päivitys onnistui");
				}catch(Exception e1) {
					e1.printStackTrace();
				}
			}
		});
		btnPivitTiedot.setBounds(0, 0, 105, 35);
		btnPivitTiedot.setText("P\u00E4ivit\u00E4 tiedot");
		createContents();
	}

	/**
	 * Create contents of the shell.
	 */
	protected void createContents() {
		setText("Muokkaa palvelua");
		setSize(800, 600);

	}

	@Override
	protected void checkSubclass() {
		// Disable the check that prevents subclassing of SWT components
	}

}
