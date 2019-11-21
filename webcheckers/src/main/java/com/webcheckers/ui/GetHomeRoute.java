package com.webcheckers.ui;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
import java.util.logging.Logger;
import java.util.List;


import com.webcheckers.appl.PlayerLobby;
import com.webcheckers.model.Player;
import spark.*;
import static spark.Spark.halt;


import com.webcheckers.util.Message;

/**
 * The UI Controller to GET the Home page.
 *
 * @author <a href='mailto:bdbvse@rit.edu'>Bryan Basham</a>
 */
public class GetHomeRoute implements Route {
  private static final Logger LOG = Logger.getLogger(GetHomeRoute.class.getName());

  private static final Message WELCOME_MSG = Message.info("Welcome to the world of online Checkers.");

  // Key in the session attribute map for the player who started the session
  static final String PLAYER_KEY = "currentUser";
  static final String HAS_PLAYERS ="hasPlayers";
  static final String CURRENTLY_SIGNED_IN = "isSignedIn";
  static final String MESSAGE_ATTR = "message";
  static final String VIEW_NAME = "home.ftl";
  static final String LIST_OF_PLAYERS = "listOfPlayers";
  static final String NUMBER_OF_PLAYERS ="numberOfPlayers";

  private final TemplateEngine templateEngine;
  private final PlayerLobby playerLobby;
  /**
   * Create the Spark Route (UI controller) to handle all {@code GET /} HTTP requests.
   *
   * @param templateEngine
   *   the HTML template rendering engine
   * @param playerLobby
   */
  public GetHomeRoute(final TemplateEngine templateEngine, PlayerLobby playerLobby) {
    this.templateEngine = Objects.requireNonNull(templateEngine, "templateEngine is required");
    this.playerLobby = Objects.requireNonNull(playerLobby, "playerLobby is required");
    //
    LOG.config("GetHomeRoute is initialized.");
  }

  /**
   * Render the WebCheckers Home page.
   *
   * @param request
   *   the HTTP request
   * @param response
   *   the HTTP response
   *
   * @return
   *   the rendered HTML for the Home page
   */
  @Override
  public Object handle(Request request, Response response) {
    LOG.finer("GetHomeRoute is invoked.");
    
    final Session session = request.session();
    Player player = session.attribute(PLAYER_KEY);
    String errorMessage = session.attribute("errorMessage");

    Map<String, Object> vm = new HashMap<>();
    vm.put("title", "Welcome!");
    vm.put(PLAYER_KEY, player);

    vm.put(MESSAGE_ATTR, errorMessage != null ? Message.info(errorMessage) : WELCOME_MSG);
    if (errorMessage != null) session.removeAttribute("errorMessage");

    //if there is a player, display the player list
    if(player != null) {
      if (player.getCurrentGameID() != null) {
          response.redirect(WebServer.GAME_URL);
          halt();
          return null;
      }
      vm.put(CURRENTLY_SIGNED_IN, true);
      List<String> otherPlayers = playerLobby.signedInPlayers(player);
      if (otherPlayers.size() > 0) {
        vm.put(HAS_PLAYERS, true);
        vm.put(LIST_OF_PLAYERS, otherPlayers);
      } else {
        vm.put(HAS_PLAYERS, false);
      }
    } else {
      vm.put(CURRENTLY_SIGNED_IN, false);
      vm.put(NUMBER_OF_PLAYERS, playerLobby.totalSignedInPlayers());
    }

    // render the View
    return templateEngine.render(new ModelAndView(vm , VIEW_NAME));
  }
}
