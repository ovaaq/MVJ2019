import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Combo;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;

public class LisapalvelutRaportti extends Shell {

	/**
	 * Launch the application.
	 * @param args
	 */
	public static void main(String args[]) {
		try {
			Display display = Display.getDefault();
			LisapalvelutRaportti shell = new LisapalvelutRaportti(display);
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
	public LisapalvelutRaportti(Display display) {
		super(display, SWT.SHELL_TRIM);
		setLayout(new GridLayout(1, false));
		
		Composite composite = new Composite(this, SWT.NONE);
		composite.setLayout(new GridLayout(1, false));
		
		Label lblPalvelutRaportitLuonti = new Label(composite, SWT.NONE);
		lblPalvelutRaportitLuonti.setBounds(0, 0, 81, 25);
		lblPalvelutRaportitLuonti.setText("Palvelut raportin luonti");
		
		Composite composite_1 = new Composite(this, SWT.NONE);
		composite_1.setLayout(new GridLayout(1, false));
		
		Label lblToimipisteRaportti = new Label(composite_1, SWT.NONE);
		lblToimipisteRaportti.setBounds(0, 0, 81, 25);
		lblToimipisteRaportti.setText("Toimipiste - raportti");
		
		Combo combo = new Combo(composite_1, SWT.READ_ONLY);
		combo.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
			}
		});
		combo.setBounds(0, 0, 104, 33);
		combo.setText("Valitse toimipiste");
		//tähän sisältö
		
		Composite composite_2 = new Composite(this, SWT.NONE);
		composite_2.setLayout(new GridLayout(1, false));
		
		Button btnLuoPalveluraportti = new Button(composite_2, SWT.NONE);
		btnLuoPalveluraportti.setBounds(0, 0, 105, 35);
		btnLuoPalveluraportti.setText("Luo palveluraportti");
		createContents();
	}

	/**
	 * Create contents of the shell.
	 */
	protected void createContents() {
		setText("Lis\u00E4palveluraportti");
		setSize(600, 400);

	}

	@Override
	protected void checkSubclass() {
		// Disable the check that prevents subclassing of SWT components
	}

}
