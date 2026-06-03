package org.example;

import org.telegram.telegrambots.client.okhttp.OkHttpTelegramClient;
import org.telegram.telegrambots.longpolling.util.LongPollingSingleThreadUpdateConsumer;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Update;

public class CheckerXtrTelegramBot implements LongPollingSingleThreadUpdateConsumer {

    private final OkHttpTelegramClient telegramClient;

    public CheckerXtrTelegramBot(String token) {
        this.telegramClient = new OkHttpTelegramClient(token);
    }

    @Override
    public void consume(Update update) {

        if (update.hasMessage() && update.getMessage().hasText()) {

            String text = update.getMessage().getText();
            Long chatId = update.getMessage().getChatId();

            System.out.println("Pesan masuk: " + text);

            if (text.equals("/start")) {

                SendMessage message = SendMessage.builder()
                        .chatId(chatId.toString())
                        .text("""
                  🔍 CheckerXtr Bot

                  Gunakan:
                  /tinder username
                  """)
                        .build();

                try {
                    telegramClient.execute(message);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }

            else if (text.startsWith("/tinder ")) {

                String username = text.replace("/tinder ", "");

                String response =
                        "🔍 [Tinder Checker Bot]\n\n" +
                                "👤 Basic info\n" +
                                "• Username: @" + username + "\n" +
                                "• Status: ✅ Normal\n" +
                                "• Nickname: Demo User\n\n" +
                                "👤 Detailed info\n" +
                                "Deep info unavailable, please contact admin to update credentials\n\n" +
                                "• Official link:\n" +
                                "https://tinder.com/@" + username;

                SendMessage message = SendMessage.builder()
                        .chatId(chatId.toString())
                        .text(response)
                        .build();

                try {
                    telegramClient.execute(message);
                } catch (Exception e) {
                    e.printStackTrace();

                }
            }
        }
    }
}