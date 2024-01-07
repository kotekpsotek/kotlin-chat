package com.example.demo.view

import com.example.demo.app.Styles
import javafx.beans.property.SimpleListProperty
import javafx.beans.property.SimpleObjectProperty
import javafx.collections.FXCollections
import javafx.geometry.Pos
import tornadofx.*

class MessageAndAuthor(val authorId: String, val message: String);
class ChatDescription(val name: String, val id: String, var messages: List<MessageAndAuthor>)

class MainView : View("Kotekpsotek Chat") {
    var displayChatContent = SimpleObjectProperty<ChatDescription?>(null);

    val messagesChannel = listOf(
        MessageAndAuthor(authorId = "1", message = "Txt1")
    );
    val chatList = listOf<ChatDescription?>(ChatDescription(name = "Test Name", id = "123", messages = messagesChannel))
    override val root = vbox {
        label("Chat App") {
            addClass(Styles.heading)
            style {
                textFill = c("black")
            }
        }

        vbox {
            // TODO: Download from websocket server
            if (chatList.size != 0) {
                style {
                    padding = box(0.px, 10.px, 0.px, 10.px)
                }
                for (chat in chatList) {
                    button(chat?.name ?: "") {
                        setPrefSize(400.0, 50.0)

                        this.alignment = Pos.BASELINE_LEFT

                        style {
                            padding = box(5.px)
                            fontFamily = "Inter; sans-serif"
                        }

                        // Switch to chat
                        action {
                            displayChatContent.value = chat
                        }
                    }
                }
            } else {
                this.alignment = Pos.CENTER
                label(text = "No chats are here ❌") {
                    style {
                        fontSize = 15.px
                        padding = box(50.px)
                        textFill = c("red")
                    }
                }
            }
        }
    }
}