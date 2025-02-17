package it.unibo.pyxis.model.state;

import it.unibo.pyxis.model.level.Level;
import it.unibo.pyxis.model.level.iterator.LevelIterator;

public final class GameStateImpl implements GameState {

    private LevelIterator iterator;
    private Level currentLevel;
    private int score;
    private StateEnum gameStateEnum;

    public GameStateImpl() {
        this.iterator = new LevelIterator();
        this.initialize();
    }

    /**
     * Initializes the {@link GameState} setting the first {@link Level} to play.
     * The score is also cleared on the call of this procedure.
     */
    private void initialize() {
        this.gameStateEnum = StateEnum.WAITING_FOR_NEW_GAME;
        this.currentLevel = this.iterator.next();
        this.score = 0;
    }
    /**
     * {@inheritDoc}
     */
    @Override
    public Level getCurrentLevel() {
        return this.currentLevel;
    }
    /**
     * {@inheritDoc}
     */
    @Override
    public LevelIterator getLevelIterator() {
        return this.iterator;
    }
    /**
     * {@inheritDoc}
     */
    @Override
    public int getScore() {
        return this.score;
    }
    /**
     * {@inheritDoc}
     */
    @Override
    public StateEnum getState() {
        return this.gameStateEnum;
    }
    /**
     * {@inheritDoc}
     */
    @Override
    public void setState(final StateEnum stateEnum) {
        this.gameStateEnum = stateEnum;
    }
    /**
     * {@inheritDoc}
     */
    @Override
    public void reset() {
        this.getCurrentLevel().cleanUp();
        this.iterator = new LevelIterator();
        this.initialize();
    }
    /**
     * {@inheritDoc}
     */
    @Override
    public void selectStartingLevel(final int levelNumber) {
        this.getCurrentLevel().cleanUp();
        this.iterator = new LevelIterator(levelNumber);
        this.initialize();
    }
    /**
     * {@inheritDoc}
     */
    @Override
    public void switchLevel() {
        if (this.gameStateEnum != StateEnum.PAUSE) {
            this.setState(StateEnum.PAUSE);
        }
        this.currentLevel.cleanUp();
        if (this.iterator.hasNext()) {
            this.currentLevel = this.iterator.next();
        }
    }
    /**
     * {@inheritDoc}
     */
    @Override
    public void update(final double delta) {
        this.getCurrentLevel().update(delta);
    }
    /**
     * {@inheritDoc}
     */
    @Override
    public void updateTotalScore() {
        this.score += this.currentLevel.getScore();
    }
}
