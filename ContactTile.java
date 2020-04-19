import javax.swing.JPanel;

import java.awt.Graphics;
import java.awt.Color;
import java.awt.Font;


class ContactTile extends JPanel {
    private int red, green, blue;
    private ThunderbirdContact contactInSeat = null;

    private Boolean isAnAisle = false;
    public void setAisle() { isAnAisle = true; }

    ContactTile() {
        super();

        // Todo: Remove everything to do with random colors.
        // Todo: Implement visually appealing colors for aisles and seats.
        // MO: Replaced Random values with a number. Seat color should be light blue.
        SetValues();
    }

    ContactTile(ThunderbirdContact contactInSeatIn) {
        super();
        SetValues();
        contactInSeat = contactInSeatIn;
    }

    final public void SetValues() {
        red = 20;
        green = 10;
        blue = 70;
    }

     public void paintComponent(Graphics g) {
        super.paintComponent(g); 

        int panelWidth = getWidth();
        int panelHeight = getHeight();

        if (isAnAisle) {
            g.setColor(new Color(0,0,0));
        } else {
            g.setColor(new Color(red,green,blue));
        }
        
        g.fillRect (10, 10, panelWidth-20, panelHeight-20);

        g.setColor(new Color(GetContrastingColor(red),GetContrastingColor(green),GetContrastingColor(blue)));

        final int fontSize=18;
        g.setFont(new Font("TimesRoman", Font.PLAIN, fontSize));
        int stringX = (panelWidth/2)-50;
        int stringY = (panelHeight/2)+20;
        if (contactInSeat != null) {

            // ToDo: Display preferred name instead of first and last name. 
            // MO: Done. If there is a preferred name it will display it
            if(contactInSeat.getPreferredName().isEmpty()){
                String firstAndLastName = contactInSeat.getFirstName()+" "+contactInSeat.getLastName();
                g.drawString(firstAndLastName,stringX,stringY);
            }else{
                String preferredName = contactInSeat.getPreferredName();
                g.drawString(preferredName,stringX,stringY);
            }
            
        }
    }

    private static int GetContrastingColor(int colorIn) {
        return ((colorIn+128)%256);
    }
}