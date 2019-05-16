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

public class MokkiPoista extends Shell {
	private Text nimi;
	private Text kapasiteetti;
	private Text hinta;
	private Text pinta_ala;

	PalveluJaMokki mokki = new PalveluJaMokki();
	Toimipiste toimipiste = new Toimipiste();
	private Text alv;
	private Text kuvaus;
	private Text toimipiste_1;
	
	/**
	 * Launch the application.
	 * @param args
	 */
	public static void main(String args[]) {
		try {
			Display display = Display.getDefault();
			MokkiPoista shell = new MokkiPoista(display);
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
	public MokkiPoista(Display display) {
		super(display, SWT.SHELL_TRIM);
		setLayout(new GridLayout(1, false));
		
		Composite composite = new Composite(this, SWT.NONE);
		composite.setLayout(new GridLayout(1, false));
		
		Label lblMkinPoistoJrjestelmst = new Label(composite, SWT.NONE);
		lblMkinPoistoJrjestelmst.setFont(SWTResourceManager.getFont("Segoe UI", 12, SWT.NORMAL));
		lblMkinPoistoJrjestelmst.setText("M\u00F6kin poisto j\u00E4rjestelm\u00E4st\u00E4");
		
		Label lblPoistettavaMkki = new Label(composite, SWT.NONE);
		lblPoistettavaMkki.setBounds(0, 0, 81, 25);
		lblPoistettavaMkki.setText("Poistettava m\u00F6kki");
		
		Combo combo = new Combo(composite, SWT.READ_ONLY);
		GridData gd_combo = new GridData(SWT.LEFT, SWT.CENTER, false, false, 1, 1);
		gd_combo.widthHint = 132;
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
		GridData gd_composite_1 = new GridData(SWT.LEFT, SWT.CENTER, false, false, 1, 1);
		gd_composite_1.widthHint = 333;
		composite_1.setLayoutData(gd_composite_1);
		composite_1.setLayout(new GridLayout(4, false));
		
		Label lblMkinTiedot = new Label(composite_1, SWT.NONE);
		lblMkinTiedot.setLayoutData(new GridData(SWT.LEFT, SWT.CENTER, false, false, 4, 1));
		lblMkinTiedot.setBounds(0, 0, 81, 25);
		lblMkinTiedot.setText("M\u00F6kin tiedot");
		
		Label lblNimi = new Label(composite_1, SWT.NONE);
		lblNimi.setText("Nimi");
		new Label(composite_1, SWT.NONE);
		
		Label lblPintaala = new Label(composite_1, SWT.NONE);
		lblPintaala.setText("Pinta-ala");
		
		Label lblToimipiste = new Label(composite_1, SWT.NONE);
		lblToimipiste.setText("Toimipiste");
		
		nimi = new Text(composite_1, SWT.BORDER);
		GridData gd_nimi = new GridData(SWT.FILL, SWT.CENTER, false, false, 2, 1);
		gd_nimi.widthHint = 148;
		nimi.setLayoutData(gd_nimi);
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
		
		Label lblKapasiteetti = new Label(composite_1, SWT.NONE);
		lblKapasiteetti.setText("Kapasiteetti");
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
		alv.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false, 1, 1));
		
		Composite composite_2 = new Composite(this, SWT.NONE);
		composite_2.setLayout(new GridLayout(1, false));
		
		Button btnPoistaMkki = new Button(composite_2, SWT.NONE);
		GridData gd_btnPoistaMkki = new GridData(SWT.LEFT, SWT.CENTER, false, false, 1, 1);
		gd_btnPoistaMkki.heightHint = 60;
		gd_btnPoistaMkki.widthHint = 170;
		btnPoistaMkki.setLayoutData(gd_btnPoistaMkki);
		btnPoistaMkki.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				
				mokki.poista(combo.getItem(combo.getSelectionIndex()).split(" ")[0]);
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
				
				MessageDialog.openConfirm(Display.getDefault().getActiveShell(), "Poisto", "Mökin poistaminen onnistui");
				
			}
		});
		btnPoistaMkki.setBounds(0, 0, 105, 35);
		btnPoistaMkki.setText("Poista m\u00F6kki");
		createContents();
	}

	/**
	 * Create contents of the shell.
	 */
	protected void createContents() {
		setText("Poista m\u00F6kki");
		setSize(800, 600);

	}

	@Override
	protected void checkSubclass() {
		// Disable the check that prevents subclassing of SWT components
	}

}
