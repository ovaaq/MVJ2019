import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Shell;

import com.itextpdf.text.List;

import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Monitor;
import org.eclipse.swt.widgets.DateTime;
import org.eclipse.swt.widgets.Combo;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.custom.CCombo;
import org.eclipse.swt.widgets.ToolBar;
import org.eclipse.swt.widgets.ToolItem;
import org.eclipse.swt.widgets.CoolBar;
import org.eclipse.swt.widgets.CoolItem;
import org.eclipse.swt.widgets.Table;
import org.eclipse.swt.widgets.TableColumn;
import org.eclipse.swt.widgets.Text;
import org.eclipse.swt.widgets.Tree;
import org.eclipse.jface.viewers.TreeViewer;
import org.eclipse.jface.dialogs.MessageDialog;
import org.eclipse.jface.viewers.CheckboxTableViewer;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.*;

public class VarausLuo extends Shell {

  Asiakas asiakas = new Asiakas();
  Toimipiste toimipiste = new Toimipiste();
  PalveluJaMokki mokki = new PalveluJaMokki();
  PalveluJaMokki palvelu = new PalveluJaMokki();
  Varaus varaus = new Varaus();
  private Text viivkorko;
  private Text viite;
  private Text laskunpvm;
  private Text laskuera;
  private Text huomaika;
  private Text maksuehto;
  private Text varauspvm;
  private Boolean OnkoPalvelu = false;
  private Boolean OnkoMokki = false;
  
  /* Launch the application.
   * @param args
   */
  public static void main(String args[]) {
    try {
      Display display = Display.getDefault();
      VarausLuo shell = new VarausLuo(display);
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

  
  /* Create the shell.
   * @param display
   */
  public VarausLuo(Display display) {
    super(display, SWT.SHELL_TRIM);
    setLayout(new GridLayout(1, false));
    
    Composite composite = new Composite(this, SWT.NONE);
    composite.setBounds(0, 0, 64, 64);
    composite.setLayout(new GridLayout(1, false));
    
    Label lblNewLabel = new Label(composite, SWT.NONE);
    lblNewLabel.setText("Uuden varauksen luominen");
    
    Composite composite_1 = new Composite(this, SWT.NONE);
    GridData gd_composite_1 = new GridData(SWT.LEFT, SWT.CENTER, false, false, 1, 1);
    gd_composite_1.widthHint = 853;
    composite_1.setLayoutData(gd_composite_1);
    composite_1.setBounds(0, 0, 64, 64);
    composite_1.setLayout(new GridLayout(4, false));
    
    Label lblValitseAsiakas = new Label(composite_1, SWT.NONE);
    lblValitseAsiakas.setSize(59, 25);
    lblValitseAsiakas.setText("Asiakas");
    
    Label lblToimipiste = new Label(composite_1, SWT.NONE);
    lblToimipiste.setText("Toimipiste");
    
    Label lblMkki = new Label(composite_1, SWT.NONE);
    lblMkki.setText("M\u00F6kki");
    
    Label lblPalvelu = new Label(composite_1, SWT.NONE);
    lblPalvelu.setText("Palvelu");
    
    Combo combo = new Combo(composite_1, SWT.READ_ONLY);
    combo.addSelectionListener(new SelectionAdapter() {
      @Override
      public void widgetSelected(SelectionEvent e) {
        asiakas.otaTiedot(combo.getItem(combo.getSelectionIndex()).split(" ")[0]);
      }
    });
    GridData gd_combo = new GridData(SWT.LEFT, SWT.CENTER, true, false, 1, 1);
    gd_combo.widthHint = 150;
    combo.setLayoutData(gd_combo);
    for(int i = 0; i<asiakas.getLista().size(); i++) {
      combo.add(asiakas.getLista().get(i).toString());
      }
    
    
    Combo combo_1 = new Combo(composite_1, SWT.READ_ONLY);
    Combo combo_2 = new Combo(composite_1, SWT.READ_ONLY);
    Combo combo_3 = new Combo(composite_1, SWT.READ_ONLY);
    
    combo_1.addSelectionListener(new SelectionAdapter() {
      @Override
      public void widgetSelected(SelectionEvent e) {
toimipiste.otaTiedot(combo_1.getItem(combo_1.getSelectionIndex()).split(" ")[0]);

        viite.setText(asiakas.getM_asiakas_ID() + toimipiste.getM_toimipiste_ID() + "42");
        combo_2.removeAll();
        for(int i = 0; i<mokki.getListaTP("mokki", toimipiste.getM_toimipiste_ID()).size(); i++) {
          combo_2.add(mokki.getListaTP("mokki", toimipiste.getM_toimipiste_ID()).get(i).toString());
          
          }
        combo_3.removeAll();
        for(int i = 0; i<palvelu.getListaTP("palvelu", toimipiste.getM_toimipiste_ID()).size(); i++) {
          combo_3.add(palvelu.getListaTP("palvelu", toimipiste.getM_toimipiste_ID()).get(i).toString());
          
          }
      }
    });
  
  
    GridData gd_combo_1 = new GridData(SWT.LEFT, SWT.CENTER, true, false, 1, 1);
    gd_combo_1.widthHint = 165;
    combo_1.setLayoutData(gd_combo_1);
    combo_2.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false, 1, 1));
    combo_3.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false, 1, 1));
    
    Label lblHuomautusaika = new Label(composite_1, SWT.NONE);
    lblHuomautusaika.setText("Huomautusaika");
    //sisältö
    for(int i = 0; i<toimipiste.getLista().size(); i++) {
      combo_1.add(toimipiste.getLista().get(i).toString());
      }
    
    Label lblNewLabel_2 = new Label(composite_1, SWT.NONE);
    lblNewLabel_2.setText("Laskunpvm");
    
    Label lblViivstyskorko = new Label(composite_1, SWT.NONE);
    lblViivstyskorko.setText("Viiv\u00E4styskorko");
    
    Label lblNewLabel_4 = new Label(composite_1, SWT.NONE);
    lblNewLabel_4.setText("Maksuehto");
    
    huomaika = new Text(composite_1, SWT.BORDER);
    huomaika.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false, 1, 1));
    
    laskunpvm = new Text(composite_1, SWT.BORDER);
    laskunpvm.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false, 1, 1));
    
    viivkorko = new Text(composite_1, SWT.BORDER);
    viivkorko.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false, 1, 1));
    
    maksuehto = new Text(composite_1, SWT.BORDER);
    maksuehto.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false, 1, 1));
    new Label(composite_1, SWT.NONE);
    
    Label lblNewLabel_3 = new Label(composite_1, SWT.NONE);
    lblNewLabel_3.setText("Laskun er\u00E4p\u00E4iv\u00E4");
    
    Label lblNewLabel_1 = new Label(composite_1, SWT.NONE);
    lblNewLabel_1.setText("Viitenumero");
    
    Label lblVarauspv = new Label(composite_1, SWT.NONE);
    lblVarauspv.setText("Varauspv");
    
    Label lblValitsePvm = new Label(composite_1, SWT.CENTER);
    lblValitsePvm.setSize(91, 25);
    lblValitsePvm.setText("Valitse pvm");
    
    laskuera = new Text(composite_1, SWT.BORDER);
    laskuera.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false, 1, 1));
    
    viite = new Text(composite_1, SWT.BORDER);
    viite.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false, 1, 1));
    
    varauspvm = new Text(composite_1, SWT.BORDER);
    varauspvm.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false, 1, 1));
    
    
    DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy-MM-dd");
	LocalDate localDate = LocalDate.now();
	
	
    
    
    viivkorko.setText("8.0");
    maksuehto.setText("14 pv");
    huomaika.setText("14 pv");
    laskunpvm.setText(dtf.format(localDate));
    laskuera.setText(dtf.format(localDate.plusDays(30)));
    varauspvm.setText(dtf.format(localDate));
    
    DateTime dateTime = new DateTime(composite_1, SWT.BORDER | SWT.CALENDAR);
    dateTime.addSelectionListener(new SelectionAdapter() {
      @Override
      public void widgetSelected(SelectionEvent e) {
      }
    });
    dateTime.setSize(119, 33);
    
    DateTime dateTime_1 = new DateTime(composite_1, SWT.BORDER | SWT.CALENDAR);
    dateTime_1.addSelectionListener(new SelectionAdapter() {
      @Override
      public void widgetSelected(SelectionEvent e) {
      }
    });
    dateTime_1.setSize(119, 33);
    new Label(composite_1, SWT.NONE);
    new Label(composite_1, SWT.NONE);
    
    Composite composite_2 = new Composite(this, SWT.NONE);
    composite_2.setLayout(new GridLayout(1, false));
    
    Button btnNewButton = new Button(composite_2, SWT.NONE);
    btnNewButton.addSelectionListener(new SelectionAdapter() {
      @Override
      public void widgetSelected(SelectionEvent e) {
        
        ArrayList lista = new ArrayList();
        if (!(combo_2.getSelectionIndex()==-1))  {
        	lista.add(combo_2.getItem(combo_2.getSelectionIndex()).split(" ")[0]);
        	
        }
        if (!(combo_3.getSelectionIndex()==-1)) {
        	lista.add(combo_3.getItem(combo_3.getSelectionIndex()).split(" ")[0]);
        	
        }
        varaus.lisaa(laskunpvm.getText(), laskuera.getText(), viite.getText(), viivkorko.getText(), huomaika.getText(), maksuehto.getText(), varauspvm.getText(), (dateTime.getYear()+ "-"+ dateTime.getMonth()+"-"+dateTime.getDay()), (dateTime_1.getYear()+ "-"+ dateTime_1.getMonth()+"-"+dateTime_1.getDay()), combo.getItem(combo.getSelectionIndex()).split(" ")[0], combo_1.getItem(combo_1.getSelectionIndex()).split(" ")[0], lista);
        MessageDialog.openConfirm(Display.getDefault().getActiveShell(), "Lisäys", "Varauksen luominen onnistui");
       
        viite.setText("");
        viivkorko.setText("8.0");
        maksuehto.setText("14 pv");
        huomaika.setText("14 pv");
        laskunpvm.setText(dtf.format(localDate));
        laskuera.setText(dtf.format(localDate.plusDays(30)));
        varauspvm.setText(dtf.format(localDate));
      }
    });
    btnNewButton.setSize(134, 35);
    btnNewButton.setText("Luo uusi varaus");
    createContents();
  }


  /**
   * Create contents of the shell.
   */
  protected void createContents() {
    setText("Luo uusi varaus");
    setSize(893, 600);

  }

  @Override
  protected void checkSubclass() {
    // Disable the check that prevents subclassing of SWT components
  }
}