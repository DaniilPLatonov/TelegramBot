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
                        parser.parseUrl();
                        execute(sendInlineKeyBoardMessage(update.getMessage().getChatId(), parser));
                    } catch (TelegramApiException e) {
                        e.printStackTrace();
                    } catch (IOException e) {
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
        List<InlineKeyboardButton> keyboardButtonsRow1 = new ArrayList<>();
        List<InlineKeyboardButton> keyboardButtonsRow2 = new ArrayList<>();
        List<InlineKeyboardButton> keyboardButtonsRow3 = new ArrayList<>();
        List<InlineKeyboardButton> keyboardButtonsRow4 = new ArrayList<>();
        List<InlineKeyboardButton> keyboardButtonsRow5 = new ArrayList<>();
        List<InlineKeyboardButton> keyboardButtonsRow6 = new ArrayList<>();
        List<InlineKeyboardButton> keyboardButtonsRow7 = new ArrayList<>();
        List<InlineKeyboardButton> keyboardButtonsRow8 = new ArrayList<>();
        List<InlineKeyboardButton> keyboardButtonsRow9 = new ArrayList<>();
        List<InlineKeyboardButton> keyboardButtonsRow10 = new ArrayList<>();
        for (int i = 1; i<3; i++) {

            keyboardButtonsRow2.add(new InlineKeyboardButton().builder()
                    .text(parser.Foods.get(i).getProduct())
                    .callbackData("В продукте " + parser.getCal(parser.Foods.get(i).getProduct()) + " калорий")
                    .build());
        }
        for (int i = 3; i< 5; i++)
        {
            keyboardButtonsRow3.add(new InlineKeyboardButton().builder()
                    .text(parser.Foods.get(i).getProduct())
                    .callbackData("В продукте " +parser.getCal(parser.Foods.get(i).getProduct())+" калорий").build());
        }
        for (int i = 5; i<7; i++) {
            keyboardButtonsRow4.add(new InlineKeyboardButton().builder()
                    .text(parser.Foods.get(i).getProduct())
                    .callbackData("В продукте " +parser.getCal(parser.Foods.get(i).getProduct())+" калорий").build());
        }
        for (int i = 7; i< 9; i++) {
            keyboardButtonsRow5.add(new InlineKeyboardButton().builder()
                    .text(parser.Foods.get(i).getProduct())
                    .callbackData("В продукте " +parser.getCal(parser.Foods.get(i).getProduct())+" калорий").build());
        }

        for (int i = 9; i<11; i++) {
            keyboardButtonsRow6.add(new InlineKeyboardButton().builder()
                    .text(parser.Foods.get(i).getProduct())
                    .callbackData("В продукте " +parser.getCal(parser.Foods.get(i).getProduct())+" калорий").build());
        }
        for (int i = 11; i< 13; i++) {
            keyboardButtonsRow7.add(new InlineKeyboardButton().builder()
                    .text(parser.Foods.get(i).getProduct())
                    .callbackData("В продукте " +parser.getCal(parser.Foods.get(i).getProduct())+" калорий").build());
        }
        for (int i = 13; i<15; i++) {
            keyboardButtonsRow8.add(new InlineKeyboardButton().builder()
                    .text(parser.Foods.get(i).getProduct())
                    .callbackData("В продукте " +parser.getCal(parser.Foods.get(i).getProduct())+" калорий").build());
        }
        for (int i = 15; i< 17; i++) {
            keyboardButtonsRow9.add(new InlineKeyboardButton().builder()
                    .text(parser.Foods.get(i).getProduct())
                    .callbackData("В продукте " +parser.getCal(parser.Foods.get(i).getProduct())+" калорий").build());
        }

        List<List<InlineKeyboardButton>> rowList = new ArrayList<>();
        rowList.add(keyboardButtonsRow1);
        rowList.add(keyboardButtonsRow2);
        rowList.add(keyboardButtonsRow3);
        rowList.add(keyboardButtonsRow4);
        rowList.add(keyboardButtonsRow5);
        rowList.add(keyboardButtonsRow6);
        rowList.add(keyboardButtonsRow7);
        rowList.add(keyboardButtonsRow8);
        rowList.add(keyboardButtonsRow9);
        inlineKeyboardMarkup.setKeyboard(rowList);
        SendMessage message = new SendMessage();
        message.setChatId(String.valueOf(chatId));
        message.setText(" Количество калорий в 100 гр. какого продукта ты хочешь узнать?");
        message.setReplyMarkup(inlineKeyboardMarkup);
        return message;
    }
}






