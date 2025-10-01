package dev.leandrocardoso.tictactoe;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.freetype.FreeTypeFontGenerator;
import com.badlogic.gdx.utils.ScreenUtils;

/** {@link com.badlogic.gdx.ApplicationListener} implementation shared by all platforms. */
public class Main extends ApplicationAdapter {
    private SpriteBatch batch;
    private com.badlogic.gdx.graphics.g2d.GlyphLayout layout;

    private BitmapFont titleFont;
    private BitmapFont textFont;
    private String title;
    float titleHeight;
    float titleWidth;
    float textHeight;
    float textWidth;
    
    private Texture blackboardTexture;
    private Texture gridTexture;
    private Texture xTexture;
    private Texture oTexture;

    private Sprite blackboardSprite;
    private Sprite gridSprite;
    private Sprite xSprite;

    private BitmapFont criateFont(int size, Color color, String fileUrl) {
        BitmapFont font;
        FreeTypeFontGenerator generator = new FreeTypeFontGenerator(Gdx.files.internal(fileUrl));
        FreeTypeFontGenerator.FreeTypeFontParameter parameter = new FreeTypeFontGenerator.FreeTypeFontParameter();

        parameter.size = size;
        parameter.color = color;
        font = generator.generateFont(parameter);

        generator.dispose();

        return font;
    }

    @Override
    public void create() {
        batch = new SpriteBatch();
        layout = new com.badlogic.gdx.graphics.g2d.GlyphLayout();
        
        // TEXTURES:
        blackboardTexture = new Texture("images/blackboard.png");
        gridTexture = new Texture("images/grid.png");
        xTexture = new Texture("images/x.png");
        oTexture = new Texture("images/o.png");

        // SPRITES:
        blackboardSprite = new Sprite(blackboardTexture);
        gridSprite = new Sprite(gridTexture);
        xSprite = new Sprite(xTexture);

        // SET SIZE:
        if (Gdx.graphics.getWidth() > Gdx.graphics.getHeight()) {
            blackboardSprite.setSize(Gdx.graphics.getWidth(), Gdx.graphics.getWidth());
            gridSprite.setSize(Gdx.graphics.getWidth() / 1.5f, Gdx.graphics.getWidth() / 1.5f);
            xSprite.setSize(Gdx.graphics.getWidth() * 0.15f, Gdx.graphics.getWidth() * 0.15f);
            titleHeight = Gdx.graphics.getWidth() * 0.06f;
        }
        else {
            blackboardSprite.setSize(Gdx.graphics.getHeight(), Gdx.graphics.getHeight());
            gridSprite.setSize(Gdx.graphics.getHeight() / 1.5f, Gdx.graphics.getHeight() / 1.5f);
            xSprite.setSize(Gdx.graphics.getHeight() * 0.15f, Gdx.graphics.getHeight() * 0.15f);
            titleHeight = Gdx.graphics.getHeight() * 0.06f;
        }
        textHeight = titleHeight * 0.8f;

        // FONTS:
        titleFont = criateFont((int) titleHeight, Color.WHITE, "fonts/CabinSketch-Regular.ttf");
        textFont = criateFont((int) textHeight, Color.WHITE, "fonts/Schoolbell-Regular.ttf");

        title = "Tic-Tac-Toe";
        layout.setText(titleFont, title);
        titleWidth = layout.width;

        // SET POSITION:
        blackboardSprite.setPosition((Gdx.graphics.getWidth() / 2) - (blackboardSprite.getWidth() / 2), (Gdx.graphics.getHeight() / 2) - (blackboardSprite.getHeight() / 2));
        gridSprite.setPosition((Gdx.graphics.getWidth() * 0.7f / 2) - (gridSprite.getWidth() / 2), (Gdx.graphics.getHeight() / 2) - (gridSprite.getHeight() / 2) - (titleHeight / 2));
        xSprite.setPosition((Gdx.graphics.getWidth() * 0.7f / 2) - (xSprite.getWidth() / 2), (Gdx.graphics.getHeight() / 2) - (xSprite.getHeight() / 2) - (titleHeight / 2));
    }

    @Override
    public void render() {
        ScreenUtils.clear(0.15f, 0.15f, 0.2f, 1f);

        batch.begin();

        blackboardSprite.draw(batch);
        gridSprite.draw(batch);
        xSprite.draw(batch);
        
        titleFont.draw(batch, title, (Gdx.graphics.getWidth() / 2) - (titleWidth / 2), Gdx.graphics.getHeight() - titleHeight / 2);
        textFont.draw(batch, "X = ", Gdx.graphics.getWidth() * 0.7f, Gdx.graphics.getHeight() / 2 + textHeight);
        textFont.draw(batch, "O = ", Gdx.graphics.getWidth() * 0.7f, Gdx.graphics.getHeight() / 2);

        batch.end();
    }

    @Override
    public void dispose() {
        batch.dispose();

        titleFont.dispose();
        textFont.dispose();

        blackboardTexture.dispose();
        gridTexture.dispose();
        xTexture.dispose();
        oTexture.dispose();
    }
}
