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
                                
                                How to use:
                                @username
                                """)
                        .build();

                try {
                    telegramClient.execute(message);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            } else if (text.startsWith("@")) {

                String username = text.substring(1);

                String response =
                        "👤Basic info\n" +
                                "•Username: @" + username + "\n" +
                                "•Status: ✅Normal\n" +
                                "•Nickname: Muloh Abdul Gani\n" +
                                "•Registered: 2025-11-08 04:45:58 (207 days)\n\n" +

                                "👤Detailed info\n" +
                                "•Verified: ✅Verified\n" +
                                "•Photos: 8\n" +
                                "•Gender: Other\n" +
                                "•Age: 22 years old\n" +
                                "•Birthday: 2003-12-05\n" +
                                "•Job: cio\n\n" +

                                "•Official link:\n" +
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