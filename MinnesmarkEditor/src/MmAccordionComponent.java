
import java.awt.*;


import java.awt.event.*;
import javax.swing.*;
// Import the Java classes
import java.util.*;





/**
 * A JOutlookBar provides a component that is similar to a JTabbedPane, but instead of maintaining
 * tabs, it uses Outlook-style bars to control the visible component
 */
public class MmAccordionComponent extends JPanel implements ActionListener
{
  /**
	 * 
	 */
	private static final long serialVersionUID = 1L;

/**
   * The top panel: contains the buttons displayed on the top of the JOutlookBar
   */
  private JPanel topPanel = new JPanel();
 

  /**
   * The bottom panel: contains the buttons displayed on the bottom of the JOutlookBar
   */
  //private JPanel bottomPanel = new JPanel( new GridLayout( 1, 1 ) );

  /**
   * A LinkedHashMap of bars: we use a linked hash map to preserve the order of the bars
   */
  private Map bars = new LinkedHashMap();

  /**
   * The currently visible bar (zero-based index)
   */
  private int visibleBar = 0;

  /**
   * A place-holder for the currently visible component
   */
  private JComponent visibleComponent = null;
  
  public static int width,height;
  

  /**
   * Creates a new JOutlookBar; after which you should make repeated calls to
   * addBar() for each bar
   */
  public MmAccordionComponent(int width,int height)
  {
	  
	    
    this.setLayout( new BorderLayout() );
	//this.topPanel.setLayout(new GridBagLayout());
    //this.topPanel.setAlignmentX(Component.TOP_ALIGNMENT);
    this.add( topPanel, BorderLayout.NORTH );
    //this.add( bottomPanel, BorderLayout.SOUTH );
    MmAccordionComponent.width=width;
    MmAccordionComponent.height=height;
  }
  
  public MmAccordionComponent()
  {
    this.setLayout( new BorderLayout() );
    //this.topPanel.setAlignmentX(Component.TOP_ALIGNMENT);
    //this.add( topPanel, BorderLayout.NORTH );
    //this.add( bottomPanel, BorderLayout.SOUTH );
  }

  /**
   * Adds the specified component to the JOutlookBar and sets the bar's name
   * 
   * @param  name      The name of the outlook bar
   * @param  componenet   The component to add to the bar
   */
  public void addBar( String name, JComponent component )
  {
	System.out.println("component width "+ MmAccordionComponent.width+" component height "+ MmAccordionComponent.height);  
    BarInfo barInfo = new BarInfo( name, component,MmAccordionComponent.width,MmAccordionComponent.height );
    //barInfo.getButton().addActionListener( this );
    barInfo.accordionButton.addActionListener( this );
    this.bars.put( name, barInfo );
    render();
  }

  /**
   * Adds the specified component to the JOutlookBar and sets the bar's name
   * 
   * @param  name      The name of the outlook bar
   * @param  icon      An icon to display in the outlook bar
   * @param  componenet   The component to add to the bar
   */
  public void addBar( String name, Icon icon, JComponent component )
  {
    BarInfo barInfo = new BarInfo( name, icon, component );
    barInfo.getButton().addActionListener( this );
    this.bars.put( name, barInfo );
    render();
  }

  /**
   * Removes the specified bar from the JOutlookBar
   * 
   * @param  name  The name of the bar to remove
   */
  public void removeBar( String name )
  {
    this.bars.remove( name );
    render();
  }

  /**
   * Returns the index of the currently visible bar (zero-based)
   * 
   * @return The index of the currently visible bar
   */
  public int getVisibleBar()
  {
    return this.visibleBar;
  }

  /**
   * Programmatically sets the currently visible bar; the visible bar
   * index must be in the range of 0 to size() - 1
   * 
   * @param  visibleBar   The zero-based index of the component to make visible
   */
  public void setVisibleBar( int visibleBar )
  {
	 
    if( visibleBar > 0 &&
      visibleBar < this.bars.size() - 1 )
    {
       this.visibleBar = visibleBar;
       render();
    }
  }

  /**
   * Causes the outlook bar component to rebuild itself; this means that
   * it rebuilds the top and bottom panels of bars as well as making the
   * currently selected bar's panel visible
   */
  public void render()
  {
    // Compute how many bars we are going to have where
    //int totalBars = this.bars.size();
    //int topBars = this.visibleBar+1;
    //int components = 0;
    int topBars = this.bars.size();
    //int bottomBars = totalBars - topBars;

    

    // Get an iterator to walk through out bars with
   // Iterator itr1 = this.bars.keySet().iterator();
    
    BarInfo barInfo = null;

   /* for( int i=0; i<topBars; i++ )
    {
    	String barName = ( String )itr1.next();
        barInfo = ( BarInfo )this.bars.get( barName);
        if(barInfo.panelVisible)
        {
           components+=1;
        }   
    }
    
    topBars+=components;*/
    
    // Get an iterator to walk through out bars with
    //Iterator itr = this.bars.keySet().iterator();
   

    // Render the top bars: remove all components, reset the GridLayout to
    // hold to correct number of bars, add the bars, and "validate" it to
    // cause it to re-layout its components
    this.topPanel.removeAll();
    //GridLayout topLayout = ( GridLayout )this.topPanel.getLayout();
    //topLayout.setRows( topBars );
    this.topPanel.setLayout(new GridBagLayout());
    GridBagConstraints accordion = new GridBagConstraints();
    this.topPanel.setFocusable(true);  
    this.topPanel.validate();
    this.topPanel.repaint();
    int j=0;    
    //for( int i=0; i<topBars; i++ )
    for(Iterator itr = this.bars.keySet().iterator();itr.hasNext();)
    {
        String barName = ( String )itr.next();
        barInfo = ( BarInfo )this.bars.get( barName);
        accordion.fill = GridBagConstraints.HORIZONTAL;
        accordion.gridx=0;
        accordion.gridy=j;
        //accordion.weighty=0.0;
        //accordion.weighty=0.5;
        accordion.insets = new Insets(0,0,0,0);
        this.topPanel.add( barInfo.getButton(),accordion);
        j++;
        this.topPanel.repaint();
        if(barInfo.panelVisible==true)
        {	  
    	    accordion.fill = GridBagConstraints.HORIZONTAL;
            accordion.gridx=0;
            accordion.gridy=j;
            //accordion.weighty=0.5;
            accordion.insets = new Insets(0,10,0,0);
            this.topPanel.add(new Label("Hi"),accordion);
            //barInfo.getComponent().setCursor(getCursor());
            j++;
            
            accordion.fill = GridBagConstraints.HORIZONTAL;
            accordion.gridx=0;
            accordion.gridy=j;
            //accordion.weighty=0.5;
            accordion.insets = new Insets(0,10,0,0);
            
            this.topPanel.add(new Label("Hi"),accordion);
            //barInfo.getComponent().setCursor(getCursor());
            j++;
              
        }  
      
    }
    
    this.topPanel.setFocusable(true);
    
    //this.topPanel.setFocusable(true);
    
    
   /* if(barInfo.panelVisible)
    {	  System.out.println("entered");
  	  this.topPanel.add(barInfo.getButton());
  	  
    }*/	
    
    this.topPanel.validate();
    this.topPanel.repaint();


    // Render the center component: remove the current component (if there
    // is one) and then put the visible component in the center of this panel
    
    //if(barInfo.panelVisible)
    /*{	
    	
        if( this.visibleComponent != null )
        {
            this.remove( this.visibleComponent );
        }
    	this.add(barInfo.getButton(),BorderLayout.CENTER);
        this.visibleComponent = barInfo.getComponent();
        this.add( barInfo.getComponent(), BorderLayout.CENTER );
    }    


    // Render the bottom bars: remove all components, reset the GridLayout to
    // hold to correct number of bars, add the bars, and "validate" it to
    // cause it to re-layout its components
    this.bottomPanel.removeAll();
    GridLayout bottomLayout = ( GridLayout )this.bottomPanel.getLayout();
    bottomLayout.setRows( bottomBars );
    for( int i=0; i<bottomBars; i++ )
    {
      String barName = ( String )itr.next();
      barInfo = ( BarInfo )this.bars.get( barName );  
      this.bottomPanel.add( barInfo.getButton());
    }
    this.bottomPanel.validate();*/


    // Validate all of our components: cause this container to re-layout its subcomponents
    this.validate();
    this.repaint();
  }

  /**
   * Invoked when one of our bars is selected
   */
  public void actionPerformed( ActionEvent e )
  {
    int currentBar = 0;
    for( Iterator i=this.bars.keySet().iterator(); i.hasNext(); )
    {
      String barName = ( String )i.next();
      BarInfo barInfo = ( BarInfo )this.bars.get( barName );
      if( barInfo.accordionButton == e.getSource() )
      {
        // Found the selected button
        this.visibleBar = currentBar;
        barInfo.panelVisible=!barInfo.panelVisible;
        //showPanel(barInfo);
        render();
        return;
      }
      currentBar++;
    }
  }
  
  /*public void showPanel(BarInfo barInfo)
  {
	  this.visibleComponent = barInfo.getComponent();
      //this.add( visibleComponent);
      this.add(visibleComponent, BorderLayout.CENTER);
      this.validate();
  }*/
  

  /**
   * Debug, dummy method
   */
  public static JPanel getDummyPanel( String name, int width )
  {
    JPanel panel = new JPanel( new BorderLayout() );
    System.out.println(" MMcomponent width "+ MmAccordionComponent.width);
    panel.setPreferredSize(new Dimension(MmAccordionComponent.width-50,200));
    panel.add( new JLabel( name, JLabel.CENTER ) );
    return panel;
  }
  
  public static JPanel getDummyPanel( JPanel panel )
  {
    //panel.setLayout(new BorderLayout());
    //System.out.println(MMAccordionComponent.width);
    panel.setPreferredSize(new Dimension(MmAccordionComponent.width-50,350));
    //panel.add( new JLabel( name, JLabel.CENTER ) );
     
    return panel;
  }

  /**
   * Debug test...
   */
  /*public static void main( String[] args )
  {
    JFrame frame = new JFrame( "JOutlookBar Test" );
    MMAccordionComponent outlookBar = new MinnesmarkEditorMain();
    outlookBar.addBar( "One", getDummyPanel( "One" ) );
    outlookBar.addBar( "Two", getDummyPanel( "Two" ) );
    outlookBar.addBar( "Three", getDummyPanel( "Three" ) );
    outlookBar.addBar( "Four", getDummyPanel( "Four" ) );
    outlookBar.addBar( "Five", getDummyPanel( "Five" ) );
    outlookBar.setVisibleBar( 2 );
    frame.getContentPane().add( outlookBar );

    frame.setSize( 800, 600 );
    Dimension d = Toolkit.getDefaultToolkit().getScreenSize();
    frame.setLocation( d.width / 2 - 400, d.height / 2 - 300 );
    frame.setVisible( true );
    frame.setDefaultCloseOperation( JFrame.EXIT_ON_CLOSE );
  }*/

  /**
   * Internal class that maintains information about individual Outlook bars;
   * specifically it maintains the following information:
   * 
   * name      The name of the bar
   * button     The associated JButton for the bar
   * component    The component maintained in the Outlook bar
   */
  class BarInfo
  {
    /**
     * The name of this bar
     */
    private String name;

    /**
     * The JButton that implements the Outlook bar itself
     */
    private JButton button;
    public JButton accordionButton;

    /**
     * The component that is the body of the Outlook bar
     */
    private JComponent component;
    public boolean panelVisible;

    /**
     * Creates a new BarInfo
     * 
     * @param  name    The name of the bar
     * @param  component  The component that is the body of the Outlook Bar
     */
    public BarInfo( String name, JComponent component,int width,int height )
    {
      this.name = name;
      this.component = component;
      this.component.validate();
      this.panelVisible = false;
      MmAccordionBar bar = new MmAccordionBar(name,width,height);
      this.button = bar.button;
      //this.button.setPreferredSize(new Dimension(300,40));
      this.accordionButton = bar.accordionButton;
      bar.validate();
      bar.repaint();
      /*this.button.setPreferredSize(new Dimension(300,40));
      this.button.setHorizontalAlignment(SwingConstants.LEFT);
      this.button.setBorderPainted(true);
      this.button.setFocusPainted(false);
      this.button.setBackground(Color.black);
      this.button.setForeground(Color.darkGray);
      Color color = Color.blue;
      Border b = BorderFactory.createEmptyBorder(1, 1, 1, 1);
      Border buttonBorder = BorderFactory.createCompoundBorder(b, new MMAccordionBorder(color));
      
      this.button.setBorder(buttonBorder);*/
      
    }

    /**
     * Creates a new BarInfo
     * 
     * @param  name    The name of the bar
     * @param  icon    JButton icon
     * @param  component  The component that is the body of the Outlook Bar
     */
    public BarInfo( String name, Icon icon, JComponent component )
    {
      this.name = name;
      this.component = component;
      this.button = new JButton( name, icon );
      
    }

    /**
     * Returns the name of the bar
     * 
     * @return The name of the bar
     */
    public String getName()
    {
      return this.name;
    }

    /**
     * Sets the name of the bar
     * 
     * @param  The name of the bar
     */
    public void setName( String name )
    {
      this.name = name;
    }

    /**
     * Returns the outlook bar JButton implementation
     * 
     * @return   The Outlook Bar JButton implementation
     */
    public JButton getButton()
    {
      return this.button;
    }

    /**
     * Returns the component that implements the body of this Outlook Bar
     * 
     * @return The component that implements the body of this Outlook Bar
     */
    public JComponent getComponent()
    {
      return this.component;
    }
  }
}

