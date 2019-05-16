import org.eclipse.jface.dialogs.MessageDialog;
import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Combo;
import org.eclipse.swt.widgets.Text;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.widgets.Button;
import org.eclipse.wb.swt.SWTResourceManager;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;

public class ToimipisteMuokkaa extends Shell {
	private Text paikkakunta;
	private Text lahiosoite;
	private Text postinumero;
	private Text puh_num;
	private Text nimi;
	private Text email;
	
	Toimipiste toimipiste = new Toimipiste();
	
	/**
	 * Launch the application.
	 * @param args
	 */
	public static void main(String args[]) {
		try {
			Display display = Display.getDefault();
			ToimipisteMuokkaa shell = new ToimipisteMuokkaa(display);
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
	public ToimipisteMuokkaa(Display display) {
		super(display, SWT.SHELL_TRIM);
		setBackground(SWTResourceManager.getColor(SWT.COLOR_WIDGET_LIGHT_SHADOW));
		setLayout(new GridLayout(1, false));
		
		Composite composite = new Composite(this, SWT.NONE);
		composite.setBackground(SWTResourceManager.getColor(SWT.COLOR_WIDGET_LIGHT_SHADOW));
		composite.setLayout(new GridLayout(1, false));
		
		Label lblNewLabel = new Label(composite, SWT.NONE);
		lblNewLabel.setBackground(SWTResourceManager.getColor(SWT.COLOR_WIDGET_LIGHT_SHADOW));
		lblNewLabel.setText("Muokattava toimipiste");
		
		Combo combo = new Combo(composite, SWT.READ_ONLY);
		combo.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				
				toimipiste.otaTiedot(combo.getItem(combo.getSelectionIndex()).split(" ")[0]);
				paikkakunta.setText(toimipiste.getM_paikkakunta());
				nimi.setText(toimipiste.getM_nimi());
				email.setText(toimipiste.getM_email());
				lahiosoite.setText(toimipiste.getM_lahiosoite());
				postinumero.setText(toimipiste.getM_postinumero());
				puh_num.setText(toimipiste.getM_puh_nro());
			}
		});
		
		combo.setBounds(0, 0, 104, 33);
		combo.setText("Valitse toimipiste");
		
		for(int i = 0; i<toimipiste.getLista().size(); i++) {
		combo.add(toimipiste.getLista().get(i).toString());
		}
		
		Composite composite_1 = new Composite(this, SWT.NONE);
		composite_1.setBackground(SWTResourceManager.getColor(SWT.COLOR_WIDGET_LIGHT_SHADOW));
		composite_1.setLayout(new GridLayout(6, false));
		
		Label lblNewLabel_1 = new Label(composite_1, SWT.NONE);
		lblNewLabel_1.setBackground(SWTResourceManager.getColor(SWT.COLOR_WIDGET_LIGHT_SHADOW));
		lblNewLabel_1.setLayoutData(new GridData(SWT.LEFT, SWT.CENTER, false, false, 3, 1));
		lblNewLabel_1.setBounds(0, 0, 81, 25);
		lblNewLabel_1.setText("Toimipisteen tiedot");
		new Label(composite_1, SWT.NONE);
		new Label(composite_1, SWT.NONE);
		new Label(composite_1, SWT.NONE);
		
		paikkakunta = new Text(composite_1, SWT.BORDER);
		paikkakunta.setText("paikkakunta");
		paikkakunta.setBounds(0, 0, 80, 31);
		
		lahiosoite = new Text(composite_1, SWT.BORDER);
		lahiosoite.setText("l\u00E4hiosoite");
		lahiosoite.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false, 1, 1));
		
		postinumero = new Text(composite_1, SWT.BORDER);
		postinumero.setText("postinumero");
		postinumero.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false, 1, 1));
		
		puh_num = new Text(composite_1, SWT.BORDER);
		puh_num.setText("puh_num");
		puh_num.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false, 1, 1));
		
		nimi = new Text(composite_1, SWT.BORDER);
		nimi.setText("nimi");
		nimi.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false, 1, 1));
		
		email = new Text(composite_1, SWT.BORDER);
		email.setText("email");
		email.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false, 1, 1));
		
		Composite composite_2 = new Composite(this, SWT.NONE);
		composite_2.setBackground(SWTResourceManager.getColor(SWT.COLOR_WIDGET_LIGHT_SHADOW));
		composite_2.setLayout(new GridLayout(1, false));
		
		Button btnTallennaTiedot = new Button(composite_2, SWT.NONE);
		btnTallennaTiedot.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				
				try {
				toimipiste.paivita(nimi.getText(), puh_num.getText(), lahiosoite.getText(), postinumero.getText(), paikkakunta.getText(), email.getText(), combo.getItem(combo.getSelectionIndex()).split(" ")[0]);
				combo.removeAll();
				for(int i = 0; i<toimipiste.getLista().size(); i++) {
					combo.add(toimipiste.getLista().get(i).toString());
					}
				paikkakunta.setText("");
				nimi.setText("");
				email.setText("");
				lahiosoite.setText("");
				postinumero.setText("");
				puh_num.setText("");
				MessageDialog.openConfirm(Display.getDefault().getActiveShell(), "Päivitys", "Päivitys onnistui");
				}catch(Exception e1){
					e1.printStackTrace();
				}
			}
		});
		btnTallennaTiedot.setBounds(0, 0, 105, 35);
		btnTallennaTiedot.setText("Tallenna tiedot");
		createContents();
	}

	/**
	 * Create contents of the shell.
	 */
	protected void createContents() {
		setText("Toimipisteen muokkaus");
		setSize(600, 400);

	}

	@Override
	protected void checkSubclass() {
		// Disable the check that prevents subclassing of SWT components
	}

}
