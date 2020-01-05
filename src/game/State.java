package game;

import java.awt.Graphics;
import java.awt.event.ActionListener;
import java.awt.event.KeyListener;

import javax.swing.JPanel;

public abstract class State implements ActionListener, KeyListener
{
	protected Game game;
	
	protected JPanel contentPane;
	
	public State(Game game)
	{
		this.game = game;
	}
	
	public abstract void update();

	public abstract void render();

	public JPanel getContentPane()
	{
		return contentPane;
	}
}
