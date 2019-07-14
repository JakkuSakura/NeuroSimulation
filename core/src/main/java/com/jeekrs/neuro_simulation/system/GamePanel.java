package com.jeekrs.neuro_simulation.system;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Pixmap;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.collision.Ray;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.Drawable;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;
import com.jeekrs.neuro_simulation.Species.Species;
import com.jeekrs.neuro_simulation.entities.BaseCamp;
import com.jeekrs.neuro_simulation.entities.Entity;
import com.jeekrs.neuro_simulation.entities.nest.Nest;
import com.jeekrs.neuro_simulation.renderers.NestRenderer;

import java.util.ArrayList;

import static com.jeekrs.neuro_simulation.GameScreen.systemManager;

public class GamePanel extends UIComponent {
    public Stage stage = new Stage();
    private Table table = new Table();
    private Skin skin = new Skin(Gdx.files.internal("skin/uiskin.json"));
    private Label left = new Label("GamePanel", skin);
    private Label middle = new Label("Information", skin);
    private Label right = new Label("Information", skin);
    private Table table2 = new Table();


    public Drawable getBackgroundColor(Color color) {
        Pixmap bgPixmap = new Pixmap(1, 1, Pixmap.Format.RGB565);
        bgPixmap.setColor(color);
        bgPixmap.fill();
        return new TextureRegionDrawable(new TextureRegion(new Texture(bgPixmap)));

    }

    private int height = 200;

    @Override
    void init() {
        stage = new Stage();
//        stage.setDebugAll(true);


        skin = new Skin(Gdx.files.internal("skin/uiskin.json"));
        table = new Table();

        stage.addActor(table);

        table.setPosition(0, 0);

        table.setSize(Gdx.graphics.getWidth(), height);
//        table.setFillParent(true);
        table.setBackground(getBackgroundColor(Color.OLIVE));


        left.getStyle().font = TextHelper.font;
        middle.getStyle().font = TextHelper.font;
        right.getStyle().font = TextHelper.font;

        left.setScale(2);
        middle.setScale(2);
        right.setScale(2);

        table.add(left).padRight(20);
        table.add(middle).padRight(20);
        table.add(right).padRight(20);

        table.add(table2);
        systemManager.inputSystem.inputStack.stack.addFirst(stage);

    }

    private Nest selected_nest;
    private Species species;
    private NestRenderer renderer = new NestRenderer();

    @Override
    void update() {
        {
            StringBuilder sb = new StringBuilder();
            sb.append(String.format("You are %s\n", systemManager.agendaSystem.agendas.get(systemManager.agendaSystem.playerAgenda)));

//            sb.append(String.format("You have %.2f units of food\n", systemManager.resourceSystem.getFood(systemManager.agendaSystem.playerAgenda)));
            middle.setText(sb);
        }

        {
            StringBuilder sb = new StringBuilder();
            Entity selected = systemManager.inputSystem.picker.selected;
            if (selected != null) {
                sb.append("You chose ").append(selected.toString()).append("\n");
                if (selected_nest != null)
                    sb.append(String.format("You are putting %s\n", selected_nest.toString()));
            }
            right.setText(sb);
            if (selected instanceof BaseCamp && ((BaseCamp) selected).getAgenda() == systemManager.agendaSystem.playerAgenda) {
                if (species != ((BaseCamp) selected).getSpecies()) {
                    species = ((BaseCamp) selected).getSpecies();
                    table2.clearChildren();
                    ArrayList<Nest> nests = species.getNests();
                    TextButton.TextButtonStyle textButtonStyle = skin.get("default", TextButton.TextButtonStyle.class);
                    for (Nest nest : nests) {
                        TextButton actor = new TextButton(nest.toString(), textButtonStyle);
                        final boolean[] processed = {false};
                        actor.addListener(event -> {
                            if (actor.isPressed() && !processed[0]) {
                                selected_nest = (Nest) nest.clone();
                                selected_nest.setAgenda(((BaseCamp) selected).getAgenda());
                                processed[0] = true;
                                systemManager.inputSystem.inputStack.stack.addFirst(new SimpleInputProcessor() {
                                    @Override
                                    public boolean mouseMoved(int screenX, int screenY) {

                                        if (selected_nest != null) {
                                            Ray ray = systemManager.renderSystem.camera.getPickRay(screenX, screenY);
                                            selected_nest.getPos().set(ray.origin.x, ray.origin.y);
                                            systemManager.inputSystem.picker.selected = selected_nest;
                                            return true;
                                        } else {
                                            systemManager.inputSystem.inputStack.toDel.add(this);
                                        }
                                        return false;
                                    }

                                    @Override
                                    public boolean touchDown(int screenX, int screenY, int pointer, int button) {

                                        if (selected_nest != null) {
                                            systemManager.entitySystem.addEntity(selected_nest);
//                                            systemManager.resourceSystem.addFood();
                                            selected_nest = null;
                                            return true;
                                        }
                                        return false;
                                    }


                                });
                                return true;
                            }
                            return false;
                        });
                        table2.add(actor);
                        table2.row();
                    }
                }
                table2.setVisible(true);
            } else {
                species = null;
                table2.setVisible(false);
            }
        }

        stage.act();
        stage.draw();
        if (selected_nest != null)
        {
            renderer.batch.setProjectionMatrix(systemManager.renderSystem.camera.combined);
            renderer.batch.begin();
            renderer.renderNest(selected_nest);
//            System.out.println(selected_nest.getRectangle());
            renderer.batch.end();
        }
    }

    @Override
    void resize(int w, int h) {
        stage.getViewport().update(w, h, true);
    }

    @Override
    void dispose() {
        skin.dispose();
        stage.dispose();
        renderer.dispose();
        systemManager.inputSystem.inputStack.stack.removeFirst();
    }
}
