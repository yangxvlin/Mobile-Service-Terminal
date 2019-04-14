import java.util.Scanner;

/**
 * Xulin Yang, 904904
 *
 * @create 2019-04-14 13:42
 * description:
 **/

public class Main {
    /* text based UI */
    public static void main(String argv[]) {
        /* create real word */
        Store store = new Store("Melbourne");
        store.registerStaff(0, "xuliny", "NormalStaff");
        store.registerStaff(1, "peter", "Supervisor");
        /* create real word */

        Scanner scanner = new Scanner(System.in);

        boolean flag = true;

        Controller controller = new Controller(store);

        while(flag) {
            System.out.println("What are you going to do?");
            System.out.println("0: quit, 1:login");
            int operation = scanner.nextInt();
            scanner.nextLine();

            switch (operation) {
                case 0:
                    flag = false;
                    break;
                case 1:
                    if (login(scanner, controller)) {
                        staffOperation(scanner, controller);
                    } else {
                        System.out.println("Invalid staff information.");
                    }

                    break;
                default:
                    System.out.println("invalid input");
                    break;
            }
        }

        scanner.close();
    }

    public static void staffOperation(Scanner scanner, Controller controller) {
        if (controller.getOperatingStaff() instanceof NormalStaff) {
            boolean flag = true;
            while(flag) {
                System.out.println("0:logout, 1:add item, 2:make card sale");
                int operation = scanner.nextInt();
                scanner.nextLine();

                switch (operation) {
                    case 0:
                        controller.logout();
                        flag = false;
                        break;
                    case 1:
                        System.out.print("Item type: ");
                        String itemType = scanner.nextLine();
                        System.out.print("Item bar code: ");
                        String itemBarCode = scanner.nextLine();
                        System.out.print("Item price: ");
                        float itemPrice = scanner.nextFloat();
                        System.out.print("Item discount rate: ");
                        float itemDiscountRate = scanner.nextFloat();
                        scanner.nextLine();

                        controller.addNewItem(new Item(itemType, itemBarCode, itemPrice, itemDiscountRate));
                        System.out.println("Item added successful.");

                        break;
                    case 2:
                        makeCardSale(scanner, controller);
                        break;
                    default:
                        System.out.println("invalid input");
                        break;
                }
            }
        } else if (controller.getOperatingStaff() instanceof Supervisor) {
            boolean flag = true;
            while(flag) {
                System.out.println("0:logout, 1:add item, 2:make card sale, 3: inventory lookup");
                int operation = scanner.nextInt();
                scanner.nextLine();

                switch (operation) {
                    case 0:
                        controller.logout();
                        flag = false;
                        break;
                    case 1:
                        System.out.print("Item type: ");
                        String itemType = scanner.nextLine();
                        System.out.print("Item bar code: ");
                        String itemBarCode = scanner.nextLine();
                        System.out.print("Item price: ");
                        float itemPrice = scanner.nextFloat();
                        System.out.print("Item discount rate: ");
                        float itemDiscountRate = scanner.nextFloat();
                        scanner.nextLine();

                        controller.addNewItem(new Item(itemType, itemBarCode, itemPrice, itemDiscountRate));
                        System.out.println("Item added successful.");

                        break;
                    case 2:
                        makeCardSale(scanner, controller);
                        break;
                    case 3:
                        inventoryLookup(scanner, controller);
                        break;
                    default:
                        System.out.println("invalid input");
                        break;
                }

            }
        } else {
            System.out.println("Unsupported staff");
        }

    }

    public static void makeCardSale(Scanner scanner, Controller controller) {
        boolean flag = true;
        while(flag) {
            System.out.println("0:cancel card sale, 1:create a sale");
            int operation = scanner.nextInt();
            scanner.nextLine();

            switch (operation) {
                case 0:
                    System.out.println("Sale cancelled");
                    flag = false;
                    controller.cancelSale();
                    break;
                case 1:
                    controller.createSale();

                    enterSaleItem(scanner, controller);

                    System.out.print("Total price: ");
                    System.out.println(controller.calculateSalePrince());

                    boolean checkoutRes = checkout(scanner, controller);

                    if (checkoutRes) {
                        System.out.println("Payment successful.");
                    } else {
                        System.out.println("Payment unsuccessful.\nSale cancelled");
                        controller.cancelSale();
                    }
                    flag = false;
                    break;
                default:
                    System.out.println("invalid input");
                    break;
            }
        }

    }

    public static boolean checkout(Scanner scanner, Controller controller) {
        boolean flag = true;
        while(flag) {
            System.out.println("0: cancel check out, 1: enter payment Method");
            int operation = scanner.nextInt();
            scanner.nextLine();

            switch (operation) {
                case 0:
                    return false;
                case 1:
                    System.out.println("0: pay by credit card; 1: pay by store debit card");
                    int isStoreCard = scanner.nextInt();
                    scanner.nextLine();
                    System.out.println("card num");
                    float cardNum = scanner.nextFloat();
                    scanner.nextLine();

                    PaymentMethod paymentMethod = new PaymentMethod(isStoreCard==1, cardNum);

                    controller.createTransaction(paymentMethod);

                    if (!controller.validatePayment()) {
                        return false;
                    }

                    controller.makePayment();

                    return true;
                default:
                    System.out.println("invalid input");
                    break;
            }
        }

        return false;
    }

    public static void enterSaleItem(Scanner scanner, Controller controller) {
        boolean flag = true;
        while (flag) {
            System.out.println("0:finish enter items, 1:enter more item");
            int operation = scanner.nextInt();
            scanner.nextLine();

            switch (operation) {
                case 0:
                    flag = false;
                    break;
                case 1:
                    System.out.print("Enter item barcode: ");
                    String itemBarCode = scanner.nextLine();
                    Item tmpItem = controller.getItem(itemBarCode);

                    if (tmpItem != null) {
                        controller.enterSaleItem(tmpItem);
                    }
                    break;
                default:
                    System.out.println("invalid input");
                    break;
            }
        }
    }


    public static void inventoryLookup(Scanner scanner, Controller controller) {
        controller.inventoryLookUp();
    }


    public static boolean login(Scanner scanner, Controller controller) {
        System.out.print("Your staff id: ");
        int staffId = scanner.nextInt();
        scanner.nextLine();
        System.out.print("Your name: ");
        String staffName = scanner.nextLine();

        return controller.login(staffId, staffName);
    }
}
