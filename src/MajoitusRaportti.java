import java.awt.Desktop;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

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
import org.eclipse.swt.widgets.DateTime;
import org.eclipse.wb.swt.SWTResourceManager;
import org.eclipse.swt.widgets.List;
import org.eclipse.swt.layout.GridData;

public class MajoitusRaportti extends Shell {

	Boolean OnkoPalvelu = null;
	
	/**
	 * Launch the application.
	 * @param args
	 */
	public static void main(String args[]) {
		try {
			Display display = Display.getDefault();
			MajoitusRaportti shell = new MajoitusRaportti(display);
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
	public MajoitusRaportti(Display display) {
		super(display, SWT.SHELL_TRIM);
		setLayout(new GridLayout(2, false));
		
		Composite composite = new Composite(this, SWT.NONE);
		composite.setLayout(new GridLayout(1, false));
		
		Label lblMajoitusraportinLuonti = new Label(composite, SWT.NONE);
		lblMajoitusraportinLuonti.setFont(SWTResourceManager.getFont("Segoe UI", 12, SWT.NORMAL));
		lblMajoitusraportinLuonti.setBounds(0, 0, 81, 25);
		lblMajoitusraportinLuonti.setText("Raportin luonti");
		new Label(this, SWT.NONE);
		
		Label lblMiltVliltHaluat = new Label(this, SWT.NONE);
		lblMiltVliltHaluat.setText("Milt\u00E4 v\u00E4lilt\u00E4 haluat raportin?");
		new Label(this, SWT.NONE);
		
		Label lblAlkupivmr = new Label(this, SWT.NONE);
		lblAlkupivmr.setText("Alkup\u00E4iv\u00E4m\u00E4\u00E4r\u00E4");
		
		Label lblLoppupivmr = new Label(this, SWT.NONE);
		lblLoppupivmr.setText("Loppup\u00E4iv\u00E4m\u00E4\u00E4r\u00E4");
		
		DateTime dateTime = new DateTime(this, SWT.BORDER | SWT.CALENDAR);
		dateTime.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
			}
		});
		
		DateTime dateTime_1 = new DateTime(this, SWT.BORDER | SWT.CALENDAR);
		dateTime_1.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
			}
		});
		
		Composite composite_1 = new Composite(this, SWT.NONE);
		GridData gd_composite_1 = new GridData(SWT.FILL, SWT.CENTER, false, false, 2, 1);
		gd_composite_1.heightHint = 151;
		composite_1.setLayoutData(gd_composite_1);
		composite_1.setLayout(new GridLayout(2, false));
		
		Label lblToimipisteMajoitusraportti = new Label(composite_1, SWT.NONE);
		lblToimipisteMajoitusraportti.setBounds(0, 0, 81, 25);
		lblToimipisteMajoitusraportti.setText("Toimipisteet");
		new Label(composite_1, SWT.NONE);
		
		Toimipiste toimipiste = new Toimipiste();
		Button[] napit = new Button[toimipiste.getLista().size()];
		for(int i = 0; i<toimipiste.getLista().size(); i++) {
		      
			napit[i] = new Button(composite_1, SWT.CHECK);
			napit[i].addSelectionListener(new SelectionAdapter() {
				@Override
				public void widgetSelected(SelectionEvent e) {
				}	});
			napit[i].setText(toimipiste.getLista().get(i).toString());
		      }
		
		
		new Label(composite_1, SWT.NONE);
		new Label(composite_1, SWT.NONE);
		
		Label lblMist = new Label(composite_1, SWT.NONE);
		lblMist.setText("Mist\u00E4");
		new Label(composite_1, SWT.NONE);
		
		
		
		Button btnPalvelu = new Button(composite_1, SWT.RADIO);
		btnPalvelu.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				
				OnkoPalvelu = true;
				
			}
		});
		btnPalvelu.setText("Palvelut");
		
		Button btnMajoitus = new Button(composite_1, SWT.RADIO);
		btnMajoitus.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				OnkoPalvelu = false;
			}
		});
		btnMajoitus.setText("Majoitus");
		//t�h�n sis�lt�
		
		Composite composite_2 = new Composite(this, SWT.NONE);
		composite_2.setLayout(new GridLayout(1, false));
		
		
		Button btnLuoMajoitusraportti = new Button(composite_2, SWT.NONE);
		btnLuoMajoitusraportti.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				ArrayList toimipisteet = new ArrayList();
				for(int i = 0; i< napit.length; i++) {
					if(napit[i].getSelection()) {
						toimipisteet.add(napit[i].getText().split(" ")[0]);
					}
				}
				System.out.println(toimipisteet);
				if(OnkoPalvelu = true) {
			        File myFile = new File("//home//ovaaq//eclipse-workspace//MokkiVarausJarjestelma.zip_expanded//MokkiVarausJarjestelma//raportti_palvelut.pdf");
					myFile.delete();
					Raportti raportti1 = new Raportti();
					raportti1.CreatePdfReportService((dateTime.getYear()+ "-"+ dateTime.getMonth()+"-"+dateTime.getDay()),(dateTime_1.getYear()+ "-"+ dateTime_1.getMonth()+"-"+dateTime_1.getDay()), toimipisteet );
					if (Desktop.isDesktopSupported()) {
					    try {
					        File myFile2 = new File("//home//ovaaq//eclipse-workspace//MokkiVarausJarjestelma.zip_expanded//MokkiVarausJarjestelma//raportti_palvelut.pdf");
					        Desktop.getDesktop().open(myFile2);
					    } catch (IOException ex) {
					        // no application registered for PDFs
					    }
					}
				} else if (OnkoPalvelu = false) {
					
			        File myFile3 = new File("//home//ovaaq//eclipse-workspace//MokkiVarausJarjestelma.zip_expanded//MokkiVarausJarjestelma//raportti_majoitus.pdf");
				    myFile3.delete();
					Raportti raportti2 = new Raportti();
					raportti2.CreatePdfReportCottage((dateTime.getYear()+ "-"+ dateTime.getMonth()+"-"+dateTime.getDay()),(dateTime_1.getYear()+ "-"+ dateTime_1.getMonth()+"-"+dateTime_1.getDay()), toimipisteet);
					if (Desktop.isDesktopSupported()) {
					    try {
					        File myFile4 = new File("//home//ovaaq//eclipse-workspace//MokkiVarausJarjestelma.zip_expanded//MokkiVarausJarjestelma//raportti_palvelut.pdf");
					        Desktop.getDesktop().open(myFile4);
					    } catch (IOException ex) {
					        // no application registered for PDFs
					    }
					}
				}
				
			}
		});
		btnLuoMajoitusraportti.setBounds(0, 0, 105, 35);
		btnLuoMajoitusraportti.setText("Luo raportti");
		new Label(this, SWT.NONE);
		createContents();
	}

	/**
	 * Create contents of the shell.
	 */
	protected void createContents() {
		setText("Raportti");
		setSize(800, 700);

	}

	@Override
	protected void checkSubclass() {
		// Disable the check that prevents subclassing of SWT components
	}

}
