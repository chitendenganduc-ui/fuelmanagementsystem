import java.util.Scanner;
public class FuelManagementSystem {
    public static void main (String[] args){

        Scanner input = new Scanner(System.in);
        System.out.println("Hello please enter your name: ");
        String name = input.nextLine();

        System.out.println("What shift are you working in today eg. day or night");
        String shift = input.nextLine();

        System.out.println("Enter price per litre for diesel ");
        double diesel_price = input.nextDouble();

        System.out.println("Enter price per litre for petrol");
        double petrol_price = input.nextDouble();

        System.out.println("Enter price per litre for kerosene");
        double kerosene_price = input.nextDouble();

        System.out.println("Enter opening stock for diesel in litres");
        double diesel_stock = input.nextDouble();

        System.out.println("Enter opening stock for petrol litres");
        double petrol_stock = input.nextDouble();

        System.out.println("enter opening stock for kerosene in litres");
        double kerosene_stock = input.nextDouble();

        String[] fuel_names = {"diesel", "petrol", "kerosene"};
        double[] fuel_price = { diesel_price, petrol_price, kerosene_price };
        double[] fuel_stock = { diesel_stock, petrol_stock, kerosene_stock };

        double[] litresSold = {0, 0, 0};
        double[] salesMoney = {0, 0, 0};

        System.out.println("--the value of the opening stock is--");
            for (int i = 0; i < 3; i++){
            double value = fuel_stock[i] * fuel_price[i];
            System.out.println(fuel_names[i] + " = " + value + "zmw ");
            }

        int choice = 0;
            while (choice != 5){
                showMenue();
                System.out.println("Select Choice");
                choice = input.nextInt();

                switch(choice){
                    case 1: sellfuel(input, fuel_names, fuel_price, fuel_stock, litresSold, salesMoney);
                        break;

                    case 2: addstock(input, fuel_stock, fuel_names);
                        break;

                    case 3: printStockReport(fuel_names, fuel_price, fuel_stock);
                        break;

                    case 4: printSalesReport(fuel_names, litresSold, salesMoney);
                        break;

                    case 5: System.out.println("\n----Closing for " + name + "(" + shift + " shift)---");
                        printSalesReport (fuel_names, litresSold, salesMoney);
                        printStockReport (fuel_names, fuel_price, fuel_stock);
                        System.out.println("Goodbye!!");
                        break;

                    default: System.out.println(" Inalid choice! try 1 - 5" );
                        break;
                }
            }
            input.close();


    }


    public static void showMenue(){
        System.out.println("\nSelect options below");
        System.out.println("1. sell fuel ");
        System.out.println("2. add stock");
        System.out.println("3. view stock report");
        System.out.println("4. view daily sales total");
        System.out.println("5. Exit");
    }

    public static void sellfuel(Scanner input, String[] fuel_names, double[] fuel_price, double[] fuel_stock, double[] litresSold, double[] salesMoney) {
        System.out.println("which fuel? \n1 diesel \n2 petrol \n3 kerosene ");
        System.out.println("Enter number");
        int fuelIndex = input.nextInt() - 1;
        if (fuelIndex < 0 || fuelIndex > 3) {
            System.out.println("Inavlid number");
            return;
        }
        System.out.println("Enter litres to sale for " + fuel_names[fuelIndex]);
        double litres = input.nextDouble();

        if(litres > fuel_stock[fuelIndex]){
            System.out.println("not enough stock available ");
        }else{
            fuel_stock[fuelIndex] = fuel_stock[fuelIndex] - litres;
            litresSold[fuelIndex]  = litresSold[fuelIndex] - litres;
            double money = litres * fuel_price[fuelIndex];
            salesMoney[fuelIndex] = salesMoney[fuelIndex] + money;
            System.out.println("sold!! \namount to pay is " + money + " zmw" );
        }

    }
    public static void addstock(Scanner input, double[] fuel_stock, String[] fuel_names) {
        System.out.println("which fuel to refil ? \n1. diesel \n2. petrol \n3. kerosene ");
        System.out.println("Enter number");
        int fuelIndex = input.nextInt() - 1;

        if(fuelIndex < 0 || fuelIndex > 3) {
            System.out.println("invalid number");
            return;
        }
            System.out.println("Enter litres to add for " + fuel_names[fuelIndex]);
            double litres = input.nextDouble();
            fuel_stock[fuelIndex] = fuel_stock[fuelIndex] + litres;
            System.out.println("refilled!!" + fuel_stock[fuelIndex]);

    }
    public static void printStockReport (String[] fuel_names, double[] fuel_price, double[] fuel_stock){
        System.out.println("---stock report----");
        for(int i = 0; i < 3; i++) {
            System.out.println(fuel_names[i] + " | price: " + fuel_price[i] + " | stock: " + fuel_stock[i] + " litres | value: " + (fuel_stock[i] * fuel_price[i]) + "zmw");
        }
    }

    public static void printSalesReport(String[] fuel_names, double[] litresSold, double[] salesMoney){
        System.out.println("\n---Daily Sales Total---");
        double grandTotal = 0;
        for(int i = 0; i < 3; i++){
            System.out.println( fuel_names[i] + " | litres sold " + litresSold[i] + " | money " + salesMoney[i] + " zmw ");
            grandTotal = litresSold[i] * salesMoney[i];
        }
        System.out.println("THE GRAND TOTAL SALES : " + grandTotal + " zmw ");
    }

}