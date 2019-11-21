package com.webcheckers.ui;

import com.webcheckers.model.Player;
import spark.*;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
import static spark.Spark.halt;

/**
 * UI controller to GET signin page
 *
 * @author Elana Aronson
 */

public class GetSignIn implements Route {

    static final String TITLE_ATTR = "title";
    static final String TITLE = "sign in";
    static final String VIEW_NAME = "signin.ftl";

    private final TemplateEngine templateEngine;

    GetSignIn(final TemplateEngine templateEngine) {
        // validation
        Objects.requireNonNull(templateEngine, "templateEngine must not be null");

        this.templateEngine = templateEngine;
    }

    /**
     * This enters to the sign in page if the user has not signed in yet
     * @param request The HTTP request
     * @param response The HTTP response
     * @return The sign in page
     */
    @Override
    public String handle(Request request, Response response){
        final Session httpSession = request.session();
        final Player player = httpSession.attribute(GetHomeRoute.PLAYER_KEY);

        //if there is no player signed in
        if(player == null) {
            //build View-Model for the sign in page
            final Map<String, Object> vm = new HashMap<>();
            vm.put(TITLE_ATTR, TITLE);
            return templateEngine.render(new ModelAndView(vm, VIEW_NAME));
        }

        //goes to the home page if a player is signed in
        else {
            response.redirect(WebServer.HOME_URL);
            //halt();
            return null;
        }
    }
}
