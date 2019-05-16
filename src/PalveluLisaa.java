import org.eclipse.jface.dialogs.MessageDialog;
import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Text;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.widgets.Combo;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;

public class PalveluLisaa extends Shell {
	private Text nimi;
	private Text hinta;
	private Text kuvaus;
	private Text alv;
	
	PalveluJaMokki palvelu = new PalveluJaMokki();
	Toimipiste toimipiste = new Toimipiste();

	/**
	 * Launch the application.
	 * @param args
	 */
	public static void main(String args[]) {
		try {
			Display display = Display.getDefault();
			PalveluLisaa shell = new PalveluLisaa(display);
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
	public PalveluLisaa(Display display) {
		super(display, SWT.SHELL_TRIM);
		setLayout(new GridLayout(1, false));
		
		Composite composite = new Composite(this, SWT.NONE);
		composite.setLayout(new GridLayout(1, false));
		
		Label lblNewLabel = new Label(composite, SWT.NONE);
		lblNewLabel.setText("Uuden palvelun luonti");
		
		Composite composite_1 = new Composite(this, SWT.NONE);
		GridData gd_composite_1 = new GridData(SWT.LEFT, SWT.CENTER, false, false, 1, 1);
		gd_composite_1.widthHint = 278;
		composite_1.setLayoutData(gd_composite_1);
		composite_1.setLayout(new GridLayout(3, false));
		
		Label lblPalvelunNimi = new Label(composite_1, SWT.NONE);
		lblPalvelunNimi.setText("Palvelun nimi");
		new Label(composite_1, SWT.NONE);
		
		Label lblToimipiste = new Label(composite_1, SWT.NONE);
		lblToimipiste.setText("Toimipiste");
		
		nimi = new Text(composite_1, SWT.BORDER);
		nimi.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, false, false, 2, 1));
		
		Combo combo = new Combo(composite_1, SWT.READ_ONLY);
		combo.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false, 1, 1));
		for(int i = 0; i<toimipiste.getLista().size(); i++) {
			combo.add(toimipiste.getLista().get(i).toString());
			}
		
		Label lblKuvaus = new Label(composite_1, SWT.NONE);
		lblKuvaus.setText("Kuvaus");
		new Label(composite_1, SWT.NONE);
		new Label(composite_1, SWT.NONE);
		
		kuvaus = new Text(composite_1, SWT.BORDER);
		kuvaus.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false, 2, 1));
		new Label(composite_1, SWT.NONE);
		
		Label lblHinta = new Label(composite_1, SWT.NONE);
		lblHinta.setBounds(0, 0, 81, 25);
		lblHinta.setText("Hinta");
		new Label(composite_1, SWT.NONE);
		new Label(composite_1, SWT.NONE);
		
		hinta = new Text(composite_1, SWT.BORDER);
		hinta.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, false, false, 2, 1));
		hinta.setBounds(0, 0, 80, 31);
		new Label(composite_1, SWT.NONE);
		
		Label lblAlv = new Label(composite_1, SWT.NONE);
		lblAlv.setText("alv");
		new Label(composite_1, SWT.NONE);
		new Label(composite_1, SWT.NONE);
		
		alv = new Text(composite_1, SWT.BORDER);
		alv.setText("24");
		alv.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false, 2, 1));
		new Label(composite_1, SWT.NONE);
		
		Composite composite_2 = new Composite(this, SWT.NONE);
		composite_2.setLayout(new GridLayout(1, false));
		
		Button btnNewButton = new Button(composite_2, SWT.NONE);
		GridData gd_btnNewButton = new GridData(SWT.LEFT, SWT.CENTER, false, false, 1, 1);
		gd_btnNewButton.heightHint = 60;
		gd_btnNewButton.widthHint = 170;
		btnNewButton.setLayoutData(gd_btnNewButton);
		btnNewButton.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				palvelu.lisaa(null, null, kuvaus.getText(), "palvelu", hinta.getText(), nimi.getText(), alv.getText(), combo.getItem(combo.getSelectionIndex()).split(" ")[0]);
				MessageDialog.openConfirm(Display.getDefault().getActiveShell(), "Lisäys", "Palvelun lisääminen onnistui");
			
				kuvaus.setText("");
				hinta.setText("");
				nimi.setText("");
				alv.setText("");
				combo.removeAll();
				for(int i = 0; i<palvelu.getLista("palvelu").size(); i++) {
					combo.add(palvelu.getLista("palvelu").get(i).toString());
					}
			}
		});
		btnNewButton.setText("Lis\u00E4\u00E4 palvelu");
		createContents();
	}

	/**
	 * Create contents of the shell.
	 */
	protected void createContents() {
		setText("Lis\u00E4\u00E4 palvelu");
		setSize(800, 600);

	}

	@Override
	protected void checkSubclass() {
		// Disable the check that prevents subclassing of SWT components
	}

}
