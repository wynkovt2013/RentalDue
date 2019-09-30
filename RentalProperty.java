public abstract class RentalProperty implements Payment {
    private char type;
    private String id;
    private int bednum;
    private double price;
    private double rate;

    private void validateInfo(String info){

        if(!info.matches("(A|S) (A|S)ABQ\\d{3} \\d")) {
            System.err.println("invalid info");
            System.exit(1);
        }
    }

    private void validateType(char temp, char t) {
        if (temp != t) {
            System.err.printf("invalid type ['%c'], should be '%c'.", temp, t);
            System.exit(1);
        }
    }

    private void validateBedNum(int num, int maxNum) {
        if (num < 1 || num > maxNum) {
            System.err.printf("invalid number of bedroom [%d], should be in [1,%d].",num,maxNum);
            System.exit(1);
        }
    }

    public RentalProperty(String info, char type, String idLetters, int maxBedNum, double rate) {
        char temp_type = info.charAt(0);
        String temp_id = info.substring(2,9);
        int temp_bednum = Character.getNumericValue(info.charAt(10));

        validateInfo(info);
        validateType(temp_type,type);
        validateBedNum(temp_bednum, maxBedNum);
        this.type = temp_type;
        this.id = temp_id;
        this.bednum = temp_bednum;
        this.rate = rate;
        switch (type) {
            case 'S':
                price = (bednum == 3) ? 1300.00 : (bednum == 2) ? 1100.00 : 900.00;
                break;
            case 'A':
                price = (bednum == 2) ? 800.00 : 600.00;
                break;
            default:
                System.err.println("invalid type, gg");
                System.exit(1);
        }
    }

    @Override
    public double getRentDue(){
        return price * (1.00 + rate);
    }

    private String AmountToString() {
        return String.format("$%,.2f",getRentDue());
    }
    public char getType() {
        return type;
    }

    public String getId() {
        return id;
    }

    public int getBedNum() {
        return bednum;
    }

    public void OutputHeader() {
        if (type == 'S') {
            System.out.println("Single-Family Rental Summary:\n" +
                               "House ID Number   # of Bedrooms  Rent Due\n"+
                               "===============   ============= =========");
        }
        else if (type == 'A') {
            System.out.println("Apartment rental Summary:\n" +
                               "Apartment ID No   # of Bedrooms  Rent Due\n"+
                               "===============   ============= =========");
        }
    }

    public void OutputCurrentRent(){
        System.out.printf("%-6s                 %d       %9s\n",id,bednum,AmountToString());
    }
}
