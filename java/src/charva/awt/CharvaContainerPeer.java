package charva.awt;

import java.awt.Insets;
import java.awt.peer.ContainerPeer;

public class CharvaContainerPeer extends CharvaComponentPeer implements ContainerPeer {

    boolean paintPending = false;
    boolean isLayouting = false;
    boolean enabled;

	public void beginLayout() {
    	isLayouting = true;
	}

	@Override
	public void beginValidate() {
		// TODO Auto-generated method stub
		
	}

	public void endLayout() {
    	isLayouting = false;
	}

	@Override
	public void endValidate() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public Insets getInsets() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Insets insets() {
		// TODO Auto-generated method stub
		return null;
	}

	public boolean isPaintPending() {
		return paintPending;
	}

	@Override
	public boolean isRestackSupported() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public void restack() {
		// TODO Auto-generated method stub
		
	}

}
