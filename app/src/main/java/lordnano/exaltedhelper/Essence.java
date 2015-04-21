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

        int animaLevel = this.maxPheripheral - this.currentPheripheral;
        if(animaLevel == 0){
            return "No Anima Effect";
        }
        if((animaLevel >= 1)&&(animaLevel <= 3)){
            return "The character’s caste mark glitters and is visible " +
                    "from certain angles. The player of anyone " +
                    "seeing the Exalt may make a (Perception + " +
                    "Awareness) roll at standard diffi culty for his " +
                    "character to notice the caste mark. The Solar " +
                    "can use the Stealth Ability normally and may " +
                    "still hide behind Stealth Charms and other " +
                    "concealing magic without fear of detection. " +
                    "This effect can persist for as long as an hour after " +
                    "the character has ceased to burn Essence.";
        }
        if((animaLevel >= 4)&&(animaLevel <= 7)){
            return "The character’s caste mark burns and will shine " +
                    "through anything placed over it. It is impossible " +
                    "to mistake the character for anything but what " +
                    "she is. Stealth Charms and other such magic, " +
                    "including the Night Caste’s ability to mute " +
                    "sensory impressions, fail. A character may use " +
                    "the Stealth Ability to hide in natural cover, " +
                    "but all such attempts incur a +2 diffi culty.";
        }
        if((animaLevel >= 8)&&(animaLevel <= 10)){
            return "The character is surrounded by a coruscant " +
                    "aura bright enough to read by, and her caste " +
                    "mark is a burning golden brand on her forehead. " +
                    "Stealth is impossible.";
        }
        if((animaLevel >= 11)&&(animaLevel <= 15)){
            return "The character is engulfed in a brilliant bonfi re " +
                    "of Essence that burns from her feet to at least " +
                    "a foot above her head. Objects that come in " +
                    "contact with the aura may be left bleached or " +
                    "faded, as if they had been exposed to the sun " +
                    "for many days. The character is visible for miles. " +
                    "The light produced is bright and steady enough " +
                    "to read by, out to a spearcast’s distance.";
        }
        if((animaLevel >= 16)&&(animaLevel < this.maxPheripheral)){
            return "The character is surmounted or surrounded " +
                    "by a burning image totemic to her person. A " +
                    "warrior might be surrounded by a great golden " +
                    "bull, a Twilight Caste magician by an incredibly " +
                    "elaborate mandala, and so on. This effect fades " +
                    "during any action the character does not spend " +
                    "Essence, but it leaps back into existence from " +
                    "the solar bonfi re of the character’s anima if the " +
                    "character again burns Peripheral Essence.";
        }
        if(animaLevel == this.maxPheripheral) {
            return "No More Essence!";
        }


        return "";
    }

    public int getTotalRemainingEssence(){
        return (currentPersonal + currentPheripheral);
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
