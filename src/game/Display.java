package game;

import java.awt.event.KeyListener;

import javax.swing.JFrame;

public class Display extends JFrame
{
	public Display(State state)
	{
		super("FlappyClone");

		setDefaultCloseOperation(EXIT_ON_CLOSE); // Exit when clicking close button
		setResizable(false); // Not resizable
		setLocationRelativeTo(null); // Centered on screen

		changeState(state);
	}

	public synchronized void changeState(State state)
	{
		setVisible(false);
		
		setSize(state.getContentPane().getWidth() * 2, state.getContentPane().getHeight() * 2);
		
		setContentPane(state.getContentPane());

		// Remove all current KeyListeners
		for(KeyListener el : getKeyListeners())
		{
			removeKeyListener(el);
		}
		
		// Add the new State as the only KeyListener
		addKeyListener(state);
		
		pack();

		state.contentPane.revalidate();
		revalidate();
		
		setVisible(true);
	}
}
