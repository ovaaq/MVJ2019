import org.eclipse.jface.dialogs.MessageDialog;
import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Text;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.widgets.Combo;
import org.eclipse.wb.swt.SWTResourceManager;

public class MokkiLisaa extends Shell {
	private Text nimi;
	private Text kapasiteetti;
	private Text hinta;
	private Text pinta_ala;
	private Text kuvaus;
	private Text alv;
	
	PalveluJaMokki mokki = new PalveluJaMokki();
	Toimipiste toimipiste = new Toimipiste();

	/**
	 * Launch the application.
	 * @param args
	 */
	public static void main(String args[]) {
		try {
			Display display = Display.getDefault();
			MokkiLisaa shell = new MokkiLisaa(display);
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
	public MokkiLisaa(Display display) {
		super(display, SWT.SHELL_TRIM);
		setLayout(new GridLayout(1, false));
		
		Composite composite = new Composite(this, SWT.NONE);
		composite.setBounds(0, 0, 64, 64);
		composite.setLayout(new GridLayout(1, false));
		
		Label lblLisUusiMkki = new Label(composite, SWT.NONE);
		lblLisUusiMkki.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, false, false, 1, 1));
		lblLisUusiMkki.setFont(SWTResourceManager.getFont("Segoe UI", 12, SWT.NORMAL));
		lblLisUusiMkki.setText("Lis\u00E4\u00E4 uusi m\u00F6kki");
		
		Composite composite_1 = new Composite(this, SWT.NONE);
		GridData gd_composite_1 = new GridData(SWT.LEFT, SWT.CENTER, false, false, 1, 1);
		gd_composite_1.widthHint = 356;
		composite_1.setLayoutData(gd_composite_1);
		composite_1.setBounds(0, 0, 64, 64);
		composite_1.setLayout(new GridLayout(5, false));
		
		Label lblMkinNimi = new Label(composite_1, SWT.NONE);
		lblMkinNimi.setText("M\u00F6kin nimi");
		new Label(composite_1, SWT.NONE);
		
		Label lblHuoneet = new Label(composite_1, SWT.NONE);
		lblHuoneet.setLayoutData(new GridData(SWT.CENTER, SWT.CENTER, false, false, 1, 1));
		lblHuoneet.setText("pinta-ala");
		new Label(composite_1, SWT.NONE);
		
		
		
		Label lblToimipiste = new Label(composite_1, SWT.NONE);
		lblToimipiste.setText("Toimipiste");
		
		nimi = new Text(composite_1, SWT.BORDER);
		nimi.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, false, false, 2, 1));
		
		pinta_ala = new Text(composite_1, SWT.BORDER);
		pinta_ala.setLayoutData(new GridData(SWT.CENTER, SWT.CENTER, false, false, 2, 1));
		pinta_ala.setSize(80, 31);
		
		Combo combo = new Combo(composite_1, SWT.READ_ONLY);
		combo.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false, 1, 1));
		
		for(int i = 0; i<toimipiste.getLista().size(); i++) {
			combo.add(toimipiste.getLista().get(i).toString());
			}
		
		Label lblKuvaus = new Label(composite_1, SWT.NONE);
		lblKuvaus.setText("Kuvaus");
		new Label(composite_1, SWT.NONE);
		new Label(composite_1, SWT.NONE);
		new Label(composite_1, SWT.NONE);
		new Label(composite_1, SWT.NONE);
		
		kuvaus = new Text(composite_1, SWT.BORDER);
		kuvaus.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false, 4, 1));
		new Label(composite_1, SWT.NONE);
		
		Label lblOsoite = new Label(composite_1, SWT.NONE);
		lblOsoite.setSize(56, 25);
		lblOsoite.setText("Kapasiteetti");
		new Label(composite_1, SWT.NONE);
		
		Label lblHinta = new Label(composite_1, SWT.NONE);
		lblHinta.setSize(42, 25);
		lblHinta.setText("Hinta");
		new Label(composite_1, SWT.NONE);
		
		Label lblAlv = new Label(composite_1, SWT.NONE);
		lblAlv.setText("alv (%)");
		
		kapasiteetti = new Text(composite_1, SWT.BORDER);
		kapasiteetti.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, false, false, 2, 1));
		kapasiteetti.setSize(80, 31);
		
		hinta = new Text(composite_1, SWT.BORDER);
		hinta.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, false, false, 2, 1));
		hinta.setSize(80, 31);
		
		alv = new Text(composite_1, SWT.BORDER);
		alv.setText("10");
		alv.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false, 1, 1));
		
		Composite composite_2 = new Composite(this, SWT.NONE);
		composite_2.setLayout(new GridLayout(1, false));
		
		Button LisääMökki = new Button(composite_2, SWT.NONE);
		GridData gd_LisääMökki = new GridData(SWT.LEFT, SWT.CENTER, false, false, 1, 1);
		gd_LisääMökki.heightHint = 60;
		gd_LisääMökki.widthHint = 130;
		LisääMökki.setLayoutData(gd_LisääMökki);
		LisääMökki.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				mokki.lisaa(kapasiteetti.getText(), pinta_ala.getText(), kuvaus.getText(), "mokki", hinta.getText(), nimi.getText(), alv.getText(), combo.getItem(combo.getSelectionIndex()).split(" ")[0]);
				MessageDialog.openConfirm(Display.getDefault().getActiveShell(), "Lisäys", "Mökin lisääminen onnistui");
				
				kapasiteetti.setText("");
				pinta_ala.setText("");
				kuvaus.setText("");
				hinta.setText("");
				nimi.setText("");
				alv.setText("");
				combo.removeAll();
				for(int i = 0; i<mokki.getLista("mokki").size(); i++) {
					combo.add(mokki.getLista("mokki").get(i).toString());
					}
				
			}
		});
		LisääMökki.setText("Lis\u00E4\u00E4 m\u00F6kki");
		createContents();
	}

	/**
	 * Create contents of the shell.
	 */
	protected void createContents() {
		setText("Lis\u00E4\u00E4 m\u00F6kki");
		setSize(800, 600);

	}

	@Override
	protected void checkSubclass() {
		// Disable the check that prevents subclassing of SWT components
	}

}
