package main;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class KeyHandler implements KeyListener {
	
	GPane gp;
	
	public boolean upPressed, downPressed, leftPressed, rightPressed, chatButtonPressed;
	public boolean checkDrawtime = false;
	
	public KeyHandler(GPane gp) {
		this.gp = gp;
	}

	@Override
	public void keyTyped(KeyEvent e) {
	}

	@Override
	public void keyPressed(KeyEvent e) {
		int code = e.getKeyCode();
		// title
		if(gp.gameState == gp.titleState) {
			titleState(code);
		}
		else if (gp.gameState == gp.playState) {
			playState(code);
		}
		else if(gp.gameState == gp.pauseState) {
			pauseState(code);
		}
		else if(gp.gameState == gp.chatState) {
			chatState(code);
		}
		// char
		else if(gp.gameState == gp.charState) {
			charState(code);
		}
	}
	public void titleState(int keyCode) {
		if(gp.gameState == gp.titleState) {
			if(keyCode == KeyEvent.VK_W) {
				gp.ui.commandNum--;
				if(gp.ui.commandNum < 0) gp.ui.commandNum = 2;
			}
			
			if(keyCode == KeyEvent.VK_S) {
				gp.ui.commandNum++;
				if(gp.ui.commandNum > 2) gp.ui.commandNum = 0;
			}
			
			if(keyCode == KeyEvent.VK_D) {
				if(gp.ui.commandNum == 0) {
					gp.gameState = gp.playState;
					//gp.stopMusic();
					gp.playMusic(2);
				}
				if(gp.ui.commandNum == 1) {
					//TODO: Add load function
				}
				if(gp.ui.commandNum == 2) {
					System.exit(0);;
				}
			}
		}
	}
	public void playState(int keyCode) {
		if(keyCode == KeyEvent.VK_W) {
			upPressed = true;
		}
		
		if(keyCode == KeyEvent.VK_S) {
			downPressed = true;
		}
		
		if(keyCode == KeyEvent.VK_A) {
			leftPressed = true;
		}
		
		if(keyCode == KeyEvent.VK_D) {
			rightPressed = true;
		}
		
		if(keyCode == KeyEvent.VK_ESCAPE) {
			gp.gameState = gp.pauseState;
		}
		
		if(keyCode == KeyEvent.VK_E) {
			chatButtonPressed = true;
		}
		if(keyCode == KeyEvent.VK_C) {
			gp.gameState = gp.charState;
		}
		
		
		// debug
		if(keyCode == KeyEvent.VK_F3) {
			if(checkDrawtime == false) {
				checkDrawtime = true;
			} else if (checkDrawtime == true) {
				checkDrawtime = false;
			}
		}
	}
	public void pauseState(int keyCode) {
		if(keyCode == KeyEvent.VK_ESCAPE) {
			gp.gameState = gp.playState;
		}
	}
	public void chatState(int keyCode) {
		if(keyCode == KeyEvent.VK_E) {
			gp.gameState = gp.playState;
		}
	}
	public void charState(int keyCode) {
		if(keyCode == KeyEvent.VK_C) {
			gp.gameState = gp.playState;
		}
	}

	@Override
	public void keyReleased(KeyEvent e) {
		int code = e.getKeyCode();
		
		if(code == KeyEvent.VK_W) {
			upPressed = false;
		}
		
		if(code == KeyEvent.VK_S) {
			downPressed = false;
		}

		if(code == KeyEvent.VK_A) {
			leftPressed = false;
		}
		
		if(code == KeyEvent.VK_D) {
			rightPressed = false;
		}
	}

}
