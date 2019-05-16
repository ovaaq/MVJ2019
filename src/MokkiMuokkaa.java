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

public class MokkiMuokkaa extends Shell {
	private Text nimi;
	private Text kapasiteetti;
	private Text hinta;
	private Text pinta_ala;
	
	PalveluJaMokki mokki = new PalveluJaMokki();
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
			MokkiMuokkaa shell = new MokkiMuokkaa(display);
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
	public MokkiMuokkaa(Display display) {
		super(display, SWT.SHELL_TRIM);
		setLayout(new GridLayout(1, false));
		
		Composite composite = new Composite(this, SWT.NONE);
		composite.setLayout(new GridLayout(1, false));
		
		Label lblMuokkaaMkinTietoja = new Label(composite, SWT.NONE);
		lblMuokkaaMkinTietoja.setFont(SWTResourceManager.getFont("Segoe UI", 12, SWT.NORMAL));
		lblMuokkaaMkinTietoja.setText("Muokkaa m\u00F6kin tietoja");
		
		Label lblMuokattavaMkki = new Label(composite, SWT.NONE);
		lblMuokattavaMkki.setBounds(0, 0, 81, 25);
		lblMuokattavaMkki.setText("Muokattava m\u00F6kki");
		
		Combo combo = new Combo(composite, SWT.READ_ONLY);
		GridData gd_combo = new GridData(SWT.LEFT, SWT.CENTER, false, false, 1, 1);
		gd_combo.widthHint = 116;
		combo.setLayoutData(gd_combo);
		combo.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				 
				mokki.otaTiedot(combo.getItem(combo.getSelectionIndex()).split(" ")[0]);
				toimipiste.otaTiedot(mokki.getM_toimipiste_ID());
				nimi.setText(mokki.getM_nimi());
				kapasiteetti.setText(mokki.getM_kapasiteetti());
				pinta_ala.setText(mokki.getM_pinta_ala());
				hinta.setText(mokki.getM_hinta());
				alv.setText(mokki.getM_alv());
				kuvaus.setText(mokki.getM_kuvaus());
				toimipiste_1.setText(toimipiste.getM_nimi());
			}
		});
		combo.setBounds(0, 0, 104, 33);
		combo.setText("Valitse m\u00F6kki");
		//tähän sisältö
		for(int i = 0; i<mokki.getLista("mokki").size(); i++) {
			combo.add(mokki.getLista("mokki").get(i).toString());
			}
		
		Composite composite_1 = new Composite(this, SWT.NONE);
		composite_1.setLayout(new GridLayout(4, false));
		
		Label lblMkinTiedot = new Label(composite_1, SWT.NONE);
		lblMkinTiedot.setLayoutData(new GridData(SWT.LEFT, SWT.CENTER, false, false, 4, 1));
		lblMkinTiedot.setBounds(0, 0, 81, 25);
		lblMkinTiedot.setText("M\u00F6kin tiedot");
		
		Label lblNimi = new Label(composite_1, SWT.NONE);
		lblNimi.setText("Nimi");
		new Label(composite_1, SWT.NONE);
		
		Label lblPintaala = new Label(composite_1, SWT.NONE);
		lblPintaala.setText("pinta-ala");
		
		Label lblPaikkakunta = new Label(composite_1, SWT.NONE);
		lblPaikkakunta.setText("Paikkakunta");
		
		nimi = new Text(composite_1, SWT.BORDER);
		nimi.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, false, false, 2, 1));
		nimi.setBounds(0, 0, 80, 31);
		
		pinta_ala = new Text(composite_1, SWT.BORDER);
		pinta_ala.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false, 1, 1));
		
		toimipiste_1 = new Text(composite_1, SWT.BORDER | SWT.READ_ONLY);
		toimipiste_1.setBackground(SWTResourceManager.getColor(SWT.COLOR_WIDGET_LIGHT_SHADOW));
		toimipiste_1.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false, 1, 1));
		
		Label lblKuvaus = new Label(composite_1, SWT.NONE);
		lblKuvaus.setText("Kuvaus");
		new Label(composite_1, SWT.NONE);
		new Label(composite_1, SWT.NONE);
		new Label(composite_1, SWT.NONE);
		
		kuvaus = new Text(composite_1, SWT.BORDER);
		kuvaus.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false, 3, 1));
		
		new Label(composite_1, SWT.NONE);
		
		Label lblNewLabel = new Label(composite_1, SWT.NONE);
		lblNewLabel.setText("Kapasiteetti");
		new Label(composite_1, SWT.NONE);
		
		Label lblHinta = new Label(composite_1, SWT.NONE);
		lblHinta.setText("Hinta");
		
		Label lblAlv = new Label(composite_1, SWT.NONE);
		lblAlv.setText("Alv(%)");
		
		kapasiteetti = new Text(composite_1, SWT.BORDER);
		kapasiteetti.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false, 2, 1));
		
		hinta = new Text(composite_1, SWT.BORDER);
		hinta.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false, 1, 1));
		
		alv = new Text(composite_1, SWT.BORDER);
		alv.setText("10");
		alv.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false, 1, 1));
		
		Composite composite_2 = new Composite(this, SWT.NONE);
		composite_2.setLayout(new GridLayout(1, false));
		
		Button btnTallennaMuutokset = new Button(composite_2, SWT.NONE);
		GridData gd_btnTallennaMuutokset = new GridData(SWT.LEFT, SWT.CENTER, false, false, 1, 1);
		gd_btnTallennaMuutokset.heightHint = 60;
		gd_btnTallennaMuutokset.widthHint = 170;
		btnTallennaMuutokset.setLayoutData(gd_btnTallennaMuutokset);
		btnTallennaMuutokset.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				try {
					mokki.paivita(kapasiteetti.getText(), pinta_ala.getText(), kuvaus.getText(), "mokki", hinta.getText(), nimi.getText(), alv.getText(), toimipiste.getM_toimipiste_ID(), combo.getItem(combo.getSelectionIndex()).split(" ")[0]);
					combo.removeAll();
					for(int i = 0; i<mokki.getLista("mokki").size(); i++) {
						combo.add(mokki.getLista("mokki").get(i).toString());
						}
					kapasiteetti.setText("");
					pinta_ala.setText("");
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
		btnTallennaMuutokset.setBounds(0, 0, 105, 35);
		btnTallennaMuutokset.setText("Tallenna muutokset");
		createContents();
	}

	/**
	 * Create contents of the shell.
	 */
	protected void createContents() {
		setText("Muokkaa m\u00F6kki\u00E4");
		setSize(800, 600);

	}

	@Override
	protected void checkSubclass() {
		// Disable the check that prevents subclassing of SWT components
	}

}
