package com.webcheckers.ui;

import com.webcheckers.appl.PlayerLobby;
import com.webcheckers.model.Player;
import com.webcheckers.util.Message;
import com.webcheckers.util.PlayerName;
import spark.*;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

import static spark.Spark.halt;

/**
 * UI controller to POST signin page
 *
 * @author Elana Aronson
 */

public class PostSignIn implements Route {

    static final Message NUMOFCHARERROR_MSG = Message.info("Names must be between 1 and 15 characters.");
    static final Message SPECIALCHAR_MSG = Message.info("Names must only contain letters or numbers.");
    static final Message EXIST_MSG = Message.info("This username is already being used");
    static final String VIEW_NAME = "signin.ftl";
    static final String MESSAGE_ATTR = "message";
    static final String USERNAME= "username";
    static final String PLAYER ="currentUser";

    private final TemplateEngine templateEngine;
    private final PlayerLobby playerLobby;

    public PostSignIn(TemplateEngine templateEngine, PlayerLobby playerLobby) {
        Objects.requireNonNull(templateEngine, "templateEngine must not be null");
        this.templateEngine = templateEngine;
        this.playerLobby = playerLobby;
    }

    /**
     *
     * @param request The HTTP request
     * @param response The HTTP response
     * @return If username is valid, return to home page
     * @throws Exception
     */
    @Override
    public Object handle(Request request, Response response){
        //Start building the View-Model
        final Map<String, Object> vm = new HashMap<>();
        vm.put(GetSignIn.TITLE_ATTR, GetSignIn.TITLE);

        // retrieve the Player's name
        final Session session = request.session();//create a new session
        final String username = request.queryParams(USERNAME);

        //Checks to see if the number of characters qualifies
        if(PlayerName.numOfChar(username)){
            vm.put(MESSAGE_ATTR,NUMOFCHARERROR_MSG);
            return templateEngine.render(new ModelAndView(vm,VIEW_NAME));
        }

        //checks to see if there are special characters
        else if(PlayerName.hasSpecialChar(username)){
            vm.put(MESSAGE_ATTR,SPECIALCHAR_MSG);
            return templateEngine.render(new ModelAndView(vm,VIEW_NAME));
        }

        //if the username already exists
        else if(playerLobby.getPlayerFromName(username) != null){
            vm.put(MESSAGE_ATTR,EXIST_MSG);
            return templateEngine.render(new ModelAndView(vm,VIEW_NAME));
        }

        //If the sign in is valid, it returns to the homepage
        else{
            //Create a new player that is signed in
            Player player = playerLobby.signInPlayer(username);
            session.attribute(PLAYER,player);
            response.redirect(WebServer.HOME_URL);
            //halt();
            return null;
        }

    }
}
