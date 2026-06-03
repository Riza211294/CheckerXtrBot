package org.example;

import org.telegram.telegrambots.longpolling.TelegramBotsLongPollingApplication;

public class CheckerXtrBot {

    private static final String TOKEN = "8364491145:AAHmeghY4ZYnGOvpWA--P76Cl7kyaDIRMNM";

    public static void main(String[] args) {

        try {

            TelegramBotsLongPollingApplication botsApplication =
                    new TelegramBotsLongPollingApplication();

            botsApplication.registerBot(
                    TOKEN,
                    new CheckerXtrTelegramBot(TOKEN)
            );

            System.out.println("CheckerXtr Bot ONLINE");

            Thread.currentThread().join();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}