package com.webcheckers.ui;

import com.webcheckers.appl.PlayerLobby;
import com.webcheckers.model.Player;
import javafx.geometry.Pos;
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
 * Unit Test for PostSignIn.java
 * @author Elana Aronson
 */
public class TestPostSignIn {
    private PostSignIn CuT;

    private Request request;
    private Session session;
    private Response response;
    private TemplateEngine engine;
    private PlayerLobby playerLobby;

    @BeforeEach
    public void setup(){
        request = mock(Request.class);
        session = mock(Session.class);
        when(request.session()).thenReturn(session);
        response = mock(Response.class);
        engine = mock(TemplateEngine.class);
        playerLobby = new PlayerLobby();

        //create a unique CuT for each test
        CuT = new PostSignIn(engine,playerLobby);
    }

    /**
     * Test to see if the TemplateEngine is not null
     */
    @Test
    public void test_create_signin(){
        new PostSignIn(engine,playerLobby);
    }

    /**
     * Test if the player's username fails and then it goes back to the sign in page
     */
    @Test
    public void test_player_name_invalid() {
        when(request.queryParams(PostSignIn.USERNAME)).thenReturn("Player!");
        final TemplateEngineTester testHelper = new TemplateEngineTester();
        when(engine.render(any(ModelAndView.class))).thenAnswer(testHelper.makeAnswer());

        CuT.handle(request,response);

        testHelper.assertViewModelExists();
        testHelper.assertViewModelIsaMap();
        testHelper.assertViewModelAttribute(GetSignIn.TITLE_ATTR, GetSignIn.TITLE);

    }

    /**
     * Test to see if the player's name fails if it has more than 15 characters
     */
    @Test
    public void test_max_numofchar(){
        final TemplateEngineTester testHelper = new TemplateEngineTester();
        when(engine.render(any(ModelAndView.class))).thenAnswer(testHelper.makeAnswer());
        when(request.queryParams("username")).thenReturn("bobbylovesswen261");

        CuT.handle(request, response);

        testHelper.assertViewModelAttribute(PostSignIn.MESSAGE_ATTR,PostSignIn.NUMOFCHARERROR_MSG);
        testHelper.assertViewModelExists();
        testHelper.assertViewModelIsaMap();
        testHelper.assertViewModelAttribute(GetSignIn.TITLE_ATTR, GetSignIn.TITLE);
        testHelper.assertViewName(GetSignIn.VIEW_NAME);
    }

    /**
     * Test if there are no characters
     */
    @Test
    public void min_numofchar(){
        final TemplateEngineTester testHelper = new TemplateEngineTester();
        when(engine.render(any(ModelAndView.class))).thenAnswer(testHelper.makeAnswer());
        when(request.queryParams("username")).thenReturn("");

        CuT.handle(request, response);

        testHelper.assertViewModelAttribute(PostSignIn.MESSAGE_ATTR,PostSignIn.NUMOFCHARERROR_MSG);
        testHelper.assertViewModelExists();
        testHelper.assertViewModelIsaMap();
        testHelper.assertViewModelAttribute(GetSignIn.TITLE_ATTR, GetSignIn.TITLE);
        testHelper.assertViewName(GetSignIn.VIEW_NAME);
    }

    /**
     * Test to see if there are special characters
     */
    @Test
    public void test_specialchar(){
        final TemplateEngineTester testHelper = new TemplateEngineTester();
        when(engine.render(any(ModelAndView.class))).thenAnswer(testHelper.makeAnswer());
        when(request.queryParams("username")).thenReturn("player$");

        CuT.handle(request, response);

        testHelper.assertViewModelAttribute(PostSignIn.MESSAGE_ATTR,PostSignIn.SPECIALCHAR_MSG);
        testHelper.assertViewModelExists();
        testHelper.assertViewModelIsaMap();
        testHelper.assertViewModelAttribute(GetSignIn.TITLE_ATTR, GetSignIn.TITLE);
        testHelper.assertViewName(GetSignIn.VIEW_NAME);
    }

    @Test
    public void test_user_exists(){

    }

    @Test
    public void test_user_valid(){

    }

}


