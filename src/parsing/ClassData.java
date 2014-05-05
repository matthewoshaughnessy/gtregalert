package parsing;

/**
 * Holds the registration data for a GT Class
 * @author Matthew O'Shaughnessy
 *
 */
public class ClassData {

    public ClassData() {
    	
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public int getCrn() {
        return crn;
    }

    public void setCrn(int crn) {
        this.crn = crn;
    }

    public String getHeading() {
        return heading;
    }

    public void setHeading(String heading) {
        this.heading = heading;
    }

    public String getSection() {
        return section;
    }

    public void setSection(String section) {
        this.section = section;
    }

    public int getCapacity() {
        return capacity;
    }

    public void setCapacity(int capacity) {
        this.capacity = capacity;
    }

    public int getActual() {
        return actual;
    }

    public void setActual(int actual) {
        this.actual = actual;
    }

    public int getRemaining() {
        return remaining;
    }

    public void setRemaining(int remaining) {
        this.remaining = remaining;
    }

    public int getW_capacity() {
        return w_capacity;
    }

    public void setW_capacity(int w_capacity) {
        this.w_capacity = w_capacity;
    }

    public int getW_actual() {
        return w_actual;
    }

    public void setW_actual(int w_actual) {
        this.w_actual = w_actual;
    }

    public int getW_remaining() {
        return w_remaining;
    }

    public void setW_remaining(int w_remaining) {
        this.w_remaining = w_remaining;
    }

    // class title
    private String title;
    
    // class CRN
    private int crn;
    
    // class heading (e.g., ECE)
    private String heading;
    
    // class section (e.g., A)
    private String section;
    
    // current class capacity
    private int capacity;
    
    // current number registered
    private int actual;
    
    // current number remaining
    private int remaining;
    
    // current waitlist capacity
    private int w_capacity;
    
    // current waitlist registered
    private int w_actual;
    
    // current waitlist remaining
    private int w_remaining;
}
