package animation;
import biuoop.DrawSurface;
import biuoop.GUI;
import biuoop.KeyboardSensor;
import geometry.Boundary;
import geometry.Point;
import geometry.Rectangle;
import helpers.Counter;
import helpers.GameEnvironment;
import levels.LevelInformation;
import helpers.Velocity;
import listeners.BallRemover;
import listeners.BlockRemover;
import listeners.HitListener;
import listeners.ListenerCollection;
import listeners.ScoreTrackingListener;
import sprites.Ball;
import sprites.Block;
import sprites.Collidable;
import sprites.LevelName;
import sprites.LivesIndicator;
import sprites.Paddle;
import sprites.ScoreIndicator;
import sprites.Sprite;
import sprites.SpriteCollection;
import java.awt.Color;
import java.util.ArrayList;
import java.util.List;

/**
 * The class represents the "Arknoid" game.
 *
 * @author Daniel Hermon.
 */
public class GameLevel implements Animation {
    // class members.
    private SpriteCollection sprites;
    private GameEnvironment environment;
    private GUI graphicUI;
    private DrawSurface gameDrawSurface;
    private KeyboardSensor keyBS;
    private BlockRemover blockRemover;
    private BallRemover ballRemover;
    private LivesIndicator livesIndicator;
    private AnimationRunner runner;
    private boolean running;
    private Counter score;
    private ListenerCollection list;
    private ScoreTrackingListener scoreListener;
    private ScoreIndicator scoreIndicator;
    private LevelInformation levelInfo;
    private Paddle paddle;
    /**
     * Creates a game object.
     * A default Constructor.
     */
    public GameLevel() {
        this.sprites = new SpriteCollection();
        this.environment = new GameEnvironment();
        this.runner = new AnimationRunner(this.graphicUI, 60);
        this.blockRemover = new BlockRemover(this, new Counter());
        this.ballRemover = new BallRemover(this, new Counter());
        this.livesIndicator = new LivesIndicator();
        this.score = new Counter();
        this.list = new ListenerCollection();
        this.scoreListener = new ScoreTrackingListener(score);
        this.scoreIndicator = new ScoreIndicator(this);
    }
    /**
     * The method creates a game level.
     * @param lInfo -The level information.
     */
    public GameLevel(LevelInformation lInfo) {
        this();
        this.levelInfo = lInfo;
        this.blockRemover.getBlocksCounter().increase(this.levelInfo.numberOfBlocksToRemove());
        this.ballRemover.getBallCounter().increase(this.levelInfo.numberOfBalls());
        this.livesIndicator.getliveCounter().increase(5);
        this.graphicUI = new GUI("Arknoid", 800, 600);
        this.keyBS = this.graphicUI.getKeyboardSensor();
        this.gameDrawSurface = this.graphicUI.getDrawSurface();
        this.runner = new AnimationRunner(this.getGraphicUI(), 60);
    }
    /**
     * The method Creates new game level object.
     * @param levelInfo -The level information.
     * @param ks -The keyboard sensor.
     * @param ar -Animation runner.
     * @param score -Score.
     * @param lives -Number of lives.
     */
    public GameLevel(LevelInformation levelInfo, KeyboardSensor ks, AnimationRunner ar, int score, int lives) {
        this();
        this.levelInfo = levelInfo;
        this.blockRemover.getBlocksCounter().increase(this.levelInfo.numberOfBlocksToRemove());
        this.ballRemover.getBallCounter().increase(this.levelInfo.numberOfBalls());
        this.score.increase(score);
        this.runner = ar;
        this.graphicUI = ar.getGui();
        this.gameDrawSurface = this.graphicUI.getDrawSurface();
        this.keyBS = this.graphicUI.getKeyboardSensor();
        this.livesIndicator.getliveCounter().increase(lives);
    }

    /**
     * The method adds a colliadble to the game.
     *
     * @param c - the colliadable we want to add.
     */
    public void addCollidable(Collidable c) {
        this.environment.addCollidable(c);
    }

    /**
     * THe method adds a sprite object to the game.
     *
     * @param s - The spirte object we want to add.
     */
    public void addSprite(Sprite s) {
        this.sprites.addSprite(s);
    }

    /**
     * The method sets a GUI for the game.
     *
     * @param graphicui The new GUI for the game.
     */
    public void setGUI(GUI graphicui) {
        this.graphicUI = graphicui;
    }

    /**
     * The method initializes a ball, blocks and a paddle for the game.
     */
    public void initialize() {
        this.createBackground();
        this.createBorders();
        this.createBlocks();
        this.createLevelNameLabel();
        this.list.addListener(this.ballRemover);
        this.list.addListener(this.scoreListener);
        this.list.addListener(this.blockRemover);
        this.addSprite(this.livesIndicator);
        this.addSprite(this.scoreIndicator);
    }

    private void createBorders() {
        Block leftBorder =
                new Block(new Rectangle(new Point(0, 40), 20, 600), Color.GRAY);
        Block upperBorder =
                new Block(new Rectangle(new Point(0, 40), 800, 20), Color.GRAY);
        Block lowerBorder =
                new Block(new Rectangle(new Point(0, 595), 800, 20), Color.BLACK);
        Block rightBorder =
                new Block(new Rectangle(new Point(780, 40), 20, 600), Color.GRAY);
        leftBorder.addToGame(this);
        upperBorder.addToGame(this);
        lowerBorder.turnBlockToDeathBlock();
        lowerBorder.addHitListener(this.ballRemover);
        lowerBorder.addToGame(this);
        rightBorder.addToGame(this);
    }

    /**
     * The method creates the level name.
     */
    private void createLevelNameLabel() {
        LevelName lvlName = new LevelName(this.levelInfo.levelName());
        this.addSprite(lvlName);
    }
    /**
     * The method creates the balls.
     */
    private void createBall() {
        List<Velocity> veloList = this.levelInfo.initialBallVelocities();
        for (int i = 0; i < veloList.size(); i++) {
            double xBall = this.getPaddle().getWidth() / 2 + this.getPaddle().getUpperLeft().getX();
            double yBall = this.getPaddle().getUpperLeft().getY() - 30;
            Ball b = new Ball(new Point(xBall, yBall), 3, Color.magenta, this.environment);
            b.setVelocity(veloList.get(i).getDX(), veloList.get(i).getDY());
            b.setBoundary(new Boundary(this.gameDrawSurface.getHeight(), 0, 0, this.gameDrawSurface.getWidth()));
            b.addToGame(this);
        }

    }
    /**
     * The method creates the paddle.
     */
    private void createBackground() {
        this.addSprite(this.levelInfo.getBackground());

    }
    /**
     * The method creates the paddle.
     */
    public void createPaddle() {
        Rectangle rect = new Rectangle(new Point(400 - this.levelInfo.paddleWidth() / 2, 570),
                                                            this.levelInfo.paddleWidth(), 10);
        Block b = new Block(rect, Color.GREEN);
        this.paddle = new Paddle(this.keyBS, b, this.levelInfo.paddleSpeed());
        this.paddle.setBoundary(
                new Boundary(this.gameDrawSurface.getHeight(), 0, 20, this.gameDrawSurface.getWidth() - 20));
        this.paddle.addToGame(this);
    }
    /**
     * The method creates blocks for the game.
     */
    public void createBlocks() {
        List<Block> listOfBlocks = this.levelInfo.blocks();
        Block b;
        for (int i = 0; i < listOfBlocks.size(); i++) {
            b = listOfBlocks.get(i);
            b.addToGame(this);
            b.setListenerList(this.getListenersList());
        }
    }

    /**
     * The method runs the animation loop.
     */
    public void playOneTurn() {
        this.createPaddle(); // or a similar method
        this.createBall();
        this.ballRemover.getBallCounter().changeValue(this.levelInfo.numberOfBalls());
        this.runner.run(new CountdownAnimation(3, 3, this.sprites, this.graphicUI));
        this.running = true;
        // use our runner to run the current animation -- which is one turn of
        // the game.
        this.runner.run(this);
        if (this.blockRemover.getBlocksCounter().getValue() < 1) {
            this.scoreListener.increaseCounter(100);
        }
        this.setPaddleToCenter();

    }
    /**
     * The method runs the animation.
     */
    public void run() {
        this.initialize();
        while (this.livesIndicator.getliveCounter().getValue() > 0) {
            this.playOneTurn();
            if (this.ballRemover.getBallCounter().getValue() < 1) {
                this.livesIndicator.getliveCounter().decrease(1);
            }
        }
        return;
    }

    /**
     * The function removes the block from the colliadables objects.
     *
     * @param c -The colliadable object that is ought to be removed.
     */
    public void removeColliadable(Collidable c) {
        this.environment.getColliadList().remove(c);
    }

    /**
     * The function removes the block from the sprite objects.
     * @param s -The sprite that is ought to be removed.
     */
    public void removeSprite(Sprite s) {
        this.sprites.getArray().remove(s);
    }
    /**
     * The method sets paddle to the center.
     */
    public void setPaddleToCenter() {
        this.removeColliadable(this.paddle);
        this.removeSprite(this.paddle);
    }
    /**
     * The method returns the GUI.
     * @return the GUI.
     */
    public GUI getGraphicUI() {
        return this.graphicUI;
    }
    /**
     * The method draws a frame of the game.
     * @param d -The draw surface which we draw on.
     */
    @Override
    public void doOneFrame(DrawSurface d, double dt) {
        if (this.keyBS.isPressed("p")) {
            this.runner.run(new PauseScreen(this.keyBS));
            CountdownAnimation ani = new CountdownAnimation(3, 3, this.sprites, this.graphicUI);
            ani.doOneFrame(d, dt);
        }
        this.sprites.drawAllOn(d);
        this.sprites.notifyAllTimePassed(dt);
    }
    /**
     * The method returns if game should stop.
     * @return if game should stop.
     */
    @Override
    public boolean shouldStop() {
        if (this.ballRemover.getBallCounter().getValue() < 1 || this.blockRemover.getBlocksCounter().getValue() < 1) {
            return true;
        }
        return false;
    }
    /**
     * The method returns the sprite collection.
     * @return the sprite collection.
     */
    public SpriteCollection getSprites() {
        return this.sprites;
    }
    /**
     * The method returns the score.
     * @return the score.
     */
    public Counter getScore() {
        return this.score;
    }
    /**
     * The method returns the listener list.
     * @return the listener list.
     */
    public ArrayList<HitListener> getListenersList() {
        return this.list.getArray();
    }
    /**
     * The method returns the live indicator.
     * @return the live indicator.
     */
    public LivesIndicator getLiveIndicator() {
        return this.livesIndicator;
    }
    /**
     * The method returns the block remover.
     * @return the block remover.
     */
    public BlockRemover getBlockRemover() {
        return this.blockRemover;
    }
    /**
     * The method returns the ball remover.
     * @return the ball remover.
     */
    public BallRemover getBallRemover() {
        return this.ballRemover;
    }
    /**
     * The method returns the paddle.
     * @return the paddle.
     */
    public Paddle getPaddle() {
        return this.paddle;
    }
}