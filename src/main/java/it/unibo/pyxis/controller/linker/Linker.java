package it.unibo.pyxis.controller.linker;

import it.unibo.pyxis.controller.command.Command;
import it.unibo.pyxis.model.level.Level;
import it.unibo.pyxis.model.state.GameState;
import it.unibo.pyxis.view.scene.SceneHandler;


public interface Linker {
    /**
     * Sets the {@link GameState}'s {@link it.unibo.pyxis.model.state.StateEnum} to
     * PAUSE and load the {@link it.unibo.pyxis.view.EndLevelView}.
     */
    void endLevel();

    /**
     * Returns the instance of {@link GameState}.
     *
     * @return The {@link GameState}.
     */
    GameState getGameState();

    /**
     * Returns the maximum level reached by the player during the actual game session.
     *
     * @return The index of the maximum {@link it.unibo.pyxis.model.level.Level}.
     */
    int getMaximumLevelReached();

    /**
     * Adds a {@link Command} to the list of commands that a player can input.
     *
     * @param command The {@link Command} to add.
     */
    void insertCommand(Command<GameState> command);

    /**
     * Adds a {@link Command} to the {@link it.unibo.pyxis.controller.engine.GameLoop}
     * input list.
     *
     * @param command The {@link Command} to add.
     */
    void insertGameCommand(Command<Level> command);

    /**
     * Loads the {@link it.unibo.pyxis.view.MenuView}.
     */
    void menu();

    /**
     * Loads the {@link it.unibo.pyxis.view.PauseView} and
     * set the {@link GameState}'s {@link it.unibo.pyxis.model.state.StateEnum}
     * to PAUSE.
     */
    void pause();

    /**
     * Closes the application.
     */
    void quit();

    /**
     * Renders the current {@link it.unibo.pyxis.view.View} if
     * {@link it.unibo.pyxis.view.RenderableView}.
     */
    void render();

    /**
     * Resumes a paused {@link GameState}.
     */
    void resume();

    /**
     * Loads the {@link it.unibo.pyxis.view.GameView}.
     */
    void run();

    /**
     * Loads the {@link it.unibo.pyxis.view.SelectLevelView}.
     */
    void selectLevel();

    /**
     * Sets the {@link SceneHandler}.
     * @param sceneHandler The {@link SceneHandler} instance to set.
     */
    void setSceneHandler(SceneHandler sceneHandler);

    /**
     * Loads the {@link it.unibo.pyxis.view.SettingsView}.
     */
    void settings();

    /**
     * Loads, if present, the next {@link it.unibo.pyxis.model.level.Level}.
     */
    void switchLevel();
}
