import org.eclipse.swt.widgets.Display;

import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.custom.CLabel;

import javax.swing.JOptionPane;

import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Label;
import org.eclipse.wb.swt.SWTResourceManager;
import org.eclipse.swt.widgets.Menu;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Text;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.widgets.MenuItem;
import org.eclipse.swt.events.ArmListener;
import org.eclipse.swt.events.ArmEvent;
import swing2swt.layout.BorderLayout;
import org.eclipse.swt.events.MenuDetectListener;
import org.eclipse.swt.events.MenuDetectEvent;
import org.eclipse.swt.custom.StackLayout;
import org.eclipse.swt.layout.FormLayout;
import org.eclipse.swt.layout.FormData;
import org.eclipse.swt.layout.FormAttachment;
import org.eclipse.swt.layout.FillLayout;
import org.eclipse.swt.layout.RowLayout;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.layout.GridData;



public class MainWindow {

	protected Shell shlMkkivarausjrjestelm;
	private Composite parent;
	private Composite Raportit;
	private Composite Laskut;
	private Composite TeeVaraus;
	private Text txtRaportit;
	private Text txtLaskut;
	private Text txtTeeVaraus;
	private Button btnAsiakkaat;
	private Button btnMökit;
	private Button btnPalvelut;
	private Button btnToimipisteet;
	private Text txtToimipisteet;
	private Text txtPalvelut;
	private Text txtMkit;
	private Text txtAsiakkaat;
	private Composite Toimipisteet;
	private Composite Palvelut;
	private Composite Mökit;
	private Composite Asiakkaat;

	/**
	 * Launch the application.
	 * @param args
	 */
	public static void main(String[] args) {
		try {
			MainWindow window = new MainWindow();
			window.open();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Open the window.
	 */
	public void open() {
		Display display = Display.getDefault();
		createContents();
		shlMkkivarausjrjestelm.open();
		shlMkkivarausjrjestelm.layout();
		while (!shlMkkivarausjrjestelm.isDisposed()) {
			if (!display.readAndDispatch()) {
				display.sleep();
			}
		}
	}

	/**
	 * Create contents of the window.
	 */
	protected void createContents() {
		shlMkkivarausjrjestelm = new Shell();
		shlMkkivarausjrjestelm.setBackground(SWTResourceManager.getColor(255, 255, 255));
		shlMkkivarausjrjestelm.setSize(865, 530);
		shlMkkivarausjrjestelm.setText("M\u00F6kkivarausj\u00E4rjestelm\u00E4");
		shlMkkivarausjrjestelm.setLayout(new GridLayout(2, false));
		
		Menu menu = new Menu(shlMkkivarausjrjestelm, SWT.BAR);
		shlMkkivarausjrjestelm.setMenuBar(menu);
		
		MenuItem mntmTeeVaraus = new MenuItem(menu, SWT.CASCADE);
		mntmTeeVaraus.setText("Tiedosto");
		
		Menu menu_1 = new Menu(mntmTeeVaraus);
		mntmTeeVaraus.setMenu(menu_1);
		
		MenuItem mntmSulje = new MenuItem(menu_1, SWT.NONE);
		mntmSulje.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				int ret = JOptionPane.showConfirmDialog(null, "Haluatko varmasti sulkea ohjelman?");
				if (ret == JOptionPane.YES_OPTION)
				System.exit(0);
			}
		});
		mntmSulje.setText("Sulje");
		
		Composite composite = new Composite(shlMkkivarausjrjestelm, SWT.NONE);
		composite.setBackground(SWTResourceManager.getColor(65, 105, 225));
		GridData gd_composite = new GridData(SWT.LEFT, SWT.CENTER, false, false, 1, 1);
		gd_composite.heightHint = 435;
		composite.setLayoutData(gd_composite);
		RowLayout rl_composite = new RowLayout(SWT.VERTICAL);
		rl_composite.fill = true;
		rl_composite.center = true;
		composite.setLayout(rl_composite);
		
		//Painike
		Button btnTeeVaraus = new Button(composite, SWT.NONE);
		btnTeeVaraus.setFont(SWTResourceManager.getFont("Segoe UI", 9, SWT.BOLD));
		btnTeeVaraus.setForeground(SWTResourceManager.getColor(65, 105, 225));
		btnTeeVaraus.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				showTeeVaraus();
			}
		});
		btnTeeVaraus.setText("Tee varaus");
		
		//Painike
		Button btnAsiakkaat = new Button(composite, SWT.NONE);
		btnAsiakkaat.setFont(SWTResourceManager.getFont("Segoe UI", 9, SWT.BOLD));
		btnAsiakkaat.setForeground(SWTResourceManager.getColor(65, 105, 225));
		btnAsiakkaat.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				showAsiakkaat();
			}
		});
		btnAsiakkaat.setText("Asiakkaat");
		
		//Painike
		Button btnMökit = new Button(composite, SWT.NONE);
		btnMökit.setFont(SWTResourceManager.getFont("Segoe UI", 9, SWT.BOLD));
		btnMökit.setForeground(SWTResourceManager.getColor(65, 105, 225));
		btnMökit.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				showMökit();
			}
		});
		btnMökit.setText("M\u00F6kit");
		
		//Painike
		Button btnPalvelut = new Button(composite, SWT.NONE);
		btnPalvelut.setFont(SWTResourceManager.getFont("Segoe UI", 9, SWT.BOLD));
		btnPalvelut.setForeground(SWTResourceManager.getColor(65, 105, 225));
		btnPalvelut.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				showPalvelut();
			}
		});
		btnPalvelut.setText("Palvelut");
		
		Button btnToimipisteet = new Button(composite, SWT.NONE);
		btnToimipisteet.setFont(SWTResourceManager.getFont("Segoe UI", 9, SWT.BOLD));
		btnToimipisteet.setForeground(SWTResourceManager.getColor(65, 105, 225));
		btnToimipisteet.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				showToimipisteet();
			}
		});
		btnToimipisteet.setText("Toimipisteet");
		
		//Painike
		Button btnLaskut = new Button(composite, SWT.NONE);
		btnLaskut.setFont(SWTResourceManager.getFont("Segoe UI", 9, SWT.BOLD));
		btnLaskut.setForeground(SWTResourceManager.getColor(65, 105, 225));
		btnLaskut.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				showLaskut();
			}
		});
		btnLaskut.setText("Laskut");
		
		//Painike
		Button btnRaportit = new Button(composite, SWT.NONE);
		btnRaportit.setFont(SWTResourceManager.getFont("Segoe UI", 9, SWT.BOLD));
		btnRaportit.setForeground(SWTResourceManager.getColor(65, 105, 225));
		btnRaportit.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				showRaportit();
			}
		});
		btnRaportit.setText("Raportit");
		
		parent = new Composite(shlMkkivarausjrjestelm, SWT.NONE);
		parent.setBackground(SWTResourceManager.getColor(SWT.COLOR_WIDGET_LIGHT_SHADOW));
		parent.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, false, 1, 1));
		StackLayout sl_parent = new StackLayout();
		parent.setLayout(sl_parent);
		
		Raportit = new Composite(parent, SWT.NONE);
		Raportit.setBackground(SWTResourceManager.getColor(SWT.COLOR_WIDGET_LIGHT_SHADOW));
		Raportit.setLayout(null);
		
		txtRaportit = new Text(Raportit, SWT.CENTER);
		txtRaportit.setForeground(SWTResourceManager.getColor(65, 105, 225));
		txtRaportit.setFont(SWTResourceManager.getFont("Segoe UI", 16, SWT.BOLD));
		txtRaportit.setBackground(SWTResourceManager.getColor(SWT.COLOR_TRANSPARENT));
		txtRaportit.setBounds(10, 10, 686, 64);
		txtRaportit.setText("Raportit");
		
		Composite composite_1 = new Composite(Raportit, SWT.NONE);
		composite_1.setBounds(293, 80, 130, 130);
		
		Button btnRaportti = new Button(composite_1, SWT.NONE);
		btnRaportti.setBounds(0, 0, 130, 130);
		btnRaportti.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				openMajoitusRaportti();
			}
		});
		btnRaportti.setText("Luo Raportti");
		
		Laskut = new Composite(parent, SWT.NONE);
		Laskut.setBackground(SWTResourceManager.getColor(SWT.COLOR_WIDGET_LIGHT_SHADOW));
		
		txtLaskut = new Text(Laskut, SWT.CENTER);
		txtLaskut.setFont(SWTResourceManager.getFont("Segoe UI", 16, SWT.BOLD));
		txtLaskut.setForeground(SWTResourceManager.getColor(65, 105, 225));
		txtLaskut.setBackground(SWTResourceManager.getColor(SWT.COLOR_TRANSPARENT));
		txtLaskut.setText("Laskut");
		txtLaskut.setBounds(10, 10, 686, 45);
		
		Composite composite_13 = new Composite(Laskut, SWT.NONE);
		composite_13.setBounds(218, 61, 130, 130);
		
		Button btnListaaLaskut = new Button(composite_13, SWT.CENTER);
		btnListaaLaskut.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				openLaskutListaa();
			}
		});
		btnListaaLaskut.setBounds(0, 0, 130, 130);
		btnListaaLaskut.setText("Laskun seuranta");
		
		Composite composite_12 = new Composite(Laskut, SWT.NONE);
		composite_12.setBounds(354, 61, 130, 130);
		
		Button btnPoistaLaskuja = new Button(composite_12, SWT.NONE);
		btnPoistaLaskuja.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				openLaskutPoista();
			}
		});
		btnPoistaLaskuja.setBounds(0, 0, 130, 130);
		btnPoistaLaskuja.setText("Laskun l\u00E4hetys");
		
		TeeVaraus = new Composite(parent, SWT.NONE);
		TeeVaraus.setBackground(SWTResourceManager.getColor(SWT.COLOR_WIDGET_LIGHT_SHADOW));
		
		txtTeeVaraus = new Text(TeeVaraus, SWT.CENTER);
		txtTeeVaraus.setForeground(SWTResourceManager.getColor(65, 105, 225));
		txtTeeVaraus.setFont(SWTResourceManager.getFont("Segoe UI", 16, SWT.BOLD));
		txtTeeVaraus.setBackground(SWTResourceManager.getColor(SWT.COLOR_TRANSPARENT));
		txtTeeVaraus.setText("Tee varaus");
		txtTeeVaraus.setBounds(10, 10, 686, 52);
		
		Composite composite_17 = new Composite(TeeVaraus, SWT.NONE);
		composite_17.setBounds(217, 68, 130, 130);
		
		Button btnVarausLuo = new Button(composite_17, SWT.NONE);
		btnVarausLuo.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				openVarausLuo();
			}
		});
		btnVarausLuo.setBounds(0, 0, 130, 130);
		btnVarausLuo.setText("Luo varaus");
		
		Composite composite_18 = new Composite(TeeVaraus, SWT.NONE);
		composite_18.setBounds(353, 68, 130, 130);
		
		Button btnPoistaVaraus = new Button(composite_18, SWT.NONE);
		btnPoistaVaraus.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				openVarausPoista();
			}
		});
		btnPoistaVaraus.setBounds(0, 0, 130, 130);
		btnPoistaVaraus.setText("Poista varaus");
		
		Asiakkaat = new Composite(parent, SWT.NONE);
		Asiakkaat.setBackground(SWTResourceManager.getColor(SWT.COLOR_WIDGET_LIGHT_SHADOW));
		
		txtAsiakkaat = new Text(Asiakkaat, SWT.CENTER);
		txtAsiakkaat.setForeground(SWTResourceManager.getColor(65, 105, 225));
		txtAsiakkaat.setFont(SWTResourceManager.getFont("Segoe UI", 16, SWT.BOLD));
		txtAsiakkaat.setBackground(SWTResourceManager.getColor(SWT.COLOR_TRANSPARENT));
		txtAsiakkaat.setText("Asiakkaat");
		txtAsiakkaat.setBounds(10, 10, 686, 45);
		
		Composite composite_14 = new Composite(Asiakkaat, SWT.NONE);
		composite_14.setBounds(128, 63, 130, 130);
		
		Button btnAsiakasLisää = new Button(composite_14, SWT.NONE);
		btnAsiakasLisää.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				openAsiakasLisaa();
			}
		});
		btnAsiakasLisää.setBounds(0, 0, 130, 130);
		btnAsiakasLisää.setText("Lis\u00E4\u00E4");
		
		Composite composite_15 = new Composite(Asiakkaat, SWT.NONE);
		composite_15.setBounds(264, 63, 130, 130);
		
		Button btnAsiakasMuokkaa = new Button(composite_15, SWT.NONE);
		btnAsiakasMuokkaa.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				openAsiakasMuokkaa();
			}
		});
		btnAsiakasMuokkaa.setBounds(0, 0, 130, 130);
		btnAsiakasMuokkaa.setText("Muokkaa");
		
		Composite composite_16 = new Composite(Asiakkaat, SWT.NONE);
		composite_16.setBounds(400, 61, 130, 130);
		
		Button btnAsiakasPoista = new Button(composite_16, SWT.NONE);
		btnAsiakasPoista.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				openAsiakasPoista();
			}
		});
		btnAsiakasPoista.setBounds(0, 0, 130, 130);
		btnAsiakasPoista.setText("Poista");
		
		Mökit = new Composite(parent, SWT.NONE);
		Mökit.setBackground(SWTResourceManager.getColor(SWT.COLOR_WIDGET_LIGHT_SHADOW));
		
		txtMkit = new Text(Mökit, SWT.CENTER);
		txtMkit.setForeground(SWTResourceManager.getColor(65, 105, 225));
		txtMkit.setFont(SWTResourceManager.getFont("Segoe UI", 16, SWT.BOLD));
		txtMkit.setBackground(SWTResourceManager.getColor(SWT.COLOR_TRANSPARENT));
		txtMkit.setText("M\u00F6kit");
		txtMkit.setBounds(10, 10, 686, 45);
		
		Composite composite_3 = new Composite(Mökit, SWT.NONE);
		composite_3.setBounds(150, 61, 129, 129);
		
		Button btnMökkiLisää = new Button(composite_3, SWT.NONE);
		btnMökkiLisää.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				openMökkiLisää();
			}
		});
		btnMökkiLisää.setBounds(0, 0, 129, 129);
		btnMökkiLisää.setText("Lis\u00E4\u00E4");
		
		Composite composite_4 = new Composite(Mökit, SWT.NONE);
		composite_4.setBounds(285, 61, 129, 129);
		
		Button btnMökkiMuokkaa = new Button(composite_4, SWT.NONE);
		btnMökkiMuokkaa.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				openMökkiMuokkaa();
				
			}
		});
		btnMökkiMuokkaa.setBounds(0, 0, 129, 129);
		btnMökkiMuokkaa.setText("Muokkaa");
		
		Composite composite_5 = new Composite(Mökit, SWT.NONE);
		composite_5.setBounds(420, 61, 129, 129);
		
		Button btnMökkiPoista = new Button(composite_5, SWT.NONE);
		btnMökkiPoista.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				openMökkiPoista();
			}
		});
		btnMökkiPoista.setBounds(0, 0, 129, 129);
		btnMökkiPoista.setText("Poista");
		
		Palvelut = new Composite(parent, SWT.NONE);
		Palvelut.setBackground(SWTResourceManager.getColor(SWT.COLOR_WIDGET_LIGHT_SHADOW));
		
		txtPalvelut = new Text(Palvelut, SWT.CENTER);
		txtPalvelut.setForeground(SWTResourceManager.getColor(65, 105, 225));
		txtPalvelut.setFont(SWTResourceManager.getFont("Segoe UI", 16, SWT.BOLD));
		txtPalvelut.setBackground(SWTResourceManager.getColor(SWT.COLOR_TRANSPARENT));
		txtPalvelut.setText("Palvelut");
		txtPalvelut.setBounds(10, 10, 686, 51);
		
		Composite composite_9 = new Composite(Palvelut, SWT.NONE);
		composite_9.setBounds(158, 67, 130, 130);
		
		Button btnPalveluLisää = new Button(composite_9, SWT.NONE);
		btnPalveluLisää.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				openPalveluLisää();
			}
		});
		btnPalveluLisää.setBounds(0, 0, 130, 130);
		btnPalveluLisää.setText("Lis\u00E4\u00E4");
		
		Composite composite_10 = new Composite(Palvelut, SWT.NONE);
		composite_10.setBounds(294, 67, 130, 130);
		
		Button btnPalveluMuokkaa = new Button(composite_10, SWT.NONE);
		btnPalveluMuokkaa.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				openPalveluMuokkaa();
			}
		});
		btnPalveluMuokkaa.setBounds(0, 0, 130, 130);
		btnPalveluMuokkaa.setText("Muokkaa");
		
		Composite composite_11 = new Composite(Palvelut, SWT.NONE);
		composite_11.setBounds(430, 67, 130, 130);
		
		Button btnPalveluPoista = new Button(composite_11, SWT.NONE);
		btnPalveluPoista.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				openPalveluPoista();
			}
		});
		btnPalveluPoista.setBounds(0, 0, 130, 130);
		btnPalveluPoista.setText("Poista");
		
		Toimipisteet = new Composite(parent, SWT.NONE);
		Toimipisteet.setBackground(SWTResourceManager.getColor(SWT.COLOR_WIDGET_LIGHT_SHADOW));
		
		txtToimipisteet = new Text(Toimipisteet, SWT.CENTER);
		txtToimipisteet.setForeground(SWTResourceManager.getColor(65, 105, 225));
		txtToimipisteet.setFont(SWTResourceManager.getFont("Segoe UI", 16, SWT.BOLD));
		txtToimipisteet.setBackground(SWTResourceManager.getColor(SWT.COLOR_TRANSPARENT));
		txtToimipisteet.setText("Toimipisteet");
		txtToimipisteet.setBounds(10, 10, 686, 52);
		
		Composite composite_6 = new Composite(Toimipisteet, SWT.NONE);
		composite_6.setBounds(148, 68, 130, 130);
		
		Button btnToimipisteLisää = new Button(composite_6, SWT.NONE);
		btnToimipisteLisää.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				openToimipisteLisää();
			}
		});
		btnToimipisteLisää.setBounds(0, 0, 130, 130);
		btnToimipisteLisää.setText("Lis\u00E4\u00E4");
		
		Composite composite_7 = new Composite(Toimipisteet, SWT.NONE);
		composite_7.setBounds(284, 68, 130, 130);
		
		Button btnToimipisteMuokkaa = new Button(composite_7, SWT.NONE);
		btnToimipisteMuokkaa.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				openToimipisteMuokkaa();
			}
		});
		btnToimipisteMuokkaa.setBounds(0, 0, 130, 130);
		btnToimipisteMuokkaa.setText("Muokkaa");
		
		Composite composite_8 = new Composite(Toimipisteet, SWT.NONE);
		composite_8.setBounds(420, 68, 130, 130);
		
		Button btnToimipistePoista = new Button(composite_8, SWT.NONE);
		btnToimipistePoista.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				openToimipistePoista();
			}
		});
		btnToimipistePoista.setBounds(0, 0, 130, 130);
		btnToimipistePoista.setText("Poista");

	}
	
	//metodit valikon välilehtien vaihtamiselle.
	private void showRaportit() {
		StackLayout layout = (StackLayout) this.parent.getLayout();
		layout.topControl = this.Raportit;
		this.parent.layout();
	}
	private void showLaskut() {
		StackLayout layout = (StackLayout) this.parent.getLayout();
		layout.topControl = this.Laskut;
		this.parent.layout();
	}
	private void showTeeVaraus() {
		StackLayout layout = (StackLayout) this.parent.getLayout();
		layout.topControl = this.TeeVaraus;
		this.parent.layout();
	}
	private void showAsiakkaat() {
		StackLayout layout = (StackLayout) this.parent.getLayout();
		layout.topControl = this.Asiakkaat;
		this.parent.layout();
		
	}
	private void showMökit() {
		StackLayout layout = (StackLayout) this.parent.getLayout();
		layout.topControl = this.Mökit;
		this.parent.layout();
		
	}
	private void showPalvelut() {
		StackLayout layout = (StackLayout) this.parent.getLayout();
		layout.topControl = this.Palvelut;
		this.parent.layout();
		
	}
	private void showToimipisteet(){
		StackLayout layout = (StackLayout) this.parent.getLayout();
		layout.topControl = this.Toimipisteet;
		this.parent.layout();
		
	}
	
	//metodit toimipisteiden hallinnan ikkunoiden siirtymiin
	private void openToimipisteLisää() {
		ToimipisteLisaa.main(null);
	}	
	
	private void openToimipisteMuokkaa() {
		ToimipisteMuokkaa.main(null);	
	}
	private void openToimipistePoista() {
		ToimipistePoista.main(null);	
	}
	
	//metodit mökkien hallinnan ikkunoiden siirtymiin
	private void openMökkiLisää() {
		MokkiLisaa.main(null);
	}
	private void openMökkiMuokkaa() {
		MokkiMuokkaa.main(null);
	}
	private void openMökkiPoista() {
		MokkiPoista.main(null);
	}
	
	//metodit palveluiden hallinnan ikkunoiden siirtymiin
	private void openPalveluLisää() {
		PalveluLisaa.main(null);
	}
	private void openPalveluMuokkaa() {
		PalveluMuokkaa.main(null);
	}
	private void openPalveluPoista() {
		PalveluPoista.main(null);
	}
	
	//metodit asiakkaiden hallinnan ikkunoiden siirtymiin
	private void openAsiakasLisaa() {
		AsiakasLisaa.main(null);
	}
	private void openAsiakasMuokkaa() {
		AsiakasMuokkaa.main(null);
	}
	private void openAsiakasPoista() {
		AsiakasPoista.main(null);
	}
	//metodit varauksen hallinnan ikkunoiden siirtymiin
	private void openVarausLuo() {
		VarausLuo.main(null);
	}
	private void openVarausPoista() {
		VarausPoista.main(null);
	}
	//metodit laskujen hallinnan ikkunoiden siirtymiin
	private void openLaskutListaa() {
		LaskutListaa.main(null);
	}
	private void openLaskutPoista() {
		LaskutPoista.main(null);
	}
	//metodit raporttien ikkunoiden siirtymiin
	private void openMajoitusRaportti() {
		MajoitusRaportti.main(null);
	}
	
}

