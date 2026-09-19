package app;

import abstractfactory.DeliveryApplication;
import abstractfactory.GUIFactory;
import abstractfactory.MacOSFactory;
import abstractfactory.WindowsFactory;
import factorymethod.Logistics;
import factorymethod.RoadLogistics;
import factorymethod.SeaLogistics;

import java.util.Locale;
import java.util.Scanner;

public class Main {
    private static final String CARGO = "laboratory equipment";
    private static final String DESTINATION = "Aktau warehouse";

    public static void main(String[] args) {
        String[] choices = getChoices(args);

        if (choices == null) {
            return;
        }

        String deliveryMode = choices[0].toUpperCase(Locale.ROOT);
        String platform = choices[1].toUpperCase(Locale.ROOT);

        if (!isDeliveryModeValid(deliveryMode)) {
            System.out.println("Unsupported delivery mode: " + choices[0]);
            return;
        }

        if (!isPlatformValid(platform)) {
            System.out.println("Unsupported platform: " + choices[1]);
            return;
        }

        Logistics logistics = createLogistics(deliveryMode);
        GUIFactory guiFactory = createFactory(platform);

        System.out.println("Delivery mode: " + deliveryMode);
        System.out.println("UI platform: " + platform);

        DeliveryApplication application = new DeliveryApplication(guiFactory, logistics);
        application.run(CARGO, DESTINATION);
    }

    private static String[] getChoices(String[] args) {
        if (args.length == 2) {
            return new String[]{args[0], args[1]};
        }

        if (args.length == 1) {
            System.out.println("Missing UI platform.");
            return null;
        }

        if (args.length > 2) {
            System.out.println("Please provide delivery mode and UI platform.");
            System.out.println("Supported values: ROAD or SEA, WINDOWS or MACOS.");
            return null;
        }

        Scanner scanner = new Scanner(System.in);

        System.out.print("Enter delivery mode (ROAD or SEA): ");
        if (!scanner.hasNext()) {
            System.out.println("Missing delivery mode.");
            return null;
        }
        String deliveryMode = scanner.next();

        System.out.print("Enter UI platform (WINDOWS or MACOS): ");
        if (!scanner.hasNext()) {
            System.out.println("Missing UI platform.");
            return null;
        }
        String platform = scanner.next();

        return new String[]{deliveryMode, platform};
    }

    private static boolean isDeliveryModeValid(String deliveryMode) {
        return deliveryMode.equals("ROAD") || deliveryMode.equals("SEA");
    }

    private static boolean isPlatformValid(String platform) {
        return platform.equals("WINDOWS") || platform.equals("MACOS");
    }

    private static Logistics createLogistics(String deliveryMode) {
        if (deliveryMode.equals("ROAD")) {
            return new RoadLogistics();
        }

        return new SeaLogistics();
    }

    private static GUIFactory createFactory(String platform) {
        if (platform.equals("WINDOWS")) {
            return new WindowsFactory();
        }

        return new MacOSFactory();
    }
}
