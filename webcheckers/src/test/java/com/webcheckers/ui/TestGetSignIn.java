package com.webcheckers.ui;

import com.webcheckers.model.Player;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import spark.*;
import spark.TemplateEngine;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

/**
 * Unit Testing for GetSignIn.Java
 * @author Elana Aronson
 */
public class TestGetSignIn {
    private GetSignIn CuT;

    private Request request;
    private Session session;
    private Response response;
    private TemplateEngine engine;


    @BeforeEach
    public void setup(){
        request = mock(Request.class);
        session = mock(Session.class);
        when(request.session()).thenReturn(session);
        response = mock(Response.class);
        engine = mock(TemplateEngine.class);

        //create a unique CuT for each test
        CuT = new GetSignIn(engine);
    }

    /**
     * Test to see if the TemplateEngine is not null
     */
    @Test
    public void test_create_signIn(){
        new GetSignIn(engine);
    }

    /**
     * Test if there is a player that is signed in where it stays in the homepage
     */
    @Test
    public void test_player_not_null(){
        //setup
        Player player = new Player("player");
        when(session.attribute(GetHomeRoute.PLAYER_KEY)).thenReturn(player);

        //invoke
        String actual = CuT.handle(request,response);

        //analyze
        verify(response).redirect(WebServer.HOME_URL);
        assertEquals(null,actual);//return CuT null
    }

    /**
     * Test if there is not a player signed in where it will route to sign in page.
     */
    @Test
    public void test_player_notSignIn(){
        //setup
        when(session.attribute(GetHomeRoute.PLAYER_KEY)).thenReturn(null);//check if player is null
        final TemplateEngineTester testHelper = new TemplateEngineTester();
        when(engine.render(any(ModelAndView.class))).thenAnswer(testHelper.makeAnswer());

        //invoke
        CuT.handle(request,response);

        //analyze
        testHelper.assertViewModelExists();
        testHelper.assertViewModelIsaMap();
        testHelper.assertViewModelAttribute(GetSignIn.TITLE_ATTR, GetSignIn.TITLE);
        testHelper.assertViewName(GetSignIn.VIEW_NAME);
    }

}
