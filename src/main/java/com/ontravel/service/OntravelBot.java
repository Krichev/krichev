package com.ontravel.service;

import com.ontravel.model.City;
import com.ontravel.repository.FindAttractions;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.experimental.FieldDefaults;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

@Component
@Log4j2
@FieldDefaults(level = AccessLevel.PRIVATE)
public class OntravelBot extends TelegramLongPollingBot {
    FindAttractions findAttractions;

    @Getter
    @Value("${bot.ontravel.username}")
    String botUsername;

    @Getter
    @Value("${bot.ontravel.token}")
    String botToken;

    final FindAttr findAttr;


    @Autowired
    public OntravelBot(FindAttr findAttr) {
        this.findAttr = findAttr;
    }


    @Override
    public void onUpdateReceived(Update update) {

        City description = null;
        try {
            description = findAttr.find(update).get();
            if (description.getAttractions() != null)
                sendTextMessage(update.getMessage().getChatId(), description.getAttractions());
            else sendTextMessage(update.getMessage().getChatId(), "there is no attractions ");

        } catch (Exception e) {
            sendTextMessage(update.getMessage().getChatId(), "there is no such city");
        }
    }

    public synchronized void sendTextMessage(Long chatId, String text) {
        SendMessage sendMessage = new SendMessage();
        sendMessage.enableMarkdown(true);
        sendMessage.setChatId(chatId);
        sendMessage.setText(text);


        try {
            execute(sendMessage);
        } catch (TelegramApiException e) {
            log.error(e);
        }
    }


}