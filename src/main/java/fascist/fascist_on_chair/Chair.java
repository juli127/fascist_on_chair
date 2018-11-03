package fascist.fascist_on_chair;

public class Chair {

	private volatile boolean isBusy = false;

	public void occupy() {
		isBusy = true;
	}

	public void free() {
		isBusy = false;
	}

}
