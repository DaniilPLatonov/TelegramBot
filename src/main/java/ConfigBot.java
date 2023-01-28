import org.telegram.telegrambots.bots.TelegramLongPollingBot;

import org.telegram.telegrambots.meta.api.methods.send.SendMessage;

import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.InlineKeyboardMarkup;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.InlineKeyboardButton;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class ConfigBot extends TelegramLongPollingBot {
    public static String TOKEN = "5177974839:AAGcnInVjTEkN1pSiBhSIyMEF9sK7KIZ6Tg";
    public static String NAME = "DANIKPLAT_BOT";

    @Override
    public String getBotUsername() {
        return NAME;
    }


    @Override
    public String getBotToken() {
        return TOKEN;
    }


    @Override
    public void onUpdateReceived(Update update) {
        if (update.hasMessage()) {
            if (update.getMessage().hasText()) {
                if (update.getMessage().getText().equals("/start")) {
                    try {
                        Parser parser = new Parser();
                        Parser.parseUrl();
                        execute(sendInlineKeyBoardMessage(update.getMessage().getChatId(), parser));
                    } catch (TelegramApiException | IOException e) {
                        e.printStackTrace();
                    }
                }
            }
        } else if (update.hasCallbackQuery()) {
            try {
                SendMessage message = new SendMessage();
                message.setChatId(String.valueOf(update.getCallbackQuery().getMessage().getChatId()));
                message.setText(update.getCallbackQuery().getData());
                execute(message);
            } catch (TelegramApiException e) {
                e.printStackTrace();
            }
        }
    }

    public static SendMessage sendInlineKeyBoardMessage(long chatId, Parser parser) throws IOException {
        InlineKeyboardMarkup inlineKeyboardMarkup = new InlineKeyboardMarkup();


        List<List<InlineKeyboardButton>> rowList = new ArrayList<>();

        for (int j = 1; j < 40; j += 2) {
            List<InlineKeyboardButton> keyboardButtonsRow2 = new ArrayList<>();
            for (int i = j; i < j + 2; i++) {
                keyboardButtonsRow2.add(new InlineKeyboardButton().builder()
                        .text(Parser.Foods.get(i).getProduct())
                        .callbackData("В продукте " + Parser.getCal(Parser.Foods.get(i).getProduct()) + " калорий")
                        .build());
            }
            rowList.add(keyboardButtonsRow2);
        }


        inlineKeyboardMarkup.setKeyboard(rowList);
        SendMessage message = new SendMessage();
        message.setChatId(String.valueOf(chatId));
        message.setText(" Количество калорий в 100 гр. какого продукта ты хочешь узнать?");
        message.setReplyMarkup(inlineKeyboardMarkup);
        return message;
    }
}






