package lordnano.exaltedhelper;

/**
 * Created by damian.girardello on 15/04/2015.
 */
public class Essence {
    private int maxPersonal;
    private int maxPheripheral;
    private int commited;
    private int currentPersonal;
    private int currentPheripheral;

    public Essence() {
        this.maxPersonal = 0;
        this.maxPheripheral = 0;
        this.commited = 0;
        this.calculateMaxEssence();
    }

    // Setters
    public void setMaxPersonal(int maxPersonal) {
        this.maxPersonal = maxPersonal;
    }

    public void setMaxPheripheral(int maxPheripheral) {
        this.maxPheripheral = maxPheripheral;
    }

    public void setCommited(int commited) {
        this.commited = commited;
    }

    // Getters
    public int getMaxPersonal() {
        return maxPersonal;
    }

    public int getMaxPheripheral() {
        return maxPheripheral;
    }

    public int getCommited() {
        return commited;
    }

    public int getCurrentPersonal() {
        return currentPersonal;
    }

    public int getCurrentPheripheral() {
        return currentPheripheral;
    }

    public String getAnimaText(){

        if(this.currentPersonal == 0){
            return "No more Essence";
        }

        int animaLevel = this.maxPheripheral - this.currentPheripheral;
        if(animaLevel == 0){
            return "No Anima Effect";
        }
        if((animaLevel >= 1)&&(animaLevel <= 3)){
            return "The character’s caste mark glitters and is visible\n" +
                    "from certain angles. The player of anyone\n" +
                    "seeing the Exalt may make a (Perception +\n" +
                    "Awareness) roll at standard diffi culty for his\n" +
                    "character to notice the caste mark. The Solar\n" +
                    "can use the Stealth Ability normally and may\n" +
                    "still hide behind Stealth Charms and other\n" +
                    "concealing magic without fear of detection.\n" +
                    "This effect can persist for as long as an hour after\n" +
                    "the character has ceased to burn Essence.";
        }
        if((animaLevel >= 4)&&(animaLevel <= 7)){
            return "The character’s caste mark burns and will shine\n" +
                    "through anything placed over it. It is impossible\n" +
                    "to mistake the character for anything but what\n" +
                    "she is. Stealth Charms and other such magic,\n" +
                    "including the Night Caste’s ability to mute\n" +
                    "sensory impressions, fail. A character may use\n" +
                    "the Stealth Ability to hide in natural cover,\n" +
                    "but all such attempts incur a +2 diffi culty.";
        }
        if((animaLevel >= 8)&&(animaLevel <= 10)){
            return "The character is surrounded by a coruscant\n" +
                    "aura bright enough to read by, and her caste\n" +
                    "mark is a burning golden brand on her forehead.\n" +
                    "Stealth is impossible.";
        }
        if((animaLevel >= 11)&&(animaLevel <= 15)){
            return "The character is engulfed in a brilliant bonfi re\n" +
                    "of Essence that burns from her feet to at least\n" +
                    "a foot above her head. Objects that come in\n" +
                    "contact with the aura may be left bleached or\n" +
                    "faded, as if they had been exposed to the sun\n" +
                    "for many days. The character is visible for miles.\n" +
                    "The light produced is bright and steady enough\n" +
                    "to read by, out to a spearcast’s distance.";
        }
        if((animaLevel >= 16)&&(animaLevel < this.maxPheripheral)){
            return "The character is surmounted or surrounded\n" +
                    "by a burning image totemic to her person. A\n" +
                    "warrior might be surrounded by a great golden\n" +
                    "bull, a Twilight Caste magician by an incredibly\n" +
                    "elaborate mandala, and so on. This effect fades\n" +
                    "during any action the character does not spend\n" +
                    "Essence, but it leaps back into existence from\n" +
                    "the solar bonfi re of the character’s anima if the\n" +
                    "character again burns Peripheral Essence.";
        }

        return "";
    }

    // Other
    public void calculateMaxEssence(){
        this.currentPersonal = this.maxPersonal - this.commited;
        this.currentPheripheral = this.maxPheripheral;
        if (this.currentPersonal < 0){
            this.currentPheripheral += this.currentPersonal;
            this.currentPersonal = 0;
        }
        if (this.currentPheripheral < 0){
            this.currentPheripheral = 0;
        }
    }

    public boolean useEssence(int qtyMotes){
        if ((this.currentPersonal + this.currentPheripheral - qtyMotes) < 0) return false;

        if(this.currentPersonal > 0){
            this.currentPersonal -= qtyMotes;
            if (this.currentPersonal < 0){
                this.currentPheripheral += this.currentPersonal;
                this.currentPersonal = 0;
            }
        } else {
            this.currentPheripheral -= qtyMotes;
        }
        return true;
    }

    public void gainEssence(int qtyMotes){
        if (this.currentPheripheral < this.maxPheripheral) {
            this.currentPheripheral += qtyMotes;
            if (this.currentPheripheral > this.maxPheripheral) {
                this.currentPersonal += this.currentPheripheral - this.maxPheripheral;
            }
        } else {
            this.currentPersonal += qtyMotes;
        }

        if(this.currentPersonal > this.maxPersonal){
            this.currentPersonal = this.maxPersonal;
        }
    }

}
