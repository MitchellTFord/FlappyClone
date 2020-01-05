package game;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;

import javax.swing.JButton;
import javax.swing.JPanel;

public class MenuState extends State
{
	public static int PANEL_WIDTH = 400;
	public static int PANEL_HEIGHT = 400;
	
	JButton testButton;
	
	public MenuState(Game game)
	{
		super(game);
		
		// TODO Auto-generated constructor stub
		contentPane = new JPanel(new BorderLayout());
		contentPane.setSize(PANEL_WIDTH, PANEL_HEIGHT);
		
		testButton = new JButton("play");
		testButton.setPreferredSize(new Dimension(200, 200));		
		testButton.addActionListener(this);
		contentPane.add(testButton);
	}

	@Override
	public void update()
	{
		// TODO Auto-generated method stub
		
	}

	@Override
	public void render()
	{
		// TODO Auto-generated method stub
		
	}
	
	@Override
	public void actionPerformed(ActionEvent e)
	{
		if(e.getSource() instanceof JButton)
		{
			if(e.getSource() == testButton)
			{
				game.switchToGameState();
			}
		}
	}

	@Override
	public void keyTyped(KeyEvent e)
	{
		// TODO Auto-generated method stub
		
	}

	@Override
	public void keyPressed(KeyEvent e)
	{
		// TODO Auto-generated method stub
		
	}

	@Override
	public void keyReleased(KeyEvent e)
	{
		// TODO Auto-generated method stub
		
	}

}
