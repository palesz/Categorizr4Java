package net.palesz.categorizr4java;

public class Options {

	private boolean categorizeTabletsAsDesktop = false;
	private boolean categorizeTVsAsDesktop = false;

	public boolean isCategorizeTabletsAsDesktop() {
		return categorizeTabletsAsDesktop;
	}

	public void setCategorizeTabletsAsDesktop(boolean categorizeTabletsAsDesktop) {
		this.categorizeTabletsAsDesktop = categorizeTabletsAsDesktop;
	}

	public boolean isCategorizeTVsAsDesktop() {
		return categorizeTVsAsDesktop;
	}

	public void setCategorizeTVsAsDesktop(boolean categorizeTVsAsDesktop) {
		this.categorizeTVsAsDesktop = categorizeTVsAsDesktop;
	}
}
