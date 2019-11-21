package com.webcheckers.ui;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
import java.util.logging.Logger;
import java.util.UUID;

import spark.ModelAndView;
import spark.Request;
import spark.Response;
import spark.Route;
import spark.TemplateEngine;
import spark.Session;
import spark.*;
import static spark.Spark.halt;


import com.webcheckers.appl.GameCenter;
import com.webcheckers.appl.PlayerLobby;
import com.webcheckers.model.Game;
import com.webcheckers.model.Player;
import com.webcheckers.util.Message;
import com.webcheckers.model.Game.ActiveColor;;

/**
 * Renders the Checkers Game page
 */
public class GetGameRoute implements Route {

    private static final Logger LOG = Logger.getLogger(GetHomeRoute.class.getName());
    private final TemplateEngine templateEngine;
    private final GameCenter gameCenter;
    private final PlayerLobby playerLobby;

    /**
     * Create the Spark Route (UI controller) to handle all {@code GET /game} HTTP requests.
     *
     * @param templateEngine
     *   the HTML template rendering engine
     */
    public GetGameRoute(
        final TemplateEngine templateEngine, 
        final GameCenter gameCenter,
        final PlayerLobby playerLobby
    ) {
        this.templateEngine = Objects.requireNonNull(templateEngine, "templateEngine is required");
        this.gameCenter = Objects.requireNonNull( gameCenter, "gameCenter is required");
        this.playerLobby = Objects.requireNonNull( playerLobby, "gameCenter is required");

        LOG.config("GetHomeRoute is initialized.");
    }

    /**
     * Render the WebCheckers Game page.
     *
     * @param request
     *   the HTTP request
     * @param response
     *   the HTTP response
     *
     * @return
     *   the rendered HTML for the Game page
     */
    @Override
    public Object handle(Request request, Response response) {
        LOG.finer("GetGameRoute is invoked.");

        final Session httpSession = request.session();
        final Player currentPlayer = httpSession.attribute("currentUser");
        
        Game playersGame = gameCenter.getCurrentGame(currentPlayer);
        String opponentName;
        Player opponent, redPlayer, whitePlayer;

        Map<String, Object> vm = new HashMap<>();
        if (playersGame != null) {
            // The current user is in a game
            opponentName = playersGame.getOpponentsName(currentPlayer.getName());
            opponent = playerLobby.getPlayerFromName(opponentName);
            redPlayer = currentPlayer.getColor() == ActiveColor.RED ? currentPlayer : opponent;   
            whitePlayer = currentPlayer.getColor() == ActiveColor.RED ? opponent : currentPlayer; 
        } else {
            // The current user is not in a game -> Trying to create a new game
            opponentName = request.queryParams("opponent");
            if (opponentName == null) {
                // No opponent name is provided (if user manually types /game)
                response.redirect(WebServer.HOME_URL);
                request.session().attribute("errorMessage", "Pick an opponent to play against!");
                halt();
                return "test";
            }
            opponent = playerLobby.getPlayerFromName(opponentName);
            if (opponent.getCurrentGameID() != null) {
                // The opponent they picked is in another game already
                response.redirect(WebServer.HOME_URL);
                request.session().attribute("errorMessage", "This player is already in a game!");
                halt();
                return "test";
            }
            playersGame = gameCenter.createNewGame(currentPlayer, opponent);
            redPlayer = currentPlayer;
            whitePlayer = opponent;
        } 
        // Render the board if no errors are hit before this point
        vm.put("board", playersGame.getBoard(currentPlayer.getColor() == ActiveColor.WHITE));
        vm.put("title", "Checkers Game");
        vm.put("currentUser", currentPlayer);
        vm.put("redPlayer", redPlayer);
        vm.put("whitePlayer", whitePlayer);
        vm.put("activeColor", playersGame.getActiveColor());
        vm.put("viewMode", currentPlayer.getViewMode());
        return templateEngine.render(new ModelAndView(vm , "game.ftl"));
    }

}