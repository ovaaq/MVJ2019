import java.awt.Desktop;
import java.io.File;
import java.io.IOException;

import org.eclipse.jface.dialogs.MessageDialog;
import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.DateTime;
import org.eclipse.swt.widgets.Combo;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.widgets.Text;

public class LaskutListaa extends Shell {
	private Text status;
	Boolean maksettu = null;

	/**
	 * Launch the application.
	 * @param args
	 */
	public static void main(String args[]) {
		try {
			Display display = Display.getDefault();
			LaskutListaa shell = new LaskutListaa(display);
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
	public LaskutListaa(Display display) {
		super(display, SWT.SHELL_TRIM);
		setLayout(new GridLayout(4, false));
		
		Composite composite = new Composite(this, SWT.NONE);
		composite.setLayout(new GridLayout(1, false));
		
		Label lblLaskujenTarkastelu = new Label(composite, SWT.NONE);
		lblLaskujenTarkastelu.setBounds(0, 0, 81, 25);
		lblLaskujenTarkastelu.setText("Laskun seuranta");
		new Label(this, SWT.NONE);
		new Label(this, SWT.NONE);
		new Label(this, SWT.NONE);
		
		Composite composite_2 = new Composite(this, SWT.NONE);
		composite_2.setBounds(0, 0, 64, 64);
		composite_2.setLayout(new GridLayout(1, false));
		
		Label lblvalitseAsiakas = new Label(composite_2, SWT.NONE);
		lblvalitseAsiakas.setBounds(0, 0, 81, 25);
		lblvalitseAsiakas.setText("Valitse asiakas");
		
		Label lblValitseLasku = new Label(this, SWT.NONE);
		
		lblValitseLasku.setText("Valitse Lasku");
		new Label(this, SWT.NONE);
		
		Label lblStatus = new Label(this, SWT.NONE);
		lblStatus.setText("Status");
		Asiakas asiakas = new Asiakas();
		Varaus varaus = new Varaus();
		
		Combo combo_1 = new Combo(this, SWT.READ_ONLY);
		Combo combo = new Combo(this, SWT.READ_ONLY);
		
		for(int i = 0; i<asiakas.getLista().size(); i++) {
			combo_1.add(asiakas.getLista().get(i).toString());
			}
		
		
		
		combo_1.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, false, false, 1, 1));
		
		
		combo.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, false, false, 1, 1));
		new Label(this, SWT.NONE);
		
		status = new Text(this, SWT.BORDER | SWT.READ_ONLY);
		status.setLayoutData(new GridData(SWT.LEFT, SWT.CENTER, true, false, 1, 1));
		new Label(this, SWT.NONE);
		
		Button btnPoistaLasku = new Button(this, SWT.NONE);
		btnPoistaLasku.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				varaus.Piilota(combo.getItem(combo.getSelectionIndex()).split(" ")[0]);
				combo.removeAll();
				for(int i = 0; i<varaus.getListaAsiakasFiltteri(asiakas.getM_asiakas_ID()).size(); i++) {
					combo.add(varaus.getListaAsiakasFiltteri(asiakas.getM_asiakas_ID()).get(i).toString());
					}
				status.setText(" ");
				 MessageDialog.openConfirm(Display.getDefault().getActiveShell(), "Poisto", "Laskun poistaminen onnistui");

			}
		});
		btnPoistaLasku.setText("Poista lasku");
		new Label(this, SWT.NONE);
		new Label(this, SWT.NONE);
		
		Label lblVaihdaStatus = new Label(this, SWT.NONE);
		lblVaihdaStatus.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, false, false, 1, 1));
		lblVaihdaStatus.setText("Vaihda Status");
		new Label(this, SWT.NONE);
		new Label(this, SWT.NONE);
		new Label(this, SWT.NONE);
		
		Button btnMaksettu = new Button(this, SWT.RADIO);
		btnMaksettu.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				
				maksettu = !maksettu;
				System.out.println(maksettu);
			}
		});
		btnMaksettu.setText("Maksettu");
		new Label(this, SWT.NONE);
		new Label(this, SWT.NONE);
		new Label(this, SWT.NONE);
		
		
		
		Button btnMaksamaton = new Button(this, SWT.RADIO);
		btnMaksamaton.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				
			}
		});
		
		combo_1.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				asiakas.otaTiedot(combo_1.getItem(combo_1.getSelectionIndex()).split(" ")[0]);
				combo.removeAll();
				for(int i = 0; i<varaus.getListaAsiakasFiltteri(asiakas.getM_asiakas_ID()).size(); i++) {
					combo.add(varaus.getListaAsiakasFiltteri(asiakas.getM_asiakas_ID()).get(i).toString());
					}
			
				status.setText("");
				btnMaksamaton.setSelection(false);
				btnMaksettu.setSelection(false);
				
			}
		});
		
		btnMaksamaton.setText("Maksamaton");
		combo.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				btnMaksamaton.setSelection(false);
				btnMaksettu.setSelection(false);

				varaus.otaTiedot(combo.getItem(combo.getSelectionIndex()).split(" ")[0]);
				System.out.println("SQL:"+varaus.getMstatus());
				status.setText(varaus.getMstatus());
				System.out.println(status.getText().equals("maksettu"));
				if(status.getText().equals("maksettu")) {
					System.out.println("status maksettu");
					btnMaksettu.setSelection(true);
					maksettu = true;
					
				}else {
					System.out.println("status maksamaton");
					maksettu = false;
					btnMaksamaton.setSelection(true);
					
				}
				
				
			}
		});
		Button btnKatso = new Button(this, SWT.NONE);
		btnKatso.setLayoutData(new GridData(SWT.CENTER, SWT.CENTER, false, false, 2, 1));
		btnKatso.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				Bill bill = new Bill();
				bill.CreatePDF(combo.getItem(combo.getSelectionIndex()).split(" ")[0], " ");
				
				if (Desktop.isDesktopSupported()) {
				    try {
				        File myFile = new File("//home//ovaaq//eclipse-workspace//MokkiVarausJarjestelma.zip_expanded//MokkiVarausJarjestelma//lasku.pdf");
				        Desktop.getDesktop().open(myFile);
				    } catch (IOException ex) {
				        // no application registered for PDFs
				    }
				}
			}
		});
		btnKatso.setText("Katso");
		
		Button btnTallenna = new Button(this, SWT.NONE);
		btnTallenna.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				System.out.println("valitsemassa");
				if(maksettu) {
					System.out.println("maksettu");
					
					varaus.MuutaStatus(combo.getItem(combo.getSelectionIndex()).split(" ")[0], "maksettu");
					
					
				} else {
					System.out.println("maksamaton");
					varaus.MuutaStatus(combo.getItem(combo.getSelectionIndex()).split(" ")[0], "maksamaton");
					
				}
				
				varaus.otaTiedot(combo.getItem(combo.getSelectionIndex()).split(" ")[0]);
				status.setText(varaus.getMstatus());
				 MessageDialog.openConfirm(Display.getDefault().getActiveShell(), "Tallennus", "Statuksen vaihtaminen onnistui");

			}
		});
		btnTallenna.setText("Tallenna");
		createContents();
	}

	/**
	 * Create contents of the shell.
	 */
	protected void createContents() {
		setText("Laskut");
		setSize(800, 600);

	}

	@Override
	protected void checkSubclass() {
		// Disable the check that prevents subclassing of SWT components
	}
}
